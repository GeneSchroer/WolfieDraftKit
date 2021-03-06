/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import java.util.ArrayList;
import javafx.stage.Stage;
import wdk.gui.MenuScreen;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author Work
 */
public class PlayersScreen implements MenuScreen{
    
    
    PlayersView playersView;
    PlayersController playersController;

    public PlayersScreen(Stage initPrimaryStage, WDK_GUI initGUI, MessageDialog mD, YesNoCancelDialog yNCD, ArrayList<String> proTeams){
        playersController = new PlayersController(initPrimaryStage, initGUI.getDraftDataManager().getDraft(), mD, yNCD, proTeams);
        playersView = new PlayersView(playersController, initGUI);
    }
    public void initGUI(ArrayList<String> proTeams){
        playersView.initGUI();
    }
    @Override
    public PlayersView getView(){
        return playersView;
    }
    @Override
    public void reset(){
        playersView.reset();
    }

    @Override
    public void setVisible(boolean isVisible) {
        playersView.getScreen().setVisible(isVisible);
    }

    public void update() {
        playersView.update();
    }
}
