/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui;

import javafx.scene.layout.Pane;

/**
 *
 * @author Work
 */
public interface MenuScreen {
   void initWorkspace();
   void initTable();
   void initUIControls();
   Pane getScreen();
}
