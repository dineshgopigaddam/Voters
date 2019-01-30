package com.voter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.util.*;




public class VoteValidity 
{
		// Declaring the Variables for Regions
		public  ArrayList<Character> R1=new ArrayList<Character>();
		public  ArrayList<Character> R2=new ArrayList<Character>();
		
		//Declaring the variables for Voters
		HashMap<Integer,String> R1votes=new HashMap<Integer,String>();
		HashMap<Integer,String> R2votes=new HashMap<Integer,String>();
	
		//Invalid Votes by Regions
		HashMap<Integer,String> InvalidR1=new HashMap<Integer,String>();
		HashMap<Integer,String> InvalidR2=new HashMap<Integer,String>();

		
		public void sendData(ArrayList<Character> region1, ArrayList<Character> region2)   // Receive the Contestants by Regions
		{
			this.R1=region1;
			this.R2=region2;
		}
		
		public void invalid2(String id,String votes,int count)	           //Store the Invalid Vote Condition 2 
		{
			int voter_id=Integer.parseInt(id);
			String votes_list=votes.replace("", " ").trim();
			
			switch(count)
			{
			case 1:
				InvalidR1.put(voter_id, votes_list);
				System.out.println("Invalid Vote in Region1 "+InvalidR1);
				break;
			case 2:
				InvalidR2.put(voter_id, votes_list);
				System.out.println(" Invalid Vote in Region2 "+InvalidR2);
				break;
			}
			
		}	
		
		public HashMap<Integer,String> Invalid3(HashMap<Integer, String> vr1, int count)				
		{
			Iterator it=vr1.entrySet().iterator();
			if(count==1)
			{
				for(Map.Entry<Integer, String> entry :vr1.entrySet())
				{
					int i=0;
					int voter_id=entry.getKey();
					String vote_list=entry.getValue();
					int size=vote_list.length()-1;
label1:				while(i<=size)
					{
						char c=vote_list.charAt(i);
					      if(R1.contains(c))
					      {
					    	  if(i==size)
					    	  {
					    		 R1votes.put(voter_id,vote_list); 
					    	  }
					    	  i=i+1;
					      }
					      else
					      {
					    	  InvalidR1.put(voter_id,vote_list);
					    	  System.out.println("Invalid Vote in Region1:: "+InvalidR1);
					    	  
					    	  Map.Entry pair = (Map.Entry)it.next();
					    	  break label1 ;
					      }
					}  
				}
				return R1votes;
			  }
			  else if(count==2)
			  {
				  for(Map.Entry<Integer, String> entry :vr1.entrySet())
					{
						int i=0;
						int voter_id=entry.getKey();
						String vote_list=entry.getValue();
						int size=vote_list.length()-1;
label2:					while(i<=size)
						{
							char c=vote_list.charAt(i);
						      if(R2.contains(c))
						      {
						    	  if(i==size)
						    	  {
						    		  R2votes.put(voter_id,vote_list); 
						    	  }
						    	  i=i+1;
						      }
						      else
						      {
						    	  InvalidR2.put(voter_id,vote_list);
								  System.out.println("Invalid Vote in Region2::"+InvalidR2);
								  Map.Entry pair = (Map.Entry)it.next();
						    	  break label2 ;
						      }
						}  
					} 
			  	}		
			return R2votes;
		}
}