package com.singletonpattern;
    
import java.io.*;

    public class TestReadResolve implements Serializable {  
      
        private static final TestReadResolve INSTANCE = new TestReadResolve();  
      
        public static void main(String[] args) throws Exception {  
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.ser"));  
            oos.writeObject(INSTANCE);  
            oos.close();  
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"));  
            TestReadResolve test = (TestReadResolve) ois.readObject();  
            ois.close();  

			// To verify if this works correctly-> comment the below readResolve method, compile and run.
            System.out.println(test == INSTANCE);  
        }  
      
	     private Object readResolve() throws ObjectStreamException {  
            return INSTANCE;  
        };  
	
    }  