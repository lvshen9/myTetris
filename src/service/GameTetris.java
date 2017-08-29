package service;

import java.awt.Point;
import java.util.Map;
import java.util.Random;

import config.GameConfig;
import dto.GameDto;
import entity.GameAct;

public class GameTetris implements GameService{
	/**
	 * ��Ϸ���ݶ���
	 */
	private GameDto dto;
	/**
	 * �����������
	 */
	private Random  random=new Random();
	
	/**
	 * �����������
	 */
	private static final int MAX_TPYE=GameConfig.getSystemConfig().getTypeConfig().size()-1;
	/**
	 * ��������
	 * @param 
	 */
	private static final int LEVEL_UP=GameConfig.getSystemConfig().getLevelUp();
	/**
	 * �������з�����
	 */
	private static final Map<Integer,Integer> PLUS_POINT=GameConfig.getSystemConfig().getPlusPoint(); 
	
	public GameTetris(GameDto dto){
		this.dto=dto;
		
	}
	
	/**
	 * ���Ʒ����(��)
	 */
	public boolean keyUp() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized(this.dto){
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}
	/**
	 * ���Ʒ����(��)
	 */
	public boolean keyDown() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized(this.dto){
			//���������ƶ������ж��Ƿ��ƶ��ɹ�
			if(this.dto.getGameAct().move(0, 1,this.dto.getGameMap())){
				return false;
			}
			//�����Ϸ��ͼ����
			boolean[][] map=this.dto.getGameMap();
			Point[] act=this.dto.getGameAct().getActPoints();
			//������ѻ�����ͼ����
			for (int i = 0; i < act.length; i++) {
				map[act[i].x][act[i].y]=true;
			}
			// �ж����У��������þ���ֵ
			int plusExp=this.plusExp();
			//���Ӿ���ֵ
			if(plusExp>0){
				this.plusPoint(plusExp);
			}
			//ˢ���µķ���
			this.dto.getGameAct().init(this.dto.getNext());
			//���������һ������
			this.dto.setNext(random.nextInt(MAX_TPYE));
			//�����Ϸ�Ƿ�ʧ��
			if(this.isLose()){
				//������Ϸ
				this.dto.setStart(false);
			}
		}
		return true;
	}

	/**
	 * �����Ϸ�Ƿ�ʧ��
	 */
	private boolean isLose() {
		//������ڵĻ����
		Point[] actPoints=this.dto.getGameAct().getActPoints();
		//������ڵ���Ϸ��ͼ
		boolean[][] map=this.dto.getGameMap();
		for (int i = 0; i < actPoints.length; i++) {
			if(map[actPoints[i].x][actPoints[i].y]){
				return true;
			}
		}
		return false;
	}

	/**
	 * �ӷ���������
	 * @param exp
	 */
	private void plusPoint(int plusExp) {
		int lv=this.dto.getNowLevel();
		int rmLine=this.dto.getNowRemoveLine();
		int point=this.dto.getNowPoint();
		if(rmLine % LEVEL_UP + plusExp >= LEVEL_UP){
			this.dto.setNowLevel(++lv);
		}
		this.dto.setNowRemoveLine(rmLine+plusExp);
		this.dto.setNowPoint(point+PLUS_POINT.get(plusExp));
	}

	/**
	 * ���Ʒ����(��)
	 */
	public boolean keyLeft() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized(this.dto){
			this.dto.getGameAct().move(-1, 0,this.dto.getGameMap());
		}
		return true;
	}
	/**
	 * ���Ʒ����(��)
	 */
	public boolean keyRight() {
		if(this.dto.isPause()){
			return true;
		}
		synchronized(this.dto){
			this.dto.getGameAct().move(1, 0,this.dto.getGameMap());
		}
		return true;
	}
	//===============TODO����ר��====================
	public void testLevelUp() {
		int  point=this.dto.getNowPoint();
		int rmLine=this.dto.getNowRemoveLine();
		int level=this.dto.getNowLevel();
		point+=10;
		rmLine+=1;
		if(rmLine%20==0){
			level+=1;
		}
		this.dto.setNowPoint(point);
		this.dto.setNowLevel(level);
		this.dto.setNowRemoveLine(rmLine);
	}
	
	@Override
	public boolean keyFunUp() {
		//���׼���
		this.dto.setCheat(true);
		testLevelUp();
		//this.plusPoint(4)
		return true;
	}

	@Override
	public boolean keyFunDown() {
		// ˲������
		if(this.dto.isPause()){
			return true;
		}
		while(!this.keyDown());
		return true;
	}

	@Override
	public boolean keyFunLeft() {
		// ��Ӱ����
		this.dto.changeShowShadow();
		return true;
	}

	@Override
	public boolean keyFunRight() {
		if(this.dto.isStart()){
			this.dto.changePause();
		}
		return true;
		//��ͣ
	}
	/**
	 * ���в���
	 */
	private int plusExp() {
		//�����Ϸ��ͼ
		boolean[][] map=this.dto.getGameMap();
		//ɨ����Ϸ��ͼ���Ƿ������
		int exp=0;
		for (int y = 0; y <GameDto.GAMEZONE_H; y++) {
			//�ж��Ƿ������
			if(this.isCanRemoveLine(y, map)){
				//����
				this.removeLine(y, map);
				//���Ӿ���ֵ
				exp++;
			}
		}
		return exp;
	}
	/**
	 * ���д���
	 * @param y
	 * @param map
	 */
	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y >0; y--) {
				map[x][y]=map[x][y-1];
			}
			map[x][0]=false;
		}
	}

	/**
	 * �ж�ĳһ���Ƿ������
	 * @param y
	 * @return
	 */
	private boolean isCanRemoveLine(int y,boolean[][] map){
		//�����ڶ�ÿһ����Ԫ��ɨ��
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if(!map[x][y]){
				//�����һ������Ϊfalse,ֱ��������һ��
				return false;
			}
		}
		return true;
	}

	@Override
	public void startGame() {
		//���������һ������
		this.dto.setNext(random.nextInt(MAX_TPYE));
		//���������Ϸ����
		dto.setGameAct(new GameAct(random.nextInt(MAX_TPYE)));
		//��Ϸ״̬��Ϊ��ʼ
		this.dto.setStart(true);
		//dto��ʼ��
		this.dto.dtoInit();
	}
	@Override
	public void mainAction() {
		this.keyDown();
	}
}
