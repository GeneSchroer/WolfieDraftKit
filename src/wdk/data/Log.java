/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Work
 */



public class Log {
    Player player;
    String teamName;
    Contract contract;
    DoubleProperty salary;
    
    Log(){
        player = new Player();
        teamName = "";
        contract = Contract.NONE;
        salary = new SimpleDoubleProperty(0);
    }
    
    Log(Player p, String tN, Contract c, double s){
        this();
        
    }
    
}
