/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import java.util.Comparator;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
    boolean running = false;
    boolean trick = true;
  public DraftController( Draft initDraft, WDK_GUI gui ){
      draft = initDraft;
      
      Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
              //  progressLock.lock();
//                ObservableList<Team> teamList = draft.getTeams();
//                int totalNeeded = 0;
//                while(true){
////                    for(int z= 0; z<teamList.size(); ++z)
//                        totalNeeded += teamList.get(z).getPlayersNeeded() + teamList.get(z).getTaxi();
//                    if (totalNeeded != 0 ){
//                                    handleDraftBestPlayerRequest(gui);
//
//                      //Thread.sleep(1000);
//                    }
//                }
                        // WE'RE SLEEPING FIRST JUST TO LET THE FIRST MESSAGE SHOW
                        while(trick == true){
//                            for(int i = 0; i<  ;i++){
//                        // UPDATE ANY PROGRESS DISPLAY
                        Platform.runLater(new Runnable() {
                           
//
                            public void run() {
                                try {
                                    if(running)
                                        handleDraftBestPlayerRequest(gui);
                                } catch (Exception ex) {
                                    Logger.getLogger(DraftController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
//                        System.out.println("EXPORTING " + pages[pageIndex]);
                        // SLEEP EACH FRAME
//                            try {
                                Thread.sleep(1500);
                        }
                           // } catch (InterruptedException ie) {
                          //      ie.printStackTrace();
                          //  }
//                   };
            
     //           }
        
                return null;
            }
        
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
      
      
      
    }
    public void handleDraftBestPlayerRequest(WDK_GUI gui) throws Exception{
        Draft draft = gui.getDataManager().getDraft();
        if(!draft.getTeams().isEmpty()){
            
            int taxiNum=0;
            int startNum = 0;
            
            ObservableList<Team> teamList = draft.getTeams();
            
            for(int i = 0; i< teamList.size() ;++i){
                startNum += teamList.get(i).getPlayersNeeded();
                taxiNum += teamList.get(i).getTaxi();
            }
                
            
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
        
            
            if(startNum != 0)
                for(int z = 0; z<playerList.size() && !playerDrafted; ++z){
                    for(int i = 0; i < teamList.size() && !playerDrafted; ++i){
                        Team team = teamList.get(i);
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
            else if(taxiNum != 0)
                for (int i = 0; i < teamList.size() && !playerDrafted; ++i) {
                    Team team = teamList.get(i);
                    if (!team.teamFull()) {
                        team.addPlayer(bestPlayer, (Position) bestPlayer.getPositionList().get(0));
                        playerDrafted = true;
                }
            }
            gui.updateGUI(false);
        }
                

    }


    void handleStartAutomatedDraftRequest(WDK_GUI gui) {
        
                        running = true;

        
//        
//        Task<Void> task = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//              //  progressLock.lock();
////                ObservableList<Team> teamList = draft.getTeams();
////                int totalNeeded = 0;
////                while(true){
//////                    for(int z= 0; z<teamList.size(); ++z)
////                        totalNeeded += teamList.get(z).getPlayersNeeded() + teamList.get(z).getTaxi();
////                    if (totalNeeded != 0 ){
////                                    handleDraftBestPlayerRequest(gui);
////
////                      //Thread.sleep(1000);
////                    }
////                }
//                        // WE'RE SLEEPING FIRST JUST TO LET THE FIRST MESSAGE SHOW
//                running = true;
//                        for(int i = 0; i< gui.getDraftDataManager().getDraft().getAllPlayers().size() ;i++){
////                            for(int i = 0; i<  ;i++){
////                        // UPDATE ANY PROGRESS DISPLAY
//                        Platform.runLater(new Runnable() {
//                           
////
//                            public void run() {
//                                try {
//                                    if(running)
//                                        handleDraftBestPlayerRequest(gui);
//                                } catch (Exception ex) {
//                                    Logger.getLogger(DraftController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                        });
////                        System.out.println("EXPORTING " + pages[pageIndex]);
//                        // SLEEP EACH FRAME
////                            try {
//                                Thread.sleep(1500);
//                        }
//                           // } catch (InterruptedException ie) {
//                          //      ie.printStackTrace();
//                          //  }
////                   };
//            
//     //           }
//        
//                return null;
//            }
//        
//        };
//
//        Thread thread = new Thread(task);
//        thread.setDaemon(true);
//        thread.start();
    }
    public void handlepauseAutomatedDraftRequest(DraftView aThis) {
        running = false;


    }
    
}
