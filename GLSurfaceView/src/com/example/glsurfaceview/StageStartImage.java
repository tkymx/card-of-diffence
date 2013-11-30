package com.example.glsurfaceview;

import com.example.data.DataBase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StageStartImage extends Activity {

	ImageView image = null;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		image = new ImageView(this);
		image.setOnClickListener(new SampleClickListener());
		
		setContentView(image);
	}
	
	public class ImageView extends View {

		private Bitmap image;

		public ImageView(Context context) {
			super(context);
			setBackgroundColor(Color.WHITE);

			// ���\�[�X�̉摜�t�@�C���̓ǂݍ���
			Resources r = context.getResources();
			image = BitmapFactory.decodeResource(r, DataBase.getPresentStage().getStage_select_image_id() );
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// �C���[�W�`��			
			int w = image.getWidth();
			int h = image.getHeight();
			// �`�挳�̋�`�C���[�W
			Rect src = new Rect(0, 0, w, h);
			// �`���̋�`�C���[�W
			Rect dst = new Rect(0, 0 , Const.rx(1), Const.ry(1) );
			canvas.drawBitmap(image, src, dst, null);
			
			Paint paint = new Paint();
			paint.setTextSize( Const.ry(0.08) );
			paint.setColor(Color.MAGENTA);
			
			canvas.drawText("�^�b�`���ăX�^�[�g���܂��B", Const.rx(0.22), Const.ry(0.84), paint );
			
		}
	}	
	
	class SampleClickListener implements android.view.View.OnClickListener{
		public void onClick(View v) {
			if(v.equals( image ))
			{
				//�Q�[���̊J�n
				SceneManager.ChangeScene( SceneManager.gameKey );

				//����
				finish();
				
			}
		}
	}	
	
}
