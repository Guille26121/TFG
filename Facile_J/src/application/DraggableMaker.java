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
    	
    	if(node.getStyleClass().contains("drag")) {
    		if(node.getParent().getStyleClass().contains("body")) {
        		p = node.getParent();

    		}else {
        		p = node;
    		}
    	}

        node.setOnMousePressed(mouseEvent -> {
        	node.toFront();
        	ScrollPane sp = (ScrollPane) node.getScene().getRoot();
    		mouseAnchorX = mouseEvent.getX();
    		mouseAnchorY = mouseEvent.getY();
    		System.out.println("SceneX: "+mouseAnchorX+" SceneY: "+mouseAnchorY);

    		scrollXOffset = sp.getHvalue() * (sp.getContent().getBoundsInLocal().getWidth() - sp.getViewportBounds().getWidth());
    		scrollYOffset = sp.getVvalue() * (sp.getContent().getBoundsInLocal().getHeight() - sp.getViewportBounds().getHeight());
    		System.out.println("ScrolleadoX: "+scrollXOffset+" ScrolledY: "+scrollYOffset);
        	ScrollPane root = (ScrollPane)p.getScene().getRoot();
        	AnchorPane root2 = (AnchorPane)root.getContent();
        	if(p.getParent() instanceof VBox ) {
        		System.out.println("Entro aqui");
            	VBox vb = (VBox) p.getParent();
            	p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
                p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
            	vb.getChildren().remove(p);
            	root2.getChildren().add(p);
        	}else if(p.getParent() instanceof HBox) {
        		System.out.println("No, entro aqui");
        		HBox hb = (HBox) p.getParent();
        		p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
                p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            	hb.getChildren().remove(p);
            	root2.getChildren().add(p);
        	}
        });

        // Lo que hace que las cosas se muevan (sustituir node por p)
        node.setOnMouseDragged(mouseEvent -> {
        	ScrollPane sp = (ScrollPane) node.getScene().getRoot();
    		double scrollXOffset = sp.getHvalue() * (sp.getContent().getBoundsInLocal().getWidth() - sp.getViewportBounds().getWidth());
    		double scrollYOffset = sp.getVvalue() * (sp.getContent().getBoundsInLocal().getHeight() - sp.getViewportBounds().getHeight());
        	p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
            p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
        });
        
        node.setOnMouseReleased(mouseEvent -> {
        	Node  fBox = null;
            Set<Node> containers = p.getScene().getRoot().lookupAll(".drop");
            mouseAnchorX = mouseEvent.getSceneX();
            mouseAnchorY = mouseEvent.getSceneY();
            Point2D centerInScene =  new Point2D(mouseAnchorX+scrollXOffset, mouseAnchorY+scrollYOffset);
            ImageView trash = (ImageView)p.getScene().getRoot().lookup("#Trash");
            if(trash.getBoundsInParent().contains(centerInScene)) {
            	ScrollPane root = (ScrollPane)p.getScene().getRoot();
            	AnchorPane root2 = (AnchorPane)root.getContent();
            	root2.getChildren().remove(p);
            	return;
            }

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

            if (fBox != null) {
                if(fBox instanceof VBox) {
                	vb = (VBox) fBox;
                    vb.getChildren().add(p);
                    expandV(vb);
                    expandH(vb);
                }else if(fBox instanceof HBox) {
                	hb = (HBox) fBox;
                    hb.getChildren().add(p);
                    expandH(hb);
                    expandV(hb);
                }
            }

        });
        
    }
    
    public void expandV (Node son) {
    	VBox vs = null;
		HBox hs = null;
    	AnchorPane p = null;
		double Vheight = 0;

    	// Asegurarse de que es VBox
    	if(son instanceof VBox) {
    		vs = (VBox) son; // He de comprobar si sumo o no al hbox
    		p = (AnchorPane) vs.getParent();
    		// Lograr altura de el contenido
    		for (Node hijo : vs.getChildren()) {
    		    Vheight += hijo.getBoundsInParent().getHeight();
    		}
    		
    		double total_dist_down = vs.getLayoutY() + Vheight+50;
    		if(total_dist_down >= p.getPrefHeight()) {
    			p.setPrefHeight(total_dist_down);
    			
        	    if (Vheight > vs.getMinHeight()) {
        	    	vs.setPrefHeight(Vheight + 30);
        	    	vs.setMinHeight(Vheight+30);
        	    }
    			if(!p.getId().equals("Class")) {
    				expandV(vs.getParent().getParent());
    			}else {
    		    	ScrollPane sp = null;
    		    	AnchorPane ap = null;
    		    	sp = (ScrollPane)son.getScene().getRoot();
    		    	ap = (AnchorPane)sp.getContent();
    		    	double total = p.getLayoutY()+p.getPrefHeight()+100;
    		    	sp.setPrefHeight(total);
    		    	ap.setPrefHeight(total);
    			}
    		}
    		// Expandir el VBox (exitoso hasta aquí)
    	}else if(son instanceof HBox) {
    		hs = (HBox) son; // He de comprobar si sumo o no al hbox
    		p = (AnchorPane) hs.getParent();
    		for (Node hijo : hs.getChildren()) {
    		    if(hijo.getBoundsInLocal().getHeight()>Vheight) {
    		    	Vheight = hijo.getBoundsInLocal().getHeight();
    		    }
    		}
    		double total_dist_down = hs.getLayoutY() + Vheight+50;
    		if(total_dist_down >= p.getPrefHeight()) {
    			p.setPrefHeight(total_dist_down);
    			
        	    if (Vheight > hs.getMinHeight()) {
        	    	hs.setPrefHeight(Vheight + 30);
        	    	hs.setMinHeight(Vheight+30);
        	    }
    			if(!p.getId().equals("Class")) {
    				expandV(hs.getParent().getParent());
    			}else {
    		    	ScrollPane sp = null;
    		    	AnchorPane ap = null;
    		    	sp = (ScrollPane)son.getScene().getRoot();
    		    	ap = (AnchorPane)sp.getContent();
    		    	double total = p.getLayoutY()+p.getPrefHeight()+100;
    		    	sp.setPrefHeight(total);
    		    	ap.setPrefHeight(total);
    			}
    		}
    	}
    }
    
    
    public void expandH (Node son) {
    	// En este caso supondremos que partiremos de un VBox, en ese caso se expandirán lateralmente los nodos pero tan solo el primero debe ser Vbox creo yo. 
    	HBox hs = null;
    	VBox vs = null;
    	AnchorPane p = null;
    	if(son instanceof HBox) {
    		hs = (HBox) son; // He de comprobar si sumo o no al hbox
    		p = (AnchorPane) hs.getParent();
    		double Vwidth =0;
    		for (Node hijo : hs.getChildren()) {
    		    Vwidth += hijo.getBoundsInParent().getWidth();
    		}
    			
    		double total_dist_left = hs.getLayoutX() + Vwidth+50;
    		if(total_dist_left >= p.getPrefWidth()) {
    			p.setPrefWidth(total_dist_left);
    			
        	    if (Vwidth > hs.getMinWidth()) {
        	    	hs.setPrefWidth(Vwidth + 30);
        	    	hs.setMinWidth(Vwidth+30);
        	    }
    			if(!p.getId().equals("Class")) {
    				expandH(hs.getParent().getParent());
    			}else {
    		    	ScrollPane sp = null;
    		    	AnchorPane ap = null;
    		    	sp = (ScrollPane)son.getScene().getRoot();
    		    	ap = (AnchorPane)sp.getContent();
    		    	double total = p.getLayoutX()+p.getPrefWidth()+100;
    		    	sp.setPrefWidth(total);
    		    	ap.setPrefWidth(total);
    			}
    		}
    		
    		
    	}else if (son instanceof VBox) {
    		vs = (VBox) son;
    		p = (AnchorPane) vs.getParent();
    		double Vwidth = 0;
    		for (Node hijo : vs.getChildren()) {
    		    if(hijo.getBoundsInLocal().getWidth()>Vwidth) {
    		    	Vwidth = hijo.getBoundsInLocal().getWidth();
    		    }
    		}
    		
    		double total_dist_left = vs.getLayoutX() + Vwidth+50;
    		if(total_dist_left >= p.getPrefWidth()) {
    			p.setPrefWidth(total_dist_left);
    			
        	    if (Vwidth > vs.getMinWidth()) {
        	    	vs.setPrefWidth(Vwidth + 30);
        	    	vs.setMinWidth(Vwidth+30);
        	    }
    			if(!p.getId().equals("Class")) {
    				expandH(vs.getParent().getParent());
    			}else {
    		    	ScrollPane sp = null;
    		    	AnchorPane ap = null;
    		    	sp = (ScrollPane)son.getScene().getRoot();
    		    	ap = (AnchorPane)sp.getContent();
    		    	double total = p.getLayoutX()+p.getPrefWidth()+100;
    		    	sp.setPrefWidth(total);
    		    	ap.setPrefWidth(total);
    			}
    		}	
    	}
    }
}
