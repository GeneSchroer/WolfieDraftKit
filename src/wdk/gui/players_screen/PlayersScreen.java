/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import javafx.stage.Stage;
import wdk.data.DraftDataManager;
import wdk.gui.MenuScreen;
import wdk.gui.MessageDialog;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author Work
 */
public class PlayersScreen implements MenuScreen{
    
    
    PlayersView playersView;
    PlayersController playersController;

    public PlayersScreen(Stage initPrimaryStage, DraftDataManager draftManager, MessageDialog mD, YesNoCancelDialog yNCD){
        playersController = new PlayersController(initPrimaryStage, draftManager.getDraft(), mD, yNCD);
        playersView = new PlayersView(playersController, draftManager);
    }
    public void initGUI(){
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
}
