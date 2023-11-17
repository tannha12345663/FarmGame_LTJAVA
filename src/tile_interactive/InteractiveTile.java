package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {

	GamePanel gp;
	public boolean destructible = false;
	public boolean gieoTrongCay = false;
	public boolean phatTrien = false;
	public int daytoGrow;
	//Tưới nước cho cây
	public int water = 0;
	public int waterToGrow = 0;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp=gp;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
		return isCorrecItem;
	}
	
	public void playSE() {
		
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	public InteractiveTile pickPlantForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	public void update() {
		
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}

}
