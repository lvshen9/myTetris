package ui.window;

import javax.swing.JFrame;

import util.FrameUtil;
import config.FrameConfig;
import config.GameConfig;

public class JFrameGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4056290386335708982L;

	public JFrameGame(JPanelGame panelGame){
		//�����Ϸ����
		FrameConfig fCfg=GameConfig.getFrameConfig();
		//���ñ���
		this.setTitle(fCfg.getTitle());
		//����Ĭ�Ϲر�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô��ڴ�С
		this.setSize(fCfg.getWidth(),fCfg.getHeight());
		//��ֹ��Ҹı䴰���С
		this.setResizable(false);
		//����
		FrameUtil.setFrameCenter(this);
		//����Ĭ��Panel
		this.setContentPane(panelGame);
		//Ĭ�ϴ���Ϊ��ʾ
		this.setVisible(true);
	}
}
