/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.GeneralPropertyType;
import wdk.gui.MenuScreen;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_HEADING_LABEL;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;
import wdk.util.MethodList;

/**
 *
 * @author Work
 */


public class DraftScreen implements MenuScreen {
    private VBox mainWorkspacePane;
    
    
    
    private GridPane    topWorkspacePane;
    private Label       headingLabel;
    private ComboBox    selectTeamComboBox;
    private HBox automatedDraftBox;
    private Button draftBestPlayerButton;
    private Button startAutomatedDraftButton;
    private Button pauseAutomatedDraftButton;
    
    
    //private TeamStatsTable teamStatsTable;
    
    public DraftScreen(Stage initPrimaryStage){
        
    }

    @Override
    public void initWorkspace() {
        mainWorkspacePane = new VBox();
        try {
            initTopWorkspace();
        } catch (IOException ex) {
            Logger.getLogger(DraftScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        initBottomTable();
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
    }

  
    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }

    private void initTopWorkspace() throws IOException {
        topWorkspacePane = new GridPane();
        headingLabel = MethodList.initGridLabel(topWorkspacePane, GeneralPropertyType.DRAFT_SUMMARY_LABEL, StyleSheet.CLASS_HEADING_LABEL, 0, 0, 1, 1); 
        automatedDraftBox = new HBox();
        draftBestPlayerButton = MethodList.initChildButton(automatedDraftBox, GeneralPropertyType.ADD_ICON, GeneralPropertyType.DRAFT_BEST_PLAYER_TOOLTIP, true);
        startAutomatedDraftButton = MethodList.initChildButton(automatedDraftBox, GeneralPropertyType.START_ICON, GeneralPropertyType.START_AUTOMATED_DRAFT_TOOLTIP, false);
        pauseAutomatedDraftButton = MethodList.initChildButton(automatedDraftBox, GeneralPropertyType.PAUSE_ICON, GeneralPropertyType.PAUSE_AUTOMATED_DRAFT_TOOLTIP, true);
        topWorkspacePane.add(automatedDraftBox, 0, 1);
    
    }

    private void initBottomTable() {
    }
    
    public void reset() {
    }
    @Override
    public void initGUI() {
        initWorkspace();
        initEventHandlers();
    }

    @Override
    public void initEventHandlers() {
    }
}
