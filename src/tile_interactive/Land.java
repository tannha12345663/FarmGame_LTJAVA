package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class Land extends InteractiveTile{

	GamePanel gp;
	public Land(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		
		name = "Đất";
		down1 = setup("/tile_interactive/Tilled-Dirt_08");
		stackable = false; //Không thể chồng lên
		destructible = true;
		collisionOn = false;
//		solidArea.x = 1;
//		solidArea.y = 1;
//		solidArea.height = 1;
//		solidArea.width = 1;
//		solidAreaDefaultX = solidArea.x;
//		solidAreaDefaultY = solidArea.y;
		
		//Set up có thể cho người chơi đi xuyên qua được
	}
	//Kiểm tra xem khi đào đất có phải là cây cuốc không
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
			
		if(entity.currentCongCu.type == type_pickaxe) {
			isCorrecItem = true;
		}
		return isCorrecItem;
	}
	public void playSE() {
		//Âm thanh khi đào đất
		gp.playSE(20);
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new PlowedLand(gp, worldX/gp.titleSize, worldY/gp.titleSize);
		
		return tile;
	}

}
