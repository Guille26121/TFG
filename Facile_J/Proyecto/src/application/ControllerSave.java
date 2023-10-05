package application;

import java.awt.Desktop;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.stage.FileChooser;
import javafx.stage.*;


public class ControllerSave implements Initializable {

	@FXML
    private AnchorPane Background;
	
	@FXML
	private TextArea code;
	
	@FXML
	private ImageView Save;
	
	private Stage stg = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	Save.setOnMouseClicked(event->{
    		DirectoryChooser dirChooser = new DirectoryChooser();
    		dirChooser.setTitle("Open Resource File");
			File selectedDirectory = dirChooser.showDialog(stg);
			System.out.println(selectedDirectory);
			String txt = code.getText();
	        int classIndex = txt.indexOf("class");
            String classDeclaration = txt.substring(classIndex + 5).trim();
            String[] classDeclarationParts = classDeclaration.split("\\s+");
            String cls =  classDeclarationParts[0];
            String pth = selectedDirectory+"/"+cls+".java";
            File file = new File(pth);
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo creado correctamente.");
    				FileWriter fw = new FileWriter(file);
    				PrintWriter pw = new PrintWriter(fw);
    				pw.write(txt);
    				pw.close();
                } else {
                    System.out.println("El archivo ya existe.");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
    	});
    }
    
}
