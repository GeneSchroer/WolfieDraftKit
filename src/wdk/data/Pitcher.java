/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Work
 */
public class Pitcher extends Player{
    DoubleProperty inningsPitched;
    IntegerProperty earnedRuns;
    IntegerProperty wins;
    IntegerProperty saves;
    IntegerProperty hits;
    IntegerProperty basesOnBalls;
    IntegerProperty strikeouts;
    
    public Pitcher(){
        
        inningsPitched  = new SimpleDoubleProperty();
        earnedRuns      = new SimpleIntegerProperty();
        wins            = new SimpleIntegerProperty();
        saves           = new SimpleIntegerProperty();
        hits            = new SimpleIntegerProperty();
        basesOnBalls    = new SimpleIntegerProperty();
        strikeouts      = new SimpleIntegerProperty();
        
    }
    
    public void setInningsPitched(double ip){
        inningsPitched.set(ip);
    }
    
    public double getInningsPitched(){
        return inningsPitched.get();
    }
    
    public DoubleProperty inningsPitchedProperty(){
        return inningsPitched;
    }
        
    public void setEarnedRuns(int er){
        earnedRuns.set(er);
    }
    
    public int getEarnedRuns(){
        return earnedRuns.get();
    }
    
    public IntegerProperty earnedRunsProperty(){
        return earnedRuns;
    }
    
    public void setWins(int w){
        wins.set(w);
    }
    
    public int getWins(){
        return wins.get();
    }
    
    public IntegerProperty winsProperty(){
        return wins;
    }
    public void setSaves(int s){
        saves.set(s);
    }
    
    public int getSaves(){
        return saves.get();
    }
    
    public IntegerProperty savesProperty(){
        return saves;
    }
    
    public void setHits(int h){
        hits.set(h);
    }
    
    public int getHits(){
        return hits.get();
    }
    
    public IntegerProperty hitsProperty(){
        return hits;
    }
    
     public void setBasesOnBalls(int bb){
        basesOnBalls.set(bb);
    }
    
    public int getBasesOnBalls(){
        return basesOnBalls.get();
    }
    
    public IntegerProperty basesOnBallsProperty(){
        return basesOnBalls;
    }
    
    public void setStrikeouts(int s){
        strikeouts.set(s);
    }
    
    public int getStrikeouts(){
        return strikeouts.get();
    }
    
    public IntegerProperty strikeoutsProperty(){
        return strikeouts;
    }
    
   
}
