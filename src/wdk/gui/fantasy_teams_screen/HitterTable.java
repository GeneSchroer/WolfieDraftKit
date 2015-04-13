/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javax.swing.text.TableView;
import wdk.data.Hitter;

/**
 *
 * @author Work
 */
public class HitterTable {
    private TableView hittleTable;
    private TableColumn positionColumn;
    private TableColumn nameColumn;
    private TableColumn teamColumn;
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
    
}
