/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
/**
 *
 * @author Rohan Zahid
 */
public class Manager extends User{
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private int currentCustomer;
    public Manager(String username, String password, String role) {
        super(username, password, role);
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    public void addCustomer(Customer c) {
        customers.add(c);
        try {             
            FileWriter fps = new FileWriter(new File(c.getUsername() + ".txt"), false);
            //fps.write(c.getUsername() + " " + c.getPassword() + " " + c.getRole() + " " + c.account.getAmount() + "\n");
            fps.write(c.toString());
            fps.close();
            
        } catch (IOException e) {             
            System.out.println("An error occurred.");
            e.printStackTrace();  
        }
    }
    public void removeCustomer(String s, String x) {
        File file = new File(s);
        file.delete();
        for(int j = 0; j < customers.size(); j++)
            if(customers.get(j).getUsername().equals(x))
                 customers.remove(j);
    }
    public void setCurrentCustomer(int i) {
        this.currentCustomer = i;
    }
    public int getCurrentCustomer() {
        return currentCustomer;
    }
    public boolean doesUserExist(String username) {
        for(int i = 0; i < customers.size(); i++)
            if(customers.get(i).getUsername().equals(username))
                return true;
        return false;
    }
    public boolean login(String username, String password) {
        if(this.getUsername().equals(username) && this.getPassword().equals(password))
            return true;
        return false;
    }
}
