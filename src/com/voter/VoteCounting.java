

package com.voter;
import java.util.*;

public class VoteCounting 

{
			int total_R1;
			int total_R2;
	
			ArrayList<Character> Region1=new ArrayList<Character>();
			ArrayList<Character> Region2=new ArrayList<Character>();
			HashMap<Integer,String> R1votes=new HashMap<Integer,String>();
			HashMap<Integer,String> R2votes=new HashMap<Integer,String>();
			
			public void assignData(ArrayList<Character> r1,ArrayList<Character> r2,HashMap<Integer, String> vr1,HashMap<Integer, String> vr2)
			{
				
				VoteCounting obj2=new VoteCounting();
				obj2.resultsProcessing(r1,r2,vr1,vr2);
			}
			
			
			
			public void resultsProcessing(ArrayList<Character> r1, ArrayList<Character> r2, HashMap<Integer, String> vr1, HashMap<Integer, String> vr2)
			{
				
				HashMap<String,Integer> R1Result=new HashMap<String,Integer>();
				HashMap<String,Integer> R2Result=new HashMap<String,Integer>();
				
				VotingResults vr=new VotingResults();
				VoteValidity vv=new VoteValidity();
				VoteCounting obj=new VoteCounting();
				this.Region1=r1;
				this.Region2=r2;
				this.R1votes=vr1;
				this.R2votes=vr2;
				
				vv.sendData(Region1,Region2);
				R1votes=vv.Invalid3(R1votes,1);
				System.out.println("Final Valid R1votes: "+R1votes);
				R2votes=vv.Invalid3(R2votes,2);
				System.out.println("Final Valid R2votes: "+R2votes);
				R1Result=obj.regionVoteIterator(R1votes);
				R2Result=obj.regionVoteIterator(R2votes);
				
				vr.finalRegionsResult(R1Result,R2Result,vr1,vr2);
			
			}
			
			

			public HashMap<String,Integer> regionVoteIterator(HashMap<Integer,String> list)
			{
				Input allcon=new Input();
				HashMap<String,Integer> allContestants=allcon.allContestants();
				
				VoteCounting vote=new VoteCounting();
				for(String value :list.values())
				{
					allContestants=vote.voteCounting(value,allContestants);
				}
				return allContestants;
			}
			
			
			public HashMap<String,Integer> voteCounting(String v1,HashMap<String,Integer> temp)
			{
				int points=0;
				for(int i=0;i<v1.length();i++)
				{
					char c=v1.charAt(i);
					String z=Character.toString(c);
					boolean flag=temp.containsKey(z);
						if(flag)
						{
							if(i==0)
							{
								points=points+3;
								temp.put(z,temp.get(z)+points);
								points=0;
							}
							else if(i==1)
							{
								points=points+2;
								temp.put(z,temp.get(z)+points);
								points=0;
							}
							else
							{
								points=points+1;
								temp.put(z,temp.get(z)+points);
								points=0;
							}
						}
				}
				return temp;
			}
}
	


