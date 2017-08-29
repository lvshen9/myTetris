package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

/**
 * ���ƴ���
 * @author lvshen9
 *
 */
public abstract class Lay {
	/**
	 * �ڱ߾�
	 */
	protected static final int PADDING;
	/**
	 * �߿���
	 */
	protected static final int BORDER;
	
	static{
		//��ȡ��Ϸ����
		FrameConfig fCfg=GameConfig.getFrameConfig();
		PADDING=fCfg.getPadding();
		BORDER=fCfg.getBorder();
	}
	
	
	private static int WINDOW_W=Img.WINDOW.getWidth(null);
	private static int WINDOW_H=Img.WINDOW.getHeight(null);
	
	
	/**
	 * ������Ƭ�Ŀ��
	 */
	protected static final int IMG_NUMBER_W=Img.NUMBER.getWidth(null)/10;
	/**
	 * ������Ƭ�ĸ߶�
	 */
	private static final int IMG_NUMBER_H=Img.NUMBER.getHeight(null);
	/**
	 * ����ֵ��(�߶�)
	 */	
	protected static final int IMG_RECT_H=Img.RECT.getHeight(null);
	/**
	 *  ����ֵ��ͼƬ(���)
	 */
	private static final int IMG_RECT_W=Img.RECT.getWidth(null);
	
	private static final int IMG_SMALLWINDOW_W=Img.SMALLWINDOW.getWidth(null);
	private static final int IMG_SMALLWINDOW_H=Img.SMALLWINDOW.getWidth(null);
	
	
	/**
	 *  ����ֵ��(���)
	 */
	private  final int rectW;
	/**
	 * Ĭ������
	 */
	private static final  Font DEF_FONT=new Font("΢���ź�",Font.ITALIC,20);
	/**
	 * �������Ͻ�x����
	 */
	protected int x;
	
	/**
	 * �������Ͻ�y����
	 */
	protected int y;
	
	/**
	 * ���ڿ��
	 */
	protected int w;
	
	/**
	 * ���ڸ߶�
	 */
	protected int h;
	
	/**
	 * ��Ϸ����
	 */
	protected GameDto dto=null;
	
	protected Lay(int x, int y, int w, int h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rectW=this.w-(PADDING<<1);
	}
	
	/*
	 * ���ƴ���
	 */
		
	protected void createWindow(Graphics g){
		//g.drawImage(WINDOW_IMG, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);

		//����
		g.drawImage(Img.WINDOW, x, y, x+BORDER,y+BORDER,0,0,BORDER,BORDER,null);
		//����
		g.drawImage(Img.WINDOW, x+BORDER, y, x+w-BORDER,y+BORDER,BORDER,0,WINDOW_W-BORDER,BORDER,null);

		//����
		g.drawImage(Img.WINDOW, x+w-BORDER, y, x+w,y+BORDER,WINDOW_W-BORDER,0,WINDOW_W,BORDER,null);
		//����
		g.drawImage(Img.WINDOW, x, y+BORDER, x+BORDER,y+h-BORDER,0,BORDER,BORDER,WINDOW_H-BORDER,null);
		//��
		g.drawImage(Img.WINDOW, x+BORDER, y+BORDER, x+w-BORDER, y+h-BORDER, BORDER, BORDER, WINDOW_W-BORDER, WINDOW_H-BORDER, null);
		//����
		g.drawImage(Img.WINDOW, x+w-BORDER,y+BORDER, x+w, y+h-BORDER, WINDOW_W-BORDER, BORDER, WINDOW_W, WINDOW_H-BORDER, null);
		//����
		g.drawImage(Img.WINDOW, x, y+h-BORDER, x+BORDER, y+h, 0, WINDOW_H-BORDER, BORDER, WINDOW_H, null);
		//����
		g.drawImage(Img.WINDOW, x+BORDER, y+h-BORDER, x+w-BORDER,y+h, BORDER, WINDOW_H-BORDER, WINDOW_W-BORDER, WINDOW_H, null);
		//����
		g.drawImage(Img.WINDOW, x+w-BORDER, y+h-BORDER, x+w, y+h, WINDOW_W-BORDER, WINDOW_H-BORDER, WINDOW_W, WINDOW_H, null);

	}
	
	
	/**
	 * @param dto Ҫ���õ� dto
	 */
	public void setDto(GameDto dto) {
		this.dto = dto;
	}

	
	/**
	 * ��ʾ����
	 * @param x ���Ͻ�x����
	 * @param y ���Ͻ�y����
	 * @param num Ҫ��ʾ������
	 * @param bitCount ����λ�� 
	 * @param g ���ʶ���
	 */
	protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g){
		//��Ҫ��ӡ������ת���ַ���
		String strNum=Integer.toString(num);
		//ѭ�����������Ҷ���
		for (int i = 0; i < maxBit; i++) {
			//�ж��Ƿ������������
			if(maxBit-i<=strNum.length()){
				//����������ַ����е��±�
				int idx=i-maxBit+strNum.length();
				//��number�е�ÿһλȡ��
				int bit=strNum.charAt(idx)-'0';
				//��������
				g.drawImage(Img.NUMBER,
						this.x+x+IMG_NUMBER_W*i,this.y+y,
						this.x+x+IMG_NUMBER_W*(i+1),
						this.y+y+IMG_NUMBER_W, 
						bit*IMG_NUMBER_W, 0,
						(bit+1)*IMG_NUMBER_W,IMG_NUMBER_H,null);
			}

		}

	}
	/**
	 * ����ֵ��
	 * @param title
	 * @param number
	 * @param value
	 * @param maxValue
	 * @param g
	 */
	protected void drawRect(int y,String title,String number,double percent,Graphics g){
		//����ֵ��ʼ��
		int rect_x=this.x+PADDING;
		int rect_y=this.y+y;
		//���Ʊ���
//		g.setColor(Color.BLACK);
//		g.fillRect(rect_x, rect_y, this.rectW, IMG_RECT_H+4);
		g.drawImage(Img.SMALLWINDOW,rect_x, rect_y, this.rectW, IMG_RECT_H+4,null);
		
//		g.setColor(new Color(100,100,102,80));		
//		g.fillRect(rect_x+1, rect_y+1, this.rectW-2, IMG_RECT_H+2);
//		
//		g.setColor(new Color(200,200,200,200));	
//		g.fillRect(rect_x+2, rect_y+2, this.rectW-4, IMG_RECT_H);
		
		
		//������
		int w=(int)(percent*(this.rectW-4));
		//�����ɫ
		int subIdx=(int)(percent*IMG_RECT_W)-1;
		//����ֵ��	
		g.drawImage(Img.RECT, 
				rect_x+2,  rect_y+2, 
				rect_x+2+w, rect_y+2+IMG_RECT_H, 
				subIdx, 0, subIdx+1, IMG_RECT_H, 
				null);
		//ֵ��������ɫ
		g.setColor(new Color(0,0,0,200));
		//ֵ����������
		g.setFont(DEF_FONT);
		g.drawString(title, rect_x+4, rect_y+22);
		if(number!=null){
			g.drawString(number, rect_x+235, rect_y+22);
		}
	}
	/**
	 * ���л�ͼ
	 * @param g
	 */
	protected void drawImageAtCenter(Image img,Graphics g ){
		int imgW=img.getWidth(null);
		int imgH=img.getHeight(null);
		
		g.drawImage(img, this.x+(this.w-imgW>>1), this.y+(this.h-imgH>>1), null);
		
	}
	/**
	 * ˢ����Ϸ��������
	 * @author lvshen9
	 * @param g ����
	 */
	abstract public void paint(Graphics g);

}
