package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL [] = new URL[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume ;
	
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/Caketown-1.wav");
		soundURL[1] = getClass().getResource("/sound/Soliloquy.wav");
		soundURL[2] = getClass().getResource("/sound/TownTheme.wav");
		
		soundURL[3] = getClass().getResource("/sound/hoanthanh2.wav");
		soundURL[4] = getClass().getResource("/sound/hoanthanhnv.wav");
		soundURL[5] = getClass().getResource("/sound/mixkit-electronic-retro-block-hit-2185.wav");
		soundURL[6] = getClass().getResource("/sound/mixkit-explainer-video-game-alert-sweep-236.wav");
		soundURL[7] = getClass().getResource("/sound/mixkit-game-flute-bonus-2313.wav");
		soundURL[8] = getClass().getResource("/sound/mixkit-small-hit-in-a-game-2072.wav");
		
		soundURL[10] = getClass().getResource("/sound/vachamcua.wav");
		
		//Từ số 20 trở đi sẽ là hiệu ứng âm thanh
		//Nhặt item
		soundURL[9] = getClass().getResource("/sound/nhatitem.wav");
		//Đào đất
		soundURL[20] = getClass().getResource("/soundAnimation/DaoDat2.wav");
		//Lên level
		soundURL[21] = getClass().getResource("/soundAnimation/levelUp.wav");
		//cursor 
		soundURL[22] = getClass().getResource("/soundAnimation/cursor.wav");
		//Trang bị item
		soundURL[23] = getClass().getResource("/soundAnimation/equipItem.wav");
		//Gieo hạt
		soundURL[24] = getClass().getResource("/soundAnimation/gieoHat.wav");
		//Chặt cây
		soundURL[25] = getClass().getResource("/soundAnimation/cuttree.wav");
		//Bẫy 
		soundURL[26] = getClass().getResource("/soundAnimation/animalTrap.wav");
	}
	
	public void setFile(int i) {
		
		try {
			//Khai báo biến Audio và xuất ra âm thanh
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		// Lặp lại âm thanh nền liên tục
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		//Khi muốn dùng âm thanh
		clip.stop();
	}
	public void checkVolume() {
		
		switch (volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		fc.setValue(volume);
	}
	
	
}
