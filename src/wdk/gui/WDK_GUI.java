package wdk.gui;

import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import wdk.GeneralPropertyType;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.data.DraftDataView;
import wdk.file.DraftExporter;
import properties_manager.PropertiesManager;
import static wdk.WDK_StartUpConstants.*;
import wdk.controller.DraftEditController;
import wdk.controller.FileController;
import wdk.file.DraftFileManager;
import static wdk.gui.StyleSheet.CLASS_BACKGROUND_PANE;
import static wdk.gui.StyleSheet.PRIMARY_STYLE_SHEET;
import wdk.gui.draft_screen.DraftScreen;
import wdk.gui.fantasy_standings_screen.FantasyStandingsScreen;
import wdk.gui.fantasy_teams_screen.FantasyTeamsScreen;
import wdk.gui.players_screen.PlayersScreen;
import wdk.gui.sport_screen.SportScreen;

/**
 *
 * @author Work
 */
public class WDK_GUI implements DraftDataView {



    PlayersScreen playersScreen;
    FantasyTeamsScreen fantasyTeamsScreen;
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
    StackPane workspacePane;
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
    private Draft startingDraft;

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
     * @param proTeams
     * @throws IOException Thrown if any initialization files fail to load.
     */
    public void initGUI(String windowTitle, ArrayList<String> proTeams) throws IOException {
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
        initWorkspace(proTeams);

        /* Now we set up the event handlers for our various UI controls.
         * each menu screen gets called to set up its own event handlers as well*/
        initEventHandlers();

        /* And finally we start up the window with no menu screen visible */
        initWindow(windowTitle);
        /* Showtime! */

    }
    
    /**
     * This function takes all of the data out of the courseToReload 
     * argument and loads its values into the user interface controls.
     * 
     * @param draftToReload The Course whose data we'll load into the GUI.
     */
    @Override
    public void reloadDraft(Draft draftToReload) {
        if (!workspaceActivated) {
            activateWorkspace();
        }
        draftController.enable(false);
        
        /*
            This space to be filled in later homeworks
        */
        playersScreen.reset();
        fantasyTeamsScreen.reset();
        fantasyStandingsScreen.reset();
        draftScreen.reset();
        sportScreen.reset();
        
        draftController.enable(true);
    }

    /**
     * This method is used to activate/deactivate toolbar buttons when they can
     * and cannot be used so as to provide foolproof design.
     *
     * @param saved Describes whether the loaded draft has been saved or not.
     */
    public void updateGUI(boolean saved){
        updateToolbarControls(saved);
        updateMenuControls();
    }
    public void updateMenuControls(){
        playersScreen.update();
        fantasyTeamsScreen.update();
        fantasyStandingsScreen.update();
        draftScreen.update();
        sportScreen.update();
    }
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
    /**
     * This function loads all the values currently in the user interface
     * into the course argument.
     * 
     * @param draft The course to be updated using the data from the UI controls.
     */
    public void updateDraftInfo(Draft draft) {
//        This will be added to in HW6
    }

     /****************************************************************************/
    /* The private methods used to initialze the GUI. */
    /****************************************************************************/
    
