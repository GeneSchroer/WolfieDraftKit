/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import java.util.Comparator;
import javafx.collections.ObservableList;
import wdk.data.Contract;
import wdk.data.Draft;
import wdk.data.Player;
import wdk.data.Position;
import wdk.data.Team;
import wdk.gui.WDK_GUI;

/**
 *
 * @author Work
 */
public class DraftController {
    private final Draft draft;
    
  public DraftController( Draft initDraft ){
      draft = initDraft;
    }
    public void handleDraftBestPlayerRequest(WDK_GUI gui) throws Exception{
        Draft draft = gui.getDataManager().getDraft();
        
        
        if(!draft.getTeams().isEmpty()){
        ObservableList<Player> playerList = draft.getAvailablePlayers();
        playerList.sort(new Comparator<Player>(){

            @Override
            public int compare(Player o1, Player o2) {
                Double i1 = o1.getEV();
                Double i2 = o2.getEV();
                return -(i1.compareTo(i2));
            }});
        
            boolean playerDrafted = false;
            int bestRank = 0;
            Player bestPlayer = playerList.get(bestRank);
        
            ObservableList<Team> teamList = draft.getTeams();
            Team team;
            
            
            for(int z = 0; z<playerList.size() && !playerDrafted; ++z){
                for(int i = 0; i < teamList.size() && !playerDrafted; ++i){
                    team = teamList.get(i);
                    if(!team.teamFull()&&!team.startingLineupFilled())
                    {
                        ObservableList<Position> pos = bestPlayer.getPositionList();
                        for(int j = 0; j < pos.size() && !playerDrafted; ++j){
                            if(!team.positionFilled(pos.get(j))){
                                bestPlayer.setContract(Contract.S2);
                                bestPlayer.setSalary(1);
                                team.addPlayer(bestPlayer, pos.get(j));
                                playerDrafted=true;
                            }
                        }
                    }
                }
                bestPlayer = playerList.get(++bestRank);
            }
            gui.updateGUI(false);
        }
    }

    void handleStartAutomatedDraftRequest(DraftView aThis) {
    }

    void handlepauseAutomatedDraftRequest(DraftView aThis) {
    }
    
}
