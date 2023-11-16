package tile_interactive;
import entity.Entity;
import main.GamePanel;

public class IT_TrunkTree extends InteractiveTile {
	GamePanel gp;
	
	public IT_TrunkTree(GamePanel gp, int col, int row) {
		super(gp, col,row);
		// TODO Auto-generated constructor stub
		this.gp=gp;
		
		this.worldX = gp.titleSize*col;
		this.worldY = gp.titleSize*row;
		stackable = false;
		
		name = "Cây xanh";
		down1 = setup("/tile_interactive/Basic-Grass-Biom-things-1_22");
		//destructible = true;
		collision = true;
		//Set up có thể cho người chơi đi xuyên qua được
//		solidArea.x = 0;
//		solidArea.y = 0;
//		solidArea.width = 0;
//		solidArea.height = 0;
//		solidAreaDefaultX = solidArea.x;
//		solidAreaDefaultY = solidArea.y;
	}
	//Kiểm tra xem khi chặt cây có phải là cây rìu không
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrecItem = false;
			
		if(entity.currentCongCu.type == type_axe) {
			isCorrecItem = true;
		}
		return isCorrecItem;
	}
}
