/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wdk.data.Draft;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;
import wdk.data.Team;

/**
 *
 * @author Work
 */
public class DraftController {
    private final Draft draft;
    
  public DraftController( Draft initDraft ){
      draft = initDraft;
    }
    public void handleDraftBestPlayerRequest(DraftView ds){
     
        
    
       ObservableList<Hitter> hitterList= draft.getAvailableHitters();
       hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                if(o1.getRuns()<o2.getRuns())
                    return -1;
                else if (o1.getRuns() > o2.getRuns())
                    return 1;
                else
                    return 0;
            }
       });
       
       
       ObservableList<Pitcher> pitcherList = draft.getAvailablePitchers();
        
        
        
        
    }

    void handleStartAutomatedDraftRequest(DraftView aThis) {
    }

    void handlepauseAutomatedDraftRequest(DraftView aThis) {
    }
    
}
