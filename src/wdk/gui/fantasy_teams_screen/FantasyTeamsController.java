/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.stage.Stage;
import wdk.data.Draft;
import wdk.gui.MessageDialog;
import wdk.gui.PlayerDialog;
import wdk.gui.YesNoCancelDialog;
import wdk.gui.TeamDialog;

/**
 *
 * @author Work
 */
public class FantasyTeamsController {
    private final MessageDialog messageDialog;
    private final YesNoCancelDialog yesNoCancelDialog;
    private final PlayerDialog pd;
    private final TeamDialog td;
    
    

    public FantasyTeamsController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        pd = new PlayerDialog(initPrimaryStage, draft, initMessageDialog);
        td = new TeamDialog(initPrimaryStage, draft, initMessageDialog);
        this.messageDialog = initMessageDialog;
        this.yesNoCancelDialog = initYesNoCancelDialog;
    }

    void handleOwnerChangeRequest(FantasyTeamsView aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void handleNameChangeRequest(FantasyTeamsView aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    void handleRemoveTeamRequest(FantasyTeamsView aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    void handleAddTeamRequest(FantasyTeamsView aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
