/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import static wdk.GeneralPropertyType.*;
import static wdk.WDK_StartUpConstants.PATH_DRAFTS;
import wdk.data.Draft;
import wdk.data.DraftDataManager;
import wdk.error.ErrorHandler;
import wdk.file.DraftExporter;
import wdk.file.DraftFileManager;
import wdk.gui.MessageDialog;
import wdk.gui.WDK_GUI;
import wdk.gui.YesNoCancelDialog;

/**
 *
 * @author Work
 */
public class FileController {
    private enum screen {ADD, EDIT};
    private boolean saved;
    private final DraftFileManager draftFileManager;
    private final DraftExporter draftExporter;
    private final ErrorHandler errorHandler;
    private final YesNoCancelDialog yesNoCancelDialog;
    private final MessageDialog messageDialog;
    private final PropertiesManager properties;

    public FileController(MessageDialog messageDialog, YesNoCancelDialog yesNoCancelDialog, DraftFileManager draftFileManager, DraftExporter draftExporter) {
          // NOTHING YET
        saved = true;
        
        // KEEP THESE GUYS FOR LATER
        this.draftFileManager = draftFileManager;
        this.draftExporter = draftExporter;
        
        // BE READY FOR ERRORS
        this.errorHandler = ErrorHandler.getErrorHandler();
        
        // AND GET READY TO PROVIDE FEEDBACK
        this.messageDialog = messageDialog;
        this.yesNoCancelDialog = yesNoCancelDialog;
        properties = PropertiesManager.getPropertiesManager();
    
    }

    public void handleNewDraftRequest(WDK_GUI gui) {
    try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW draft
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                DraftDataManager dataManager = gui.getDataManager();
                dataManager.reset();
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                gui.updateGUI(saved);

                // TELL THE USER THE draft HAS BEEN CREATED
                messageDialog.show(properties.getProperty(NEW_DRAFT_CREATED_MESSAGE));
            }
        } catch (IOException ioe) {
            // SOMETHING WENT WRONG, PROVIDE FEEDBACK
            errorHandler.handleNewdraftError();
        }
    }

    public void handleLoadDraftRequest(WDK_GUI gui) {
     try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO OPEN A draft
            if (continueToOpen) {
                // GO AHEAD AND PROCEED LOADING A draft
                promptToOpen(gui);
            }
        } catch (IOException ioe) {
            // SOMETHING WENT WRONG
            errorHandler.handleLoaddraftError();
        }}

//    public void handleExportDraftRequest(WDK_GUI gui) {
//        // EXPORT THE draft
//        DraftDataManager dataManager = gui.getDataManager();
//        Draft draftToExport = dataManager.getDraft();
//
//        // WE'LL NEED THIS TO LOAD THE EXPORTED PAGE FOR VIEWING
//        String draftURL = draftExporter.getPageURLPath(draftToExport);
//        
//        // NOW GET THE EXPORTER
//        try {
//            
//            // AND EXPORT THE draft
//            
//            draftExporter.exportDraft(draftToExport);
//            
//            // AND THEN OPEN UP THE PAGE IN A BROWSER
//            Stage webBrowserStage = new Stage();
//            WebBrowser webBrowser = new WebBrowser(webBrowserStage, draftURL);
//            webBrowserStage.show();
//        }
//        // WE'LL HANDLE draft EXPORT PROBLEMS AND draft PAGE VIEWING
//        // PROBLEMS USING DIFFERENT ERROR MESSAGES
//        catch (MalformedURLException murle) {
//            errorHandler.handleViewSchedulePageError(draftURL);
//        } catch (Exception ioe) {
//            errorHandler.handleExportDraftError(draftToExport);
//        }
//    }

    public void handleExitRequest(WDK_GUI gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handleExitError();
        }
    }

    public void handleSaveDraftRequest(WDK_GUI gui, Draft draftToSave) {
        if(draftToSave.getDraftName().isEmpty())
            messageDialog.show("This draft needs a name");
        else
            try {
                     // SAVE IT TO A FILE
                     draftFileManager.saveDraft(draftToSave);

                     // MARK IT AS SAVED
                     saved = true;

                     // TELL THE USER THE FILE HAS BEEN SAVED
                     messageDialog.show(properties.getProperty(DRAFT_SAVED_MESSAGE));

                     // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                     // THE APPROPRIATE CONTROLS
                     gui.updateToolbarControls(saved);
                 } catch (IOException ioe) {
                     errorHandler.handleSavedraftError();
                 }
             }
    
     /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * draft, or opening another draft. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is returned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave(WDK_GUI gui) throws IOException{
         // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(properties.getProperty(SAVE_UNSAVED_WORK_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            // SAVE THE draft
            DraftDataManager dataManager = gui.getDataManager();
            draftFileManager.saveDraft(dataManager.getDraft());
            saved = true;
            
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (selection.equals(YesNoCancelDialog.CANCEL)) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    private void promptToOpen(WDK_GUI gui) {
         // AND NOW ASK THE USER FOR THE draft TO OPEN
        FileChooser draftFileChooser = new FileChooser();
        draftFileChooser.setInitialDirectory(new File(PATH_DRAFTS));
        File selectedFile = draftFileChooser.showOpenDialog(gui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
                Draft draftToLoad = gui.getDataManager().getDraft();
                draftFileManager.loadDraft(draftToLoad, selectedFile.getAbsolutePath());
                gui.reloadDraft(draftToLoad);
                saved = true;
                gui.updateGUI(saved);
            } catch (Exception e) {
                ErrorHandler eH = ErrorHandler.getErrorHandler();
                eH.handleLoaddraftError();
            }
        }
    }
    
    
}
