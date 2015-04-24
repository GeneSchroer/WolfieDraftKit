/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import wdk.data.Draft;
import wdk.data.Hitter;
import wdk.data.Pitcher;

/**
 *
 * @author Work
 */
public class JsonDraftFileManager implements DraftFileManager {

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
    
    
    
    
    
    
    
    
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            h.setQualifiedPositions(jso.getString(JSON_QP));
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
            b.setQualifiedPositions("P");
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
    
}
