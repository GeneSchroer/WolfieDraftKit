/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.table;

import java.net.URL;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class MixedPlayerTable {
    private TableView playerTable;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn proTeamColumn;
    private TableColumn positionsColumn;
    private TableColumn yearOfBirthColumn;
    private TableColumn runs_WinsColumn;
    private TableColumn homeRuns_SavesColumn;
    private TableColumn runsBattedIn_StrikeoutsColumn;
    private TableColumn stolenBases_EarnedRunAverageColumn;
    private TableColumn battingAverage_WhipColumn;
    private TableColumn estimatedValueColumn;
    private TableColumn notesColumn;
    
    public MixedPlayerTable(ObservableList<? extends Player> players){
        initTable();
        
        
        
        fillTable(players);
    }
// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        playerTable                         = new TableView();
        firstNameColumn                     = new TableColumn("First");
        lastNameColumn                      = new TableColumn("Last");
        proTeamColumn                       = new TableColumn("Pro Team");
        positionsColumn                     = new TableColumn("Positions");
        yearOfBirthColumn                   = new TableColumn("Year Of Birth");
        runs_WinsColumn                     = new TableColumn("R/W");
        homeRuns_SavesColumn                = new TableColumn("HR/SV");
        runsBattedIn_StrikeoutsColumn       = new TableColumn("RBI/K");
        stolenBases_EarnedRunAverageColumn  = new TableColumn("SB/ERA");
        battingAverage_WhipColumn           = new TableColumn("BA/WHIP");
        estimatedValueColumn                = new TableColumn("Estimated Value");
        notesColumn                         = new TableColumn("Notes");
        
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("proTeam"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("positions")); //Come Back To this one
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<String, String>("yearOfBirth"));
        runs_WinsColumn.setCellValueFactory(new PropertyValueFactory<String, String>(""));
        homeRuns_SavesColumn.setCellValueFactory(new PropertyValueFactory<String, String>(""));
        runsBattedIn_StrikeoutsColumn.setCellValueFactory(new PropertyValueFactory<String, String>(""));
        stolenBases_EarnedRunAverageColumn.setCellValueFactory(new PropertyValueFactory<String, String>(""));
        battingAverage_WhipColumn.setCellValueFactory(new PropertyValueFactory<String, String>(""));
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<String, String>("estimatedValue"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<String, String>("notes"));
        
        playerTable.getColumns().add(firstNameColumn);
        playerTable.getColumns().add(lastNameColumn);
        playerTable.getColumns().add(proTeamColumn);
        playerTable.getColumns().add(positionsColumn);
        playerTable.getColumns().add(yearOfBirthColumn);
        playerTable.getColumns().add(runs_WinsColumn);
        playerTable.getColumns().add(homeRuns_SavesColumn);
        playerTable.getColumns().add(runsBattedIn_StrikeoutsColumn);
        playerTable.getColumns().add(stolenBases_EarnedRunAverageColumn);
        playerTable.getColumns().add(battingAverage_WhipColumn);
        playerTable.getColumns().add(estimatedValueColumn);
        playerTable.getColumns().add(notesColumn);
        

    }
    
    private void fillTable(ObservableList<? extends Player> players){
        playerTable.setItems(players);
    }
    
}
