package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import title.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	//Thiết lập cài đặt cho trò chơi
	final int originalTileSize = 16; // kích ô = 16
	final int scale = 3; 
	
	public final int titleSize = originalTileSize  * scale ; // Kích thước chuẩn để nhìn được là 160px x 160px
	public int maxScreenCol = 16; //Số cột tối đa 
	public int maxScreenRow = 12; //Số dòng tối đa
	public final int screenWidth = titleSize * maxScreenCol; // ~ 1280 pixels
	public final int screenHeight = titleSize * maxScreenRow; // ~ 960 pixels
	
	//Thông số bản đồ thế giới
	public int maxWorldCol = 50; // Số cột tối đa
	public int maxWorldRow = 50; // Số dòng tối đa
	//Tạo ranh giới trong world map 
	public final int worldWidth = titleSize * maxWorldCol;
	public final int worldHeight = titleSize * maxWorldRow;

	
	
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
	Thread gameThread;
	//Entity object
	public Player player = new Player(this,keyH);//Khai báo thông tin nhân vật
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	
	//GAME STATE: Trạng thái của game 
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	//Tạo hội thoại cho NPC
	public final int dialogueState = 3;
	
	
	
	
	
//	// Thiết lập vị trí mặc định của người chơi
//	int playerX = 100;
//	int playerY = 100;
//	int playerSpeed = 4;
//	
	
	//Hàm tạo Panel
	public GamePanel () {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.blue);
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
		//Phát nhạt theo số mong muốn truyền vào
		playMusic(2);
		stopMusic();
		gameState = playState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
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
				drawCount =0;
				timer = 0;
			}
		}
		

	}
	public void update() {
		
		if(gameState == playState) {
			//Player
			player.update();
			//NPC
			for(int i = 0; i < npc.length; i ++) {
				if(npc[i] != null) {
					npc[i].update();
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
//		//
		
		//Debug kiểm tra thời gian vẽ khung hình
		long drawStart = 0;
		if (keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		
		
		
		//Tile
		//Phải vẽ các ô đất trước khi vẽ nhân vật
		tileM.draw(g2);//Vẽ các đất trong quá trình repaint vẽ lại
		
		//Object
		//Vẽ các object cố định từ trước
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//NPC
		for(int i =0; i< npc.length;i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		
		//Player
		//Vẽ nhân vật
		player.draw(g2);
		
		//UI
		ui.draw(g2);
		
		//Debug vẽ khung hình
		if(keyH.checkDrawTime == true ) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time" + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
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
