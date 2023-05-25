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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ControllerCombo implements Initializable {

	// Choices Boxes del Main y Botón de Play
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

	// Métodos de evento onAction de cada choicebox 
	@FXML
	void actionCond(ActionEvent event) {
		AnchorPane componente = null;
		Node n = (Node)event.getSource();
		AnchorPane p = (AnchorPane)n.getParent();
		if ("If".equals(Cond.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/if.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Else If".equals(Cond.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/elseif.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Else".equals(Cond.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/else.fxml"));
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

		}else if("Vacío".equals(Func.getValue())){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/void.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("String[] args".equals(Func.getValue())){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/strargs.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Retorno".equals(Func.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/return.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Llamada estática".equals(Func.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/call_st.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Llamada".equals(Func.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/call.fxml"));
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

		}else if ("Tipo Entero".equals(Vars.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/int.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Tipo Cadena de texto".equals(Vars.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/string.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Asignación".equals(Vars.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/asign.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("Variable".equals(Vars.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/var.fxml"));
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
		// Por ejemplo aquí sacamos el anchorpane al que meteremos el nodo 
		AnchorPane componente = null;
		Node n = (Node)event.getSource();
		AnchorPane p = (AnchorPane)n.getParent();
		// Los casos posibles con los que cargamos el fxml
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
		}else if ("=".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/equal.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("≠".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/notequal.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (">".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/more.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("<".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/less.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("%".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/modulus.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("true".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/true.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("false".equals(Ops.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/false.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			// Esto evita errores
			return;
		}
		// Añadir el nuevo nodo al panel, posicionarlo y borrar la selección. 
		p.getChildren().add(componente);
		componente.setLayoutX(50.0);
		componente.setLayoutY(100.0);
		Ops.setValue(null);
	}

	@FXML
	void actionLoop(ActionEvent event) {
		AnchorPane componente = null;
		Node n = (Node)event.getSource();
		AnchorPane p = (AnchorPane)n.getParent();
		if ("While".equals(Loop.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/while.fxml"));
			try {
				componente = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if ("For".equals(Loop.getValue())) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/for.fxml"));
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

		Loop.setValue(null);

	}




	@Override
	public void initialize(URL url,ResourceBundle rb) {
		// Poblar con opciones los ChoiceBox. Poner un evento al botón de Play. 
		ObservableList<String> COvars = FXCollections.observableArrayList("Inicialización","Asignación","Variable","Tipo Entero","Tipo Coma Flotante","Tipo Cadena de texto","Tipo Booleano","Tipo Otro","Valor Numérico","Valor de texto");
		ObservableList<String> COops = FXCollections.observableArrayList("+","-","x","÷","%","AND","OR","NOT","=","≠",">","<","true","false");
		ObservableList<String> COloop = FXCollections.observableArrayList("While","For");
		ObservableList<String> COcond = FXCollections.observableArrayList("If","Else If","Else");
		ObservableList<String> COfunct = FXCollections.observableArrayList("Función","Vacío","Retorno","Llamada estática","Llamada","String[] args");

		Vars.setItems(COvars);
		Ops.setItems(COops);
		Loop.setItems(COloop);
		Func.setItems(COfunct);
		Cond.setItems(COcond);

		translate.setOnMouseClicked(event->{
			// Inicio del programa, con las clases. 
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
				// Punto de comienzo de la navegación por el arbol
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

	// Método para navegar por el arbol del programa con distintos casos. 
	public void treeTravel (Node n, PrintWriter pw,int indent) {
		//System.out.println("Pasa por aquí: "+n+" indenta: "+indent);
		if(n instanceof VBox) {
			VBox vb = (VBox)n;
			ObservableList<Node> nodes = vb.getChildren();
			for(Node node : nodes){
				treeTravel(node,pw,indent);
				if(nodes.indexOf(node) != nodes.size()-1);
			}
		}else if (n instanceof HBox) {
			HBox hb = (HBox)n;
			ObservableList<Node> nodes = hb.getChildren();
			for(Node node : nodes){
				treeTravel(node,pw,indent);
			}
		}else if (n instanceof AnchorPane) {
			// Nodos hoja o distintos
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
			case "Equal" :WriteEqual(ap, pw);break;
			case "NEqual" : WriteNEqual(ap,pw);break;
			case "More" : WriteMore(ap,pw);break;
			case "Less" : WriteLess(ap,pw);break;
			case "Mod" : WriteMod(ap,pw);break;
			case "True" : WriteTrue(ap,pw);break;
			case "False" : WriteFalse(ap,pw);break;
			case "Void" : WriteVoid(ap,pw);break;
			case "StrArgs" : WriteStrArgs(ap,pw);break;
			case "Return" : WriteRet(ap,pw,indent);break;
			case "StCall" : WriteStCall(ap,pw,indent);break;
			case "Call" : WriteCall(ap,pw,indent);break;
			case "Asign" : WriteAsign(ap,pw,indent);break;
			case "Var" : WriteVar(ap,pw);break;
			case "For" : WriteFor(ap,pw,indent);break;
			case "If" : WriteIf(ap,pw,indent);break;
			case "ElseIf" : WriteElseIf(ap,pw,indent);break;
			case "Else" : WriteElse(ap,pw,indent);break;
			}

		}
	}
	public void WriteIf(Node n,PrintWriter pw,int indent) {
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write("if (");
		HBox cond = (HBox)n.lookup("#ICond");
		treeTravel(cond, pw,indent);
		pw.write(" ){ \n");
		VBox cont = (VBox)n.lookup("#IContent");
		treeTravel(cont,pw,indent+1);
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write("} \n");
	}
	
	public void WriteElseIf(Node n,PrintWriter pw,int indent) {
		VBox p = (VBox)n.getParent();
		if(p.getChildren().indexOf(n)-1 >= 0) {
			Node upbroth = p.getChildren().get(p.getChildren().indexOf(n)-1);
			if((upbroth.getId()).equals("If") || (upbroth.getId()).equals("ElseIf") ) {
				for (int i = 0; i<indent;i++) {
					pw.write("\t");
				}
				pw.write("else if (");
				HBox cond = (HBox)n.lookup("#EICond");
				treeTravel(cond, pw,indent);
				pw.write(" ){ \n");
				VBox cont = (VBox)n.lookup("#EIContent");
				treeTravel(cont,pw,indent+1);
				for (int i = 0; i<indent;i++) {
					pw.write("\t");
				}
				pw.write("} \n");
		}

		}else {
	        Alert alerta = new Alert(AlertType.ERROR);
	        alerta.show();
		}
	}
	
	public void WriteElse(Node n,PrintWriter pw,int indent) {
		VBox p = (VBox)n.getParent();
		if(p.getChildren().indexOf(n)-1 >= 0) {
			Node upbroth = p.getChildren().get(p.getChildren().indexOf(n)-1);
			if((upbroth.getId()).equals("If") || (upbroth.getId()).equals("ElseIf") ) {
				for (int i = 0; i<indent;i++) {
					pw.write("\t");
				}
				pw.write("else { \n");
				VBox cont = (VBox)n.lookup("#EContent");
				treeTravel(cont,pw,indent+1);
				for (int i = 0; i<indent;i++) {
					pw.write("\t");
				}
				pw.write("} \n");
			}
		}else {
	        Alert alerta = new Alert(AlertType.ERROR);
	        alerta.show();
		}
	}
	
	
	public void WriteWhile(Node n,PrintWriter pw,int indent) {
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write("while (");
		HBox cond = (HBox)n.lookup("#WCond");
		treeTravel(cond, pw,indent);
		pw.write(" ){ \n");
		VBox cont = (VBox)n.lookup("#WContent");
		treeTravel(cont,pw,indent+1);
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write("} \n");
	}
	
	public void WriteFor(Node n,PrintWriter pw,int indent) {
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write("for (");
		HBox type = (HBox)n.lookup("#FType");
		TextField name = (TextField)n.lookup("#FName");
		HBox val = (HBox)n.lookup("#FVal");
		ObservableList<Node> nodes = type.getChildren();
		for(Node node : nodes){
			treeTravel(node,pw,indent);
		}
		pw.write(name.getText()+" = ");
		nodes = val.getChildren();
		for(Node node : nodes){
			treeTravel(node,pw,indent);
		}
		pw.write("; ");
		HBox cond = (HBox)n.lookup("#FCond");
		treeTravel(cond, pw,indent);
		pw.write("; ");
		
		HBox change = (HBox)n.lookup("#FChange");
		treeTravel(change, pw,indent);
		pw.write("){ \n");
		
		VBox cont = (VBox)n.lookup("#FContent");
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
		pw.write("* ");
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
	public void WriteEqual(Node n,PrintWriter pw) {
		pw.write("=  ");
	}
	public void WriteNEqual(Node n,PrintWriter pw) {
		pw.write("≠  ");
	}
	public void WriteMore(Node n,PrintWriter pw) {
		pw.write(">  ");
	}
	public void WriteLess(Node n,PrintWriter pw) {
		pw.write("<  ");
	}
	public void WriteMod(Node n,PrintWriter pw) {
		pw.write("%  ");
	}
	public void WriteTrue(Node n,PrintWriter pw) {
		pw.write("true  ");
	}
	public void WriteFalse(Node n,PrintWriter pw) {
		pw.write("false  ");
	}
	public void WriteVoid(Node n,PrintWriter pw) {
		pw.write("void  ");
	}
	public void WriteStrArgs(Node n,PrintWriter pw) {
		pw.write("String[] args ");
	}
	public void WriteVar(Node n,PrintWriter pw) {
		TextField name = (TextField)n.lookup("#VName");
		pw.write(name.getText()+" ");
	}
	
	public void WriteAsign(Node n,PrintWriter pw, int indent) {
		TextField name = (TextField)n.lookup("#AName");
		HBox type = (HBox)n.lookup("#ACont");
		ObservableList<Node> nodes = type.getChildren();
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write(name.getText()+" = ");
		for(Node node : nodes){
			treeTravel(node,pw,indent);
		}
		if(n.getParent() instanceof HBox) {
		}else {
			pw.print("; \n");
		}
	}
	
	
	
	public void WriteStCall(Node n,PrintWriter pw,int indent) {
		HBox ret = (HBox)n.lookup("#SCParams");
		TextField name = (TextField)n.lookup("#SCName");
		ObservableList<Node> nodes = ret.getChildren();
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write(name.getText()+"(");
		for(Node node : nodes){
			treeTravel(node,pw,indent);
			if(nodes.indexOf(node) != nodes.size()-1) pw.write(" ,");
		}
		pw.write("); \n");
	}
	
	public void WriteCall(Node n,PrintWriter pw,int indent) {
		HBox ret = (HBox)n.lookup("#CParams");
		TextField name = (TextField)n.lookup("#CName");
		TextField obj = (TextField)n.lookup("#CObj");
		ObservableList<Node> nodes = ret.getChildren();
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write(obj.getText()+"."+name.getText()+"(");
		for(Node node : nodes){
			treeTravel(node,pw,indent);
			if(nodes.indexOf(node) != nodes.size()-1) pw.write(" ,");
		}
		pw.write("); \n");
	}
	
	public void WriteRet(Node n,PrintWriter pw,int indent) {
		HBox ret = (HBox)n.lookup("#RVal");
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
		pw.write("return ");
		treeTravel(ret,pw,indent);
		pw.write(" ; \n");
	}

	public void WriteInit(Node n,PrintWriter pw, int indent) {
		TextField name = (TextField)n.lookup("#IName");
		HBox type = (HBox)n.lookup("#IType");
		ObservableList<Node> nodes = type.getChildren();
		for (int i = 0; i<indent;i++) {
			pw.write("\t");
		}
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
