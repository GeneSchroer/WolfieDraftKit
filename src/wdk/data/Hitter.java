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
    private IntegerProperty rRank;
    private IntegerProperty hrRank;
    private IntegerProperty rbiRank;
    private IntegerProperty sbRank;
    private IntegerProperty baRank;
    public Hitter(){
        this(null);
    }
    
    public Hitter(Player h){
        super();
        atBat           = new SimpleIntegerProperty(0);
        runs            = new SimpleIntegerProperty(0);
        hits            = new SimpleIntegerProperty(0);
        homeRuns        = new SimpleIntegerProperty(0);
        runsBattedIn    = new SimpleIntegerProperty(0);
        stolenBases     = new SimpleIntegerProperty(0);
        battingAverage  = new DoubleBinding(){
            {super.bind(hits, atBat);}
            @Override
            protected double computeValue() {
                if(atBat.getValue() == 0)
                    return 0;
                else{
                    Double ba = Double.parseDouble(new DecimalFormat("#.###").format((double)hits.getValue()/(double)atBat.getValue())); 
                    return ba;
                }}};
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
        rRank = new SimpleIntegerProperty();
        hrRank= new SimpleIntegerProperty();
        rbiRank= new SimpleIntegerProperty();
        sbRank= new SimpleIntegerProperty();
        baRank= new SimpleIntegerProperty();
        averageRank.bind(new DoubleBinding(){
            {super.bind(rRank,hrRank,rbiRank,sbRank,baRank);}

            @Override
            protected double computeValue() {
                return (double)(rRank.get() + hrRank.get() + rbiRank.get() + sbRank.get() + baRank.get())/5;
            }});
    }
    
    public void setAtBat(int ab){ atBat.set(ab);}
    
    public int getAtBat(){return atBat.get();}
    
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
        return homeRuns;
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
        return new SimpleDoubleProperty(battingAverage.get());
    }
    
   
    
    @Override
    public void setQualifiedPositions(String pos){
        qualifiedPositions.set(pos);
    }
    
    public int getRRank(){
        return rRank.get();
    }
    public void setRRank(int i){
        rRank .set(i);
    }
    public int getHRRank(){
        return hrRank.get();
    }
    public void setHRRank(int i){
        hrRank.set(i);
    }
    public int getRBIank(){
        return rbiRank.get();
    }
    public void setRBIRank(int i){
        rbiRank .set(i);
    }
    public int getSBRank(){
        return sbRank.get();
    }
    public void setSBRank(int i){
        sbRank.set( i);
    }
    public int getBARank(){
        return baRank.get();
    }
    public void setBARank(int i){
        baRank .set(i);
    }
   
    
}    
