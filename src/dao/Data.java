package dao;

import java.util.List;

import dto.Player;

/**
 * ���ݳ־ò�ӿ�
 * @author lvshen
 *
 */
public interface Data {
	/**
	 * �������
	 * @return
	 */
	List<Player> loadData();
	/**
	 * �洢����
	 * @param players
	 */
	void saveData(Player players);
}
