/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.gui.fantasy_standings_screen;

import java.text.DecimalFormat;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import wdk.data.Draft;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;
import wdk.data.Team;

/**
 *
 * @author Work
 */
public class TeamStatsTable {
    
    private TableView<Team> teamsTable;
    private TableColumn<Team, String> teamNameColumn;
    private TableColumn playersNeededColumn;
    private TableColumn moneyLeftColumn;
    private TableColumn perPlayerColumn;
    private TableColumn totalRunsColumn;
    private TableColumn totalHomeRunsColumn;
    private TableColumn totalRunsBattedInColumn;
    private TableColumn totalStolenBasesColumn;
    private TableColumn totalWinsColumn;
    private TableColumn totalSavesColumn;
    private TableColumn totalEraColumn;
    private TableColumn totalWhipColumn;
    private TableColumn totalPointsColumn;
    Draft draft;
    private TableColumn totalStrikeoutsColumn;
    private TableColumn totalBattingAverageColumn;
    private Callback playersNeeded;
    
    
    
public TeamStatsTable(Draft draft){
        this.draft = draft;
        initTable();
        
        
        
        
        //Collections.sort(playerList);
    }
// NOW SETUP THE TABLE COLUMNS
    private void initTable() {    
        teamsTable                  = new TableView();
        teamNameColumn              = new TableColumn("Team Name");
        playersNeededColumn         = new TableColumn("Players Needed");
        moneyLeftColumn             = new TableColumn("Money Left");
        perPlayerColumn             = new TableColumn("PP");
        totalRunsColumn             = new TableColumn("R");
        totalHomeRunsColumn         = new TableColumn("HR");
        totalRunsBattedInColumn     = new TableColumn("RBI");
        totalStolenBasesColumn      = new TableColumn("SB");
        totalWinsColumn             = new TableColumn("W");
        totalSavesColumn            = new TableColumn("SV");
        totalEraColumn              = new TableColumn("ERA");
        totalWhipColumn             = new TableColumn("WHIP");
        totalStrikeoutsColumn       = new TableColumn("K");
        totalBattingAverageColumn   = new TableColumn("BA");
        totalPointsColumn           = new TableColumn("Total Points");
        
        playersNeeded = new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){

          

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                IntegerProperty p = new SimpleIntegerProperty(23 - draft.getTeamPlayers(param.getValue()).size());
                return p.asObject();
            }
            
        };
        
        
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        playersNeededColumn.setCellValueFactory(playersNeeded);
       
        moneyLeftColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Player p;
                        p = temp.get(i);
                        total += p.getSalary();
                }
                return new SimpleIntegerProperty(260 - total).asObject();
            }
        });
        perPlayerColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Double>, ObservableValue<Double>>(){
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Team, Double> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                if(temp.isEmpty())
                    return new SimpleDoubleProperty(0).asObject();
                else{
                    double total = 0;
                    for(int i = 0; i < temp.size() ; ++i){
                        Player p;
                            p = temp.get(i);
                            total += p.getSalary();
                        }
                    DecimalFormat df = new DecimalFormat("#.00");
                    double pp = Double.valueOf(new DecimalFormat("#.00").format((260-total)/temp.size()));
                    return new SimpleDoubleProperty(pp).asObject();
                }
            }
        });
        
        
         totalRunsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getRuns();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalHomeRunsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getHomeRuns();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalRunsBattedInColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getRunsBattedIn();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalStolenBasesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Hitter h;
                    if(temp.get(i) instanceof Hitter){
                        h = (Hitter)temp.get(i);
                        total += h.getStolenBases();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalWinsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        total += p.getWins();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalSavesColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        total += p.getSaves();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalEraColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        total += p.earnedRunAverageProperty().get();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        totalWhipColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){
                
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                int total = 0;
                for(int i = 0; i < temp.size() ; ++i){
                    Pitcher p;
                    if(temp.get(i) instanceof Pitcher){
                        p = (Pitcher)temp.get(i);
                        total += p.whipProperty().get();
                    }
                }
                return new SimpleIntegerProperty(total).asObject();
            }
            
        });
        
    totalPointsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Double>, ObservableValue<Double>>(){
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Team, Double> param) {
                ObservableList<Team> teams = param.getTableView().getItems();
                double totalPoints = 0;
                ObservableList<Player> temp = draft.getTeamPlayers(param.getValue());
                
                int yourR = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.R);
                int yourHR = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.HR);
                int yourRBI = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.RBI);
                int yourSB = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.SB);
                int yourW = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.W);
                int yourSV = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.SV);
                double yourERA = draft.getTeamStatTotal(param.getValue(), Draft.Stat.ERA);
                double yourWHIP = draft.getTeamStatTotal(param.getValue(), Draft.Stat.WHIP);
                int yourK = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.K);
                double yourBA = draft.getTeamStatTotal(param.getValue(), Draft.Stat.BA);
                
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
                        
                    if(teams.get(i) != param.getValue()){

                    int theirR      = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.R);
                    int theirHR     = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.HR);
                    int theirRBI    = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.RBI);
                    int theirSB     = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.SB);
                    int theirW      = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.W);
                    int theirSV     = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.SV);
                    double theirERA = draft.getTeamStatTotal(teams.get(i), Draft.Stat.ERA);
                    double theirWHIP = draft.getTeamStatTotal(teams.get(i), Draft.Stat.WHIP);
                    int theirK      = (int) draft.getTeamStatTotal(teams.get(i), Draft.Stat.K);
                    double theirBA  = draft.getTeamStatTotal(teams.get(i), Draft.Stat.BA);
                    
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
                    return new SimpleDoubleProperty(totalPoints).asObject();
            }
                //Buckle up kids!
                 
    });

        totalStrikeoutsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Integer>, ObservableValue<Integer>>(){

            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Team, Integer> param) {
                int k = (int) draft.getTeamStatTotal(param.getValue(), Draft.Stat.K);
                return new SimpleIntegerProperty(k).asObject();
            }
            
        });
        totalBattingAverageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Team, Double>, ObservableValue<Double>>(){

            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Team, Double> param) {
                double ba =  draft.getTeamStatTotal(param.getValue(), Draft.Stat.BA);
                return new SimpleDoubleProperty(ba).asObject();
            }
            
        });
        
        teamsTable.setEditable(true);
        teamsTable.getColumns().add(teamNameColumn);
        teamsTable.getColumns().add(playersNeededColumn);
        teamsTable.getColumns().add(moneyLeftColumn);
        teamsTable.getColumns().add(perPlayerColumn);
        teamsTable.getColumns().add(totalRunsColumn);
        teamsTable.getColumns().add(totalHomeRunsColumn);
        teamsTable.getColumns().add(totalRunsBattedInColumn);
        teamsTable.getColumns().add(totalStolenBasesColumn);
        teamsTable.getColumns().add(totalWinsColumn);
        teamsTable.getColumns().add(totalSavesColumn);
        teamsTable.getColumns().add(totalEraColumn);
        teamsTable.getColumns().add(totalWhipColumn);
        teamsTable.getColumns().add(totalStrikeoutsColumn);
        teamsTable.getColumns().add(totalBattingAverageColumn);
        
        teamsTable.getColumns().add(totalPointsColumn);
        
        teamsTable.setItems(draft.getTeams());
        

    }
    
  
    
    
    public void setTable(){       

    }
    public TableView<Team> getTable(){
        return teamsTable;
    }
    
    
    
    
    
}