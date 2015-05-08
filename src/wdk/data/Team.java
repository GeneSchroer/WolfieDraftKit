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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.WDK_StartUpConstants.FREE_AGENT;

/**
 *
 * @author Work
 */
public class Team {
    
    private final int MAXPLAYERS = 23;
    
    private IntegerProperty playersNeeded;
    private StringProperty name;
    private StringProperty owner;
    private ObservableList<Position> positionsList;
    private DoubleProperty salaryLeft;
    private DoubleProperty pointsPerPlayer;
    
    private IntegerProperty totalR;
    private IntegerProperty totalHR;
    private IntegerProperty totalRBI;
    private IntegerProperty totalSB;
    private IntegerProperty totalHH;
    private IntegerProperty totalAB;
    
    private DoubleProperty totalBA;
    private IntegerProperty totalW;
    private IntegerProperty totalSV;
     private IntegerProperty totalK;
     
     
    private SimpleIntegerProperty totalHP;
    private SimpleDoubleProperty totalIP;
    private SimpleIntegerProperty totalER;
    
    private DoubleProperty totalERA;
    private DoubleProperty totalWHIP;  
    
    private int catchers;
    private int outFielders;
    private int pitchers;
    private int taxi;
    private SimpleIntegerProperty totalBB;
   
    
    public Team(){
        name = new SimpleStringProperty();
        owner = new SimpleStringProperty();
        salaryLeft = new SimpleDoubleProperty(260);
         playersNeeded = new SimpleIntegerProperty(23); 
        pointsPerPlayer = new SimpleDoubleProperty();
        pointsPerPlayer.bind(new DoubleBinding (){
            { super.bind(salaryLeft, playersNeeded); }
            @Override
            protected double computeValue() {
                if((MAXPLAYERS - playersNeeded.getValue()) == 0)
                    return 0;
                else{
                    Double ba = Double.parseDouble(new DecimalFormat("#.###").format(salaryLeft.getValue()/(double)(playersNeeded.getValue()))); 
                    return ba;
                }}});  
        positionsList = FXCollections.observableArrayList();
        catchers = 0;
        outFielders = 0;
        pitchers = 0;
        taxi = 0;
        
      
    totalR = new SimpleIntegerProperty(0);
    totalHR = new SimpleIntegerProperty(0);
    totalRBI = new SimpleIntegerProperty(0);
    totalSB = new SimpleIntegerProperty(0);
    
    totalHH = new SimpleIntegerProperty(0);
    totalAB = new SimpleIntegerProperty(0);
    totalBA = new SimpleDoubleProperty(0);
    totalBA.bind(new DoubleBinding(){
            { super.bind(totalHH, totalAB); }
            @Override
            protected double computeValue() {
                if(totalAB.getValue() == 0)
                    return 0;
                else{
                    Double ba = Double.parseDouble(new DecimalFormat("#.###").format((double)totalHH.getValue()/(double)totalAB.getValue())); 
                    return ba;
                }}});    
    
    totalW = new SimpleIntegerProperty(0);
    totalSV = new SimpleIntegerProperty(0);
    totalK = new SimpleIntegerProperty(0);
    
    totalBB = new SimpleIntegerProperty(0);
    totalER = new SimpleIntegerProperty(0);
    totalIP = new SimpleDoubleProperty(0);
    totalHP = new SimpleIntegerProperty(0);
    
    totalERA = new SimpleDoubleProperty(0);
    totalERA.bind(new DoubleBinding(){
            { super.bind(totalER, totalIP); }
            @Override
            protected double computeValue() {
                if(totalIP.getValue() == 0)
                    return 0;
                Double era = Double.parseDouble(DecimalFormat.getInstance().format(9 * (totalER.getValue() / totalIP.getValue())));
                return era;
            }});
     totalWHIP = new SimpleDoubleProperty(0);
     totalWHIP.bind( new DoubleBinding(){
            {
                super.bind(totalBB, totalHH, totalIP);
            }
            @Override
            protected double computeValue() {
                if(totalIP.getValue() == 0)
                    return 0;
                Double w = Double.parseDouble(DecimalFormat.getInstance().format((totalBB.getValue() + totalHP.getValue())/totalIP.getValue()));
                return w;
            }
        });
    }
    
    
    
    public Team(String name, String owner, ObservableList<Player> startLine, ObservableList<Player> taxiSquad){
        this(name, owner);
        for(int i = 0; i < startLine.size() ; ++i){
            addStartPlayer(startLine.get(i), startLine.get(i).getTeamPosition());
            addToTotals(startLine.get(i));
        }
        for(int i = 0; i < taxiSquad.size() ; ++i){
            addTaxiPlayer(taxiSquad.get(i), taxiSquad.get(i).getTeamPosition());
            addToTotals(taxiSquad.get(i));
        }
        
    }
    
