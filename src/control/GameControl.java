package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import service.GameService;
import service.GameTetris;
import ui.window.JFrameConfig;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;
import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dto.GameDto;
import dto.Player;

/**
 * 接收玩家键盘事件
 * 控制画面
 * 控制游戏逻辑
 * 
 * @author lvshen9
 *
 */
public class GameControl {
	/**
	 * 数据访问接口A
	 */
	private Data dataA;
	/**
	 * 数据访问接口A
	 */
	private Data dataB;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	/**
	 * 游戏界面层
	 */
	private JPanelGame panelGame;
	/**
	 * 游戏控制设置窗口
	 */
	private JFrameConfig frameConfig;
	/**
	 * 保存分数窗口
	 */
	private JFrameSavePoint frameSavePoint;
	
	/**
	 * 游戏行为控制
	 */
	private Map<Integer,Method> actionList;
	/**
	 * 游戏数据源
	 */
	private GameDto dto=null;
	/**
	 * 游戏线程
	 */
	private Thread gameThread=null;
	
	public GameControl(){
		//创建游戏数据源
		this.dto=new GameDto();
		//创建游戏逻辑快，链接游戏数据源
		this.gameService=new  GameTetris(dto);
		//创建数据接口A对象
		this.dataA=createDataObject(GameConfig.getDataConfig().getDataA());
		//设置数据库记录到游戏
		this.dto.setDbRecode(dataA.loadData());
		//设置数据接口B获得本地磁盘记录
		this.dataB=createDataObject(GameConfig.getDataConfig().getDataB());
		//设置本地磁盘记录到游戏
		this.dto.setDiskRecode(dataB.loadData());
		//创建游戏面板
		this.panelGame=new JPanelGame(this,dto);
		//读取用户控制设置
		this.setControlConfig();
		//初始化用户配置窗口
		this.frameConfig=new JFrameConfig(this);
		//初始化保存分数窗口
		this.frameSavePoint=new JFrameSavePoint(this);
		//初始化游戏主窗口，安装游戏面板
		new JFrameGame(this.panelGame);
		
	}
	/**
	 * 读取用户控制设置
	 */
	private void setControlConfig(){
		//创建键盘码与方法名的映射数组
		this.actionList=new HashMap<Integer,Method>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet=(HashMap<Integer, String>)ois.readObject();
			Set<Entry<Integer,String>> entrySet=cfgSet.entrySet();
			for (Entry<Integer, String> e : entrySet) {
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 创建数据对象
	 * @param cfg
	 * @return
	 */
	private Data createDataObject(DataInterfaceConfig cfg){
		try {
			//获得类对象
			Class<?> cls=Class.forName(cfg.getClassName());
			//获得构造器
			Constructor<?> ctr=cls.getConstructor(HashMap.class);
			//创建对象
			return (Data)ctr.newInstance(cfg.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据玩家控制来决定行为
	 * @param keyCode
	 */
	public void actionByKeyCode(int keyCode) {
		try {
			if(this.actionList.containsKey(keyCode)){
				this.actionList.get(keyCode).invoke(this.gameService);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		this.panelGame.repaint();
	}
	/**
	 * 显示玩家控制窗口
	 */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}
	/**
	 * 子窗口关闭事件
	 */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}
	/**
	 * 开始按钮事件
	 */
	public void start() {
		//面板按钮设置为不可点击
		this.panelGame.buttonSwitch(false);
		//关闭窗口
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		
		//游戏数据初始化
		this.gameService.startGame();
		//创建线程对象
		this.gameThread=new MainThread();
		//启动线程
		this.gameThread.start();
		//刷新画面
		this.panelGame.repaint();
	}
	/**
	 * 失败后的处理
	 */
	private void afterLose(){
		if(!this.dto.isCheat()){
			//显示保存的分窗口
			this.frameSavePoint.show(this.dto.getNowPoint());
		}
		//是按钮可以点击
		this.panelGame.buttonSwitch(true);
		
	}
	/**
	 * 保存分数
	 * @param name
	 */
	public void savePoint(String name) {
		Player pla=new Player(name,this.dto.getNowPoint());
		//保存记录到数据库
		this.dataA.saveData(pla);
		//保存记录到本地
		this.dataB.saveData(pla);
		//设置数据库记录到游戏
		this.dto.setDbRecode(dataA.loadData());
		//设置本地记录到游戏
		this.dto.setDiskRecode(dataB.loadData());
		//刷新画面
		this.panelGame.repaint();
	}
	
	public void repaint(){
		this.panelGame.repaint();
	}
	/**
	 * 游戏主线程
	 * @author lvshen
	 *
	 */
	private class MainThread extends Thread{
		@Override
		public void run(){
			//刷新画面
			panelGame.repaint();
			//主循环
			while(dto.isStart()){
				try {
					//线程睡眠
					Thread.sleep(dto.getSleepTime());
					//如果暂停，不执行主行为
					if(dto.isPause()){
						continue;
					}
					//游戏主行为
					gameService.mainAction();
					//刷新画面
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			afterLose();
		}
	}
	//以下代码作废，有更加效率的代码
	/**
	 * 控制方向键(上)
	 *//*
	public void keyUp() {
		this.gameService.keyUp();
		this.panelGame.repaint();
	}
	*//**
	 * 控制方向键(下)
	 *//*
	public void keyDown() {
		this.gameService.keyDown();
		this.panelGame.repaint();
	}
	*//**
	 * 控制方向键(左)
	 *//*
	public void keyLeft() {
		this.gameService.keyLeft();
		this.panelGame.repaint();
	}
	*//**
	 * 控制方向键(右)
	 *//*
	public void keyRight() {
		this.gameService.keyRight();
		this.panelGame.repaint();
	}

//===============TODO测试专用====================
	public void testLevelUp() {
		this.panelGame.repaint();
		this.gameService.testLevelUp();
	}*/
}
