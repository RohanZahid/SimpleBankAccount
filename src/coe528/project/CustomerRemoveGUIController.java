/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author Rohan Zahid
 */
public class CustomerRemoveGUIController implements Initializable {
    public ComboBox customersDropDown;
    public Button deleteButton;
    @FXML
    private void deleteButtonClicked (ActionEvent event) throws Exception{
         String s = customersDropDown.getValue().toString() + ".txt";
         Manager m = Main.getManager();
         m.removeCustomer(s, customersDropDown.getValue().toString());
         Parent root = FXMLLoader.load(getClass().getResource("managerGUI.fxml"));
         Main.getStage().setScene(new Scene(root, 800, 352));
    }
    @FXML
    private void backButtonClicked (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("managerGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 800, 352));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try { 
            File f = new File("./"); 
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("txt");
            }
        });
            for(int i = 0; i < matchingFiles.length; i++) {
                String s = matchingFiles[i].getName();
                s = s.replace(".txt","");
                customersDropDown.getItems().add(s);
            }
            if(matchingFiles.length > 0)
                customersDropDown.setValue(matchingFiles[0].getName().replace(".txt",""));
            else {
                customersDropDown.setDisable(true);
                customersDropDown.setPromptText("No Customers.");
                deleteButton.setDisable(true);
            }
        } 
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
    }    
    
}
