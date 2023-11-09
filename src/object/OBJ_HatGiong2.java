package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_HatGiong2 extends Entity {

	public OBJ_HatGiong2(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		type = type_plant2;
		name = "Cà Tím";
		down1 = setup("/HatGiong/HatGiong2");
		description = "[" + name +"] \n Khi Cà Tím đã \n hoàn thành cả 5 \n giai đoạn trên, hạt \n bắp sẽ ở trong tình \n trạng tốt nhất để \n thu hoạch ";
		collision = true;
		daysToGrow = 5; //số ngày tối thiểu để thu hoạch
		daysGrown = 0; // Số ngày phát triển
	}

}
