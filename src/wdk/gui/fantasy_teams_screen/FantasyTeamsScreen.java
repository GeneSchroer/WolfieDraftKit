/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.stage.Stage;
import wdk.data.DraftDataManager;
import wdk.gui.MenuScreen;
import wdk.gui.MenuView;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author Work
 */
public class FantasyTeamsScreen implements MenuScreen{
    private final FantasyTeamsController fantasyTeamsController;
    private final FantasyTeamsView fantasyTeamsView;

    
    
    public FantasyTeamsScreen(Stage initPrimaryStage, WDK_GUI gui, MessageDialog mD, YesNoCancelDialog yNCD){
        fantasyTeamsController = new FantasyTeamsController(initPrimaryStage, gui.getDataManager().getDraft(), mD, yNCD);
        fantasyTeamsView = new FantasyTeamsView(fantasyTeamsController, gui);
    }
    @Override
    public void setVisible(boolean b) {
        fantasyTeamsView.getScreen().setVisible(b);
    }

    @Override
    public MenuView getView() {
        return fantasyTeamsView;
    }

    @Override
    public void reset() {
        fantasyTeamsView.reset();
    }

    public void initGUI() {
        fantasyTeamsView.initGUI();
    }

    public void update() {
        fantasyTeamsView.update();
    }
    
}
