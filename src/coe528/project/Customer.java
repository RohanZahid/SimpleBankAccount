/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 * Overview: Customer is a mutable class that is responsible for the representation
 *           of a customer of the bank, with a username, password, role, level, and bank account.
 *           The customer is able to login, logout, deposit money, withdraw money, get balance, 
 *           and do online purchase(s).
 * 
 * AF(c) =   A customer that can login, logout, deposit money, withdraw money, 
 *           get balance, and do online purchase(s).
 *           The customer's username = c.username. 
 *           The customer's password = c.password. 
 *           The customer's level, based on how much money the customer has in their account (ranging from Silver to Gold to Platinum) = c.level. 
 *           The customer's  role = c.role. 
 *           The customer's bank account, which contains the customer's balance = c.account.
 * 
 * RI(c) =   true if c.username does not equal to null and c.password does not equal to null and c.level does not equal to null and c.account.getAmount() is greater than or equal to 0
 *       =   false, otherwise
 */
public class Customer extends User{
    private Level level = new Silver();
    BankAccount account;
    /**
     * EFFECTS: Creates a new Customer object
     * @param username
     * @param password
     * @param role
     * @param account
     */
    public Customer(String username, String password, String role, BankAccount account) {
        super(username, password, role);
        this.account = account;
        checkLevel();
    }
    
    /**
     * MODIFIES: this
     * EFFECTS: Changes the level of this Customer to the given parameter
     * @param l
     */
    public void setLevel(Level l) {
        level = l;
    }
    
    /**
     * EFFECTS: Returns the level of this Customer
     * @return level
     */
    public Level getLevel() {
        return level;
    }
    
    /**
     * MODIFIES: this
     * EFFECTS: Checks the level of this Customer and changes the level if necessary
     */
    public void checkLevel() {
        level.checkLevel(this);
    }
    
    /**
     * EFFECTS: Returns true if the username of this Customer matches the given parameter; otherwise, returns false
     * @param username
     * @return true or false
     */
    public boolean login(String username) {
        if(this.getUsername().equals(username))
            return true;
        return false;
    }
    
    /**
     * EFFECTS: Returns true if the rep invariant holds for this            
     *          object; otherwise returns false     
     * @return true or false
     */
    public boolean repOK() {         
        if (this.getUsername() != null && this.getPassword() != null && this.getLevel() != null && this.account.getAmount() >= 0)
            return true;
        return false;
    }  
    
    /**
     * EFFECTS: Returns a string that contains the identifying information about the current Customer. Implements the abstraction function.
     */
    @Override
    public String toString() {
        if(repOK() == false)
            return "ERROR! Invalid information.";
        return this.getUsername() + " " + this.getPassword() + " " + this.getRole() + " " + this.account.getAmount() + "\n";
    }
}
