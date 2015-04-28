/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.awt.Image;
import java.util.Collections;
import java.util.Comparator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author Work
 */
public class Player implements Comparable {
    StringProperty proTeam;
    StringProperty lastName;
    StringProperty firstName;
    StringProperty fullName;
    StringProperty qualifiedPositions;
    StringProperty notes;
    StringProperty yearOfBirth;
    StringProperty nationOfBirth;
    StringProperty fantasyTeam;

    ObservableList<Position> PositionList;
        
    //Position positions;
    
    
    Contract contract;
    DoubleProperty salary;
    Image image;
   
    
    public Player() {
        proTeam             = new SimpleStringProperty();
        lastName            = new SimpleStringProperty();
        firstName           = new SimpleStringProperty();
        fullName            = new SimpleStringProperty();
        qualifiedPositions  = new SimpleStringProperty();
        notes               = new SimpleStringProperty();
        yearOfBirth         = new SimpleStringProperty();
        nationOfBirth       = new SimpleStringProperty();
        fantasyTeam         = new SimpleStringProperty();
        
       PositionList = FXCollections.observableArrayList();
       
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
    
    @Override
    public int compareTo(Object obj){
        Player otherPlayer = (Player)obj;
        return getLastName().compareTo(otherPlayer.getLastName());
    }
    public void addPosition(Position ep){
        if(!PositionList.contains(ep)){
            PositionList.add(ep);
            FXCollections.sort(PositionList, new Comparator<Position>(){
                @Override
                public int compare(Position p1, Position p2){
                    if(p1.ordinal() > p2.ordinal())
                        return 1;
                    if(p1.ordinal() < p2.ordinal())
                        return -1;
                    
                    return 0;
                }
        });
            setQualifiedPositions(positionString());
        }
        else{
            
        }
        
    }
    
    public ObservableList getPositionList(){
        return PositionList;
    }
    
    public void removePosition(Position ep){
        if(PositionList.contains(ep)){
            PositionList.remove(ep);
            setQualifiedPositions(positionString());
        }
        
    }
           
    public String positionString(){
            String positions = "";
            for(int i = 0 ; i < PositionList.size() ; ++i){
                positions += PositionList.get(i).toString() + "_";
            }
            if (!positions.equals(""))
                positions = positions.subSequence(0, positions.length()-1).toString();

            return positions;
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
    
    
//    public void addPosition(ExplicitPosition position){
//        
//    }
    public static class PlayerBuilder {
        private final StringProperty lastName;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty proTeam;
    
        public PlayerBuilder(String firstName, String lastName, String proTeam){
        this.lastName = new SimpleStringProperty(lastName);
        this.firstName = new SimpleStringProperty(firstName);
        this.proTeam = new SimpleStringProperty(proTeam);
        }
        
     
        
        
    }
}

