package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{

	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		
		name ="Key";
		down1 = setup("/tiles_Nha/key");
		collision = true;
		
//		solidArea.x= 0;
//		solidArea.y = 16;
		solidArea.width = 54;
		solidArea.height = 32;
//		solidAreaDefaultX = solidArea.x;
//		solidAreaDefaultY = solidArea.y;
	}
	
}
