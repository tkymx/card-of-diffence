package com.example.glsurfaceview;

import android.media.MediaPlayer;

public class BGMSound {
	
	// �T�E���h��
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
	
	// �T�E���h���p�\����
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
	
	// �T�E���h���
	private Info[] soundID = {
			new Info( R.raw.se02, false ),
			new Info( R.raw.se03, false ),
			new Info( R.raw.sample, true ),
			new Info( R.raw.se01, false ),
	};
	
	public class BGM
	{
		private boolean isLoop;
		private MediaPlayer player;							// �v���C���[
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
			// �Đ�����Ă���Ƃ�
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
	
	
	public static BGM[] BGMChoose = new BGM[SOUND.SOUND_MAX.getValue()];	// BGM�̌Ăяo���z��	
	private static BGMSound Instance = new BGMSound();						// �C���X�^���X
	
	// �C���X�^���X�擾
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
		// ���f�B�A�v���C���[�̐���
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			BGMChoose[i] = new BGM( soundID[i].ID, soundID[i].isLoop );
		}
	}
	
	//�@���ׂĂ̍Đ���؂�
	public void stopAll()
	{
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			BGMChoose[i].StopPause();
		}
	}
	
	// �Đ����Ă�����̂����Đ�
	public void replayAll()
	{
		for( int i = 0; i < SOUND.SOUND_MAX.getValue(); i++ )
		{
			BGMChoose[i].Replay();
		}
	}
	
	// �Đ��Ď�
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
