package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Emotej extends SuperObject {
	GamePanel gp;
	public OBJ_Emotej(GamePanel gp) {
			
		this.gp = gp;
			name ="Emotej";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/Emotej/Teemo-Basic-emote-animations-sprite-sheet_01.png"));
				//Chia tỷ lệ hiển thị 
				image = uTool.scaleImage(image, gp.titleSize + 10, gp.titleSize + 15);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
