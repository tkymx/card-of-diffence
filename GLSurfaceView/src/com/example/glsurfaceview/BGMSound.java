package com.example.glsurfaceview;

import android.media.MediaPlayer;

public class BGMSound {
	
	private class BGM
	{
		public String name;
		public boolean isLoop;
		public boolean isPlay;
		
		public BGM( String s, boolean loop )
		{
			name = s;
			isLoop = loop;
			isPlay = false;
		}
	}
	
	public static BGM[] BGMChoose = new BGM[1];
	
	private static BGMSound Instance;
	// 1BGM�ɑ΂���1��
	private MediaPlayer player;							// �v���C���[
	
	// �C���X�^���X�擾
	public BGMSound getInstance()
	{
		return Instance;
	}

	public BGMSound()
	{
		// ���f�B�A�v���C���[�̐���
		try
		{
			//player = MediaPlayer.create(OpenGLSurfaceView.c, R.id.action_settings);
			player.setLooping(BGMChoose[0].isLoop);
		}
		catch ( Exception e )
		{
		}
	}
	
	public void Play( int id )
	{
		// �܂��Đ�����Ă��Ȃ��Ƃ�
		if( BGMChoose[id].isPlay == false )
		{
			player.seekTo(0);
			player.start();
			
			BGMChoose[id].isPlay = true;
		}
	}
	
	public void Stop( int id )
	{
		// �Đ�����Ă���Ƃ�
		if( BGMChoose[id].isPlay == true )
		{
			player.pause();
			
			BGMChoose[id].isPlay = false;
		}
	}
}
