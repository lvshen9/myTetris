package dao;

import java.util.List;

import dto.Player;

/**
 * 数据持久层接口
 * @author lvshen
 *
 */
public interface Data {
	/**
	 * 获得数据
	 * @return
	 */
	List<Player> loadData();
	/**
	 * 存储数据
	 * @param players
	 */
	void saveData(Player players);
}
