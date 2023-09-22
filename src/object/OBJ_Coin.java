package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Coin extends SuperObject {
	GamePanel gp;
	public OBJ_Coin(GamePanel gp) {
			
		this.gp = gp;
			name ="Coin";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/giaoDien/Coin.png"));  
				uTool.scaleImage(image, gp.titleSize, gp.titleSize);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
