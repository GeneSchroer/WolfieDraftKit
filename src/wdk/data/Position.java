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
    }
    
    
    
    
}
