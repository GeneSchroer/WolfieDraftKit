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
import wdk.data.Pitcher;

/**
 *
 * @author Work
 */
public class PitcherTable {
    private TableView pitcherTable;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn positionColumn;
    private TableColumn proTeamColumn;
    private TableColumn qualifiedPositionColumn;
    private TableColumn winsColumn;
    private TableColumn strikeoutsColumn;
    private TableColumn savesColumn;
    private TableColumn earnedRunAverageColumn;
    private TableColumn whipColumn;
    private TableColumn contractColumn;
    private TableColumn salaryColumn;
    
    void initTable(){
        pitcherTable = new TableView();
        firstNameColumn = new TableColumn();
        lastNameColumn = new TableColumn();
        proTeamColumn = new TableColumn();
        qualifiedPositionColumn = new TableColumn();
        winsColumn = new TableColumn();
        strikeoutsColumn = new TableColumn();
        strikeoutsColumn = new TableColumn();
        savesColumn = new TableColumn();
        earnedRunAverageColumn = new TableColumn();
        whipColumn = new TableColumn();
        contractColumn = new TableColumn();
        salaryColumn = new TableColumn();
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("teamName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("proTeam"));
        qualifiedPositionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("qualifiedPosition"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("wins"));
        strikeoutsColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("strikeouts"));
        savesColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("saves"));
        earnedRunAverageColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("earnedRunAverage"));
        whipColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("whip"));
        contractColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("contract"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Integer, String>("salary"));
        
        pitcherTable.getColumns().add(firstNameColumn);
        pitcherTable.getColumns().add(lastNameColumn);
        pitcherTable.getColumns().add(proTeamColumn);
        pitcherTable.getColumns().add(qualifiedPositionColumn);
        pitcherTable.getColumns().add(winsColumn);
        pitcherTable.getColumns().add(strikeoutsColumn);
        pitcherTable.getColumns().add(savesColumn);
        pitcherTable.getColumns().add(earnedRunAverageColumn);
        pitcherTable.getColumns().add(whipColumn);
        pitcherTable.getColumns().add(contractColumn);
        pitcherTable.getColumns().add(salaryColumn);
        
    }
    public TableView getTable(){
        
        return pitcherTable;
    }
}


