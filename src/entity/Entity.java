package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1,up2,up3,up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4;
	public String direction, direction1;
	
	public int spriteCounter =0, spriteCounter1 =0;
	public int spriteNum = 1, spriteNum1 = 1;
	
	//Thiết lập định hình cho nhân vật không thể đi qua các vật cản
	//B1: Thiết lập khối hình khối bên trong nhân vật
	public Rectangle solidArea = new Rectangle(0,0,48,48) ; // Thiết lập giá trị mặc định cho tất cả NPC
	//Thiết lập các khối set up để phát hiện va chạm 
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	//Phạm vị bộ đếm khóa hành động
	public int actionLockCounter = 0;
	//Phạm vi của đoạn hội thoại danh cho mỗi NPC
	String dialogue[] = new String[20];
	int dialogưeIndex = 0;
	
	//Trạng thái máu của nhân vật 
	public int maxLife;
	public int life;
	
	
	
	//Check NPC có đc direction hay không 
	boolean check = false;
	//Bảng điều khiển trò chơi
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	//Thiết lập animation cho NPC
	public void setAction() {
		//AI tự động điều khiển NPC di chuyển
	}
	public void speak() {
		
	}
	public void update() {
		
		setAction();
		//kiểm tra chiongws ngại vật
		collisionOn = false;
		gp.cChecker.checkTile(this);
		//Kiểm tra đối tượng thứ 2
		gp.cChecker.checkObject(this, false);
		//Kiểm tra đối tượng người chơi
		gp.cChecker.checkPlayer(this);
		
		//Nếu vật thẩ xuyên được là false , người chơi có thể di chuyển
		if(collisionOn == false) {
			switch (direction) {
			case "up": worldY 	-= speed; break;
			case "down": worldY += speed;break;
			case "left":worldX 	-= speed; break;
			case "right":worldX += speed;break;
			}
		}
		
		//Sprite sẽ được tăng lên khi người dùng nhấn vào các nút di chuyển
		spriteCounter++;
		//Với mỗi khung hình được lặp đi lặp lại cho tới khi lớn hơn 12 sẽ thực hiện animation
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
	}
	//Vẽ nhân vật
	public void draw (Graphics2D g2) {
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX; 
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) 
		{
			try {
				
			//System.out.println("Bạn đang : "+ spriteNum);
			switch (direction) {
			case "up": {
				
				//Kiểm tra check 
				if (check == false) {
					//Hành động đang đứng thở
					if(spriteNum == 1) {
						image = up1;
					}
					else if (spriteNum == 2) {
						image = up2;
					}
				}
				else {
					//Hành động bước đi
					if(spriteNum == 1) {
						image = up3;
					}
					else if (spriteNum == 2) {
						image = up4;
					}
				}
				break;
			}
			case "down": {
				
				if (check == false) {
					//Hành động đang đứng thở
					if(spriteNum == 1) {
						image = down1;
					}
					else if(spriteNum == 2) {
						image = down2;
					}
				}
				else {
					//Hành động bước đi
					if(spriteNum == 1) {
						image = down3;
					}
					else if(spriteNum == 2) {
						image = down4;
					}
					
					
				}

				break;
			}
			case "left": {
				
				if(check == false) {
					//Hành động thở
					if(spriteNum == 1) {
						image = left1;
					}
					else if(spriteNum == 2) {
						image = left2;
					}
				}
				else {
					//Hành động bước đi
					if(spriteNum == 1) {
						image = left3;
					}
					else if(spriteNum == 2) {
						image = left4;
					}
				}
				
				

				break;
			}
			case "right": {
				
				if(check == false) {
					//Hành động thở
					if(spriteNum == 1) {
						image = right1;
					}
					else if(spriteNum == 2) {
						image = right2;
					}
				}
				else {
					//Hành động bước đi
					if(spriteNum == 1) {
						image = right3;
					}
					else if(spriteNum == 2) {
						image = right4;
					}
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: ");
			}
					g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize,null);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Xảy ra lỗi trong quá trình set up Object");
			}
		}
		
	}
	//Thiết lập phần vẽ cho các nhân vật thông qua setup
	public BufferedImage setup(String imagePath) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, gp.titleSize, gp.titleSize);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return image;
	}
	
}
