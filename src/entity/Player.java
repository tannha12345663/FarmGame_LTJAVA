package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	
	KeyHandler keyH;
	int check = 0;
	
	//Thiết lập camera di theo nhân vật người chơi với tọa độ riêng biệt
	public final int screenX;
	public final int screenY;
//	public int hasKey = 0; // Khai báo đối tượng vật phẩm key
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		//Thiết lập thông số màn hình camera
		screenX = gp.screenWidth/2 - (gp.titleSize/2);
		screenY = gp.screenHeight/2 - (gp.titleSize/2);
		
		
		//Thiết lập khối định hình cho nhân vật 
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y= 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.titleSize*10;
		worldY = gp.titleSize*13;
		speed = 4;
		direction ="down"; //Đặt hướng mặc định cho nhân vật
				
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/player/Basic-Charakter-up_01");
		up2 = setup("/player/Basic-Charakter-up_02");
		up3 = setup("/player/Basic-Charakter-up_03");
		up4 = setup("/player/Basic-Charakter-up_04");
		
		down1 = setup("/player/Basic-Charakter-down_01");
		down2 = setup("/player/Basic-Charakter-down_02");
		down3 = setup("/player/Basic-Charakter-down_03");
		down4 = setup("/player/Basic-Charakter-down_04");
		
		left1 = setup("/player/Basic-Charakter-Left_01");
		left2 = setup("/player/Basic-Charakter-Left_02");
		left3 = setup("/player/Basic-Charakter-Left_03");
		left4 = setup("/player/Basic-Charakter-Left_04");
		
		right1 = setup("/player/Basic-Charakter-Right_01");
		right2 = setup("/player/Basic-Charakter-Right_02");
		right3 = setup("/player/Basic-Charakter-Right_03");
		right4 = setup("/player/Basic-Charakter-Right_04");
	}
