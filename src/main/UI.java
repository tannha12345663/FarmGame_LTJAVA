package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Entity;
import object.OBJ_BoxSmall;
import object.OBJ_Emotej;
import object.OBJ_Heart;
import object.OBJ_BoxCoin;
import object.OBJ_Coin;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage heart_full, heart_half, heart_blank, boxSmall, emoteJ, boxCoins, coins;
//	BufferedImage keyImage;
	//Khai báo nội dung tin nhắn
	public boolean messageOn = false;
	//public String message ="";
	//Khai báo thời gian hiển thị message
	//int messageCounter = 0;
	ArrayList<String> message = new ArrayList<String>();
	ArrayList<Integer> messageCounter = new ArrayList<Integer>();
	
	//Khai báo trò chơi đã kết thúc hay chưa
	public boolean gameFinished = false;
	// Khai báo đoạn thoại tin nhắn
	public String currentDialouge = "";
	//Khai báo biến chọn menu ở màn hình screen
	public int commandNum = 0;
	//Khai báo biến màn hình thứ hai sau khi new game
	public int titleScreenState = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	
	// Chú thích : 
	//với 0: là màn hình khởi đầu 
	//với 1: là màn hình thứ 2 		
	//Delay 
	
//	//Khai báo hiển thị time
//	double playTime;
//	//Format lại thời gian hiển thị
//	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Airal", Font.PLAIN,40);
		arial_80B = new Font("Airal", Font.BOLD,50);
//		OBJ_Key key = new OBJ_Key(gp);
//		keyImage = key.image;
		
		//Tạo HUD đối tượng
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image1;
		heart_blank = heart.image2;
		
		Entity box = new OBJ_BoxSmall(gp);
		boxSmall = box.image;
		
		Entity emotej = new OBJ_Emotej(gp);
		emoteJ = emotej.image; 
		
		Entity boxCoin = new OBJ_BoxCoin(gp);
		boxCoins = boxCoin.image;
		
		Entity coin = new OBJ_Coin(gp);
		coins = coin.image;
	}
	
	//Hiển thị nội dung tin nhắn
	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0);
		
	}
	
	//Vẽ màn hình hiển thị số lượng key đã nhặt
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//System.out.println(gp.gameState);
		
		//Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//Player State
		if(gp.gameState == gp.playState) {
			//Cho trò chơi tiếp tục hoạt động
			drawPlayerLife();
			drawMessage();
		}
		//Pause State
		if(gp.gameState == gp.pauseState) {
			// Tạm ngưng trò chơi
			drawPlayerLife();
			drawPauseScreen();
		}
		//Dialouge State
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		//Character state
		
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory();
		}
		
		//Trò chơi kết thúc
//		if(gameFinished == true) {
			
		//Tutorial khởi đầu 
			//Set font chữ
