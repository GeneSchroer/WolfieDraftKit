package wdk.gui;

import wdk.zzz.DraftScreen;
import wdk.zzz.FantasyStandingsScreen;
import wdk.zzz.SportScreen;
import wdk.zzz.FantasyTeamsScreen;
import java.io.IOException;
import javafx.geometry.Rectangle2D;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
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

    PlayersScreen playersScreen;
    FantasyTeamsScreen fantasyTeams;
    FantasyStandingsScreen fantasyStandingsScreen;
    DraftScreen draftScreen;
    SportScreen sportScreen;

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
    HBox workspacePane;
    boolean workspaceActivated;

    /* We will put the workspace inside a scroll pane so we don't have to
     * maximize the GUI to see all of our data */
    ScrollPane workspaceScrollPane;

    /* This will be for switching between our screens */
    FlowPane screenToolbarPane;
    Button playersScreenButton;
    Button fantasyTeamsScreenButton;
    Button fantasyStandingsScreenButton;
    Button draftScreenButton;
    Button sportScreenButton;  // Change the name of this one later
    private String windowTitle;

    public WDK_GUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }

    /**
     * Accessor method for the data manager.
     *
     * @return The DraftDataManager used by this UI.
     */
    public DraftDataManager getDataManager() {
        return draftDataManager;
    }

    /**
     * Accessor method for the file controller.
     *
     * @return The FileController used by this UI.
     */
    public FileController getFileController() {
        return fileController;
    }

    /**
     * Accessor method for the draft file manager.
     *
     * @return The DraftFileManager used by this UI.
     */
    public DraftFileManager getdraftFileManager() {
        return draftFileManager;
    }

    /**
     * Accessor method for the site exporter.
     *
     * @return The DraftExporter used by this UI.
     */
    public DraftExporter getSiteExporter() {
        return draftExporter;
    }

    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }

    public MessageDialog getMessageDialog() {
        return messageDialog;
    }

    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }

    /**
     * Mutator method for the data manager.
     *
     * @param draftDataManager The DraftDataManager to be used by this UI.
     */
    public void setDraftDataManager(DraftDataManager draftDataManager) {
        this.draftDataManager = draftDataManager;
    }

    /**
     * Mutator method for the draft file manager.
     *
     * @param draftFileManager The DraftFileManager to be used by this UI.
     */
    public void setDraftFileManager(DraftFileManager draftFileManager) {
        this.draftFileManager = draftFileManager;
    }

    /**
     * Mutator method for the draft exporter.
     *
     * @param draftExporter The DraftSiteExporter to be used by this UI.
     */
    public void setDraftExporter(DraftExporter draftExporter) {
        this.draftExporter = draftExporter;
    }

    /**
     * This method fully initializes the user interface for use.
     *
     * @param windowTitle The text to appear in the UI window's title bar.
     * @throws IOException Thrown if any initialization files fail to load.
     */
    public void initGUI(String windowTitle /* maybe more parameters later */) throws IOException {

        /* First we initialize the various dialog classes */
        initDialogs();

        /* Then the toolbar that handles the draft file */
        initFileToolbar();

        /* Then the toolbars which let us access the screens */
        initScreenToolbar();

        /* Then initialize the workspace, 
         * the main area where we manipulate data in the draft. 
         * Which means we get each menu screen to initialize itself
         */
        initWorkspace();

        /* Now we set up the event handlers for our various UI controls.
         * each menu screen gets called to set up its own event handlers as well*/
        initEventHandlers();

        /* And finally we start up the window with no menu screen visible */
        initWindow(windowTitle);
        /* Showtime! */

    }

    public void reloadDraft(Draft draftToReload) {
        if (!workspaceActivated) {
            activateWorkspace();
        }
        draftController.enable(false);
        /*
            Everything in here gets reset. Somehow
        
        */
        draftController.enable(true);
    }

    /**
     * This method is used to activate/deactivate toolbar buttons when they can
     * and cannot be used so as to provide foolproof design.
     *
     * @param saved Describes whether the loaded draft has been saved or not.
     */
    public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT draft
        // HAS BEEN SAVED OR NOT
        saveDraftButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST Draft BEGINS
        loadDraftButton.setDisable(false);
        exportDraftButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }

    public void updateDraftInfo(Draft draft) {

    }

    private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }

    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        newDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.NEW_DRAFT_ICON, WDK_PropertyType.NEW_DRAFT_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.LOAD_DRAFT_ICON, WDK_PropertyType.LOAD_DRAFT_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.SAVE_DRAFT_ICON, WDK_PropertyType.SAVE_DRAFT_TOOLTIP, true);
        exportDraftButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXPORT_DRAFT_ICON, WDK_PropertyType.EXPORT_DRAFT_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, WDK_PropertyType.EXIT_ICON, WDK_PropertyType.EXIT_TOOLTIP, false);

    }

    private void initScreenToolbar() {
        screenToolbarPane = new FlowPane();

        playersScreenButton = initChildButton(screenToolbarPane, WDK_PropertyType.PLAYERS_ICON, WDK_PropertyType.PLAYERS_SCREEN_TOOLTIP, true);
        fantasyTeamsScreenButton = initChildButton(screenToolbarPane, WDK_PropertyType.FANTASY_TEAMS_ICON, WDK_PropertyType.PLAYERS_SCREEN_TOOLTIP, true);
        fantasyStandingsScreenButton = initChildButton(screenToolbarPane, WDK_PropertyType.FANTASY_STANDINGS_ICON, WDK_PropertyType.PLAYERS_SCREEN_TOOLTIP, true);
        draftScreenButton = initChildButton(screenToolbarPane, WDK_PropertyType.DRAFT_ICON, WDK_PropertyType.PLAYERS_SCREEN_TOOLTIP, true);
        sportScreenButton = initChildButton(screenToolbarPane, WDK_PropertyType.SPORT_ICON, WDK_PropertyType.SPORT_SCREEN_TOOLTIP, true);
    }

    private void initWorkspace() {
        playersScreen = new PlayersScreen(primaryStage);
        playersScreen.initWorkspace();
        workspacePane = new HBox();
        workspacePane.getChildren().add(playersScreen.getScreen());
    }

    private void initEventHandlers() {
        fileController = new FileController(messageDialog, yesNoCancelDialog, draftFileManager, draftExporter);

        newDraftButton.setOnAction(e -> {
            fileController.handleNewDraftRequest(this);
        });
        loadDraftButton.setOnAction(e -> {
            //   fileController.handleLoadDraftRequest(this);
        });
        saveDraftButton.setOnAction(e -> {
            //    fileController.handleSaveDraftRequest(this, draftDataManager.getDraft());
        });
        exportDraftButton.setOnAction(e -> {
            //   fileController.handleExportDraftRequest(this);
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest(this);
        });

    }

    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A draft
        wdkPane = new BorderPane();
        wdkPane.setTop(fileToolbarPane);
        wdkPane.setBottom(screenToolbarPane);
        primaryScene = new Scene(wdkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void initMenuSceens() {
        playersScreen.initGUI();
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

    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            draftController.handleDraftChangeRequest(this);
        });
    }

    private void activateWorkspace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
