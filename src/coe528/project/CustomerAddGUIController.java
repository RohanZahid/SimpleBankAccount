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
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author Rohan Zahid
 */
public class CustomerAddGUIController implements Initializable {
    @FXML
    public Label warningText1, warningText2, warningText3;
    public TextField userText;
    public TextField passText;
    public TextField balanceText;
    
    @FXML
    private void enterButtonClicked (ActionEvent event) throws Exception{
        warningText1.setText("");
        warningText2.setText("");
        warningText3.setText("");
        warningText3.setTextFill(Color.rgb(255, 39, 39));
        Manager m = Main.getManager();
        Boolean exists = false;
        if(passText.getText().isEmpty() || passText.getText().contains(" "))
            warningText2.setText("Please enter a password (No spaces).");
        if(balanceText.getText().isEmpty())
            warningText3.setText("Please enter a valid amount.");
        if(userText.getText().isEmpty() || userText.getText().contains(" "))
            warningText1.setText("Please enter a username (No spaces).");
        else if(passText.getText().isEmpty() || passText.getText().contains(" ")) {
            warningText2.setText("Please enter a password (No spaces).");
        }
        else {
            File f = new File("./");
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.equals(userText.getText() + ".txt");
                }
            });
            if(matchingFiles.length > 0) {
                exists = true;
                warningText1.setText("A customer with this username already exists!");
            }
            if(exists == false) {
                if(isNumeric(balanceText.getText())) {
                    Double balance = Double.parseDouble(balanceText.getText());
                    if (balance >= 100) {
                        warningText3.setText("Made a new account for " + userText.getText());
                        warningText3.setTextFill(Color.rgb(19, 252, 3));
                        BankAccount b = new BankAccount(balance);
                        Customer c = new Customer(userText.getText(), passText.getText(), "customer", b);
                        m.addCustomer(c);
                    }
                    else
                        warningText3.setText("The account balance must be at least $100!");
                }
                else {
                    warningText3.setText("Please enter a valid value for the account balance!");
                }
            }
        }
    }
    @FXML
    private void clearButtonClicked (ActionEvent event) throws Exception{
        userText.clear();
        passText.clear();
        balanceText.clear();
        warningText1.setText("");
        warningText2.setText("");
        warningText3.setText("");
    }
    @FXML
    private void backButtonClicked (ActionEvent event) throws Exception{
        userText.clear();
        passText.clear();
        balanceText.clear();
        warningText1.setText("");
        warningText2.setText("");
        warningText3.setText("");
        Parent root = FXMLLoader.load(getClass().getResource("managerGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 800, 352));
    }
    public static boolean isNumeric(String str) { 
        try {  
            Double.parseDouble(str);  
            return true;
        } 
        catch(NumberFormatException e){  
            return false;  
        }       
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
