package com.example.user;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Texture;
import com.example.glsurfaceview.Touch;

/**
 * 
 * 基本的はカードの描画とカードの状態の変更、理想的は内部に画像を持ち、
 * 更新の時に切り替えられるような処理ができれば理想である。
 * 
 * textureにその時のテクスチャを割り振っているが
 * 状態もtextureの設置されているインスタンスによって変えている
 * 
 * @author ultra-tkymx
 *
 */

public abstract class Card extends Sprite {

	//全体的の選択対象(選択されているカード)
	static Vector<Card> SelectedCard = new Vector<Card>();
	
	//生贄の数
	int neednum;

	//時間情報 -1で使わない
	long alive_time;
	//しようかかどうか
	boolean isUse;
			
	public void startCan() {
		//時間を設定できるようにする
		if( alive_time < 0 )
		{
			alive_time = System.currentTimeMillis();			
		}
	}
	public void setCan() {
		texture.SetColor( 1.0f,1.0f,1.0f,1.0f );
		isUse = true;
		
		//時間の初期化
		alive_time = -1;
	}
	public void setSelected() {
		texture.SetColor( 1.5f,1.2f,0.0f,0.5f );
	}
	public void setUsed() {
		texture.SetColor( 0.4f,0.4f,0.4f,1.0f );
		isUse = false;
	}	
	
	//継承先からしか生産できない
	protected Card(  )
	{		
	}
	protected Card( int left , int top, int width , int height , int id , int need )
	{
		//しようかにする
		isUse = true;
		//時間の初期化
		alive_time = -1;
		
		Init(left, top, width, height, id, SpriteType.TYPE_CARD.getValue());				
		//生贄の数
		neednum = need;
	}	
	
	//選択されている時の更新処理
	public abstract void SelectedUpdate();		
	//メインかどうか
	public boolean isMainSelected( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			if( SelectedCard.get( 0 ) == card )
			return true;
		}
		return false;
	}
	//サブかどうか
	public boolean isSubSelected( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			if( SelectedCard.get( 0 ) != card )
			return true;
		}
		return false;
	}
	//他のかどうか	
	public boolean isElseSelected( Card card )
	{
		if( SelectedCard.indexOf(card) == -1 )return true;
		return false;
	}
	//必要数に満たしているか
	public boolean isNeed()
	{
		if( SelectedCard.size() > 0 )
		{
			//メインの必要数
			int need = SelectedCard.get(0).neednum;
			//必要個数揃っていたら
			if( SelectedCard.size() == need )
			{
				return true;
			}			
		}
		return false;
	}
	//必要数の取得
	public int getNeed()
	{
		if( SelectedCard.size() > 0 )
		{
			//メインの必要数
			return SelectedCard.get(0).neednum;
		}
		return -1;
	}
	//セレクトの追加
	public void SelectedAdd( Card card )
	{
		card.setSelected();
		SelectedCard.add(card);
	}
	//セレクトのクリア
	public void SelectedClear(  )
	{
		for( Card card : SelectedCard )
		{
			card.setCan();
		}
		SelectedCard.clear();
	}
	public void SelectedClear( Card card )
	{
		if( SelectedCard.indexOf(card) != -1 )
		{
			card.setCan();
		}
		SelectedCard.remove(this);
	}
	//セレクトのクリア
	public void SelectedAllUsedClear(  )
	{
		for( Card card : SelectedCard )
		{
			card.setUsed();
		}
		SelectedCard.clear();
	}
	// 更新処理
	public boolean Update()
	{
		//自分が選択対象なら
		//使用可だったら
		if( isUse )
		{			
			//自分がメインの時
			if( isMainSelected(this) )
			{
				//タッチしたら
				if( IsTouch() )
				{
					//すべてを消す
					SelectedClear();
				}
				else
				{
					//個数が揃っていたら
					if( isNeed() )
					{
						//自分がタッチされていなかったら更新動作
						SelectedUpdate();						
					}
				}
			}
			//自分がサブの時
			else if( isSubSelected(this) )
			{
				if( IsTouch() )
				{
					//自分を消す
					SelectedClear(this);
				}
			}
			//自分が他の時
			else if( isElseSelected(this))
			{
				if( IsTouch() )
				{
					//個数が揃っていたら
					if( isNeed() )
					{
						//すべてを消して自分を入れる
						SelectedClear();
						//自分を入れる
						SelectedAdd(this);
					}
					//必要未満だったら
					else if( SelectedCard.size() < getNeed() || getNeed() == -1 )
					{
						SelectedAdd(this);
					}
				}
				else
				{
					setCan();
				}
			}
		}
		else
		{
			//0より上で時間判断開始
			if( alive_time > 0 )
			{
				//使えない時に時間経過で使えるようにする
				if( System.currentTimeMillis() - alive_time > 1000 )
				{
					setCan();
				}
			}
		}
		
		return true;		
	}	

	//カードを使用する
	public void use()
	{
		//すべてを消す
		SelectedAllUsedClear();
		//自分を使用済みにする
		setUsed();
	}
	
	//使用を許可する
	public void permitUse()
	{
		startCan();
	}

	
	//今使用できるかをチェックする
	public boolean IsUse()
	{
		return isUse;
	}
	
	/*
	 * カードが選択画面に表示されている時の表示についての抽象関数を作成しておく
	 */
	public abstract String GetNameForList(String name);
	public abstract String GetExplainForList(String name);
	public abstract String GetParameter1ForList(String name);
	public abstract String GetParameter2ForList(String name);
	

	/*
	 * カードが作成された時の作成抽象関数を作っておく
	 */
	public abstract Card Create( int left , int top, int width , int height , int id , String name , int need  );
	
	
}
