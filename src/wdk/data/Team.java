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
    private ObservableList<Position> positionsList;
    private IntegerProperty salaryLeft;
    private DoubleProperty moneyPerPlayer;
    private int catchers;
    private int outFielders;
    private int pitchers;
    private int taxi;
    
    public Team(){
        name = new SimpleStringProperty();
        owner = new SimpleStringProperty();
        positionsList = FXCollections.observableArrayList();
        catchers = 0;
        outFielders = 0;
        pitchers = 0;
        taxi = 0;
    }
    public Team(String name, String owner, int c, int b1, int ci, int b3, int b2, int mi, int ss, int of, int u, int p, int t){
        this.name = new SimpleStringProperty(name);
        this.owner = new SimpleStringProperty(owner);
        positionsList = FXCollections.observableArrayList();
        
        catchers = c;
        
        if (b1 == 1)
            positionsList.add(Position.B1);
        if (ci == 1)
            positionsList.add(Position.CI);
        if (b3 == 1)
            positionsList.add(Position.B3);
        if (b2 == 1)
            positionsList.add(Position.B2);
        if (mi == 1)
            positionsList.add(Position.MI);
        if (ss == 1)
            positionsList.add(Position.SS);
        if (u ==1)
            positionsList.add(Position.U);
        outFielders = of;
        pitchers = p;
        taxi = t;    
    }
    
    
    public Team(String name, String owner){
        this.name.set(name);
        this.owner.set(owner);
        
    }
    
    public boolean taxiSquadFilled(){
        return taxi == 11;
    }
    public boolean pitchersFilled(){
        return pitchers  == 9;
    }
    public boolean catchersFilled(){
        return catchers == 2;
    }
    public boolean outFieldersFilled(){
        return outFielders == 5;
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
            return positionsList.contains(pos);
        }
        
        
    }
    
    public void addPlayer(Player p, Position pos) throws Exception{
        
        
        if(positionFilled(pos))
            if(!taxiSquadFilled()){
                p.setDraftType(DraftType.TAXI);
                p.setFantasyTeam(getName());
                p.setTeamPosition(pos);
                ++taxi;
            
            }
            else
                throw new Exception("This position is full ");
        else{
            p.setFantasyTeam(getName());
            p.setTeamPosition(pos);
            p.setDraftType(DraftType.STARTING);
            if(pos.equals(Position.C))
                ++catchers;
            else if(pos.equals(Position.OF))
                ++outFielders;
            else if(pos.equals(Position.P))
                ++pitchers ;
            else
                positionsList.add(pos);
        }
        
        
    }
    public void removePlayer(Player p) throws Exception{
        
//        if(positionFilled(pos))
//            throw new Exception("This position is full ");
//        else{
            if(p.getDraftType().equals(DraftType.TAXI)){
                --taxi;
                p.setFantasyTeam(FREE_AGENT);
                p.setDraftType(DraftType.NONE);
                p.setTeamPosition(Position.NONE);
                p.setContract(Contract.NONE);
                p.setSalary(0);
            }
            else if(p.getDraftType().equals(DraftType.STARTING)){
            
                if(p.getTeamPosition().equals(Position.C))
                    --catchers;
                else if(p.getTeamPosition().equals(Position.OF))
                    --outFielders;
                else if(p.getTeamPosition().equals(Position.P))
                    --pitchers ;
                else
                    positionsList.remove(p.getTeamPosition());
                p.setFantasyTeam(FREE_AGENT);
                p.setDraftType(DraftType.NONE);
                p.setTeamPosition(Position.NONE);
                p.setContract(Contract.NONE);
                p.setSalary(0);
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
    
    
    
    public int getPosition(Position pos){
        if(pos.equals(Position.C)){
            return catchers;
        }
        else if(pos.equals(Position.OF)){
            return outFielders;
        }
        else if(pos.equals(Position.P)){
            return pitchers;
        }
        else
            if(positionsList.contains(pos))
                return 1;
        
        return 0;
        
        
    }
    public int getTaxi(){
        return taxi;
    }
    
}
