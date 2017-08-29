package entity;

import java.awt.Point;
import java.util.List;

import config.GameConfig;

public class GameAct {
	/**
	 * 方块数组
	 */
	private Point[] actPoints=null;
	/**
	 * 方块编号
	 */
	private int typeCode;
	
	private static final int MIN_X=GameConfig.getSystemConfig().getMinX();
	private static final int MAX_X=GameConfig.getSystemConfig().getMaxX();
	private static final int MIN_Y=GameConfig.getSystemConfig().getMinY();
	private static final int MAX_Y=GameConfig.getSystemConfig().getMaxY();
	
	private static final List<Point[]> TYPE_CONFIG=GameConfig.getSystemConfig().getTypeConfig();
	private static final List<Boolean> TYPE_Round=GameConfig.getSystemConfig().getTypeRound();
	/*static{
		//TODO 写入配置文件
		TYPE_CONFIG=new ArrayList<Point[]>(7);
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(6,0)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(4,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(3,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(5,0),new Point(3,1),new Point(4,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(5,0),new Point(4,1),new Point(5,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(5,0),new Point(5,1)});
		TYPE_CONFIG.add(new Point[]{new Point(4,0),new Point(3,0),new Point(4,1),new Point(5,1)});
	}*/
	
	public GameAct(int typeCode){
		this.init(typeCode);
		
	}
	public void init(int typeCode){
		this.typeCode=typeCode;
		Point[] points=TYPE_CONFIG.get(typeCode);
		actPoints=new Point[points.length];	
		for (int i = 0; i < points.length; i++) {
			actPoints[i]=new Point(points[i].x,points[i].y);
		}
		
		
	}
	/**
	 * @return actPoint
	 */
	public Point[] getActPoints() {
		return actPoints;
	}
	/**
	 * 方块移动
	 * @param moveX X轴偏移量
	 * @param moveY Y轴偏移量
	 */
	public boolean move(int moveX,int moveY,boolean[][] gameMap){
		
		
		
		//移动处理
		for (int i = 0; i < actPoints.length; i++) {
			int newX=actPoints[i].x+moveX;
			int newY=actPoints[i].y+moveY;
			
			if (isOverZone(newX, newY, gameMap)) {
				return false;
			}
		}
		for (int i = 0; i < actPoints.length; i++) {
			actPoints[i].x+=moveX;
			actPoints[i].y+=moveY;
		}
		return true;
	}
	/**
	 * 方块旋转
	 * 
	 * 顺时针算法公式
	 * A.x=o.y+o.x-B.y
	 * A.y=o.y-o.x+B.x
	 */
	public void round(boolean[][] gameMap){
		
		if(!TYPE_Round.get(this.typeCode)){
			return;
		}
		for (int i = 0; i < actPoints.length; i++) {
			int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
			int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
			
			if(isOverZone(newX, newY,gameMap)){
				return;
			}
			
		}
		for (int i = 0; i < actPoints.length; i++) {
			int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
			int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
			actPoints[i].x=newX;
			actPoints[i].y=newY;
		}
	}
	/**
	 * 判断是否超出边界
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isOverZone(int x,int y,boolean[][] gameMap){
		return x<MIN_X||x>MAX_X||y<MIN_X||y>MAX_Y||gameMap[x][y];
		
	}
	/**
	 *获取方块编号
	 * @return actCode
	 */
	public int getTypeCode() {
		return typeCode;
	}
	
}
