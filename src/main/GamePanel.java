package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import data.SaveLoad;
import entity.Entity;
import entity.Player;
import tile_interactive.InteractiveTile;
import title.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	//Thiết lập cài đặt cho trò chơi
	final int originalTileSize = 16; // kích ô = 16
	final int scale = 3; 
	
	public final int titleSize = originalTileSize  * scale ; // Kích thước chuẩn để nhìn được là 160px x 160px
	public int maxScreenCol = 16; //Số cột tối đa 
	public int maxScreenRow = 12; //Số dòng tối đa
	public final int screenWidth = titleSize * maxScreenCol; // ~ 960 pixels
	public final int screenHeight = titleSize * maxScreenRow; // ~ 576 pixels
	
	//Thông số bản đồ thế giới
	public int maxWorldCol = 50; // Số cột tối đa
	public int maxWorldRow = 50; // Số dòng tối đa
	//Tạo ranh giới trong world map 
	public final int worldWidth = titleSize * maxWorldCol;
	public final int worldHeight = titleSize * maxWorldRow;
	
	//Bộ đếm thời gian
	public long days = 1, hours = 23,minutes = 0;
	//Tính khoảng thời gian đói
	public int lowheal = 0;
	//For full screen
	
	
	public boolean fullScreenOn = false;
	//FPS 
	int FPS = 60;
	
	
	TileManager tileM = new TileManager(this);
	//Cấp luồng xử lý FPS khung hình/s cho trò chơi 
	public KeyHandler keyH = new KeyHandler(this);
	//Khai báo lớp âm thanh cho trò chơi
	Sound se = new Sound(); // Khai báo hiệu ứng âm thanh
	Sound music = new Sound(); // Khái báo nhạc nền
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this); // Khai báo asset
	//Khai báo UIs
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	Config config = new Config(this);
	SaveLoad saveLoad = new SaveLoad(this);
	
	Thread gameThread;
	//Entity object
	public Player player = new Player(this,keyH);//Khai báo thông tin nhân vật
	public Entity obj[] = new Entity[100];
	public InteractiveTile objDig[] = new InteractiveTile[100];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	public InteractiveTile iTile[] = new InteractiveTile[100]; // Khai báo các đối tượng có thể tương tác bằng dụng cụ
	//Tất cả các thực thế hay npc đều sẽ được lưu dưới dạng danh sách
	ArrayList<Entity> entityList = new ArrayList<>();
	
	
	//GAME STATE: Các trạng thái của game 
	public int gameState;
	public final int titleState = 0; // Tiêu đề nội dung Screen
	public final int playState = 1; //Bắt đầu trò chơi
	public final int pauseState = 2; // Dừng trò chơi
	public final int dialogueState = 3; // hội thoại cho NPC
	public final int characterState = 4; // Tình trạng cho nhân vật 
	public final int optionsState = 5; // Màn hình setting
	public final int tradeState = 8; //Thực hiện giao dịch
	public final int gameOverState = 6; // Thực hiện màn hình game over
	
	private BufferedImage image ;
	
	//Login & Register
	public String usernameInput = ""; // Biến lưu trữ tên đăng nhập
	public String passwordInput = ""; // Biến lưu trữ mật khẩu
	public boolean isTypingUsername = false; // Biến xác định người dùng đang nhập tên đăng nhập
	public boolean isTypingPassword = false; // Biến xác định người dùng đang nhập mật khẩu
	public boolean checkAccountLogin = false;
	public boolean checkAccountRegister = false;
	
//	// Thiết lập vị trí mặc định của người chơi
//	int playerX = 100;
//	int playerY = 100;
//	int playerSpeed = 4;
//	
	
	//Hàm tạo Panel
	public GamePanel () {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		

		this.setDoubleBuffered(true);
		//Lắng nghe sự kiện từ bàn phím
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		
	}
	
	public void setupGane() {
		
		//Tiến hành set up các đối tượng ghi đè
		aSetter.setObject();
		//Set up NPC định sẵn tại vị trí
		aSetter.setNPC();
		//set up các cây có thể khai thác được
		aSetter.setInteractiveTile();
		//Set up các vị trí ô đất có thể đào được
		aSetter.setDig();
		//Phát nhạt theo số mong muốn truyền vào
		
		gameState = titleState;
		
		if(fullScreenOn == true) {
			//setFullScreen();
		}
		
		
	}
	
	public void resetTime() {
		days = 1;
		hours = 23;
		minutes = 0;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		resetTime();
		
		
	}
	public void resetGame() {
		player.setDefaultValues();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setInteractiveTile();
		aSetter.setDig();
	}
	
	public void retry() {
		player.setDefaultPositions();
		player.restoreStatus();
	}
	
	//Phương pháp 1: Delta
	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		double drawInterval = 1000000000/FPS; // 0.16666 giây để làm mới lại khung hình 
