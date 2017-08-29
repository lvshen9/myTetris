package ui;

import java.awt.Graphics;

import config.GameConfig;

public class LayPoint extends Lay {
	/**
	 * �������λ��
	 */
	private static final int POINT_BIT=5;	
	/**
	 * ��������
	 */
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	
	
	/**
	 * ����y����
	 */
	private  final int rmLineY;
	/**
	 * ����y����
	 */
	private  final int pointY;
	/**
	 * ����ֵy����
	 */
	private final int expY;

	/**
	 * ����x����
	 */
	private   int comX;

	public LayPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		//��ʼ����ͨ��ʾ��X����
		this.comX=this.w-IMG_NUMBER_W*POINT_BIT-PADDING;
		//��ʼ��������ʾ��y����
		this.pointY=PADDING;
		//��ʼ��������ʾ��y����
		this.rmLineY=this.pointY+Img.POINT.getHeight(null)+PADDING;
		//��ʼ��������ʾ��y����
		this.expY=this.rmLineY+Img.RMLINE.getHeight(null)+PADDING;	
	}
	public void paint(Graphics g){
		this.createWindow(g);
		//���ڱ���(����)
		
		g.drawImage(Img.POINT, this.x+pointY, this.y+pointY, null);	
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);
		
		//���ڱ���(����)
		g.drawImage(Img.RMLINE, this.x+pointY, this.y+rmLineY, null);
		this.drawNumberLeftPad(comX, rmLineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//���Ʋ�ֵ(����ֵ)
		int rmLine=this.dto.getNowRemoveLine();	
		this.drawRect(expY,"��һ��",null,(double)(rmLine%LEVEL_UP)/(double)LEVEL_UP,g);
		
		//TODO ��ʱ
		
	}	
}
