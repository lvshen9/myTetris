package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayNext extends Lay {

	public LayNext(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	
	public void paint(Graphics g){
		this.createWindow(g);
		//����ǿ�ʼ״̬�Ż�����һ������
		if(this.dto.isStart()){
			this.drawImageAtCenter(Img.NEXT_ACT[this.dto.getNext()], g);
		}
		
	}
}
