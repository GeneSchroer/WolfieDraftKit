/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.draft_screen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wdk.GeneralPropertyType;
import wdk.data.DraftDataManager;
import wdk.gui.MenuView;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;
import wdk.gui.WDK_GUI;
import wdk.gui.draft_screen.DraftController;
import wdk.util.MethodList;

/**
 *
 * @author Work
 */


public class DraftView implements MenuView {
    private VBox mainWorkspacePane;
    
    
    
    private GridPane    topWorkspacePane;
    private Label       headingLabel;
    private ComboBox    selectTeamComboBox;
    private FlowPane automatedDraftBox;
    private Button draftBestPlayerButton;
    private Button startAutomatedDraftButton;
    private Button pauseAutomatedDraftButton;
    private DraftController draftController;
    private final DraftDataManager draftManager;
    
    
    public DraftTable draftTable;
    private final WDK_GUI gui;
    
    //private TeamStatsTable teamStatsTable;
    
   public DraftView(DraftController initDraftController, WDK_GUI gui){
       this.gui = gui;
       
       this.draftController = initDraftController;
       this.draftManager = gui.getDataManager();
    }

    @Override
    public void initWorkspace() {
        mainWorkspacePane = new VBox();
        try {
            initTopWorkspace();
        } catch (IOException ex) {
            Logger.getLogger(DraftView.class.getName()).log(Level.SEVERE, null, ex);
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
        automatedDraftBox = new FlowPane();
        draftBestPlayerButton = MethodList.initChildButton(automatedDraftBox, GeneralPropertyType.ADD_ICON, GeneralPropertyType.DRAFT_BEST_PLAYER_TOOLTIP, true);
        startAutomatedDraftButton = MethodList.initChildButton(automatedDraftBox, GeneralPropertyType.START_ICON, GeneralPropertyType.START_AUTOMATED_DRAFT_TOOLTIP, true);
        pauseAutomatedDraftButton = MethodList.initChildButton(automatedDraftBox, GeneralPropertyType.PAUSE_ICON, GeneralPropertyType.PAUSE_AUTOMATED_DRAFT_TOOLTIP, true);
        topWorkspacePane.add(automatedDraftBox, 0, 1);
    
    }

    private void initBottomTable() {
    }
    
    public void reset() {
        update();
    }
    @Override
    public void initGUI() {
        initWorkspace();
        initEventHandlers();
    }

    @Override
    public void initEventHandlers() {
        draftBestPlayerButton.setOnAction(e->{
            try {
                draftController.handleDraftBestPlayerRequest(this.gui);
            } catch (Exception ex) {
                Logger.getLogger(DraftView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        startAutomatedDraftButton.setOnAction(e->{
            draftController.handleStartAutomatedDraftRequest(this);
        });
        pauseAutomatedDraftButton.setOnAction(e->{
            draftController.handlepauseAutomatedDraftRequest(this);
        });
        
    }

    public void update(){
        if (draftManager.getDraft().getTeams().isEmpty()){
            draftBestPlayerButton.setDisable(true);
            startAutomatedDraftButton.setDisable(true);
            pauseAutomatedDraftButton.setDisable(true);
        }
        else{
            draftBestPlayerButton.setDisable(false);
            startAutomatedDraftButton.setDisable(false);
            pauseAutomatedDraftButton.setDisable(false);
        }
    }
}
