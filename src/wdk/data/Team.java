/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Work
 */
public class Team {
    private IntegerProperty playersNeeded;
    private IntegerProperty startingLineupSize;
    private IntegerProperty taxiRosterSize;
    private StringProperty name;
    private StringProperty owner;
    private ObservableList<Hitter> hitterLine;
    private ObservableList<Pitcher> pitcherLine;
    private ObservableList<Player> taxiList;
    private IntegerProperty salaryLeft;
    private DoubleProperty moneyPerPlayer;
    
    public Team(){
        
    }
    
    public Team(String name, String owner){
        this.name.set(name);
        this.owner.set(owner);
        
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
    public ObservableList<Hitter> getHitterLine(){
        return hitterLine;
    }
    public ObservableList<Pitcher> getPitcherLine(){
        return pitcherLine;
    }
}
