package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.GameFunction;
import config.GameConfig;
import entity.GameAct;

public class GameDto {
	/**
	 * ��Ϸ���
	 */
	public static final int GAMEZONE_W=GameConfig.getSystemConfig().getMaxX()+1;
	/**
	 * ��Ϸ�߶�
	 */
	public static final int GAMEZONE_H=GameConfig.getSystemConfig().getMaxY()+1;
	/**
	 * ���ݿ��¼
	 */
	private List<Player> dbRecode;
	/**
	 * ���ؼ�¼
	 */
	private List<Player> diskRecode;
	/**
	 * ��Ϸ��ͼ
	 */
	private boolean[][] gameMap;
	/**
	 * ���䷽��
	 */
	private GameAct gameAct;
	/**
	 * ��һ������
	 */
	private int next;
	/**
	 * �ȼ�
	 */
	private int nowLevel;
	/**
	 * ����
	 */
	private int nowPoint;
	/**
	 * ����
	 */
	private int nowRemoveLine;
	/**
	 * ��Ϸ�Ƿ��ǿ�ʼ״̬
	 */
	private boolean start;
	/**
	 * �Ƿ���ʾ��Ӱ
	 */
	private boolean showShadow;
	/**
	 * ��ͣ
	 */
	private boolean pause;
	/**
	 * �Ƿ�ʹ�����׼���
	 */
	private boolean cheat;
	/**
	 * �߳�˯��ʱ��
	 */
	private long sleepTime;
	/**
	 * ���캯��
	 */
	public GameDto(){
		dtoInit();
	}
	/**
	 * dto��ʼ��
	 */
	public void dtoInit(){
		this.gameMap=new boolean[GAMEZONE_W][GAMEZONE_H];
		//TODO ��ʼ��������Ϸ����
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
	 * @param dbRecode Ҫ���õ� dbRecode
	 */
	public void setDbRecode(List<Player> dbRecode) {
		
		this.dbRecode = this.setFillRecode(dbRecode);
	}
	private List<Player> setFillRecode(List<Player> players){
		//����������ǿգ��򴴽�
		if(dbRecode==null){
			dbRecode=new ArrayList<Player>();
		}
		//�����¼��С��5���ͼӵ�5��Ϊֹ
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
	 * @param diskRecode Ҫ���õ� diskRecode
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
	 * @param gameMap Ҫ���õ� gameMap
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
	 * @param gameAct Ҫ���õ� gameAct
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
	 * @param next Ҫ���õ� next
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
	 * @param level Ҫ���õ� level
	 */
	public void setNowLevel(int nowlevel) {
		this.nowLevel = nowlevel;
		//�����̵߳ȴ�ʱ��(�����ٶ�)
		this.sleepTime=GameFunction.getSleepTimeByLevel(this.nowLevel);
	}
	/**
	 * @return nowPoint
	 */
	public int getNowPoint() {
		return nowPoint;
	}
	/**
	 * @param nowPoint Ҫ���õ� nowPoint
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
	 * @param nowRemoveLine Ҫ���õ� nowRemoveLine
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
	 * @param start Ҫ���õ� start
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
	 * @param showShadow Ҫ���õ� showShadow
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
	 * @param pause Ҫ���õ� pause
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
	 * @param cheat Ҫ���õ� cheat
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
