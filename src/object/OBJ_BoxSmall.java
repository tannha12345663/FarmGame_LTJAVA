package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_BoxSmall extends Entity {
	public OBJ_BoxSmall(GamePanel gp) {
		super(gp);
		name ="BoxSmall";
		image = setupOption("/giaoDien/boxSmall",220,80);
		//image = uTool.scaleImage(image, gp.titleSize + 220, gp.titleSize + 80);
		
		
		}
}
