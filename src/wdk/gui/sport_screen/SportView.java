/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.sport_screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.GeneralPropertyType;
import wdk.data.DraftDataManager;
import wdk.data.Team;
import wdk.gui.MenuView;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;

/**
 *
 * @author Work
 */


public class SportView implements MenuView {
    private VBox mainWorkspacePane;
    
    
    
    private GridPane    topWorkspacePane;
    private Label       headingLabel;
    private ComboBox    selectTeamComboBox;
    
    
    private ProTeamTable proTeamTable;
    private SportController sportController;
    private DraftDataManager draftManager;
    ArrayList<String> proTeams;

    SportView(SportController initSportController, DraftDataManager initDraftManager, ArrayList<String> proTeams) {
        this.sportController = initSportController;
        this.draftManager = initDraftManager;  
        this.proTeams = proTeams;
    }

    @Override
    public void initWorkspace() {
        mainWorkspacePane = new VBox();
        try {
            initTopWorkspace();
        } catch (IOException ex) {
            Logger.getLogger(SportView.class.getName()).log(Level.SEVERE, null, ex);
        }
        initBottomTable();
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getChildren().add(proTeamTable.getTable());
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }

    private void initTopWorkspace() throws IOException {
        topWorkspacePane = new GridPane();
        headingLabel = initGridLabel(topWorkspacePane, GeneralPropertyType.MLB_TEAMS_LABEL, StyleSheet.CLASS_HEADING_LABEL, 0, 0, 1, 1); 
        selectTeamComboBox = initGridComboBox(topWorkspacePane, 0, 1, 1, 1);
        selectTeamComboBox.setItems(FXCollections.observableArrayList( proTeams));
    }

    private void initBottomTable() {
        proTeamTable = new ProTeamTable();
        proTeamTable.setTable(draftManager.getDraft().getAllPlayers(), null);
    }
    
    private Label initGridLabel(GridPane container, GeneralPropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
    private Label initLabel(GeneralPropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
     private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }

    public void reset() {
    }

    @Override
    public void initGUI() {
        initWorkspace();
        initEventHandlers();
    }
    
    public void update(){
        String s = "";
        if(!selectTeamComboBox.getSelectionModel().isEmpty())
            s = String.valueOf( selectTeamComboBox.getSelectionModel().getSelectedItem());
        proTeamTable.setTable(draftManager.getDraft().getAllPlayers(), s);
    }

    @Override
    public void initEventHandlers() {
        selectTeamComboBox.setOnAction(e->{
            update();
        });
    }

    public Object getView() {
        return mainWorkspacePane;
    }

}
