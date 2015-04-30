/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import  wdk.GeneralPropertyType;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import wdk.data.DraftDataManager;
import wdk.data.Player;
import wdk.data.Position;
import wdk.gui.MenuView;
import wdk.gui.MessageDialog;
import wdk.gui.StyleSheet;
import wdk.gui.YesNoCancelDialog;
import static wdk.gui.StyleSheet.*;
import wdk.gui.WDK_GUI;
import wdk.util.MethodList;

/**
 *
 * @author Work
 */
public class PlayersView implements MenuView {
    
   private String sortPlayer;
   
   Position p;
    /* Our list of available players, will be fed into the mixed table*/
    ArrayList<Player> availablePlayers;
    
    
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
    
    
    PlayersController playersController;
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    private DraftDataManager draftManager;
    private WDK_GUI gui;
    
    
   
    PlayersView(PlayersController initPlayersController, WDK_GUI initGUI) {
        this.playersController = initPlayersController;
        this.gui = initGUI;
        draftManager = initGUI.getDataManager();
        availablePlayers = new ArrayList();
        availablePlayers.addAll( draftManager.getDraft().getAvailablePlayers().subList(0, draftManager.getDraft().getAvailablePlayers().size()));
        sortPlayer = "";
        p = null;
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

        playersTable = new MixedPlayerTable(availablePlayers);
    }

    @Override
    public void initEventHandlers() {
        
        addPlayerButton.setOnAction(e->{
            playersController.handleAddPlayerRequest(this.gui);
        });
        removePlayerButton.setOnAction(e->{
            playersController.handleRemovePlayerRequest(this.gui, playersTable.getTable().getSelectionModel().getSelectedItem());
        });
        
        registerTextFieldController(playerSearchTextField);
        selectAllRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, null);
            playersTable.statsSorted(false);
            playersTable.setColumnLabels(PSColumnLabel.ALL);
        });
        selectCatcherRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.C);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        select1stBasemanRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.B1);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectCornerInfielderRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.CI);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        select3rdBasemanRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.B3);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        select2ndBasemanRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.B2);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectMiddleInfielderRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.MI);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectShortstopRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.SS);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectOutfielderRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.OF);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectUtilityRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.U);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.HITTER);
        });
        selectPitcherRadioButton.setOnAction(e->{
            playersController.handleSelectPlayerTypeRequest(this, Position.P);
            playersTable.statsSorted(true);
            playersTable.setColumnLabels(PSColumnLabel.PITCHER);
        });
        playersTable.getTable().setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
            //Open up the lecture editor
            Player player = playersTable.getTable().getSelectionModel().getSelectedItem();
            playersController.handleEditPlayerRequest(this.gui, player);
            }
        });
    }
    public void setPlayersTable(ObservableList<Player> playerList){
        playersTable.setTable(playerList);
        

    }
        
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            playersController.handleSearchForPlayerRequest(this, textField.getText());
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
        playersController.handleSelectPlayerTypeRequest(this, null);
    }
     
    public ArrayList<Player> getAvailablePlayers() {
        return availablePlayers;
    }
     public DraftDataManager getDraftManager(){
         return draftManager;
     }

    public void update() {
        availablePlayers.clear();
       // ArrayList<Player> temp = buildFilteredList(draftManager.getDraft().getAvailablePlayers().subList(0, draftManager.getDraft().getAvailablePlayers().size()));
        //availablePlayers.addAll(temp);
       // playersTable.setTable(availablePlayers);
        playersTable.setTable(draftManager.getDraft().getAvailablePlayers(), sortPlayer, p);
    }
    
    
    private ArrayList<Player> buildFilteredList(List<Player> playerList){
        ArrayList<Player> filteredList = new ArrayList();
        String sp = sortPlayer;
        for(int i = 0; i < playerList.size(); ++i){
            String ln = playerList.get(i).getLastName();
            String fn = playerList.get(i).getFirstName();
            
            if(p == null){
                if( (ln.toLowerCase().startsWith(sp.toLowerCase())
                    ||fn.toLowerCase().startsWith(sp.toLowerCase()))
                        && playerList.get(i).getFantasyTeam().equals(FREE_AGENT)){
                    filteredList.add(playerList.get(i));
                }
            }
            else
                if( (ln.toLowerCase().startsWith(sp.toLowerCase())
                        ||fn.toLowerCase().startsWith(sp.toLowerCase()))
                        && playerList.get(i).getPositionList().contains(p)
                            && playerList.get(i).getFantasyTeam().equals(FREE_AGENT)){
                        filteredList.add(playerList.get(i));
                }
        }
        return filteredList;
    }
    public void setSortPosition(Position sp){
        p = sp;
    }
    public void setSortPlayer(String p){
        sortPlayer = p;
    }
    
}
