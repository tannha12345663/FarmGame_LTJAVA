package entity;
import java.util.Random;

import main.GamePanel;


public class NPC_Person extends Entity{
	
	public boolean check = false;
	public NPC_Person(GamePanel gp) {
		super(gp);
		
		direction ="down";
		speed = 1;
		
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/npc_person/player_up01");
		up2 = setup("/npc_person/player_up02");
		up3 = setup("/npc_person/player_up03");
		up4 = setup("/npc_person/player_up04");
		
		down1 = setup("/npc_person/player_down01");
		down2 = setup("/npc_person/player_down02");
		down3 = setup("/npc_person/player_down03");
		down4 = setup("/npc_person/player_down04");
		
		left1 = setup("/npc_person/player_left01");
		left2 = setup("/npc_person/player_left02");
		left3 = setup("/npc_person/player_left03");
		left4 = setup("/npc_person/player_left04");
		
		right1 = setup("/npc_person/player_right01");
		right2 = setup("/npc_person/player_right02");
		right3 = setup("/npc_person/player_right03");
		right4 = setup("/npc_person/player_right04");
	}
	
	public void setDialogue() {
		
		//Lưu trữ đoạn hội thoại cho NPC
		dialogue[0]= "Xin chào "+gp.usernameInput+". \n Bạn là người mới đến hòn đảo này à"; 
		dialogue[1]= "Rất vui khi được gặp bạn.";
		dialogue[2]= "Tôi xin giới thiệu tôi tên là John \n là người chuyên thu mua nông sản.";
	}
	//Tạo hành vi cho nhân vật
	public void setAction() {
		actionLockCounter ++;
		//120 = 2s
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1; // chọn một số từ 1 - 100
			
			if (i <= 25) {
				direction = "up";
				check = true;
			}
			if(i > 25 && i <= 50) {
				direction = "down";
				check = true;
			}
			if(i > 50 && i <= 75) {
				direction = "left";
				check = true;
			}
			if(i > 75 && i <= 100) {
				direction = "right";
				check = true;
			}
			actionLockCounter = 0;
		}
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
	}
}
