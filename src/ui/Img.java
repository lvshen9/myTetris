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
	 * 图片路径
	 */
	public static final String GRAPHICS_PATH="graphics/";
	//TODO
	private static final String DEFAULT_PATH="进撃の侣神/";
	
	public static void setSkin(String path){
		String skinPath=GRAPHICS_PATH+path;
		// 个人签名
		SIGN=new ImageIcon(skinPath+"/string/sign.PNG").getImage();
		// 窗口图片
		WINDOW=new ImageIcon(skinPath+"/window/Window3.png").getImage();
		// 数字图片260X36
		NUMBER=new ImageIcon(skinPath+"/string/num.PNG").getImage();
		// 矩形值槽
		RECT=new ImageIcon(skinPath+"/window/rect1.png").getImage();
		// 数据库窗口标题
		DB=new ImageIcon(skinPath+"/string/db.PNG").getImage();
		// 本地记录窗口标题
		DISK=new ImageIcon(skinPath+"/string/disk.PNG").getImage();
		// 方块图片
		ACT=new ImageIcon(skinPath+"/game/rect.png").getImage();
		ACT_1=new ImageIcon(skinPath+"/game/rect2.png").getImage();
		// 等级标题图片
		LEVEL=new ImageIcon(skinPath+"/string/level.PNG").getImage();
		// 分数标题图片(分数)
		POINT=new ImageIcon(skinPath+"/string/point.PNG").getImage();
		// 消行标题图片(消行)
		RMLINE=new ImageIcon(skinPath+"/string/rmline.PNG").getImage();

		SMALLWINDOW=new ImageIcon(skinPath+"/window/Window6.png").getImage();
		// 阴影
		SHODOW=new ImageIcon(skinPath+"/game/shodow.png").getImage();
		// 开始按钮
		BTN_START=new ImageIcon(skinPath+"/string/start.PNG");
		// 设置按钮
		BTN_CONFIG=new ImageIcon(skinPath+"/string/config.PNG");
		// 暂停图片
		PAUSE=new ImageIcon(skinPath+"/string/pause.PNG").getImage();
		// 下一个方块图片
		NEXT_ACT=new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i]=new ImageIcon(skinPath+"/game/"+i+".png").getImage(); 
		}		
		// 背景图片数组
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
	 * 个人签名
	 */
	public static  Image SIGN=null;
	/**
	 * 窗口图片
	 */
	public static  Image  WINDOW=null;
	/**
	 * 数字图片260X36
	 */
	public static  Image  NUMBER=null;
	/**
	 * 矩形值槽
	 */
	public static  Image RECT=null;
	/**
	 * 数据库窗口标题
	 */
	public static  Image DB=null;
	/**
	 * 本地记录窗口标题
	 */
	public static  Image DISK=null;
	/**
	 * 方块图片
	 */
	public static  Image  ACT=null;
	public static  Image  ACT_1=null;
	/**
	 * 等级标题图片
	 */
	public static  Image LEVEL=null;
	/**
	 * 分数标题图片(分数)
	 */
	public static  Image POINT=null;
	/**
	 * 消行标题图片(消行)
	 */
	public static  Image RMLINE=null;

	public static  Image SMALLWINDOW=null;
	/**
	 * 阴影
	 */
	public static  Image SHODOW=null;
	/**
	 * 开始按钮
	 */
	public static  ImageIcon BTN_START=null;
	/**
	 * 设置按钮
	 */
	public static  ImageIcon BTN_CONFIG=null;
	/**
	 * 暂停图片
	 */
	public static  Image PAUSE=null;

	/**
	 * 下一个图片数组
	 */
	public static  Image[] NEXT_ACT=null;

	public static List<Image> BG_LIST=null;
	/**
	 * 初始化图片
	 */
	static{
		setSkin(DEFAULT_PATH);
	}
}
