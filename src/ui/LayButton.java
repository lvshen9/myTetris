package ui;

import java.awt.Graphics;

public class LayButton extends Lay {

	public LayButton(int x, int y, int w, int h) {
		super(x, y, w, h);
		
	}
	public void paint(Graphics g){
		this.createWindow(g);
	}

}
