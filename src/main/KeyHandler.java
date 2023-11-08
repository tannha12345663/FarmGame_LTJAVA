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
			
			if(gp.ui.titleScreenState == 0) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
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
					}
					if(gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreenState == 1) {
				if(code == KeyEvent.VK_Q) {
					//Quay lại màn hình đầu
					gp.ui.titleScreenState = 0;
					gp.stopMusic();
				}
				if (code == KeyEvent.VK_E) {
					//chuyển sang giao diện 02
					gp.ui.titleScreenState = 2;
				}
			}else if(gp.ui.titleScreenState == 2) {
				if(code == KeyEvent.VK_Q) {
					//Quay lại màn hình đầu
					gp.ui.titleScreenState = 1;
				}
				if (code == KeyEvent.VK_E) {
					//chuyển sang giao diện 02
					gp.ui.titleScreenState = 3;
				}
			}else if(gp.ui.titleScreenState == 3) {
				if(code == KeyEvent.VK_Q) {
					//Quay lại màn hình đầu
					gp.ui.titleScreenState = 2;
				}
				if (code == KeyEvent.VK_E) {
					//chuyển sang giao diện 02
					gp.gameState = gp.playState;
					gp.stopMusic();
					gp.playMusic(2);
				}
			}
			
			
		}
		
			
		//Trạng thái chơi
		else if(gp.gameState == gp.playState) {
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
			if(code == KeyEvent.VK_E) {
				enterPressed = true;
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
		//Tạm dừng trờ chơi
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}
		//Lời thoại trò chơi
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_E) {
				gp.gameState = gp.playState;
				
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
