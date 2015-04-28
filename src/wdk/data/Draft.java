/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Work
 */
public class Draft {
    private ObservableList<Team> teams;
    private ObservableList<Player> availablePlayers;
    
    
   // private ObservableList<Log> draftLog;
    private int numTeams;
    public Draft(){
        teams = FXCollections.observableArrayList();
        availablePlayers = FXCollections.observableArrayList();
        numTeams=0;
    }
    private void updatePoints(){
        
    }
    public void addTeam(Team t){
        teams.add(t);
        ++numTeams;
    }
    public DraftedTeam removeTeam(DraftedTeam team){
        return team;
    }
    public void addPlayer(Player playerToAdd){
        availablePlayers.add(playerToAdd);
        
    }
    public Player removePlayer(Player playerToRemove){
        availablePlayers.remove(playerToRemove);
        return playerToRemove;
    }
    public void addPlayerToTeam(Player player, DraftedTeam team){
        
    }
    public void removePlayerFromTeam(Player player, DraftedTeam team){
        
    }
    
    public ObservableList<Player> getAvailablePlayers(){
        return availablePlayers;
    }
  

    void clearTeams() {
        teams.clear();
    }

    public ObservableList<DraftedTeam> getTeams() { 
        return teams;
    }
    
    public int getNumTeams(){
        return numTeams;
    }
    
}
