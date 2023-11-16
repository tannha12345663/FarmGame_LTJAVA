package object;


import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	
	GamePanel gp;
	int value = 2;
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		
		this.gp=gp;
		
		type = type_consumable;
		name ="Rương bí ẩn ?";
		maxLife = 4;
		life = maxLife;
		collision = true;
		stackable = true; // cho phép lưu trữ nhiều
		price = 200;
		down1 = setup("/tiles_Nha/Chest_04");
		description = "[" + name +"] \n Khi bạn sử dụng "
				+ "\n rương này bạn sẽ "
				+ "\n được thêm 2 máu "
				+ "\n và nhận thêm đồ.";
		}
	public void use(Entity entity) {
		// TODO Auto-generated method stub
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialouge = "Bạn đã dùng "+ name+"!";
		
		
		
		Random random = new Random();

        // Tạo số nguyên ngẫu nhiên trong phạm vi 0 đến 100
        int randomNumber = random.nextInt(50);

        // In số nguyên ngẫu nhiên ra màn hình
        System.out.println("Số nguyên ngẫu nhiên là: " + randomNumber);

        // Kiểm tra xem số nguyên này là số chẵn hay số lẻ
        if (randomNumber % 2 == 0) {
        	gp.player.life += value;
    		if(gp.player.life > gp.player.maxLife) {
    			gp.player.life = gp.player.maxLife;
    		}
            System.out.println(randomNumber + " là số chẵn.");
        } else {
        	Entity loot = new OBJ_HatGiong1(gp);
        	Entity loot1 = new OBJ_HatGiong2(gp);
        	for(int i = 0 ; i < randomNumber ; i++) {
        		if(i % 2 == 0) {
        			gp.player.canObtainItem(loot);
        		}else {
        			gp.player.canObtainItem(loot1);
        		}
        	}
        	
            System.out.println(randomNumber + " là số lẻ.");
        }
		
		//Thêm hiệu ứng
		gp.playSE(24);
	}
//	public void interact() {
//		
//		gp.gameState = gp.dialogueState;
//		if(opened == false) {
//			gp.playSE(value);
//			
//			StringBuilder sb = new StringBuilder();
//			sb.append("Bạn có thê")
//		}
//		
//	}
}
