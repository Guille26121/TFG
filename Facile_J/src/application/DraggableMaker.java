package application;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Set;
import javafx.scene.image.ImageView;
import application.Expander;

public class DraggableMaker {
	private double mouseAnchorX;
	private double mouseAnchorY;
	private double scrollXOffset;
	private double scrollYOffset;
	private Node p;
	private Expander exp = new Expander();

	public void makeDraggable(Node node){

		// Código no necesario del todo, sirve para tratar con el padre en vez de con el que arrastra
		if(node.getStyleClass().contains("drag")) {
			p = node.getParent();
		}

		node.setOnMousePressed(mouseEvent -> {
			// Manda el nodo al frente
			p.toFront();
			ScrollPane sp = (ScrollPane) node.getScene().getRoot();
			// Posición del ratón
			mouseAnchorX = mouseEvent.getX();
			mouseAnchorY = mouseEvent.getY();
			// Cantidad de pixeles scrolleados
			scrollXOffset = sp.getHvalue() * (sp.getPrefWidth() -node.getScene().getWidth());//(sp.getContent().getBoundsInLocal().getWidth() - sp.getViewportBounds().getWidth());
			scrollYOffset = sp.getVvalue() * (sp.getPrefHeight() -node.getScene().getHeight());//(sp.getContent().getBoundsInLocal().getHeight() - sp.getViewportBounds().getHeight());
			ScrollPane root = (ScrollPane)p.getScene().getRoot();
			AnchorPane root2 = (AnchorPane)root.getContent();
			// Extraer nodo de VBox o de HBox
			if(p.getParent() instanceof VBox ) {
				VBox vb = (VBox) p.getParent();
				p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
				p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
				vb.getChildren().remove(p);
				root2.getChildren().add(p);
				if(vb.getStyleClass().contains("imp")) {
					exp.dexpandImp(vb);
				}else {
					exp.dexpand(vb);
				}

			}else if(p.getParent() instanceof HBox) {
				HBox hb = (HBox) p.getParent();
				p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
				p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
				hb.getChildren().remove(p);
				root2.getChildren().add(p);
				exp.dexpand(hb);

			}
		});

		// Calcula la posición por el arrastre
		node.setOnMouseDragged(mouseEvent -> {
			ScrollPane sp = (ScrollPane) node.getScene().getRoot();
			scrollXOffset = sp.getHvalue() * (sp.getPrefWidth() -node.getScene().getWidth());//(sp.getContent().getBoundsInLocal().getWidth() - sp.getViewportBounds().getWidth());
			scrollYOffset = sp.getVvalue() * (sp.getPrefHeight() -node.getScene().getHeight());//(sp.getContent().getBoundsInLocal().getHeight() - sp.getViewportBounds().getHeight());
			p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
			p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
		}); 


		node.setOnMouseReleased(mouseEvent -> {
			Node  fBox = null;
			// Obtiene donde se puede dropear el nodo
			Set<Node> containers = p.getScene().getRoot().lookupAll(".drop");
			ScrollPane sp = (ScrollPane) node.getScene().getRoot();
			scrollXOffset = sp.getHvalue() * (sp.getPrefWidth() -node.getScene().getWidth());//(sp.getContent().getBoundsInLocal().getWidth() - sp.getViewportBounds().getWidth());
			scrollYOffset = sp.getVvalue() * (sp.getPrefHeight() -node.getScene().getHeight());//(sp.getContent().getBoundsInLocal().getHeight() - sp.getViewportBounds().getHeight());
			mouseAnchorX = mouseEvent.getSceneX();
			mouseAnchorY = mouseEvent.getSceneY();
			Point2D centerInScene =  new Point2D(mouseAnchorX+scrollXOffset, mouseAnchorY+scrollYOffset);
			ImageView trash = (ImageView)p.getScene().getRoot().lookup("#Trash");
			// Cacula si se está dropeando en la basura
			if(trash.getBoundsInParent().contains(centerInScene)) {
				ScrollPane root = (ScrollPane)p.getScene().getRoot();
				AnchorPane root2 = (AnchorPane)root.getContent();
				root2.getChildren().remove(p);
				return;
			}

			// Revisa que nodo contiene la posición del que estamos dropeando
			for (Node n : containers) {
				boolean t = false;
				if(n.getId() == null) {
					t = true;
				}else {
					t = n.getId().equals(p.getId());
					t= !t;
				}
				Point2D point = new Point2D(mouseAnchorX, mouseAnchorY);      
				if (n.contains(n.sceneToLocal(point)) && t) {
					fBox =  n;
				}
			}
			VBox vb;
			HBox hb;

			// Revisa si este nodo al que dropeamos es nulo, en caso de no serlo revisa si es HBox o VBox, si se puede y si es así lo añade y expande
			if (fBox != null) {
				double compY = 0;
				double compX = 0;
				if(fBox instanceof VBox && (node.getStyleClass().contains("V") || node.getStyleClass().contains("B"))) {
					vb = (VBox) fBox;

					double layout = vb.getLayoutY();
					Node findL = vb;
					do{
						findL = findL.getParent();
						layout += findL.getLayoutY();
					}while(!findL.getId().equals("Class"));
					compY = mouseEvent.getSceneY()-layout+scrollYOffset;
					int index = vb.getChildren().size();
					ObservableList<Node> others = vb.getChildren();
					for (Node o : others) {

						// SORPRESA SORPRESA, PROBLEMAS DE SCROLL XD
						if (compY >= o.getLayoutY() && compY <= o.getLayoutY() + o.getBoundsInParent().getHeight()) {
							index = vb.getChildren().indexOf(o);
						}
					}
					vb.getChildren().add(index,p);
					exp.expand(vb);

				}else if(fBox instanceof HBox && (node.getStyleClass().contains("H") || node.getStyleClass().contains("B")) && !fBox.getStyleClass().contains("one")) {
					hb = (HBox) fBox;
					double layout = hb.getLayoutX();
					Node findL = hb;
					do{
						findL = findL.getParent();
						layout += findL.getLayoutX();
					}while(!findL.getId().equals("Class"));
					compX = mouseEvent.getSceneX()-layout+scrollXOffset;
					int index = hb.getChildren().size();
					ObservableList<Node> others = hb.getChildren();
					for (Node o : others) {
						// POR CONSIGUIENTE AQUí TAMBIÉN 
						if (compX >= o.getLayoutX() && compX <= o.getLayoutX() + o.getBoundsInParent().getWidth()) {
							index = hb.getChildren().indexOf(o);
						}
					}
					hb.getChildren().add(index,p);
					exp.expand(hb);

				}else if(fBox instanceof HBox && node.getStyleClass().contains("O") && fBox.getStyleClass().contains("one")){
					hb = (HBox) fBox;
					if(hb.getChildren().size() == 0) {
						hb.getChildren().add(p);
						exp.expand(hb);
					}
				}else if(fBox instanceof VBox && node.getStyleClass().contains("imp") && fBox.getStyleClass().contains("imp")) {
					System.out.println("Entra");
					vb = (VBox) fBox;
					double layout = vb.getLayoutY();
					Node findL = vb;
					do{
						findL = findL.getParent();
						layout += findL.getLayoutY();
					}while(!findL.getId().equals("Imp"));
					compY = mouseEvent.getSceneY()-layout+scrollYOffset;
					int index = vb.getChildren().size();
					ObservableList<Node> others = vb.getChildren();
					for (Node o : others) {
						if (compY >= o.getLayoutY() && compY <= o.getLayoutY() + o.getBoundsInParent().getHeight()) {
							index = vb.getChildren().indexOf(o);
						}
					}
					vb.getChildren().add(index,p);
					exp.expandImp(p);// Cambiar el método expand xd
				}
			}

		});

	}
	
	
	

}
