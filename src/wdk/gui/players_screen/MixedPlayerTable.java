/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import java.util.Collections;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class MixedPlayerTable {
    
    private TableView<Player> playerTable;
    private TableColumn<Player, String> firstNameColumn;
    private TableColumn<Player, String> lastNameColumn;
    private TableColumn<Player, String> proTeamColumn;
    private TableColumn<Player, String> positionsColumn;
    private TableColumn<Player, String> yearOfBirthColumn;
    private TableColumn<Player, Integer> runsWinsColumn;
    private TableColumn<Player, Integer> homeRunsSavesColumn;
    private TableColumn<Player, Integer> runsBattedInStrikeoutsColumn;
    private TableColumn<Player, Double> stolenBasesEarnedRunAverageColumn;
    private TableColumn<Player, Double> battingAverageWhipColumn;
    private TableColumn<Player, String> estimatedValueColumn;
    private TableColumn<Player, String> notesColumn;
    
    public MixedPlayerTable(List<Player> playerList){
        initTable();
        playerTable.setItems((ObservableList) playerList);
        Collections.sort(playerList);
    }
// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        playerTable                         = new TableView();
        firstNameColumn                     = new TableColumn("First");
        lastNameColumn                      = new TableColumn("Last");
        proTeamColumn                       = new TableColumn("Pro Team");
        positionsColumn                     = new TableColumn("Positions");
        yearOfBirthColumn                   = new TableColumn("Year Of Birth");
        runsWinsColumn                      = new TableColumn("R/W");
        homeRunsSavesColumn                 = new TableColumn("HR/SV");
        runsBattedInStrikeoutsColumn        = new TableColumn("RBI/K");
        stolenBasesEarnedRunAverageColumn   = new TableColumn("SB/ERA");
        battingAverageWhipColumn            = new TableColumn("BA/WHIP");
        estimatedValueColumn                = new TableColumn("Estimated Value");
        notesColumn                         = new TableColumn("Notes");
        notesColumn.setSortable(false);
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("proTeam"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("qualifiedPositions")); 
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("yearOfBirth"));
        
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
        
        
        
        
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("estimatedValue"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("notes"));
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setEditable(true);
        
        notesColumn.setOnEditCommit(new EventHandler<CellEditEvent<Player, String>>() {
        @Override
        public void handle(CellEditEvent<Player, String> t) {
            ((Player) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setNotes(t.getNewValue());
        }
    }
);
        playerTable.setEditable(true);
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

    public void setColumnLabels(PSColumnLabel pSCL) {
        if(pSCL == PSColumnLabel.ALL){
        runsWinsColumn.setText("R/W");
        homeRunsSavesColumn.setText("HR/SV");
        runsBattedInStrikeoutsColumn.setText("RBI/K");
        stolenBasesEarnedRunAverageColumn.setText("SB/ERA");
        battingAverageWhipColumn.setText("BA/WHIP");
        }
        else if(pSCL == PSColumnLabel.HITTER){
            runsWinsColumn.setText("R");
            homeRunsSavesColumn.setText("HR");
            runsBattedInStrikeoutsColumn.setText("RBI");
            stolenBasesEarnedRunAverageColumn.setText("SB");
            battingAverageWhipColumn.setText("BA");    
        }
        else if(pSCL == PSColumnLabel.PITCHER){
        runsWinsColumn.setText("W");
        homeRunsSavesColumn.setText("SV");
        runsBattedInStrikeoutsColumn.setText("K");
        stolenBasesEarnedRunAverageColumn.setText("ERA");
        battingAverageWhipColumn.setText("WHIP");
        }
    }
    
    
    
    
    
}
