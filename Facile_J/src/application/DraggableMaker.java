package application;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.PickResult;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import java.util.Comparator;

public class DraggableMaker {
	private double mouseAnchorX;
	private double mouseAnchorY;
	private double scrollXOffset;
	private double scrollYOffset;
	private Node p;

	public void makeDraggable(Node node){

		// Código no necesario del todo, sirve para tratar con el padre en vez de con el que arrastra
		if(node.getStyleClass().contains("drag")) {
			p = node.getParent();
		}

		node.setOnMousePressed(mouseEvent -> {
			// Manda el nodo al frente
			node.toFront();
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
				dexpand(vb);

			}else if(p.getParent() instanceof HBox) {
				HBox hb = (HBox) p.getParent();
				p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
				p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
				hb.getChildren().remove(p);
				root2.getChildren().add(p);
				dexpand(hb);

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
					expand(vb);

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
					expand(hb);

				}else if(fBox instanceof HBox && node.getStyleClass().contains("O") && fBox.getStyleClass().contains("one")){
					hb = (HBox) fBox;
					if(hb.getChildren().size() == 0) {
						hb.getChildren().add(p);
						expand(hb);
					}
				}
			}

		});

	}

	public void dexpand (Node son) {
		VBox vs = null;
		HBox hs = null;
		AnchorPane ap = null;
		double h = 0;
		double w = 0;
		boolean dexp = false;
		//Lograr altura y anchura
		if(son instanceof HBox) {
			hs = (HBox) son;
			ap = (AnchorPane) hs.getParent();
			for (Node n : hs.getChildren()) {
				AnchorPane n2 = (AnchorPane)n;
				w += n2.getPrefWidth();
				if(h < n2.getPrefHeight()) {
					h = n2.getPrefHeight();
				}
			}  	


			if (w < hs.getPrefWidth() ) {
				if(w >= hs.getMinWidth()) {
					hs.setPrefWidth(w+30);
				}else {
					hs.setPrefWidth(hs.getMinWidth());
				}
				double max_p_w = hs.getPrefWidth()+hs.getLayoutX();
				ObservableList<Node> brothers  = ap.getChildren();
				for(Node b : brothers) {
					if(b instanceof HBox) {
						HBox hb = (HBox)b;
						if(hb.getPrefWidth()+hb.getLayoutX() > max_p_w) {
							max_p_w = hb.getPrefWidth()+hb.getLayoutX();
						}
					}else if(b instanceof VBox) {
						VBox vb = (VBox) b;
						if(vb.getPrefWidth()+vb.getLayoutX() > max_p_w) {
							max_p_w = vb.getPrefWidth()+vb.getLayoutX();
						}
					}
				}
				if(ap.getPrefWidth() > max_p_w+30) {
					ap.setPrefWidth(max_p_w+30);
					dexp = true;
				}  

			}

			if (h < hs.getPrefHeight() ) {
				double diff ;
				if(h >= hs.getMinHeight()) {
					diff = hs.getPrefHeight()-h;
					hs.setPrefHeight(h);

				}else {
					diff = hs.getPrefHeight() - hs.getMinHeight();
					hs.setPrefHeight(hs.getMinHeight());
				}
				if(diff > 0) {
					VBox down = (VBox)ap.lookup(".vbox");

					if(down != null) {
						down.setLayoutY(down.getLayoutY()-diff);
						ap.setPrefHeight(ap.getPrefHeight()-diff);
						dexp = true;
					}
				}

			}

			if(dexp) {
				if(!ap.getId().equals("Class")) {
					dexpand(hs.getParent().getParent());
				}
			}	


		}else if(son instanceof VBox){
			vs = (VBox) son;
			ap = (AnchorPane) vs.getParent();
			for (Node n : vs.getChildren()) {
				AnchorPane n2 = (AnchorPane)n;
				h += n2.getPrefHeight();
				if(w < n2.getPrefWidth()) {
					w = n2.getPrefWidth();
				}
			}
			if (w < vs.getPrefWidth() ) {
				if(w >= vs.getMinWidth()) {
					vs.setPrefWidth(w);
				}else {
					vs.setPrefWidth(vs.getMinWidth());
				}
				double max_p_w = vs.getPrefWidth()+vs.getLayoutX();
				ObservableList<Node> brothers  = ap.getChildren();
				for(Node b : brothers) {
					if(b instanceof HBox) {
						HBox hb = (HBox)b;
						if(hb.getPrefWidth()+hb.getLayoutX() > max_p_w) {
							max_p_w = hb.getPrefWidth()+hb.getLayoutX();
						}
					}else if(b instanceof VBox) {
						VBox vb = (VBox) b;
						if(vb.getPrefWidth()+vb.getLayoutX() > max_p_w) {
							max_p_w = vb.getPrefWidth()+vb.getLayoutX();
						}
					}
				}
				if(ap.getPrefWidth() > max_p_w+30) {
					ap.setPrefWidth(max_p_w+30);
					dexp = true;
				}  

			}

			// En teoría esto está arreglado (Revisa los otros tres)
			if (h < vs.getPrefHeight() ) {
				if(w >= vs.getMinHeight()) {
					vs.setPrefHeight(h+30);
				}else {
					vs.setPrefHeight(vs.getMinHeight());
				}
				double max_p_h = vs.getPrefHeight()+vs.getLayoutY();
				ObservableList<Node> brothers  = ap.getChildren();
				for(Node b : brothers) {
					if(b instanceof HBox) {
						HBox hb = (HBox)b;
						if(hb.getPrefHeight()+hb.getLayoutY() > max_p_h) {
							max_p_h = hb.getPrefHeight()+hb.getLayoutY();
						}
					}else if(b instanceof VBox) {
						VBox vb = (VBox) b;
						if(vb.getPrefHeight()+vb.getLayoutY() > max_p_h) {
							max_p_h = vb.getPrefHeight()+vb.getLayoutY();
						}
					}
				}
				if(ap.getPrefHeight() > max_p_h+30) {
					ap.setPrefHeight(max_p_h+30);
					dexp = true;
				}  

			}

			if(dexp) {
				if(!ap.getId().equals("Class")) {
					dexpand(vs.getParent().getParent());
				}
			}
		}
	}


	public void expand (Node son) {
		VBox vs = null;
		HBox hs = null;
		AnchorPane ap = null;
		double h = 0;
		double w = 0;
		if(son instanceof HBox) {
			hs = (HBox) son;
			ap = (AnchorPane) hs.getParent();
			boolean exp = false;
			for (Node n : hs.getChildren()) {
				AnchorPane n2 = (AnchorPane)n;
				w += n2.getPrefWidth();
				if(h < n2.getPrefHeight()) {
					h = n2.getPrefHeight();
				}
			}  			
			if (w > hs.getPrefWidth()) {
				hs.setPrefWidth(w+30);
				double total_w_dist = hs.getLayoutX() + w+50;
				if(total_w_dist > ap.getPrefWidth()) {
					ap.setPrefWidth(total_w_dist);
					exp = true;
				}  
			}



			if (h > hs.getPrefHeight()) {
				hs.setPrefHeight(h+10);
				VBox down = (VBox)ap.lookup(".vbox");
				down.setLayoutY(down.getLayoutY()+10);
				ap.setPrefHeight(ap.getPrefHeight()+10);
				exp = true;
			}


			if(exp) {
				if(!ap.getId().equals("Class")) {
					expand(hs.getParent().getParent());
				}else {
					endReach(ap);
				}
			}	
		}else if(son instanceof VBox){
			vs = (VBox) son;
			ap = (AnchorPane) vs.getParent();
			boolean exp = false;
			for (Node n : vs.getChildren()) {
				AnchorPane n2 = (AnchorPane)n;
				h += n2.getPrefHeight();
				if(w < n2.getPrefWidth()) {
					w = n2.getPrefWidth();
				}
			}
			if (w > vs.getPrefWidth()) {
				vs.setPrefWidth(w );
				double total_w_dist = vs.getLayoutX() + w+30;
				if(total_w_dist > ap.getPrefWidth()) {
					ap.setPrefWidth(total_w_dist);
					exp = true;
				} 
			}
			if (h > vs.getPrefHeight()) {
				vs.setPrefHeight(h+30);
				double total_h_dist = vs.getLayoutY() + h+50;
				if(total_h_dist > ap.getPrefHeight()) {
					ap.setPrefHeight(total_h_dist);
					exp = true;
				} 
			}


			if(exp) {
				if(!ap.getId().equals("Class")) {
					expand(vs.getParent().getParent());
				}else {
					endReach(ap);
				}
			}
		}
	}

	public void endReach(AnchorPane p) {
		ScrollPane sp = null;
		AnchorPane ap = null;
		sp = (ScrollPane)p.getScene().getRoot();
		ap = (AnchorPane)sp.getContent();
		double totalh = p.getLayoutY()+p.getPrefHeight()+100;
		double totalw = p.getLayoutX()+p.getPrefWidth()+100;
		sp.setPrefHeight(totalh);
		ap.setPrefHeight(totalh);
		sp.setPrefWidth(totalw);
		ap.setPrefWidth(totalw);
	}

	public double getcenterY (Node n) {
		return n.getLayoutY() + n.getBoundsInParent().getHeight()/2;
	}

	public double getcenterX (Node n) {
		return n.getLayoutX() + n.getBoundsInParent().getWidth()/2;
	}

}
