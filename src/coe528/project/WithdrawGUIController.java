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
public class WithdrawGUIController implements Initializable {
    public TextField withdrawAmount;
    public Label warningText;
    public Label successText;
    @FXML
    private void backButtonClicked (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 860, 352)); 
    }
    @FXML
    private void withdrawButtonClicked (ActionEvent event) throws Exception{
        warningText.setText("");
        successText.setText("");
        Manager m = Main.getManager();
        Customer c = m.getCustomers().get(m.getCurrentCustomer());
        if(isNumeric(withdrawAmount.getText())) {
                Double balance = Double.parseDouble(withdrawAmount.getText());
                if (balance < 1) {
                    warningText.setText("You must withdraw at least $1");
                }
                else if (balance > c.account.getAmount()) {
                    warningText.setText("You do not have enough funds!");
                }
                else {
                    Double newBalance = c.account.getAmount() - balance;
                    System.out.println("Before: " + c.account.getAmount());
                    c.account.setAmount(newBalance);
                    m.addCustomer(c);
                    System.out.println("After: " + c.account.getAmount());
                    successText.setText("The withdrawal of $" + String.format("%.2f", balance) + " has been completed.");
                    //Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
                    //Main.getStage().setScene(new Scene(root, 860, 352)); 
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
        Customer c = m.getCustomers().get(m.getCurrentCustomer());;
        warningText.setText("");
        successText.setText("");
    }    
    
}
