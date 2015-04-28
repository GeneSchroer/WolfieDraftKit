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
public class Pitcher extends Player{
    DoubleProperty inningsPitched;
    IntegerProperty earnedRuns;
    IntegerProperty wins;
    IntegerProperty saves;
    IntegerProperty hits;
    IntegerProperty basesOnBalls;
    IntegerProperty strikeouts;
    DoubleBinding   whip;
    private final DoubleBinding earnedRunAverage;
    public Pitcher(){
       this(null);
    }
    public Pitcher(Player p){
        
        if(p!=null){
            setLastName(p.getLastName());
            setFirstName(p.getFirstName());
            setProTeam(p.getProTeam());
            for(int i = 0; i < p.getPositionList().size(); ++i){
                addPosition((Position) p.getPositionList().get(i));
            }
            
        }
        
        
        inningsPitched  = new SimpleDoubleProperty(0);
        earnedRuns      = new SimpleIntegerProperty(0);
        wins            = new SimpleIntegerProperty(0);
        saves           = new SimpleIntegerProperty(0);
        hits            = new SimpleIntegerProperty(0);
        basesOnBalls    = new SimpleIntegerProperty(0);
        strikeouts      = new SimpleIntegerProperty(0);
        
        earnedRunAverage             = new DoubleBinding(){
            {
                super.bind(earnedRuns, inningsPitched);
            }
            @Override
            protected double computeValue() {
                if(inningsPitched.getValue() == 0)
                    return 0;
                
                Double era = Double.parseDouble(DecimalFormat.getInstance().format(9 * (earnedRuns.getValue() / inningsPitched.getValue())));
                return era;
            }
            
        };
      
        
        whip            = new DoubleBinding(){
            {
                super.bind(basesOnBalls, hits, inningsPitched);
            }
            @Override
            protected double computeValue() {
                if(inningsPitched.getValue() == 0)
                    return 0;
                Double w = Double.parseDouble(DecimalFormat.getInstance().format((basesOnBalls.getValue() + hits.getValue())/inningsPitched.getValue()));
                return w;
            }
        };
        
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
    
    public DoubleProperty whipProperty(){
        return new SimpleDoubleProperty(whip.getValue());
    }
    public DoubleProperty earnedRunAverageProperty(){
        return new SimpleDoubleProperty(earnedRunAverage.getValue());
    }
    
    
   
}
