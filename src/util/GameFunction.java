package util;

public class GameFunction {
	/**
	 * �����߳�˯��ʱ��
	 * @param level
	 * @return
	 */
	public static long getSleepTimeByLevel(int level){
		long sleep=(-40 * level + 600);
		sleep = sleep < 100 ? 100 : sleep;
		return sleep;
	}
}
