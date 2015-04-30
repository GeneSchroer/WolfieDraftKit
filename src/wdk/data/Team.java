/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.WDK_StartUpConstants.FREE_AGENT;

/**
 *
 * @author Work
 */
public class Team {
    private IntegerProperty playersNeeded;
    private StringProperty name;
    private StringProperty owner;
    private ObservableList<Position> positionsFilled;
    private IntegerProperty salaryLeft;
    private DoubleProperty moneyPerPlayer;
    private int catchersNeeded;
    private int outFieldersNeeded;
    private int pitchersNeeded;
    private int taxiNeeded;
    
    public Team(){
        name = new SimpleStringProperty();
        owner = new SimpleStringProperty();
        positionsFilled = FXCollections.observableArrayList();
        catchersNeeded = 2;
        outFieldersNeeded = 5;
        pitchersNeeded = 9;
        taxiNeeded = 11;
    }
    public Team(String name, String owner){
        this.name.set(name);
        this.owner.set(owner);
        
    }
    
    public boolean taxiSquadFilled(){
        return taxiNeeded == 0;
    }
    public boolean pitchersFilled(){
        return pitchersNeeded == 0;
    }
    public boolean catchersFilled(){
        return catchersNeeded == 0;
    }
    public boolean outFieldersFilled(){
        return outFieldersNeeded == 0;
    }
    
    
    
    
    
    public void setPlayersNeeded(int pn){
        playersNeeded.set(pn);
    }
    
    public int getPlayersNeeded(){
        return playersNeeded.get();
    }
    
    public IntegerProperty playersNeededProperty(){
        return playersNeeded;
    }
    
    public void setName(String n){
        name.set(n);
    }
    
    public String getName(){
        return name.get();
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public void setOwner(String o){
        owner.set(o);
    }
    
    public String getOwner(){
        return owner.get();
    }
    
    public StringProperty ownerProperty(){
        return owner;
    }
    
    public boolean positionFilled(Position pos){
        if(pos.equals(Position.C)){
            return catchersFilled();
        }
        else if(pos.equals(Position.OF)){
            return outFieldersFilled();
        }
        else if(pos.equals(Position.P)){
            return pitchersFilled();
        }
        else{
            return positionsFilled.contains(pos);
        }
        
        
    }
    
    public void addPlayer(Player p, Position pos) throws Exception{
        
        if(positionFilled(pos))
            throw new Exception("This position is full ");
        else{
            p.setFantasyTeam(getName());
            p.setTeamPosition(pos);
            p.setDraftType(DraftType.STARTING);
            if(pos.equals(Position.C))
                --catchersNeeded;
            else if(pos.equals(Position.OF))
                --outFieldersNeeded;
            else if(pos.equals(Position.P))
                --pitchersNeeded;
            else
                positionsFilled.add(pos);
        }
        
        
    }
    public void removePlayer(Player p) throws Exception{
        
//        if(positionFilled(pos))
//            throw new Exception("This position is full ");
//        else{
            p.setFantasyTeam(FREE_AGENT);
            p.setDraftType(DraftType.NONE);
            if(p.getTeamPosition().equals(Position.C))
                ++catchersNeeded;
            else if(p.getTeamPosition().equals(Position.OF))
                ++outFieldersNeeded;
            else if(p.getTeamPosition().equals(Position.P))
                ++pitchersNeeded;
            
//        }
           else
             positionsFilled.remove(p.getTeamPosition());
            
            p.setTeamPosition(null);
        
        
    }
    public void addPlayerToTaxi(Player p, Position pos){
        if(!taxiSquadFilled()){
            p.setFantasyTeam(getName());
            p.setTeamPosition(pos);
            --taxiNeeded;
        }
    }
    public boolean allPositonsFilled(){
        return positionFilled(Position.C)
                && positionFilled(Position.B1)
                    && positionFilled(Position.CI)
                            && positionFilled(Position.B3)
                                &&positionFilled(Position.B2)
                                    &&positionFilled(Position.MI)
                                        &&positionFilled(Position.SS)
                                            &&positionFilled(Position.OF)
                                            &&positionFilled(Position.U)
                                                &&positionFilled(Position.P);
    }
    
    
    
    
    
}
