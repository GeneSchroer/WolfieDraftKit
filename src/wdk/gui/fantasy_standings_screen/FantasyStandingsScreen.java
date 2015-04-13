/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_standings_screen;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.WDK_PropertyType;
import wdk.gui.MenuScreen;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_HEADING_LABEL;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;

/**
 *
 * @author Work
 */


public class FantasyStandingsScreen implements MenuScreen {
    private VBox mainWorkspacePane;
    
    
    
    private GridPane    topWorkspacePane;
    private Label       headingLabel;
    private ComboBox    selectTeamComboBox;
    
    
    private TeamStatsTable teamStatsTable;
    
    public FantasyStandingsScreen(Stage initPrimaryStage){
        
    }

    @Override
    public void initWorkspace() {
        mainWorkspacePane = new VBox();
        try {
            initTopWorkspace();
        } catch (IOException ex) {
            Logger.getLogger(FantasyStandingsScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        initBottomTable();
        mainWorkspacePane.getChildren().add(topWorkspacePane);
        mainWorkspacePane.getStyleClass().add(CLASS_SCREEN_BACKGROUND_PANE);
    }

    @Override
    public void initTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initUIControls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pane getScreen() {
        return mainWorkspacePane;
    }

    private void initTopWorkspace() throws IOException {
        topWorkspacePane = new GridPane();
        headingLabel = initGridLabel(topWorkspacePane, WDK_PropertyType.FANTASY_STANDINGS_LABEL, StyleSheet.CLASS_HEADING_LABEL, 0, 0, 1, 1); 
        selectTeamComboBox = initGridComboBox(topWorkspacePane, 0, 1, 1, 1);
        
    }

    private void initBottomTable() {
    }
    
    private Label initGridLabel(GridPane container, WDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
    private Label initLabel(WDK_PropertyType labelProperty, String styleClass) {
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

    public void initGUI() {
        initWorkspace();
        initEventHandlers();
    }

    private void initEventHandlers() {
    }
}
