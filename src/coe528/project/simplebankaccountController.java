package coe528.project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Rohan Zahid
 */
public class simplebankaccountController implements Initializable {
    public Label welcomeMessage;
    public Label correctPassText;
    public Label correctUserText;
    public PasswordField passText;
    public TextField userText;
    public Button loginButton;
    
    @FXML
    private void loginButtonClicked (ActionEvent event) throws Exception{
        Manager m = Main.getManager();
        correctPassText.setText("");
        correctUserText.setText("");
        correctUserText.setTextFill(Color.rgb(255, 8, 0));
        correctPassText.setTextFill(Color.rgb(255, 8, 0));
        if(userText.getText().isEmpty()) 
            correctUserText.setText("Please enter a username.");
        if(passText.getText().isEmpty())
            correctPassText.setText("Please enter a password.");
        else if(m.login(userText.getText(), passText.getText())) {
            correctPassText.setText("Your password is valid.");
            correctPassText.setTextFill(Color.rgb(19, 252, 3));
            Parent root = FXMLLoader.load(getClass().getResource("managerGUI.fxml"));
            Main.getStage().setScene(new Scene(root, 800, 352));   
        } 
        else {
            File f = new File("./");
            File[] matchingFiles = f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.equals(userText.getText() + ".txt");
                }
            });
            
            for(int i = 0; i < matchingFiles.length; i++) {
                String u = "", p = "", r = "";
                double a = 0;
                BankAccount b = new BankAccount(0);
                try {             
                    Scanner fscan = new Scanner(new File(userText.getText()+".txt"));
                    u = fscan.next();
                    p = fscan.next();
                    r = fscan.next();
                    a = Double.parseDouble(fscan.next());
                    b = new BankAccount(a);
                } catch (IOException e) {             
                    System.out.println("An error occurred.");             
                    e.printStackTrace();         
                }                     
                if(passText.getText().equals(p) && userText.getText().equals(u)) {
                    System.out.println(p + " " + u);
                    if(m.doesUserExist(u) == false) {
                        Customer c = new Customer(u, p, r, b);
                        m.getCustomers().add(c);
                    }
                    for(int j = 0; j < m.getCustomers().size(); j++)
                        if(m.getCustomers().get(j).login(u))
                            m.setCurrentCustomer(j);
                    Parent root = FXMLLoader.load(getClass().getResource("customerGUI.fxml"));
                    Main.getStage().setScene(new Scene(root, 860, 352));   
                }
            }
            correctPassText.setText("Your login is incorrect!");
            correctPassText.setTextFill(Color.rgb(255, 8, 0));
        }
        passText.clear();
    }
    @FXML
    private void clearButtonClicked(ActionEvent event) {
        userText.clear();
        passText.clear();
        correctPassText.setText("");
        correctUserText.setText("");
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
