/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import wdk.table.PitcherTable;
import wdk.table.HitterTable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Work
 */
public class FantasyTeamsScreen implements MenuScreen{
    private VBox teamTablePane;
    private PitcherTable hitterTable;
    private HitterTable pitcherTable;
    private ComboBox selectTeamComboBox;
    private Button addTeamButton;
    private Button removeTeamButton;
    
    private Label teamNameLabel;
    private TextField teamNameTextField;
    private Label teamOwnerLabel;
    private TextField teamOwnerTextField;
    
    public FantasyTeamsScreen(Scene initPrimaryScene){
        
    }
    public void updateTeamList(){
        
    }

    @Override
    public void initScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initUIControls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
