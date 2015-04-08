/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Work
 */
public class Pitcher extends Player{
    IntegerProperty wins;
    IntegerProperty strikeouts;
    IntegerProperty saves;
    DoubleProperty earnedRunAverage;
    DoubleProperty whip;
    
    public Pitcher(){
        
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
    public void setStrikeouts(int s){
        strikeouts.set(s);
    }
    
    public int getStrikeouts(){
        return strikeouts.get();
    }
    
    public IntegerProperty strikeoutsProperty(){
        return strikeouts;
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
    public void setEarnedRunAverage(double era){
        earnedRunAverage.set(era);
    }
    
    public double getEarnedRunAverage(){
        return earnedRunAverage.get();
    }
    
    public DoubleProperty earnedRunAverageProperty(){
        return earnedRunAverage;
    }
    public void setWhip(double w){
        whip.set(w);
    }
    
    public double getWhip(){
        return whip.get();
    }
    
    public DoubleProperty whipProperty(){
        return whip;
    }

}
