package main;

import entity.NPC_Merchant;
import entity.NPC_Person;
import object.OBJ_Chest;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;
import object.OBJ_Well;
import tile_interactive.IT_DryTree;
import tile_interactive.Land;

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
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 7*gp.titleSize;
		gp.obj[i].worldY = 11*gp.titleSize;
		i++;
		gp.obj[i] = new OBJ_HatGiong1(gp);
		gp.obj[i].worldX = 15*gp.titleSize;
		gp.obj[i].worldY = 11*gp.titleSize;
		i++;
		gp.obj[i] = new OBJ_HatGiong2(gp);
		gp.obj[i].worldX = 20*gp.titleSize;
		gp.obj[i].worldY = 11*gp.titleSize;
		i++;
		
		
		
	}
	public void setNPC() {
		
		int i = 0;
		gp.npc[i] = new NPC_Person(gp);
		gp.npc[i].worldX = gp.titleSize * 10;
		gp.npc[i].worldY = gp.titleSize * 10;
		i++;
		
		gp.npc[i] = new NPC_Merchant(gp);
		gp.npc[i].worldX = gp.titleSize * 28;
		gp.npc[i].worldY = gp.titleSize * 7;
		i++;
		
//		gp.npc[i] = new NPC_Merchant(gp);
//		gp.npc[i].worldX = gp.titleSize * 7;
//		gp.npc[i].worldY = gp.titleSize * 15;
//		i++;
		
	}
	//Set up cho những khu đất đào được
	public void setDig() {
		int i = 0;
		
		//Hàng 1
		
//		gp.objDig[i] = new Land(gp, 18, 11);i++;
//		gp.objDig[i] = new Land(gp, 19, 11);i++;
//		gp.objDig[i] = new Land(gp, 20, 11);i++;
//		gp.objDig[i] = new Land(gp, 21, 11);i++;
//		gp.objDig[i] = new Land(gp, 26, 11);i++;
//		gp.objDig[i] = new Land(gp, 26, 11);i++;
		
		for(int j = 11;j< 15;j++) {
			for(int k = 18;k <28;k++) {
				gp.objDig[i] = new Land(gp, k, j);i++;
			}
		}
		
		//Hàng 2
	}
	//
	public void setMonster() {
		
	}
	public void setInteractiveTile() {
		int i = 0;
		gp.iTile[i] = new IT_DryTree(gp,14,15);
		i++;
		
		gp.iTile[i] = new IT_DryTree(gp,10,12);
		i++;
//		gp.iTile[i] = new Land(gp,13,13);
//		i++;
		gp.iTile[i] = new OBJ_Well(gp,13,13);
		i++;
		
		gp.iTile[i] = new IT_DryTree(gp,28,8);
		i++;
	}
}
