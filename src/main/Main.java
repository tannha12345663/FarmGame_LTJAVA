package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Xin chào");
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Nông trại vui vẻ");
		
		//Phần khởi động hiển thị trò chơi
		GamePanel gamePanel = new GamePanel();
		
		
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGane();
		gamePanel.startGameThread();
		//20/09/2023
		//Nội dung
	}

}
