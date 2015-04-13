/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Pair;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class MixedPlayerTable {
    
    private TableView<Player> playerTable;
    private TableColumn firstNameColumn;
    private TableColumn lastNameColumn;
    private TableColumn proTeamColumn;
    private TableColumn positionsColumn;
    private TableColumn yearOfBirthColumn;
    private TableColumn<Player, Integer> runsWinsColumn;
    private TableColumn homeRunsSavesColumn;
    private TableColumn runsBattedInStrikeoutsColumn;
    private TableColumn stolenBasesEarnedRunAverageColumn;
    private TableColumn battingAverageWhipColumn;
    private TableColumn estimatedValueColumn;
    private TableColumn notesColumn;
    private TableColumn winsColumn;
    
    public MixedPlayerTable(List<Player> playerList){
        initTable();
        playerTable.setItems((ObservableList) playerList);
    }
// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        playerTable                         = new TableView();
        firstNameColumn                     = new TableColumn("First");
        lastNameColumn                      = new TableColumn("Last");
        proTeamColumn                       = new TableColumn("Pro Team");
        positionsColumn                     = new TableColumn("Positions");
        yearOfBirthColumn                   = new TableColumn("Year Of Birth");
        runsWinsColumn                          = new TableColumn("R/W");
        winsColumn                          = new TableColumn();
        
        homeRunsSavesColumn                = new TableColumn("HR/SV");
        runsBattedInStrikeoutsColumn       = new TableColumn("RBI/K");
        stolenBasesEarnedRunAverageColumn  = new TableColumn("SB/ERA");
        battingAverageWhipColumn           = new TableColumn("BA/WHIP");
        estimatedValueColumn                = new TableColumn("Estimated Value");
        notesColumn                         = new TableColumn("Notes");
        notesColumn.setSortable(false);
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<String, String>("proTeam"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("qualifiedPositions")); 
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<String, String>("yearOfBirth"));
        
        runsWinsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Player, Integer> param) {
                Player p = param.getValue();
                if(p instanceof Hitter){
                    Hitter h = (Hitter)p;
                    return h.runsProperty().asObject();
                }
                else if(p instanceof Pitcher){
                    Pitcher pit = (Pitcher)p;
                    return pit.winsProperty().asObject();
                }
                return null;
            }
        });
        
        homeRunsSavesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Player, Integer> param) {
                Player p = param.getValue();
                if(p instanceof Hitter){
                    Hitter h = (Hitter)p;
                    return h.homeRunsProperty().asObject();
                }
                else if(p instanceof Pitcher){
                    Pitcher pit = (Pitcher)p;
                    return pit.savesProperty().asObject();
                }
                return null;
            }
        });
        
        runsBattedInStrikeoutsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player,Integer>,ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Player, Integer> param) {
                Player p = param.getValue();
                if(p instanceof Hitter){
                    Hitter h = (Hitter)p;
                    return h.runsBattedInProperty().asObject();
                }
                else if(p instanceof Pitcher){
                    Pitcher pit = (Pitcher)p;
                    return pit.strikeoutsProperty().asObject();
                }
                return null;
            }
        });
        
        stolenBasesEarnedRunAverageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player,Double>,ObservableValue<Double>>(){
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Player, Double> param) {
                Player p = param.getValue();
                if(p instanceof Hitter){
                    Hitter h = (Hitter)p;
                    Double sb = (double) h.getStolenBases();
                    DoubleProperty d = new SimpleDoubleProperty(h.getStolenBases());
                    return d.asObject();
                }
                else if(p instanceof Pitcher){
                    Pitcher pit = (Pitcher)p;
                    return pit.earnedRunAverageProperty().asObject();
                }
                return null;
            }
        });
        
        battingAverageWhipColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player,Double>,ObservableValue<Double>>(){
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Player, Double> param) {
                Player p = param.getValue();
                if(p instanceof Hitter){
                    Hitter h = (Hitter)p;
                    return h.battingAverageProperty().asObject();
                }
                else if(p instanceof Pitcher){
                    Pitcher pit = (Pitcher)p;
                    return pit.whipProperty().asObject();
                }
                return null;
            }
        });
        
        
        
        
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<String, String>("estimatedValue"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<String, String>("notes"));
        
        notesColumn.setEditable(true);
        
        playerTable.getColumns().add(firstNameColumn);
        playerTable.getColumns().add(lastNameColumn);
        playerTable.getColumns().add(proTeamColumn);
        playerTable.getColumns().add(positionsColumn);
        playerTable.getColumns().add(yearOfBirthColumn);
        playerTable.getColumns().add(runsWinsColumn);
        playerTable.getColumns().add(homeRunsSavesColumn);
        playerTable.getColumns().add(runsBattedInStrikeoutsColumn);
        playerTable.getColumns().add(stolenBasesEarnedRunAverageColumn);
        playerTable.getColumns().add(battingAverageWhipColumn);
        playerTable.getColumns().add(estimatedValueColumn);
        playerTable.getColumns().add(notesColumn);
        winsColumn.setVisible(false);
        playerTable.getColumns().add(winsColumn);
        
        
        

    }
    
    public void setTable(ObservableList<Player> players){
        playerTable.setItems(players);
    }
    public TableView getTable(){
        return playerTable;
    }
    public void statsSorted(boolean isSorted){
        
            runsWinsColumn.setSortable(isSorted);
            homeRunsSavesColumn.setSortable(isSorted);
            runsBattedInStrikeoutsColumn.setSortable(isSorted);
            stolenBasesEarnedRunAverageColumn.setSortable(isSorted);
            battingAverageWhipColumn.setSortable(isSorted);
        
    }
}
