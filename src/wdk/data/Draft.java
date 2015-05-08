/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.text.DecimalFormat;
import java.util.Comparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.WDK_StartUpConstants.FREE_AGENT;

/**
 *
 * @author Work
 */
public class Draft {
    private ObservableList<Team> teams;
    private ObservableList<Player> availablePlayers;
    
    
   // private ObservableList<Log> draftLog;
    private String draftName;
    private int numTeams;
    private int totalMoney;
    public Draft(){
        teams = FXCollections.observableArrayList();
        availablePlayers = FXCollections.observableArrayList();
        numTeams=0;
        draftName = "";
        totalMoney = 0;
    }
    public void addTeam(Team t){
        teams.add(t);
        ++numTeams;
    }
    public void removeTeam(String teamToRemove){
     //   Team draft = teams.c
       // teams.remove(team);
        for(int i = 0; i < availablePlayers.size() ; ++i){
            if(availablePlayers.get(i).getFantasyTeam().equals(teamToRemove)){
                availablePlayers.get(i).setFantasyTeam(FREE_AGENT);
                availablePlayers.get(i).setContract(Contract.NONE);
                availablePlayers.get(i).setSalary(0);
                availablePlayers.get(i).setTeamPosition(Position.NONE);
                
            }
        }
        for(int i = 0; i < teams.size() ; ++i){
            if(teams.get(i).getName().equals(teamToRemove))
                teams.remove(i);
        }
        --numTeams;
    }
    public void addPlayer(Player playerToAdd){
        availablePlayers.add(playerToAdd);
        updateEV();
    }
    public Player removePlayer(Player playerToRemove){
        availablePlayers.remove(playerToRemove);
        updateEV();
        return playerToRemove;
    }
    public void addPlayerToTeam(Player player, Team team){
        
    }
    public void removePlayerFromTeam(Player player, Team team){
        
    }
    
    public ObservableList<Player> getAllPlayers(){
        return availablePlayers;
    }
  

    void clearTeams() {
        teams.clear();
    }

    public ObservableList<Team> getTeams() { 
        return teams;
    }
    
    public int getNumTeams(){
        return numTeams;
    }
    
    public String getDraftName(){
        return draftName;
    }
    public void setDraftName(String dn){
        draftName = dn;
    }
    
    public Team getTeam(String name){
        for(int i = 0; i< teams.size(); ++i){
            if(teams.get(i).getName().equals(name))
                return teams.get(i);
        }
        return null;
    }
    
    public void setAvailablePlayers(ObservableList<Player> players){
        availablePlayers = FXCollections.observableArrayList(players);
    }
    
    
    public ObservableList<Player> getAvailablePlayers(){
        
         ObservableList<Player> temp = FXCollections.observableArrayList();
        for (int i = 0; i< getAllPlayers().size(); ++ i){
            Player p = getAllPlayers().get(i);
            if (p.getFantasyTeam().equals(FREE_AGENT) ){
                temp.add(p);
            }
        }
        
        return temp;
    }
    
    
    public ObservableList<Pitcher> getAvailablePitchers(){
        
        ObservableList<Player> temp = FXCollections.observableArrayList(getAvailablePlayers());
                ObservableList<Pitcher> availablePitchers = FXCollections.observableArrayList();


         for (int i = 0; i< temp.size(); ++ i){
            Player p = temp.get(i);
            if (p.getFantasyTeam().equals(FREE_AGENT) && p instanceof Pitcher ){
                availablePitchers.add((Pitcher)p);
            }
        }
         return availablePitchers;
    }
    
