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
        	}else if(p.getParent() instanceof HBox) {
        		HBox hb = (HBox) p.getParent();
        		p.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX +scrollXOffset);
                p.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY +scrollYOffset);
            	hb.getChildren().remove(p);
            	root2.getChildren().add(p);
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
                if(fBox instanceof VBox && node.getStyleClass().contains("V")) {
                	vb = (VBox) fBox;
                    vb.getChildren().add(p);
                    expand(vb);
                }else if(fBox instanceof HBox && node.getStyleClass().contains("H")) {
                	hb = (HBox) fBox;
                    hb.getChildren().add(p);
                    expand(hb);
                }
            }

        });
        
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
    		for (Node n : hs.getChildren()) {
    			AnchorPane n2 = (AnchorPane)n;
    		    w += n2.getPrefWidth();
    		}  			
    	    if (w > hs.getPrefWidth()) {
    	    	hs.setPrefWidth(w );
    	    }
    	    double total_w_dist = hs.getLayoutX() + w+30;
    		if(total_w_dist > ap.getPrefWidth()) {
    			ap.setPrefWidth(total_w_dist);
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
	    	double total = p.getLayoutY()+p.getPrefHeight()+100;
	    	sp.setPrefHeight(total);
	    	ap.setPrefHeight(total);
    }
    
}
  