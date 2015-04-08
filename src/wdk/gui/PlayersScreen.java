/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import wdk.table.MixedPlayerTable;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class PlayersScreen implements MenuScreen {
    private VBox playersScreenPane;
    private Label searchForPlayerLabel;
    private TextField searchForPlayerTextField;
    private Label movePlayerToTeamLabel;
    private ComboBox movePlayerToTeamComboBox;
    private Button addPlayer;
    private Button removePlayerButton;
    
    private HBox selectPlayerTypesCriteriaPane;
    private ToggleGroup sortCriteriaToggleGroup;
    private RadioButton selectAllRadioButton;
    private RadioButton selectCatcherRadioButton;
    private RadioButton select1stBasemanRadioButton;
    private RadioButton selectCornerInfielderRadioButton;
    private RadioButton select3rdBasemanRadioButton;
    private RadioButton select2ndBasemanRadioButton;
    private RadioButton selectMiddleInfielderRadioButton;
    private RadioButton selectShortstopRadioButton;
    private RadioButton selectOutfieldRadioButton;
    private RadioButton selectUtilityRadioButton;
    private MixedPlayerTable playersTable;
    
    public PlayersScreen(Stage primaryStage){
        initScreen();
        initTable();
        initUIControls();
    }
    public void setPlayersTable(ObservableList<? extends Player> player){
        
    }
    private void registerTextFieldController(TextField textField){
        
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
