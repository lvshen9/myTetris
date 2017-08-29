package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import ui.Img;
import util.FrameUtil;
import control.GameControl;

public class JFrameConfig extends JFrame{
	
	private JButton btnOk=new JButton("确定");
	
	private JButton btnCancel=new JButton("取消");
	
	private JButton btnUser=new JButton("应用");
	
	private TextCtrl[] keyText=new TextCtrl[8];
	
	private JList skinList=null;
	
	private JPanel skinView=null;
	
	private DefaultListModel skinData=new DefaultListModel();
	
	private JLabel errorMsg=new JLabel();
	
	private GameControl gameControl;
	
	private final static Image IMG_PSP=new ImageIcon("data/psp2.png").getImage();
	
	private Image[] skinViewList=null;
	
	private final static String[] MeTHOD_NAMES={
		"keyRight","keyUp","keyLeft","keyDown",
		"keyFunUp","keyFunLeft","keyFunRight","keyFunDown"
	};
	
	private final static String PATH="data/control.dat";
	public JFrameConfig(GameControl gameControl){
		//获得游戏控制器对象
		this.gameControl=gameControl;
		//设置布局管理器为“边界布局”
		this.setLayout(new BorderLayout());
		this.setTitle("设置");
		//初始化按键输入框
		this.initKeyText();
		//添加主面板
		this.add(createMainPanel(), BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.createButtonPanel(),BorderLayout.SOUTH);
		//设置不能变大小
		this.setResizable(false);
		//设置窗口大小
		this.setSize(651, 421);
		//居中
		FrameUtil.setFrameCenter(this,-200);
	}
	/**
	 * 初始化按键输入方法
	 */
	private void initKeyText() {
		//0,68,64,20
		keyText[0]=new TextCtrl(4,68,64,20,MeTHOD_NAMES[0]);
		keyText[1]=new TextCtrl(4,115,64,20,MeTHOD_NAMES[1]);
		keyText[2]=new TextCtrl(4,146,64,20,MeTHOD_NAMES[2]);
		keyText[3]=new TextCtrl(4,180,64,20,MeTHOD_NAMES[3]);
		keyText[4]=new TextCtrl(565,16,64,20,MeTHOD_NAMES[4]);
		keyText[5]=new TextCtrl(565,60,64,20,MeTHOD_NAMES[5]);
		keyText[6]=new TextCtrl(565,93,64,20,MeTHOD_NAMES[6]);
		keyText[7]=new TextCtrl(565,130,64,20,MeTHOD_NAMES[7]);

		
		try {
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream(PATH));
			HashMap<Integer, String> cfgSet=(HashMap)ois.readObject();
			ois.close();
			Set<Entry<Integer,String>> entrySet=cfgSet.entrySet();
			for (Entry<Integer, String> e : entrySet) {
				for (TextCtrl tc:keyText) {
					if(tc.getMethodName().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建按钮面板
	 * @return
	 */
	private JPanel createButtonPanel() {
		//创建按钮面板，流式布局
		JPanel jp=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//给确定按钮增加时间监听
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(writeConfig()){
					setVisible(false);
					gameControl.setOver();
				}
			}
		});
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);
		jp.add(this.btnOk);
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameControl.setOver();
			}
		});
		jp.add(this.btnCancel);
		this.btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeConfig();
			}
		});
		jp.add(this.btnUser);
		return jp;
	}
	/**
	 * 创建主面板(选项卡面版)
	 * @return
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jtp=new JTabbedPane();
		jtp.addTab("控制设置", this.createControlPanel());
		jtp.addTab("皮肤设置", this.createSkinPanel());
		return jtp;
	}
	/**
	 * 玩家皮肤面板
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Component createSkinPanel() {
		JPanel panel=new JPanel(new BorderLayout());
		File dir=new File(Img.GRAPHICS_PATH);
		File[] files=dir.listFiles();
		this.skinViewList=new Image[files.length];
		for (int i=0;i<files.length;i++) {
			//增加选项
			this.skinData.addElement(files[i].getName());
			//增加预览图
			this.skinViewList[i]=new ImageIcon(files[i].getPath()+"\\view.jpg").getImage();
		}
		//添加列表数据到列表
		this.skinList=new JList(this.skinData);
		//设置默认选项为第一个
		this.skinList.setSelectedIndex(0);
		this.skinList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				repaint();
			}
			
		});
		this.skinView=new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(skinViewList[skinList.getSelectedIndex()], 0, 0,520,320,null);
			}
		};
		panel.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		panel.add(new JScrollPane(this.skinView),BorderLayout.CENTER);
		
		return panel;
	}
	/**
	 * 玩家控制设置面板
	 * @return
	 */
	private JPanel createControlPanel() {
		JPanel jp=new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		//设置布局管理
		jp.setLayout(null);
		for (int i = 0; i <keyText.length; i++) {
			jp.add(keyText[i]);
		}
		return jp;
	}

	/**
	 * 写入游戏配置
	 * @param 
	 */
	private boolean writeConfig(){
		HashMap<Integer,String> keySet=new HashMap<Integer,String>();
		for (int i = 0; i < this.keyText.length; i++) {
			int keyCode=this.keyText[i].getKeyCode();
			if(keyCode==0){
				this.errorMsg.setText("错误按键");
				return false;
			}
			keySet.put(keyCode, this.keyText[i].getMethodName());
		}
		if(keySet.size()!=8){
			this.errorMsg.setText("重复按键");
			return false;
		}
		try {
			//切换皮肤
			
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString()+"/");
			//写入控制配置
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		} catch (Exception e) {
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}
	/*public static void main(String[] args) {
		new FrameConfig();
	}*/
}
