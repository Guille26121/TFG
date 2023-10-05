package application;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Expander {

	public void expandImp(Node son) {
		VBox vb = (VBox)son.getParent();
		AnchorPane p = (AnchorPane)vb.getParent();
		double h = 0;
		for (Node n : vb.getChildren()) {
			AnchorPane n2 = (AnchorPane)n;
				h += n2.getPrefHeight();
		}  	
		if (h > vb.getPrefHeight()) {
			vb.setPrefHeight(h+30);
			double total_h_dist = vb.getLayoutY() + h+50;
			if(total_h_dist > p.getPrefHeight()) {
				double diff = total_h_dist-p.getPrefHeight();
				p.setPrefHeight(total_h_dist);
				ScrollPane sp = (ScrollPane)p.getScene().getRoot();
				AnchorPane ap = (AnchorPane)sp.getContent();
				AnchorPane clss = (AnchorPane) ap.lookup("#Class");
				clss.setLayoutY(clss.getLayoutY()+diff);
				double totalh = clss.getLayoutY()+clss.getPrefHeight()+100;
				double totalw = clss.getLayoutX()+clss.getPrefWidth()+100;
				sp.setPrefHeight(totalh);
				ap.setPrefHeight(totalh);
				sp.setPrefWidth(totalw);
				ap.setPrefWidth(totalw);
			} 
		}
		
	}

	public void dexpandImp(Node son) {
		VBox vb = (VBox)son;
		AnchorPane p = (AnchorPane)vb.getParent();
		double h = 0;
		double diff = 0;
		ScrollPane sp = (ScrollPane)p.getScene().getRoot();
		AnchorPane ap = (AnchorPane)sp.getContent();
		for (Node n : vb.getChildren()) {
			AnchorPane n2 = (AnchorPane)n;
				h += n2.getPrefHeight();
		} 
		
		if (h < vb.getPrefHeight() ) {

			if(h >= vb.getMinHeight()) {
				diff = vb.getPrefHeight() - (h+30);
				vb.setPrefHeight(h+30);
			}else {
				diff = vb.getPrefHeight() - vb.getMinHeight();
				vb.setPrefHeight(vb.getMinHeight());
			}
			if(p.getPrefHeight() - diff < p.getMinHeight()) {
				diff = p.getPrefHeight()-p.getMinHeight();
				p.setPrefHeight(p.getMinHeight());
				AnchorPane clss = (AnchorPane) ap.lookup("#Class");
				clss.setLayoutY(clss.getLayoutY()-diff);
			}else {
				p.setPrefHeight(p.getPrefHeight()-diff);
				AnchorPane clss = (AnchorPane) ap.lookup("#Class");
				clss.setLayoutY(clss.getLayoutY()-diff);
			}

		}
		
		
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

					if(ap.getPrefHeight()-diff <= ap.getMinHeight()) {
						ap.setPrefHeight(ap.getMinHeight());
					}else {
						ap.setPrefHeight(ap.getPrefHeight()-diff);
					}
					dexp = true;
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
				if(h >= vs.getMinHeight()) {
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
				if(hs.getStyleClass().contains("one")) {
					hs.setPrefWidth(w);
				}else {
					hs.setPrefWidth(w+30);
				}
				double total_w_dist = hs.getLayoutX() + w+50;
				if(total_w_dist > ap.getPrefWidth()) {
					ap.setPrefWidth(total_w_dist);
					exp = true;
				}  
			}



			if (h > hs.getPrefHeight()) {
				double diff = h- hs.getPrefHeight()+10;
				hs.setPrefHeight(h);
				ObservableList<Node> nodes = ap.getChildren();
				double max_h = 0;
				for(Node n : nodes){
					double its_h = 0;
					if(n instanceof HBox) {
						HBox hb = (HBox)n;
						its_h = hb.getLayoutY()+ hb.getPrefHeight();
					}else if(n instanceof VBox) {
						VBox vb = (VBox)n;
						its_h = vb.getLayoutY()+ vb.getPrefHeight();
					}
					if(its_h> max_h) {
						max_h = its_h;
					}
				}
				if(max_h > ap.getPrefHeight()) {
					ap.setPrefHeight(max_h+10);
					exp = true;
				}

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
