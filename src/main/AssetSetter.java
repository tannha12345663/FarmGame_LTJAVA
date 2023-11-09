package main;

import entity.NPC_Person;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;
import object.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter (GamePanel gp) {
		this.gp=gp;
	}
	
	//Thiết lập mặc định cho đối tượng đặc biệt
	public void setObject() {
		
		int i =0;
//		gp.obj[0] = new OBJ_Key(gp);
//		gp.obj[0].worldX = 11*gp.titleSize;
//		gp.obj[0].worldY = 15*gp.titleSize;
//		i++;
		
//		gp.obj[1] = new OBJ_Key(gp);
//		gp.obj[1].worldX = 21*gp.titleSize;
//		gp.obj[1].worldY = 11*gp.titleSize;
//		
//		gp.obj[2] = new OBJ_Door(gp);
//		gp.obj[2].worldX = 8*gp.titleSize;
//		gp.obj[2].worldY = 8*gp.titleSize;
//		
//		gp.obj[3] = new OBJ_Key(gp);
//		gp.obj[3].worldX = 25*gp.titleSize;
//		gp.obj[3].worldY = 11*gp.titleSize;
//		
		gp.obj[1] = new OBJ_Chest(gp);
		gp.obj[1].worldX = 7*gp.titleSize;
		gp.obj[1].worldY = 11*gp.titleSize;
		i++;
		gp.obj[2] = new OBJ_HatGiong1(gp);
		gp.obj[2].worldX = 15*gp.titleSize;
		gp.obj[2].worldY = 11*gp.titleSize;
		i++;
		gp.obj[3] = new OBJ_HatGiong2(gp);
		gp.obj[3].worldX = 20*gp.titleSize;
		gp.obj[3].worldY = 11*gp.titleSize;
		i++;
		
		
		
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Person(gp);
		gp.npc[0].worldX = gp.titleSize * 10;
		gp.npc[0].worldY = gp.titleSize * 10;
		
	}
	//Set up cho những khu đất đào được
	public void setDig() {
	}
	//
	public void setMonster() {
		
	}
	
}
