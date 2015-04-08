/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.collections.ObservableList;

/**
 *
 * @author Work
 */
public class Draft {
    private ObservableList<DraftedTeam> teams;
    private ObservableList<? extends Player> availablePlayers;
    private ObservableList<Log> draftLog;
    public Draft(){
        
    }
    private void updatePoints(){
        
    }
    public void addTeam(){
        
    }
    public DraftedTeam removeTeam(DraftedTeam team){
        return team;
    }
    public void addPlayer(){
        
    }
    public Player removePlayer(Player player){
        
        return player;
    }
    public void addPlayerToTeam(Player player, DraftedTeam team){
        
    }
    public void removePlayerFromTeam(Player player, DraftedTeam team){
        
    }
}
