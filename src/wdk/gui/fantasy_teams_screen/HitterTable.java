/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wdk.data.Hitter;

/**
 *
 * @author Work
 */
public class HitterTable {
    private TableView hitterTable;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn proTeamColumn;
    private TableColumn qualifiedPositionColumn;
    private TableColumn runsColumn;
    private TableColumn homeRunsColumn;
    private TableColumn runsBattedInColumn;
    private TableColumn stolenBasesColumn;
    private TableColumn battingAverageColumn;
    private TableColumn contractColumn;
    private TableColumn salaryColumn;
    
    public HitterTable(ObservableList<Hitter> hitters){
        
    }
    
    void initTable(){
        hitterTable = new TableView();
        firstNameColumn = new TableColumn();
        proTeamColumn = new TableColumn();
        qualifiedPositionColumn = new TableColumn();
        runsColumn = new TableColumn();
        homeRunsColumn = new TableColumn();
        runsBattedInColumn = new TableColumn();
        stolenBasesColumn = new TableColumn();
        battingAverageColumn = new TableColumn();
        contractColumn = new TableColumn();
        salaryColumn = new TableColumn();
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("teamName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("proTeam"));
        qualifiedPositionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("qualifiedPosition"));
        runsColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("runs"));
        homeRunsColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("homeRuns"));
        runsBattedInColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("runsBattedIn"));
        stolenBasesColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("stolenBases"));
        battingAverageColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("battingAverage"));
        contractColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("contract"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("salary"));
        
        hitterTable.getColumns().add(firstNameColumn);
        hitterTable.getColumns().add(lastNameColumn);
        hitterTable.getColumns().add(proTeamColumn);
        hitterTable.getColumns().add(qualifiedPositionColumn);
        hitterTable.getColumns().add(runsColumn);
        hitterTable.getColumns().add(homeRunsColumn);
        hitterTable.getColumns().add(runsBattedInColumn);
        hitterTable.getColumns().add(stolenBasesColumn);
        hitterTable.getColumns().add(battingAverageColumn);
        hitterTable.getColumns().add(contractColumn);
        hitterTable.getColumns().add(salaryColumn);
        
    }
    public TableView getTable(){
        
        return hitterTable;
    }
}
