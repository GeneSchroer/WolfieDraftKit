/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static wdk.WDK_StartUpConstants.FREE_AGENT;

/**
 *
 * @author Work
 */
public class Draft {
    private ObservableList<Team> teams;
    private ObservableList<Player> allPlayers;
    
    
   // private ObservableList<Log> draftLog;
    private String draftName;
    private int numTeams;
    private int totalMoney;
    public Draft(){
        teams = FXCollections.observableArrayList();
        allPlayers = FXCollections.observableArrayList();
        numTeams=0;
        draftName = "";
        totalMoney = 0;
    }
    public void addTeam(Team t){
        teams.add(t);
        ++numTeams;
        updateEV();
    }
    public void removeTeam(String teamToRemove){
     //   Team draft = teams.c
       // teams.remove(team);
        for(int i = 0; i < allPlayers.size() ; ++i){
            if(allPlayers.get(i).getFantasyTeam().equals(teamToRemove)){
                allPlayers.get(i).setFantasyTeam(FREE_AGENT);
                allPlayers.get(i).setContract(Contract.NONE);
                allPlayers.get(i).setSalary(0);
                allPlayers.get(i).setTeamPosition(Position.NONE);
                
            }
        }
        for(int i = 0; i < teams.size() ; ++i){
            if(teams.get(i).getName().equals(teamToRemove))
                teams.remove(i);
        }
        --numTeams;
        updateEV();
    }
    public void addFreePlayer(Player playerToAdd){
        allPlayers.add(playerToAdd);
        playerToAdd.setFantasyTeam(FREE_AGENT);
        updateEV();
    }
    public Player removeFreePlayer(Player playerToRemove){
        allPlayers.remove(playerToRemove);
        updateEV();
        return playerToRemove;
    }
    public void addTeamPlayer(Player player, Team team, Position pos) throws Exception{
        team.addPlayer(player, pos);
        updateEV();
    }
    
    public void editTeamPlayer(Player p, Team team, DraftType newDraft, double newSalary, Contract newContract, Position newPosition)
        {
            team.editTeamPlayer(p, newDraft, newSalary, newContract, newPosition);
            updateEV();
        }
    
    public void removeTeamPlayer(Player player, Team team) throws Exception{
        team.removePlayer(player);
        updateEV();
    }
    
