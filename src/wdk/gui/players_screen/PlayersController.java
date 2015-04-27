/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import wdk.gui.TeamDialog;
import javafx.stage.Stage;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;
import wdk.data.Player;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import static wdk.GeneralPropertyType.REMOVE_PLAYER_MESSAGE;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.gui.PlayerDialog;



public class PlayersController {
    PlayerDialog pd;
    TeamDialog td;
    Draft draft;
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    public PlayersController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog){
        pd = new PlayerDialog(initPrimaryStage, draft, initMessageDialog);
        td = new TeamDialog(initPrimaryStage, draft, initMessageDialog);
        this.messageDialog = initMessageDialog;
        this.yesNoCancelDialog = initYesNoCancelDialog;
        this.draft = draft;
    }

    public void handleSelectPlayerTypeRequest(PlayersView playersView, String position){
            
            if(!playersView.getAvailablePlayers().isEmpty()){
                playersView.setSortPosition(position);
                playersView.update();
            }
    }
        
    public void handleSearchForPlayerRequest(PlayersView playersView, String name){
        playersView.setSortPlayer(name);
        playersView.update();
    }
    public void handleAddPlayerRequest(WDK_GUI gui){
        DraftDataManager ddm = gui.getDataManager();
        draft = ddm.getDraft();
        pd.showAddPlayerDialog();
        if(pd.wasCompleteSelected()){
            Player p = pd.getPlayer();
            draft.addPlayer(p);
            gui.updateGUI(false);
        }
        else{
            //
        }
    }
    public void handleRemovePlayerRequest(WDK_GUI gui, Player playerToRemove) {
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_PLAYER_MESSAGE));
        
        String selection = yesNoCancelDialog.getSelection();
        
        if (selection.equals(YesNoCancelDialog.YES)) { 
            gui.getDataManager().getDraft().removePlayer(playerToRemove);
            //Update the Toolbar, because this is not saved.
            gui.updateGUI(false);
        }        
    }

    void handleEditPlayerRequest(WDK_GUI gui, Player playerToEdit) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        pd.showEditPlayerDialog(playerToEdit);
        
        // DID THE USER CONFIRM?
        if (pd.wasCompleteSelected()) {
            // UPDATE THE SCHEDULE ITEM
            Player player = pd.getPlayer();
            
            //playerToEdit.setFantasyTeam(player.getFantasyTeam());
            //playerToEdit.setTeamPosition(player.getTeamPosition());
            //player.setContract(player.getContract());
           // playerToEdit.setSalary(player.getSalary());
            
            
            //Update the Toolbar, because this is not saved.
            gui.updateToolbarControls(false);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }   
    }
    
}
