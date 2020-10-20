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
public class Silver extends Level {
    double fee = 20;
    @Override
    public void checkLevel(Customer c) {
        System.out.println(c.account.getAmount());
        if(c.account.getAmount() >= 20000)
            c.setLevel(new Platinum());
        else if(c.account.getAmount() >= 10000)
            c.setLevel(new Gold());
        else
            c.setLevel(new Silver());
    }
    public double getFee() {
        return this.fee;
    }
}
