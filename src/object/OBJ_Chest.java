package object;


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
		
		gp.player.life += value;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
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
