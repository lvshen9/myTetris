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
	//用数组存放布局数据
	public JPanelGame(GameControl gameControl, GameDto dto){
		//连接游戏控制器
		this.gameControl=gameControl;
		//设置布局管理器为自由布局
		this.setLayout(null);
		//初始化组件
		this.initComponent();
		//初始化层
		this.initLayer(dto);
		//安装键盘监听器
		this.addKeyListener(new PlayerControl(gameControl));
	}
	/**
	 * 初始化组件
	 */
	private void initComponent(){
		//TODO 
		//初始化开始按钮
		this.btnStart=new JButton(Img.BTN_START);
		this.btnStart.setContentAreaFilled(false);
		//设置开始按钮位置
		btnStart.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getStartX(),
				GameConfig.getFrameConfig().getButtonConfig().getStartY(),
				BTN_SIZE_W,BTN_SIZE_H);
		//给开始按钮增加事件监听
		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		//添加按钮到面板
		this.add(btnStart);
		//初始化设置按钮
		this.btnConfig=new JButton(Img.BTN_CONFIG);
		this.btnConfig.setContentAreaFilled(false);
	
		//设置设置按钮位置
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
		//添加按钮到面板
		this.add(btnConfig);
	}
	/**
	 * 初始化层
	 * 
	 */
	private void initLayer(GameDto dto){
		try {
			//获得游戏配置
			FrameConfig fCfg=GameConfig.getFrameConfig();
			//获得层配置
			List<LayConfig> laysCfg=fCfg.getLaysConfig();
			//创建游戏层数组
			lays=new  ArrayList<Lay>(laysCfg.size());
			//创建所有层对象
			for (LayConfig layConfig : laysCfg) {
				//获得类对象
				Class<?> cls=Class.forName(layConfig.getClassName());
				//获得构造函数
				Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
				//创建调用构造函数，创建类对象
				Lay lay=(Lay)ctr.newInstance(
						layConfig.getX(),layConfig.getY(),layConfig.getW(),layConfig.getH()
						);
				//设置游戏数据对象
				lay.setDto(dto);
				//把创建的Lay对象放入集合
				lays.add(lay);
			} 

		}catch (Exception e) {
			e.printStackTrace();
		}	

	}
	public void paintComponent(Graphics g){
		//调用基类方法
		super.paintComponent(g);
		//绘制游戏界面
		for (int i = 0; i < lays.size(); i++) {

			lays.get(i).paint(g);
		}
		
		//返回焦点
		this.requestFocus();
		
	}
	/**
	 * 控制按钮是否可点击
	 */
	public void buttonSwitch(boolean onOff){
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}
}
