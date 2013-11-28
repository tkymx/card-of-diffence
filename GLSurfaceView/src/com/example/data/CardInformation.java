package com.example.data;

import java.util.HashMap;

import com.example.user.Card;

/**
 * 
 * ��{�I�ȃJ�[�h�̏�Ԃ��킩��
 * 
 * �J�[�h�̏���ێ����Ă���A���O���w�肷�邱�ƂŃJ�[�h�����擾���邱�Ƃ��ł���
 * 
 * @author ultra-tkymx
 *
 */

public class CardInformation {
	
	//��{���////////////////////////////////////////////
	
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
	
	
	//�ÓI�ȏ��////////////////////////////////////////////
	
	//�J�[�h�̏����Z�b�g���Ă���
	private static HashMap<String, CardInformation> cards;
	public static void SetCardInforation( CardInformation ci )
	{
		cards.put(ci.getName(), ci);
	}
	//���O����J�[�h�����擾
	public static CardInformation GetCardInformaionFromName( String str )
	{
		return cards.get(str);
	}	
	//���O����J�[�h�����擾
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
	//������
	public static void Init()
	{
		cards = new HashMap<String, CardInformation>();
	}
	
	//�R���X�g���N�^���////////////////////////////////////////////
	
	private CardInformation( String name,Card kind,int id,int need  )
	{
		this.name = name;
		this.kind = kind;
		this.card_id = id;
		this.need = need;
	}
	
	//�J�[�h��񂩂�J�[�h���擾
	public Card getCard(int l, int t,int w,int h )
	{
		//��{�I�ɃJ�[�h�����̏��Ƃ��āA�J�[�h�N���X�ɕ`�敔�����ɂȂ��Ă��炤�̂�������������Ȃ�		
		return kind.Create(l, t, w, h, card_id, name, need);
	}
	
	//�ǂݍ��݉\�ȃf�[�^�̎擾////////////////////////////////////////////
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
