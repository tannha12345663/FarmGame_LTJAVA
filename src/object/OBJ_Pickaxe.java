package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {

	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name = "Cây cuốc";
		down1 = setupOption("/DungCu/Pickaxe",- 5, - 5);
		description = "[" + name +"] \n Cuốc được dùng \n để đào đất.";
	}

}
