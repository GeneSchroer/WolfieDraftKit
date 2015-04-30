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
    FantasyTeamsView(FantasyTeamsController initFantasyTeamsController, WDK_GUI initGUI) {
        fantasyTeamsController = initFantasyTeamsController;
        this.gui = initGUI;
        draftManager = initGUI.getDataManager();
        
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
        mainWorkspacePane.getChildren().add(startingLineupTable.getTable());
        mainWorkspacePane.getChildren().add(taxiSquadTable.getTable());
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
        
        
        
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }
    private void initTables() {
        startingLineupTable = new FantasyTeamTable();
        taxiSquadTable = new FantasyTeamTable();
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
            update();
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
        updateTeamBox();
        
    }
    private void updateTeamBox(){ 
        if(!selectTeamComboBox.getSelectionModel().isEmpty()){
            String current = (String) selectTeamComboBox.getSelectionModel().getSelectedItem();
        }
        
        
        selectTeamComboBox.getItems().clear();
        
        ArrayList<String> temp = new ArrayList();
        for(int i = 0 ; i< draftManager.getDraft().getTeams().size() ; ++i){
                temp.add(draftManager.getDraft().getTeams().get(i).getName());
            }
            MethodList.loadComboBox(selectTeamComboBox, temp);
        
//        selectTeamComboBox.getItems().sort(new Comparator<String>(){
//
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareTo(s2);
//            }
//        });
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
        
