/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import static wdk.WDK_StartUpConstants.PATH_DRAFTS;
import wdk.data.Draft;
import wdk.data.DraftType;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Player;
import wdk.data.Position;
import wdk.data.Team;

/**
 *
 * @author Work
 */
public class JsonDraftFileManager implements DraftFileManager {
        private static JsonDraftFileManager singleton = null;

    
    
    
    private String JSON_PRO_TEAMS = "teams";
    String JSON_PLAYERS             = "Players";
    
    
    
    
    String JSON_HITTERS             = "Hitters";
    String JSON_PITCHERS            = "Pitchers";
    String JSON_PRO_TEAM                = "TEAM";
    String JSON_LAST_NAME           = "LAST_NAME";
    String JSON_FIRST_NAME           = "FIRST_NAME";
    String JSON_NOTES               = "NOTES";
    String JSON_YEAR_OF_BIRTH       = "YEAR_OF_BIRTH";
    String JSON_NATION_OF_BIRTH     = "NATION_OF_BIRTH";
    String JSON_QP                  = "QP";
    
    
    
    String JSON_P = "P";
    
    
    
    /* Hitter specific */ 
    
    String JSON_AB      = "AB";
    String JSON_R       = "R";
    String JSON_H       = "H";
    String JSON_HR      = "HR";
    String JSON_RBI     = "RBI";
    String JSON_SB      = "SB";

    /* Pitcher specfic */
    String JSON_IP      = "IP";
    String JSON_ER      = "ER";
    String JSON_W       = "W";
    String JSON_SV      = "SV";
//  String JSON_H       = "H";
    String JSON_BB      = "BB";
    String JSON_K       = "K";

    
    
    //File Strings
    String JSON_PLAYER_LAST_NAME = "Last Name";
    String JSON_PLAYER_FIRST_NAME = "First Name";
    String JSON_EXT = ".json";
    String SLASH = "/";
    
    
    String JSON_TEAMS               = "TEAMS";
    String JSON_TEAM_NAME = "NAME";
    String JSON_TEAM_OWNER = "OWNER";
    private String JSON_FANTASY_TEAM = "Fantasy Team";
    private String JSON_TEAM_POSITION = "Team Position";
    private String JSON_DRAFT_TYPE = "Draft Type";
    private String JSON_CONTRACT = "Contract";
     private String JSON_SALARY = "Salary";
    private String JSON_TAXI = "Taxi";
    private String JSON_DRAFT_NAME = "Draft";
    
    
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
        String draftListing = "" + draftToSave.getDraftName();
        String jsonFilePath = PATH_DRAFTS + SLASH + draftListing + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
        
        // MAKE A JSON ARRAY FOR THE PAGES ARRAY
        JsonArray playersJsonArray = makePlayersJsonArray(draftToSave.getAvailablePlayers());
        
        // AND AN OBJECT FOR THE TEAM NAMES
        JsonArray teamsJsonArray = makeTeamsJsonArray(draftToSave.getTeams());
        
//        JsonArray logsJsonArray = makeLogsJsonArray(draftToSave.getLogs());
        
        // THE DRAFT LOGS ARRAY
  //      JsonArray draftLogsJsonArray = makeScheduleItemsJsonArray(draftToSave.getDraftLogs());
        
        JsonObject draftJsonObject = Json.createObjectBuilder()
                                    .add(JSON_DRAFT_NAME, draftToSave.getDraftName())
                                    .add(JSON_PLAYERS, playersJsonArray)
                                    .add(JSON_TEAMS, teamsJsonArray)
                                //    .add(JSON_LECTURE_DAYS, lectureDaysJsonArray)
                .build();
        
