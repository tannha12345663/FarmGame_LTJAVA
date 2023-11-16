package object;
import main.GamePanel;
import tile_interactive.InteractiveTile;

public class OBJ_Well extends InteractiveTile {

	GamePanel gp;
	public OBJ_Well(GamePanel gp, int col, int row) {
		super(gp, col, row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		
		
		name = "Well";
		down1 = setup("/tiles/Welll");
		collision = true;
	}


}
