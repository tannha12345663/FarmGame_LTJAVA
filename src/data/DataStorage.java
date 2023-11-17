package data;

import java.io.Serializable;
import java.util.ArrayList;
public class DataStorage implements Serializable {
	
	//Timer
	long days = 1, hours = 23,minutes = 0;
	
	// Player state
	int level;
	int life;
	int maxLife;
	int exp;
	int nextLevelExp;
	int coin;
	
	//type Character 
	int selectPlayer;
	
	//Player Inventory
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmounts = new ArrayList<>();
	int valueConsumable[];
	
	int currentCongCuSlot;
	
	//Object on Map
	String ObjectName [] ;
	int ObjectWorldX [] ;
	int objectWorldY [];
	//Sức chứa bình tưới
	
	
	String InteracName[];
	int InteracWorldX [];
	int InteracWorldY [];
	int water [];
	
}
