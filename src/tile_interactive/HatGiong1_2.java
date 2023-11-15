package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class HatGiong1_2 extends InteractiveTile {

	GamePanel gp;
	public HatGiong1_2(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		water = 0;
		waterToGrow = 3;
		
		name = "Bap2";
		down1 = setup("/HatGiong/HatGiong1_2");
		phatTrien = true;
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
			InteractiveTile tile = new HatGiong1_3(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			water = 0;
			return tile;
		}
		else if(water < waterToGrow) {
			InteractiveTile tile = new Land(gp, worldX/gp.titleSize, worldY/gp.titleSize);
			return tile;
		}
		return null;
		
	}

}
