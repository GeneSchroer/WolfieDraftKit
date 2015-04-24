/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.data.Draft;
import wdk.data.Player;

/**
 *
 * @author Work
 */
public class PlayerDialog extends Stage{
    
     Player player;
    
    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
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
    
    
    
    
    Button completeButton;
    Button cancelButton;
    private final GridPane gridPane;
    private final Label headingLabel;
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    private static final String PLAYER_DETAILS_HEADING = "Player Details";
//    private static final String PLAYER_DETAILS_HEADING = "Player Details";
//    private static final String PLAYER_DETAILS_HEADING = "Player Details";

    public PlayerDialog(Stage primaryStage, Draft draft, MessageDialog initMessageDialog) {
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        
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
        firstNameLabel = new Label(FIRSTNAME_PROMPT);
        firstNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        firstNameTextField = new TextField();
        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
//            scheduleItem.setFirstName(newValue);
        });
        
        // AND THE DATE

        catcherCheckBox = new CheckBox("C");
        catcherCheckBox.setOnAction(e->{
            
        });
        
        
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            PlayerDialog.this.selection = sourceButton.getText();
            PlayerDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(firstNameLabel, 0, 1, 1, 1);
        gridPane.add(firstNameTextField, 1, 1, 1, 1);
        gridPane.add(lastNameLabel, 0, 2, 1, 1);
        gridPane.add(lastNameTextField, 1, 2, 1, 1);
        gridPane.add(catcherCheckBox, 0, 3, 1, 1);
        gridPane.add(firstBasemanCheckBox, 1, 3, 1, 1);
        gridPane.add(thirdBasemanCheckBox, 2, 3, 1, 1);
        gridPane.add(secondBasemanCheckBox, 3, 3, 1, 1);
        gridPane.add(shortstopCheckBox, 4, 3, 1, 1);
        gridPane.add(outFielderCheckBox, 5, 3, 1, 1);
        gridPane.add(pitcherCheckBox, 6, 3, 1, 1);
        gridPane.add(completeButton, 0, 7, 1, 1);
        gridPane.add(cancelButton, 1, 7, 1, 1);

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
    
//    public ScheduleItem getScheduleItem() { 
//        return scheduleItem;
//    }
    
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @param message Message to appear inside the dialog.
     */
    public Player showAddPlayerDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_PLAYER_TITLE);
        
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        player = new Player();
        
        // LOAD THE UI STUFF
        firstNameTextField.setText(player.getFirstName());
        
        
        
        teamownerTextField.setText(scheduleItem.getLink());
        
        // AND OPEN IT UP
        this.showAndWait();
        
        return scheduleItem;
    }
    
    public void loadGUIData() {
        // LOAD THE UI STUFF
        nameTextField.setText(scheduleItem.getFirstName());
        datePicker.setValue(scheduleItem.getDate());
        teamownerTextField.setText(scheduleItem.getLink());       
    }
    
    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);
    }
    
    public void showEditScheduleItemDialog(ScheduleItem itemToEdit) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_SCHEDULE_ITEM_TITLE);
        
        // LOAD THE SCHEDULE ITEM INTO OUR LOCAL OBJECT
        scheduleItem = new ScheduleItem();
        scheduleItem.setFirstName(itemToEdit.getFirstName());
        scheduleItem.setDate(itemToEdit.getDate());
        scheduleItem.setLink(itemToEdit.getLink());
        
        // AND THEN INTO OUR GUI
        loadGUIData();
               
        // AND OPEN IT UP
        this.showAndWait();
    }
}
