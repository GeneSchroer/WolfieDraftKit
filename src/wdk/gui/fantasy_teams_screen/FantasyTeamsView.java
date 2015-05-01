/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import wdk.gui.MenuView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import wdk.GeneralPropertyType;
import wdk.data.DraftDataManager;
import wdk.data.DraftType;
import wdk.data.Player;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;
import wdk.gui.WDK_GUI;
import wdk.util.MethodList;

/**
 *
 * @author Work
 */
public class FantasyTeamsView implements MenuView{
    private VBox mainWorkspacePane;
    
    
    
    /* Panes and controls for top pane */
    private VBox topWorkspacePane;
    private Label headingLabel;
    private Label draftNameLabel;
    private TextField draftNameTextField;
    
    private HBox teamButtonBox;
    private Button addTeamButton;
    private Button removeTeamButton;    
    private Button editTeamButton;
    private Label selectTeamLabel;
    private ComboBox selectTeamComboBox;

    
    
    
    /* Hitter and pitcher tables */
    private VBox teamTableBox;
    private FantasyTeamTable startingLineupTable;
    private FantasyTeamTable taxiSquadTable;
    private VBox taxiSquadBox;
    
    
    private FantasyTeamsController fantasyTeamsController;
    private HBox draftNameBox;
    private HBox teamBox;
    private  WDK_GUI gui;
    private DraftDataManager draftManager;
    private ArrayList<Player> currentStartingLineup;
    private ArrayList<Player> currentTaxiSquad;
    private boolean trigger;
    private VBox lineupBox;
    private VBox taxiBox;
    private Label lineupLabel;
    private Label taxiLabel;
    FantasyTeamsView(FantasyTeamsController initFantasyTeamsController, WDK_GUI initGUI) {
        fantasyTeamsController = initFantasyTeamsController;
        this.gui = initGUI;
        draftManager = initGUI.getDataManager();
        trigger = true;
    }
    public void updateTeamList(){
        
    }

    @Override
    public void initWorkspace() throws IOException {
        
        
        initTopWorkspace();
        initTables();
        initEventHandlers();
        mainWorkspacePane = new VBox();
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getChildren().add(lineupBox);
        mainWorkspacePane.getChildren().add(taxiBox);
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
        
        
        
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }
    private void initTables() {
        lineupBox = new VBox();
        lineupLabel = MethodList.initChildLabel(lineupBox, GeneralPropertyType.STARTING_LINEUP_LABEL, StyleSheet.CLASS_HEADING_LABEL);
        startingLineupTable = new FantasyTeamTable();
        lineupBox.getChildren().add(startingLineupTable.getTable());
        
        taxiBox = new VBox();
        taxiLabel = MethodList.initChildLabel(taxiBox, GeneralPropertyType.TAXI_SQUAD_LABEL, StyleSheet.CLASS_HEADING_LABEL);
        taxiSquadTable = new FantasyTeamTable();
        taxiBox.getChildren().add(taxiSquadTable.getTable());
    }
    
 
    

    private void initTopWorkspace() throws IOException {
        
        
        
        topWorkspacePane    = new VBox();
        
        headingLabel        = MethodList.initChildLabel(topWorkspacePane, GeneralPropertyType.FANTASY_TEAMS_LABEL, StyleSheet.CLASS_HEADING_LABEL); 
        draftNameBox = new HBox();
        draftNameLabel     = MethodList.initChildLabel(draftNameBox, GeneralPropertyType.TEAM_SELECT_LABEL, StyleSheet.CLASS_SUBHEADING_LABEL); 
        draftNameTextField   = MethodList.initChildTextField(draftNameBox, 25, null, true);
        topWorkspacePane.getChildren().add(draftNameBox);
        
        teamBox = new HBox();
        teamButtonBox    = new HBox();
        addTeamButton       = MethodList.initChildButton(teamButtonBox, GeneralPropertyType.ADD_ICON, GeneralPropertyType.ADD_TEAM_TOOLTIP, false);
        removeTeamButton    = MethodList.initChildButton(teamButtonBox, GeneralPropertyType.MINUS_ICON, GeneralPropertyType.REMOVE_TEAM_TOOLTIP, false);
        editTeamButton    = MethodList.initChildButton(teamButtonBox, GeneralPropertyType.EDIT_ICON, GeneralPropertyType.EDIT_TEAM_TOOLTIP, false);
        teamBox.getChildren().add(teamButtonBox);
        
       
        selectTeamLabel     = MethodList.initChildLabel(teamBox, GeneralPropertyType.TEAM_NAME_LABEL, StyleSheet.CLASS_SUBHEADING_LABEL); 
        selectTeamComboBox  = MethodList.initChildComboBox(teamBox);
         topWorkspacePane.getChildren().add(teamBox);
    }

