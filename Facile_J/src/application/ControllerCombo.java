package application;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ControllerCombo implements Initializable {

    @FXML
    private ChoiceBox<String> Vars;
    @FXML
    private ChoiceBox<String> Ops;
    @FXML
    private ChoiceBox<String> Loop;
    @FXML
    private ImageView translate;
    


    @FXML
    void actionVar(ActionEvent event) {
    	if ("Inicialización".equals(Vars.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Int_Block.fxml"));
             AnchorPane componente = null;
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
            // Controller controlador = loader.getController();
             Node n = (Node)event.getSource();
             AnchorPane p = (AnchorPane)n.getParent();
             p.getChildren().add(componente);
    	}
    	Vars.setValue(null);

    }
    
    @FXML
    void actionOp(ActionEvent event) {

        AnchorPane componente = null;
        Node n = (Node)event.getSource();
        AnchorPane p = (AnchorPane)n.getParent();
        
    	if ("+".equals(Ops.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("plus.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else if ("-".equals(Ops.getValue())) {
   		 FXMLLoader loader = new FXMLLoader(getClass().getResource("minus.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        p.getChildren().add(componente);
    	Ops.setValue(null);
    }
    
    @FXML
    void actionLoop(ActionEvent event) {

    	if ("While".equals(Loop.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("while.fxml"));
             AnchorPane componente = null;
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
            // Controller controlador = loader.getController();
             Node n = (Node)event.getSource();
             AnchorPane p = (AnchorPane)n.getParent();
             p.getChildren().add(componente);
             componente.setLayoutX(50.0);
             componente.setLayoutY(100.0);

    	}
    	
    	Loop.setValue(null);

    }
    
    @FXML
    private void translator(MouseEvent event) {
    	System.out.println("Hola");
    }

    
    @Override
    public void initialize(URL url,ResourceBundle rb) {
    	ObservableList<String> COvars = FXCollections.observableArrayList("Inicialización","Asignación","Entero","Coma Flotante","Cadena de texto","Booleano");
    	ObservableList<String> COops = FXCollections.observableArrayList("+","-","x","/","%","AND","OR","NOT","^","√");
    	ObservableList<String> COloop = FXCollections.observableArrayList("While","For","Do While");

    	Vars.setItems(COvars);
    	Ops.setItems(COops);
    	Loop.setItems(COloop);
    	
    	translate.setOnMouseClicked(event->{
    		ScrollPane start = (ScrollPane)Vars.getScene().getRoot();
    		AnchorPane inside = (AnchorPane)start.getContent();
    		AnchorPane Cstart = (AnchorPane) inside.lookup("#Class");
    		try {
        		File file = new File("out.txt");
				FileWriter fw = new FileWriter(file);
	    		PrintWriter pw = new PrintWriter(fw);
	    		TextField packg = (TextField)Cstart.lookup("#packg");
	    		TextField clss = (TextField)Cstart.lookup("#clss");
	    		TextField inhrt = (TextField)Cstart.lookup("#inhrt");
	    		TextField impl = (TextField)Cstart.lookup("#impl");

	    		
	    		if(!packg.getText().isEmpty()) pw.write("package "+packg.getText()+"; \n");
	    		if(!clss.getText().isEmpty()) pw.write("public Class "+clss.getText()+" ");
	    		if(!inhrt.getText().isEmpty()) pw.write("extends "+inhrt.getText()+" ");
	    		if(!impl.getText().isEmpty()) pw.write("implements "+impl.getText()+" ");
	    		pw.write("{ \n");
	    		
	    		
	    		
	    		
	    		pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }
    
    public void treeTravel (Node n, PrintWriter pw) {
    	if(n instanceof VBox) {
    		VBox vb = (VBox)n;
    		ObservableList<Node> nodes = vb.getChildren();
    		for(Node node : nodes){
    			treeTravel(node,pw);
    			pw.write("\n");
    		}
    	}else if (n instanceof HBox) {
    		HBox hb = (HBox)n;
    		ObservableList<Node> nodes = hb.getChildren();
    		for(Node node : nodes){
    			treeTravel(node,pw);
    		}
    	}else if (n instanceof AnchorPane) {
    		AnchorPane ap = (AnchorPane)n;
    		String id = ap.getId();
    		switch (id) {
    		case "While": WriteWhile(ap,pw); break;
    		case "B": break;
    		}
    			
    	}
    }
    public void WriteWhile(Node n,PrintWriter pw) {
    	pw.write("while (");
    	AnchorPane node = (AnchorPane)n;
    	HBox cond = (HBox)node.lookup("#WCond");
    	treeTravel(cond, pw);
    	pw.write(" ){ \n");
    	VBox cont = (VBox)node.lookup("#WCont");
    	treeTravel(cont,pw);
    	pw.write("} \n");
    }
	
	
}
