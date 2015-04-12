/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import wdk.file.DraftFileManager;

/**
 *
 * @author Work
 */
public class DraftDataManager {
    
    private Draft draft;
    
    DraftDataView view;
    
    DraftFileManager draftFileManager;

    static String DEFAULT_LAST_NAME = "Ballrisian";
    static String DEFAULT_FIRST_NAME = "Rando";
    static int    DEFAULT_AGE = 2015-1937;
    static String DEFAULT_TEAM_NAME = " ";
    static String DEFAULT_TEAM_OWNER = " ";
    private Draft startingDraft;
    
    
    
    public DraftDataManager(DraftDataView initView, Draft startingDraft) {
        view = initView;
        this.startingDraft = startingDraft;
        draft = startingDraft;
    }
    
    public Draft getDraft(){
        return draft;
    }
    public DraftFileManager getFileManager(){
        return draftFileManager;
    }
    public void reset() {
        ArrayList<Player> temp = new ArrayList();
        temp.addAll(startingDraft.getAvailablePlayers().subList(0, startingDraft.getAvailablePlayers().size()-1));
        draft.getAvailablePlayers().clear();
        draft.getAvailablePlayers().addAll(temp);
        
        draft.getTeams().clear();
        view.reloadDraft(draft);
    }

    
}
