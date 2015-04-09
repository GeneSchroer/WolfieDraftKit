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
public class Hitter extends Player{
    
    IntegerProperty atBat;
    IntegerProperty runs;
    IntegerProperty homeRuns;
    IntegerProperty runsBattedIn;
    IntegerProperty stolenBases;
    
    public Hitter(){
        super();
    }
    
    public void setAtBat(int ab){
        atBat.set(ab);
    }
    
    public int getAtBat(){
        return atBat.get();
    }
    
    public IntegerProperty atBatProperty(){
        return atBat;
    }
    public void setRuns(int r){
        runs.set(r);
    }
    
    public int getRuns(){
        return runs.get();
    }
    
    public IntegerProperty runsProperty(){
        return runs;
    }
    public void setHomeRuns(int hr){
        homeRuns.set(hr);
    }
    
    public int getHomeRuns(){
        return homeRuns.get();
    }
    
    public IntegerProperty homeRunsProperty(){
        return runs;
    }
    
    public void setRunsBattedIn(int rbi){
        runsBattedIn.set(rbi);
    }
    
    public int getRunsBattedIn(){
        return runsBattedIn.get();
    }
    
    public IntegerProperty runsBattedInProperty(){
        return runsBattedIn;
    }
    public void setStolenBases(int sb){
        stolenBases.set(sb);
    }
    
    public int getStolenBases(){
        return stolenBases.get();
    }
    
    public IntegerProperty stolenBasesProperty(){
        return stolenBases;
    }
}