    public ObservableList<Player> getAllPlayers(){
        return allPlayers;
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
  
    
    public void updateEV(){
        updateTotalMoney();
        updateRank();
        updateTotalPoints();
//        ObservableList<Hitter> hitterList = getAvailableHitters();
//        ObservableList<Pitcher> pitcherList = getAvailablePitchers();
        ObservableList<Player> playerList = getAvailablePlayers();
        for(int i = 0; i< playerList.size(); ++i){
            Player p = playerList.get(i);
            if(p.getAverageRank() == 0)
                p.setEV(0);
            else
                p.setEV((totalMoney/p.getAverageRank()));
            
       }
    }
    
    public void updateRank(){
        ObservableList<Hitter> hitterList = getAvailableHitters();
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                Integer i1 = o1.getRuns();
                Integer i2 = o2.getRuns();
                return -(i1.compareTo(i2));
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setRRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                Integer i1 = o1.getHomeRuns();
                Integer i2 = o2.getHomeRuns();
                return -( i1.compareTo(i2));
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setHRRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){
            @Override
            public int compare(Hitter o1, Hitter o2) {
                Integer i1 = o1.getRunsBattedIn();
                Integer i2 = o2.getRunsBattedIn();
                return -(i1.compareTo(i2));
            }
         });
         
         
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setRBIRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
            Integer i1 = o1.getStolenBases();
                Integer i2 = o2.getStolenBases();
                return -(i1.compareTo(i2)); 
            }
         });
       for(int i = 0; i< hitterList.size(); ++i){
           Hitter h = hitterList.get(i);
           h.setSBRank(i);
       }
         hitterList.sort(new Comparator<Hitter>(){

            @Override
            public int compare(Hitter o1, Hitter o2) {
                Double i1 = o1.battingAverageProperty().get();
                Double i2 = o2.battingAverageProperty().get();
                return -(i1.compareTo(i2));
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
                Integer i1 = o1.getWins();
                Integer i2 = o2.getWins();
                return -(i1.compareTo(i2));
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setWRank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                 Integer i1 = o1.getSaves();
                Integer i2 = o2.getSaves();
                return -(i1.compareTo(i2));
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setSVRank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                Integer i1 = o1.getStrikeouts();
                Integer i2 = o2.getStrikeouts();
                return -(i1.compareTo(i2));
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setKRank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                Double i1 = o1.earnedRunAverageProperty().get();
                Double i2 = o2.earnedRunAverageProperty().get();
                return (i1.compareTo(i2));
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setERARank(i);
       }
        pitcherList.sort(new Comparator<Pitcher>(){

            @Override
            public int compare(Pitcher o1, Pitcher o2) {
                Double i1 = o1.whipProperty().get();
                Double i2 = o2.whipProperty().get();
                return (i1.compareTo(i2));
            }
         });
       for(int i = 0; i< pitcherList.size(); ++i){
           Pitcher h = pitcherList.get(i);
           h.setWHIPRank(i);
       }
       
       
    }
    
    public void updateTotalPoints(){
        
       
                for(int z = 0; z<teams.size(); ++z){
                    Team currentTeam = teams.get(z);
                    double totalPoints = 0;
                    ObservableList<Player> temp = getTeamPlayers(currentTeam);




                    int yourR       = currentTeam.totalRProperty().get();
                    int yourHR      = currentTeam.totalHRProperty().get();
                    int yourRBI     = currentTeam.totalRBIProperty().get();
                    int yourSB      = currentTeam.totalSBProperty().get();
                    int yourW       = currentTeam.totalWProperty().get();                
                    int yourSV      = currentTeam.totalSVProperty().get();
                    double yourERA  = currentTeam.totalERAProperty().get();
                    double yourWHIP = currentTeam.totalWHIPProperty().get();
                    int yourK       = currentTeam.totalKProperty().get();
                    double yourBA   = currentTeam.totalBAProperty().get();

                    int equalR      = 1;
                    int equalHR     = 1;
                    int equalRBI    = 1;
                    int equalSB     = 1;
                    int equalW      = 1;
                    int equalSV     = 1;
                    int equalERA    = 1;
                    int equalWHIP   = 1;
                    int equalK      = 1;
                    int equalBA     = 1;

                    double pointsFromR = teams.size();
                    double pointsFromHR = teams.size();
                    double pointsFromRBI = teams.size();
                    double pointsFromSB = teams.size();
                    double pointsFromW = teams.size();
                    double pointsFromSV = teams.size();
                    double pointsFromERA= teams.size();
                    double pointsFromWHIP = teams.size();
                    double pointsFromK = teams.size();
                    double pointsFromBA = teams.size();

                    for(int i = 0; i < teams.size(); ++i){

                        if(teams.get(i) != currentTeam){
    //
                        int theirR      = teams.get(i).totalRProperty().get();
                    int theirHR     = teams.get(i).totalHRProperty().get();
                    int theirRBI    = teams.get(i).totalRBIProperty().get();
                    int theirSB     = teams.get(i).totalSBProperty().get();
                    int theirW      = teams.get(i).totalWProperty().get();
                    int theirSV     = teams.get(i).totalSVProperty().get();
                    double theirERA = teams.get(i).totalERAProperty().get();
                    double theirWHIP = teams.get(i).totalWHIPProperty().get();
                    int theirK      = teams.get(i).totalKProperty().get();
                    double theirBA  = teams.get(i).totalBAProperty().get();
                    
                    if(theirR>yourR)
                        --pointsFromR;
                    else if(theirR == yourR)
                        ++equalR;
                    if(theirHR > yourHR)
                        --pointsFromHR;
                    else if(theirHR == yourHR)
                        ++equalHR;
                    if(theirRBI>yourRBI)
                        --pointsFromRBI;
                    else if(theirRBI == yourRBI)
                        ++equalRBI;
                    if(theirSB>yourSB)
                        --pointsFromSB;
                    else if(theirSB == yourSB)
                        ++equalSB;
                    if(theirW>yourW)
                        --pointsFromW;
                    else if(theirW == yourW)
                        ++equalW;
                    if(theirSV>yourSV)
                        --pointsFromSV;
                    else if(theirSV == yourSV)
                        ++equalSV;
                    if(theirERA>yourERA)
                        --pointsFromERA;
                    else if(theirERA == yourERA)
                        ++equalERA;
                    if(theirWHIP>yourWHIP)
                        --pointsFromWHIP;
                    else if(theirWHIP == yourWHIP)
                        ++equalWHIP;
                    if(theirK>yourK)
                        --pointsFromK;
                    else if(theirK == yourK)
                        ++equalK;
                    if(theirBA>yourBA)
                        --pointsFromBA;
                    else if(theirBA == yourBA)
                        ++equalBA;
                    }
                }
                totalPoints += (pointsFromR/equalR);
                totalPoints += (pointsFromHR/equalHR);
                totalPoints += (pointsFromRBI/equalRBI);
                totalPoints += (pointsFromSB/equalSB);
                totalPoints += (pointsFromW/equalW);
                totalPoints += (pointsFromSV/equalSV);
                totalPoints += (pointsFromERA/equalERA);
                totalPoints += (pointsFromWHIP/equalWHIP);
                totalPoints += (pointsFromK/equalK);
                totalPoints += (pointsFromBA/equalBA);
                currentTeam.setTotalPoints(totalPoints);
            }
                //Buckle up kids!
                 
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

