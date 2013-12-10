package com.example.scene;

import javax.microedition.khronos.opengles.GL10;

import android.content.Intent;
import com.example.glsurfaceview.BGMSound;
import com.example.glsurfaceview.BGMSound.SOUND;
import com.example.glsurfaceview.Button;
import com.example.glsurfaceview.Const.SpriteType;
import com.example.glsurfaceview.MainActivity;
import com.example.glsurfaceview.OpenGLSurfaceView;
import com.example.glsurfaceview.R;
import com.example.glsurfaceview.Scene;
import com.example.glsurfaceview.Sprite;
import com.example.glsurfaceview.Text;

public class TitleScene extends Scene {

	private Button playButton;
	
	@Override
	public void Init() {
		// TODO Auto-generated method stub
		int w = MainActivity.width;
		int h = MainActivity.height;
		
		Sprite.Create(0, 0, w, h, R.drawable.title, SpriteType.TYPE_OTHER.getValue());
		Sprite.Create(w/10, h-h/3, w/6*5, h/4, R.drawable.titlelogo, SpriteType.TYPE_TEXT.getValue());
		playButton = Button.Create(MainActivity.width/3, MainActivity.height/4, 
				MainActivity.width/3, MainActivity.height/3, 
				R.drawable.playbutton);
		
		String text = "‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨‚ ‚¢‚¤‚¦‚¨";
		float[] color = { 1.0f, 1.0f, 1.0f, 1.0f };
		Text.Create(0, 0, 500, 100, text, color);
	}

	@Override
	public void Uninit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Update() {

		super.Update();		
		
		if( playButton.IsTouch() )
		{
			Intent intent = new Intent();
			intent.setClass( OpenGLSurfaceView.c , com.example.glsurfaceview.StageSelect.class);
			OpenGLSurfaceView.c.startActivity(intent);
			
			BGMSound.BGMChoose[SOUND.SOUND_ENTER.getValue()].Play();
		}
	}

	@Override
	public void Draw(GL10 gl) {

		super.Draw(gl);
		
	}

}
