package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;
import object.OBJ_Pickaxe;
import object.OBJ_Watering;

public class SaveLoad  {
	
	GamePanel gp;
	public SaveLoad (GamePanel gp) {
		this.gp = gp;
		
		
	}
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		switch (itemName) {
		case "Cây rìu": obj = new OBJ_Axe(gp);break;
		case "Rương bí ẩn ?" : obj = new OBJ_Chest(gp); break;
		case "Bap": obj = new OBJ_HatGiong1(gp);break;
		case "CaTim": obj = new OBJ_HatGiong2(gp);break;
		case "Cây cuốc": obj = new OBJ_Pickaxe(gp);break;
		case "Watering": obj = new OBJ_Watering(gp);break;
		}
		
		return obj;
	}
	
	public void save() {
		
		try {
			ObjectOutputStream cos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			//Timer
			ds.days = gp.days;
			ds.hours = gp.hours;
			ds.minutes = gp.minutes;
			
			//Player state
			ds.level = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.coin = gp.player.coins;
			ds.exp = gp.player.exps;
			ds.nextLevelExp = gp.player.nextLevel;
			ds.selectPlayer = gp.player.selectPlayer;
			
			//Player Inventory
			for(int i = 0;i< gp.player.inventory.size();i++) {
				ds.itemNames.add(gp.player.inventory.get(i).name);
				ds.itemAmounts.add(gp.player.inventory.get(i).amount);
			}
			
			//Player Equipment
			ds.currentCongCuSlot = gp.player.getCurrentCongCuSlot();
			System.out.println("Tổng số object có trong mảng: "+ gp.obj.length);
			//Object on Map
			ds.ObjectName = new String[gp.obj.length];
			ds.ObjectWorldX = new int[gp.obj.length];
			ds.objectWorldY = new int[gp.obj.length];
			for(int i = 0;i < gp.obj.length;i++) {
				if(gp.obj[i] == null) {
					ds.ObjectName[i] = "NA";
				}
				else {
					ds.ObjectName[i] = gp.obj[i].name;
					ds.ObjectWorldX[i] = gp.obj[i].worldX;
					ds.objectWorldY[i] = gp.obj[i].worldY;
				}
			}
			
			
			//Write the DataStorage object
			cos.writeObject(ds);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi khi save!");
		}
	}
	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			//Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			//Timer
			gp.days = ds.days;
			gp.hours = ds.hours;
			gp.minutes = ds.minutes;
			//Player State
			gp.player.level = ds.level;
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.coins = ds.coin;
			gp.player.exps = ds.exp;
			gp.player.nextLevel = ds.nextLevelExp;
			gp.player.selectPlayer = ds.selectPlayer;
			
			//Player Inventory 
			gp.player.inventory.clear();
			
			for(int i = 0; i <ds.itemNames.size();i++) {
				gp.player.inventory.add(getObject(ds.itemNames.get(i)));
				gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
			}
			//Player Equipment
			gp.player.currentCongCu = gp.player.inventory.get(ds.currentCongCuSlot);
			
			//Object on map (Entity)
			System.out.println("Tổng số object có trong mảng o data: "+ ds.ObjectName.length);
			for(int i  = 0 ; i < ds.ObjectName.length;i++) {
				if(ds.ObjectName[i].equals("NA")) {
					gp.obj[i]= null;
				}
				else {
					gp.obj[i] = getObject(ds.ObjectName[i]);
					gp.obj[i].worldX = ds.ObjectWorldX[i];
					gp.obj[i].worldY = ds.objectWorldY[i];
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi khi load data!"+ e.getMessage());
		}
	}
}
