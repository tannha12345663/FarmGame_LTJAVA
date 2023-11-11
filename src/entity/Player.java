package entity;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Axe;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;
import object.OBJ_Pickaxe;
import object.OBJ_Watering;
import title.TileManager;

public class Player extends Entity {
	
	KeyHandler keyH;
	int check = 0;
	TileManager tm;
	
	//Thiết lập camera di theo nhân vật người chơi với tọa độ riêng biệt
	public final int screenX;
	public final int screenY;
	
	public boolean daoDatCanceled = false;
	//Khai báo danh túi đồ
	public ArrayList<Entity> inventory = new ArrayList<Entity>();
	public final int maxInventorySize = 20;
	public int selectPlayer;
	
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
		//Attack Area -- phụ thuộc vào mỗi công cụ khác nhau
//		targetArea.width = 36;
//		targetArea.height = 36;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAnimationImage();
		setItems();
		
	}
	public void setDefaultValues() {
		//Tọa độ cho nhân vật
		worldX = gp.titleSize*10;
		worldY = gp.titleSize*13;
		speed = 4;
		direction ="down"; //Đặt hướng mặc định cho nhân vật
		
		
		//Trạng thái máu ban đầu của người chơi
		level = 1;
		maxLife = 6; //Mạng số tối đa
		life = maxLife; // Mạng sống hiện tại của người chơi
		exps = 0;
		nextLevel = 10;
		coins = 0;
		days = 1;
		currentCongCu = new OBJ_Pickaxe(gp); // Trang bị sử dụng hiện tại là cây cuốc
		
	}
	public void setItems() {
		
		inventory.add(currentCongCu);
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Watering(gp));
		inventory.add(new OBJ_HatGiong1(gp));
		inventory.add(new OBJ_HatGiong2(gp));
	}
	
	
	public void getPlayerImage() {
		
		if(selectPlayer == 1) {
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
		else if(selectPlayer == 2) {
			up1 = setup("/player_01/Player01_up1");
			up2 = setup("/player_01/Player01_up2");
			up3 = setup("/player_01/Player01_up3");
			up4 = setup("/player_01/Player01_up4");
			
			down1 = setup("/player_01/Player01_down1");
			down2 = setup("/player_01/Player01_down2");
			down3 = setup("/player_01/Player01_down3");
			down4 = setup("/player_01/Player01_down4");
			
			left1 = setup("/player_01/Player01_left1");
			left2 = setup("/player_01/Player01_left2");
			left3 = setup("/player_01/Player01_left3");
			left4 = setup("/player_01/Player01_left4");
			
			right1 = setup("/player_01/Player01_right1");
			right2 = setup("/player_01/Player01_right2");
			right3 = setup("/player_01/Player01_right3");
			right4 = setup("/player_01/Player01_right4");
		}
		
	}
	
	//HÀm thực hiên animation cho nhân vật
	public void getPlayerAnimationImage() {
		
		System.out.println("Vũ khí hiện tại: "+ currentCongCu.type);
		if(currentCongCu.type == type_pickaxe) {
			animaTionUp1 = setupAnimation("/playerAnimation/up1_DaoDat",gp.titleSize,gp.titleSize+ 20);
			animaTionUp2 = setupAnimation("/playerAnimation/up2_DaoDat",gp.titleSize,gp.titleSize+ 20);
			
			animaTionDown1 = setupAnimation("/playerAnimation/down1_DaoDat",gp.titleSize,gp.titleSize+ 20);
			animaTionDown2 = setupAnimation("/playerAnimation/down2_DaoDat",gp.titleSize,gp.titleSize+ 20);
			
			animaTionLeft1 = setupAnimation("/playerAnimation/left1_DaoDat",gp.titleSize + 25,gp.titleSize);
			animaTionLeft2 = setupAnimation("/playerAnimation/left2_DaoDat",gp.titleSize + 25,gp.titleSize);
			
			animaTionRight1 = setupAnimation("/playerAnimation/right1_DaoDat",gp.titleSize+ 25,gp.titleSize);
			animaTionRight2 = setupAnimation("/playerAnimation/right2_DaoDat",gp.titleSize+ 25,gp.titleSize);
		}
		if(currentCongCu.type == type_axe) {
			animaTionUp1 = setupAnimation("/playerAnimation/up2_ChatCay",gp.titleSize,gp.titleSize+ 20);
			animaTionUp2 = setupAnimation("/playerAnimation/up1_ChatCay",gp.titleSize,gp.titleSize+ 20);
			
			animaTionDown1 = setupAnimation("/playerAnimation/down1_ChatCay",gp.titleSize,gp.titleSize+ 20);
			animaTionDown2 = setupAnimation("/playerAnimation/down2_ChatCay",gp.titleSize,gp.titleSize+ 20);
			
			animaTionLeft1 = setupAnimation("/playerAnimation/left2_ChatCay",gp.titleSize + 40,gp.titleSize );
			animaTionLeft2 = setupAnimation("/playerAnimation/left1_ChatCay",gp.titleSize + 40,gp.titleSize );
			
			animaTionRight1 = setupAnimation("/playerAnimation/right2_ChatCay",gp.titleSize+ 40,gp.titleSize );
			animaTionRight2 = setupAnimation("/playerAnimation/right1_ChatCay",gp.titleSize+ 40,gp.titleSize);
		}
		
		
	}
	
	public void update () {
		int npcIndex;
		if(playerAnimation == true) {
			gp.ui.addMessage("Bạn vừa đào đất!");
			playerAnimation();
		}
		//Ở bước nãy sẽ chỉ nhận sự kiện khi người dùng nhấn phím
		else if( keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true|| keyH.rightPressed == true || keyH.enterPressed == true) {
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
				//worldX -= speed;	eeee
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
			System.out.println(npcIndex);
			interactNPC(npcIndex);
			
			//Kiểm tra va chạm với monster
			int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
			//Người chơi sẽ bị mất máu nếu đến gần quái vật
			contactMonster(monsterIndex);
			
//			System.out.println("x = "+ gp.player.worldX + ", y ="+gp.player.worldY);
			//Check event
			gp.eHandler.checkEvent();
			
			
			
			//Nếu vật thẩ xuyên được là false , người chơi có thể di chuyển
			if(collisionOn == false && keyH.enterPressed == false ) {
				switch (direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed;break;
				case "left":worldX -= speed; break;
				case "right":worldX += speed;break;
				}
			}
			
			if(keyH.enterPressed == true && daoDatCanceled == false) {
				gp.playSE(20);
				playerAnimation = true;
				spriteCounter = 0;
			}
			
			daoDatCanceled = false;
			gp.keyH.enterPressed = false;
			
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
			if(keyH.enterPressed == true) {
				npcIndex = gp.cChecker.checkEntity(this, gp.npc);
				System.out.println(npcIndex);
				interactNPC(npcIndex);
				keyH.enterPressed = false;
			}
			
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
		//Bằng cách này khi người chơi lại gần monster sẽ không phải tuột máu nhanh mà theo đó mỗi lần chạm sẽ bị mất máu
		if(invincible == true) {
			invincibleCounter ++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void playerAnimation() {
		
		targetArea = currentCongCu.targetArea;
		spriteCounter ++;
		if(spriteCounter <= 5 ) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
			
			//Lưu trữ vị trí hiện tại của người chơi khi thực hiện animation
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			//Lưu trữ nơi vị trí đang đứng
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//Thay đổi vị trí WorldX/Y của người chơi cho việc đào đất
			switch (direction){
			case "up": worldY -= targetArea.height; break;
			case "down": worldY += targetArea.height; break;
			case "left": worldX -= targetArea.width; break;
			case "right": worldX += targetArea.width; break;
			}
			
			// Chỗ đào đất sẽ trở thành vùng bị tác động
			solidArea.width = targetArea.width;
			solidArea.height = targetArea.height;
			
			//Kiểm tra va chạm với monster cùng với cập nhật worldX, worldY và solidArea
			//Dòng code này chỉ được mở khi có Monster
			// int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			
			//Đổi sang đất đã được đào
			
			
//			gp.cChecker.checkDig(this,111);
			//Kế đó sẽ làm event điều gì sẽ xảy ra khi người chơi va chạm vật này
			//digDaoDat(objIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter > 25) {
			spriteNum =1;
			spriteCounter = 0;
			playerAnimation = false;
		}
		
	}
	
	//Hàm này có ý nghĩa sẽ nhặt các món item trên map
	public void pickUpObject(int i) {
		
		//Nếu là 999 sẽ không có va chạm và ngược lại
		if(i != 999) {
			
			String text;
			//Kiểm tra túi người chơi có đầy không
			if(inventory.size() != maxInventorySize) {
				inventory.add(gp.obj[i]);
				gp.playSE(9);
				text = "Bạn đã nhặt "+ gp.obj[i].name + "!";
			}
			//Nếu túi đã đầy
			else {
				text = "Bạn không thể nhặt thêm!";
			}
			gp.ui.addMessage(text);
			gp.obj[i] = null;
			
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
		if(gp.keyH.enterPressed == true) {
			if(i != 999) {
				daoDatCanceled = true;
				gp.gameState = gp.dialogueState;
				//System.out.println(i);
				gp.npc[i].speak();
				
			}
//			else {
//				//Khi người chơi không gặp npc thì sẽ thực hiện đào đất
//				gp.playSE(20);
//				playerAnimation = true;
//			}
			
		}
		//gp.keyH.enterPressed = false;
	}
	public void contactMonster(int i) {
		if(i != 999) {
			
			if(invincible == false) {
				life -= 1;
				invincible = true;
			}
			
			
		}
	}
	public void digDaoDat(int i) {
		if(i!= 999) {
			System.out.println("Bạn đã đào đất ở vị trí này");
		}
		else {
			System.out.println("Bạn chưa đào đất được");
		}
	}
	
	public void checkLevelUp() {
		if(exps >= nextLevel) {
			level++;
			nextLevel = nextLevel+10;
			coins += 100;
			
			gp.playSE(21);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialouge = "Bạn đang ở level " + level +" now!\n"
								+ "Bạn thật tuyệt vời.";
		}
	}
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot();
		if(itemIndex < inventory.size()) {
			Entity selectItem = inventory.get(itemIndex);
			if(selectItem.type == type_axe || selectItem.type == type_pickaxe || selectItem.type == type_watering 
					|| selectItem.type == type_plant1
					|| selectItem.type == type_plant2) {
				currentCongCu = selectItem;
				getPlayerAnimationImage();
			}
			if(selectItem.type == type_consumable) {
				selectItem.use(selectItem);
				inventory.remove(itemIndex);
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
////		//Thiết lập màu cho màn hình
//		g2.setColor(Color.BLACK);
////		//Thiết lập màn hình khi được khởi động
//		g2.fillRect(x, y, gp.titleSize , gp.titleSize);
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		//System.out.println("Check : "+ check);
		switch (direction) {
		case "up": {
			
			if(playerAnimation == false) {
				//Kiểm tra check 
				if (check == 0) {
					//Hành động đang đứng thở
					if(spriteNum1 == 1) {image = up1;}
					if (spriteNum1 == 2) {image = up2;}
				}
				else {
					//Hành động bước đi
					if(spriteNum == 1) {image = up3;}
					if (spriteNum == 2) {image = up4;}
				}
			}
			if(playerAnimation == true) {
				tempScreenY = screenY - gp.titleSize/2;
				if(spriteNum == 1) {image = animaTionUp1;}
				if (spriteNum == 2) {image = animaTionUp2;}
			}
			break;
		}
		case "down": {
			if(playerAnimation == false) {
				if (check == 0) {
					//Hành động đang đứng thở
					if(spriteNum1 == 1) {image = down1;}
					if(spriteNum1 == 2) {image = down2;}
				}
				else {
					//Hành động bước đi
					if(spriteNum == 1) {image = down3;}
					if(spriteNum == 2) {image = down4;}
				}
			}
			if(playerAnimation == true) {
				if(spriteNum == 1) {image = animaTionDown1;}
				if (spriteNum == 2) {image = animaTionDown2;}
			}
			
			break;
		}
		case "left": {
			if(playerAnimation == false) {
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
			}
			if(playerAnimation == true) {
				tempScreenX = screenX - gp.titleSize/2;
				if(spriteNum == 1) {image = animaTionLeft1;}
				if (spriteNum == 2) {image = animaTionLeft2;}
			}
			break;
		}
		case "right": {
			if(playerAnimation == false) {
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
			}
			if(playerAnimation == true) {
				if(spriteNum == 1) {image = animaTionRight1;}
				if (spriteNum == 2) {image = animaTionRight2;}
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
		if(invincible ==true) {
			//Làm cho nhân vật chớp hình khi va chạm với monster
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		//Reset lại ban đầu khi đã hết 
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		//Debug
//		g2.setFont(new Font("Arial",Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible: "+ invincibleCounter, 10, 400);
		// DEBUG
		// AttackArea -- hiển thị ô vuông khi đào đất
		tempScreenX = screenX + solidArea.x;
		tempScreenY = screenY + solidArea.y;		
		switch(direction) {
			case "up": tempScreenY = screenY - targetArea.height; break;
			case "down": tempScreenY = screenY + gp.titleSize; break; 
			case "left": tempScreenX = screenX - targetArea.width; break;
			case "right": tempScreenX = screenX + gp.titleSize; break;
		}				
		g2.setColor(Color.red);g2.setStroke(new BasicStroke(1));
		g2.drawRect(tempScreenX, tempScreenY, targetArea.width, targetArea.height);
	}
}
