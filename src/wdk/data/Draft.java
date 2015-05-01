/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.WDK_StartUpConstants.FREE_AGENT;

/**
 *
 * @author Work
 */
public class Draft {
    private ObservableList<Team> teams;
    private ObservableList<Player> availablePlayers;
    
    
   // private ObservableList<Log> draftLog;
    private String draftName;
    private int numTeams;
    public Draft(){
        teams = FXCollections.observableArrayList();
        availablePlayers = FXCollections.observableArrayList();
        numTeams=0;
        draftName = "bob";
    }
    private void updatePoints(){
        
    }
    public void addTeam(Team t){
        teams.add(t);
        ++numTeams;
    }
    public void removeTeam(String teamToRemove){
     //   Team draft = teams.c
       // teams.remove(team);
        for(int i = 0; i < availablePlayers.size() ; ++i){
            if(availablePlayers.get(i).getFantasyTeam().equals(teamToRemove)){
                availablePlayers.get(i).setFantasyTeam(FREE_AGENT);
                availablePlayers.get(i).setContract(Contract.NONE);
                availablePlayers.get(i).setSalary(0);
                availablePlayers.get(i).setTeamPosition(Position.NONE);
                
            }
        }
    }
    public void addPlayer(Player playerToAdd){
        availablePlayers.add(playerToAdd);
        
    }
    public Player removePlayer(Player playerToRemove){
        availablePlayers.remove(playerToRemove);
        return playerToRemove;
    }
    public void addPlayerToTeam(Player player, Team team){
        
    }
    public void removePlayerFromTeam(Player player, Team team){
        
    }
    
    public ObservableList<Player> getAvailablePlayers(){
        return availablePlayers;
    }
  

    void clearTeams() {
        teams.clear();
    }

    public ObservableList<Team> getTeams() { 
        return teams;
    }
    
    public int getNumTeams(){
        return numTeams;
    }
    
    public String getDraftName(){
        return draftName;
    }
    public void setDraftName(String dn){
        draftName = dn;
    }
    
    
    public void setAvailablePlayers(ObservableList<Player> players){
        availablePlayers = FXCollections.observableArrayList(players);
    }
    
}
