/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import static coe528.project.CustomerAddGUIController.isNumeric;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Rohan Zahid
 */
public class DepositGUIController implements Initializable {
    public TextField depositAmount;
    public Label warningText;
    @FXML
    private void backButtonClicked (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 860, 352)); 
    }
    @FXML
    private void depositButtonClicked (ActionEvent event) throws Exception{
        if(isNumeric(depositAmount.getText())) {
                Double balance = Double.parseDouble(depositAmount.getText());
                if (balance < 1) {
                    warningText.setText("You must deposit at least $1.");
                }
                else {
                    Manager m = Main.getManager();
                    Customer c = m.getCustomers().get(m.getCurrentCustomer());
                    balance += c.account.getAmount();
                    System.out.println("Before: " + c.account.getAmount());
                    c.account.setAmount(balance);
                    m.addCustomer(c);
                    System.out.println("After: " + c.account.getAmount());
                    Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
                    Main.getStage().setScene(new Scene(root, 860, 352)); 
                }
        }
        else 
            warningText.setText("Please enter a valid amount.");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager m = Main.getManager();
        Customer c = m.getCustomers().get(m.getCurrentCustomer());
    }    
    
}