    public Team(String name, String owner, int c, int b1, int ci, int b3, int b2, int mi, int ss, int of, int u, int p, int t){
        this.name = new SimpleStringProperty(name);
        this.owner = new SimpleStringProperty(owner);
        positionsList = FXCollections.observableArrayList();
        
        catchers = c;
        
        if (b1 == 1)
            positionsList.add(Position.B1);
        if (ci == 1)
            positionsList.add(Position.CI);
        if (b3 == 1)
            positionsList.add(Position.B3);
        if (b2 == 1)
            positionsList.add(Position.B2);
        if (mi == 1)
            positionsList.add(Position.MI);
        if (ss == 1)
            positionsList.add(Position.SS);
        if (u ==1)
            positionsList.add(Position.U);
        outFielders = of;
        pitchers = p;
        taxi = t;    
    }
    
    
    public Team(String name, String owner){
        this();
        this.name.set(name);
        this.owner.set(owner);
        
    }
    
    public boolean taxiSquadFilled(){
        return taxi == 11;
    }
    public boolean pitchersFilled(){
        return pitchers  == 9;
    }
    public boolean catchersFilled(){
        return catchers == 2;
    }
    public boolean outFieldersFilled(){
        return outFielders == 5;
    }
    public void setPlayersNeeded(int pn){
        playersNeeded.set(pn);
    }
    
    public int getPlayersNeeded(){
        return playersNeeded.get();
    }
    
    public IntegerProperty playersNeededProperty(){
        return playersNeeded;
    }
    
    public void setName(String n){
        name.set(n);
    }
    
