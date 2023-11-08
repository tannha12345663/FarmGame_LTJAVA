package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HatGiong1 extends Entity{

	/**
	 * The days for the crop to grow.
	 */
	private int daysToGrow;
	
	/**
	 * The days the crop has grown.
	 */
	private int daysGrown = 0;
	
	public OBJ_HatGiong1(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		name = "Bắp";
		down1 = setup("/HatGiong/HatGiong1");
		description = "[" + name +"] \n Khi bắp đã hoàn \n thành cả 5 giai đoạn \n trên, hạt bắp sẽ \n ở trong tình trạng \n tốt nhất để thu hoạch ";
	}

}
