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
		
		
		name = "Đất đã đào";
		down1 = setup("/tile_interactive/Tilled-Dirt_13");
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	//Kiểm tra xem khi chặt cây có phải là cây rìu không
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
				
		if(entity.currentCongCu.type == type_pickaxe) {
			isCorrecItem = true;
		}
		return isCorrecItem;
	}
}
