package monty;

import static org.junit.Assert.*;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;


public final class TestAll {
	
	 private int n_doors = 3;
     private boolean chosen = false;
     private boolean monty_type_secret = false; //true if monty type is secret, false otherwise
     private double probability = 0.5;
     private int choice_door=1;
     private Monty m = null;
     private New_Monty nm = null;

     public double compareStatOneThird(int tries,int[] results)
     {	
    	int delta = 0;
 		int delta_sq = 0;
 		
    	Random rand = new Random(System.currentTimeMillis());
    	for(int i=0; i<tries; ++i)
 	    {
 		    int ranVar = rand.nextInt(n_doors) + 1;
 		    int ranVarBool = 1;
 		    if(ranVar == 1 || ranVar == 2)
 		    	ranVarBool = 0;
 		    	
 		    delta+=results[i]-ranVarBool;
 		    delta_sq+=(results[i]-ranVarBool)*(results[i]-ranVarBool);
 		    
 	    }
    	double t = ((double)delta/tries)/(Math.sqrt((delta_sq-(double)delta*delta/tries)/(tries*(tries-1))));
    	return t;
     }
     public double compareStatTwoThirds(int tries,int[] results)
     {	
    	int delta = 0;
 		int delta_sq = 0;
 		
    	Random rand = new Random(System.currentTimeMillis());
    	for(int i=0; i<tries; ++i)
 	    {
 		    int ranVar = rand.nextInt(n_doors) + 1;
 		    int ranVarBool = 1;
 		    if(ranVar == 1)
 		    	ranVarBool = 0;
 		    	
 		    delta+=results[i]-ranVarBool;
 		    delta_sq+=(results[i]-ranVarBool)*(results[i]-ranVarBool);
 		    
 	    }
    	double t = ((double)delta/tries)/(Math.sqrt((delta_sq-(double)delta*delta/tries)/(tries*(tries-1))));
    	return t;
     }

	@Test
	 public void testMontyConstructor() {
		//standard Monty variations
		Random rand = new Random(System.currentTimeMillis());
	    int prize = rand.nextInt(n_doors) + 1;
	    
	    new StandardMonty(n_doors, choice_door, prize);
	  }
	@Test
	public void testStandardMontyStay(){
		
		PrintStream stdout = System.out;
		int tries=100000;
		int successTries=0;
		
		int[] results;
		results = new int[tries];
	     
	    try {
			System.setOut(new PrintStream(new File("testFile.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    Random rand = new Random(System.currentTimeMillis());
	    
	    for(int i=0; i<tries; ++i)
	    {
		    int prize = rand.nextInt(n_doors) + 1;
		    int chosen_door = rand.nextInt(n_doors) + 1;
		    	
		    m = new StandardMonty(n_doors, chosen_door, prize);
		    String data = Integer.toString(chosen_door);
		    System.setIn(new ByteArrayInputStream(data.getBytes()));
		    int ans = m.execute()? 1 : 0;
		    results[i]=ans;
		    
		    if(ans == 1)
		    	successTries++;
		    
	    }
	    
	    double t = compareStatOneThird(tries,results);

	    
	    System.setOut(stdout);
	    System.out.println("testStandardMontyStay: "+(double)successTries/tries);
	    System.out.println("t-value is " + t);
	    assert(Math.abs(t)<1.282);
	}
	@Test
	public void testStandardMontySwitch(){
		
		PrintStream stdout = System.out;
		int tries=100000;
		int successTries=0;
		
		int[] results;
		results = new int[tries];
	    
		
	    try {
			System.setOut(new PrintStream(new File("testFile.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    Random rand = new Random(System.currentTimeMillis());
	    
	    for(int i=0; i<tries; ++i)
	    {
		    int prize = rand.nextInt(n_doors) + 1;
		    int chosen_door = rand.nextInt(n_doors) + 1;
		    
		    
		    int new_door = rand.nextInt(n_doors) + 1;
		    while(new_door == chosen_door)
		    {
		    	new_door = rand.nextInt(n_doors) + 1;
		    }
		    int new_new_door = rand.nextInt(n_doors)+1;
		    while(new_new_door == chosen_door || new_new_door == new_door)
		    {
		    	new_new_door = rand.nextInt(n_doors) + 1;
		    }
		    String data = Integer.toString(new_door)+"\n"+Integer.toString(new_new_door);
		    System.setIn(new ByteArrayInputStream(data.getBytes()));
		    	
		    m = new StandardMonty(n_doors, chosen_door, prize);
		    int ans = m.execute() ? 1 : 0;
		    results[i]=ans;
		    
		    if(ans == 1)
		    	successTries++;
		    
	    }
	    
	    double t = compareStatTwoThirds(tries,results);
	    
	    System.setOut(stdout);
	    System.out.println("testStandardMontySwitch: "+(double)successTries/tries);
	    System.out.println("t-value is " + t);
	    assert(Math.abs(t)<1.282);
	}

	    
}
