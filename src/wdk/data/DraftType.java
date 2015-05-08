  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

/**
 *
 * @author Work
 */
public enum DraftType {
    NONE{
        @Override
        public String toString(){
            return "NONE";
        }
    },
    STARTING{
        @Override
        public String toString(){
            return "STARTING";
        }
    },
    TAXI{
        @Override
        public String toString(){
            return "TAXI";
        }
    };
    public static DraftType parse(String dt){
        if(dt.equals(NONE.toString()))
            return NONE;
        else if(dt.equals(STARTING.toString()))
            return STARTING;
        else if(dt.equals(TAXI.toString()))
            return TAXI;
        return null;
    }
}
