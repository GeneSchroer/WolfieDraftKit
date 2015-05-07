/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import java.util.Comparator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import wdk.data.DraftType;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class FantasyTeamTable {
    
    private TableView<Player> playerTable;
    private TableColumn<Player, String> positionColumn;
    private TableColumn<Player, String> firstNameColumn;
    private TableColumn<Player, String> lastNameColumn;
    private TableColumn<Player, String> proTeamColumn;
    private TableColumn<Player, String> positionsColumn;
    private TableColumn<Player, Integer> runsWinsColumn;
    private TableColumn<Player, Integer> homeRunsSavesColumn;
    private TableColumn<Player, Integer> runsBattedInStrikeoutsColumn;
    private TableColumn<Player, Double> stolenBasesEarnedRunAverageColumn;
    private TableColumn<Player, Double> battingAverageWhipColumn;
    private TableColumn<Player, String> estimatedValueColumn;
    private TableColumn<Player, String> contractColumn;
    private TableColumn<Player, String> salaryColumn;
    
    public FantasyTeamTable(){
        initTable();
    }


// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        playerTable                         = new TableView();
        positionColumn                      = new TableColumn("Position");
        firstNameColumn                     = new TableColumn("First");
        lastNameColumn                      = new TableColumn("Last");
        proTeamColumn                       = new TableColumn("Pro Team");
        positionsColumn                     = new TableColumn("Positions");
        runsWinsColumn                      = new TableColumn("R/W");
        homeRunsSavesColumn                 = new TableColumn("HR/SV");
        runsBattedInStrikeoutsColumn        = new TableColumn("RBI/K");
        stolenBasesEarnedRunAverageColumn   = new TableColumn("SB/ERA");
        battingAverageWhipColumn            = new TableColumn("BA/WHIP");
        estimatedValueColumn                = new TableColumn("Estimated Value");
        contractColumn                      = new TableColumn("Contract");
        salaryColumn                        = new TableColumn("Salary");
        
        
        positionColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("teamPosition"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("proTeam"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("qualifiedPositions")); 
        
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
        contractColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("contract"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("salary"));
        
        playerTable.setEditable(true);
        playerTable.getColumns().add(positionColumn);
        playerTable.getColumns().add(firstNameColumn);
        playerTable.getColumns().add(lastNameColumn);
        playerTable.getColumns().add(proTeamColumn);
        playerTable.getColumns().add(positionsColumn);
        playerTable.getColumns().add(runsWinsColumn);
        playerTable.getColumns().add(homeRunsSavesColumn);
        playerTable.getColumns().add(runsBattedInStrikeoutsColumn);
        playerTable.getColumns().add(stolenBasesEarnedRunAverageColumn);
        playerTable.getColumns().add(battingAverageWhipColumn);
        playerTable.getColumns().add(estimatedValueColumn);
        playerTable.getColumns().add(contractColumn);
        playerTable.getColumns().add(salaryColumn);
    }
    
    public void setTable(ObservableList<Player> playerList, String teamName, DraftType dT){
        
        
        playerTable.getItems().clear();
        
        for(int i = 0; i < playerList.size() ; ++i){
            if(playerList.get(i).getFantasyTeam().equals(teamName)&&playerList.get(i).getDraftType().equals(dT))
                    playerTable.getItems().add(playerList.get(i));
        }
        playerTable.getItems().sort(new Comparator<Player>(){
                @Override
                public int compare(Player p1, Player p2){
                    if(p1.getTeamPosition().ordinal() > p2.getTeamPosition().ordinal())
                        return 1;
                    if(p1.getTeamPosition().ordinal() < p2.getTeamPosition().ordinal())
                        return -1;
                    
                    return 0;
                }
        });
    }
    public TableView<Player> getTable(){
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
