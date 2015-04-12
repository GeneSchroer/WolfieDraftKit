/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.awt.Image;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Work
 */
public class Player {
    StringProperty proTeam;
    StringProperty lastName;
    StringProperty firstName;
    StringProperty fullName;
    StringProperty qualifiedPositions;
    StringProperty notes;
    StringProperty yearOfBirth;
    StringProperty nationOfBirth;

    
        
    //Position positions;
    
    
    Contract contract;
    DoubleProperty salary;
    Image image;
   
    
    public Player(){
        proTeam                = new SimpleStringProperty();
        lastName            = new SimpleStringProperty();
        firstName           = new SimpleStringProperty();
        fullName            = new SimpleStringProperty();
        qualifiedPositions  = new SimpleStringProperty();
        notes               = new SimpleStringProperty();
        yearOfBirth         = new SimpleStringProperty();
        nationOfBirth       = new SimpleStringProperty();
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
    public void setProTeam(String pt){
        proTeam.set(pt);
    }
    
    public String getProTeam(){
        return proTeam.get();
    }
    
    public StringProperty proTeamProperty(){
        return proTeam;
    }
    
    public void setYearOfBirth(String yob){
        yearOfBirth.set(yob);
    }
    
    public String getYearOfBirth(){
        return yearOfBirth.get();
    }
    
    public StringProperty yearOfBirthProperty(){
        return yearOfBirth;
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
     public void setNationOfBirth(String nob){
        nationOfBirth.set(nob);
    }
    
    public String getNationOfBirth(){
        return nationOfBirth.get();
    }
    
    public StringProperty nationOfBirthProperty(){
        return fullName;
    }
     public void setQualifiedPositions(String qp){
        qualifiedPositions.set(qp);
    }
    
    public String getQualifiedPositions(){
        return qualifiedPositions.get();
    }
    
    public StringProperty qualifiedPositionsProperty(){
        return qualifiedPositions;
    }
     public void setNotes(String n){
        notes.set(n);
    }
    
    public String getNotes(){
        return notes.get();
    }
    
    public StringProperty notesProperty(){
        return notes;
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

