package object;

import main.GamePanel;
import tile_interactive.InteractiveTile;

public class OBJ_Trap extends InteractiveTile {

	GamePanel gp;
	public OBJ_Trap(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		name ="Trap";
		down1 = setup("/tile_interactive/Trap");
		collision = false;
	}

}
