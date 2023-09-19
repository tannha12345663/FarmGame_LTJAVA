package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	//Mỗi đối tượng đều phải có solidArea
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
//		// STOP MOVING CAMERA
//		  if(gp.player.worldX < gp.player.screenX) {
//		   screenX = worldX;
//		  }
//		  if(gp.player.worldY < gp.player.screenY) {
//		   screenY = worldY;
//		  }   
//		  int rightOffset = gp.screenWidth - gp.player.screenX;      
//		  if(rightOffset > gp.worldWidth - gp.player.worldX) {
//		   screenX = gp.screenWidth - (gp.worldWidth - worldX);
//		  } 
//		  int bottomOffset = gp.screenHeight - gp.player.screenY;
//		  if(bottomOffset > gp.worldHeight - gp.player.worldY) {
//		   screenY = gp.screenHeight - (gp.worldHeight - worldY);
//		  }
//		  ///////////////////
		
		//Chỉ vẽ nhưng ô cần thiết tại vị trí của người chơi còn lại thì không cần vẽ để tăng hiệu suất trò chơi
		//Kiểm tra 
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
			try {
				//System.out.println("Bạn đang ở ô : "+ tileNum);
				g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize,null);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Xảy ra lỗi trong quá trình set up Object");
			}
		}
	}
}
