package com.voter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VotingResults 
{

			HashMap<String, Integer> R1Result=new HashMap<String, Integer>();
			HashMap<String, Integer> R2Result=new HashMap<String, Integer>();
			HashMap<String, Integer> R3Result=new HashMap<String, Integer>();
			static int total_R1;
			static int total_R2;
			
			
			public void totalVotes(int vote_count, int region) 
			{
				if(region==1)
				{
					this.total_R1=vote_count;
				}
				else
				{
					this.total_R2=vote_count;
				}
				
			}
			
			public void finalRegionsResult(HashMap<String, Integer> R1Result, HashMap<String, Integer> R2Result, HashMap<Integer, String> r1votes, HashMap<Integer, String> r2votes) 
			{
				
				VotingResults obj=new VotingResults();
				this.R1Result=R1Result;
				this.R2Result=R2Result;
				
				int x=Collections.max(R1Result.values());
				int y=Collections.max(R2Result.values());
				String R1winner=obj.regionalHead(x, R1Result);
				String R2winner=obj.regionalHead(y, R2Result);
		
				Map<String, Integer> R3 = Stream.concat(R1Result.entrySet().stream(), R2Result.entrySet().stream())
					    .collect(Collectors.groupingBy(Map.Entry::getKey,
					             Collectors.summingInt(Map.Entry::getValue)));
				
				R3Result=(HashMap<String,Integer>) R3;
				int z=Collections.max(R3Result.values());
				String chief=obj.regionalHead(z, R3Result);
				
				int validR1count=r1votes.size();
				int validR2count=r2votes.size();
				
				int invalidR1count=total_R1-validR1count;
				int invalidR2count=total_R2-validR2count;
				
				Final finalresult= new Final();
				finalresult.displayResult(invalidR1count,invalidR2count,R1winner,R2winner,chief,x,y,z);
				}
		
			
			public String regionalHead(int k,HashMap<String,Integer> region)
			{
				for(Map.Entry<String,Integer> entry :region.entrySet())
				{
					if (entry.getValue().equals(k)) 
					{
			            return entry.getKey(); 
					}
				}
				return null;
			}
}
