package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		name ="Heart";
		
		image = setup("/giaoDien/Special-Icons_01");
		image1 = setup("/giaoDien/Special-Icons_02");
		image2 = setup("/giaoDien/Special-Icons_03");
		}
}
 