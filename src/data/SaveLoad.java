package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_HatGiong1;
import object.OBJ_HatGiong2;
import object.OBJ_Hook;
import object.OBJ_Pickaxe;
import object.OBJ_Watering;
import tile_interactive.HatGiong1_1;
import tile_interactive.HatGiong1_2;
import tile_interactive.HatGiong1_3;
import tile_interactive.HatGiong1_4;
import tile_interactive.HatGiong1_5;
import tile_interactive.HatGiong2_1;
import tile_interactive.HatGiong2_2;
import tile_interactive.HatGiong2_3;
import tile_interactive.HatGiong2_4;
import tile_interactive.HatGiong2_5;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_TrunkTree;
import tile_interactive.InteractiveTile;
import tile_interactive.Land;
import tile_interactive.PlowedLand;

public class SaveLoad  {
	
	GamePanel gp;
	public SaveLoad (GamePanel gp) {
		this.gp = gp;
		
		
	}
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		int wX = 1;
		int wY = 1;
		
		wX = wX / gp.titleSize;
		wY = wY / gp.titleSize;
		
		// Lưu ý khi có sự thay đổi về trang bị phải chú ý dòng này 
		switch (itemName) {
		case "Cây rìu": obj = new OBJ_Axe(gp);break;
		case "Rương bí ẩn ?" : obj = new OBJ_Chest(gp); break;
		case "Bap": obj = new OBJ_HatGiong1(gp);break;
		case "CaTim": obj = new OBJ_HatGiong2(gp);break;
		case "Cây cuốc": obj = new OBJ_Pickaxe(gp);break;
		case "Watering": obj = new OBJ_Watering(gp);break;
		case "Hook": obj = new OBJ_Hook(gp); break;
		case "Bắp chín": obj = new HatGiong1_5(gp,wX,wY);break;
		case "Cà Tím chín" : obj = new HatGiong2_5(gp, wX, wY); break;
		}
		
