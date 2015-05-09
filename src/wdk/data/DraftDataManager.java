/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import wdk.file.DraftFileManager;
import wdk.file.JsonDraftFileManager;
import static wdk.sports.baseball.WDK_BaseballStartUpConstants.JSON_FILE_PATH_HITTERS;
import static wdk.sports.baseball.WDK_BaseballStartUpConstants.JSON_FILE_PATH_PITCHERS;

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
    
    private final ArrayList<Player> startingPlayers;
    
    Player p;
    private ObservableList<Player> Player;
    public DraftDataManager(DraftDataView initView, Draft startingDraft) {
        
        startingPlayers = new ArrayList(startingDraft.getAllPlayers().subList(0, startingDraft.getAllPlayers().size()));
        
        
        
        view = initView;
        this.startingDraft = startingDraft;
        draft = new Draft();
        draft.setPlayers(startingDraft.getAllPlayers());
    }
    
    public Draft getDraft(){
        return draft;
    }
    public DraftFileManager getFileManager(){
        return draftFileManager;
    }
    public void reset() throws IOException {
        
        
        draft.setDraftName("");
        draft.getAllPlayers().clear();
        draft.getTeams().clear();
        
        
        ArrayList<String> filePathList = new ArrayList();
                filePathList.add(JSON_FILE_PATH_HITTERS);
                filePathList.add(JSON_FILE_PATH_PITCHERS);
        JsonDraftFileManager temp = JsonDraftFileManager.getPropertiesManager();
        startingDraft = temp.loadStartingDraft(filePathList);
        
        draft.setPlayers(startingDraft.getAllPlayers());

        
        view.reloadDraft(draft);
    }

    
}