    public ObservableList<Hitter> getAvailableHitters(){
        ObservableList<Player> temp = FXCollections.observableArrayList(getAvailablePlayers());
                ObservableList<Hitter> availableHitters = FXCollections.observableArrayList();


         for (int i = 0; i< temp.size(); ++ i){
            Player p = temp.get(i);
            if (p.getFantasyTeam().equals(FREE_AGENT) && p instanceof Hitter ){
                availableHitters.add((Hitter)p);
            }
        }
         return availableHitters;
    }
    
    
    public ObservableList<Player> getAvailablePlayers(PlayerType pT){
        
         ObservableList<Player> temp = FXCollections.observableArrayList(getAvailablePlayers());
                ObservableList<Player> availablePlayers = FXCollections.observableArrayList();


         for (int i = 0; i< temp.size(); ++ i){
            Player p = temp.get(i);
            if (p.getFantasyTeam().equals(FREE_AGENT) ){
                availablePlayers.add(p);
            }
        }
        
        return availablePlayers;
    }
    
    
    public ObservableList<Player> getTeamPlayers (Team t, DraftType d){
        ObservableList<Player> temp = FXCollections.observableArrayList();
        for (int i = 0; i< getAllPlayers().size(); ++ i){
            Player p = getAllPlayers().get(i);
            if (p.getFantasyTeam().equals(t.getName()) && p.getDraftType().equals(d)){
                temp.add(p);
            }
        }
        
        
        return temp;
        
        
    }
    public ObservableList<Player> getTeamPlayers (Team t){
        ObservableList<Player> temp = FXCollections.observableArrayList();
        for (int i = 0; i< getAllPlayers().size(); ++ i){
            Player p = getAllPlayers().get(i);
            if (p.getFantasyTeam().equals(t.getName())){
                temp.add(p);
            }
        }
        return temp;
    }
    
    
    public double getTeamStatTotal(Team t, Stat s){
        if(s.equals(Stat.R))
            return getTotalRuns(t).get();
        else if (s.equals(Stat.HR))
            return getTotalHomeRuns(t).get();
        else if (s.equals(Stat.RBI))
            return getTotalRunsBattedIn(t).get();
        else if (s.equals(Stat.SB))
            return getTotalStolenBases(t).get();
        else if(s.equals(Stat.W))
            return getTotalWins(t).get();
        else if(s.equals(Stat.SV))
            return getTotalSaves(t).get();
        else if(s.equals(Stat.ERA))
            return getTotalEra(t).get();
        else if(s.equals(Stat.WHIP))
            return getTotalWhip(t).get();
        else if(s.equals(Stat.K))
            return getTotalStrikeout(t).get();
        else if(s.equals(Stat.BA))
            return getTotalBA(t).get();
        return -1;
    
    }
    
    
    private SimpleIntegerProperty getTotalRuns(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getRuns();
                    }
                }
                return new SimpleIntegerProperty(total);
            }
    
    private SimpleIntegerProperty getTotalHomeRuns(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getHomeRuns();
                    }
                }
                return new SimpleIntegerProperty(total);
            }
    
    private SimpleIntegerProperty getTotalRunsBattedIn(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getRunsBattedIn();
                    }
                }
                return new SimpleIntegerProperty(total);
            }
    
    private SimpleIntegerProperty getTotalStolenBases(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t, DraftType.STARTING);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getStolenBases();
                    }
                }
                return new SimpleIntegerProperty(total);
            }
    
    private SimpleIntegerProperty getTotalWins(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        total += p.getWins();
                    }
                }
                return new SimpleIntegerProperty(total);
            }
    
    private SimpleIntegerProperty getTotalSaves(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher h;
                    if(temp.get(i) instanceof Pitcher){
                        h = (Pitcher)temp.get(i);
                        total += h.getSaves();
                    }
                }
                return new SimpleIntegerProperty(total);
            }
    private SimpleDoubleProperty getTotalEra(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int er = 0;
                int ip = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        er += p.getEarnedRuns();
                        ip += p.getInningsPitched();
                    }
                }
                if(ip == 0)
                    return new SimpleDoubleProperty(0);
                else{
                    double era = Double.parseDouble((new DecimalFormat("#.000").format(9*(er/ip))));
                    return new SimpleDoubleProperty(era);
            }
    }
    private SimpleDoubleProperty getTotalWhip(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int bb = 0;
                int h = 0;
                int ip = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        bb += p.getBasesOnBalls();
                        h += p.getHits();
                        ip += p.getInningsPitched();
                    }
                }
                if(ip == 0)
                    return new SimpleDoubleProperty(0);
                else{
                    double whip = Double.parseDouble((new DecimalFormat("#.000").format(( (bb + h) / ip ) ) ) );
                    return new SimpleDoubleProperty(whip);
                }
        }
    private SimpleDoubleProperty getTotalBA(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int hit = 0;
                int ab = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        hit += h.getHits();
                        ab += h.getAtBat();
                    }
                }
                if(ab == 0)
                    return new SimpleDoubleProperty(0);
                else{
                double ba = Double.parseDouble((new DecimalFormat("#.000").format( hit / ab ) ) );
                return new SimpleDoubleProperty(ba);
            }
    }
    
    
    
    private SimpleIntegerProperty getTotalStrikeout(Team t){
        
                 ObservableList<Player> temp = getTeamPlayers(t);
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher h;
                    if(temp.get(i) instanceof Pitcher){
                        h = (Pitcher)temp.get(i);
                        total += h.getStrikeouts();
                    }
                }
                return new SimpleIntegerProperty(total);
            }

    private void updateTotalMoney() {
        
           ObservableList<Team> teamList = getTeams();
         totalMoney = 0;
        
       for(int i = 0; i < teamList.size() ; ++i){
           if(teamList.get(i).getPlayersNeeded()!=0)
              totalMoney+=teamList.get(i).getSalaryLeft();
       }
    }
    
    
    
    public enum Stat{
        R,
        HR,
        RBI,
        SB,
        W,
        SV,
        ERA,
        WHIP,
        BA,
        K
        
        
        
        
        
    }
    
    public enum PlayerType{
        HITTER,
        PITCHER
    }
    
    public void updateEV(){
        updateTotalMoney();
        updateRank();
        
//        ObservableList<Hitter> hitterList = getAvailableHitters();
//        ObservableList<Pitcher> pitcherList = getAvailablePitchers();
        ObservableList<Player> playerList = getAvailablePlayers();
        for(int i = 0; i< playerList.size(); ++i){
            Player p = playerList.get(i);
            p.setEV((totalMoney/p.getAverageRank()));
            
       }
    }
    
    public void updateRank(){
        ObservableList<Hitter> hitterList = getAvailableHitters();
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                if(o1.getRuns()<o2.getRuns())
                    return -1;
                else if (o1.getRuns() > o2.getRuns())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setRRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                if(o1.getHomeRuns()<o2.getHomeRuns())
                    return -1;
                else if (o1.getHomeRuns() > o2.getHomeRuns())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setHRRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                if(o1.getRunsBattedIn()<o2.getRunsBattedIn())
                    return -1;
                else if (o1.getRunsBattedIn() > o2.getRunsBattedIn())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setRBIRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                if(o1.getStolenBases()<o2.getStolenBases())
                    return -1;
                else if (o1.getStolenBases() > o2.getStolenBases())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setSBRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                if(o1.battingAverageProperty().get()<o2.battingAverageProperty().get())
                    return -1;
                else if (o1.battingAverageProperty().get()> o2.battingAverageProperty().get())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setBARank(i);
       }
       
       
       
        ObservableList<Pitcher> pitcherList = getAvailablePitchers();
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                if(o1.getWins()<o2.getWins())
                    return -1;
                else if (o1.getWins() > o2.getWins())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setWRank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                if(o1.getSaves()<o2.getSaves())
                    return -1;
                else if (o1.getWins() > o2.getWins())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setSVRank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                if(o1.getStrikeouts()<o2.getStrikeouts())
                    return -1;
                else if (o1.getStrikeouts() > o2.getStrikeouts())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setKRank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                if(o1.estimatedValueProperty().get()<o2.estimatedValueProperty().get())
                    return -1;
                else if (o1.estimatedValueProperty().get() > o2.estimatedValueProperty().get())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setERARank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                if(o1.whipProperty().get()<o2.whipProperty().get())
                    return -1;
                else if (o1.whipProperty().get()> o2.whipProperty().get())
                    return 1;
                else
                    return 0;
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setWHIPRank(i);
       }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    }
}
