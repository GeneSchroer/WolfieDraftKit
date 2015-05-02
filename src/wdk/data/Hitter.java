/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.text.DecimalFormat;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Work
 */
public class Hitter extends Player{
    
    IntegerProperty atBat;
    IntegerProperty runs;
    IntegerProperty hits;
    IntegerProperty homeRuns;
    IntegerProperty runsBattedIn;
    IntegerProperty stolenBases;
    DoubleBinding battingAverage;
    public Hitter(){
        this(null);
        
    }
    
    public Hitter(Player h){
        
        
        atBat           = new SimpleIntegerProperty(0);
        runs            = new SimpleIntegerProperty(0);
        hits            = new SimpleIntegerProperty(0);
        homeRuns        = new SimpleIntegerProperty(0);
        runsBattedIn    = new SimpleIntegerProperty(0);
        stolenBases     = new SimpleIntegerProperty(0);
        battingAverage  = new DoubleBinding(){
            {
                super.bind(hits, atBat);
            }
            @Override
            protected double computeValue() {
                if(atBat.getValue() == 0)
                    return 0;
                
                Double ba = Double.parseDouble(DecimalFormat.getInstance().format((double)hits.getValue()/atBat.getValue())); 
                return ba;
            }
        };
        if(h!=null){
            setLastName(h.getLastName());
            setFirstName(h.getFirstName());
            setProTeam(h.getProTeam());
            setNotes(h.getNotes());
            setYearOfBirth(h.getYearOfBirth());
            setNationOfBirth(h.getNationOfBirth());
            setFantasyTeam(h.getFantasyTeam());
            setTeamPosition(h.getTeamPosition());
            setDraftType(h.getDraftType());
            setContract(h.getContract());
            setSalary(h.getSalary());
            
            for(int i = 0; i < h.getPositionList().size(); ++i){
                addPosition((Position) h.getPositionList().get(i));
            }
            
        }
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
    public void setHits(int h){
        hits.set(h);
    }
    
    public int getHits(){
        return hits.get();
    }
    
    public IntegerProperty hitsProperty(){
        return hits;
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
    public DoubleProperty battingAverageProperty(){
        return new SimpleDoubleProperty(battingAverage.getValue());
    }
    
    
    public Hitter copy(Hitter h){
        Hitter hitter = new Hitter();
        setProTeam(h.getProTeam());
        setLastName(h.getLastName());
        setFirstName(h.getFirstName());
        getPositionList().addAll(h.getPositionList());
        
        
        return hitter;
    }
    
    @Override
    public void setQualifiedPositions(String pos){
        qualifiedPositions.set(pos);
    }
    
}
