/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Work
 */
public class Draft {
    private ObservableList<DraftedTeam> teams;
    private ObservableList<Player> availablePlayers;
   // private ObservableList<Log> draftLog;
    public Draft(){
        teams = FXCollections.observableArrayList();
        availablePlayers = FXCollections.observableArrayList();
    }
    private void updatePoints(){
        
    }
    public void addTeam(){
        
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

    void clearLog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
