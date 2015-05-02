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
public enum Contract {
    NONE{
        @Override
        public String toString(){
            return "NONE";
        }
    },
    S1{
        @Override
        public String toString(){
            return "S1";
        }
    },
    
    S2{
        @Override
        public String toString(){
            return "S2";
        }
    },
    X{
        @Override
        public String toString(){
            return "X";
        }
    };

    /**
     *
     * @param c
     * @return
     */
    public static Contract parse(String c){
        if(c.equals(NONE.toString()))
            return NONE;
        else if(c.equals(S1.toString()))
            return S1;
        else if(c.equals(S2.toString()))
            return S2;
        else if(c.equals(X.toString()))
            return X;    
            
        return null;
    }
    
    
}
