/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.players_screen;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.GeneralPropertyType;
import static wdk.WDK_StartUpConstants.PATH_FLAGS;
import static wdk.WDK_StartUpConstants.PATH_PLAYERS;
import wdk.data.Draft;
import wdk.data.Player;
import wdk.data.Position;
import wdk.gui.MessageDialog;
import static wdk.gui.StyleSheet.CLASS_HEADING_LABEL;
import static wdk.gui.StyleSheet.CLASS_PROMPT_LABEL;
import static wdk.gui.StyleSheet.PRIMARY_STYLE_SHEET;


/**
 *
 * @author Work
 */
public class PlayerDialog extends Stage{
    private Object props;
    
    
    EventHandler completeAddHandler;
    EventHandler completeEditHandler;

    
    

    private enum screen { ADD, EDIT };
     Player player;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    
    //CONTROLS FOR OUR ADD DIALOG 
    Label firstNameLabel;
    TextField firstNameTextField;
    Label dateLabel;
    Label lastNameLabel;
    TextField lastNameTextField;
    Label proTeamLabel;
    ComboBox proTeamComboBox;
    CheckBox catcherCheckBox;
    CheckBox firstBasemanCheckBox;
    CheckBox thirdBasemanCheckBox;
    CheckBox secondBasemanCheckBox;
    CheckBox shortstopCheckBox;
    CheckBox outFielderCheckBox;
    CheckBox pitcherCheckBox;
    
    
    //CONTROLS FOR OUR EDIT DIALOG
    
    Image playerImage;
    ImageView playerView;
    Image flagImage;
    ImageView flagView;
    Label playerNameLabel;
    Label qualifiedPositionLabel;
    Label fantasyTeamLabel;
    ComboBox fantasyTeamComboBox;
    Label positionLabel;
    ComboBox positionComboBox;
    Label contractLabel;
    ComboBox contractComboBox;
    Label salaryLabel;
    ComboBox salaryComboBox;
            
            
            
    Button completeButton;
    Button cancelButton;
    
    
    String selection;
    
    
    private int qp;
    
    private static final String PLAYER_DETAILS_HEADING = "Player Details";
    public static final String FIRST_NAME_PROMPT = "First Name:";
    public static final String LAST_NAME_PROMPT = "Last Name:";
    public static final String PRO_TEAM_PROMPT = "Pro Team:";
    public static final String ADD_PLAYER_TITLE = "Add New Player";
    public static final String EDIT_PLAYER_TITLE = "Edit Player";
    public static final String DIALOG_FANTASY_LABEL = "Fantasy Team";
    public static final String DIALOG_POSITION_LABEL = "Position";
    public static final String DIALOG_CONTRACT_LABEL = "Contract";
    public static final String DIALOG_SALARY_LABEL = "Salary";
    
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
//    private static final String PLAYER_DETAILS_HEADING = "Player Details";
//    private static final String PLAYER_DETAILS_HEADING = "Player Details";

    public PlayerDialog(Stage primaryStage, Draft draft, MessageDialog messageDialog, ArrayList<String> proTeam) {
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        qp = 0;
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(PLAYER_DETAILS_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
    
        // NOW THE FIRSTNAME 
        lastNameLabel = new Label(LAST_NAME_PROMPT);
        lastNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        lastNameTextField = new TextField();
        lastNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setLastName(newValue);
        });
        // NOW THE FIRSTNAME 
        firstNameLabel = new Label(FIRST_NAME_PROMPT);
        firstNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        firstNameTextField = new TextField();
        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setFirstName(newValue);
        });
        // NOW THE PROTEAM
        proTeamLabel = new Label(PRO_TEAM_PROMPT);
        proTeamLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        proTeamComboBox = new ComboBox();
        loadComboBox(proTeamComboBox, proTeam);
