package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile {

	GamePanel gp;
	
	public IT_DryTree(GamePanel gp, int col, int row) {
		super(gp, col,row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		stackable = false;
		water = 0;
		name = "Cây xanh";
		down1 = setup("/tile_interactive/Basic-Grass-Biom-things-1_13");
		destructible = true;
		collision = true;
		
		life = 3;
	}
	//Kiểm tra xem khi chặt cây có phải là cây rìu không
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
		
		if(entity.currentCongCu.type == type_axe) {
			isCorrecItem = true;
		}
		return isCorrecItem;
	}
	public void playSE() {
		//Âm thanh khi chặt cây
		gp.playSE(25);
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_TrunkTree(gp, worldX/gp.titleSize, worldY/gp.titleSize);
		
		return tile;
	}
}
