package ui;

import java.awt.Graphics;

public class LayDataBase extends LayData {	
	public LayDataBase(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	public void paint(Graphics g){
		
		this.createWindow(g);
		this.showData(Img.DB, this.dto.getDbRecode(), g);
	}

}
