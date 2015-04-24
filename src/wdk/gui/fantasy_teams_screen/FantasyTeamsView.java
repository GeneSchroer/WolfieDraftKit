/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import wdk.gui.MenuView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wdk.GeneralPropertyType;
import wdk.data.DraftDataManager;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;
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
    private PitcherTable hitterTable;
    private HitterTable pitcherTable;
    private FantasyTeamTable startingLineupTable;
    
    private VBox taxiSquadBox;
    
    
    
    
    private FantasyTeamsController fantasyTeamsController;
    private HBox draftNameBox;
    private HBox teamBox;
    FantasyTeamsView(FantasyTeamsController initFantasyTeamsController, DraftDataManager draftManager) {
        fantasyTeamsController = initFantasyTeamsController;
    }
    public void updateTeamList(){
        
    }

    @Override
    public void initWorkspace() throws IOException {
        
        
        initTopWorkspace();
        initStartingLineup();
        initTaxiSquad();
        initEventHandlers();
        mainWorkspacePane = new VBox();
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
        
        
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }
    private void initStartingLineup() {
    //startingLineupTable = new FantasyTeamTable(players);
    
    }
    
    
    
    private void initTaxiSquad() {
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
            //   fileController.handleExportDraftRequest(this);
        });
        
        removeTeamButton.setOnAction(e -> {
            //   fileController.handleExportDraftRequest(this);
        });
        editTeamButton.setOnAction(e -> {
            //   fileController.handleExportDraftRequest(this);
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

    
}
