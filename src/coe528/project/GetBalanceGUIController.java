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
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Rohan Zahid
 */
public class GetBalanceGUIController implements Initializable {
    public Label balance;
    public Label name;
    @FXML
    private void backButtonClicked (ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 860, 352)); 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager m = Main.getManager();
        Customer c = m.getCustomers().get(m.getCurrentCustomer());
        balance.setText("$" + String.format("%.2f", c.account.getAmount()));
        name.setText(c.getUsername() + ", you have");
    }    
    
}
