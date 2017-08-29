package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataTest implements Data {
	
	public DataTest(HashMap<String,String> param) {
	}
	
	@Override
	public List<Player> loadData() {
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("小明", 100));
		players.add(new Player("小明", 1000));
		//players.add(new Player("小明", 2000));
		//players.add(new Player("小明", 3000));
		players.add(new Player("小明", 4000));
		return players;
	}

	@Override
	public void saveData(Player players) {
		System.out.println();
	}

}
