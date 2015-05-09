/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Work
 */



public class Log {
    StringProperty lastName;
    StringProperty firstName;
    StringProperty fantasyTeam;
    DoubleProperty salary;
    ObjectProperty<Contract> contract;
    Log(){
        
        lastName = new SimpleStringProperty();
        firstName = new SimpleStringProperty();
        fantasyTeam = new SimpleStringProperty();
        contract = new SimpleObjectProperty();
        salary = new SimpleDoubleProperty(0);
    }
    
    Log(String l, String f, String tN, Contract c, double s){
        this();
        
        
    }
    
     public void setLastName(String ln){
        lastName.set(ln);
    }
    
    public String getLastName(){
        return lastName.get();
    }
    
    public StringProperty lastNameProperty(){
        return lastName;
    }
     public void setFirstName(String fn){
        firstName.set(fn);
    }
    
    public String getFirstName(){
        return firstName.get();
    }
    
    public StringProperty firstNameProperty(){
        return firstName;
    }
     public String getFantasyTeam(){
        return fantasyTeam.get();
    }
    public void setFantasyTeam(String ft){
        fantasyTeam.set(ft);
    }
    public StringProperty fantasyTeamProperty(){
        return fantasyTeam;
    }
     public void setContract(Contract c){
        contract.set(c);
    }
      public Contract getContract(){
        return contract.get();
    }
      public ObjectProperty<Contract> contractProperty(){
          return contract;
      }
      
       public void setSalary(double s){
        salary.set(s);
    }
    
    public double getSalary(){
        return salary.get();
    }
    
    public DoubleProperty salaryProperty(){
        return salary;
    }
}
