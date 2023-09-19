package title;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

//Hàm xây dựng nền đất map cho trò chơi
public class TileManager {
	
	GamePanel gp;
	public Tile[] tile ;
	public	int mapTileNum[][]; // Khai báo biến của map
	
	//Khai báo tự động nhập liệu map
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	public TileManager(GamePanel gp) {
		
		this.gp=gp;
		
		//Đọc dữ liệu từ file Tile Data
		InputStream is = getClass().getResourceAsStream("/maps/map_04_tile_data.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));	
		//Lấy tên Tile và thông tin Collision từ Tile_data
		String line;
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		
		//Cách 1
//		tile = new Tile[500];
//		
//		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //Khai báo thông tin 
//		
//		getTileImage();
//		//Map 1
//		loadMap("/maps/map_04.txt");
		//Cách 2
		
		//Lấy thông tin maxWorldCol & Row
		tile = new Tile[fileNames.size()];
		getTileImage();
		
		is = getClass().getResourceAsStream("/maps/map_04.txt");
		br = new BufferedReader(new InputStreamReader(is));
		try {
			String line2 = br.readLine();
			String maxTile[] = line2.split(" ");
			
			gp.maxWorldCol = maxTile.length;
			gp.maxScreenRow = maxTile.length;
			mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //Khai báo thông tin
			
			br.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Xảy ra lỗi lấy thông tin kích thước bản đồ");
		}
		
		
		loadMap("/maps/map_04.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			for(int i = 0 ; i< fileNames.size(); i++) {
				
				String fileName;
				boolean collision;
				
				//Lấy một file name
				fileName = fileNames.get(i);
				
				//Lấy thông tin collision
				if(collisionStatus.get(i).equals("true")) {
					collision = true;
				}
				else {
					collision = false;
				}
				setup(i, fileName, collision);
				
			}
//			tile[1] = new Tile();
//			tile[1].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_02.png"));
//			tile[1].collision=true;
//			
//			tile[2] = new Tile();
//			tile[2].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_03.png"));
//			tile[2].collision=true;
//			
//			tile[3] = new Tile();
//			tile[3].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_04.png"));
//			tile[3].collision=true;
//			
//			tile[4] = new Tile();
//			tile[4].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_05.png"));
//			tile[4].collision=true;
//			
//			tile[10] = new Tile();
//			tile[10].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_11.png"));
//			tile[10].collision=true;
//			
//			tile[11] = new Tile();
//			tile[11].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_12.png"));
//			tile[11].collision=true;
//			
//			tile[12] = new Tile();
//			tile[12].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_13.png"));
//			tile[12].collision=true;
//			
//			tile[13] = new Tile();
//			tile[13].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Basic-Grass-Biom-things-1_14.png"));
//			tile[13].collision=true;
//			
//			tile[49] = new Tile();
//			tile[49].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Chest_05.png"));
//			tile[49].collision=true;
//			
//			tile[54] = new Tile();
//			tile[54].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_01.png"));
//			tile[54].collision=true;
//			
//			tile[55] = new Tile();
//			tile[55].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_02.png"));
//			tile[55].collision=true;
//			
//			tile[56] = new Tile();
//			tile[56].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_03.png"));
//			tile[56].collision=true;
//
//			tile[57] = new Tile();
//			tile[57].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_04.png"));
//			tile[57].collision=true;
//			
//			tile[58] = new Tile();
//			tile[58].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_05.png"));
//			tile[58].collision=true;
//			
//			tile[59] = new Tile();
//			tile[59].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_06.png"));
//			tile[59].collision=true;
//			
//			tile[60] = new Tile();
//			tile[60].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_07.png"));
//			tile[60].collision=true;
//			
//			tile[61] = new Tile();
//			tile[61].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_08.png"));
//			tile[61].collision=true;
//			
//			tile[62] = new Tile();
//			tile[62].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Free_Chicken_House_09.png"));
//			tile[62].collision=true;
//			
//			tile[64] = new Tile();
//			tile[64].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_02.png"));
//			
//			tile[76] = new Tile();
//			tile[76].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_20.png"));
//			
//			tile[77] = new Tile();
//			tile[77].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_21.png"));
//			
//			tile[78] = new Tile();
//			tile[78].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_22.png"));
//			
//			tile[82] = new Tile();
//			tile[82].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_28.png"));
//			
//			tile[83] = new Tile();
//			tile[83].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_29.png"));
//			
//			tile[84] = new Tile();
//			tile[84].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_30.png"));
//			
//			tile[86] = new Tile();
//			tile[86].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_32.png"));
//			
//			tile[87] = new Tile();
//			tile[87].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_33.png"));
//			
//			tile[88] = new Tile();
//			tile[88].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Grass_34.png"));
//			
//			tile[106] = new Tile();
//			tile[106].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/key.png"));
//			tile[106].collision=true;
//			
//			tile[124] = new Tile();
//			tile[124].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Water_03.png"));
//			tile[124].collision=true;
//			
//		    tile[125] = new Tile();
//			tile[125].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_01.png"));
//			tile[125].collision=true;
//			
//		    tile[126] = new Tile();
//			tile[126].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_03.png"));
//			tile[126].collision=true;
//			
//		    tile[128] = new Tile();
//			tile[128].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_07.png"));
//			tile[128].collision=true;
//			
//			tile[130] = new Tile();
//			tile[130].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_10.png"));
//			//tile[130].collision=true;
//			
//			tile[131] = new Tile();
//			tile[131].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_11.png"));
//			tile[131].collision=true;
//			
//			tile[135] = new Tile();
//			tile[135].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/Wooden-House_16.png"));
//			tile[135].collision=true;
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();;
			System.out.println("");
		}
		
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		 UtilityTool uTool = new UtilityTool();
		 
		 try {
			tile[index] = new Tile();
			tile[index].imgae = ImageIO.read(getClass().getResourceAsStream("/tiles_Nha/"+ imageName));
			tile[index].imgae = uTool.scaleImage(tile[index].imgae, gp.titleSize, gp.titleSize);
			tile[index].collision = collision;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi đọc file :"+ imageName);
		}
	}
	
