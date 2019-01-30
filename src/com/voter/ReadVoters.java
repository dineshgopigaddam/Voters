package com.voter;

import java.io.*;
import java.util.HashMap;

public class ReadVoters 
{
	HashMap<Integer, String> R1votes = new HashMap<Integer, String>();
	HashMap<Integer, String> R2votes = new HashMap<Integer, String>();
	
	HashMap<Integer, String> temp = new HashMap<Integer, String>();
	HashMap<Integer, String> temp1 = new HashMap<Integer, String>();
	
	HashMap<Integer,String> invalid=new HashMap<Integer,String>();

	public Input readVoters(int count) throws IOException 
	{
		Input rv1 = new Input();
		Input rv2 = new Input();
		
		FilterInput data = new FilterInput();
		VotingResults votes= new VotingResults();

		FileReader file = new FileReader("/Users/dineshgaddam/Downloads/voting.dat");
		BufferedReader reader = new BufferedReader(file);

		String s1 = "";
		int count1 = count + 1;
		int flag=0;

		String label = "R" + count;
		String label1 = "R" + count1;

		String line = reader.readLine();

		flow: while (line != null) 
		{
			if ((line.length() >= 2) && !(line.contains("/"))) 
			{
				if (line.contains(label)) 
				{
					line = reader.readLine();
					while (!(line.contains(label1)) || line.contains("&&")) 
					{
						if (line.contains("&&")) 
						{
							file.close();
							break flow;
						}
						s1 = s1 + line;
						flag=flag+1;							
						temp = data.filterVotes(s1,count);
						temp1.putAll(temp);
						temp.clear();
						s1 = "";
						line = reader.readLine();
					}
					if (line.contains(label1)) 
					{
						file.close();
						break flow;
					}
				} 
				else if (!(line.contains(label))) 
				{
					line = reader.readLine();
				}
			} 
			else 
			{

				line = reader.readLine();
			}
		}

		switch (count) 
		{
		case 1:
			votes.totalVotes(flag, count);
			R1votes.putAll(temp1);
			rv1.setR1votes(R1votes);
			temp1.clear();
			return rv1;
		case 2:
			votes.totalVotes(flag, count);
			R2votes.putAll(temp1);
			rv2.setR2votes(R2votes);
			temp1.clear();
			break;
		}
		return rv2;

	}
}
