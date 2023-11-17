package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Lớp này để set up bàn phím cho người chơi
public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed , downPressed, leftPressed, rightPressed, enterPressed;
	//DEBUG
	boolean checkDrawTime = false;
	
	//Tạo bảng điều khiển
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		//System.out.println(gp.ui.titleScreenState);
		// Trạng thái Title (Màn hình khởi động)
		//code cu if(gp.gameState == gp.titleState)
		if(gp.gameState == gp.titleState) {
			titleState(code,e);
		}	
		//Trạng thái chơi
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		//Tạm dừng trờ chơi
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		//Lời thoại trò chơi
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		//Character State -- Trạng thái người chơi
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		//Options State -- Màn hình cài đặt
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		//Trade state
		else if(gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
	}
	//Giao diện màn hình
	public void titleState(int code, KeyEvent e) {
		if(gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < -1) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = -1;
				}
			}
			//Set up núp chọn mod cho game
			if(code == KeyEvent.VK_E) {
				//Kiểm tra nếu là chơi mới
				if(gp.ui.commandNum == 0) {
					//Khởi động trò chơi mới
					gp.ui.titleScreenState = 1;// Chuyển sang màn hình thứ 2
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1) {
					//Add late
					//Load game
					gp.gameState = gp.playState;
					String file = "";
					if(gp.checkAccountLogin == true) {
						file = gp.usernameInput + "_" + gp.passwordInput + ".dat";
						
					}
					else if(gp.checkAccountLogin == false) {
						file = "save.dat";
					}
					
					gp.saveLoad.load(file);
					gp.player.getPlayerImage();
					
					gp.playMusic(2);
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
				if(gp.ui.commandNum == -1) {
					gp.ui.commandNum = 0;
					gp.checkAccountLogin = false;
					gp.ui.titleScreenState = -1;
				}
			}
		}
		else if(gp.ui.titleScreenState == 1) {// Màn hình 1
			if(code == KeyEvent.VK_Q) {
				//Quay lại màn hình đầu
				gp.ui.titleScreenState = 0;
				gp.stopMusic();
			}
			if (code == KeyEvent.VK_E) {
				//chuyển sang giao diện 02
				gp.ui.titleScreenState = 2;
			}
		}else if(gp.ui.titleScreenState == 2) {// Màn hình 2
			if(code == KeyEvent.VK_Q) {
				//Quay lại màn hình đầu
				gp.ui.titleScreenState = 1;
			}
			if (code == KeyEvent.VK_E) {
				//chuyển sang giao diện 02
				gp.ui.titleScreenState = 3;
			}
		}
		else if(gp.ui.titleScreenState == 3) { // Màn hình 3
			if(code == KeyEvent.VK_Q) {
				//Quay lại màn hình đầu
				gp.ui.titleScreenState = 2;
			}
			if (code == KeyEvent.VK_E) {
				//chuyển sang giao diện 02
				gp.ui.titleScreenState = 4;
			}
		}
		else if(gp.ui.titleScreenState == 4) {// Màn hình 4
			if(code == KeyEvent.VK_A) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_D) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			//Set up các nút chọn
			if(code == KeyEvent.VK_Q) {
				//Quay lại màn hình đầu
				gp.ui.titleScreenState = 3;
			}
			if (code == KeyEvent.VK_E) {
				//chuyển sang giao diện 04
				//Kiểm tra xem người dùng chọn nhân vật nào
				if(gp.ui.commandNum == 0) {
					gp.player.selectPlayer = 1;
					
				}
				if(gp.ui.commandNum == 1) {
					gp.player.selectPlayer = 2;
				}
				gp.player.getPlayerImage();
				String file1 = "";
				System.out.println("Tinh trạng đăng nhập" + gp.checkAccountLogin);
				if(gp.checkAccountLogin == true) {
					file1 = gp.usernameInput + "_" + gp.passwordInput + ".dat";
				}
				else {
					file1 = "save.dat";
				}
				gp.resetGame();
				gp.saveLoad.clearFileContent(file1);
				gp.gameState = gp.playState;
				gp.stopMusic();
				gp.playMusic(2);
//				gp.ui.titleScreenState = ;
			}
		}
		else if(gp.ui.titleScreenState == -1) {
			
			if(code == KeyEvent.VK_W && gp.isTypingPassword == false && gp.isTypingUsername == false ) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_S && gp.isTypingPassword == false && gp.isTypingUsername == false) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.isTypingUsername = true;
					gp.isTypingPassword = false;
				}
				if(gp.ui.commandNum == 1) {
					gp.isTypingUsername = false;
					gp.isTypingPassword = true;
				}
				
			}
			if(code == KeyEvent.VK_E) {
				if(gp.ui.commandNum == 2) {
					//Tiến hành kiểm tra và hiển thị thông báo
					
					//Kiểm tra đăng nhập 
					System.out.println("Username: "+ gp.usernameInput + ", Password: "+ gp.passwordInput + ", khi đăng nhập");
					if(gp.saveLoad.doesFileExist(gp.usernameInput,gp.passwordInput) == true) {
						gp.checkAccountLogin = true;

					}
					else {
						gp.checkAccountLogin = false;
					}
					
					gp.ui.commandNum = 4 ;
				}
				if(gp.ui.commandNum == 3) {
					
					//Kiểm tra thông tin đăng ký
					System.out.println("Username: "+ gp.usernameInput + ", Password: "+ gp.passwordInput + ", khi đăng ký");
					if(gp.saveLoad.doesFileExist(gp.usernameInput,gp.passwordInput) == false) {
						gp.checkAccountRegister = true;
						gp.saveLoad.createFileForUser(gp.usernameInput,gp.passwordInput);
					}
					else {
						gp.checkAccountRegister = false;
					}
					
					gp.ui.commandNum = 5;
				}
				if(gp.ui.commandNum == 5) {
					gp.checkAccountLogin = true;
					gp.ui.titleScreenState = 0;
				}
			}
			
			if(code == KeyEvent.VK_BACK_SPACE && (gp.isTypingPassword || gp.isTypingUsername)) {
				if(gp.isTypingUsername && !gp.usernameInput.isEmpty()) {
					gp.usernameInput = gp.usernameInput.substring(0, gp.usernameInput.length() - 1);
				}
				else if(gp.isTypingPassword && !gp.passwordInput.isEmpty()) {
					gp.passwordInput = gp.passwordInput.substring(0, gp.passwordInput.length() - 1);
				}
			}
			else if(Character.isLetterOrDigit(e.getKeyChar())) {
				//Cho phép người dùng nhập user từ bàn phím
				if( gp.isTypingUsername == true && gp.ui.commandNum == 0) {
					gp.usernameInput += e.getKeyChar();
				}
				//Cho phép người dùng nhập pass từ bàn phím
				if( gp.isTypingPassword == true && gp.ui.commandNum == 1) {
					gp.passwordInput += e.getKeyChar();
				}
			}
			
			//Thoát khỏi ô đang nhập 
			if(code == KeyEvent.VK_ESCAPE) {
				gp.isTypingUsername = false;
				gp.isTypingPassword = false;
			}
			if(code == KeyEvent.VK_Q) {
				gp.ui.titleScreenState = 0;
			}
		}
	}
	//Giao diện chơi
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed= true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_E) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
		
		//Debug
		if (code == KeyEvent.VK_T) {
			if(checkDrawTime == false) {
				checkDrawTime = true;
			}
			else if(checkDrawTime == true) {
				checkDrawTime = false;
			}
		}	
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
		}
	}
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_E) {
			gp.playSE(23);
			gp.player.selectItem();
		}
		playerInventory(code);
	}
	
	public void optionsState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_E) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum = 5; break;
		case 3: maxCommandNum = 1; break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(22);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(22);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
		//Thiết lập nút tăng giảm âm lượng trong game
		if(code == KeyEvent.VK_A) {
			if(gp.ui.subState == 0){
				if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSE(22);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(22);
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.subState == 0){
				if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(22);
				}
				if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(22);
				}
			}
		}
	}
	public void tradeState(int code) {
		
		if(code == KeyEvent.VK_E) {
			enterPressed = true;
		}
		if(gp.ui.subState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum --;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSE(22);
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum ++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(22);
			}
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	
	public void playerInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSE(22);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSE(22);
			}	
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSE(22);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSE(22);
			}
		}
	}
	public void npcInventory(int code) {
		if(code == KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSE(22);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSE(22);
			}	
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSE(22);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSE(22);
			}
		}
	}
	
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum --;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(22);
			
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(22);
		}
		
		if(code == KeyEvent.VK_E) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				//Nghỉ ngơi
				gp.retry();
			}
			else if(gp.ui.commandNum == 1) {
				gp.gameState = gp.titleState;
				//Chơi lại
				gp.resetGame();
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed= false;
		}
	}
	
}
