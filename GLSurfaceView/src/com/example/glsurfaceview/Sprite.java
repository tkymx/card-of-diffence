//////////////////////////////////////////////////////////////////
//	��{�I�ɂ��̃N���X�̓V�[���ŌĂяo���ăC���X�^���X�𐶐����Ă��������B
//	�������@�� Sprite s = Scene.Create(~); ���Ċ����ł��B
//	�쐬����ۂ�SpriteType�Ń��X�g�z��̂ǂ��ɂ���邩�����߂Ă��������B
//	�������ړ����������ꍇ�� transToVector�֐��Ɉړ��ʂ�Vector3�^�œ���Ă�������
//	������j���������ꍇ�� remove�֐����Ă�ł��������B
//	��]�Ɋւ��Ă͌��ݒ������ł��B
//////////////////////////////////////////////////////////////////
package com.example.glsurfaceview;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class Sprite {
	
	// TODO ���X�g����͂���
	public static ArrayList<LinkedList<Sprite>> spriteList = new ArrayList<LinkedList<Sprite>>();
	public static final int maxSpriteListNum = Const.SpriteType.TYPE_MAX.getValue();
	
	protected VertexBuffer vertexBuffer;
	protected Texture texture;
	protected Vector3 trans = new Vector3();
	protected Vector3 rot = new Vector3();
	protected Vector3 scl = new Vector3( 1.0f, 1.0f, 1.0f );
	protected boolean bUse;
	protected boolean bTextureSend;
	protected float m_width, m_height;
	protected int spriteType = Const.SpriteType.TYPE_OTHER.getValue();
	private static boolean isCreate = false;
	private int TextureIDBackUp;
		

	public float getM_width() {
		return m_width;
	}

	public void setM_width(float m_width) {
		this.m_width = m_width;
	}

	public float getM_height() {
		return m_height;
	}

	public void setM_height(float m_height) {
		this.m_height = m_height;
	}	
	
	//�e�N�X�`���̃Z�b�g������
	public void SetTexture( Texture tex )
	{
		//�e�N�X���̐ݒ�
		texture = tex;
		bTextureSend = false;
	}
	
	// �R���X�g���N�^
	public Sprite()
	{
	}
	
	// ����
	public static Sprite Create( float[] vertex, Vector3 pos, int id, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// ������
		s.Init( vertex, id, SpriteType );
		s.setTrans( pos );
		
		return s;
	}
	
	// ����
	public static Sprite Create( float[] vertex, int id, float[] UV, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// ������
		s.Init( vertex, id, UV, SpriteType );
		
		return s;
	}
	
	// ����
	public static Sprite Create( float[] vertex, int id, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// ������
		s.Init( vertex, id, SpriteType );
		
		return s;
	}

	// ���� ���ꐄ��
	public static Sprite Create( float left, float top, float width, float height, int id, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// ������
		s.Init( left, top, width, height, id, SpriteType );
		
		return s;
	}
	
	public static Sprite Create( float left, float top, float width, float height, int id, float[] UV, int SpriteType )
	{
		Sprite s = new Sprite();
		
		// ������
		s.Init( left, top, width, height, id, UV, SpriteType );
		
		return s;
	}
	
	// ����������
	public void Init()
	{
	}
	
	// ����������
	public void Init( float[] vertex, int id, float[] UV, int SpriteType )
	{
		vertexBuffer = new VertexBuffer( vertex );
		texture = new Texture( id, UV );
		appear( SpriteType );
		
		bUse = true;
		bTextureSend = false;
	}
	
	// ����������
	public void Init( float[] vertex, int id, int SpriteType )
	{
		vertexBuffer = new VertexBuffer( vertex );
		texture = new Texture( id );
		appear( SpriteType );
		
		bUse = true;
		bTextureSend = false;
	}
	
	// ����������
	public void Init( float left, float top, float width, float height, int id, int SpriteType )
	{
		// ���̊i�[
		m_width = width * 2.0f;
		m_height = height * 2.0f;
				
		float[] vertexs = transToVertex(new Vector3( left, top, 0.0f ));
		
		vertexBuffer = new VertexBuffer( vertexs );
		texture = new Texture( id );
		appear( SpriteType );
		
		bUse = true;
		bTextureSend = false;
	}
	
	// ����������
		public void Init( float left, float top, float width, float height, int id, float[] UV, int SpriteType )
		{
			// ���̊i�[
			m_width = width * 2.0f;
			m_height = height * 2.0f;
					
			float[] vertexs = transToVertex(new Vector3( left, top, 0.0f ));
			
			vertexBuffer = new VertexBuffer( vertexs );
			texture = new Texture( id, UV );
			appear( SpriteType );
			
			bUse = true;
			bTextureSend = false;
			TextureIDBackUp = id;
		}
	
	// �㏈��
	public void Uninit()
	{
	}
	
	// �X�V����
	public boolean Update()
	{
		return true;
	}
	
	// �X�v���C�g�̐ݒ�
    public void SetSprite( GL10 gl )
    {
    	// �e�N�X�`����opengl�֑����Ă��Ȃ��Ƃ�
    	if( bTextureSend != true )
    	{
	    	// �e�N�X�`���o�b�t�@�̎擾
	    	Texture texture = GetTexture();
	    	
	    	int tex[] = texture.GetTextureBuffer();
	    	Bitmap bitmap = texture.GetBitmap();
	    		    	
	    	// �摜�̓ǂݍ���
	    	gl.glGenTextures( 1, tex, 0 );
	    		    	
	    	// �e�N�X�`���̃o�C���h
	    	gl.glBindTexture( GL10.GL_TEXTURE_2D, tex[0] );
	    		    	
	    	// �T���v���[�Ƀe�N�X�`���𑗂�
	        GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bitmap, 0 );
	        
	        // �g�k�̐ݒ�
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
	        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
	        	        
	        bTextureSend = true;
    	}
    }
    
    // �X�v���C�g�̕`��
    public void DrawSprite( GL10 gl )
    {    	
    	Texture texture = GetTexture();

    	//�e�N�X�`������������`��
    	if( texture != null )
    	{
    	
	    	FloatBuffer buffer = vertexBuffer.GetVertexBuffer();
	    	FloatBuffer UV = texture.GetUV();
	    	int size = vertexBuffer.GetVertexBufferSize();
	    	int tex[] = texture.GetTextureBuffer();
	    	
	    	// ���_�z���L���ɂ���
	    	gl.glEnableClientState( GL10.GL_VERTEX_ARRAY );
	    	
	    	// �e�N�X�`����L���ɂ���
	    	gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	    	
	    	//gl.glActiveTexture(GL10.GL_TEXTURE0);
	    	
	    	// �f�v�X�o�b�t�@�̃e�X�g�𖳌��ɂ���
	     	gl.glDisable(GL10.GL_DEPTH_TEST);
	     	
	     	// ���C�g�𖳌��ɂ���
	     	gl.glDisable(GL10.GL_LIGHTING);
	     	
	     	// �e�N�X�`����L���ɂ���
	     	gl.glEnable(GL10.GL_TEXTURE_2D);
	     	
	     	// 1�x�����摜��opengl�֓]������
	     	SetSprite(gl);
	    	
	    	// �e�N�X�`���̃o�C���h
	    	gl.glBindTexture( GL10.GL_TEXTURE_2D, tex[0] );
	
	        // UV�z���GL�ɕR�Â�
	        gl.glTexCoordPointer( 2, GL10.GL_FLOAT, 0, UV );
	        
	        // ���_�z��̃Z�b�g
	     	gl.glVertexPointer( 3, GL10.GL_FLOAT, 0, buffer );
			
			// �`��
			gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, size );
    	}
    }
	
	// ���_�o�b�t�@�̎擾
	public VertexBuffer GetVertexBuffer()
	{
		return  vertexBuffer;
	}
	
	// �e�N�X�`���̎擾
	public Texture GetTexture()
	{
		return texture;
	}

	// ���W�̎擾
	public Vector3 getTrans()
	{
		return trans;
	}
	
	protected float[] transToVertex( Vector3 trans )
	{
		this.trans = trans;
		
		float l, t, w, h;
		
		l = ( this.trans.x * 2.0f ) - ( float )MainActivity.width;
		t = ( this.trans.y * 2.0f ) - ( float )MainActivity.height;
		
		l = ( float )l / (float )MainActivity.width;
		t = ( float )t / ( float )MainActivity.height;
		w = l + ( float )m_width / ( float )MainActivity.width;
		h = t + ( float )m_height / ( float )MainActivity.height;
		
		float[] vertexs ={
				l, t, 0.0f,
				l, h, 0.0f,
				w, t, 0.0f,
				w, h, 0.0f
		};
		
		return vertexs;
	}

	// ���W�̃Z�b�g
	public void setTrans( Vector3 trans )
	{
		this.trans = trans;
		
		float[] vertexs = transToVertex(trans);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}
	
	
	// ���W�̑���
	public void Translate( Vector3 trans )
	{
		this.trans.Add(trans);
		
		float[] vertexs = transToVertex(this.trans);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}

	// �p�x�̎擾
	public Vector3 getRot()
	{
		return rot;
	}

	// �p�x�̃Z�b�g
	public void setRot( Vector3 rot )
	{
		this.rot = rot;
		
		float[] vertexs = rotToVertex(rot);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}
	
	// �p�x�̑���
	public void Rotation( Vector3 rot )
	{
		this.rot.Add(rot);
		
		float[] vertexs = rotToVertex(this.rot);
		
		vertexBuffer.SetVertexBuffer(vertexs);
	}
	
	// ��]
	protected float[] rotToVertex( Vector3 rot )
	{
		float[] work = transToVertex(trans);
		
		float[] vertexs = new float[12];
		
		vertexs[0] = (float) (work[0] * Math.cos( rot.x ) * Math.cos( rot.y )
					+ work[1] * ( Math.cos( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) - Math.sin( rot.y ) * Math.cos( rot.z ) ));
		vertexs[1] = (float) (work[0] * Math.sin( rot.x ) * Math.cos( rot.y )
					+ work[1] * ( Math.sin( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) + Math.cos( rot.x ) * Math.cos( rot.z ) ));
		vertexs[3] = vertexs[0];
		vertexs[4] = (float) (work[3] * Math.sin( rot.x ) * Math.cos( rot.y )
				+ work[4] * ( Math.sin( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) + Math.cos( rot.x ) * Math.cos( rot.z ) ));
		vertexs[6] = (float) (work[6] * Math.cos( rot.x ) * Math.cos( rot.y )
				+ work[7] * ( Math.cos( rot.x ) * Math.sin( rot.y ) * Math.sin( rot.z ) - Math.sin( rot.y ) * Math.cos( rot.z ) ));
		vertexs[7] = vertexs[1];
		vertexs[9] = vertexs[6];
		vertexs[10] = vertexs[4];
		
		return vertexs;
	}

	// �X�P�[���̎擾
	public Vector3 getScl()
	{
		return scl;
	}

	// �X�P�[���̃Z�b�g
	public void setScl( Vector3 scl )
	{
		this.scl = scl;
	}
	
	// �g�p�����̔���
	public boolean GetUse()
	{
		return bUse;
	}
	
	// �����̎擾
	public float GetWidth()
	{
		return m_width;
	}
	
	// �c���̎擾
	public float GetHeight()
	{
		return m_height;
	}
	
	// ���X�g�ɒǉ�
	public void appear( int spriteType )
	{
		// TODO ������null�̂܂܍���Ă�̂ŃG���[���Ă�
		if( Sprite.spriteList != null )
		{
			LinkedList<Sprite> list = Sprite.spriteList.get(spriteType);
		
			//���������Ă�
			this.Init();
			
			// ���X�g�Ɏ��g��ǉ�
			list.add(this);
			
			//�g�p���ɂ���
			bUse = true;
			
			//�����̔ԍ���o�^
			this.spriteType = spriteType;
		}
	}
	
	// ���X�g����͂���
	public void remove()
	{
		// ���X�g���玩�g���폜
		spriteList.get(spriteType).remove(this);
	}
	
	// ���X�g���炷�ׂĊO��
	public static void removeAll()
	{
		for( int i = 0; i < maxSpriteListNum; i++ )
		{
			for( int j = 0; j < spriteList.get(i).size(); j++ )
			{
				// ���X�g����͂���
				spriteList.get(i).get(j).remove();
			}
		}
	}
	
	public static boolean ListCreate()
	{
		// �܂�����Ă��Ȃ��Ƃ�
		if( !isCreate )
		{
			for( int i = 0; i < Const.SpriteType.TYPE_MAX.getValue(); i++ )
			{
				// ���X�g���쐬
				Sprite.spriteList.add(i, new LinkedList<Sprite>());
			}
			
			isCreate = true;
		}
		
		return isCreate;
	}
	
	//�^�b�`���ꂽ
	public boolean IsTouch()
	{
		Touch touch = Touch.getInstance();
		if( touch.IsTouch() )
		{
			float x = touch.getX();
			//opengl�̍��W�n�ւ̕ύX
			float y = touch.getY();
			if( trans.getX() < x && trans.getX() + GetWidth() > x && trans.getY() < y && trans.getY() + GetHeight() > y )
			{
				return true;
			}
		}		
		return false;
	}
	
	//Sprite���m�̐ڐG
	public boolean Collission( Sprite s )
	{
		//���
		int X1 = (int)getTrans().getX();
		int Y1 = (int)getTrans().getY();
		int W1 = (int)getM_width();
		int H1 = (int)getM_height();
		int X2 = (int)s.getTrans().getX();
		int Y2 = (int)s.getTrans().getY();
		int W2 = (int)s.getM_width();
		int H2 = (int)s.getM_width();
		
		if((X2 <= X1+W1)&&(X1 <= X2+W2)&&(Y2 <= Y1+H1)&&(Y1 <= Y2+H2 ) )
		{
			return true;
		}
		
		return false;		
	}
	
}
