package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BoxCoin extends Entity {

	public OBJ_BoxCoin(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name ="BoxCoin";
		image = setupOption("/giaoDien/boxCoin",50,10);
		//Chia tỷ lệ hiển thị 
		//image = uTool.scaleImage(image, gp.titleSize + 10, gp.titleSize + 15);
	}

}
