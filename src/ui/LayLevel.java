package ui;

import java.awt.Graphics;

public class LayLevel extends Lay {
	
	
	/**
	 * ����ͼƬ�Ŀ��
	 */
	private static final int IMG_LV_W=Img.LEVEL.getWidth(null);

	public LayLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	public void paint(Graphics g){
		this.createWindow(g);
		//���ڱ���
		int centerX=this.w-IMG_LV_W>>1;
		g.drawImage(Img.LEVEL, this.x+centerX, this.y+PADDING, null);
		//��ʾ�ȼ�
		this.drawNumberLeftPad(centerX, 64, this.dto.getNowLevel(),2, g);
	}

}
