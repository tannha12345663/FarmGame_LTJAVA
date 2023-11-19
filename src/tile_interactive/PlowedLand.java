package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class PlowedLand extends InteractiveTile {

	GamePanel gp;
	public PlowedLand(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		water = 0;
		name = "Đất đã đào";
		down1 = setup("/tile_interactive/Tilled-Dirt_13");
		stackable = false;
		gieoTrongCay = true;
		collision = false;
		
		
		
	}
	//Kiểm tra xem khi chặt cây có phải là cây rìu không
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
				
		if(entity.currentCongCu.type == type_plant1 || entity.currentCongCu.type == type_plant2) {
			isCorrecItem = true;
		}
		
		return isCorrecItem;
		
	}
	public InteractiveTile getDestroyedForm() {
		if(gp.player.currentCongCu.type == type_plant1) {
			InteractiveTile tile = new HatGiong1_1(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			return tile;
		}
		else if(gp.player.currentCongCu.type == type_plant2){
			InteractiveTile tile = new HatGiong2_1(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			return tile;
		}
		return null;
	}
}
