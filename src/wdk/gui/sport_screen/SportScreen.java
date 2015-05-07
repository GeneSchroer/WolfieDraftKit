/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.sport_screen;

import java.util.ArrayList;
import javafx.stage.Stage;
import wdk.data.DraftDataManager;
import wdk.gui.MenuScreen;
import wdk.gui.MessageDialog;
import wdk.gui.YesNoCancelDialog;


/**
 *
 * @author Work
 */
public class SportScreen implements MenuScreen{
    SportView sportView;
    SportController sportController;
    ArrayList<String> proTeams;
   public SportScreen(DraftDataManager draftManager, ArrayList<String> teamsList){
       proTeams = teamsList; 
       sportController = new SportController(draftManager.getDraft());
        sportView = new SportView(sportController, draftManager, proTeams);
    }
    public void initGUI(){
        sportView.initGUI();
    }
    @Override
    public SportView getView(){
        return sportView;
    }
    @Override
    public void reset(){
        sportView.reset();
    }
 
   @Override
    public void setVisible(boolean isVisible) {
        sportView.getScreen().setVisible(isVisible);
    }

    public void update() {
    }
}
