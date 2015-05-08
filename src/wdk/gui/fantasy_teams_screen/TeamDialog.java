/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_teams_screen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import wdk.data.Draft;
import wdk.data.Team;
import wdk.gui.MessageDialog;
import static wdk.gui.StyleSheet.CLASS_HEADING_LABEL;
import static wdk.gui.StyleSheet.CLASS_PROMPT_LABEL;
import static wdk.gui.StyleSheet.PRIMARY_STYLE_SHEET;

/**
 *
 * @author Work
 */
public class TeamDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label nameLabel;
    TextField teamNameTextField;
    Label teamownerLabel;
    TextField teamOwnerTextField;
    Button completeButton;
    Button cancelButton;
    
    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    // CONSTANTS FOR OUR UI
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String NAME_PROMPT = "Name: ";
    public static final String OWNER_PROMPT = "Owner: ";
    public static final String SCHEDULE_ITEM_HEADING = "Schedule Item Details";
    public static final String ADD_TEAM_TITLE = "Add New Team";
    public static final String EDIT_TEAM_TITLE = "Edit Team";
    private Team team;
    /**
     * Initializes this dialog so that it can be used for either adding
     * new schedule items or editing existing ones.
     * 
     * @param primaryStage The owner of this modal dialog.
     * @param draft
     * @param messageDialog
     */
    public TeamDialog(Stage primaryStage, Draft draft,  MessageDialog messageDialog) {       
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(SCHEDULE_ITEM_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
    
        // NOW THE DESCRIPTION 
        nameLabel = new Label(NAME_PROMPT);
        nameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        teamNameTextField = new TextField();
        teamNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setName(newValue);
        });
        
        
        // AND THE TEAMOWNER
        teamownerLabel = new Label(OWNER_PROMPT);
        teamownerLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        teamOwnerTextField = new TextField();
        teamOwnerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
          team.setOwner(newValue);
        });
        
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            TeamDialog.this.selection = sourceButton.getText();
            if(selection.equals(COMPLETE)){
                    if (teamNameTextField.getText() == null
                        || teamOwnerTextField.getText()==null){
                messageDialog.show("You have not filled in all the needed information");
                    }
                    else{
                        if(teamNameTextField.getText().equalsIgnoreCase(FREE_AGENT))
                            messageDialog.show("You can not name your team Free Agent.");
                        else
                            TeamDialog.this.hide();
                    }
            }
            TeamDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.add(teamNameTextField, 1, 1, 1, 1);
        gridPane.add(teamownerLabel, 0, 3, 1, 1);
        gridPane.add(teamOwnerTextField, 1, 3, 1, 1);
        gridPane.add(completeButton, 0, 4, 1, 1);
        gridPane.add(cancelButton, 1, 4, 1, 1);

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
    
    public Team getTeam() { 
        return team;
    }
    
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @return 
     */
    public Team showAddTeamDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_TEAM_TITLE);
        
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        team = new Team();
        
        // LOAD THE UI STUFF
        teamNameTextField.setText(team.getName());
        teamOwnerTextField.setText(team.getOwner());
        
        // AND OPEN IT UP
        this.showAndWait();
        
        return team;
    }
    
    public void loadGUIData() {
        // LOAD THE UI STUFF
        teamNameTextField.setText(team.getName());
        teamOwnerTextField.setText(team.getOwner());
    }
    
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public void showEditTeamDialog(Team teamToEdit) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_TEAM_TITLE);
        
        // LOAD THE SCHEDULE ITEM INTO OUR LOCAL OBJECT
        team = new Team();
        team.setName(teamToEdit.getName());
        team.setOwner(teamToEdit.getOwner());
        
        // AND THEN INTO OUR GUI
        loadGUIData();
               
        // AND OPEN IT UP
        this.showAndWait();
    }
}
