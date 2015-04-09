/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import wdk.table.MixedPlayerTable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import  wdk.WDK_PropertyType;
import static wdk.WDK_StartUpConstants.*;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class PlayersScreen implements MenuScreen {
    private VBox playersScreenPane;
    private Label searchForPlayerLabel;
    private TextField searchForPlayerTextField;
    private Label movePlayerToTeamLabel;
    private ComboBox movePlayerToTeamComboBox;
    private HBox addRemovePlayerBox;
    private Button addPlayerButton;
    private Button removePlayerButton;
    
    private HBox selectPlayerTypesCriteriaPane;
    private ToggleGroup sortCriteriaToggleGroup;
    private RadioButton selectAllRadioButton;
    private RadioButton selectCatcherRadioButton;
    private RadioButton select1stBasemanRadioButton;
    private RadioButton selectCornerInfielderRadioButton;
    private RadioButton select3rdBasemanRadioButton;
    private RadioButton select2ndBasemanRadioButton;
    private RadioButton selectMiddleInfielderRadioButton;
    private RadioButton selectShortstopRadioButton;
    private RadioButton selectOutfieldRadioButton;
    private RadioButton selectUtilityRadioButton;
    private RadioButton selectPitcherRadioButton;
    private MixedPlayerTable playersTable;
    private final Stage primaryStage;
    
    public PlayersScreen(Stage initPrimaryStage){
       primaryStage = initPrimaryStage;
    }
    public void setPlayersTable(ObservableList<? extends Player> player){
        
    }
    private void registerTextFieldController(TextField textField){
        
    }

    @Override
    public void initScreen() {
        playersScreenPane = new VBox();
    }

    @Override
    public void initTable() {
        playersTable = new MixedPlayersTable(players);
    }

    @Override
    public void initUIControls() {
        addRemovePlayerBox = new HBox();
        addPlayerButton = initChildButton(addRemovePlayerBox, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_PLAYER_TOOLTIP, false);
        removePlayerButton = initChildButton(addRemovePlayerBox, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_PLAYER_TOOLTIP, false);
        
        
        
        
        
        
        
        selectPlayerTypesCriteriaPane = new HBox();
        
    sortCriteriaToggleGroup = new ToggleGroup();
    
    selectAllRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "All");
    
    
    selectCatcherRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "C"); ;
    select1stBasemanRadioButton= initGroupRadioButton(sortCriteriaToggleGroup, "1B");;
    selectCornerInfielderRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "CI");;
    select3rdBasemanRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "3B");
    select2ndBasemanRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "2B");
    selectMiddleInfielderRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "MI");
    selectShortstopRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "SS");
    selectOutfieldRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "OF");
    selectUtilityRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "U");
    selectPitcherRadioButton = initGroupRadioButton(sortCriteriaToggleGroup, "P");
        
    }
    private RadioButton initGroupRadioButton(ToggleGroup toggle, String label){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        RadioButton radio = new RadioButton(label);
        toggle.getToggles().add(radio);
        return radio;
    }
    
    private Button initChildButton(Pane toolbar, WDK_PropertyType icon, WDK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    @Override
    public Pane getScreen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
