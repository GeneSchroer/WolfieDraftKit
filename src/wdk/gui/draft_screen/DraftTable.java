/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

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
class DraftTable {
    

/**
 *
 * @author Work
 */
    
    private TableView<Player> playerTable;
    private TableColumn<Player, String> firstNameColumn;
    private TableColumn<Player, String> lastNameColumn;
    private TableColumn<Player, String> teamColumn;
    private TableColumn pickNumberColumn;
    private TableColumn contractColumn;
    private TableColumn salaryColumn;
    
    public DraftTable(){
        initTable();
        //Collections.sort(playerList);
    }
// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        playerTable                 = new TableView();
        pickNumberColumn            = new TableColumn("Pick #");
        firstNameColumn             = new TableColumn("First");
        lastNameColumn              = new TableColumn("Last");
        teamColumn                  = new TableColumn("Team");
        contractColumn              = new TableColumn("Contract");
        salaryColumn                = new TableColumn("Salary");
        
        
        pickNumberColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("pick"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("lastName"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("fantasyTeam")); 
        contractColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("contract")); 
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("salary")); 
        
        
        
        playerTable.getColumns().add(pickNumberColumn);
        playerTable.getColumns().add(firstNameColumn);
        playerTable.getColumns().add(lastNameColumn);
        playerTable.getColumns().add(teamColumn);
        playerTable.getColumns().add(contractColumn);
        playerTable.getColumns().add(salaryColumn);
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