package com.example.data;

import java.util.HashMap;

import com.example.user.Enemy;
import com.example.user.Player;

public class CharactorInfomation {

	private String name;
	private String explation;

	private int hp;
	private int attack;
	private int deffence;
	private int speed;
	
	private int walk_id;
	private int attack_before_id;
	private int attack_after_id;

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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDeffence() {
		return deffence;
	}

	public void setDeffence(int deffence) {
		this.deffence = deffence;
	}

	public static HashMap<String, CharactorInfomation> getCharahash() {
		return charahash;
	}

	public static void setCharahash(HashMap<String, CharactorInfomation> charahash) {
		CharactorInfomation.charahash = charahash;
	}	
	
	public int getWalk_id() {
		return walk_id;
	}

	public void setWalk_id(int walk_id) {
		this.walk_id = walk_id;
	}

	public int getAttack_before_id() {
		return attack_before_id;
	}

	public void setAttack_before_id(int attack_before_id) {
		this.attack_before_id = attack_before_id;
	}

	public int getAttack_after_id() {
		return attack_after_id;
	}

	public void setAttack_after_id(int attack_after_id) {
		this.attack_after_id = attack_after_id;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}	
	
	/////////////////////////ê√ìIÇ»èÓïÒ///////////////////////////////

	public static HashMap<String, CharactorInfomation> charahash;
	
	public static void Init()
	{
		charahash = new HashMap<String, CharactorInfomation>();
	}
	
	public static void AddCharactor( CharactorInfomation chara )
	{
		charahash.put(chara.getName(), chara);
	}
	
	public static CharactorInfomation GetCharactorInformation( String name )
	{
		return charahash.get(name);
	}
	
	public static Player GetPlayerFromName( String name )
	{
		CharactorInfomation ci = charahash.get(name);		
		if( ci == null )return null;		
		return new Player( ci.getHp() , ci.getAttack() , ci.getSpeed() , ci.getWalk_id() , ci.getAttack_before_id() , ci.getAttack_after_id() );
	}
		
	public static Enemy GetEnemyFromName( String name )
	{
		CharactorInfomation ci = charahash.get(name);		
		if( ci == null )return null;		
		return new Enemy( ci.getHp() , ci.getAttack() , -ci.getSpeed() , ci.getWalk_id() , ci.getAttack_before_id() , ci.getAttack_after_id() );
	}
		
	public static CharactorInfomation Create(String name,String explation,int hp,int attack,int deffence,int speed,int walk_id,int attack_before_id,int attack_after_id )
	{
		CharactorInfomation ci = new CharactorInfomation();
		ci.setName(name);
		ci.setExplation(explation);
		ci.setHp(hp);
		ci.setAttack(attack);
		ci.setDeffence(deffence);
		ci.setSpeed(speed);
		ci.setWalk_id(walk_id);
		ci.setAttack_before_id(attack_before_id);
		ci.setAttack_after_id(attack_after_id);
		return ci;
	}
	
}
