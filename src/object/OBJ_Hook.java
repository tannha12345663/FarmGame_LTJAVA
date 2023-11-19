package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hook extends Entity {

	public OBJ_Hook(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_hook;
		name = "Hook";
		down1 = setupOption("/DungCu/Hook",- 5, - 5);
		description = "[" + name +"] \n Dụng cụ này dùng \n để  thu hoạch ! ";
		//Set up phạm vi của công cụ
		targetArea.width = 36;
		targetArea.height = 36;
		price = 150;
	}

}