//		double nextDrawTime = System.nanoTime()+ drawInterval;
//		
//		
//		
//		//Lập vòng lặp FPS = 60s
//		while(gameThread !=null) {
//			
//			
//			System.out.println("Vòng lặp trò chơi đang chạy");
//			
//			//Thiết lập cử chỉ hành động cho nhân vật
//			
//			// 1. Cập nhật: Thông tin cập nhật như là vị trí nhân vật
//			update();
//			// 2. Phác thảo : phác thảo màn hình với thông tin cập nhật 
//			repaint();
//			
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/ 1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
	//Phương pháp 2: Tích lũy
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) /drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta>=1) {
				update();
				repaint();
				delta--;
				drawCount ++;
				
			}
			if(timer >= 1000000000) {
				//Kiểm tra FPS
				//System.out.println("FPS: "+ drawCount);
				updateTime();
				drawCount =0;
				timer = 0;
			}
		}
		

	}
	public void updateTime() {
		minutes++; // Tăng số phút lên sau mỗi phút
        if (minutes >= 60) { // Nếu đạt 60 phút
            minutes = 0; // Đặt lại số phút về 0
            hours++; // Tăng số giờ lên sau mỗi giờ
            plantTime();
            //Giảm heal
            lowheal++;
            if(lowheal > 6) {
            	player.giamMau();
            	lowheal = 0;
            }
            if (hours >= 24) { // Nếu đạt 24 giờ (1 ngày)
                hours = 0; // Đặt lại số giờ về 0
                days++; // Tăng số ngày lên sau mỗi ngày
                
                
            }
        }
	}
	public void plantTime() {
		for(int i = 0; i < this.objDig.length;i++) {
			if(this.objDig[i] != null) {
				System.out.println(this.objDig[i].name);
				if(this.objDig[i].phatTrien == true) {
					objDig[i] = objDig[i].getDestroyedForm();
				}
			}
		}
	}
	
	
	public void update() {
		
//		System.out.println(playState);
		if(gameState == playState) {
			//Player
			player.update();
			//NPC
			for(int i = 0; i < npc.length; i ++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			//Khi cần thì tại chỗ này vẽ animation cho monster
			
			//Tiến hành vẽ các obj có tương tác bằng dụng cụ
			for(int i =0;i<iTile.length;i++) {
				if(iTile[i] != null) {
					iTile[i].update();
				}
			}
			//Vẽ các khu đất
			for(int i =0;i<objDig.length;i++) {
				if(objDig[i] != null) {
					objDig[i].update();
				}
			}
		}
		
		
		if(gameState == pauseState) {
			//Không cần cập nhật
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		//Chuyển đổi sang 2D
		Graphics2D g2 = (Graphics2D)g;
//		//Thiết lập màu cho màn hình
//		g2.setColor(Color.BLACK);
//		//Thiết lập màn hình khi được khởi động
//		g2.fillRect(playerX, playerY, titleSize , titleSize);
//		//Set hình nền cho giao diện
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/background/bg01.png"));
			g.drawImage(image, 0, 0, screenWidth, screenHeight, null);
		} catch (Exception e) {
			// TODO: handle exception
			g.setColor(Color.BLUE);
		}
		
		
		//Debug kiểm tra thời gian vẽ khung hình
		long drawStart = 0;
		if (keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		
//		System.out.println(gameState);
		//Tile Screen
		if(gameState == titleState) {
			ui.draw(g2);
		}else {
			//Tile
			//Phải vẽ các ô đất trước khi vẽ nhân vật
			tileM.draw(g2);//Vẽ các đất trong quá trình repaint vẽ lại
			
			//Thêm các Entities vào trong danh sách entity
			entityList.add(player);
			
			//Vẽ các obj có thể tương tác bằng dụng cụ -- Interactive Tile
			for(int i = 0;i< iTile.length;i++) {
				if(iTile[i] != null) {
					iTile[i].draw(g2);
				}
			}
			//Vẽ các khu đất có thể tương tác bằng dụng cụ -- Interactive Tile
			for(int i = 0;i< objDig.length;i++) {
				if(objDig[i] != null) {
					objDig[i].draw(g2);
				}
			}
			//Vẽ npc trước 
			for(int i =0; i <npc.length;i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			//Vẽ các object hiện diện trên map
			for(int i = 0;i<obj.length;i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			
			//Với monster cũng sẽ lấy hàm for ở trên đếm xuống và thay obj => monster
			
			
			
			
			//Sort
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					// TODO Auto-generated method stub
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			//Draw Entities
			for(int i =0; i < entityList.size();i++) {
				entityList.get(i).draw(g2);
			}
			//Empty entity list
//			for(int i =0; i < entityList.size();i++) {
//				entityList.remove(i);
//			}
			entityList.clear();
			
			//Player
			//Vẽ nhân vật
			//player.draw(g2);
			
			//UI
			ui.draw(g2);
		}
		
		
		//Debug vẽ khung hình
		if(keyH.checkDrawTime == true ) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time" + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
			
			g2.setColor(Color.white);
			g2.drawString("Col: " + this.player.worldX/titleSize , 10, 420);
			
			g2.setColor(Color.white);
			g2.drawString("Row: " + this.player.worldY/titleSize, 10, 440);
		}
		
		g2.dispose();
	}
	//Hàm chơi nhạc
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	//Dừng nhạc
	public void stopMusic() {
		music.stop();
	}
	//hiệu ứng âm thanh
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
}