//	public BufferedImage setup(String imageName) {
//		
//		UtilityTool uTool = new UtilityTool();
//		BufferedImage image = null;
//		
//		try {
//			image = ImageIO.read(getClass().getResourceAsStream("/player/"+ imageName+".png"));
//			image = uTool.scaleImage(image, gp.titleSize, gp.titleSize);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return image;
//	}
	
	public void update () {
		
		int npcIndex;
		//Ở bước nãy sẽ chỉ nhận sự kiện khi người dùng nhấn phím
		if( keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true|| keyH.rightPressed == true) {
			//System.out.println("Người chơi đã nhấn nút");
			check = 1;
			if(keyH.upPressed == true) {
				direction ="up";
				//worldY -= speed;
			}
			else if(keyH.downPressed == true) {
				direction ="down";
				//worldY += speed;
			}
			else if(keyH.leftPressed == true) {
				direction ="left";
				//worldX -= speed;	
			}
			else if(keyH.rightPressed==true) {
				direction ="right";
				//worldX += speed;
			}
			//System.out.println("Bạn đang ở ô : "+ worldX/48 + ", Y:  "+ worldY/48);
			// Kiểm tra vật thể có rào cản hay không
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//Kiểm tra xung đột các đối tượng
			int objIndex = gp.cChecker.checkObject(this, true);
			//Kế đó sẽ làm event điều gì sẽ xảy ra khi người chơi va chạm vật này
			pickUpObject(objIndex);
			//Check NPC collision (Kiểm tra va chạm với npc)
			npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			
			//Nếu vật thẩ xuyên được là false , người chơi có thể di chuyển
			if(collisionOn == false) {
				switch (direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed;break;
				case "left":worldX -= speed; break;
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
		else {
			npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			check = 0;
			
			//Sprite sẽ được tăng lên khi người dùng nhấn vào các nút di chuyển
			spriteCounter1++;
			//Với mỗi khung hình được lặp đi lặp lại cho tới khi lớn hơn 12 sẽ thực hiện animation
			if(spriteCounter1 > 12) {
				if(spriteNum1 == 1) {
					spriteNum1 = 2;
				}
				else if(spriteNum1 == 2) {
					spriteNum1 = 1;
				}
				spriteCounter1 = 0;
			}
			
		}
		//Check tọa độ
		//System.out.println("Vị trí hiện tại x: "+ worldX +", y :"+ worldY);
		
	}
	
	//Hàm này có ý nghĩa sẽ nhặt các món item trên map
	public void pickUpObject(int i) {
		
		//Nếu là 999 sẽ không có va chạm và ngược lại
		if(i != 999) {
			
			
			//Tutorial
//			String objectName = gp.obj[i].name;
//			
//			switch (objectName) {
//			case "Key": 
//				//Hiệu ứng âm thanh nhặt key
//				gp.playSE(9);
//				hasKey ++;
//				//Nếu chúng ta lại gần và nhặt thì item sẽ tự động biến mất
//				gp.obj[i] = null;
//				//Hiển thị nội dung tin nhắn khi nhặt key
//				gp.ui.showMessage("Bạn đã nhặt chìa khóa! ");
//				//System.out.println("Số lượng khóa đã lum :"+hasKey);
//				break;
//			
//			case "Door":
//				if (hasKey > 0) {
//					//Hiệu ứng âm thanh khi mở cửa
//					gp.playSE(10);
//					gp.obj[i] = null;
//					hasKey--;
//					gp.ui.showMessage("Bạn vừa mở cửa! ");
//					
//				}else {
//					gp.ui.showMessage("Bạn cần chìa khóa để mở cửa! ");
//				}
//				//System.out.println("Số lượng khóa đã lum :"+hasKey);
//				break;
//			case "Chest":
//				if (hasKey > 0) {
//					//Hiệu ứng âm thanh khi mở cửa
//					gp.playSE(9);
//					gp.obj[i] = null;
//					hasKey--;
//					gp.ui.showMessage("Bạn vừa mở rương! ");
//					gp.ui.gameFinished = true;
//					gp.stopMusic();
//				}else {
//					gp.ui.showMessage("Bạn cần chìa khóa để mở rương này! ");
//				}
//				//System.out.println("Số lượng khóa đã lum :"+hasKey);
//				break;
//		}
			
	}
}
	public void interactNPC(int i) {
		if(i != 999) {
//			System.out.println("Bạn đã va chạm với npc");
			//
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			
		}
		gp.keyH.enterPressed = false;
	}
	
	public void draw(Graphics2D g2) {
		
////		//Thiết lập màu cho màn hình
//		g2.setColor(Color.BLACK);
////		//Thiết lập màn hình khi được khởi động
//		g2.fillRect(x, y, gp.titleSize , gp.titleSize);
		BufferedImage image = null;
		//System.out.println("Check : "+ check);
		switch (direction) {
		case "up": {
			
			//Kiểm tra check 
			if (check == 0) {
				//Hành động đang đứng thở
				if(spriteNum1 == 1) {
					image = up1;
				}
				else if (spriteNum1 == 2) {
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
			
			if (check == 0) {
				//Hành động đang đứng thở
				if(spriteNum1 == 1) {
					image = down1;
				}
				else if(spriteNum1 == 2) {
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
			
			if(check == 0) {
				//Hành động thở
				if(spriteNum1 == 1) {
					image = left1;
				}
				else if(spriteNum1 == 2) {
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
			
			if(check == 0) {
				//Hành động thở
				if(spriteNum1 == 1) {
					image = right1;
				}
				else if(spriteNum1 == 2) {
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
//		int x = screenX;
//		int y = screenY;
//		
//		if(screenX > worldX) {
//			x = worldX;
//		}
//		if(screenY > worldY) {
//			y = worldY;
//		}
//		int rightOffset = gp.screenWidth - screenX;
//		if(rightOffset > gp.worldWidth - worldX) {
//			x = gp.screenWidth - (gp.worldWidth - worldX);
//		}
//		int bottomOffset = gp.screenHeight - screenY;
//		if(bottomOffset > gp.worldHeight - worldY) {
//			y = gp.screenHeight - (gp.worldHeight - worldY);
//		}
//		System.out.println("Screen X = "+ screenX +", Screen Y = "+ screenY);
		//Màn hình trục x và y sẽ không thay thổi trong suốt trò chơi
		g2.drawImage(image, screenX, screenY,gp.titleSize,gp.titleSize, null);
		
	}
}
