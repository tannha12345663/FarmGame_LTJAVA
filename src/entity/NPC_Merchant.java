package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;
import object.OBJ_Pickaxe;
import object.OBJ_Watering;

public class NPC_Merchant extends Entity {

	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction ="down";
		speed = 0;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		getImage();
		setDialogue();
		setItems();
	}
	
	public void getImage() {	
		
		left1 = setup("/npc_Merchant/down01");
		left2 = setup("/npc_Merchant/down02");
		left3 = setup("/npc_Merchant/down01");
		left4 = setup("/npc_Merchant/down02");
		
		down1 = setup("/npc_Merchant/down01");
		down2 = setup("/npc_Merchant/down02");
		down3 = setup("/npc_Merchant/down01");
		down4 = setup("/npc_Merchant/down02");
		

	}
	public void setDialogue() {
		//Lưu trữ đoạn hội thoại cho NPC
		dialogue[0]= "Xin chào Nhã Trương. Tôi tên Daniel và \n tôi có một vài món hàng dành cho bạn. \n Bạn có muốn trao đổi không?"; 
	}
	//Tạo hành vi nói cho NPC
	public void speak() {
		if(dialogue[dialogưeIndex] == null) {
			dialogưeIndex = 0 ;
		}
		gp.ui.currentDialouge = dialogue[dialogưeIndex];
		dialogưeIndex ++;
		
		//NPC sẽ quay lưng khi chúng ta bắt chuyện
		switch (gp.player.direction) {
		case "up":  
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "right":
			direction = "left";
			break;
		case "left":
			direction = "right";
			break;
		}
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
	public void setItems() {
		
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Pickaxe(gp));
		inventory.add(new OBJ_Watering(gp));
		inventory.add(new OBJ_HatGiong1(gp));
		inventory.add(new OBJ_HatGiong2(gp));
		inventory.add(new OBJ_Chest(gp));
	}
	
}
