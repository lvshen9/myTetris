package service;

public interface GameService {
	/**
	 * �������
	 */
	public boolean keyUp();
	/**
	 * �������
	 */
	public boolean keyDown();
	/**
	 * �������
	 */
	public boolean keyLeft();
	/**
	 * �������
	 */
	public boolean keyRight();
	/**
	 * Y
	 */
	public boolean keyFunUp();
	/**
	 * A
	 */
	public boolean keyFunDown();
	/**
	 * X
	 */
	public boolean keyFunLeft();
	/**
	 * B
	 */
	public boolean keyFunRight();

	/**
	 * �������߳̿�ʼ��Ϸ
	 */
	public void startGame();
	/**
	 * ��Ϸ��Ҫ��Ϊ
	 */
	public void mainAction();
}
