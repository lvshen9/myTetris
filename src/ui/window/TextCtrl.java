package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextCtrl extends JTextField{
	
	private int keyCode;
	
	private final String methodName;
	
	public TextCtrl(int x,int y,int w,int h,String methodName){
		//设置文本框位置
		this.setBounds(x,y,w,h);
	
		//初始化方法名
		this.methodName=methodName;
		//增加事件监听
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			/**
			 * 键盘松开
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				setKeyCode(e.getKeyCode());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}

	/**
	 * @return keyCode
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * @param keyCode 要设置的 keyCode
	 */
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
		this.setText(KeyEvent.getKeyText(this.keyCode));
	}

	/**
	 * @return methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	
}
