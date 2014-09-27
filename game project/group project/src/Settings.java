import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;


public class Settings {
	int screenWidth = 1280;
	int screenHeight = 800;
	Color[] playerColor = new Color[10];
	Color title = new Color(24,196,195);
	Color button = new Color(24,196,195);
	Color buttonclick = new Color(0,0,255);
	Color buttonover = new Color(12,98,255);
	Color buttonbkgd = new Color(100,100,100);
	Color system = new Color(0, 255, 0);
	final float textsize = 50;
	final int endgameWait = 5000;
	final float endgameTextSize = 70;
		
	File textfont = new File("bin/fonts/CGF Locust Resistance.ttf");
		
	Font optnfont;
	final float optnfontsize = 30;
	
	
	final int mapPlaces = 29;
	int beginArmies = 5; // starting number of armies per player.
	int maxPlayers = 2; //will work for 2-4 players
	int roundsBeforeNewArmies = 2; //number of rounds before bonus armies are added
	int armiesPerRound = 4; // number of armies to add per round
	

	Settings(){
		playerColor[0] = Color.RED;
		playerColor[1] = Color.BLUE;
		playerColor[2] = Color.CYAN;
		playerColor[3] = Color.DARK_GRAY;
		playerColor[4] = Color.GRAY;
		playerColor[5] = Color.GREEN;
		playerColor[6] = Color.LIGHT_GRAY;
		playerColor[7] = Color.MAGENTA;
		playerColor[8] = Color.ORANGE;
		playerColor[9] = Color.PINK;
		Font temp = null;
		try {
			temp = Font.createFont(Font.TRUETYPE_FONT, new File("bin/fonts/CGF Locust Resistance.ttf"));
		} catch (FontFormatException e) {
			System.out.println(textfont);
		} catch (IOException e) {
			System.out.println(textfont);
		}
		optnfont = temp.deriveFont(optnfontsize);

	}
}