//			g2.setFont(arial_40);
//			//Set màu 
//			g2.setColor(Color.white);
//			
//			String text;
//			int textLength;
//			int x;
//			int y;
//			
//			text = "Bạn đã tìm thấy kho báu";
//			//Trả về độ dài ký tự
//			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			
//			//Xuống dòng và căn giữa đoạn message
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 - (gp.titleSize*3);
//			g2.drawString(text, x, y);
//			
//			//Dòng chúc mừng
//			//Set font chữ
//			g2.setFont(arial_80B);
//			//Set màu 
//			g2.setColor(Color.yellow);
//			text = "Chúc mừng bạn";
//			//Trả về độ dài ký tự
//			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			//Xuống dòng và căn giữa đoạn message
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 + (gp.titleSize*2);
//			g2.drawString(text, x, y);
//			
//			//Dòng chúc mừng
//			//Set font chữ
//			g2.setFont(arial_80B);
//			//Set màu 
//			g2.setColor(Color.yellow);
//			text = "đã hoàn thành nhiệm vụ!";
//			//Trả về độ dài ký tự
//			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			//Xuống dòng và căn giữa đoạn message
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 + (gp.titleSize*3);
//			g2.drawString(text, x, y);
//			
//			//Dòng hiển thị time
//			//Set font chữ
//			g2.setFont(arial_40);
//			//Set màu 
//			g2.setColor(Color.white);
//			text = "Thời gian: "+ dFormat.format(playTime)+ "!";
//			//Trả về độ dài ký tự
//			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//			//Xuống dòng và căn giữa đoạn message
//			x = gp.screenWidth/2 - textLength/2;
//			y = gp.screenHeight/2 + (gp.titleSize*4);
//			g2.drawString(text, x, y);
//			
//			
//			//Thực hiện dừng trờ chơi
//			gp.gameThread = null;
//			
//		}
//		else {
//			//Set font chữ
//			g2.setFont(arial_40);
//			//Set màu 
//			g2.setColor(Color.white);
//			//Set drawimage key trên màn hình
//			//g2.drawImage(keyImage, gp.titleSize/2, gp.titleSize/2,gp.titleSize, gp.titleSize, null);
//			//Set văn bàn 
//			g2.drawString("Key = " + gp.player.hasKey, 24, 65);
//			//Time
//			playTime +=(double)1/60;
//			g2.drawString("Thời gian: " + dFormat.format(playTime), gp.titleSize*10, 65);
//			
//			
//			//Message
//			if(messageOn == true) {
//				
//				g2.setFont(g2.getFont().deriveFont(30F));
//				g2.drawString(message, gp.titleSize/2, gp.titleSize*5);
//				
//				messageCounter ++;
//				Tuyển
//				//Nếu trong vòng 120 khung hình = 2s sẽ tự động biến mất message
//				if(messageCounter > 120) {
//					messageCounter = 0;
//					messageOn = false;
//				}
//			}
//		}
		
	}
	//Vẽ nội dung tin nhắn
	public void drawMessage() {
		
		int messageX = gp.titleSize;
		int messageY = gp.titleSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		for(int i = 0;i < message.size();i++) {
			
			if(message.get(i)!= null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) +1; //MessageCounter ++
				messageCounter.set(i, counter); // set the counter to the array
				messageY += 50;
				
				if(messageCounter.get(i)>180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	//Vẽ thanh máu cho nhân vật
	public void drawPlayerLife() {
		
		//Test số máu giảm đối với người chơi
		//gp.player.life = 3;
		
		int x = gp.titleSize / 2;
		int y = gp.titleSize / 2;
		int i = 0;
		
		g2.drawImage(boxSmall, x -20,y -20,null); // Ô box thông tin người chơi
		g2.drawImage(emoteJ, x ,y+ 10,null); // Cảm xúc của nhân vật
		g2.drawImage(boxCoins, x - 15 ,y + 105, null); //Ô chứa coin
		
		g2.drawImage(coins, x - 5 ,y + 115, null); //Số tiền hiện tại
		String soCoin = String.valueOf(gp.player.coins);
		g2.setColor(Color.RED);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
		g2.drawString(soCoin, x + 30 ,y + 143);
		
		//Máu ban đầu sẽ rỗng tối đa của nhân vật
		while(i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x + 80 ,y + 33,null);
			i++;
			x += gp.titleSize + 4;
		}
		//Reset
		x = gp.titleSize / 2;
		y = gp.titleSize / 2;
		i = 0;
		
		//Vẽ số máu hiện tại của người chơi
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x + 80 ,y + 33,null);
			i++;
			if( i < gp.player.life) {
				g2.drawImage(heart_full, x + 80 ,y + 33,null);
			}
			i++;
			x += gp.titleSize + 4;
		}
		//Vẽ hiển thị bộ đếm thời gian
		String time = " "+ gp.days+" ngày "+gp.hours + " giờ "+ gp.minutes +" Phút";
		g2.setColor(Color.BLUE);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		g2.drawString(time, x + 350 ,y + 10);
	}
	
	//Vẽ nội dung màn hình mở đầu
	public void drawTitleScreen() {
		
//		System.out.println(titleScreenState);
		//Kiểm tra màn hình trạng thái tiêu đề
		System.out.println("Man hinh hien tai " + titleScreenState);
		if(titleScreenState == 0) {
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
			//g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			String text = "Nông trại vui vẻ NNT";
			int x = getXforCenteredText(text);
			int y = gp.titleSize * 3;
			
			//Shadow đổ bóng
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			
			g2.setColor(Color.PINK);
			g2.drawString(text, x, y);
			
			//Hiển thị nhân vật người chơi ra giữa màn hình
//			x = gp.screenWidth / 2 - (gp.titleSize*2)/4;
//			y += gp.titleSize*2;
//			g2.drawImage(gp.player.down2, x, y, gp.titleSize ,gp.titleSize,null);
			
			//MENU
			g2.setColor(new Color(205,92,92));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			
			text = "CHƠI MỚI";
			x= getXforCenteredText(text);
			y += gp.titleSize*2.5 ;
			
            
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(new Color(205,92,92));
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.setColor(Color.black);
				g2.drawString(">", x - gp.titleSize/2, y);
			}
			
			text = "CHƠI TIẾP";
			x = getXforCenteredText(text);
			y += gp.titleSize ;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(new Color(205,92,92));
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.setColor(Color.black);
				g2.drawString(">", x - gp.titleSize/2, y);
			}
			
			text = "THOÁT";
			x = getXforCenteredText(text);
			y += gp.titleSize ;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(new Color(205,92,92));
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.setColor(Color.black);
				g2.drawString(">", x - gp.titleSize/2, y);
			}
		}else if(titleScreenState == 1) {
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,18));
			
			//Cửa sổ hiển thị
			int x = gp.titleSize*2 - 50;
			int y = gp.titleSize/2;
			int width = gp.screenWidth - (gp.titleSize * 3);
			int height = gp.titleSize * 5;
			drawSubWindow(x,y, width + 15, height);
			
			String text = "_ Một buổi sáng tươi đẹp, ánh nắng mặt trời len lỏi qua những tán cây \n xanh rờn rợn trên cánh đồng. Trên chiếc xe ngựa cũ kỹ, người nông \n dân đang dẫn lữ hành trên con đường đá lát sỏi. \n _ Anh ta đã nghe nhiều câu chuyện về hòn đảo xa xôi nằm giữa biển, \n nơi mà đất đai màu mỡ và cơ hội đang chờ đợi những ai dám mạo\n hiểm.";
			for(String line : text.split("\n")) {
				g2.drawString(line, x + 10, y+30);
				y += 40;
				
			}
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			text = "Nhấn E để tiếp tục";
			g2.drawString(text, x *10 - 30 , y + 30);
			
			text = "Nhấn Q để quay lại";
			g2.drawString(text, x - 10, y *2 + 35);
		}else if (titleScreenState == 2) {
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,18));
			
			//Cửa sổ hiển thị
			int x = gp.titleSize*2 - 50;
			int y = gp.titleSize/2;
			int width = gp.screenWidth - (gp.titleSize * 3);
			int height = gp.titleSize * 5;
			drawSubWindow(x,y, width + 15, height);
			
			String text = "_ Cuộc sống trên mảnh đất quê hương của anh ta đã trở nên khó khăn \n hơn qua từng ngày, với đất đai trở nên bằng phẳng và sản lượng nông \n nghiệp giảm sút. Nỗi hy vọng vào một tương lai tươi sáng đã dẫn dắt \n anh ta đến hòn đảo này, nơi mà lời đồn về cơ hội và cuộc sống mới đã \n lan tỏa khắp nơi.";
			for(String line : text.split("\n")) {
				g2.drawString(line, x + 10, y+30);
				y += 40;
				
			}
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			text = "Nhấn E để tiếp tục";
			g2.drawString(text, x *10 - 30 , y + 70);
			
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			
			text = "Nhấn Q để quay lại";
			g2.drawString(text, x - 10, y *2 + 115);
			
		}
		else if(titleScreenState == 3) {
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,18));
			
			//Cửa sổ hiển thị
			int x = gp.titleSize*2 - 50;
			int y = gp.titleSize/2;
			int width = gp.screenWidth - (gp.titleSize * 3);
			int height = gp.titleSize * 5;
			drawSubWindow(x,y, width + 15, height);
			
			String text = "_ Khi người nông dân đến gần bờ biển, anh ta không thể không ngạc \n nhiên bởi vẻ đẹp hoang sơ của hòn đảo. Biển xanh biếc lấp lánh, \n những dãy núi đá nguyên sơ nâng đôi tay lên trời, và bãi cát trải dài xa \n xôi. Người dân địa phương chào đón anh ta với sự nhiệt tình, và cuộc \n hành trình mới bắt đầu từ đây, khi anh ta quyết tâm xây dựng cuộc \n sống mới trên hòn đảo đầy triển vọng này.";
			for(String line : text.split("\n")) {
				g2.drawString(line, x + 10, y+30);
				y += 40;
				
			}
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			text = "Nhấn E để tiếp tục";
			g2.drawString(text, x *10 - 30 , y + 30);
			
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			
			text = "Nhấn Q để quay lại";
			g2.drawString(text, x - 10, y *2 + 35);
		}
		else if(titleScreenState == 4) {
			String text = "Chọn nhân vật";
			int x = getXforCenteredText(text);
			int y = gp.titleSize * 3;
			
			//Shadow đổ bóng
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			
			g2.setColor(Color.PINK);
			g2.drawString(text, x, y);
			
			//Hiển thị nhân vật 0
			x = gp.screenWidth / 2 - (gp.titleSize*2)/4 - 100;
			y += gp.titleSize*2;
			g2.drawImage(gp.player.setup("/player/Basic-Charakter-down_01"), x, y, gp.titleSize ,gp.titleSize,null);
			if(commandNum == 0) {
				g2.setColor(Color.black);
				g2.drawString(">", x - gp.titleSize/2 - 20, y + 40);
			}
			g2.setColor(Color.PINK);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
			g2.drawString("Trẻ trung", x - gp.titleSize/2 - 5, y + 80);
			
			//Hiển thị nhân vật 2
			x += 200;
			//gp.player.selectPlayer = 1;
			g2.drawImage(gp.player.setup("/player_01/Player01_down1"), x, y, gp.titleSize ,gp.titleSize,null);
			if(commandNum == 1) {
				g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
				g2.setColor(Color.black);
				g2.drawString(">", x - gp.titleSize/2 - 20, y + 40);
			}
			g2.setColor(Color.PINK);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
			g2.drawString("Trung niên", x - 40 , y + 80);
			
			 x = gp.titleSize*2 - 50;
			 y = (gp.titleSize/2)*20;
			
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			text = "Nhấn E để chơi";
			g2.drawString(text, x *10 + 60 , y + 83);
			
			g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			
			text = "Nhấn Q để quay lại";
			g2.drawString(text, x - 10, y + 83);
		}
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		String text = "TẠM NGỪNG";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2 - (gp.titleSize*2);
		gp.stopMusic();
		g2.drawString(text, x, y);
	}
	//Hàm hiển thị lời thoại ở màn hình
	public void drawDialogueScreen() {
		
		//Cửa sổ hiển thị
		int x = gp.titleSize*2;
		int y = gp.titleSize/2;
		int width = gp.screenWidth - (gp.titleSize * 4);
		int height = gp.titleSize * 4;
		drawSubWindow(x,y, width, height);
		
		//Set font chữ
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
		x += gp.titleSize;
		y += gp.titleSize;
		
		for(String line : currentDialouge.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
		
	}
	public void drawCharacterScreen() {
		final int frameX = 10;
		final int frameY = gp.titleSize;
		final int frameWidth = gp.titleSize *6;
		final int frameHeight = 320;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//Text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(30F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.titleSize;
		final int lineHeight = 35;
		
		//Names
		g2.drawString("Thông tin nhân vật", textX, textY);
		textY += lineHeight;
		textY += lineHeight;
		g2.drawString("Day", textX, textY);
		textY += lineHeight;
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight;
		
		//Set Value
		int tailX = (frameX + frameWidth) - 60;
		//Reset TextY
		textY = frameY + gp.titleSize;
		String value;
		
		textY += lineHeight;
		textY += lineHeight;
		value = String.valueOf(gp.days);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/"+ gp.player.maxLife);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exps);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevel);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coins);
		textX = getXforAllignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		
		//Nếu muốn hiển thị hình ảnh cùng Character State
		//g2.drawImage(gp.player..., tailX - gp.titleSize, textY);
		//Thay ... bằng tên hình ảnh rồi .down1 vd : gp.plater.currentWeapon.down1
	}
	public void drawInventory() {
		
		int frameX = gp.titleSize*9;
		int frameY = gp.titleSize;
		int frameWidth = gp.titleSize*6;
		int frameHeght = gp.titleSize*5;	
		drawSubWindow(frameX, frameY, frameWidth, frameHeght - 10);
		
		//Tạo từng khe slot cho kho
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.titleSize+3;
		
		
		//DRAW PLAYER'S ITEMS -- Vẽ ra túi đồ 
		for(int i =0; i<gp.player.inventory.size();i++) {
//			System.out.println("Vũ khí đang có trong kho "+ gp.player.inventory.get(i));
//			System.out.println("Vũ khí đang dùng "+ gp.player.currentCongCu);
			//Equip Cursor -- trang bị công cụ khác để sử dụng
			if(gp.player.inventory.get(i)== gp.player.currentCongCu) {
				g2.setColor(new Color(253,245,230));g2.setStroke(new BasicStroke(1));
				g2.fillRoundRect(slotX, slotY, gp.titleSize,gp.titleSize,10,10);
				
			}
			
			g2.drawImage(gp.player.inventory.get(i).down1, slotX,slotY, null);
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		
		// Cursor -- con trỏ đến từng vị trí trong ô
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.titleSize;
		int cursorHeight = gp.titleSize;
		//Draw cursor -- vẽ con trỏ khi di chuyển
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);
		
		// Description frame
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeght - 5;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.titleSize *6;
		
		
		//Draw Description Text
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.titleSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		if(itemIndex < gp.player.inventory.size()) {
			drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
			if(gp.player.inventory.get(itemIndex).type == gp.player.type_watering) {
				if(gp.player.inventory.get(itemIndex).valueConsumable == 0) {
					g2.setColor(Color.YELLOW);
					g2.drawString("Sức chứa: "+ gp.player.inventory.get(itemIndex).valueConsumable +"/"+gp.player.inventory.get(itemIndex).maxValueConsum, textX, textY);
					textY += 32;
				}else {
					g2.setColor(Color.white);
					g2.drawString("Sức chứa: "+ gp.player.inventory.get(itemIndex).valueConsumable +"/"+gp.player.inventory.get(itemIndex).maxValueConsum, textX, textY);
					textY += 32;
				}
			}
			
		}		
	}
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,100);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35); // Vẽ khung hình chữ nhật 
		
		//Set khung viền cho hộp thoại
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
		
		
	}
	//Hàm canh chữ ra giữa màn hình
	public int getXforCenteredText(String text) {
		//Lấy thông tin độ dài của ký tự
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	//Hàm canh chữ sang phải màn hình
	public int getXforAllignToRightText(String text, int tailX) {
		
		//Lấy thông tin độ dài của ký tự
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
