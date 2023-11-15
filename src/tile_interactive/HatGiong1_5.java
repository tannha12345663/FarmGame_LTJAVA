package tile_interactive;

import main.GamePanel;

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
}
