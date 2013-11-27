package com.example.data;

import java.util.HashMap;

import com.example.user.Card;

/**
 * 
 * 基本的なカードの状態がわかる
 * 
 * カードの情報を保持しており、名前を指定することでカード情報を取得することができる
 * 
 * @author ultra-tkymx
 *
 */

public class CardInformation {
	
	//基本情報////////////////////////////////////////////
	
	private String name;
	private int need;
	private int card_id;	
	private Card kind;
	
	public int getNeed() {
		return need;
	}
	public void setNeed(int need) {
		this.need = need;
	}	
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	public String getName() {
		return kind.GetNameForList(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public Card getKind() {
		return kind;
	}
	public void setKind(Card kind) {
		this.kind = kind;
	}
	
	
	//静的な情報////////////////////////////////////////////
	
	//カードの情報をセットしておく
	private static HashMap<String, CardInformation> cards;
	public static void SetCardInforation( CardInformation ci )
	{
		cards.put(ci.getName(), ci);
	}
	//名前からカード情報を取得
	public static CardInformation GetCardInformaionFromName( String str )
	{
		return cards.get(str);
	}	
	//名前からカード情報を取得
	public static Card GetCardFromName( String str , int l,int t,int w,int h  )
	{
		CardInformation ci = GetCardInformaionFromName(str);
		if(ci==null)return null;
		
		return ci.getCard(l, t, w, h);
	}
	public static CardInformation CreateCardInformation(  String name,Card kind,int id,int need )
	{
		return new CardInformation( name , kind , id , need );
	}	
	//初期化
	public static void Init()
	{
		cards = new HashMap<String, CardInformation>();
	}
	
	//コンストラクタ情報////////////////////////////////////////////
	
	private CardInformation( String name,Card kind,int id,int need  )
	{
		this.name = name;
		this.kind = kind;
		this.card_id = id;
		this.need = need;
	}
	
	//カード情報からカードを取得
	public Card getCard(int l, int t,int w,int h )
	{
		//基本的にカードだけの情報として、カードクラスに描画部分をになってもらうのもいいかもしれない		
		return kind.Create(l, t, w, h, card_id, name, need);
	}
	
	//読み込み可能なデータの取得////////////////////////////////////////////
	public String getExplain()
	{
		return kind.GetExplainForList(name);
	}
	public String getParameter1()
	{
		return kind.GetParameter1ForList(name);
	}
	public String getparameter2()
	{
		return kind.GetParameter2ForList(name);		
	}
	
	
	
}
