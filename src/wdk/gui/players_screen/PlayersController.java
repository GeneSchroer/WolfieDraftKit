/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import wdk.gui.fantasy_teams_screen.TeamDialog;
import javafx.stage.Stage;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;
import wdk.data.Player;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import static wdk.GeneralPropertyType.REMOVE_PLAYER_MESSAGE;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import wdk.data.Contract;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.DraftType;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Position;
import wdk.data.Team;



public class PlayersController {
    PlayerDialog pd;
    TeamDialog td;
    Draft draft;
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    public PlayersController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog, ArrayList<String> proTeams){
        pd = new PlayerDialog(initPrimaryStage, draft, initMessageDialog, proTeams);
        td = new TeamDialog(initPrimaryStage, draft, initMessageDialog);
        this.messageDialog = initMessageDialog;
        this.yesNoCancelDialog = initYesNoCancelDialog;
        this.draft = draft;
    }

    public void handleSelectPlayerTypeRequest(PlayersView playersView, Position position){
            
         //   if(!playersView.getAvailablePlayers().isEmpty()){
                playersView.setSortPosition(position);
                playersView.update();
         //   }
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
            
            
            
            Player temp = pd.getPlayer();
            
            if(pd.getPlayer().getPositionList().contains(Position.P)){
                Pitcher p = new Pitcher(temp);
                draft.addFreePlayer(p);
            }
            else{
                Hitter p = new Hitter(temp);
                draft.addFreePlayer(p);
            }
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
            gui.getDataManager().getDraft().removeFreePlayer(playerToRemove);
            //Update the Toolbar, because this is not saved.
            gui.updateGUI(false);
        }        
    }

    public void handleEditPlayerRequest(WDK_GUI gui, Player playerToEdit) throws Exception {
        DraftDataManager ddm = gui.getDataManager();
        draft = ddm.getDraft();
        ArrayList<String> teamNames = getTeamNames(draft);
        
        pd.showEditPlayerDialog(playerToEdit, draft.getTeams());
        
        
        
        //More to do
        
        
        // DID THE USER CONFIRM?
        if (pd.wasCompleteSelected()) {
            Team team;
            if(pd.getTeamName().equals(FREE_AGENT)){
                if(!playerToEdit.getFantasyTeam().equals(FREE_AGENT)){
                    team = draft.getTeam(playerToEdit.getFantasyTeam());
                    draft.removeTeamPlayer(playerToEdit, team);
                }
            }
            else{
            // UPDATE THE SCHEDULE ITEM
                if(!playerToEdit.getFantasyTeam().equals(FREE_AGENT)){
                    team = draft.getTeam(playerToEdit.getFantasyTeam());
                    draft.editTeamPlayer(playerToEdit, team, pd.getDraftType(), pd.getSalary(), pd.getContract(), pd.getPosition());
                } 
                Player player = pd.getPlayer();
                    
                    playerToEdit.setContract(pd.getContract());
                    playerToEdit.setSalary(pd.getSalary());
                    team = pd.getTeam();
                    draft.addTeamPlayer(playerToEdit, team, pd.getPosition());
            
            //Update the Toolbar, because this is not saved.
            gui.updateGUI(false);
            }
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }   
    }
    
    
    public ArrayList<String> getTeamNames(Draft d){
        ObservableList<Team> temp = d.getTeams();
        ArrayList<String> nameList = new ArrayList();
        for(int i = 0; i < temp.size() ; ++i)
            nameList.add(temp.get(i).getName());
        return nameList;
    }
    
}
