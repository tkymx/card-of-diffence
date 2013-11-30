package com.example.user;

import java.util.LinkedList;

import com.example.glsurfaceview.Const;
import com.example.glsurfaceview.Evolution;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Vector3;

/*
 * HPBarの表示を行う。
 * 
 * 方法
 * 		spriteListからキャラクターを取得してくる
 * 		キャラクタの名前を表示、HPの後ろをヒ表示、HPのバーを表示
 * 
 * 		味方は左側に
 * 		敵は右側に配置する
 * 
 */


public class CharactorHPBarSort extends Sprite {

	@Override
	public void Init() {
		super.Init();

	
	}
	
	@Override
	public boolean Update() {
		if(super.Update()==false)return false;

		//今追加されているキャラクタのHPバーの並び替えをする。
		LinkedList<PlayerHPBar> players = new LinkedList<PlayerHPBar>();
		LinkedList<EnemyHPBar> enemys = new LinkedList<EnemyHPBar>();
		
		for( Sprite sp : spriteList.get( Const.SpriteType.TYPE_TEXT.getValue() ) )
		{
			if( sp instanceof PlayerHPBar )
			{
				players.add((PlayerHPBar)sp);
			}
			if( sp instanceof EnemyHPBar )
			{
				enemys.add((EnemyHPBar)sp);
			}
		}
		
		//何かあったらやる
		if( players.size() != 0 )
		{			
			//位置を抜き取られる				
			float start_y = Const.ry(0.23);
			float start_h = players.get(0).GetHeight() + Const.ry(0.01);
			
			for( int i = 0 ; i < players.size() ; i++ )
			{
				players.get(i).SetPosotion( start_y + start_h * i );
			}
			Evolution.touchCursol.setTrans(new Vector3( MainActivity.width/12, start_y + start_h * players.size(), 0 ));
		}
		if( enemys.size() != 0 )
		{			
			//位置を抜き取られる				
			float start_y = Const.ry(0.23);
			float start_h = enemys.get(0).GetHeight() + Const.ry(0.01);
			
			for( int i = 0 ; i < enemys.size() ; i++ )
			{
				enemys.get(i).SetPosotion( start_y + start_h * i );
			}
		}		
		
		return true;
	}
	
}
