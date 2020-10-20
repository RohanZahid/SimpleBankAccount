/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Rohan Zahid
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage sendStage;
    private static Manager m = new Manager("admin", "admin", "manager");
    public static void main(String[] args) {
        launch(args);
    }
    public static Stage getStage() {
        return sendStage;
    }
    public static Manager getManager() {
        return m;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        sendStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("simplebankaccount.fxml"));
        primaryStage.setTitle("Simple Banking");
        primaryStage.setScene(new Scene(root, 700, 395));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
