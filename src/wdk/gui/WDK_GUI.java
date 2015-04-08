package wdk.gui;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wdk.WDK_StartUpConstants;
import wdk.WDK_PropertyType;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.DraftDataView;
import wdk.file.DraftExporter;
import properties_manager.PropertiesManager;
import static wdk.WDK_StartUpConstants.*;
import wdk.controller.DraftEditController;
import wdk.controller.FileController;
import wdk.file.DraftFileManager;
import wdk.file.JsonDraftFileManager;

/**
 *
 * @author Work
 */
public class WDK_GUI implements DraftDataView {
    
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "csb_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_SUBJECT_PANE = "subject_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;
    static final int SMALL_TEXT_FIELD_LENGTH = 5;

    
    DraftExporter draftExporter;
    DraftDataManager draftDataManager;
    DraftFileManager draftFileManager;
    FileController fileController;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    DraftEditController draftController;
    
    Stage primaryStage;
    Scene primaryScene;
    
    BorderPane wdkPane;
    
    /* This is the toolbar for saving/loading the draft to file*/
    FlowPane fileToolbarPane;
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportDraftButton;
    Button exitButton;
    
    /* This is for our screens, the main workspace where we manipulate the draft */
    BorderPane workspacePane;
    boolean workspaceActivated;
    
    /* We will put the workspace inside a scroll pane so we don't have to
     * maximize the GUI to see all of our data */
    ScrollPane workspaceScrollPane;
    
    /* This will be for switching between our screens */
    TabPane screenToolbarPane;
    Button playersScreenButton;
    Button fantasyTeamsScreenButton;
    Button fantasyStandingsScreenButton;
    Button draftScreenButton;
    Button mlbScreenButton;  // Change the name of this one later
    private String windowTitle;

    public WDK_GUI(Stage primaryStage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void initGUI(){
        
        
        initDialogs();
        
        initFileToolbar();
        
        initScreenToolbar();
        initWorkspace();
        initEventHandlers();
        initWindow(windowTitle);
    }
    
    public void reloadDraft(Draft draftToReload){
        
    }
    public void updateToolbarControls(boolean saved){
        
    }
    public void updateDraftInfo(Draft draft){
        
    }
    private void initDialogs(){
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
    }
    
    private void initFileToolbar(){
        fileToolbarPane = new FlowPane();
        
        newDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.NEW_DRAFT_ICON, WDK_PropertyType.NEW_DRAFT_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.LOAD_DRAFT_ICON, WDK_PropertyType.LOAD_DRAFT_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.SAVE_DRAFT_ICON, WDK_PropertyType.SAVE_DRAFT_TOOLTIP, true);
        exportDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXPORT_DRAFT_ICON, WDK_PropertyType.EXPORT_DRAFT_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXIT_ICON, WDK_PropertyType.EXIT_TOOLTIP, false);
        
    }
    private void initScreenToolbar(){
        
    }
    private void initWorkspace(){
        
    }
    private void initEventHandlers(){
        fileController = new FileController(messageDialog, yesNoCancelDialog, draftFileManager, draftExporter);
        
        newDraftButton.setOnAction(e -> {
            fileController.handleNewDraftRequest(this);
        });
        loadDraftButton.setOnAction(e -> {
            fileController.handleLoadDraftRequest(this);
        });
        saveDraftButton.setOnAction(e -> {
            fileController.handleSaveDraftRequest(this, draftDataManager.getDraft());
        });
        exportDraftButton.setOnAction(e -> {
            fileController.handleExportDraftRequest(this);
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest(this);
        });

    
    }
    private void initWindow(String windowTitle){
        
    }
    
    private void initMenuSceens(){
        
    }
    
    private Button initChildButton(Pane toolbar, WDK_PropertyType icon, WDK_PropertyType tooltip, boolean disabled){
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
    
    private Label initChildLabel(Pane container, WDK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }
    private CheckBox initChildCheckBox(Pane container, String text) {
        CheckBox cB = new CheckBox(text);
        container.getChildren().add(cB);
        return cB;
    }
    //private void initChildRadioButton
    
    //initChildToggleButton
    
 
     private Label initGridLabel(GridPane container, WDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
   
     private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }
     private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }
     
     private Label initLabel(WDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
    private void registerTextFieldController(TextField textField){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            draftController.handleDraftChangeRequest(this);
        });
    }

    public void setDraftFileManager(JsonDraftFileManager jsonFileManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDraftExporter(DraftExporter exporter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDataManager(DraftDataManager dataManager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
