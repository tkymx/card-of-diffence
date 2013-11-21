package com.example.data;

import java.util.HashMap;

/*
 * 
 * �J�[�h�̃p�����[�^�[��ݒ�ł���N���X
 * 
 * ���@�J�[�h�ƃg���b�v�J�[�h���p
 * 
 * ���@�J�[�h�N���X�A�g���b�v�J�[�h�N���X����p�����[�^�[��������擾����ۂɎg�p����\��
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
	
	
	///////////////////////////////�ÓI�ȏ��////////////////////////////////////////////
	
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
	
	////////////////////////////////////�R���X�g���N�^////////////////////////////////////////
	
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
