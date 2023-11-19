package main;

import entity.Entity;
import tile_interactive.InteractiveTile;
import tile_interactive.Land;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker (GamePanel gp) {
		this.gp =gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.titleSize;
		int entityRightCol = entityRightWorldX/gp.titleSize;
		int entityTopRow = entityTopWorldY/gp.titleSize;
		int entityBottomRow = entityBottomWorldY/gp.titleSize;
		
		int tileNum1, tileNum2;
		
		try {
			switch (entity.direction) {
			case "up": {
				entityTopRow = ((entityTopWorldY - entity.speed) / gp.titleSize); // Lưu ý chỗ này bắt buộc phải là TitleSize lấy kích thước ban đầu
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				if(gp.tileM.tile[tileNum1].collision== true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
			}
			case "down":{
				entityBottomRow = ((entityBottomWorldY + entity.speed) / gp.titleSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision== true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
			}
			case "left":{
				entityLeftCol = ((entityLeftWorldX - entity.speed) / gp.titleSize);
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision== true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
			}
			case "right":{
				entityRightCol = ((entityRightWorldX + entity.speed) / gp.titleSize);
				tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision== true || gp.tileM.tile[tileNum2].collision == true) {
					entity.collisionOn = true;
				}
				
				break;
			}
			default:
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Không tìm thấy: ");
		}
	}
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		//Tạo vòng lặp for
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] !=null) {
				
				//Cần lấy vị trị solidArea của thực thể
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//Cần lấy thông tin solidArea của đối tượng
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;	
				
				switch (entity.direction) {
				case "up": 
					entity.solidArea.y -= entity.speed;
					break;
				
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
//					System.out.println("Hương đi lên đang va chạm với cái gì đó");
					//Làm các hàm if này để kiểm tra có phải người chơi hay không , hay là NPC, quái vật đi ngang qua
					if(gp.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					if (player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	//NPC or animal
	public int checkEntity(Entity entity, Entity[] target) {
		
		int index = 999;
		
		//Tạo vòng lặp for
		for(int i = 0; i < target.length; i++) {
			if(target[i] !=null) {
				
				//Cần lấy vị trị solidArea của thực thể
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//Cần lấy thông tin solidArea của đối tượng
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;	
				
				switch (entity.direction) {
				case "up": 
					entity.solidArea.y -= entity.speed;					
					break;
				
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				//Kiểm tra xem hai khối hình chữ nhật này có va chạm nhau hay không
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(target[i] != entity) {
						entity.collisionOn = true;
						index = i;
					}					
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	
	//Kiểm tra NPC có chạm phải người chơi hay không
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		//Cần lấy vị trị solidArea của thực thể
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		//Cần lấy thông tin solidArea của đối tượng
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;	
		
		switch (entity.direction) {
		case "up": 
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;		
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		//Kiểm tra xem hai khối hình chữ nhật này có va chạm nhau hay không
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
	//Kiểm tra khu vực này có cho đào đất hay không 
	public int checkDig(Entity entity, InteractiveTile[] target) {
		int index = 999;
		
		//Tạo vòng lặp for
		for(int i = 0; i < target.length; i++) {
			if(target[i] !=null) {
				
				//Cần lấy vị trị solidArea của thực thể
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//Cần lấy thông tin solidArea của đối tượng
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;	
				
				switch (entity.direction) {
				case "up": 
					entity.solidArea.y -= entity.speed;					
					break;
				
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				//Kiểm tra xem hai khối hình chữ nhật này có va chạm nhau hay không
				if(entity.solidArea.intersects(target[i].solidArea)) {
					System.out.println("Hai khối va chạm" + target[i].name);
					if(target[i].name !="Đất" || target[i].name != "Đất đã đào") {
						entity.collisionOn =  target[i].collision; 
						index = i;
					
					}			
				}
				
				
			}
		}
		return index;
	}
}
	
