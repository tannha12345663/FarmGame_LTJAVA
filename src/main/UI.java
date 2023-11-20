package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints.Key;
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
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	public Entity npc;
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
			drawDialogueScreen();
		}
		//Character state
		
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		
		//Option state
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		//Trade State -- thực hiện giao dịch với NPC merchant
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		//Option state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
	
		
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
		//System.out.println("Man hinh hien tai " + titleScreenState);
		if(titleScreenState == -1) {
			int x = 150;
			int y = gp.titleSize * 3;
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
			 // Vẽ ô nhập liệu cho tên đăng nhập
	        g2.setColor(Color.black);
	        g2.drawString("Tên đăng nhập: ", x, y);
	        
	        g2.setColor(Color.white);
	        g2.fillRect(x * 3 - 70, y - 25, 150, 30);
	        g2.setColor(Color.black);
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
	        g2.drawString(gp.usernameInput ,x * 3 - 65, y);
	        if(commandNum == 0) {
		        g2.setColor(Color.black);
				g2.drawString("<", (int)(x * 4 - 60) - 5, y - 5);
	        }
//	        g2.drawRect(x * 3, y - 25, 150, 30);
	        // ...
	        y += gp.titleSize ;
	        // Vẽ ô nhập liệu cho mật khẩu
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
	        g2.setColor(Color.black);
	        g2.drawString("Mật khẩu: " , x + 80, y);
	        g2.setColor(Color.white);
	        g2.fillRect(x * 3 - 70, y - 25, 150, 30);
	        g2.setColor(Color.black);
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
	        String maskedPass = "*".repeat(gp.passwordInput.length());
	        g2.drawString(maskedPass ,(int)(x * 3 - 65), y);
	        
	        if(commandNum == 1) {
		        g2.setColor(Color.black);
				g2.drawString("<", (int)(x * 4 - 60) - 5, y - 5);
	        }
	        // Vẽ button Đăng nhập
	        
	        y+= gp.titleSize;
	        g2.setColor(Color.BLUE);
	        g2.fillRoundRect((int)(x * 2) , y , 150, 40, 30, 30);
	        g2.setColor(Color.white);
	        g2.drawString("Đăng nhập" ,(int)(x * 2.2) - 5 , y + 27 );
	        
	        if(commandNum == 2) {
	        	g2.setColor(Color.black);
				g2.drawString("<", (int) (x * 3)+ 5, y+ 30 );
	        }
	        
	        y+= gp.titleSize;
	        g2.setColor(Color.BLUE);
	        g2.fillRoundRect((int)(x * 2) , y , 150, 40, 30, 30);
	        g2.setColor(Color.white);
	        g2.drawString("Đăng ký" ,(int)(x * 2.2) + 5 , y + 27 );
	        
	        if(commandNum == 3) {
	        	g2.setColor(Color.black);
				g2.drawString("<", (int) (x * 3)+ 5, y+ 30 );
	        }
	        
	        if(commandNum == 4) {
	        	//Cửa sổ hiển thị
				int width = gp.screenWidth - (gp.titleSize * 4);
				int height = gp.titleSize * 2;
				g2.setColor(Color.CYAN);
				g2.fillRoundRect(90 ,gp.titleSize, width, height,30,30);
				g2.setColor(Color.black);
				g2.drawRoundRect(90, gp.titleSize, width , height , 30, 30);
				
				if(gp.checkAccountLogin == true) {
					g2.setColor(Color.black);
					String text = "Đăng nhập thành công!";
					x = getXforCenteredText(text);
					g2.drawString(text, x, y - 210);
					text = "Vui lòng quay lại màn hình chính và chơi tiếp.";
					x = getXforCenteredText(text);
					for(String line : text.split("\n")) {
						g2.drawString(line, x, y - 180);
						y += 40;
						
					}
				}else if(gp.checkAccountLogin == false) {
					g2.setColor(Color.black);
					String text = "Đăng nhập thất bại!";
					x = getXforCenteredText(text);
					g2.drawString(text, x, y - 210);
					text = "Vui lòng kiểm tra lại thông tin Username và Password.";
					x = getXforCenteredText(text);
					for(String line : text.split("\n")) {
						g2.drawString(line, x, y - 180);
						y += 40;
						
					}
				}
				
	        }
	        if(commandNum == 5) {
	        	//Cửa sổ hiển thị
				int width = gp.screenWidth - (gp.titleSize * 4);
				int height = gp.titleSize * 2;
				g2.setColor(Color.CYAN);
				g2.fillRoundRect(90 ,gp.titleSize, width, height,30,30);
				g2.setColor(Color.black);
				g2.drawRoundRect(90, gp.titleSize, width , height , 30, 30);
				
				if(gp.checkAccountRegister == true) {
					g2.setColor(Color.black);
					String text = "Đăng ký thành công!";
					x = getXforCenteredText(text);
					g2.drawString(text, x, y - 210);
					text = "Vui lòng quay lại màn hình chính và bắt đầu chơi.";
					x = getXforCenteredText(text);
					for(String line : text.split("\n")) {
						g2.drawString(line, x, y - 180);
						y += 40;
						
					}
				}else if(gp.checkAccountRegister == false) {
					g2.setColor(Color.black);
					String text = "Đăng ký thất bại!";
					x = getXforCenteredText(text);
					g2.drawString(text, x, y - 210);
					text = "Không thể bỏ trống hoặc tên Username đã tồn tại.";
					x = getXforCenteredText(text);
					for(String line : text.split("\n")) {
						g2.drawString(line, x, y - 180);
						y += 40;
					}
				}
	        }
	        g2.setColor(new Color(33, 92, 138));
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
	        String text = "Nhấn Q để quay lại";
			g2.drawString(text, x - 110, y*2 - 13);
	        //g2.fillRect((int)(x * 2.5) , y , 150, 30);
	        
		}
		
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
			
			if(gp.checkAccountLogin == true) {
				text = "Xin chào " + gp.usernameInput;
				x= getXforCenteredText(text);
				y += gp.titleSize*2.5 ;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+3);
			}
			
			if(gp.checkAccountLogin == true) {
				text = "Đăng xuất";
			}else {
				text = "Đăng nhập";
			}
			
			
			x= getXforCenteredText(text);
			y += gp.titleSize + 20 ;
			
            
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+3);
			g2.setColor(new Color(205,92,92));
			g2.drawString(text, x, y);
			if(commandNum == -1) {
				g2.setColor(Color.black);
				g2.drawString(">", x - gp.titleSize/2, y);
			}
			
			text = "CHƠI MỚI";
			x= getXforCenteredText(text);
			y += gp.titleSize + 10;
			
            
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
			
			if(gp.checkFileNotContinue == true && gp.ui.commandNum == 3) {
				//Cửa sổ hiển thị
				int width = gp.screenWidth - (gp.titleSize * 4);
				int height = gp.titleSize * 2;
				g2.setColor(Color.CYAN);
				g2.fillRoundRect(90 ,gp.titleSize, width, height,30,30);
				g2.setColor(Color.black);
				g2.drawRoundRect(90, gp.titleSize, width , height , 30, 30);
				
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
				g2.setColor(Color.black);
				String text1 = "Thông báo!";
				x = getXforCenteredText(text1);
				y = gp.titleSize + 30;
				g2.drawString(text1, x, y );
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
				text = "Chưa có dữ liệu người chơi, vui lòng chơi mới.";
				x = getXforCenteredText(text);
				for(String line : text.split("\n")) {
					g2.drawString(line, x, y + 40);
					y += 40;
				}
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
	public void drawInventory(Entity entity, boolean cursor) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeght = 0;	
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			frameX = gp.titleSize*9;
			frameY = gp.titleSize;
			frameWidth = gp.titleSize*6;
			frameHeght = gp.titleSize*5;	
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.titleSize*2;
			frameY = gp.titleSize;
			frameWidth = gp.titleSize*6;
			frameHeght = gp.titleSize*5;	
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		//Frame
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeght - 10);
		
		//Tạo từng khe slot cho kho
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.titleSize+3;
		
		
		//DRAW PLAYER'S ITEMS -- Vẽ ra túi đồ 
		for(int i =0; i<entity.inventory.size();i++) {
//			System.out.println("Vũ khí đang có trong kho "+ gp.player.inventory.get(i));
//			System.out.println("Vũ khí đang dùng "+ gp.player.currentCongCu);
			//Equip Cursor -- trang bị công cụ khác để sử dụng
			if(entity.inventory.get(i)== entity.currentCongCu) {
				g2.setColor(new Color(253,245,230));g2.setStroke(new BasicStroke(1));
				g2.fillRoundRect(slotX, slotY, gp.titleSize,gp.titleSize,10,10);
				
			}
			//System.out.println("Vẽ túi đồ thứ "+ i);
			g2.drawImage(entity.inventory.get(i).down1, slotX,slotY, null);
			
			// Display Amount
			if( entity == gp.player && entity.inventory.get(i).amount > 1) {
				
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;
				
				String s = ""+ entity.inventory.get(i).amount;
				amountX = getXforAllignToRightText(s, slotX + 44);
				amountY = slotY+gp.titleSize;
				
				//Shadow 
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				
				//Number
				g2.setColor(Color.white);
				g2.drawString(s, amountX - 3, amountY - 3);
				
			}
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		
		// Cursor -- con trỏ đến từng vị trí trong ô
		if(cursor == true) {
			int cursorX = slotXstart + (slotSize * slotCol); // Tuyệt đối không được đổi slotCol thành playerCol
			int cursorY = slotYstart + (slotSize * slotRow); // Tuyệt đối không được đổi slotRow thành playerRơ
			int cursorWidth = gp.titleSize;
			int cursorHeight = gp.titleSize;
			//Draw cursor -- vẽ con trỏ khi di chuyển
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);
			
			// Description frame
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeght - 12;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.titleSize *4;
			
			
			//Draw Description Text
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.titleSize;
			g2.setFont(g2.getFont().deriveFont(28F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			
			if(itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
				for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 32;
				}
				if(entity.inventory.get(itemIndex).type == entity.type_watering) {
					if(entity.inventory.get(itemIndex).valueConsumable == 0) {
						g2.setColor(Color.YELLOW);
						g2.drawString("Sức chứa: "+ entity.inventory.get(itemIndex).valueConsumable +"/"+entity.inventory.get(itemIndex).maxValueConsum, textX, textY);
						textY += 32;
					}else {
						g2.setColor(Color.white);
						g2.drawString("Sức chứa: "+ entity.inventory.get(itemIndex).valueConsumable +"/"+entity.inventory.get(itemIndex).maxValueConsum, textX, textY);
						textY += 32;
					}
				}
				
			}		
		}
		
		
	}
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80f));
		
		text = "Bạn đã ngất!";
		//Shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.titleSize*4;
		g2.drawString(text, x, y);
		
		//Main
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);
		
		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Nghỉ ngơi";
		x = getXforCenteredText(text);
		y += gp.titleSize * 4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 40, y);
		}
		
		//Back to the tile screen
		text = "Thoát";
		x = getXforCenteredText(text);
		y += 80;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
		
	}
	public void drawOptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		//Sub window
		int frameX = gp.titleSize*4;
		int frameY = gp.titleSize;
		int frameWidth = gp.titleSize*8;
		int frameHeight = gp.titleSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch (subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: options_fullScreenNotification(frameX, frameY); break;
		case 2: options_control(frameX, frameY); break;
		case 3: options_saveGame(frameX, frameY); break;
		case 4: options_endGameConfirmation(frameX, frameY); break;
		}
		gp.keyH.enterPressed = false;
	}
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//TILE
		String text = "Cài đặt";
		textX = getXforCenteredText(text);
		textY = frameY + gp.titleSize;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(25F));
		//Full Screen on/off
		textX = frameX + gp.titleSize - 5;
		textY += gp.titleSize*2 - 10;
		g2.drawString("Toàn màn hình", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				if(gp.fullScreenOn == false) {
					gp.fullScreenOn = true;
				}
				else if(gp.fullScreenOn == true) {
					gp.fullScreenOn = false;
				}
				subState = 1;
			}
		}
		
		//Music 
		textY += gp.titleSize;
		g2.drawString("Nhạc nền", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		
		//SE
		textY += gp.titleSize;
		g2.drawString("Hiệu ứng", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		
		//Control
		textY += gp.titleSize;
		g2.drawString("Điều khiển", textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		//Save game
		textY += gp.titleSize;
		g2.drawString("Lưu game", textX, textY);
		if(commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				String file = "";
				if(gp.checkAccountLogin == true) {
					file = gp.usernameInput + "_" + gp.passwordInput + ".dat";
				}
				else {
					file = "save.dat";
				}
				gp.saveLoad.save(file);
				subState = 3;
				commandNum = 0;
			}
		}		
		
		
		//End game
		textY += gp.titleSize;
		g2.drawString("Thoát game", textX, textY);
		if(commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 4;
				commandNum = 0;
			}
		}
		
		//Back
		textY += (int)gp.titleSize *1.8;
		g2.drawString("Quay lại", textX, textY);
		if(commandNum == 6) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}
		
		//Full screen check box
		textX = frameX + (int)(gp.titleSize*4.8);
		textY = frameY + gp.titleSize*2 + 16;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if(gp.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		//Music volume
		textY += gp.titleSize;
		g2.drawRect(textX, textY, 120,24); // 120/5 = 24
		int volumWidth = 24* gp.music.volumeScale;
		g2.fillRect(textX, textY, volumWidth, 24);
		
		
		
		
		//SE volume
		textY += gp.titleSize;
		g2.drawRect(textX, textY, 120,24);
		volumWidth = 24* gp.se.volumeScale;
		g2.fillRect(textX, textY, volumWidth, 24);
		
		gp.config.saveConfig();
		
	}
	
	public void options_saveGame(int frameX, int frameY) {
		
		int textX = frameX + gp.titleSize;
		int textY = frameY + gp.titleSize * 3;
		
		String text = "Thông báo";
		textX = getXforCenteredText(text);
		textY = frameY + gp.titleSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.titleSize - 5;
		textY += gp.titleSize*2 - 10;
		currentDialouge = "Bạn đã lưu game thành \n công. Dữ liệu của \n bạn đã được lưu lại.";
		
		for(String line: currentDialouge.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		g2.setFont(g2.getFont().deriveFont(25F));
		// Back 
		textY = frameY + gp.titleSize*9;
		g2.drawString("Quay lại", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
		
	}
	
	public void options_fullScreenNotification(int frameX, int frameY) {
		
		int textX = frameX + gp.titleSize;
		int textY = frameY + gp.titleSize * 3;
		
		String text = "Cảnh báo";
		textX = getXforCenteredText(text);
		textY = frameY + gp.titleSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.titleSize - 5;
		textY += gp.titleSize*2 - 10;
		currentDialouge = "Thay đổi này yêu \n cầu khởi động lại \n game.";
		
		for(String line: currentDialouge.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		g2.setFont(g2.getFont().deriveFont(25F));
		// Back 
		textY = frameY + gp.titleSize*9;
		g2.drawString("Quay lại", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
			}
		}
	}
	
	public void options_control(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		//Tile
		String text = "Điều khiển";
		textX = getXforCenteredText(text);
		textY = frameY + gp.titleSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.titleSize - 30;
		textY += gp.titleSize;
		g2.setFont(g2.getFont().deriveFont(25F));
		g2.drawString("Di chuyển", textX, textY); textY += gp.titleSize;
		g2.drawString("Xác nhận/Hành động", textX, textY); textY += gp.titleSize;
		g2.drawString("Thông tin nhân vật", textX, textY); textY += gp.titleSize;
		g2.drawString("Dừng game", textX, textY); textY += gp.titleSize;
		g2.drawString("Cài đặt", textX, textY); textY += gp.titleSize;
		
		textX = frameX + gp.titleSize*6 - 20;
		textY = frameY + gp.titleSize*2;
		g2.drawString("W A S D", textX, textY); textY += gp.titleSize;
		g2.drawString("E", textX, textY); textY += gp.titleSize;
		g2.drawString("C", textX, textY); textY += gp.titleSize;
		g2.drawString("P", textX, textY); textY += gp.titleSize;
		g2.drawString("ESC", textX, textY); textY += gp.titleSize;
		
		//Back
		textX = frameX + gp.titleSize;
		textY = frameY + gp.titleSize*9;
		g2.drawString("Quay lại", textX , textY);
		if(commandNum == 0) {
			g2.drawString(">", textX -25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
		
	}
	
	public void options_endGameConfirmation(int frameX, int frameY) {
		
		int textX = frameX + gp.titleSize;
		int textY = frameY + gp.titleSize *3;
		
		String text = "Xác nhận thoát game";
		textX = getXforCenteredText(text);
		textY = frameY + gp.titleSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.titleSize;
		textY = frameY + gp.titleSize *3;
		
		currentDialouge = "Thoát game và trở \n lại màn hình chính? ";
		for(String line: currentDialouge.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//Yes
		text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.titleSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				titleScreenState = 0;
				gp.stopMusic();
				gp.gameState = gp.titleState;
				//gp.resetGame(true);
			}
			
		}
		//No
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.titleSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 4;
			}
			
		}
		
		
	}
	public void drawTradeScreen() {
		
		switch (subState) {
		case 0: trade_select(); break;
		case 1: trade_buy(); break;
		case 2: trade_sell(); break;
		}
		gp.keyH.enterPressed = false;
		
	}
	public void trade_select() {
		
		drawDialogueScreen();
		
		//Draw window
		int x = gp.titleSize * 11;
		int y = (int)(gp.titleSize * 4.5);
		int width = gp.titleSize * 3;
		int height = (int)(gp.titleSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		//Draw text
		x += gp.titleSize;
		y += gp.titleSize;
		g2.drawString("Mua", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 1;
			}
			
		}
		y+= gp.titleSize;
		g2.drawString("Bán", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.enterPressed == true) {
				subState = 2;
			}
		}
		y+= gp.titleSize;
		g2.drawString("Rời đi.", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if(gp.keyH.enterPressed == true) {
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialouge = "Tạm biệt bạn, hihi!";
			}
		}
		y+= gp.titleSize;
		
		
		
		
	}
	public void trade_buy() {
		
		//Draw player Inventory 
		drawInventory(gp.player, false);
		//Draw NPC Inventory
		drawInventory(npc,true);
		
		//Draw hint window
		int x = gp.titleSize*2;
		int y = gp.titleSize*10;
		int width = gp.titleSize*6;
		int height = gp.titleSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Quay lại", x + 24, y + 60);
		
		//Draw player coin window
		x = gp.titleSize*9;
		y = gp.titleSize*10;
		width = gp.titleSize*6;
		height = gp.titleSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Coins: " + gp.player.coins, x + 24, y + 60);
		
		//Draw price window 
		int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
			x = (int)(gp.titleSize * 5.5);
			y = (int)(gp.titleSize * 5.3);
			width = (int)(gp.titleSize*2.5);
			height = gp.titleSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coins, x+13, y+10, 26,27, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = ""+price;
			x = getXforAllignToRightText(text, gp.titleSize*8 - 8);
			g2.drawString(text, x - 5, y+32);
			
			//Buy an item
			if(gp.keyH.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coins) {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialouge = "Bạn không đủ tiền để mua!";
					drawDialogueScreen();
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coins -= npc.inventory.get(itemIndex).price;
					}else {
						subState = 0;
						gp.gameState = gp.dialogueState;
						currentDialouge = "Túi của bạn không đủ!";
					}
				}
