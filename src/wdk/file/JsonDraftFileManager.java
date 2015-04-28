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
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import static wdk.WDK_StartUpConstants.FREE_AGENT;
import static wdk.WDK_StartUpConstants.JSON_FILE_PATH_TEAMS;
import wdk.data.Draft;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.data.Position;

/**
 *
 * @author Work
 */
public class JsonDraftFileManager implements DraftFileManager {
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
    
    String SLASH = "/";
    
    
    
    
    
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
        String courseListing = "" + draftToSave.getDraftName();
        String jsonFilePath = PATH_DRAFTS + SLASH + courseListing + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
        
        // MAKE A JSON ARRAY FOR THE PAGES ARRAY
        JsonArray pagesJsonArray = makePagesJsonArray(courseToSave.getPages());
        
        // AND AN OBJECT FOR THE INSTRUCTOR
        JsonObject instructorJsonObject = makeInstructorJsonObject(courseToSave.getInstructor());
        
        // ONE FOR EACH OF OUR DATES
        JsonObject startingMondayJsonObject = makeLocalDateJsonObject(courseToSave.getStartingMonday());
        JsonObject endingFridayJsonObject = makeLocalDateJsonObject(courseToSave.getEndingFriday());
        
        // THE LECTURE DAYS ARRAY
        JsonArray lectureDaysJsonArray = makeLectureDaysJsonArray(courseToSave.getLectureDays());
        
        // THE SCHEDULE ITEMS ARRAY
        JsonArray scheduleItemsJsonArray = makeScheduleItemsJsonArray(courseToSave.getScheduleItems());
        
        // THE LECTURES ARRAY
        JsonArray lecturesJsonArray = makeLecturesJsonArray(courseToSave.getLectures());
        
        // THE HWS ARRAY
        JsonArray hwsJsonArray = makeHWsJsonArray(courseToSave.getAssignments());
        
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
}
