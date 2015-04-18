/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import  wdk.GeneralPropertyType;
import static wdk.WDK_StartUpConstants.*;
import wdk.data.Player;
import wdk.gui.MenuScreen;
import wdk.gui.MessageDialog;
import wdk.gui.StyleSheet;
import wdk.gui.YesNoCancelDialog;
import static wdk.gui.StyleSheet.*;
import wdk.util.MethodList;

/**
 *
 * @author Work
 */
public class PlayersScreen implements MenuScreen {

   
    /* Our list of available players, will be fed into the mixed table*/
    ObservableList<Player> players;
    
    
    /* The primary pane that is holding all of this */
    private VBox mainWorkspacePane;
    
    /* playersScreenPane is split into three broad segments, each holding
    blocks of UI controls */
    
    /* The top segment */
    private GridPane topWorkspacePane;
    private Label headingLabel;
    private Label playerSearchLabel;
    private TextField playerSearchTextField;
    private HBox addRemovePlayerBox;
    private Button addPlayerButton;
    private Button removePlayerButton;
    
    
    
   /* The middle segment */
   /* This holds all of our....very many radio buttons. Dear god that's alot */
    private HBox centerWorkspacePane;
    private ToggleGroup sortCriteriaToggleGroup;
    private RadioButton selectAllRadioButton;
    private RadioButton selectCatcherRadioButton;
    private RadioButton select1stBasemanRadioButton;
    private RadioButton selectCornerInfielderRadioButton;
    private RadioButton select3rdBasemanRadioButton;
    private RadioButton select2ndBasemanRadioButton;
    private RadioButton selectMiddleInfielderRadioButton;
    private RadioButton selectShortstopRadioButton;
    private RadioButton selectOutfielderRadioButton;
    private RadioButton selectUtilityRadioButton;
    private RadioButton selectPitcherRadioButton;
    
    
    /* The bottom segment */
    private MixedPlayerTable playersTable;
    
    
    private final Stage primaryStage;
    
    
    PlayersScreenController playersScreenController;
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    public PlayersScreen(Stage initPrimaryStage, ObservableList<Player> playerList){
       primaryStage = initPrimaryStage;
       players = playerList;
    }
    
    @Override
    public void initGUI(){
        initWorkspace();
        initEventHandlers();
    }
    
    @Override
    public void initWorkspace() {
        initTopWorkspace();
        initCenterWorkspace();
        initBottomWorkspace();
        mainWorkspacePane = new VBox();
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getChildren().add(centerWorkspacePane);
        mainWorkspacePane.getChildren().add(playersTable.getTable());
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }
    
     private void initTopWorkspace() {
        
        
        topWorkspacePane        = new GridPane();
        //topWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
        addRemovePlayerBox      = new HBox();
        headingLabel            = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.AVAILABLE_PLAYER_LABEL, StyleSheet.CLASS_HEADING_LABEL, 0, 0, 1, 1);
        addPlayerButton         = MethodList.initChildButton(addRemovePlayerBox, GeneralPropertyType.ADD_ICON, GeneralPropertyType.ADD_PLAYER_TOOLTIP, false);
        removePlayerButton      = MethodList.initChildButton(addRemovePlayerBox, GeneralPropertyType.MINUS_ICON, GeneralPropertyType.REMOVE_PLAYER_TOOLTIP, false);
        topWorkspacePane.add(addRemovePlayerBox, 0, 1, 1, 1);
        playerSearchLabel       = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.PLAYER_SEARCH_LABEL, CLASS_SUBHEADING_LABEL, 1, 1 , 1, 1);
        playerSearchTextField   = MethodList.initGridTextField(topWorkspacePane, 20, "", true, 2, 1, 1, 1);
     
     }
    
   
    private void initCenterWorkspace() {
    centerWorkspacePane = new HBox();
        
    sortCriteriaToggleGroup = new ToggleGroup();
    
    selectAllRadioButton                = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "All");
    selectCatcherRadioButton            = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "C"); ;
    select1stBasemanRadioButton         = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "1B");;
    selectCornerInfielderRadioButton    = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "CI");;
    select3rdBasemanRadioButton         = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "3B");
    select2ndBasemanRadioButton         = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "2B");
    selectMiddleInfielderRadioButton    = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "MI");
    selectShortstopRadioButton          = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "SS");
    selectOutfielderRadioButton         = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "OF");
    selectUtilityRadioButton            = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "U");
    selectPitcherRadioButton            = MethodList.initGroupRadioButton(sortCriteriaToggleGroup, "P");
    centerWorkspacePane.getChildren().add(selectAllRadioButton);
    centerWorkspacePane.getChildren().add(selectCatcherRadioButton);
    centerWorkspacePane.getChildren().add(select1stBasemanRadioButton);
    centerWorkspacePane.getChildren().add(selectCornerInfielderRadioButton);
    centerWorkspacePane.getChildren().add(select3rdBasemanRadioButton);
    centerWorkspacePane.getChildren().add(select2ndBasemanRadioButton);
    centerWorkspacePane.getChildren().add(selectMiddleInfielderRadioButton);
    centerWorkspacePane.getChildren().add(selectShortstopRadioButton);
    centerWorkspacePane.getChildren().add(selectOutfielderRadioButton);
    centerWorkspacePane.getChildren().add(selectUtilityRadioButton);
    centerWorkspacePane.getChildren().add(selectPitcherRadioButton);
    
    centerWorkspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
    
    }

   
     private void initBottomWorkspace() {

        playersTable = new MixedPlayerTable(players);
    }

    @Override
    public void initEventHandlers() {
        playersScreenController = new PlayersScreenController(primaryStage, messageDialog, yesNoCancelDialog);
        registerTextFieldController(playerSearchTextField);
        selectAllRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "");
            playersTable.statsSorted(false);
            playersTable.setColumnLabels(PSColumnLabel.ALL);
        });
        selectCatcherRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "C");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        select1stBasemanRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "1B");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectCornerInfielderRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "CI");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        select3rdBasemanRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "3B");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        select2ndBasemanRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "2B");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectMiddleInfielderRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "MI");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectShortstopRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "SS");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectOutfielderRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "OF");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectUtilityRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "U");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectPitcherRadioButton.setOnAction(e->{
            playersScreenController.handleSelectPlayerTypeRequest(this, "P");
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.PITCHER);
        });
        
    }
    public void setPlayersTable(ObservableList<Player> playerList){
        playersTable.setTable(playerList);
        

    }
        
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            playersScreenController.handleSearchForPlayerRequest(this, textField.getText());
        });
    }

    public void setTable(ObservableList<Player> playerList){
        playersTable.setTable(playerList);
    }
    
    public ArrayList<Player> getFilteredList(ObservableList<Player> players){
        ArrayList <Player> filteredList = new ArrayList();
        return filteredList;
    }
    
    
    public void reset() {
        sortCriteriaToggleGroup.selectToggle(selectAllRadioButton);
        playerSearchTextField.clear();
        playersTable.statsSorted(false);
        playersTable.setColumnLabels(PSColumnLabel.ALL);
        playersScreenController.handleSelectPlayerTypeRequest(this, "");
    }
    
     public List<Player> getAvailablePlayers() {
        return players;
    }
     
}
