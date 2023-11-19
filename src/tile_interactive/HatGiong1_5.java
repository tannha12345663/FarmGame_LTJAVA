package tile_interactive;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;

public class HatGiong1_5 extends InteractiveTile{

	public HatGiong1_5(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		water = 0;
		waterToGrow = 4;
		daytoGrow = 5;
		type = type_consumable;
		stackable = true; //Cho phép lưu trữ
		price = 200; //Giá bán sẽ là 100
		name = "Bắp chín";
		down1 = setup("/HatGiong/HatGiong1_5");
		phatTrien = true;
		description = "[" + name +"] \n Khi bạn sử dụng "
				+ "\n bạn sẽ được thêm"
				+ "\n 2 máu hoặc có  "
				+ "\n thể đem bán.";
		//destructible = true;
		collisionOn = true;
	}
	public InteractiveTile getDestroyedForm() {
		if(daytoGrow > 5) {
			InteractiveTile tile = new Land(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			water = 0;
			return tile;
		}
		return null;
	}
	public InteractiveTile pickPlantForm() {
		if(daytoGrow == 5) {
			InteractiveTile tile = new PlowedLand(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			water = 0;
			return tile;
		}
		return null;
	}
	public void use(Entity entity) {
		// TODO Auto-generated method stub
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialouge = "Bạn đã ăn "+ name+"!";
		
		
        // Kiểm tra xem số nguyên này là số chẵn hay số lẻ
		gp.player.life += 2;
		if(gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		
		//Thêm hiệu ứng
		gp.playSE(24);
	}
}
