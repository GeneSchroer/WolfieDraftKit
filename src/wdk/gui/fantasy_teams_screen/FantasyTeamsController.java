/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.GeneralPropertyType.REMOVE_PLAYER_MESSAGE;
import static wdk.GeneralPropertyType.REMOVE_TEAM_MESSAGE;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.Team;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author Work
 */
public class FantasyTeamsController {
    private final MessageDialog messageDialog;
    private final YesNoCancelDialog yesNoCancelDialog;
    private final TeamDialog td;
    
    

    public FantasyTeamsController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        td = new TeamDialog(initPrimaryStage, draft, initMessageDialog);
        this.messageDialog = initMessageDialog;
        this.yesNoCancelDialog = initYesNoCancelDialog;
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
         yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_PLAYER_MESSAGE));
        
        String selection = yesNoCancelDialog.getSelection();
        
        if (selection.equals(YesNoCancelDialog.YES)) { 
            Draft draft = gui.getDataManager().getDraft();
            draft.removeTeam(teamToRemove);
            
            gui.updateGUI(false);
        }        
    }
    
    public void handleEditTeamRequest(FantasyTeamsView aThis, Team teamToEdit){
        
    
    }
    
    
}
