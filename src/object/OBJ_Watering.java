package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Watering extends Entity {

	public OBJ_Watering(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_watering;
		name = "Watering";
		down1 = setupOption("/DungCu/Watering",- 5, - 5);
		description = "[" + name +"] \n Khi cần hãy tưới \n nước cho cây ";
		//Set up phạm vi của công cụ
		targetArea.width = 36;
		targetArea.height = 36;
	}

}
