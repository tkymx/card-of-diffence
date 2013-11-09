package com.example.user;

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
	static Card SelectedCard = null;
	
	//選択時
	Texture can;
	//選択時
	Texture selected;
	//使えない時
	Texture used;	
		
	public Texture getCan() {
		return can;
	}
	public void setCan(Texture can) {
		this.can = can;
	}
	public Texture getSelected() {
		return selected;
	}
	public void setSelected(Texture selected) {
		this.selected = selected;
	}
	public Texture getUsed() {
		return used;
	}
	public void setUsed(Texture used) {
		this.used = used;
	}	
	
	//継承先からしか生産できない
	protected Card( int left , int top, int width , int height , int id , int s_id , int u_id )
	{
		setCan( new Texture(id) );
		setSelected( new Texture(s_id) );
		setUsed( new Texture(u_id) );	
		Init(left, top, width, height, id, SpriteType.TYPE_CARD.getValue());		
	}	
	
	
	//選択されている時の更新処理
	public abstract void SelectedUpdate();		
	// 更新処理
	public boolean Update()
	{
		//自分が選択対象なら
		//使用可だったら
		if( GetTexture() != used )
		{
			if( SelectedCard == this )
			{
				//セレクト次の更新処理
				SelectedUpdate();
				
				//押された時、もとに戻す
				if( IsTouch() )
				{
					//しようかにする
					SetTexture( can );					
					SelectedCard = null;
				}						
				
			}
			else
			//自分が選択対象じゃなかったら
			{
				//押された時、選択にする
				if( IsTouch() )
				{
					Touch touch = Touch.getInstance();
					//セレクト状態にする。
					SetTexture( selected );					
					SelectedCard = this;
					
					IsTouch();
				}
				//普通に戻す
				else if( GetTexture() != can )
				{
					//普通のカードにする
					SetTexture( can );
				}
			}
		}
		
		return true;		
	}	

	//カードを使用する
	public void use()
	{
		SelectedCard = null;
		SetTexture( used );
	}
	
	//使用を許可する
	public void permitUse()
	{
		SetTexture( can );
	}

	
	//今使用できるかをチェックする
	public boolean IsUse()
	{
		return GetTexture() != used;
	}
	
}
