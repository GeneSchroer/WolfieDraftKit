/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.zzz;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import wdk.data.Draft;
import wdk.data.Hitter;
import wdk.data.Pitcher;
import wdk.file.DraftFileManager;

/**
 *
 * @author Work
 */
public abstract class JsonDraftFileManagerG implements DraftFileManager{
    
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
    public Draft loadStartingDraft(String filePathHitters, String filePathPitchers) throws IOException{
        JsonObject jsonH= loadJSONFile(filePathHitters);
        JsonObject jsonP= loadJSONFile(filePathPitchers);
        return buildNewDraftJsonObject(jsonH, jsonP);
    }

    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    

    private Draft buildNewDraftJsonObject(JsonObject jsonH, JsonObject jsonP) {
        
        Draft draft = new Draft();
        JsonArray jsonHittersArray = jsonH.getJsonArray(JSON_HITTERS);
        JsonArray jsonPitchersArray = jsonP.getJsonArray(JSON_PITCHERS);
        
        for(int i = 0; i < jsonHittersArray.size(); ++i){
            JsonObject jso = jsonHittersArray.getJsonObject(i);
            Hitter h = new Hitter();
            h.setTeam(jso.getString(JSON_TEAM));
            h.setLastName(jso.getString(JSON_LAST_NAME));
            h.setFirstName(jso.getString(JSON_FIRST_NAME));
            h.setQualifiedPositions(jso.getString(JSON_QP));
            h.setAtBat(jso.getInt(JSON_AB));
            h.setRuns(jso.getInt(JSON_R));
            h.setHits(jso.getInt(JSON_H));
            h.setHomeRuns(jso.getInt(JSON_HR));
            h.setRunsBattedIn(jso.getInt(JSON_RBI));
            h.setStolenBases(jso.getInt(JSON_SB));
            h.setNotes(jso.getString(JSON_NOTES));
            h.setYearOfBirth(jso.getString(JSON_YEAR_OF_BIRTH));
            h.setNationOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            
            draft.addPlayer(h);
        }
        for(int i = 0; i < jsonPitchersArray.size(); ++i){
            JsonObject jso = jsonPitchersArray.getJsonObject(i);
            Pitcher b = new Pitcher();
            b.setTeam(jso.getString(JSON_TEAM));
            b.setLastName(jso.getString(JSON_LAST_NAME));
            b.setFirstName(jso.getString(JSON_FIRST_NAME));
            b.setQualifiedPositions(jso.getString(JSON_QP));
            b.setInningsPitched(jso.getInt(JSON_IP));
            b.setEarnedRuns(jso.getInt(JSON_ER));
            b.setWins(jso.getInt(JSON_W));
            b.setSaves(jso.getInt(JSON_SV));
            b.setHits(jso.getInt(JSON_H));
            b.setBasesOnBalls(jso.getInt(JSON_BB));
            b.setStrikeouts(jso.getInt(JSON_K));
            b.setNotes(jso.getString(JSON_NOTES));
            b.setYearOfBirth(jso.getString(JSON_YEAR_OF_BIRTH));
            b.setNationOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            
            draft.addPlayer(b);
        }
        
        return draft;
    }
}
