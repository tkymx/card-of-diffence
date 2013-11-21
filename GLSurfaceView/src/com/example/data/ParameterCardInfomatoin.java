package com.example.data;

import java.util.HashMap;

/*
 * 
 * カードのパラメーターを設定できるクラス
 * 
 * 魔法カードとトラップカード兼用
 * 
 * 魔法カードクラス、トラップカードクラスからパラメーター文字列を取得する際に使用する予定
 * 
 */

public class ParameterCardInfomatoin {

	private String name;
	private String explation;

	private int parameter1;
	private int parameter2;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExplation() {
		return explation;
	}
	public void setExplation(String explation) {
		this.explation = explation;
	}
	public int getParameter1() {
		return parameter1;
	}
	public void setParameter1(int parameter1) {
		this.parameter1 = parameter1;
	}
	public int getParameter2() {
		return parameter2;
	}
	public void setParameter2(int parameter2) {
		this.parameter2 = parameter2;
	}
	
	
	///////////////////////////////静的な情報////////////////////////////////////////////
	
	private static HashMap<String, ParameterCardInfomatoin> magichash;
	
	public static void Init()
	{
		magichash = new HashMap<String, ParameterCardInfomatoin>();
	}
	
	public static void AddParameterCard( ParameterCardInfomatoin mci )
	{
		magichash.put(mci.getName() , mci );
	}
	
	public static ParameterCardInfomatoin GetMagicCard( String str )
	{
		return magichash.get(str);
	}
	
	////////////////////////////////////コンストラクタ////////////////////////////////////////
	
	private ParameterCardInfomatoin( String name , String explain , int parameter1 , int parameter2 )
	{		
		this.name = name;
		this.explation = explain;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}
	
	public static ParameterCardInfomatoin Create(String name , String explain , int parameter1 , int parameter2)
	{
		return new ParameterCardInfomatoin(name, explain, parameter1, parameter2);		
	}
	
}
