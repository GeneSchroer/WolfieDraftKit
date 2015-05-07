/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.sport_screen;

import java.util.Comparator;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import wdk.data.Player;
import wdk.data.PlayerComparator;

/**
 *
 * @author Work
 */
public class ProTeamTable {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Work
 */
    
    private TableView<Player> playerTable;
    private TableColumn<Player, String> firstNameColumn;
    private TableColumn<Player, String> lastNameColumn;
    private TableColumn<Player, String> positionsColumn;
    
    public ProTeamTable(){
        initTable();
        //Collections.sort(playerList);
    }
// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        playerTable                         = new TableView();
        firstNameColumn                     = new TableColumn("First");
        lastNameColumn                      = new TableColumn("Last");
        positionsColumn                     = new TableColumn("Positions");
        
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        positionsColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("qualifiedPositions")); 
        playerTable.getColumns().add(firstNameColumn);
        playerTable.getColumns().add(lastNameColumn);
        playerTable.getColumns().add(positionsColumn);
    }
    
    public void setTable(ObservableList<Player> playerList, String teamName){
        playerTable.getItems().clear();
        for(int i = 0; i < playerList.size(); ++i){
                if( playerList.get(i).getProTeam().equals(teamName)){
                    playerTable.getItems().add(playerList.get(i));
                }
        }
        playerTable.getItems().sort(new PlayerComparator());
    }
    
    public TableView<Player> getTable(){
        return playerTable;
    }
    }