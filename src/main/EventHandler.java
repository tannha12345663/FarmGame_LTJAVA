package main;
public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][];
	
	//Sự kiện trước đó
	int previousEventX,  previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[col][row] = new EventRect();
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23; 
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row ++;
			}
		}
		
	}
	
	public void checkEvent() {
		//Kiểm tra người chơi có nhiều hơn 1 ô hay không từ sự kiện cuối cùng
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.titleSize) {
			canTouchEvent = true;
		}
		
		//Với dòng code này khi va chạm sẽ tránh trường hợp chỉ cần di chuyển gần khu vực sẽ không bị trừ máu liên tục
		if(canTouchEvent == true) {
			//Tạo địa điểm xảy ra biến cố
			if(hit(12,16,"right") == true || hit(12,16,"left") == true) {
				//Xử lý sự kiện diễn ra tại vị trí này
				damgePit(12,16,gp.dialogueState);
			}
			if(hit(14,13,"left") == true || hit(12,13,"right") == true ||  hit(13,14,"up") == true ||  hit(13,12,"down") == true) {
				//Xử lý hổi máu cho người chơi
				//healingPool(2,16,gp.dialogueState);
				getWater();
			}
			//Khai báo vị trí tele
			if(hit(9,16,"right") == true ) {
				telePort(gp.dialogueState);
			}
//			if(hit(15,16,"down") == true) {
//				
//			}
		}
		
		
	}
	public boolean hit(int col, int row, String reqDirection) {
		
		boolean hit = false; 
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.titleSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.titleSize + eventRect[col][row].y;
//		System.out.println("col: "+ col +": Row: "+ row);
//		System.out.println(eventRect[col][row].evenDone);
		
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].evenDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
				
				//Lưu lại dấu vết người chơi khi gặp event
				previousEventX = gp.player.worldX;
				previousEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		//System.out.println(hit);
		
		return hit;
		
	}
	
	//Gây vết thương cho người chơi
	public void damgePit(int col,int row,int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialouge = "Bạn đã bị thương!";
		gp.player.life -=1;
	//	eventRect[col][row].evenDone = true; //Người chơi đã bị thương và mất hố bẫy vì vậy sẽ đánh dấu khu vực này không còn bị sát thuongw nữa
		if(gp.player.life == 0) {
			gp.gameState = gp.titleState;
			
		}
		canTouchEvent = false;
		
	}
	
	//Hồi máu cho người chơi
	public void healingPool(int col,int row,int gameState) {
		
		if(gp.keyH.enterPressed == true ) {
			gp.gameState = gameState;
			gp.player.daoDatCanceled = true;
			gp.ui.currentDialouge = "Bạn đã uống nước thần ! \n Máu của bạn đã được phục hồi.";
			//Tăng máu cho nhân vật mỗi lần uống
			if(gp.player.life < gp.player.maxLife) {
				gp.player.life ++;
			}
			else {
				gp.ui.currentDialouge = "Máu của bạn đã đầy";
			}
		}
	}
	//Tele người chơi sang khu vực khác
	public void telePort(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialouge = "Bạn đã được dịch chuyển";
		gp.player.worldX = gp.titleSize*20;
		gp.player.worldY = gp.titleSize*10;
//		eventRect[col][row].evenDone = true;
	}
	
	//Vị trí hứng nước cho người chơi
	public void getWater() {
		if(gp.keyH.enterPressed == true && gp.player.currentCongCu.type == gp.player.type_watering ) {
			if(gp.player.currentCongCu.valueConsumable < gp.player.currentCongCu.maxValueConsum) {
				gp.player.currentCongCu.valueConsumable ++;
				gp.ui.addMessage("Sức chứa: "+ gp.player.currentCongCu.valueConsumable + "/"+gp.player.currentCongCu.maxValueConsum);
			}
			else if(gp.player.currentCongCu.valueConsumable >= gp.player.currentCongCu.maxValueConsum)
			{
				gp.player.currentCongCu.valueConsumable = gp.player.currentCongCu.maxValueConsum;
				gp.ui.addMessage("Bình đã đầy!");
			}
		}
	}
}
