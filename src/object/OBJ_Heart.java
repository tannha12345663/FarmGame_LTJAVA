package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{
	
	GamePanel gp;
	public OBJ_Heart(GamePanel gp) {
			
		this.gp = gp;
			name ="Heart";
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/giaoDien/Special-Icons_01.png"));  //Full
				image1 = ImageIO.read(getClass().getResourceAsStream("/giaoDien/Special-Icons_02.png")); // Half
				image2 = ImageIO.read(getClass().getResourceAsStream("/giaoDien/Special-Icons_03.png")); // Blank
				//Chia tỷ lệ hiển thị 
				image = uTool.scaleImage(image, gp.titleSize -5, gp.titleSize -5);
				image1 = uTool.scaleImage(image1, gp.titleSize -5, gp.titleSize -5);
				image2 = uTool.scaleImage(image2, gp.titleSize -5 , gp.titleSize -5);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
 