	public void loadMap(String filePath) {
		
		//Tiến hành đọc load map từ file
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			
			
			int col = 0; //Cột
			int row =0;	//Dòng
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				//Giới hạn lại đường biên
				while (col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					//Chuyển đổi thành số nguyên
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
		} catch (Exception e) {
			System.out.println("Có lỗi xảy ra khi đếm");
		}
		
	}
	
	public void draw(Graphics2D g2) {
		//Vẽ map tại đây
		
		//Phần rìa map
		
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow ) {
			
			//Render map
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.titleSize;
			int worldY = worldRow * gp.titleSize;
			//Phần này sẽ trừ hao khi màn hình camera ở các góc cạnh cuối cùng của map
			int screenX = worldX - gp.player.worldX + gp.player.screenX; 
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//Dừng di chuyển camera khi ở ranh giới 
//			if(gp.player.screenX > gp.player.worldX) {
//				screenX = worldX;
//			}
//			if(gp.player.screenY > gp.player.screenY){
//				screenY = worldY;
//			}
//			int rightOffset = gp.screenWidth - gp.player.screenX;
//			if(rightOffset > gp.worldWidth - gp.player.worldX) {
//				screenX = gp.screenWidth - (gp.worldWidth - worldX);
//			}
//			int bottomOffset = gp.screenHeight - gp.player.screenY;
//			if(bottomOffset > gp.worldHeight - gp.player.worldY) {
//				screenY = gp.screenHeight - (gp.worldHeight - worldY);
//			}
			
			//System.out.println("Screen X = "+ screenX +", Screen Y = "+ screenY);
			//System.out.println("World X = "+ worldX +", World Y = "+ worldY);
			
			//Chỉ vẽ nhưng ô cần thiết tại vị trí của người chơi còn lại thì không cần vẽ để tăng hiệu suất trò chơi
			//Kiểm tra 
			if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
				try {
					//System.out.println("Bạn đang ở ô : "+ tileNum);
					g2.drawImage(tile[tileNum].imgae, screenX, screenY,gp.titleSize,gp.titleSize,null);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Thiếu file: "+ tileNum);
				}
			}
//			else if(gp.player.screenX > gp.player.worldX ||
//					 gp.player.screenY > gp.player.worldY ||
//					 rightOffset > gp.worldWidth - gp.player.worldX ||
//					 bottomOffset > gp.worldWidth - gp.player.worldY) {
//				g2.drawImage(tile[tileNum].imgae, screenX, screenY,gp.titleSize,gp.titleSize,null);
//			}
			
			worldCol++;
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		
	}
	
}
