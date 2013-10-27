//////////////////////////////////////////////////////////////////
//	こいつを継承して使う場合はgame.javaを参考に作ってください。
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

import javax.microedition.khronos.opengles.GL10;

public interface Scene {

	public void Init();
	public void Uninit();
	public void Update();
	public void Draw( GL10 gl );
}
