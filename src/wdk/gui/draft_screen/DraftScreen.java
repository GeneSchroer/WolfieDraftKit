/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import wdk.data.DraftDataManager;
import wdk.gui.MenuScreen;

/**
 *
 * @author Work
 */
public class DraftScreen implements MenuScreen{
    DraftView draftView;
    DraftController draftController;

    public DraftScreen(DraftDataManager draftManager){
        draftController = new DraftController(draftManager.getDraft());
        draftView = new DraftView(draftController, draftManager);
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
    
}