    private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }

    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        newDraftButton = initChildButton(fileToolbarPane, GeneralPropertyType.NEW_DRAFT_ICON, GeneralPropertyType.NEW_DRAFT_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, GeneralPropertyType.LOAD_DRAFT_ICON, GeneralPropertyType.LOAD_DRAFT_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, GeneralPropertyType.SAVE_DRAFT_ICON, GeneralPropertyType.SAVE_DRAFT_TOOLTIP, true);
        exportDraftButton = initChildButton(fileToolbarPane, GeneralPropertyType.EXPORT_DRAFT_ICON, GeneralPropertyType.EXPORT_DRAFT_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, GeneralPropertyType.EXIT_ICON, GeneralPropertyType.EXIT_TOOLTIP, false);

    }

    private void initScreenToolbar() {
        screenToolbarPane = new FlowPane();

        playersScreenButton = initChildButton(screenToolbarPane, GeneralPropertyType.PLAYERS_ICON, GeneralPropertyType.PLAYERS_SCREEN_TOOLTIP, true);
        fantasyTeamsScreenButton = initChildButton(screenToolbarPane, GeneralPropertyType.FANTASY_TEAMS_ICON, GeneralPropertyType.FANTASY_TEAMS_SCREEN_TOOLTIP, true);
        fantasyStandingsScreenButton = initChildButton(screenToolbarPane, GeneralPropertyType.FANTASY_STANDINGS_ICON, GeneralPropertyType.FANTASY_STANDINGS_SCREEN_TOOLTIP, true);
        draftScreenButton = initChildButton(screenToolbarPane, GeneralPropertyType.DRAFT_ICON, GeneralPropertyType.DRAFT_SCREEN_TOOLTIP, true);
        sportScreenButton = initChildButton(screenToolbarPane, GeneralPropertyType.SPORT_ICON, GeneralPropertyType.SPORT_SCREEN_TOOLTIP, true);
    }
    
    private void initWorkspace(ArrayList<String> proTeams) {
        workspacePane = new StackPane();
        
        initMenuScreens(proTeams);
        
        workspacePane.getChildren().add(playersScreen.getView().getScreen());
        workspacePane.getChildren().add(fantasyTeamsScreen.getView().getScreen());
        workspacePane.getChildren().add(fantasyStandingsScreen.getView().getScreen());
        workspacePane.getChildren().add(draftScreen.getView().getScreen());
        workspacePane.getChildren().add(sportScreen.getView().getScreen());
        
        
        handleSwitchMenuRequest(playersScreen);
        workspacePane.getStyleClass().add(CLASS_BACKGROUND_PANE);
        
    }

    private void initEventHandlers() {
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
            //   fileController.handleExportDraftRequest(this);
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest(this);
        });
        playersScreenButton.setOnAction(e -> {
            handleSwitchMenuRequest(playersScreen);
        });
        fantasyTeamsScreenButton.setOnAction(e -> {
            handleSwitchMenuRequest(fantasyTeamsScreen);
        });
        fantasyStandingsScreenButton.setOnAction(e -> {
            handleSwitchMenuRequest(fantasyStandingsScreen);
        });
        draftScreenButton.setOnAction(e -> {
            handleSwitchMenuRequest(draftScreen);
        });
        sportScreenButton.setOnAction(e -> {
            handleSwitchMenuRequest(sportScreen);
        });
        
        draftController = new DraftEditController();

        
        
        
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

    private void initMenuScreens(ArrayList<String> proTeams) {
        playersScreen = new PlayersScreen(primaryStage, this, messageDialog, yesNoCancelDialog, proTeams);
        fantasyTeamsScreen = new FantasyTeamsScreen(primaryStage, this, messageDialog, yesNoCancelDialog);
        fantasyStandingsScreen = new FantasyStandingsScreen(draftDataManager);
        draftScreen = new DraftScreen(this);
        sportScreen = new SportScreen(draftDataManager, proTeams);
        
        playersScreen.initGUI(proTeams);
        fantasyTeamsScreen.initGUI();
        fantasyStandingsScreen.initGUI();
        draftScreen.initGUI();
        sportScreen.initGUI();
    }

    private Button initChildButton(Pane toolbar, GeneralPropertyType icon, GeneralPropertyType tooltip, boolean disabled) {
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

    private Label initChildLabel(Pane container, GeneralPropertyType labelProperty, String styleClass) {
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
    private Label initGridLabel(GridPane container, GeneralPropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
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

    private Label initLabel(GeneralPropertyType labelProperty, String styleClass) {
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
        if(!workspaceActivated){
            /* Make the workspace visible in the gui*/
            
            
            wdkPane.setCenter(workspacePane);
                        //wdkPane.setCenter(new BorderPane(new Label("Test")));
                        
            workspaceActivated = true;
            playersScreenButton.setDisable(false);
            fantasyTeamsScreenButton.setDisable(false);
            fantasyStandingsScreenButton.setDisable(false);
            draftScreenButton.setDisable(false);
            sportScreenButton.setDisable(false);
            
        }
    }
    
    public void handleSwitchMenuRequest(MenuScreen menuScreen){
        workspacePane.setDisable(false);

        
        playersScreen.setVisible(false);
        fantasyTeamsScreen.setVisible(false);
        fantasyStandingsScreen.setVisible(false);
        draftScreen.setVisible(false);
        sportScreen.setVisible(false);
        
        menuScreen.setVisible(true);
        
    }

    public DraftDataManager getDraftDataManager() {
        return draftDataManager;
    }

}
