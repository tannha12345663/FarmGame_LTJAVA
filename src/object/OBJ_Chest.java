package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject{
	GamePanel gp;
	public OBJ_Chest(GamePanel gp) {
			
		this.gp = gp;
			name ="Chest";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Chest_05.png"));
				uTool.scaleImage(image, gp.titleSize, gp.titleSize);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			collision = true;
		}
}