    @Override
    public void initEventHandlers() {
        
        addTeamButton.setOnAction(e -> {
            fantasyTeamsController.handleAddTeamRequest(gui);
        });
        
        removeTeamButton.setOnAction(e -> {
            //if(!selectTeamComboBox.getSelectionModel().isEmpty())
         //       fantasyTeamsController.handleRemoveTeamRequest(gui, (String) selectTeamComboBox.getSelectionModel().getSelectedItem());
        });
        editTeamButton.setOnAction(e -> {
            //   fileController.handleExportDraftRequest(this);
        });
        selectTeamComboBox.setOnAction(e->{
            if(trigger)
                update();
        });
        draftNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            fantasyTeamsController.handleEditDraftNameRequest(this.gui, newValue);
        });
    }
    @Override
    public void initGUI() {
        try {
            initWorkspace();
        } catch (IOException ex) {
            Logger.getLogger(FantasyTeamsView.class.getName()).log(Level.SEVERE, null, ex);
        }
        initEventHandlers();
    }

    public void reset() {
        
    }
    
    public void update(){
        if(draftManager.getDraft().getTeams().isEmpty())
        {
            trigger = false;
            selectTeamComboBox.getItems().clear();
            trigger = true;
            // lineup and taxi tables cleared as well
            //
            
        }
        else{   //when you have teams in the draft
            updateTeamBox();
         updateTable((String)selectTeamComboBox.getSelectionModel().getSelectedItem());
        
        }
        
    }
    private void updateTeamBox(){
       String current = (String) selectTeamComboBox.getSelectionModel().getSelectedItem();
              
       selectTeamComboBox.getItems().clear();
        
        ArrayList<String> temp = new ArrayList();
        for(int i = 0 ; i< draftManager.getDraft().getTeams().size() ; ++i){
                temp.add(draftManager.getDraft().getTeams().get(i).getName());
            }
            trigger = false;
            MethodList.loadComboBox(selectTeamComboBox, temp);
            
        selectTeamComboBox.getSelectionModel().selectFirst();
            
        for(int i = 0 ; i< temp.size() ; ++i){
            if(temp.get(i).equals(current))
                selectTeamComboBox.getSelectionModel().select(i);
        }
//        selectTeamComboBox.getItems().sort(new Comparator<String>(){
//
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareTo(s2);
//            }
//        });
        trigger = true;
    }

    private void updateTable(String selectedTeam) {
        
        currentStartingLineup = buildFilteredTeam(selectedTeam, DraftType.STARTING);
        currentTaxiSquad = buildFilteredTeam(selectedTeam, DraftType.TAXI);
        startingLineupTable.setTable(draftManager.getDraft().getAvailablePlayers(), selectedTeam, DraftType.STARTING);
        taxiSquadTable.setTable(draftManager.getDraft().getAvailablePlayers(), selectedTeam, DraftType.TAXI);
        
    }
    
    
    private ArrayList<Player> buildFilteredTeam(String teamName, DraftType d){
        ArrayList<Player> aL = new ArrayList();
        ObservableList<Player> startList = draftManager.getDraft().getAvailablePlayers();
        for(int i = 0 ; i < startList.size() ; ++i){
            if(startList.get(i).getFantasyTeam().equals(teamName)
                    && startList.get(i).getDraftType() == d)
                aL.add(startList.get(i));
        }
        return aL;
        
        
    }
    
    
    
}
        
