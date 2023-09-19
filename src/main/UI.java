package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
//	BufferedImage keyImage;
	//Khai báo nội dung tin nhắn
	public boolean messageOn = false;
	public String message ="";
	//Khai báo thời gian hiển thị message
	int messageCounter = 0;
	//Khai báo trò chơi đã kết thúc hay chưa
	public boolean gameFinished = false;
	// Khai báo đoạn thoại tin nhắn
	public String currentDialouge = "";
	
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
	}
	
	//Hiển thị nội dung tin nhắn
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	//Vẽ màn hình hiển thị số lượng key đã nhặt
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//Player State
		if(gp.gameState == gp.playState) {
			//Cho trò chơi tiếp tục hoạt động
			
		}
		//Pause State
		if(gp.gameState == gp.pauseState) {
			// Tạm ngưng trò chơi
			drawPauseScreen();
		}
		//Dialouge State
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
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
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		String text = "TẠM NGỪNG";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2 - (gp.titleSize*2);
		
		g2.drawString(text, x, y);
	}
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
	
	public int getXforCenteredText(String text) {
		//Lấy thông tin độ dài của ký tự
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
