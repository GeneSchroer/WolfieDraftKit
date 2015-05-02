/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.GeneralPropertyType.REMOVE_PLAYER_MESSAGE;
import static wdk.GeneralPropertyType.REMOVE_TEAM_MESSAGE;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.Player;
import wdk.data.Team;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;
import wdk.gui.players_screen.PlayerDialog;

/**
 *
 * @author Work
 */
public class FantasyTeamsController {

    public void handleEditDraftNameRequest(WDK_GUI gui, String newValue) {
        gui.getDataManager().getDraft().setDraftName(newValue);
        gui.updateToolbarControls(false);
    }
    private final MessageDialog messageDialog;
    private final YesNoCancelDialog yesNoCancelDialog;
    private final TeamDialog td;
    
     PlayerDialog pd;

    public FantasyTeamsController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        td = new TeamDialog(initPrimaryStage, draft, initMessageDialog);
        this.messageDialog = initMessageDialog;
        this.yesNoCancelDialog = initYesNoCancelDialog;
                pd = new PlayerDialog(initPrimaryStage, draft, initMessageDialog);

    }

    public void handleAddTeamRequest(WDK_GUI gui) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        if(draft.getNumTeams()>=10){
            messageDialog.show("Too Many Teams");
        }
        else{
            td.showAddTeamDialog();
            Team team = td.getTeam();
            draft.addTeam(team);
       
            gui.updateGUI(false);
        }

    }
    public void handleRemoveTeamRequest(WDK_GUI gui, String teamToRemove) {
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_TEAM_MESSAGE));
        
        String selection = yesNoCancelDialog.getSelection();
        
        if (selection.equals(YesNoCancelDialog.YES)) { 
            Draft draft = gui.getDataManager().getDraft();
            draft.removeTeam(teamToRemove);
            
            gui.updateGUI(false);
        }        
    }
    
    public void handleEditTeamRequest(FantasyTeamsView aThis, Team teamToEdit){
        
    
    }
    public void handleEditPlayerRequest(WDK_GUI gui, Player playerToEdit) throws Exception{
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        ArrayList<String> teamNames = getTeamNames(draft);
        
        pd.showEditPlayerDialog(playerToEdit, draft.getTeams());
        
        
        
        //More to do
        
        
        // DID THE USER CONFIRM?
        if (pd.wasCompleteSelected()) {
            Team team;
            if(pd.getTeamName().equals(FREE_AGENT)){
                if(!playerToEdit.getFantasyTeam().equals(FREE_AGENT)){
                    team = draft.getTeam(playerToEdit.getFantasyTeam());
                    team.removePlayer(playerToEdit);
                }
            }
            else{
            // UPDATE THE SCHEDULE ITEM
                if(!playerToEdit.getFantasyTeam().equals(FREE_AGENT)){
                    team = draft.getTeam(playerToEdit.getFantasyTeam());
                    team.removePlayer(playerToEdit);
                } 
                Player player = pd.getPlayer();
                    team = pd.getTeam();
                    team.addPlayer(playerToEdit, pd.getPosition());
                    playerToEdit.setContract(pd.getContract());
                    playerToEdit.setSalary(player.getSalary());
                    
                
                
            
            
            
            //Update the Toolbar, because this is not saved.
            
            }
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }   
        gui.updateGUI(false);
    }
     public ArrayList<String> getTeamNames(Draft d){
        ObservableList<Team> temp = d.getTeams();
        ArrayList<String> nameList = new ArrayList();
        for(int i = 0; i < temp.size() ; ++i)
            nameList.add(temp.get(i).getName());
        return nameList;
    }
    
}
