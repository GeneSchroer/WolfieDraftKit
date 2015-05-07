/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_standings_screen;

import wdk.data.DraftDataManager;
import wdk.gui.MenuScreen;
import wdk.gui.MenuView;

/**
 *
 * @author Work
 */
public class FantasyStandingsScreen implements MenuScreen {
    
    FantasyStandingsView fantasyStandingsView;
    private  FantasyStandingsController fantasyStandingsController;
    
    
    public FantasyStandingsScreen(DraftDataManager draftManager){
        fantasyStandingsController = new FantasyStandingsController(draftManager.getDraft());
        fantasyStandingsView = new FantasyStandingsView(fantasyStandingsController, draftManager);
    }
    
    
    @Override
    public void setVisible(boolean b) {
        fantasyStandingsView.getScreen().setVisible(b);
    }

    @Override
    public MenuView getView() {
        return fantasyStandingsView;
    }

    @Override
    public void reset() {
        fantasyStandingsView.reset();
    }

    public void initGUI() {
        fantasyStandingsView.initGUI();
    }

    public void update() {
        fantasyStandingsView.update();
    }
    
}
