/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import wdk.data.Pitcher;

/**
 *
 * @author Work
 */
public class PitcherTable {
    private TableView pitcherTable;
    private TableColumn positionColumn;
    private TableColumn nameColumn;
    private TableColumn teamColumn;
    private TableColumn rolesColumn;
    private TableColumn winsColumn;
    private TableColumn strikeoutsColumn;
    private TableColumn savesColumn;
    private TableColumn earnedRunAverageColumn;
    private TableColumn whipColumn;
    private TableColumn contractColumn;
    private TableColumn salaryColumn;
    
    public PitcherTable(ObservableList<Pitcher> pitcher){
        
    }
}
