package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GameFunction;
import config.GameConfig;
import entity.GameAct;

public class GameDto {
	/**
	 * 游戏宽度
	 */
	public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	/**
	 * 游戏高度
	 */
	public static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	/**
	 * 数据库记录
	 */
	private List<Player> dbRecode;
	/**
	 * 本地记录
	 */
	private List<Player> diskRecode;
	/**
	 * 游戏地图
	 */
	private boolean[][] gameMap;
	/**
	 * 下落方块
	 */
	private GameAct gameAct;
	/**
	 * 下一个方块
	 */
	private int next;
	/**
	 * 等级
	 */
	private int nowLevel;
	/**
	 * 分数
	 */
	private int nowPoint;
	/**
	 * 消行
	 */
	private int nowRemoveLine;
	/**
	 * 游戏是否是开始状态
	 */
	private boolean start;
	/**
	 * 是否显示阴影
	 */
	private boolean showShadow;
	/**
	 * 暂停
	 */
	private boolean pause;
	/**
	 * 是否使用作弊键盘
	 */
	private boolean cheat;
	/**
	 * 线程睡眠时间
	 */
	private long sleepTime;
	/**
	 * 构造函数
	 */
	public GameDto(){
		dtoInit();
	}
	/**
	 * dto初始化
	 */
	public void dtoInit(){
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
		//TODO 初始化所有游戏对象
		this.nowLevel=0;
		this.nowPoint=0;
		this.nowRemoveLine=0;
		this.pause=false;
		this.cheat=false;
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
	}
	/**
	 * @return dbRecode
	 */
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	/**
	 * @param dbRecode 要设置的 dbRecode
	 */
	public void setDbRecode(List<Player> dbRecode) {
		
		this.dbRecode = this.setFillRecode(dbRecode);
	}
	private List<Player> setFillRecode(List<Player> players){
		//如果进来的是空，则创建
		if(dbRecode==null){
			dbRecode=new ArrayList<Player>();
		}
		//如果记录数小于5，就加到5条为止
		while(dbRecode.size()<5){
			dbRecode.add(new Player("No Data", 0));
		}
		Collections.sort(players);
		return players;
		
	}
	/**
	 * @return diskRecode
	 */
	public List<Player> getDiskRecode() {
		return diskRecode;
	}
	/**
	 * @param diskRecode 要设置的 diskRecode
	 */
	public void setDiskRecode(List<Player> diskRecode) {
		this.diskRecode = this.setFillRecode(diskRecode);
	}
	/**
	 * @return gameMap
	 */
	public boolean[][] getGameMap() {
		return gameMap;
	}
	/**
	 * @param gameMap 要设置的 gameMap
	 */
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	/**
	 * @return gameAct
	 */
	public GameAct getGameAct() {
		return gameAct;
	}
	/**
	 * @param gameAct 要设置的 gameAct
	 */
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	/**
	 * @return next
	 */
	public int getNext() {
		return next;
	}
	/**
	 * @param next 要设置的 next
	 */
	public void setNext(int next) {
		this.next = next;
	}
	/**
	 * @return level
	 */
	public int getNowLevel() {
		return nowLevel;
	}
	/**
	 * @param level 要设置的 level
	 */
	public void setNowLevel(int nowlevel) {
		this.nowLevel = nowlevel;
		//计算线程等待时间(下落速度)
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
	}
	/**
	 * @return nowPoint
	 */
	public int getNowPoint() {
		return nowPoint;
	}
	/**
	 * @param nowPoint 要设置的 nowPoint
	 */
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	/**
	 * @return nowRemoveLine
	 */
	public int getNowRemoveLine() {
		return nowRemoveLine;
	}
	/**
	 * @param nowRemoveLine 要设置的 nowRemoveLine
	 */
	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	/**
	 * @return start
	 */
	public boolean isStart() {
		return start;
	}
	/**
	 * @param start 要设置的 start
	 */
	public void setStart(boolean start) {
		this.start = start;
	}
	/**
	 * @return showShadow
	 */
	public boolean isShowShadow() {
		return showShadow;
	}
	/**
	 * @param showShadow 要设置的 showShadow
	 */
	public void changeShowShadow() {
		this.showShadow = !this.showShadow;
	}
	/**
	 * @return pause
	 */
	public boolean isPause() {
		return pause;
	}
	/**
	 * @param pause 要设置的 pause
	 */
	public void changePause() {
		this.pause =!this.pause;
	}
	/**
	 * @return cheat
	 */
	public boolean isCheat() {
		return cheat;
	}
	/**
	 * @param cheat 要设置的 cheat
	 */
	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
	/**
	 * @return sleepTime
	 */
	public long getSleepTime() {
		return sleepTime;
	}
	
}
