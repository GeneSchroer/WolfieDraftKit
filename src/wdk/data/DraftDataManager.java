/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

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
    
    
    
    public DraftDataManager(DraftDataView initView) {
        view = initView;
        draft = new Draft();
    }
    
    public Draft getDraft(){
        return draft;
    }
    public DraftFileManager getFileManager(){
        return draftFileManager;
    }
    public void reset() {
       view.reloadDraft(draft);
    }
}
