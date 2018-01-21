package com.learning.enumDemo;

import java.util.Random;

public enum Input {
	NICKEL(5),DIME(10),QUARTER(25),DOLLAR(100),TOOTHPASTE(200),CHIPS(75),SODA(100)  
    ,SOAP(50),ABORT_TRANSACTION{  
        public int amount(){  
            throw new RuntimeException("Abort exception");  
        }  
    },STOP{  
        public int amount(){  
            throw new RuntimeException("ShutDown exception");  
        }
    };
	
    int value;  
    Input(){}  
    Input(int v){  
        this.value=v;  
    }  
    public int amount(){  
        return value;  
    }  
    
    static Random rand = new Random(47);
    public static Input randomSelection() {
    	return values()[rand.nextInt(values().length-1)];
    }
}
