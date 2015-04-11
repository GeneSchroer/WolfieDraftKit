/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.zzz;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wdk.gui.MenuScreen;

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
    public void initWorkspace() {
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

    @Override
    public Pane getScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
