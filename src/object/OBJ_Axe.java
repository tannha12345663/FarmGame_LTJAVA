package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

	public OBJ_Axe(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_axe;
		name = "Cây rìu";
		down1 = setupOption("/DungCu/Axe",- 5, - 5);
		description = "[" + name +"] \n Dùng để đào đất \n trước khi gieo trồng ! ";
		//Set up phạm vi của công cụ
		targetArea.width = 36;
		targetArea.height = 36;
		price = 35;
	}
	
}
