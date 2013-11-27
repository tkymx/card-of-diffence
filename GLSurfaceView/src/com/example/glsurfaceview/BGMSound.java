package com.example.glsurfaceview;

import android.media.MediaPlayer;

public class BGMSound {
	
	// サウンド名
	public enum SOUND
	{
		SOUND_TITLE( 0 ),
		SOUND_ENTER( 1 ),
		SOUND_GAME( 2 ),
		SOUND_TOUCH( 3 ),
		SOUND_MAX( 4 );
		
		private int value;
		
		private SOUND( int n )
		{
			value = n;
		}
		
		public int getValue()
		{
			return value;
		}
	};
	
	// サウンド情報用構造体
	private class Info
	{
		public int ID;
		public boolean isLoop;
		
		private Info( int id, boolean loop )
		{
			ID = id;
			isLoop = loop;
		}
	}
	
	// サウンド情報
	private Info[] soundID = {
			new Info( R.raw.se02, false ),
			new Info( R.raw.se03, false ),
			new Info( R.raw.sample, true ),
			new Info( R.raw.se01, false ),
	};
	
	public class BGM
	{
		private boolean isLoop;
		private MediaPlayer player;							// プレイヤー
		private int nowPlay;
		private boolean isPlay;
		
		public BGM( int id, boolean loop )
		{
			isLoop = loop;
			nowPlay = 0;
			isPlay = false;
			
			player = MediaPlayer.create(OpenGLSurfaceView.c, id);
			player.setLooping(isLoop);
		}
		
		public void Play()
		{
			if( !isPlay )
			{
				player.seekTo(nowPlay);
				player.start();
				isPlay = true;
			}
		}
		
		public void Replay()
		{
			if( isPlay )
			{
				nowPlay = player.getCurrentPosition();
				
				player.seekTo(nowPlay);
				player.start();
			}
		}
		
		public void Stop()
		{
			// 再生されているとき
			if( isPlay == true )
			{
				player.pause();
		
				nowPlay = 0;
				isPlay = false;
			}
		}
		
		public void StopPause()
		{
			if( player.isPlaying() == true )
			{
				nowPlay = player.getCurrentPosition();
				
				player.pause();
			}
		}
	}
	
	
	public static BGM[] BGMChoose = new BGM[SOUND.SOUND_MAX.getValue()];	// BGMの呼び出し配列	
	private static BGMSound Instance = new BGMSound();						// インスタンス
	
	// インスタンス取得
	public static BGMSound getInstance()
	{
		return Instance;
	}

	public BGMSound()
	{
		/*try
		{
			//player = MediaPlayer.create(OpenGLSurfaceView.c, R.id.action_settings);
			//player.setLooping(BGMChoose[0].isLoop);
		}
		catch ( Exception e )
		{
		}*/
	}
	
	public void loadBGM()
	{
		// メディアプレイヤーの生成
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			BGMChoose[i] = new BGM( soundID[i].ID, soundID[i].isLoop );
		}
	}
	
	//　すべての再生を切る
	public void stopAll()
	{
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			BGMChoose[i].StopPause();
		}
	}
	
	// 再生しているものだけ再生
	public void replayAll()
	{
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			BGMChoose[i].Replay();
		}
	}
	
	// 再生監視
	public void playWatching()
	{
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			if( BGMChoose[i].isPlay )
			{
				if( !BGMChoose[i].player.isPlaying() )
				{
					BGMChoose[i].isPlay = false;
					BGMChoose[i].nowPlay = 0;
				}
			}
		}
	}
}
