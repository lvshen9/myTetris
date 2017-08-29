package ui.window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextCtrl extends JTextField{
	
	private int keyCode;
	
	private final String methodName;
	
	public TextCtrl(int x,int y,int w,int h,String methodName){
		//�����ı���λ��
		this.setBounds(x,y,w,h);
	
		//��ʼ��������
		this.methodName=methodName;
		//�����¼�����
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			/**
			 * �����ɿ�
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
	 * @param keyCode Ҫ���õ� keyCode
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
