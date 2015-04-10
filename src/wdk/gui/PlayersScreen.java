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
import wdk.controller.PlayersScreenController;
import wdk.data.Player;
import static wdk.gui.WDK_GUI.CLASS_SUBHEADING_LABEL;

/**
 *
 * @author Work
 */
public class PlayersScreen implements MenuScreen {
    /* Our list of available players, will be fed into the mixed table*/
    ObservableList<? extends Player> players;
    
    /* The primary pane that is holding all of this */
    private VBox playersScreenPane;
    
    /* playersScreenPane is split into three broad segments, each holding
    blocks of UI controls */
    
    /* The top segment */
    private GridPane topWorkspacePane;
    private Label headingLabel;
    private Label playerSearchLabel;
    private TextField playerSearchTextField;
    private HBox addRemovePlayerBox;
    private Button addPlayerButton;
    private Button removePlayerButton;
    
    
    
   /* The middle segment */
   /* This holds all of our....very many radio buttons. Dear god that's alot */
    private HBox sortCriteriaPane;
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
    
    
    /* The bottom segment */
    private MixedPlayerTable playersTable;
    
    
    private final Stage primaryStage;
    
    
    PlayersScreenController playersScreenController;
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    public PlayersScreen(Stage initPrimaryStage){
       primaryStage = initPrimaryStage;
    }

   
    
    
    public void initGUI(){
        initWorkspace();
       // initEventHandlers();
    }
    
    @Override
    public void initWorkspace() {
        initTopWorkspace();
        initCenterWorkspace();
        initBottomWorkspace();
        playersScreenPane = new VBox();
    }

    @Override
    public void initTable() {
        playersTable = new MixedPlayerTable(players);
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
        return playersScreenPane;
    }
    
     private void initTopWorkspace() {
        
        
        topWorkspacePane = new GridPane();
        addRemovePlayerBox = new HBox();
        headingLabel = initGridLabel(topWorkspacePane, WDK_PropertyType.AVAILABLE_PLAYER_LABEL,CLASS_SUBHEADING_LABEL, 0, 0, 1, 1);
        addPlayerButton = initChildButton(addRemovePlayerBox, WDK_PropertyType.ADD_ICON, WDK_PropertyType.ADD_PLAYER_TOOLTIP, false);
        removePlayerButton = initChildButton(addRemovePlayerBox, WDK_PropertyType.MINUS_ICON, WDK_PropertyType.REMOVE_PLAYER_TOOLTIP, false);
        topWorkspacePane.add(addRemovePlayerBox, 0, 1, 1, 1);
        playerSearchLabel = initGridLabel(topWorkspacePane, WDK_PropertyType.PLAYER_SEARCH_LABEL, CLASS_SUBHEADING_LABEL, 1, 1 , 1, 1);
        playerSearchTextField = initGridTextField(topWorkspacePane, 3, "", true, 2, 1, 1, 1);
     
     }
    
   
    private void initCenterWorkspace() {
    sortCriteriaPane = new HBox();
        
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
    sortCriteriaPane.getChildren().add(selectAllRadioButton);
    sortCriteriaPane.getChildren().add(selectCatcherRadioButton);
    sortCriteriaPane.getChildren().add(select1stBasemanRadioButton);
    sortCriteriaPane.getChildren().add(selectCornerInfielderRadioButton);
    sortCriteriaPane.getChildren().add(select3rdBasemanRadioButton);
    sortCriteriaPane.getChildren().add(select2ndBasemanRadioButton);
    sortCriteriaPane.getChildren().add(selectMiddleInfielderRadioButton);
    sortCriteriaPane.getChildren().add(selectShortstopRadioButton);
    sortCriteriaPane.getChildren().add(selectOutfieldRadioButton);
    sortCriteriaPane.getChildren().add(selectUtilityRadioButton);
    sortCriteriaPane.getChildren().add(selectPitcherRadioButton);
    }

   
     private void initBottomWorkspace() {
    }

    public void initEventHandlers() {
        playersScreenController = new PlayersScreenController(primaryStage, messageDialog, yesNoCancelDialog);
        registerTextFieldController(playerSearchTextField);
    }
        
        public void setPlayersTable(ObservableList<? extends Player> player){
        
    }
        /*
        FIXLATERFIXLATERFIXLATERFIXLATERFIXLATERFIXLATERFIXLATERFIXLATER
        FIXLATERFIXLATERFIXLATERFIXLATERFIXLATERFIXLATERFIXLATERFIXLATER
        */
        
        private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
          //  playersScreenController.handleSearchForPlayerRequest(this, textField.getText());
        });
    }
         
         private Label initLabel(WDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
         private Label initGridLabel(GridPane container, WDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
         private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
}
