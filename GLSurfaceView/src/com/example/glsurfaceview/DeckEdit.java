package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeckEdit extends Activity {
	public static final int THE_NUMBER_OF_DECK = 11;//�f�b�L�̖����@��11��
	
	TextView title;//�u�f�b�L�ҏW��ʁv�Ƃ�������
	TextView instruct;//�u�J�[�h���^�b�`���āA�f�b�L��ҏW���悤�I�v
	List<CardData> deckList;//�����̃f�b�L�̃��X�g�f�[�^�@����CardData�N���X��List ����ArrayList�Ƃ��Đ������Ă�
	ImageView[] member = new ImageView[THE_NUMBER_OF_DECK];//�\������11����ImageView
	Button back;//�߂�@�{�^��
	
	LinearLayout ll = new LinearLayout(this);//�S�̂𕢂�Layout
	LinearLayout ll2 = new LinearLayout(this);//�S�̂𕢂�Layout
	LinearLayout ll3 = new LinearLayout(this);//�S�̂𕢂�Layout
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//���낢�됶������
		title = new TextView(this);
		instruct = new TextView(this);
		deckList = new ArrayList<CardData>();
		back = new Button(this);
		for(int i=0;i<THE_NUMBER_OF_DECK;i++){
			member[i] = new ImageView(this);
		}
		
		
		
	
	}
}
