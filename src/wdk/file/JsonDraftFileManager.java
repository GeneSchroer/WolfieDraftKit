/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import static com.sun.corba.se.impl.ior.iiop.JavaSerializationComponent.singleton;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Collections.singleton;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import properties_manager.PropertiesManager;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import static wdk.WDK_StartUpConstants.JSON_FILE_PATH_TEAMS;
import static wdk.WDK_StartUpConstants.PATH_DRAFTS;
import wdk.data.Draft;
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
    String JSON_TEAMS               = "Teams";
    
    
    
    String JSON_HITTERS             = "Hitters";
    String JSON_PITCHERS            = "Pitchers";
    String JSON_PRO_TEAM                = "TEAM";
    String JSON_LAST_NAME           = "LAST_NAME";
    String JSON_FIRST_NAME           = "FIRST_NAME";
    String JSON_NOTES               = "NOTES";
    String JSON_YEAR_OF_BIRTH       = "YEAR_OF_BIRTH";
    String JSON_NATION_OF_BIRTH     = "NATION_OF_BIRTH";
    String JSON_QP                  = "QP";
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
    String JSON_PLAYER_LAST_NAME = "lastName";
    String JSON_PLAYER_FIRST_NAME = "firstName";
    String JSON_EXT = ".json";
    String SLASH = "/";
    
    
    
    
    
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
        JsonObject teamsJsonArray = makeTeamsJsonArray(draftToSave.getTeams());
        
        
        // THE DRAFT LOGS ARRAY
  //      JsonArray draftLogsJsonArray = makeScheduleItemsJsonArray(draftToSave.getDraftLogs());
        
        JsonObject draftJsonObject = Json.createObjectBuilder()
                                    .add(JSON_PLAYERS, playersJsonArray)
                                    .add(JSON_TEAMS, teamsJsonArray)
                                //    .add(JSON_LECTURE_DAYS, lectureDaysJsonArray)
                .build();
        
        jsonWriter.writeObject(draftJsonObject);
    }

    @Override
    public void loadDraft(Draft draftToLoad, String draftPath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private JsonObject makeTeamsJsonArray(ObservableList<Team> teams) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private JsonValue makePlayerJsonObject(Player p) {
        JsonObject jso = null;
        
        if(p instanceof Hitter){
            Hitter hitter = (Hitter) p;
             jso  = Json.createObjectBuilder().add(JSON_PLAYER_LAST_NAME, hitter.getLastName())
                                                    .add(JSON_PLAYER_FIRST_NAME, hitter.getFirstName())
                                                    .add(JSON_QP, hitter.getQualifiedPositions())
                                                    .add(JSON_AB, hitter.getAtBat())
                                                    .add(JSON_R, hitter.getRuns())
                                                    .add(JSON_H, hitter.getHits())
                                                    .add(JSON_HR, hitter.getHomeRuns())
                                                    .add(JSON_RBI, hitter.getRunsBattedIn())
                                                    .add(JSON_SB, hitter.getStolenBases())
                                                    .add(JSON_NOTES, hitter.getNotes())
                                                    .add(JSON_YEAR_OF_BIRTH, hitter.getYearOfBirth())
                                                    .add(JSON_NATION_OF_BIRTH, hitter.getNationOfBirth())
                                                    .build();
        }
        else{
            Pitcher pitcher = (Pitcher) p;
            jso  = Json.createObjectBuilder().add(JSON_PLAYER_LAST_NAME, pitcher.getLastName())
                                                    .add(JSON_PLAYER_FIRST_NAME, pitcher.getFirstName())
                                                    .add(JSON_QP, pitcher.getQualifiedPositions())
                                                    .add(JSON_IP, pitcher.getInningsPitched())
                                                    .add(JSON_ER, pitcher.getEarnedRuns())
                                                    .add(JSON_W, pitcher.getWins())
                                                    .add(JSON_SV, pitcher.getSaves())
                                                    .add(JSON_H, pitcher.getHits())
                                                    .add(JSON_BB, pitcher.getBasesOnBalls())
                                                    .add(JSON_K, pitcher.getStrikeouts())
                                                    .add(JSON_NOTES, pitcher.getNotes())
                                                    .add(JSON_YEAR_OF_BIRTH, pitcher.getYearOfBirth())
                                                    .add(JSON_NATION_OF_BIRTH, pitcher.getNationOfBirth())
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
}
