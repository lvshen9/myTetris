package ui;

import java.awt.Graphics;
import java.awt.Point;

import config.GameConfig;
import entity.GameAct;

public class LayGame extends Lay {
	/**
	 * ��λ��ƫ����
	 * 
	 */
	private static final int SIZE_ROL=GameConfig.getFrameConfig().getSizeRol();
	
	private static final int LEFT_SIDE=0;
	
	private static final int RIGHT_SIDE=GameConfig.getSystemConfig().getMaxX();
	
	private static final int LOSE_IDX=GameConfig.getFrameConfig().getLoseIdx();
	
	public LayGame(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	public void paint(Graphics g){
		this.createWindow(g);
		//��÷������鼯��
		GameAct act=this.dto.getGameAct();
		if(act!=null){
			Point[] points=act.getActPoints();
			//������Ӱ
			this.drawShadow(points,g);
			//���ƻ����
			this.drawMainAct(points,g);
		}
		//������Ϸ��ͼ
		this.drawMap(g);
		if(this.dto.isPause()){
			this.drawImageAtCenter(Img.PAUSE, g);
		}
	}
	/**
	 * ������Ϸ��ͼ
	 * @param g
	 */
	private void drawMap(Graphics g) {
		//���Ƶ�ͼ
		boolean[][] map=this.dto.getGameMap();
		//���㵱ǰ�ѻ���ɫ
		int lv=this.dto.getNowLevel();
		int imgIdx=lv==0?0:(lv-1)%7+1;
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if(map[x][y]){
					this.drawActByPoint_1(x,y,imgIdx, g);
				}
			}
		}
	}
	/**
	 * ���ƻ����
	 * @param g
	 */
	private void drawMainAct(Point[] points,Graphics g) {
		//��÷������ͱ��(0-6)
		int typeCode=this.dto.getGameAct().getTypeCode();
		//���Ʒ���
		for (int i = 0; i < points.length; i++) {
			this.drawActByPoint(points[i].x,points[i].y,typeCode+1, g);

		}
	}
	/**
	 * ������Ӱ
	 * @param points
	 * @param b
	 */
	private void drawShadow(Point[] points,Graphics g) {
		//TODO p)��Ӱ�ر�
		if(!this.dto.isShowShadow()){
			return;
		}
		int leftX=RIGHT_SIDE;
		int rightX=LEFT_SIDE;
		for (Point p : points) {
			leftX=p.x<leftX?p.x:leftX;
			rightX=p.x>rightX?p.x:rightX;
		}
		g.drawImage(Img.SHODOW,
				this.x+BORDER+(leftX<<SIZE_ROL), 
				this.y+BORDER,
				(rightX-leftX+1)<<SIZE_ROL ,
				this.h-(BORDER<<1),null
		);
	}
	/**
	 * ���������ο�
	 * @param x
	 * @param y
	 * @param ImaIndx
	 * @param g
	 */
	private void drawActByPoint(int x,int y,int imgIndx,Graphics g){
		imgIndx=this.dto.isStart()?imgIndx:LOSE_IDX;
		g.drawImage(Img.ACT, 
				this.x+(x<<SIZE_ROL)+BORDER, 
				this.y+(y<<SIZE_ROL)+BORDER,
				this.x+(x+1<<SIZE_ROL)+BORDER, 
				this.y+(y+1<<SIZE_ROL)+BORDER,
				imgIndx<<SIZE_ROL, 0, (imgIndx+1)<<SIZE_ROL, 1<<SIZE_ROL, null);
	}
	//��д���������Ƶ�ͼ
	private void drawActByPoint_1(int x,int y,int imgIndx,Graphics g){
		imgIndx=this.dto.isStart()?imgIndx:LOSE_IDX;
		g.drawImage(Img.ACT_1, 
				this.x+(x<<SIZE_ROL)+7, 
				this.y+(y<<SIZE_ROL)+7,
				this.x+(x+1<<SIZE_ROL)+7, 
				this.y+(y+1<<SIZE_ROL)+7,
				imgIndx<<SIZE_ROL, 0, (imgIndx+1)<<SIZE_ROL, 1<<SIZE_ROL, null);
	}

}
