/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import wdk.data.Draft;
import wdk.gui.MessageDialog;
import static wdk.gui.WDK_GUI.CLASS_HEADING_LABEL;
import static wdk.gui.WDK_GUI.CLASS_PROMPT_LABEL;
import static wdk.gui.WDK_GUI.PRIMARY_STYLE_SHEET;

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
    TextField nameTextField;
    Label dateLabel;
    DatePicker datePicker;
    Label teamownerLabel;
    TextField teamownerTextField;
    Button completeButton;
    Button cancelButton;
    
    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;
    
    // CONSTANTS FOR OUR UI
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String DESCRIPTION_PROMPT = "Description: ";
    public static final String DATE_PROMPT = "Date";
    public static final String TEAMOWNER_PROMPT = "TEAMOWNER";
    public static final String SCHEDULE_ITEM_HEADING = "Schedule Item Details";
    public static final String ADD_SCHEDULE_ITEM_TITLE = "Add New Schedule Item";
    public static final String EDIT_SCHEDULE_ITEM_TITLE = "Edit Schedule Item";
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
        nameLabel = new Label(DESCRIPTION_PROMPT);
        nameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        nameTextField = new TextField();
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
           // scheduleItem.setDescription(newValue);
        });
        
        
        // AND THE TEAMOWNER
        teamownerLabel = new Label(TEAMOWNER_PROMPT);
        teamownerLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        teamownerTextField = new TextField();
        teamownerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
          //  scheduleItem.setLink(newValue);
        });
        
        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);
        
        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button)ae.getSource();
            TeamDialog.this.selection = sourceButton.getText();
            TeamDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.add(nameTextField, 1, 1, 1, 1);
        gridPane.add(dateLabel, 0, 2, 1, 1);
        gridPane.add(datePicker, 1, 2, 1, 1);
        gridPane.add(teamownerLabel, 0, 3, 1, 1);
        gridPane.add(teamownerTextField, 1, 3, 1, 1);
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
    
//    public ScheduleItem getScheduleItem() { 
//        return scheduleItem;
//    }
    
    /**
     * This method loads a custom message into the label and
     * then pops open the dialog.
     * 
     * @param message Message to appear inside the dialog.
     */
    public ScheduleItem showAddScheduleItemDialog(LocalDate initDate) {
        // SET THE DIALOG TITLE
        setTitle(ADD_SCHEDULE_ITEM_TITLE);
        
        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        scheduleItem = new ScheduleItem();
        
        // LOAD THE UI STUFF
        nameTextField.setText(scheduleItem.getDescription());
        datePicker.setValue(initDate);
        teamownerTextField.setText(scheduleItem.getLink());
        
        // AND OPEN IT UP
        this.showAndWait();
        
        return scheduleItem;
    }
    
    public void loadGUIData() {
        // LOAD THE UI STUFF
        nameTextField.setText(scheduleItem.getDescription());
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
        scheduleItem.setDescription(itemToEdit.getDescription());
        scheduleItem.setDate(itemToEdit.getDate());
        scheduleItem.setLink(itemToEdit.getLink());
        
        // AND THEN INTO OUR GUI
        loadGUIData();
               
        // AND OPEN IT UP
        this.showAndWait();
    }
}
