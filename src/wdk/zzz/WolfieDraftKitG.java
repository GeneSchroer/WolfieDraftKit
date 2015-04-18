/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.zzz;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wdk.GeneralPropertyType.PROP_APP_TITLE;
import static wdk.WDK_StartUpConstants.PATH_DATA;
import static wdk.WDK_StartUpConstants.PROPERTIES_FILE_NAME;
import static wdk.WDK_StartUpConstants.PROPERTIES_SCHEMA_FILE_NAME;
import wdk.data.DraftDataManager;
import wdk.error.ErrorHandler;
import wdk.file.JsonDraftFileManager;
import wdk.gui.WDK_GUI;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author Work
 */
public class WolfieDraftKitG {
   
/**
 * draftSiteBuilder is a JavaFX application that can be used to build the
 * pages for a draft Web site. The CSE 219 draft's schedule page is
 * one such page: http://www.cs.stonybrook.edu/~cse219/Section02/schedule.html
 * 
 * @author Richard McKenna
 */
public class WolfieDraftKit extends Application {
    // THIS IS THE FULL USER INTERFACE, WHICH WILL BE INITIALIZED
    // AFTER THE PROPERTIES FILE IS LOADED
   WDK_GUI gui;

    /**
     * This is where our Application begins its initialization, it will
     * create the GUI and initialize all of its components.
     * 
     * @param primaryStage This application's window.
     */
    @Override
    public void start(Stage primaryStage) {
        // LET'S START BY GIVING THE PRIMARY STAGE TO OUR ERROR HANDLER
        ErrorHandler eH = ErrorHandler.getErrorHandler();
        eH.initMessageDialog(primaryStage);
        
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
        boolean success = loadProperties();
        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(PROP_APP_TITLE);
            try {                
                // WE WILL SAVE OUR draft DATA USING THE JSON FILE
                // FORMAT SO WE'LL LET THIS OBJECT DO THIS FOR US
                JsonDraftFileManager jsonFileManager = new JsonDraftFileManager();
                
                // AND THIS ONE WILL DO THE draft WEB PAGE EXPORTING
                //DraftExporter exporter = new DraftExporter(PATH_BASE, PATH_SITES);
                
               // Draft startingDraft = jsonFileManager.loadStartingDraft(JSON_FILE_PATH_HITTERS, JSON_FILE_PATH_PITCHERS);
                
                
                // AND NOW GIVE ALL OF THIS STUFF TO THE GUI
                // INITIALIZE THE USER INTERFACE COMPONENTS
                gui = new WDK_GUI(primaryStage);
                
                gui.setDraftFileManager(jsonFileManager);
                //gui.setDraftExporter(exporter);
                
                // CONSTRUCT THE DATA MANAGER AND GIVE IT TO THE GUI
               // DraftDataManager dataManager = new DraftDataManager(gui, startingDraft); 
                //gui.setDraftDataManager(dataManager);

                // FINALLY, START UP THE USER INTERFACE WINDOW AFTER ALL
                // REMAINING INITIALIZATION
                gui.initGUI(appTitle);                
            }
            catch(IOException ioe) {
                eH = ErrorHandler.getErrorHandler();
                eH.handlePropertiesFileError();
            }
        }
    }
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }        
    }

    /**
     * This is where program execution begins. Since this is a JavaFX app
     * it will simply call launch, which gets JavaFX rolling, resulting in
     * sending the properly initialized Stage (i.e. window) to our start
     * method in this class.
     */
//    public static void main(String[] args) {
//        Locale.setDefault(Locale.US);
//        launch(args);
//    }
}

}
