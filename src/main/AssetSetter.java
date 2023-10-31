package main;

import entity.NPC_Person;
import object.OBJ_Door;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter (GamePanel gp) {
		this.gp=gp;
	}
	
	//Thiết lập mặc định cho đối tượng đặc biệt
	public void setObject() {
		
//		gp.obj[0] = new OBJ_Key(gp);
//		gp.obj[0].worldX = 11*gp.titleSize;
//		gp.obj[0].worldY = 15*gp.titleSize;
//		
//		gp.obj[1] = new OBJ_Key(gp);
//		gp.obj[1].worldX = 21*gp.titleSize;
//		gp.obj[1].worldY = 11*gp.titleSize;
//		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 8*gp.titleSize;
		gp.obj[2].worldY = 8*gp.titleSize;
//		
//		gp.obj[3] = new OBJ_Key(gp);
//		gp.obj[3].worldX = 25*gp.titleSize;
//		gp.obj[3].worldY = 11*gp.titleSize;
//		
//		gp.obj[4] = new OBJ_Chest(gp);
//		gp.obj[4].worldX = 7*gp.titleSize;
//		gp.obj[4].worldY = 11*gp.titleSize;
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Person(gp);
		gp.npc[0].worldX = gp.titleSize * 10;
		gp.npc[0].worldY = gp.titleSize * 10;
		
	}
}
