/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author Rohan Zahid
 */
public class ManagerGUIController implements Initializable {

    @FXML
    public Label welcomeMessage;
    public Label numCustomers;
    public ImageView addCustomerButton;
    public ImageView removeCustomerButton;
    public ImageView signOutButton;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager m = Main.getManager();
        // try-catch block to handle exceptions 
        try { 
            File f = new File("./"); 
            File[] matchingFiles = f.listFiles((File dir, String name) -> name.endsWith("txt"));
            numCustomers.setText("" + matchingFiles.length);
        } 
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
        welcomeMessage.setText("Welcome, " + m.getUsername());
    }    
    @FXML
    private void addCustomerButtonClicked (ActionEvent event) throws Exception{
        Manager m = Main.getManager();
        System.out.println("Add Customer");
        Parent root = FXMLLoader.load(getClass().getResource("customerAddGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 561, 352));
    }
    @FXML
    private void removeCustomerButtonClicked (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("customerRemoveGUI.fxml"));
        System.out.println("Remove Customer");
        Main.getStage().setScene(new Scene(root, 561, 352));
    }
    @FXML
    private void signOutButtonClicked (ActionEvent event) throws Exception{
        System.out.println("Sign out");
        Parent root = FXMLLoader.load(getClass().getResource("simplebankaccount.fxml"));
        Main.getStage().setScene(new Scene(root, 700, 395));
    }
}
