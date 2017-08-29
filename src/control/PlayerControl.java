package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dto.GameDto;

public class PlayerControl extends KeyAdapter{
	private GameControl gameControl;
	public PlayerControl(GameControl gameControl){
		this.gameControl=gameControl;
	}
	
	
	/**
	 * ���̰����¼�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		this.gameControl.actionByKeyCode(e.getKeyCode());
		
		
		
	}

	
	
}
