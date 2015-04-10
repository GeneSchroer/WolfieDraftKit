/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.controller;

import wdk.gui.WDK_GUI;

/**
 *
 * @author Work
 */
public class DraftEditController {
    private boolean enabled;
    
    /**
     * Constructor that gets this controller ready, not much to
     * initialize as the methods for this function are sent all
     * the objects they need as arguments.
     */
    public DraftEditController(){
        enabled = true;
    }
    
    /**
     * This controller function is called in response to the user changing
     * course details in the UI. It responds by updating the bound Course
     * object using all the UI values, including the verification of that
     * data.
     * 
     * @param gui The user interface that requested the change.
     */
    public void handleDraftChangeRequest(WDK_GUI gui){
        
    }
    /**
     * This mutator method lets us enable or disable this controller.
     * 
     * @param enableSetting If false, this controller will not respond to
     * Course editing. If true, it will.
     */
    public void enable(boolean enableSetting) {
        enabled = enableSetting;
    }
    
}
