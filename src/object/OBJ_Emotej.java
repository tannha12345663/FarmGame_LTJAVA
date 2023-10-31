package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Emotej extends Entity {
	public OBJ_Emotej(GamePanel gp) {
		super(gp);	
		name ="Emotej";
		image = setupOption("/Emotej/Teemo-Basic-emote-animations-sprite-sheet_01",10,15);
		//Chia tỷ lệ hiển thị 
		//image = uTool.scaleImage(image, gp.titleSize + 10, gp.titleSize + 15);
		}
}
