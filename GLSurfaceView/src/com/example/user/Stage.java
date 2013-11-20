package com.example.user;

public class Stage {

	private int stage_select_image_id;
	private int stage_background_image_id;
	private int stage_enemy_castle_id;
	private int stage_player_castle_id;

	//レベル
	private int level;

	//クリア後に行けるようになるステージ番号
	private int clear_stage_number;
	
	//敵のカード
	String[] enemyDeck = null;
	
	public int getStage_select_image_id() {
		return stage_select_image_id;
	}

	public void setStage_select_image_id(int stage_select_image_id) {
		this.stage_select_image_id = stage_select_image_id;
	}

	public int getStage_background_image_id() {
		return stage_background_image_id;
	}

	public void setStage_background_image_id(int stage_background_image_id) {
		this.stage_background_image_id = stage_background_image_id;
	}

	public int getStage_enemy_castle_id() {
		return stage_enemy_castle_id;
	}

	public void setStage_enemy_castle_id(int stage_enemy_castle_id) {
		this.stage_enemy_castle_id = stage_enemy_castle_id;
	}

	public int getStage_player_castle_id() {
		return stage_player_castle_id;
	}

	public void setStage_player_castle_id(int stage_player_castle_id) {
		this.stage_player_castle_id = stage_player_castle_id;
	}

	public int getClear_stage_number() {
		return clear_stage_number;
	}

	public void setClear_stage_number(int clear_stage_number) {
		this.clear_stage_number = clear_stage_number;
	}

	public String[] getEnemyDeck() {
		return enemyDeck;
	}

	public void setEnemyDeck(String[] enemyDeck) {
		this.enemyDeck = enemyDeck;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}	
	
	
	//ステージの情報
	public Stage(int ssii,int sbii,int seci,int spci,int csn,int level,String[] ed)
	{
		stage_select_image_id = ssii;
		stage_background_image_id = sbii;
		stage_enemy_castle_id = seci;
		stage_player_castle_id = spci;
		
		this.level = level;
		
		clear_stage_number = csn;		
		
		enemyDeck = new String[11];
		for(int i=0;i<11;i++)
		{
			enemyDeck[i] = ed[i];
		}
	}
	
	
	
}
