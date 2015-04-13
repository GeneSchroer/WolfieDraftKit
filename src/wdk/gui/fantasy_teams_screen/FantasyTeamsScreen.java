/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.WDK_PropertyType;
import static wdk.WDK_PropertyType.FANTASY_TEAMS_LABEL;
import wdk.gui.MenuScreen;
import wdk.gui.StyleSheet;
import static wdk.gui.StyleSheet.CLASS_BACKGROUND_PANE;
import static wdk.gui.StyleSheet.CLASS_SCREEN_BACKGROUND_PANE;

/**
 *
 * @author Work
 */
public class FantasyTeamsScreen implements MenuScreen{
    private VBox mainWorkspacePane;
    private Label headingLabel;
    
    private VBox teamTablePane;
    private PitcherTable hitterTable;
    private HitterTable pitcherTable;
    private ComboBox selectTeamComboBox;
    private Button addTeamButton;
    private Button removeTeamButton;
    
    private Label teamNameLabel;
    private TextField teamNameTextField;
    private Label teamOwnerLabel;
    private TextField teamOwnerTextField;
    private GridPane topWorkspacePane;
    private final Stage primaryStage;
    
    public FantasyTeamsScreen(Stage initPrimaryStage){
        primaryStage = initPrimaryStage;
    }
    public void updateTeamList(){
        
    }

    @Override
    public void initWorkspace() {
        
        
        initTopWorkspace();
        initHitterTable();
        initPitcherTable();
        initEventHandlers();
        mainWorkspacePane = new VBox();
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

    private void initPitcherTable() {
    }

    private void initHitterTable() {
    }

    private void initTopWorkspace() {
        topWorkspacePane = new GridPane();
        headingLabel = initGridLabel(topWorkspacePane, WDK_PropertyType.FANTASY_TEAMS_LABEL, StyleSheet.CLASS_HEADING_LABEL , 0, 0, 1, 1); 
        
    }

    private void initEventHandlers() {
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

    public void initGUI() {
        initWorkspace();
        initEventHandlers();
    }

    public void reset() {
    }
    
}
