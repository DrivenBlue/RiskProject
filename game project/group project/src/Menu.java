import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Random;



public class Menu implements ImageObserver{
	

private GameButton bkgd; //game buttons
private GameButton start;
private GameButton help;
private TextButton optn;
//private boolean start_game, help_menu;
String status;
Game mainGame; //game
Help mainHelp;
Options mainOptn;
Settings options;
Random num = new Random();

	Menu() {
		options = new Settings();
		mainGame = new Game();
		mainHelp = new Help();
		mainOptn = new Options();
		options = mainOptn.theoptions;		
		//image file path, x position, y position, search for _over, search for _click
		if(num.nextBoolean())
			bkgd = new GameButton("bin/images/background1.jpg",0,0,false,false); // background
		else
			bkgd = new GameButton("bin/images/background2.jpg",0,0,false,false); // background

		start = new GameButton("bin/images/start2.jpg",options.screenWidth/4-100,0); // start button
		help = new GameButton("bin/images/help2.jpg",options.screenWidth*3/4-100,0); // help button
		optn = new TextButton("Options","Center", options.screenHeight*19/20,options.textsize,true,true);
		status = "menu";
	}
	// draw/paint function for Menu class
	void draw(Graphics g)
	{
		options = mainOptn.theoptions;
		mainGame.options = options;
		mainHelp.options = options;
		bkgd.options = options;
		start.options = options;
		help.options = options;
		optn.options = options;

		start.x = options.screenWidth/4-100;
		help.x = options.screenWidth*3/4-100;
		optn.yend = options.screenHeight*19/20;
		
		Font risk = null;
		if(status.equals("game"))
			mainGame.draw(g); // start game draw function
		if(status.equals("help"))
			mainHelp.draw(g);
		if(status.equals("optn"))
			mainOptn.draw(g);
		if(status.equals("menu")) {
			//draws background
			bkgd.drawBkgd(g);
			start.draw(g);
			help.draw(g);
			optn.draw(g);
			g.setColor(options.title);
			try {
				risk = Font.createFont(Font.TRUETYPE_FONT, options.textfont);
			} catch (FontFormatException e) {
				System.out.println("Error loading "+options.textfont.toString());
			} catch (IOException e) {
				System.out.println("Error loading "+options.textfont.toString());
			}
			Font rm = risk.deriveFont((float)80);
		    g.setFont(rm);
		    FontMetrics fm = g.getFontMetrics();
		    int y = fm.getHeight();
		    // center a line of text
		    String title = "Risk";
		    int x = (options.screenWidth/2)-(fm.stringWidth(title)/2);
		    y=fm.getHeight();// move down one line of text		 
			g.drawString(title,x,y);
		}

	}
	// mouse pressed function for Menu class
	// DOES NOT EXTEND/IMPLEMENT/OVERLOAD MOUSELISTENER
	void mousePressed(MouseEvent evt) {
		//calls mousePressed in GameButton class
		if(status.equals("game"))
			mainGame.mousePressed(evt);
		if(status.equals("help"))
			mainHelp.mousePressed(evt);
		if(status.equals("optn"))
			mainOptn.mousePressed(evt);
		if(status.equals("menu"))
		{
			start.mousePressed(evt);
			help.mousePressed(evt);
			optn.mousePressed(evt);
		}
	}
	//mouse release function for Menu class
	//DOES NOT EXTEND/IMPLEMENT/OVERLOAD MOUSELISTENER
	void mouseReleased(MouseEvent evt)
	{ //sets the click state boolean in each button false
		if(status.equals("menu"))
		{
			start.mouseReleased(evt);
			help.mouseReleased(evt);
			optn.mouseReleased(evt);
			if(start.onButton(evt.getX(), evt.getY())) 
				status = "game";
			if(help.onButton(evt.getX(), evt.getY()))
				status = "help";
			if(optn.onButton(evt.getX(), evt.getY()))
				status = "optn";
		}
		if(status.equals("game"))
		{
			mainGame.mouseReleased(evt);
			if(mainGame.getStatus().equals("menu")) {
				status = "menu";
				mainGame.setStatus("");
			}
		}
		if(status.equals("help"))
		{
			mainHelp.mouseReleased(evt);
			if(mainHelp.getStatus().equals("menu")) {
				status = "menu";
				mainHelp.setStatus("");
			}
		}
		if(status.equals("optn"))
		{
			mainOptn.mouseReleased(evt);
			if(mainOptn.getStatus().equals("menu")) {
				status = "menu";
				mainOptn.setStatus("");
			}
		}
	}
	public void mouseMoved(MouseEvent evt) {
		if(status.equals("game"))
			mainGame.mouseMoved(evt);
		if(status.equals("help"))
			mainHelp.mouseMoved(evt);
		if(status.equals("optn"))
			mainOptn.mouseMoved(evt);
		if(status.equals("menu"))
		{
			start.mouseMoved(evt);
			help.mouseMoved(evt);
			optn.mouseMoved(evt);
		}
	}

	//IGNORE
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		
		return false;
	}
}
