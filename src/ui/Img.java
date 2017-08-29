package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import config.GameConfig;

public class Img {
	private Img(){}
	/**
	 * ͼƬ·��
	 */
	public static final String GRAPHICS_PATH="graphics/";
	//TODO
	private static final String DEFAULT_PATH="���Ĥ�����/";
	
	public static void setSkin(String path){
		String skinPath=GRAPHICS_PATH+path;
		// ����ǩ��
		SIGN=new ImageIcon(skinPath+"/string/sign.PNG").getImage();
		// ����ͼƬ
		WINDOW=new ImageIcon(skinPath+"/window/Window3.png").getImage();
		// ����ͼƬ260X36
		NUMBER=new ImageIcon(skinPath+"/string/num.PNG").getImage();
		// ����ֵ��
		RECT=new ImageIcon(skinPath+"/window/rect1.png").getImage();
		// ���ݿⴰ�ڱ���
		DB=new ImageIcon(skinPath+"/string/db.PNG").getImage();
		// ���ؼ�¼���ڱ���
		DISK=new ImageIcon(skinPath+"/string/disk.PNG").getImage();
		// ����ͼƬ
		ACT=new ImageIcon(skinPath+"/game/rect.png").getImage();
		ACT_1=new ImageIcon(skinPath+"/game/rect2.png").getImage();
		// �ȼ�����ͼƬ
		LEVEL=new ImageIcon(skinPath+"/string/level.PNG").getImage();
		// ��������ͼƬ(����)
		POINT=new ImageIcon(skinPath+"/string/point.PNG").getImage();
		// ���б���ͼƬ(����)
		RMLINE=new ImageIcon(skinPath+"/string/rmline.PNG").getImage();

		SMALLWINDOW=new ImageIcon(skinPath+"/window/Window6.png").getImage();
		// ��Ӱ
		SHODOW=new ImageIcon(skinPath+"/game/shodow.png").getImage();
		// ��ʼ��ť
		BTN_START=new ImageIcon(skinPath+"/string/start.PNG");
		// ���ð�ť
		BTN_CONFIG=new ImageIcon(skinPath+"/string/config.PNG");
		// ��ͣͼƬ
		PAUSE=new ImageIcon(skinPath+"/string/pause.PNG").getImage();
		// ��һ������ͼƬ
		NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i]=new ImageIcon(skinPath+"/game/"+i+".png").getImage(); 
		}		
		// ����ͼƬ����
		File dir=new File(skinPath+"/background");
		File[] files=dir.listFiles();
		BG_LIST=new ArrayList<Image>();
		for (File file : files) {
			if(file.isDirectory()){
				continue;
			}
			BG_LIST.add(new ImageIcon(file.getPath()).getImage());
		}

	}
		
	/**
	 * ����ǩ��
	 */
	public static  Image SIGN=null;
	/**
	 * ����ͼƬ
	 */
	public static  Image  WINDOW=null;
	/**
	 * ����ͼƬ260X36
	 */
	public static  Image  NUMBER=null;
	/**
	 * ����ֵ��
	 */
	public static  Image RECT=null;
	/**
	 * ���ݿⴰ�ڱ���
	 */
	public static  Image DB=null;
	/**
	 * ���ؼ�¼���ڱ���
	 */
	public static  Image DISK=null;
	/**
	 * ����ͼƬ
	 */
	public static  Image  ACT=null;
	public static  Image  ACT_1=null;
	/**
	 * �ȼ�����ͼƬ
	 */
	public static  Image LEVEL=null;
	/**
	 * ��������ͼƬ(����)
	 */
	public static  Image POINT=null;
	/**
	 * ���б���ͼƬ(����)
	 */
	public static  Image RMLINE=null;

	public static  Image SMALLWINDOW=null;
	/**
	 * ��Ӱ
	 */
	public static  Image SHODOW=null;
	/**
	 * ��ʼ��ť
	 */
	public static  ImageIcon BTN_START=null;
	/**
	 * ���ð�ť
	 */
	public static  ImageIcon BTN_CONFIG=null;
	/**
	 * ��ͣͼƬ
	 */
	public static  Image PAUSE=null;

	/**
	 * ��һ��ͼƬ����
	 */
	public static  Image[] NEXT_ACT=null;

	public static List<Image> BG_LIST=null;
	/**
	 * ��ʼ��ͼƬ
	 */
	static{
		setSkin(DEFAULT_PATH);
	}
}
