package com.voter;

import java.io.IOException;
import java.util.*;

public class Input 
{
			
			public  ArrayList<Character> R1=new ArrayList<Character>();
			public  ArrayList<Character> R2=new ArrayList<Character>();
			 
			HashMap<Integer,String> R1votes=new HashMap<Integer,String>();
			HashMap<Integer,String> R2votes=new HashMap<Integer,String>();
		
			
			public void setR1(ArrayList<Character> r1)
			{
				R1 = r1;
			}
			public void setR2(ArrayList<Character> r2) 
			{
				R2 = r2;
			}

		
			public void setR1votes(HashMap R1votes) 
			{
				this.R1votes = R1votes;
			}
			public void setR2votes(HashMap R2votes) 
			{
				this.R2votes = R2votes;
			}

			
			public void processData() throws IOException
			{
				
				ReadRegions rr1=new ReadRegions();
				ReadRegions rr2=new ReadRegions();
				ReadVoters rv1=new ReadVoters();
				ReadVoters rv2=new ReadVoters();
				
				Input regioncontestent1=rr1.readRegions(1);
				Input regioncontestent2=rr2.readRegions(2);
				Input regionvotes1=rv1.readVoters(1);
				Input regionvotes2=rv2.readVoters(2);
				
				
				VoteCounting r0=new VoteCounting();
				VoteValidity obj1=new VoteValidity();
		
				
				ArrayList<Character> r1= regioncontestent1.R1;
				ArrayList<Character> r2= regioncontestent2.R2;
		
				HashMap<Integer,String> vr1=regionvotes1.R1votes;
				HashMap<Integer,String> vr2=regionvotes2.R2votes;
		
				r0.assignData(r1, r2, vr1, vr2);
				
			}
			
			public HashMap<String,Integer> allContestants()
			{
				HashMap<String,Integer> allregions=new HashMap<String,Integer>();
			
				for(int i=65;i<91;i++)
				{
					String s=String.valueOf(Character.toChars(i));
					allregions.put(s, 0);
				}
			return allregions;
			}	
			
}
