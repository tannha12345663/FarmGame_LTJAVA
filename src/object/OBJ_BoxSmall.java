package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_BoxSmall extends SuperObject {
	GamePanel gp;
	public OBJ_BoxSmall(GamePanel gp) {
			
		this.gp = gp;
			name ="BoxSmall";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/giaoDien/boxSmall.png"));  
				image = uTool.scaleImage(image, gp.titleSize + 220, gp.titleSize + 80);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
