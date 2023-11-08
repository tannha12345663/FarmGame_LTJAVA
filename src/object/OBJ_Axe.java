package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name = "Cây rìu";
		down1 = setupOption("/DungCu/Axe",- 5, - 5);
		description = "[" + name +"] \n Dùng để đào đất \n trước khi gieo trồng ! ";
	}

}
