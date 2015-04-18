/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import javafx.stage.Stage;
import wdk.data.Position;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;
import wdk.data.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wdk.gui.players_screen.PlayersScreen;
/**
 *
 * @author Work
 */


public class PlayersScreenController {
    
    String sortPosition = "";
    String sortPlayer = "";
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    public PlayersScreenController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog){
        
        
        this.messageDialog = initMessageDialog;
        this.yesNoCancelDialog = initYesNoCancelDialog;
    }
    public void handleSelectPlayerTypeRequest(PlayersScreen playersScreen, String position){
            
            sortPosition = position;
            ObservableList<Player> temp = FXCollections.observableArrayList();
                   temp.addAll(buildFilteredList(playersScreen.getAvailablePlayers()));
            playersScreen.setTable(temp);
    }
    private void selectPlayerSortCriteria(boolean sortStats){
        
    }
    public void handleSearchForPlayerRequest(PlayersScreen ps, String name){
        sortPlayer = name;
            ObservableList<Player> temp = FXCollections.observableArrayList();
                   temp.addAll(buildFilteredList(ps.getAvailablePlayers()));
            ps.setTable(temp);
    }
    private void handleAddPlayerRequest(WDK_GUI gui){
        
    }
    private void handleRemovePlayerRequest(WDK_GUI gui){
        
    }
    
    private List<Player> buildFilteredList(List<Player> playerList){
        ArrayList<Player> filteredList = new ArrayList();
        String sp = sortPlayer;
        for(int i = 0; i < playerList.size(); ++i){
            String ln = playerList.get(i).getLastName();
            String fn = playerList.get(i).getFirstName();
            
            
            if(sortPosition.equalsIgnoreCase("U")){
                if( (ln.toLowerCase().startsWith(sp.toLowerCase())
                    ||fn.toLowerCase().startsWith(sp.toLowerCase()))
                    && !playerList.get(i).getQualifiedPositions().contains("P")){
                filteredList.add(playerList.get(i));
                }
            }
            
            else{
            if(sortPosition.equalsIgnoreCase("CI")){
                if( (ln.toLowerCase().startsWith(sp.toLowerCase())
                    ||fn.toLowerCase().startsWith(sp.toLowerCase()))
                    && (playerList.get(i).getQualifiedPositions().contains("1B")
                        ||playerList.get(i).getQualifiedPositions().contains("3B"))){
                filteredList.add(playerList.get(i));
                }
            }
            if(sortPosition.equalsIgnoreCase("MI")){
                if( (ln.toLowerCase().startsWith(sp.toLowerCase())
                    ||fn.toLowerCase().startsWith(sp.toLowerCase()))
                    && (playerList.get(i).getQualifiedPositions().contains("2B")
                        ||playerList.get(i).getQualifiedPositions().contains("SS"))){
                filteredList.add(playerList.get(i));
                }
            }
                        
            if( (ln.toLowerCase().startsWith(sp.toLowerCase())
                    ||fn.toLowerCase().startsWith(sp.toLowerCase()))
                    && playerList.get(i).getQualifiedPositions().contains(sortPosition)){
                filteredList.add(playerList.get(i));
            }
            }
        }
        
        return filteredList;
        
    }
    
}
