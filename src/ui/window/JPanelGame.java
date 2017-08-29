package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Img;
import ui.Lay;
import config.FrameConfig;
import config.GameConfig;
import config.LayConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

public class JPanelGame extends JPanel {
	
	private static final int BTN_SIZE_W=GameConfig.getFrameConfig().getButtonConfig().getButtonW();
	private static final int BTN_SIZE_H=GameConfig.getFrameConfig().getButtonConfig().getButtonH();
	
	private List<Lay> lays=null;
	
	private JButton btnStart;
	
	private JButton btnConfig;
	
	private GameControl gameControl=null;
	//�������Ų�������
	public JPanelGame(GameControl gameControl, GameDto dto){
		//������Ϸ������
		this.gameControl=gameControl;
		//���ò��ֹ�����Ϊ���ɲ���
		this.setLayout(null);
		//��ʼ�����
		this.initComponent();
		//��ʼ����
		this.initLayer(dto);
		//��װ���̼�����
		this.addKeyListener(new PlayerControl(gameControl));
	}
	/**
	 * ��ʼ�����
	 */
	private void initComponent(){
		//TODO 
		//��ʼ����ʼ��ť
		this.btnStart=new JButton(Img.BTN_START);
		this.btnStart.setContentAreaFilled(false);
		//���ÿ�ʼ��ťλ��
		btnStart.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				GameConfig.getFrameConfig().getButtonConfig().getStartY(),
				BTN_SIZE_W,BTN_SIZE_H);
		//����ʼ��ť�����¼�����
		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		//��Ӱ�ť�����
		this.add(btnStart);
		//��ʼ�����ð�ť
		this.btnConfig=new JButton(Img.BTN_CONFIG);
		this.btnConfig.setContentAreaFilled(false);
	
		//�������ð�ťλ��
		this.btnConfig.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(),
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(),
				BTN_SIZE_W,BTN_SIZE_H);
		this.btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		//��Ӱ�ť�����
		this.add(btnConfig);
	}
	/**
	 * ��ʼ����
	 * 
	 */
	private void initLayer(GameDto dto){
		try {
			//�����Ϸ����
			FrameConfig fCfg=GameConfig.getFrameConfig();
			//��ò�����
			List<LayConfig> laysCfg=fCfg.getLaysConfig();
			//������Ϸ������
			lays=new  ArrayList<Lay>(laysCfg.size());
			//�������в����
			for (LayConfig layConfig : laysCfg) {
				//��������
				Class<?> cls=Class.forName(layConfig.getClassName());
				//��ù��캯��
				Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
				//�������ù��캯�������������
				Lay lay=(Lay)ctr.newInstance(
						layConfig.getX(),layConfig.getY(),layConfig.getW(),layConfig.getH()
						);
				//������Ϸ���ݶ���
				lay.setDto(dto);
				//�Ѵ�����Lay������뼯��
				lays.add(lay);
			} 

		}catch (Exception e) {
			e.printStackTrace();
		}	

	}
	public void paintComponent(Graphics g){
		//���û��෽��
		super.paintComponent(g);
		//������Ϸ����
		for (int i = 0; i < lays.size(); i++) {

			lays.get(i).paint(g);
		}
		
		//���ؽ���
		this.requestFocus();
		
	}
	/**
	 * ���ư�ť�Ƿ�ɵ��
	 */
	public void buttonSwitch(boolean onOff){
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}
}
