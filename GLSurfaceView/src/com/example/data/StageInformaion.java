package com.example.data;

import java.util.ArrayList;

import com.example.user.Stage;

public class StageInformaion {

	///////////////////////////////Ã“I‚Èî•ñ////////////////////////
	
	private static ArrayList<Stage> stages;
	
	public static void Init()
	{
		stages = new ArrayList<Stage>();
	}
	
	public static void AddStage( Stage s )
	{
		stages.add(s);
	}
	public static Stage GetStage(int i )
	{
		return stages.get(i);
	}
	public static int GetStageNum()
	{
		return stages.size();
	}
	
}
