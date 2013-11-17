package com.example.glsurfaceview;

import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeckEdit extends Activity {
	public static final int THE_NUMBER_OF_DECK = 11;//�f�b�L�̖����@��11��
	
	TextView title;//�u�f�b�L�ҏW��ʁv�Ƃ�������
	TextView instruct;//�u�J�[�h���^�b�`���āA�f�b�L��ҏW���悤�I�v
	List<CardData> deckList;//�����̃f�b�L�̃��X�g�f�[�^�@����CardData�N���X��List ����ArrayList�Ƃ��Đ������Ă�
	ImageView[] member = new ImageView[THE_NUMBER_OF_DECK];//�\������11����ImageView
	Button back;//�߂�@�{�^��
	
	LinearLayout ll;//�S�̂𕢂�Layout
	LinearLayout topRow;//��i��Layout
	LinearLayout bottomRow;//���iLayout
	
	LinearLayout.LayoutParams LayoutParam;
	LinearLayout.LayoutParams titleParam;//�p�����[�^�[
	LinearLayout.LayoutParams topParam;//�p�����[�^�[
	LinearLayout.LayoutParams bottomParam;//�p�����[�^�[
	LinearLayout.LayoutParams backParam;//�p�����[�^�[
	LinearLayout.LayoutParams imageParam;//�p�����[�^�[
	
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	//���낢�됶������
		//���C�A�E�g�̐���&�ݒ�
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		topRow = new LinearLayout(this);
		topRow.setOrientation(LinearLayout.HORIZONTAL);
		topRow.setGravity(Gravity.CENTER);
		bottomRow = new LinearLayout(this);
		bottomRow.setOrientation(LinearLayout.HORIZONTAL);
		bottomRow.setGravity(Gravity.CENTER);
		
	//�p�����[�^
		//�傫���ݒ�
			
		titleParam = new LinearLayout.LayoutParams(Const.rx(0.5), Const.ry(0.2));
		topParam = new LinearLayout.LayoutParams(Const.rx(0.9), Const.ry(0.3));
		bottomParam = new LinearLayout.LayoutParams(Const.rx(0.9), Const.ry(0.3));
		backParam = new LinearLayout.LayoutParams(Const.rx(0.3), Const.ry(0.1));
		imageParam = new LinearLayout.LayoutParams(Const.rx(0.1), Const.ry(0.25));
		//�C���[�W�̊Ԋu�ݒ�
		imageParam.setMargins(Const.rx(0.05), Const.ry(0.05), 0, Const.ry(0.05));
		topParam.setMargins(Const.rx(0.05), Const.ry(0.05),Const.rx(0.05), Const.ry(0.05));
		bottomParam.setMargins(Const.rx(0.05), Const.ry(0.05),Const.rx(0.05), Const.ry(0.05));
		
		//������
		title = new TextView(this);
		instruct = new TextView(this);
		deckList = new ArrayList<CardData>();
		back = new Button(this);
		for(int i=0;i<THE_NUMBER_OF_DECK;i++){
			member[i] = new ImageView(this);
			member[i].setImageResource(R.drawable.list);
			if(i<5)topRow.addView(member[i],imageParam);
			if(i>4)bottomRow.addView(member[i],imageParam);
			
		}
		title.setText("�f�b�L�ҏW���");
		back.setText("�߂�");
		
		
		
		//���낢��Z�b�g
		setContentView(ll);
		ll.addView(title,titleParam);
		ll.addView(topRow,topParam);
		ll.addView(bottomRow,bottomParam);
		ll.addView(back,backParam);
		
	
		
		
		
		
	}
}
