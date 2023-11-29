package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Logo extends Entity {

	public OBJ_Logo(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name ="Logo";
		image = setupOption("/background/Logo_nnt",20,-15);
	}
	
}
