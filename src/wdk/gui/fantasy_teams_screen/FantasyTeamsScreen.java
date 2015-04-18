/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.GeneralPropertyType;
import static wdk.GeneralPropertyType.FANTASY_TEAMS_LABEL;
import wdk.gui.MenuScreen;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_BACKGROUND_PANE;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;
import wdk.util.MethodList;

/**
 *
 * @author Work
 */
public class FantasyTeamsScreen implements MenuScreen{
    private VBox mainWorkspacePane;
    private Label headingLabel;
    
    
    /* Panes and controls for top pane */
    private GridPane topWorkspacePane;
    
    private Label selectTeamLabel;
    private ComboBox selectTeamComboBox;
    private HBox addRemoveTeamBox;
    private Button addTeamButton;
    private Button removeTeamButton;
    private Label teamNameLabel;
    private TextField teamNameTextField;
    private Label teamOwnerLabel;
    private TextField teamOwnerTextField;
    
    
    
    /* Hitter and pitcher tables */
    private VBox teamTablePane;
    private PitcherTable hitterTable;
    private HitterTable pitcherTable;
    
    
    
    private final Stage primaryStage;
    
    public FantasyTeamsScreen(Stage initPrimaryStage){
        primaryStage = initPrimaryStage;
    }
    public void updateTeamList(){
        
    }

    @Override
    public void initWorkspace() throws IOException {
        
        
        initTopWorkspace();
        initHitterTable();
        initPitcherTable();
        initEventHandlers();
        mainWorkspacePane = new VBox();
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
        
        
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }

    private void initPitcherTable() {
    }

    private void initHitterTable() {
    }

    private void initTopWorkspace() throws IOException {
        topWorkspacePane    = new GridPane();
        headingLabel        = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.FANTASY_TEAMS_LABEL, StyleSheet.CLASS_HEADING_LABEL, 0, 0, 1, 1); 
        selectTeamLabel     = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.TEAM_SELECT_LABEL, StyleSheet.CLASS_SUBHEADING_LABEL, 0, 1, 1, 1); 
        selectTeamComboBox  = MethodList.initGridComboBox(topWorkspacePane, 1, 1, 1, 1);
        addRemoveTeamBox    = new HBox();
        addTeamButton       = MethodList.initChildButton(addRemoveTeamBox, GeneralPropertyType.ADD_ICON, GeneralPropertyType.ADD_TEAM_TOOLTIP, false);
        removeTeamButton    = MethodList.initChildButton(addRemoveTeamBox, GeneralPropertyType.MINUS_ICON, GeneralPropertyType.REMOVE_TEAM_TOOLTIP, false);
        topWorkspacePane.add(addRemoveTeamBox, 0, 2, 1, 1);
        teamNameLabel       = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.TEAM_NAME_LABEL, StyleSheet.CLASS_SUBHEADING_LABEL, 1, 2, 1, 1); 
        teamNameTextField   = MethodList.initGridTextField(topWorkspacePane, 10, null, true, 2, 2, 1, 1);
        teamOwnerLabel      = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.TEAM_OWNER_LABEL, StyleSheet.CLASS_SUBHEADING_LABEL, 3, 2, 1, 1); 
        teamOwnerTextField  = MethodList.initGridTextField(topWorkspacePane, 10, null, true, 4, 2, 1, 1);

    }

    @Override
    public void initEventHandlers() {
        addTeamButton.setOnAction(e -> {
            //   fileController.handleExportDraftRequest(this);
        });
        
        removeTeamButton.setOnAction(e -> {
            //   fileController.handleExportDraftRequest(this);
        });
        
        
        
    }

    @Override
    public void initGUI() {
        try {
            initWorkspace();
        } catch (IOException ex) {
            Logger.getLogger(FantasyTeamsScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        initEventHandlers();
    }

    public void reset() {
        
    }
    
}
