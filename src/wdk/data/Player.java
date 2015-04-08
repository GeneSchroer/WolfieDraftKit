/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.awt.Image;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Work
 */
public class Player {
    StringProperty lastName;
    StringProperty firstName;
    StringProperty fullName;
    IntegerProperty age;
    Position currentPosition;
    StringProperty team;
    ObservableList<Position> qualifiedPositions;
    Contract contract;
    DoubleProperty salary;
    Image image;
   
    
    public Player(){
        lastName.set("Ballrisian");
        firstName.set("Bando");
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
    public void setFullName(String fn){
        fullName.set(fn);
    }
    
    public String getFullName(){
        return fullName.get();
    }
    
    public StringProperty fullNameProperty(){
        return fullName;
    }
    
    public void setAge(int a){
        age.set(a);
    }
    
    public int getAge(){
        return age.get();
    }
    
    public IntegerProperty ageProperty(){
        return age;
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
    public void setContract(Contract c){
        contract = c;
    }
    
    public Contract getContract(){
        return contract;
    }
    
    public void setImage(Image img){
        image = img;
    }
    public Image getImage(){
        return image;
    }
    
   
}