//				else if(gp.player.inventory.size() == gp.player.maxInventorySize) {
//					subState = 0;
//					gp.gameState = gp.dialogueState;
//					currentDialouge = "Túi của bạn không đủ!";
//					drawDialogueScreen();
//				}
//				else {
//					//Tiến hành trừ tiền nhân vật là thêm item vào kho đò
//					gp.player.coins -= npc.inventory.get(itemIndex).price;
//					//gp.player.inventory.add(npc.inventory.get(itemIndex));
//					gp.player.canObtainItem(npc.inventory.get(itemIndex));
//				}
			}
		}
		
	}
	public void trade_sell() {
		
		//Draw player Inventory 
		drawInventory(gp.player,true);
		
		int x;
		int y;
		int width;
		int height;
		
		//Draw hint window
		x = gp.titleSize*2;
		y = gp.titleSize*10;
		width = gp.titleSize*6;
		height = gp.titleSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("[ESC] Quay lại", x + 24, y + 60);
		
		//Draw player coin window
		x = gp.titleSize*9;
		y = gp.titleSize*10;
		width = gp.titleSize*6;
		height = gp.titleSize*2;
		drawSubWindow(x, y, width, height);
		g2.drawString("Coins: " + gp.player.coins, x + 24, y + 60);
		
		//Draw price window 
		int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			
			x = (int)(gp.titleSize * 12.5);
			y = (int)(gp.titleSize * 5.3);
			width = (int)(gp.titleSize*2.5);
			height = gp.titleSize;
			drawSubWindow(x, y, width, height);
			g2.drawImage(coins, x+13, y+10, 26,27, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = ""+price;
			x = getXforAllignToRightText(text, gp.titleSize*15 - 8);
			g2.drawString(text, x - 5, y+32);
			
			//Sell an item
			if(gp.keyH.enterPressed == true) {
				if(gp.player.inventory.get(itemIndex)== gp.player.currentCongCu) {
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialouge = "Bạn không thể bán với trang bị đang dùng!";
				}
				else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					
					gp.player.coins += price;
				}
			}
		}
		
	}
	
	
	
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
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