        jsonWriter.writeObject(draftJsonObject);
    }

    @Override
    public void loadDraft(Draft draftToLoad, String jsonFilePath) throws IOException {
        //Load the JsonFile withh all the data
        
        draftToLoad.getAvailablePlayers().clear();
        draftToLoad.getTeams().clear();
        //draftToLoad.getLogs().clear();
        JsonObject json = loadJSONFile(jsonFilePath);
        draftToLoad.setDraftName(json.getString(JSON_DRAFT_NAME));
        
        JsonArray jsonPlayersArray = json.getJsonArray(JSON_PLAYERS);
        for (int i = 0; i < jsonPlayersArray.size(); i++){
            JsonObject jso = jsonPlayersArray.getJsonObject(i);
            Player player = new Player();
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setProTeam(jso.getString(JSON_PRO_TEAM));
            player.setNotes(jso.getString(JSON_NOTES));
            player.setYearOfBirth(jso.getString(JSON_YEAR_OF_BIRTH));
            player.setNationOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setFantasyTeam(jso.getString(JSON_FANTASY_TEAM));
            player.setTeamPosition(Position.parse(jso.getString(JSON_TEAM_POSITION)));
            player.setDraftType(DraftType.parse(jso.getString(JSON_DRAFT_TYPE)));
            player.setContract(jso.getString(JSON_CONTRACT));
            player.setSalary(jso.getJsonNumber(JSON_SALARY).doubleValue());
            String qp = jso.getString(JSON_QP);
            
            
            
            if(qp.contains((CharSequence) Position.P.toString())){
                Pitcher pitcher = new Pitcher(player);
                pitcher.addPosition(Position.P);
                pitcher .setInningsPitched(jso.getJsonNumber(JSON_IP).doubleValue());
                pitcher     .setEarnedRuns(jso.getInt(JSON_ER));
                pitcher .setWins(jso.getInt(JSON_W));
                pitcher.setSaves(jso.getInt(JSON_SV));
                pitcher.setHits(jso.getInt(JSON_H));
                pitcher.setBasesOnBalls(jso.getInt(JSON_BB));
                pitcher.setStrikeouts(jso.getInt(JSON_K));
                draftToLoad.addPlayer(pitcher);
            }
            else{
                Hitter hitter = new Hitter(player);
                if(qp.contains((CharSequence) Position.C.toString())){
                    hitter.addPosition(Position.C);
                    hitter.addPosition(Position.U);
                }
                if(qp.contains((CharSequence) Position.B1.toString())){
                    hitter.addPosition(Position.B1);
                    hitter.addPosition(Position.CI);
                    hitter.addPosition(Position.U);
                }
                if(qp.contains((CharSequence) Position.B3.toString())){
                    hitter.addPosition(Position.B3);
                    hitter.addPosition(Position.CI);
                    hitter.addPosition(Position.U);
                }
                if(qp.contains((CharSequence) Position.B2.toString())){
                    hitter.addPosition(Position.B2);
                    hitter.addPosition(Position.MI);
                    hitter.addPosition(Position.U);
                }
                if(qp.contains((CharSequence) Position.SS.toString())){
                    hitter.addPosition(Position.SS);
                    hitter.addPosition(Position.MI);
                    hitter.addPosition(Position.U);
                }
                if(qp.contains((CharSequence) Position.OF.toString())){
                    hitter.addPosition(Position.OF);
                    hitter.addPosition(Position.U);
                }
                hitter.setAtBat(jso.getInt(JSON_AB));
                hitter.setRuns(jso.getInt(JSON_R));
                hitter.setHits(jso.getInt(JSON_H));
                hitter.setHomeRuns(jso.getInt(JSON_HR));
                hitter.setRunsBattedIn(jso.getInt(JSON_RBI));
                hitter.setAtBat(jso.getInt(JSON_SB));
                draftToLoad.addPlayer(hitter);
            }
            
        }
        
        JsonArray jsonTeamsArray = json.getJsonArray(JSON_TEAMS);
        for (int i = 0; i < jsonTeamsArray.size(); i++){
            JsonObject jso = jsonTeamsArray.getJsonObject(i);
            JsonObject pos = jso.getJsonObject(JSON_TEAM_POSITION);
            Team t = new Team(jso.getString(JSON_TEAM_NAME), jso.getString(JSON_TEAM_OWNER), pos.getInt("C"), pos.getInt("1B"), pos.getInt("CI"), pos.getInt("3B"), pos.getInt("2B"), pos.getInt("MI"), pos.getInt("SS"), pos.getInt("OF"), pos.getInt("U"), pos.getInt("P"), jso.getInt(JSON_TAXI));
            draftToLoad.addTeam(t);

        }
    }

    /**
     *
     * @param filePathHitters
     * @param filePathPitchers
     * @param filePath
     * @return
     * @throws IOException
     */
    @Override
    public Draft loadStartingDraft(ArrayList<String> filePathList) throws IOException{
        //It should be hitters first, then pitchers
        JsonObject jsonH= loadJSONFile(filePathList.get(0));
        JsonObject jsonP= loadJSONFile(filePathList.get(1));
        return buildNewBaseballDraftJsonObject(jsonH, jsonP);
    }

    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    

    private Draft buildNewBaseballDraftJsonObject(JsonObject jsonH, JsonObject jsonP) {
        
        Draft draft = new Draft();
        JsonArray jsonHittersArray = jsonH.getJsonArray(JSON_HITTERS);
        JsonArray jsonPitchersArray = jsonP.getJsonArray(JSON_PITCHERS);
        
        for(int i = 0; i < jsonHittersArray.size(); ++i){
            JsonObject jso = jsonHittersArray.getJsonObject(i);
            Hitter h = new Hitter();
            h.setProTeam(jso.getString(JSON_PRO_TEAM));
            h.setLastName(jso.getString(JSON_LAST_NAME));
            h.setFirstName(jso.getString(JSON_FIRST_NAME));
            
            h.setFantasyTeam(FREE_AGENT);
            
            String qp = jso.getString(JSON_QP);
            if(qp.contains((CharSequence) Position.C.toString())){
                h.addPosition(Position.C);
                h.addPosition(Position.U);
            }
            if(qp.contains((CharSequence) Position.B1.toString())){
                h.addPosition(Position.B1);
                h.addPosition(Position.CI);
                h.addPosition(Position.U);
            }
            if(qp.contains((CharSequence) Position.B3.toString())){
                h.addPosition(Position.B3);
                h.addPosition(Position.CI);
                h.addPosition(Position.U);
            }
            if(qp.contains((CharSequence) Position.B2.toString())){
                h.addPosition(Position.B2);
                h.addPosition(Position.MI);
                h.addPosition(Position.U);
            }
            if(qp.contains((CharSequence) Position.SS.toString())){
                h.addPosition(Position.SS);
                h.addPosition(Position.MI);
                h.addPosition(Position.U);
            }
            if(qp.contains((CharSequence) Position.OF.toString())){
                h.addPosition(Position.OF);
                h.addPosition(Position.U);
            }
            
            h.setAtBat(Integer.parseInt(jso.getString(JSON_AB)));
            h.setRuns(Integer.parseInt(jso.getString(JSON_R)));
            h.setHits(Integer.parseInt(jso.getString(JSON_H)));
            h.setHomeRuns(Integer.parseInt(jso.getString(JSON_HR)));
            h.setRunsBattedIn(Integer.parseInt(jso.getString(JSON_RBI)));
            h.setStolenBases(Integer.parseInt(jso.getString(JSON_SB)));
            h.setNotes(jso.getString(JSON_NOTES));
            h.setYearOfBirth(jso.getString(JSON_YEAR_OF_BIRTH));
            h.setNationOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            
            
            
            draft.addPlayer(h);
        }
        for(int i = 0; i < jsonPitchersArray.size(); ++i){
            JsonObject jso = jsonPitchersArray.getJsonObject(i);
            Pitcher b = new Pitcher();
            b.setProTeam(jso.getString(JSON_PRO_TEAM));
            b.setLastName(jso.getString(JSON_LAST_NAME));
            b.setFirstName(jso.getString(JSON_FIRST_NAME));
            
            b.setFantasyTeam(FREE_AGENT);
            
            b.addPosition(Position.P);
            b.setInningsPitched(Double.parseDouble(jso.getString(JSON_IP)));
            b.setEarnedRuns(Integer.parseInt(jso.getString(JSON_ER)));
            b.setWins(Integer.parseInt(jso.getString(JSON_W)));
            b.setSaves(Integer.parseInt(jso.getString(JSON_SV)));
            b.setHits(Integer.parseInt(jso.getString(JSON_H)));
            b.setBasesOnBalls(Integer.parseInt(jso.getString(JSON_BB)));
            b.setStrikeouts(Integer.parseInt(jso.getString(JSON_K)));
            b.setNotes(jso.getString(JSON_NOTES));
            b.setYearOfBirth(jso.getString(JSON_YEAR_OF_BIRTH));
            b.setNationOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            
            draft.addPlayer(b);
        }
        
        return draft;
    }
     public ArrayList<String> loadProTeams(String jsonFilePath) throws IOException {
        ArrayList<String> subjectsArray = loadArrayFromJSONFile(jsonFilePath, JSON_PRO_TEAMS);
        ArrayList<String> cleanedArray = new ArrayList();
        for (String s : subjectsArray) {
            // GET RID OF ALL THE QUOTE CHARACTERS
            s = s.replaceAll("\"", "");
            cleanedArray.add(s);
        }
        return cleanedArray;
    }
    
     private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
        JsonObject json = loadJSONFile(jsonFilePath);
        ArrayList<String> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        for (JsonValue jsV : jsonArray) {
            items.add(jsV.toString());
        }
        return items;
    }
    
    private void addImage(String lastName, String firstName){
        
    }

    private JsonArray makePlayersJsonArray(ObservableList<Player> availablePlayers) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Player p: availablePlayers){
            jsb.add(makePlayerJsonObject(p));
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeTeamsJsonArray(ObservableList<Team> teams) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Team t: teams){
            jsb.add(makeTeamJsonObject(t));
        }
        JsonArray jA = jsb.build();
        return jA;
    }
    private JsonArray makePositionsJsonArray(Player p){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(int i = 0; i< p.getPositionList().size();++i)
            jsb.add(p.getPositionList().get(i).toString());
        JsonArray ja =  jsb.build();
        return ja;
    }

    private JsonValue makePlayerJsonObject(Player p) {
        JsonObject jso = null;
        
        if(p instanceof Hitter){
            Hitter hitter = (Hitter) p;
             jso  = Json.createObjectBuilder().add(JSON_LAST_NAME, hitter.getLastName())
                                                    .add(JSON_FIRST_NAME, hitter.getFirstName())
                                                    .add(JSON_QP, positionString(hitter))
                                                    .add(JSON_AB, hitter.getAtBat())
                                                    .add(JSON_R, hitter.getRuns())
                                                    .add(JSON_H, hitter.getHits())
                                                    .add(JSON_HR, hitter.getHomeRuns())
                                                    .add(JSON_RBI, hitter.getRunsBattedIn())
                                                    .add(JSON_SB, hitter.getStolenBases())
                                                    .add(JSON_NOTES, hitter.getNotes())
                                                    .add(JSON_PRO_TEAM, hitter.getProTeam())    
                                                    .add(JSON_YEAR_OF_BIRTH, hitter.getYearOfBirth())
                                                    .add(JSON_NATION_OF_BIRTH, hitter.getNationOfBirth())
                                                    .add(JSON_FANTASY_TEAM, hitter.getFantasyTeam())
                                                    .add(JSON_TEAM_POSITION, hitter.getTeamPosition().toString())
                                                    .add(JSON_DRAFT_TYPE, hitter.getDraftType().toString())
                                                    .add(JSON_CONTRACT, hitter.getContract().toString())
                                                    .add(JSON_SALARY, hitter.getSalary())
                                                    .build();
        }
        else{
            Pitcher pitcher = (Pitcher) p;
            jso  = Json.createObjectBuilder().add(JSON_LAST_NAME, pitcher.getLastName())
                                                    .add(JSON_FIRST_NAME, pitcher.getFirstName())
                                                    .add(JSON_QP, positionString(pitcher))
                                                    .add(JSON_IP, pitcher.getInningsPitched())
                                                    .add(JSON_ER, pitcher.getEarnedRuns())
                                                    .add(JSON_W, pitcher.getWins())
                                                    .add(JSON_SV, pitcher.getSaves())
                                                    .add(JSON_H, pitcher.getHits())
                                                    .add(JSON_BB, pitcher.getBasesOnBalls())
                                                    .add(JSON_K, pitcher.getStrikeouts())
                                                    .add(JSON_NOTES, pitcher.getNotes())
                                                    .add(JSON_PRO_TEAM, pitcher.getProTeam())    
                                                    .add(JSON_YEAR_OF_BIRTH, pitcher.getYearOfBirth())
                                                    .add(JSON_NATION_OF_BIRTH, pitcher.getNationOfBirth())
                                                    .add(JSON_FANTASY_TEAM, pitcher.getFantasyTeam())
                                                    .add(JSON_TEAM_POSITION, pitcher.getTeamPosition().toString())
                                                    .add(JSON_DRAFT_TYPE, pitcher.getDraftType().toString())
                                                    .add(JSON_CONTRACT, pitcher.getContract().toString())
                                                    .add(JSON_SALARY, pitcher.getSalary())
                                                    .build();
        }
     
        return jso;
    }
    
    public static JsonDraftFileManager getPropertiesManager()
    {
        // IF IT'S NEVER BEEN RETRIEVED BEFORE THEN
        // FIRST WE MUST CONSTRUCT IT
        if (singleton == null)
        {
            // WE CAN CALL THE PRIVATE CONSTRCTOR FROM WITHIN THE CLASS
            singleton = new JsonDraftFileManager();
        }
        // RETURN THE SINGLETON
        return singleton;
    }

    private JsonValue makeTeamJsonObject(Team t) {
        JsonObject jso = Json.createObjectBuilder() .add(JSON_TEAM_NAME, t.getName())
                                                    .add(JSON_TEAM_OWNER, t.getOwner())
                                                    .add(JSON_TEAM_POSITION, makeJsonTeamPositionsObject(t))
                                                    .add(JSON_TAXI, t.getTaxi())
                                                    .build();
        return jso;
    }
    
    
    
    public JsonObject makeJsonTeamPositionsObject(Team t){
        JsonObject jso = Json.createObjectBuilder() .add(Position.C.toString(), t.getPosition(Position.C))
                                                    .add(Position.B1.toString(), t.getPosition(Position.B1))
                                                    .add(Position.CI.toString(), t.getPosition(Position.CI))
                                                    .add(Position.B3.toString(), t.getPosition(Position.B3))
                                                    .add(Position.B2.toString(), t.getPosition(Position.B2))
                                                    .add(Position.MI.toString(), t.getPosition(Position.MI))
                                                    .add(Position.SS.toString(), t.getPosition(Position.SS))
                                                    .add(Position.OF.toString(), t.getPosition(Position.OF))
                                                    .add(Position.U.toString(), t.getPosition(Position.U))
                                                    .add(Position.P.toString(), t.getPosition(Position.P))
                                                    .build();
       return jso;
    }
   
    
    
   private String positionString(Player p){
       String s ="";
       for(int i = 0; i< p.getPositionList().size();++i){
           if(!p.getPositionList().get(i).equals(Position.CI) && !p.getPositionList().equals(Position.MI)
                   && !p.getPositionList().equals(Position.U))
               s+=p.getPositionList().get(i).toString()+ "_";
       }
       s = s.substring(0, s.length());
       return s;
   }
    
}
