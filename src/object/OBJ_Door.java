package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject {
	GamePanel gp;
	public OBJ_Door(GamePanel gp) {
			
		this.gp = gp;
			name ="Door";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_10.png"));
				uTool.scaleImage(image, gp.titleSize, gp.titleSize);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			collision = true;
		}
}