    public String getName(){
        return name.get();
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public void setOwner(String o){
        owner.set(o);
    }
    
    public String getOwner(){
        return owner.get();
    }
    
    public StringProperty ownerProperty(){
        return owner;
    }
    
    public boolean positionFilled(Position pos){
        if(pos.equals(Position.C)){
            return catchersFilled();
        }
        else if(pos.equals(Position.OF)){
            return outFieldersFilled();
        }
        else if(pos.equals(Position.P)){
            return pitchersFilled();
        }
        else{
            return positionsList.contains(pos);
        }
        
        
    }
    
    private void addStartPlayer(Player p, Position pos){
         p.setFantasyTeam(getName());
            p.setTeamPosition(pos);
            p.setDraftType(DraftType.STARTING);
            if(pos.equals(Position.C))
                ++catchers;
            else if(pos.equals(Position.OF))
                ++outFielders;
            else if(pos.equals(Position.P))
                ++pitchers ;
            else
                positionsList.add(pos);
    }
    private void addTaxiPlayer(Player p, Position pos){
         p.setDraftType(DraftType.TAXI);
                p.setFantasyTeam(getName());
                p.setTeamPosition(pos);
                p.setContract(Contract.X);
                p.setSalary(1);
                ++taxi;
    }
    
    public void addPlayer(Player p, Position pos) throws Exception{
        
        
        if(startingLineupFilled())
            if(!taxiSquadFilled()){
               addTaxiPlayer(p, pos);
            
            }
            else
                throw new Exception("This position is full ");
        else{
           addStartPlayer(p, pos);
        }
        
        addToTotals(p);
    }
    public void removePlayer(Player p) throws Exception{
        
//        if(positionFilled(pos))
//            throw new Exception("This position is full ");
//        else{
            if(p.getDraftType().equals(DraftType.TAXI)){
                --taxi;
                p.setFantasyTeam(FREE_AGENT);
                p.setDraftType(DraftType.NONE);
                p.setTeamPosition(Position.NONE);
                p.setContract(Contract.NONE);
                p.setSalary(0);
            }
            else if(p.getDraftType().equals(DraftType.STARTING)){
            
                if(p.getTeamPosition().equals(Position.C))
                    --catchers;
                else if(p.getTeamPosition().equals(Position.OF))
                    --outFielders;
                else if(p.getTeamPosition().equals(Position.P))
                    --pitchers ;
                else
                    positionsList.remove(p.getTeamPosition());
                p.setFantasyTeam(FREE_AGENT);
                p.setDraftType(DraftType.NONE);
                p.setTeamPosition(Position.NONE);
                p.setContract(Contract.NONE);
                p.setSalary(0);
            }
        deductFromTotals(p);
    }
   
    public boolean startingLineupFilled(){
        return positionFilled(Position.C)
                && positionFilled(Position.B1)
                    && positionFilled(Position.CI)
                            && positionFilled(Position.B3)
                                &&positionFilled(Position.B2)
                                    &&positionFilled(Position.MI)
                                        &&positionFilled(Position.SS)
                                            &&positionFilled(Position.OF)
                                            &&positionFilled(Position.U)
                                                &&positionFilled(Position.P);
    }
    
    
    
    public int getPosition(Position pos){
        if(pos.equals(Position.C)){
            return catchers;
        }
        else if(pos.equals(Position.OF)){
            return outFielders;
        }
        else if(pos.equals(Position.P)){
            return pitchers;
        }
        else
            if(positionsList.contains(pos))
                return 1;
        
        return 0;
    }
    
    
    public int getTaxi(){
        return taxi;
    }
    @Override
    public String toString(){
        return getName();
    }
    
    
    private void addToTotals(Player p){
        if(p instanceof Hitter){
            Hitter h = (Hitter)p;
        
        totalR.set(totalR.get() + h.getRuns());
        totalHR.set(totalHR.get() + h.getHomeRuns());
        totalRBI.set(totalRBI.get() + h.getRunsBattedIn());
        totalSB.set(totalSB.get() + h.getStolenBases());
        totalSB.set(totalSB.get() + h.getStolenBases());
        totalHH.set(totalHH.get() + h.getHits());
        totalAB.set(totalAB.get() + h.getAtBat());
                salaryLeft.set(salaryLeft.get() - h.getSalary());
                

        }
        
        else if(p instanceof Pitcher){
            Pitcher pi = (Pitcher) p;
            totalIP.set(totalIP.get() + pi.getInningsPitched());
            totalER.set(totalER.get() + pi.getEarnedRuns());
            totalW.set(totalW.get() + pi.getWins());
            totalSV.set(totalSV.get() + pi.getSaves());
            totalHP.set(totalHP.get() + pi.getHits());
            totalBB.set(totalBB.get() + pi.getBasesOnBalls());
            totalK.set(totalK.get() + pi.getStrikeouts());
                    salaryLeft.set(salaryLeft.get() - pi.getSalary());

        }
        playersNeeded.set(playersNeeded.get() - 1);

    }
  
    private void deductFromTotals(Player p){
        if(p instanceof Hitter){
            Hitter h = (Hitter)p;
        
        totalR.set(totalR.get() - h.getRuns());
        totalHR.set(totalHR.get() - h.getHomeRuns());
        totalRBI.set(totalRBI.get() - h.getRunsBattedIn());
        totalSB.set(totalSB.get() - h.getStolenBases());
        totalSB.set(totalSB.get() - h.getStolenBases());
        totalHH.set(totalHH.get() - h.getHits());
        totalAB.set(totalAB.get() - h.getAtBat());
        }
        
        else if(p instanceof Pitcher){
            Pitcher pi = (Pitcher) p;
            totalIP.set(totalIP.get() - pi.getInningsPitched());
            totalER.set(totalER.get() - pi.getEarnedRuns());
            totalW.set(totalW.get() - pi.getWins());
            totalSV.set(totalSV.get() - pi.getSaves());
            totalHP.set(totalHP.get() - pi.getHits());
            totalBB.set(totalBB.get() - pi.getBasesOnBalls());
            totalK.set(totalK.get() - pi.getStrikeouts());
        }
                playersNeeded.set(playersNeeded.get() + 1);
                salaryLeft.set(salaryLeft.get() + p.getSalary());

    }
//    
//    public double getTotalIP(){
//        return totalIP.get();
//    }
    
    public DoubleProperty totalWHIPProperty(){
        return totalWHIP;
    }
    public IntegerProperty totalRProperty(){
        return totalR;
    }
    public IntegerProperty totalHRProperty(){
        return totalHR;
    }
    public IntegerProperty totalRBIProperty(){
        return totalRBI;
    }
    public IntegerProperty totalSBProperty(){
        return totalSB;
    }
    public IntegerProperty totalHHProperty(){
        return totalHH;
    }
    public IntegerProperty totalABProperty(){
        return totalAB;
    }
    public DoubleProperty totalBAProperty(){
        return totalBA;
    }
    public IntegerProperty totalWProperty(){
        return totalW;
    }
    public IntegerProperty totalSVProperty(){
        return totalSV;
    }
    public IntegerProperty totalKProperty(){
        return totalK;
    }
    public IntegerProperty totalHPProperty(){
        return totalHP;
    }
    public DoubleProperty totalERAProperty(){
        return new SimpleDoubleProperty(totalERA.get());
    }
   
    public DoubleProperty salaryLeftProperty(){
        return salaryLeft;
    }
    public DoubleProperty pointsPerPlayerProperty(){
        return pointsPerPlayer;
    }
    
    
    public void editTeamPlayer(Player p, DraftType newDraft, double newSalary, Contract newContract, Position newPosition){
        if(!p.getFantasyTeam().equals(name)){
            //Do Nothing
            
        }
        else{
                if(newDraft.equals(DraftType.TAXI)){
                    //Salary and Contract set to 1 and X
                    salaryLeft.set(salaryLeft.get() + p.getSalary() -1);
                    p.setDraftType(DraftType.TAXI);
                    p.setSalary(1);
                    p.setTeamPosition(newPosition);
                }
                else{
                    salaryLeft.set(salaryLeft.get() + p.getSalary() - newSalary);
                    p.setSalary(newSalary);
                    p.setContract(newContract);
                    if(p.getTeamPosition().equals(Position.C))
                        --catchers;
                    else if(p.getTeamPosition().equals(Position.OF))
                        --outFielders;
                    else if(p.getTeamPosition().equals(Position.P))
                        --pitchers ;
                    else
                        positionsList.remove(p.getTeamPosition());
                
                    
                    if(newPosition.equals(Position.C))
                        ++catchers;
                    else if(newPosition.equals(Position.OF))
                        ++outFielders;
                    else if(newPosition.equals(Position.P))
                        ++pitchers ;
                    else
                        positionsList.add(newPosition);
                    
                    p.setTeamPosition(newPosition);
                    

                }
        }
        
        
    }
    public double getSalaryLeft(){
        return salaryLeft.get();
    }
    
}
