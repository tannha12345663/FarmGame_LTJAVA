package object;


import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name ="Chest";
		maxLife = 4;
		life = maxLife;
		
		down1 = setup("/tiles_Nha/Chest_04");
		collision = true;
		
		
		}
}
