/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import wdk.data.DraftDataManager;
import wdk.gui.MenuScreen;
import wdk.gui.WDK_GUI;

/**
 *
 * @author Work
 */
public class DraftScreen implements MenuScreen{
    DraftView draftView;
    DraftController draftController;

    public DraftScreen(WDK_GUI gui){
        draftController = new DraftController(gui.getDataManager().getDraft());
        draftView = new DraftView(draftController, gui);
    }
    public void initGUI(){
        draftView.initGUI();
    }
    @Override
    public DraftView getView(){
        return draftView;
    }
    @Override
    public void reset(){
        draftView.reset();
    }

    @Override
    public void setVisible(boolean isVisible) {
        draftView.getScreen().setVisible(isVisible);
    }

    public void update() {
        draftView.update();
    }
    
}