		return obj;
	}
	
	public InteractiveTile getObject1(String itemName, int wX, int wY) {
		
		InteractiveTile obj = null;
		
		wX = wX / gp.titleSize;
		wY = wY / gp.titleSize;
		
		switch (itemName) {
		case "Bap1": obj = new HatGiong1_1(gp,wX,wY);break;
		case "Bap2" : obj = new HatGiong1_2(gp,wX,wY);break;
		case "Bap3": obj = new HatGiong1_3(gp,wX,wY);break;
		case "Bap4": obj = new HatGiong1_4(gp,wX,wY);break;
		case "Bắp chín": obj = new HatGiong1_5(gp,wX,wY);break;
		case "CaTim1" : obj = new HatGiong2_1(gp, wX, wY); break;
		case "CaTim2" : obj = new HatGiong2_2(gp, wX, wY); break;
		case "CaTim3" : obj = new HatGiong2_3(gp, wX, wY); break;
		case "CaTim4" : obj = new HatGiong2_4(gp, wX, wY); break;
		case "Cà Tím chín" : obj = new HatGiong2_5(gp, wX, wY); break;
		case "Cây xanh" : obj = new IT_DryTree(gp, wX, wY); break;
		case "Cây đổ" : obj = new IT_TrunkTree(gp, wX, wY); break;
		case "Đất" : obj = new Land(gp, wX, wY); break;
		case "Đất đã đào" : obj = new PlowedLand(gp, wX, wY); break;
		}
		
		return obj;
	}
	
	
	
	public void save(String file) {
		
		try {
			ObjectOutputStream cos = new ObjectOutputStream(new FileOutputStream(new File(file)));
			
			DataStorage ds = new DataStorage();
			
			//Timer
			ds.days = gp.days;
			ds.hours = gp.hours;
			ds.minutes = gp.minutes;
			
			//Player state
			ds.level = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.coin = gp.player.coins;
			ds.exp = gp.player.exps;
			ds.nextLevelExp = gp.player.nextLevel;
			ds.selectPlayer = gp.player.selectPlayer;
			ds.playerWorldX = gp.player.worldX;
			ds.playerworldY = gp.player.worldY;
			
			
			ds.valueConsumable = new int[gp.player.inventory.size()];
			
			//Player Inventory
			for(int i = 0;i< gp.player.inventory.size();i++) {
				if(gp.player.inventory.get(i) != null) {
					ds.itemNames.add(gp.player.inventory.get(i).name);
					if(gp.player.inventory.get(i).name == "Watering") {
						ds.valueConsumable[i] = gp.player.inventory.get(i).valueConsumable;
					}
					ds.itemAmounts.add(gp.player.inventory.get(i).amount);
				}
			}
//			for(int i = 0 ; i < ds.itemNames.size();i++) {
//				Entity check = getObject(ds.itemNames.get(i));
//				if(check.name == "Watering") {
//					ds.valueConsumable[i] = gp.player.
//				}
//					
//				
//			}
			//Player Equipment
			ds.currentCongCuSlot = gp.player.getCurrentCongCuSlot();
			//System.out.println("Tổng số object có trong mảng: "+ gp.obj.length);
			//Object on Map
			ds.ObjectName = new String[gp.obj.length];
			ds.ObjectWorldX = new int[gp.obj.length];
			ds.objectWorldY = new int[gp.obj.length];
			//Interrac on Map
			ds.InteracName = new String[gp.objDig.length];
			ds.InteracWorldX = new int[gp.objDig.length];
			ds.InteracWorldY = new int [gp.objDig.length];
			ds.water = new int[gp.objDig.length];
			
			//Object
			for(int i = 0;i < gp.obj.length;i++) {
				if(gp.obj[i] == null) {
					ds.ObjectName[i] = "NA";
				}
				else {
					//Object
					ds.ObjectName[i] = gp.obj[i].name;
					ds.ObjectWorldX[i] = gp.obj[i].worldX;
					ds.objectWorldY[i] = gp.obj[i].worldY;
				}
			}
			
			//Interactive
			for(int i = 0; i < gp.objDig.length;i++) {
				//System.out.println("Trước khi lưu Interac có: "+ gp.objDig[i]);
				if(gp.objDig[i] == null) {
					ds.InteracName[i]= "NA";
				}
				else if(gp.objDig[i] != null) {
					//Interac
					ds.InteracName[i] = gp.objDig[i].name;
					ds.InteracWorldX[i] = gp.objDig[i].worldX;
					ds.InteracWorldY[i] = gp.objDig[i].worldY;
					ds.water[i] = gp.objDig[i].water;
				}
			}
			//Write the DataStorage object
			cos.writeObject(ds);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi khi save!");
		}
	}
	public void load(String file) {
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(file)));
			
			//Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			//Timer
			gp.days = ds.days;
			gp.hours = ds.hours;
			gp.minutes = ds.minutes;
			//Player State
			gp.player.level = ds.level;
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.coins = ds.coin;
			gp.player.exps = ds.exp;
			gp.player.nextLevel = ds.nextLevelExp;
			gp.player.selectPlayer = ds.selectPlayer;
			
			//Location player
			gp.player.worldX = ds.playerWorldX;
			gp.player.worldY = ds.playerworldY;
			
			
			//Player Inventory 
			gp.player.inventory.clear();
			
			for(int i = 0; i <ds.itemNames.size();i++) {
				if(ds.itemNames.get(i) != null) {
					gp.player.inventory.add(getObject(ds.itemNames.get(i)));
					//System.out.println("Load data thứ "+ i +"Voi noi dung "+ ds.itemNames.get(i));
					Entity check = getObject(ds.itemNames.get(i));
					if(check != null) {
						if(check.name == "Watering") {
							gp.player.inventory.get(i).valueConsumable = ds.valueConsumable[i];
						}
						gp.player.inventory.get(i).amount = ds.itemAmounts.get(i);
					}
				}
				
			}
			//Player Equipment
			gp.player.currentCongCu = gp.player.inventory.get(ds.currentCongCuSlot);
			
			//Object on map (Entity)
			
			for(int i  = 0 ; i < ds.ObjectName.length;i++) {
				if(ds.ObjectName[i].equals("NA")) {
					gp.obj[i]= null;
				}
				else {
					gp.obj[i] = getObject(ds.ObjectName[i]);
					gp.obj[i].worldX = ds.ObjectWorldX[i];
					gp.obj[i].worldY = ds.objectWorldY[i];
				}
			}
			
			//System.out.println("Tổng số interac có trong mảng o data: "+ ds.InteracName.length);
			for(int i = 0; i< ds.InteracName.length;i++) {
				if(ds.InteracName[i].equals("NA")) {
					gp.objDig[i] = null;
				}
				else {
					gp.objDig[i] = getObject1(ds.InteracName[i], ds.InteracWorldX[i], ds.InteracWorldY[i]);
					gp.objDig[i].worldX = ds.InteracWorldX[i];
					gp.objDig[i].worldY = ds.InteracWorldY[i];
					gp.objDig[i].water = ds.water[i];
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi khi load data!"+ e.getMessage());
		}
	}
	public void deleteSaveFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("Xóa dữ liệu lưu trữ thành công!");
            } else {
                System.out.println("Không thể xóa dữ liệu lưu trữ.");
            }
        } else {
            System.out.println("Không tìm thấy tệp lưu trữ để xóa.");
        }
    }
	
	//Kiểm tra vùng nhớ account này đã có chứa
	public boolean doesFileExist(String userName, String password) {
        File file = new File(userName + "_" + password + ".dat");
        return file.exists();
    }
	//Tạo vùng nhớ cho account mới
	public void createFileForUser(String userName, String password) {
        File file = new File(userName + "_" + password + ".dat");
        try {
            if (file.createNewFile()) {
                System.out.println("Tạo file thành công!");
            } else {
                System.out.println("File đã tồn tại!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	//Xóa toàn bộ nội dung trước đó nếu người chơi chơi mới
	public void clearFileContent(String file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(new byte[0]); // Ghi nội dung trống để xóa dữ liệu trong file
            fileOutputStream.close();
            System.out.println("Đã xóa nội dung của file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	//Kiểm tra file có dữ liệu không
	public boolean isFileEmpty(String file1) {
        File file = new File(file1);
        System.out.println("So luong phan tu trong file "+ file.length());
        return file.length() == 0;
    }
}
