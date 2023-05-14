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
import javafx.scene.control.CheckBox;
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
    private ChoiceBox<String> Func;
    @FXML
    private ChoiceBox<String> Cond;
    @FXML
    private ImageView translate;
    
    
    @FXML
    void actionCond(ActionEvent event) {
    	AnchorPane componente = null;
        Node n = (Node)event.getSource();
        AnchorPane p = (AnchorPane)n.getParent();
    	if ("If".equals(Cond.getValue())) {
             
    	}else {
    		return;
    	}
    	p.getChildren().add(componente);
        componente.setLayoutX(50.0);
        componente.setLayoutY(100.0);
    	Cond.setValue(null);
    }
    
    
    @FXML
    void actionFunc(ActionEvent event) {
    	AnchorPane componente = null;
        Node n = (Node)event.getSource();
        AnchorPane p = (AnchorPane)n.getParent();
    	if ("Función".equals(Func.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/method.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
             
    	}else {
    		return;
    	}
    	p.getChildren().add(componente);
        componente.setLayoutX(50.0);
        componente.setLayoutY(100.0);
    	Func.setValue(null);
    }

    @FXML
    void actionVar(ActionEvent event) {
        AnchorPane componente = null;
        Node n = (Node)event.getSource();
        AnchorPane p = (AnchorPane)n.getParent();
    	if ("Inicialización".equals(Vars.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/initialization.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
             
    	}else if ("Entero".equals(Vars.getValue())) {
      		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/int.fxml"));
 			try {
 				componente = loader.load();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
     	}else if ("Cadena de texto".equals(Vars.getValue())) {
     		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/string.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		return;
    	}
    	p.getChildren().add(componente);
        componente.setLayoutX(50.0);
        componente.setLayoutY(100.0);
    	Vars.setValue(null);

    }
    
    @FXML
    void actionOp(ActionEvent event) {

        AnchorPane componente = null;
        Node n = (Node)event.getSource();
        AnchorPane p = (AnchorPane)n.getParent();
        
    	if ("+".equals(Ops.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/plus.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else if ("-".equals(Ops.getValue())) {
   		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/minus.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else if ("x".equals(Ops.getValue())) {
      		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/multiply.fxml"));
 			try {
 				componente = loader.load();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
     	}else if ("÷".equals(Ops.getValue())) {
     		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/divide.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else if ("AND".equals(Ops.getValue())) {
   		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/and.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
   	}else if ("NOT".equals(Ops.getValue())) {
  		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/not.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
	   	}else if ("OR".equals(Ops.getValue())) {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/or.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
    		return;
    	}
        p.getChildren().add(componente);
        componente.setLayoutX(50.0);
        componente.setLayoutY(100.0);
    	Ops.setValue(null);
    }
    
    @FXML
    void actionLoop(ActionEvent event) {

    	if ("While".equals(Loop.getValue())) {
    		 FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/while.fxml"));
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
    	ObservableList<String> COvars = FXCollections.observableArrayList("Inicialización","Asignación","Variable","Tipo Entero","Tipo Coma Flotante","Tipo Cadena de texto","Tipo Booleano","Tipo Otro","Valor Numérico","Valor de texto");
    	ObservableList<String> COops = FXCollections.observableArrayList("+","-","x","÷","%","AND","OR","NOT");
    	ObservableList<String> COloop = FXCollections.observableArrayList("While","For");
    	ObservableList<String> COcond = FXCollections.observableArrayList("If","Else If","Else");
    	ObservableList<String> COfunct = FXCollections.observableArrayList("Función","Vacío","Retorno","Invocación");

    	Vars.setItems(COvars);
    	Ops.setItems(COops);
    	Loop.setItems(COloop);
    	Func.setItems(COfunct);
    	Cond.setItems(COcond);
    	
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
	    		VBox program = (VBox) Cstart.lookup("#program");
	    		treeTravel(program,pw,1);
	    		pw.write("} \n");

	    		
	    		
	    		
	    		pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    }
    
    public void treeTravel (Node n, PrintWriter pw,int indent) {
    	System.out.println("Pasa por aquí: "+n+" indenta: "+indent);
    	if(n instanceof VBox) {
    		VBox vb = (VBox)n;
    		ObservableList<Node> nodes = vb.getChildren();
    		for(Node node : nodes){
    			treeTravel(node,pw,indent);
    			if(nodes.indexOf(node) != nodes.size()-1) pw.write("\n");
    		}
    	}else if (n instanceof HBox) {
    		HBox hb = (HBox)n;
    		ObservableList<Node> nodes = hb.getChildren();
    		for(Node node : nodes){
    			treeTravel(node,pw,indent);
    		}
    	}else if (n instanceof AnchorPane) {
    		AnchorPane ap = (AnchorPane)n;
    		String id = ap.getId();
    		switch (id) {
    		case "While": WriteWhile(ap,pw,indent); break;
    		case "method": WriteMethod(ap, pw,indent); break;
    		case "Plus" : WritePlus(ap,pw); break;
    		case "Minus" : WriteMinus(ap,pw); break;
    		case "Mult" : WriteMult(ap,pw);break;
    		case "Division" : WriteDiv(ap,pw);break;
    		case "Int": WriteInt(ap,pw); break;
    		case "Init" :WriteInit(ap, pw, indent);break;
    		case "String" :WriteString(ap,pw);break;
    		case "And" : WriteAnd(ap,pw);break;
    		case "Or" : WriteOr(ap,pw);break;
    		case "Not" : WriteNot(ap,pw);break;
    		}
    			
    	}
    }
    public void WriteWhile(Node n,PrintWriter pw,int indent) {
    	for (int i = 0; i<indent;i++) {
    		pw.write("\t");
    	}
    	pw.write("while (");
    	AnchorPane node = (AnchorPane)n;
    	HBox cond = (HBox)node.lookup("#WCond");
    	treeTravel(cond, pw,indent);
    	pw.write(" ){ \n");
    	VBox cont = (VBox)node.lookup("#WContent");
    	treeTravel(cont,pw,indent+1);
    	for (int i = 0; i<indent;i++) {
    		pw.write("\t");
    	}
    	pw.write("} \n");
    }
    
    public void WritePlus(Node n,PrintWriter pw) {
    	pw.write("+ ");
    }
    public void WriteMinus(Node n,PrintWriter pw) {
    	pw.write("- ");
    }
    public void WriteMult(Node n,PrintWriter pw) {
    	pw.write("x ");
    }
    public void WriteDiv(Node n,PrintWriter pw) {
    	pw.write("/ ");
    }
    public void WriteInt(Node n,PrintWriter pw) {
    	pw.write("int ");
    }
    public void WriteString(Node n,PrintWriter pw) {
    	pw.write("String ");
    }
    public void WriteAnd(Node n,PrintWriter pw) {
    	pw.write("&&  ");
    }
    public void WriteOr(Node n,PrintWriter pw) {
    	pw.write("||  ");
    }
    public void WriteNot(Node n,PrintWriter pw) {
    	pw.write("!  ");
    }
    
    public void WriteInit(Node n,PrintWriter pw, int indent) {
    	TextField name = (TextField)n.lookup("#IName");
	    HBox type = (HBox)n.lookup("#IType");
		ObservableList<Node> nodes = type.getChildren();
		for(Node node : nodes){
			treeTravel(node,pw,indent);
		}
		pw.write(name.getText());
		if(n.getParent() instanceof HBox) {
		}else {
			pw.print("; \n");
		}
    }
    
    public void WriteMethod(Node n,PrintWriter pw,int indent) {
    	TextField name = (TextField)n.lookup("#Fname");
	    CheckBox stat = (CheckBox)n.lookup("#Fstatic");
	    ChoiceBox<String> acc = (ChoiceBox<String>)n.lookup("#Facc");
	    HBox fout = (HBox)n.lookup("#Fout");
	    HBox fin = (HBox)n.lookup("#Fin");
	    VBox fcont = (VBox)n.lookup("#FCont");
    	for (int i = 0; i<indent;i++) {
    		pw.write("\t");
    	}

        if(!acc.getSelectionModel().isSelected(0)) {
        	pw.write(acc.getSelectionModel().getSelectedItem()+" ");
        }
        if(stat.isSelected()) {
        	pw.write("static ");
        }
        if(fout.getChildren().size() == 1) {
        	treeTravel(fout.getChildren().get(0), pw,indent);
        }
        pw.write(name.getText()+" (");
		ObservableList<Node> nodes = fin.getChildren();
		for(Node node : nodes){
			treeTravel(node,pw,indent);
			if(nodes.indexOf(node) != nodes.size()-1) pw.write(" ,");
		}
		pw.write(" ){ \n");
		treeTravel(fcont, pw,indent+1);
    	for (int i = 0; i<indent;i++) {
    		pw.write("\t");
    	}
		pw.write("}\n");
    }
	
	
}
