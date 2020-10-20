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
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Rohan Zahid
 */
public class CustomerGUIController implements Initializable {
    public Label welcomeMessage;
    public ImageView levelPic;
    public Image newImg;
    @FXML
    private void signOutButtonClicked (ActionEvent event) throws Exception{
        System.out.println("Sign out");
        Parent root = FXMLLoader.load(getClass().getResource("simplebankaccount.fxml"));
        Main.getStage().setScene(new Scene(root, 700, 395));
    }
    @FXML
    private void depositButtonClicked (ActionEvent event) throws Exception{
        System.out.println("Deposit");
        Parent root = FXMLLoader.load(getClass().getResource("depositGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 561, 352));
    }
    @FXML
    private void getBalanceButtonClicked (ActionEvent event) throws Exception{
        System.out.println("Get Balance");
        Parent root = FXMLLoader.load(getClass().getResource("getBalanceGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 561, 352));
    }
    @FXML
    private void withdrawButtonClicked (ActionEvent event) throws Exception{
        System.out.println("Withdraw");
        Parent root = FXMLLoader.load(getClass().getResource("withdrawGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 561, 352));
    }
    @FXML
    private void onlinePurchaseButtonClicked (ActionEvent event) throws Exception{
        System.out.println("Online Purchase");
        Parent root = FXMLLoader.load(getClass().getResource("onlinePurchaseGUI.fxml"));
        Main.getStage().setScene(new Scene(root, 561, 352));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Manager m = Main.getManager();
        Customer c = m.getCustomers().get(m.getCurrentCustomer());
        c.checkLevel();
        System.out.println(c.getLevel().getFee());
        if(c.getLevel().getFee() == 0) {
            Image image = new Image(getClass().getResource("platinum.png").toExternalForm());
            levelPic.setImage(image);
        }
        else if(c.getLevel().getFee() == 10) {
            Image image = new Image(getClass().getResource("gold.png").toExternalForm());
            levelPic.setImage(image);
        }
        welcomeMessage.setText("Welcome, " + c.getUsername());
    }    
    
}
