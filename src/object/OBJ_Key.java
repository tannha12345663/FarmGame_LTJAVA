package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject{

	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		
		this.gp = gp;
		name ="Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/key.png"));
			uTool.scaleImage(image, gp.titleSize, gp.titleSize);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}
	
}
