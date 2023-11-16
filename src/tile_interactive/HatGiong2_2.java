package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class HatGiong2_2 extends InteractiveTile {

	GamePanel gp;
	public HatGiong2_2(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		water = 0;
		waterToGrow = 3;
		
		name = "CaTim2";
		down1 = setup("/HatGiong/HatGiong2_2");
		phatTrien = true;
		stackable = false; //Không thể chồng lên
		//destructible = true;
		collisionOn = true;
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
				
		if(entity.currentCongCu.type == type_watering) {
			isCorrecItem = true;
		}	
		return isCorrecItem;
	}
	public InteractiveTile getDestroyedForm() {
		if(water >= waterToGrow) {
			InteractiveTile tile = new HatGiong2_3(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			water = 0;
			return tile;
		}
		else if(water < waterToGrow) {
			InteractiveTile tile = new PlowedLand(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			return tile;
		}
		return null;
		
	}

}
