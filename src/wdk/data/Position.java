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
public enum Position {
    C{
        
       
        
        @Override
        public String toString(){
            return "C";
        }
    },
    B1{
        @Override
        public String toString(){
            return "1B";
        }
    },
    CI{
        @Override
        public String toString(){
            return "CI";
        }
    },
    B3{
        @Override
        public String toString(){
            return "3B";
        }
    },
    B2{
        @Override
        public String toString(){
            return "2B";
        }
    },
    MI{
        @Override
        public String toString(){
            return "MI";
        }
    },
    SS{
        @Override
        public String toString(){
            return "SS";
        }
    },
    OF{
        @Override
        public String toString(){
            return "OF";
        }
    },
    U{
        @Override
        public String toString(){
            return "U";
        }
    },
    P{
        @Override
        public String toString(){
            return "P";
        }
    }, 
    ALL{
        
    },
    
    
    NONE{
        public String toString(){
            return"None";
        }
    };
    

    /**
     *
     * @param p
     * @return
     */
            
        public static Position parse(String p){
            if(p.equals("C"))
                return C;
            if(p.equals("B1")||p.equals("1B"))
                return B1;
            if(p.equals("CI"))
                return CI;
            if(p.equals("B3")||p.equals("3B"))
                return B3;
            if(p.equals("B2")||p.equals("2B"))
                return B2;
            if(p.equals("MI"))
                return MI;
            if(p.equals("SS"))
                return SS;
            if(p.equals("OF"))
                return OF;
            if(p.equals("U"))
                return U;
            if(p.equals("P"))
                return P;
            return NONE;
        }
        
    }
    
    

