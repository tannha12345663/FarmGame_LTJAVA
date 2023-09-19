package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
	
	//Lớp này dùng để điều chỉnh là kích thước hình ảnh nhằm tăng tốc độ xử lý hình ảnh
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null); // Tiến hành vẽ lại 
		g2.dispose(); //Giải phóng tài nguyên
		
		return scaledImage;
	}
}
