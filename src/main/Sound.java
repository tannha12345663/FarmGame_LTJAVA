package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL [] = new URL[30];
	
	
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
		soundURL[9] = getClass().getResource("/sound/nhatitem.wav");
		soundURL[10] = getClass().getResource("/sound/vachamcua.wav");
		
		//Từ số 20 trở đi sẽ là hiệu ứng âm thanh
		//Đào đất
		soundURL[20] = getClass().getResource("/soundAnimation/DaoDat2.wav");
	}
	
	public void setFile(int i) {
		
		try {
			//Khai báo biến Audio và xuất ra âm thanh
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
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
	
	
	
}
