package object;


import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {
	public OBJ_Coin(GamePanel gp) {
		super(gp);
		name ="Coin";
		image = setupOption("/giaoDien/Coin",-24,-12);
		}
}