//        proTeamComboBox.textProperty().addListener((observable, oldValue, newValue) -> {
//            player.setFirstName(newValue);
//        });
        
        // AND THE DATE

        catcherCheckBox = new CheckBox(Position.C.toString());
        catcherCheckBox.setOnAction(e->{
            if(catcherCheckBox.isSelected()){
            pitcherCheckBox.setSelected(false);
            player.removePosition(Position.P);
            --qp;
            player.addPosition(Position.C);            
            player.addPosition(Position.U);
            qp += 2;
            }
            else{
                player.removePosition(Position.C);
                --qp;
                if(player.getPositionList().size() == 1 
                        && player.getPositionList().contains(Position.U)){
                        player.removePosition(Position.U);
                        --qp;
                }
            }
        });
        firstBasemanCheckBox = new CheckBox(Position.B1.toString());
        firstBasemanCheckBox.setOnAction(e->{
            if(firstBasemanCheckBox.isSelected()){
            pitcherCheckBox.setSelected(false);
            player.removePosition(Position.P);
            player.addPosition(Position.B1);
            player.addPosition(Position.CI);
            player.addPosition(Position.U);
            }
            else{
                player.removePosition(Position.B1);
                if(!player.getPositionList().contains(Position.B3))
                    player.removePosition(Position.CI);
                if(player.getPositionList().size() == 1 
                        && player.getPositionList().contains(Position.U));
                        player.removePosition(Position.U);
            }
        });
        thirdBasemanCheckBox = new CheckBox(Position.B3.toString());
        thirdBasemanCheckBox.setOnAction(e->{
            if(thirdBasemanCheckBox.isSelected()){
            pitcherCheckBox.setSelected(false);
            player.removePosition(Position.P);
            player.addPosition(Position.B3);            
            player.addPosition(Position.CI);            
            player.addPosition(Position.U);
            }
            else{
                player.removePosition(Position.B3);
                if(!player.getPositionList().contains(Position.B1))
                    player.removePosition(Position.CI);
                if(player.getPositionList().size() == 1 
                        && player.getPositionList().contains(Position.U));
                        player.removePosition(Position.U);
            }
        });
        secondBasemanCheckBox = new CheckBox(Position.B2.toString());
        secondBasemanCheckBox.setOnAction(e->{
        if(secondBasemanCheckBox.isSelected()){    
            pitcherCheckBox.setSelected(false);
            player.removePosition(Position.P);
            player.addPosition(Position.B2);            
            player.addPosition(Position.MI);            
            player.addPosition(Position.U);
        }
            else{
                player.removePosition(Position.B2);
                if(!player.getPositionList().contains(Position.SS))
                    player.removePosition(Position.MI);
                if(player.getPositionList().size() == 1 
                        && player.getPositionList().contains(Position.U));
                        player.removePosition(Position.U);
            }
        });
        shortstopCheckBox = new CheckBox(Position.SS.toString());
        shortstopCheckBox.setOnAction(e->{
            if(shortstopCheckBox.isSelected()){
            pitcherCheckBox.setSelected(false);
            player.removePosition(Position.P);
            player.addPosition(Position.SS);            
            player.addPosition(Position.MI);            
            player.addPosition(Position.U);
            }
            else{
                player.removePosition(Position.SS);
                if(!player.getPositionList().contains(Position.B2))
                    player.removePosition(Position.MI);
                if(player.getPositionList().size() == 1 
                        && player.getPositionList().contains(Position.U));
                        player.removePosition(Position.U);
            }
        });
        outFielderCheckBox = new CheckBox(Position.OF.toString());
        outFielderCheckBox.setOnAction(e->{
            if(outFielderCheckBox.isSelected()){
            pitcherCheckBox.setSelected(false);
            player.removePosition(Position.P);
            player.addPosition(Position.OF);            
            player.addPosition(Position.U);
            }
            else{
                player.removePosition(Position.OF);
                if(player.getPositionList().size() == 1 
                        && player.getPositionList().contains(Position.U));
                        player.removePosition(Position.U);
            }
        });
        pitcherCheckBox = new CheckBox(Position.P.toString());
        pitcherCheckBox.setOnAction(e->{
            if(pitcherCheckBox.isSelected()){
            catcherCheckBox.setSelected(false);
            firstBasemanCheckBox.setSelected(false);
            thirdBasemanCheckBox.setSelected(false);
            secondBasemanCheckBox.setSelected(false);
            shortstopCheckBox.setSelected(false);
            outFielderCheckBox.setSelected(false);
            player.getPositionList().clear();
            player.addPosition(Position.P);
            }
            else{
                player.removePosition(Position.P);
            }
                
        });
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_PLAYERS + props.getProperty(GeneralPropertyType.BLANK_IMAGE);
        playerImage = new Image(imagePath);
        playerView = new ImageView(playerImage);
        imagePath = "file:" + PATH_FLAGS + props.getProperty(GeneralPropertyType.DEFAULT_FLAG);
        flagImage = new Image(imagePath);
        flagView = new ImageView(flagImage);
        playerNameLabel = new Label();
        qualifiedPositionLabel = new Label();
        
        fantasyTeamLabel = new Label(DIALOG_FANTASY_LABEL);
        fantasyTeamLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        
        fantasyTeamComboBox = new ComboBox();
        positionLabel = new Label(DIALOG_POSITION_LABEL);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        positionComboBox = new ComboBox();
        contractLabel = new Label(DIALOG_CONTRACT_LABEL);
        positionLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        contractComboBox = new ComboBox();
        salaryLabel = new Label(DIALOG_SALARY_LABEL);
        salaryComboBox = new ComboBox();
        
        
        
        
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        completeAddHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            PlayerDialog.this.selection = sourceButton.getText();
            if(selection.equals(COMPLETE)
                && (lastNameTextField.getText()==null
                    || firstNameTextField.getText()==null
                    || proTeamComboBox.getSelectionModel().isEmpty()
                        || (!catcherCheckBox.isSelected()
                            && !firstBasemanCheckBox.isSelected()
                                && !thirdBasemanCheckBox.isSelected()
                                    &&!firstBasemanCheckBox.isSelected()
                                        &&!secondBasemanCheckBox.isSelected()
                                            &&!shortstopCheckBox.isSelected()
                                                &&!outFielderCheckBox.isSelected()
                                                    &&!pitcherCheckBox.isSelected())))
                messageDialog.show("You haven't finished adding stuff.");
            else{
            

            
            PlayerDialog.this.hide();
            }
        };
        completeEditHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            PlayerDialog.this.selection = sourceButton.getText();
            PlayerDialog.this.hide();
        };

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        
        
        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
    }
    
    
    /**
     * Accessor method for getting the selection the user made.
     * 
     * @return Either YES, NO, or CANCEL, depending on which
     * button the user selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }
    
//    public Player getPlayer() { 
//        return player;
//    }
    
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @return 
     */
    public Player showAddPlayerDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_PLAYER_TITLE);
        setScreen(screen.ADD);
        uncheckPositions();
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        player = new Player();
        
        // LOAD THE UI STUFF
            firstNameTextField.setText(player.getFirstName());
        
            lastNameTextField.setText(player.getLastName());
        
        // AND OPEN IT UP
        this.showAndWait();
        
        return player;
    }
    
    public void loadGUIData() {
//        // LOAD THE UI STUFF
//        nameTextField.setText(player.getFirstName());
//        datePicker.setValue(player.getDate());
//        teamownerTextField.setText(player.getLink());       
    }
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public void showEditPlayerDialog(Player playerToEdit) {
        // SET THE DIALOG TITLE
        setScreen(screen.EDIT);
        setTitle(EDIT_PLAYER_TITLE);
        
        // LOAD THE SCHEDULE ITEM INTO OUR LOCAL OBJECT
        player = new Player();
        player.setFirstName(playerToEdit.getFirstName());
//        player.setDate(playerToEdit.getDate());
//        player.setLink(playerToEdit.getLink());
        
        // AND THEN INTO OUR GUI
        loadGUIData();
               
        // AND OPEN IT UP
        this.showAndWait();
    }

    public void updatePlayerUsingCheckBox(CheckBox cB, Player player, Position ep){
        
    }
    
    public Player getPlayer() {
        return player;
    }

    private void uncheckPositions() {
        catcherCheckBox.setSelected(false);
        firstBasemanCheckBox.setSelected(false);
        thirdBasemanCheckBox.setSelected(false);
        secondBasemanCheckBox.setSelected(false);
        shortstopCheckBox.setSelected(false);
        outFielderCheckBox.setSelected(false);
        pitcherCheckBox.setSelected(false);
    }
    public void setScreen(screen s){
        if (s == screen.ADD ){
            gridPane.getChildren().clear();
            
            gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(firstNameLabel, 0, 1, 1, 1);
        gridPane.add(firstNameTextField, 1, 1, 1, 1);
        gridPane.add(lastNameLabel, 0, 2, 1, 1);
        gridPane.add(lastNameTextField, 1, 2, 1, 1);
        gridPane.add(proTeamLabel, 0, 3, 1, 1);
        gridPane.add(proTeamComboBox, 1, 3, 1, 1);
        gridPane.add(catcherCheckBox, 0, 4, 1, 1);
        gridPane.add(firstBasemanCheckBox, 1, 4, 1, 1);
        gridPane.add(thirdBasemanCheckBox, 2, 4, 1, 1);
        gridPane.add(secondBasemanCheckBox, 3, 4, 1, 1);
        gridPane.add(shortstopCheckBox, 4, 4, 1, 1);
        gridPane.add(outFielderCheckBox, 5, 4, 1, 1);
        gridPane.add(pitcherCheckBox, 6, 4, 1, 1);
        gridPane.add(completeButton, 0, 10, 1, 1);
        gridPane.add(cancelButton, 1, 10, 1, 1);
          completeButton.setOnAction(completeAddHandler);
        cancelButton.setOnAction(completeAddHandler);  
        }
        else{
            gridPane.getChildren().clear();
            gridPane.add(playerView, 0, 1, 3, 3);
        gridPane.add(flagView, 1, 1, 1, 1);
        gridPane.add(playerNameLabel, 1, 2, 1, 1);
        gridPane.add(qualifiedPositionLabel, 1, 3, 1, 1);
        gridPane.add(fantasyTeamLabel, 0, 4, 1, 1);
        gridPane.add(fantasyTeamComboBox, 1, 4, 1, 1);
        gridPane.add(positionLabel, 0, 5, 1, 1);
        gridPane.add(positionComboBox, 1, 5, 1, 1);
        gridPane.add(contractLabel, 0, 6, 1, 1);
        gridPane.add(contractComboBox, 1, 6, 1, 1);
        gridPane.add(salaryLabel, 0, 7, 1, 1);
        gridPane.add(salaryComboBox, 1, 7, 1, 1);
        gridPane.add(completeButton, 0, 10, 1, 1);
        gridPane.add(cancelButton, 1, 10, 1, 1);
        
        completeButton.setOnAction(completeEditHandler);
        cancelButton.setOnAction(completeEditHandler);
        }
    }
    
    
    
    private void loadComboBox(ComboBox comboBox, ArrayList<String> list) {
            for(String s : list){
                comboBox.getItems().add(s);
            }
        
    }
}
