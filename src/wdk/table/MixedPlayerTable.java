/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.table;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class MixedPlayerTable {
    private TableView playerTable;
    private TableColumn positionColumn;
    private TableColumn qp_rolesPositionsColumn;
    private TableColumn runs_winsColumn;
    private TableColumn homeRuns_strikeoutsColumn;
    private TableColumn runsBattedIn_SavesColumn;
    private TableColumn stolenBases_earnedRunAverageColumn;
    private TableColumn battingAverage_whipColumn;
    private TableColumn contractColumn;
    private TableColumn salaryColumn;
    private TableColumn totalPointsColumn;
    
    public MixedPlayerTable(ObservableList<? extends Player> players){
        
    }
    private void loadPlayer(){
        
    }
}
