import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/**
 * Group Project
 * @param args
 * @throws Throwable
 * 
 * Credits for backgrounds go to unknown.
 * 
 * 
 * DO NOT PUT ENDLESS WHILE LOOP IN PROGRAM
 *  
 * **********EXTRA THINGS NEEDED**********
 * 
 * 
 * ************* CHANGE LOG **************
 * V1.21
 * Help menu put in.
 * Screen switching priorities now in Menu class.
 * New button images.
 * Fixed small error with loading images.
 * Removed exit on first screen.
 * Extra images removed from package
 * Players turn added to bottom of screen
 * Change made in MapButton to correct labeling error
 * Add total armies to bottom of screen for each player.
 * Changed multiple if statements to SWITCH statements.
 * Updated players to no longer be in stack if out of the game.
 * 
 * V1.2
 * World map included.
 * Now in workspace format.
 * Dice fixed.
 * DiceImage added.
 * drawBkgd changed to use scrn width and height from settings.
 * Files cleanup.
 * 
 * V1.1
 * GroupProject, Menu, Game, and Game button work.
 * Dice still needs to be worked on.
 * Added Settings interface for resolution.
 * Compile from anywhere.
 * Can start game and return to menu.
 * Simplified functions in Menu and added some more.
 * All image painting on graphics is now done in GameButton.
 * 
 * V1.0
 * GroupProject, Menu, and GameButton all work.
 * No known errors yet.
 * Double buffer included.
 * Compile only from GroupProject.
 * Game and Dice classes currently have yet to be worked on.
 * 
 */
@SuppressWarnings("serial")
class GCanvas extends Canvas
{
	public Settings options;
	Menu mainMenu; //menu
	Graphics2D buffScrn = null; //back buffer
	Image offscreen = null; //back buffer
	public boolean start_game = false, help_menu = false;
	String status;
	AudioClip music;

	GCanvas(){
		options = new Settings();
//      creates the menu 
		mainMenu = new Menu();
//		music = getAudioClip(getCodeBase(), "Snowstorm.wav");
//		music.play();
	}
	public void paint(Graphics g)
	{
		options = mainMenu.options;//pulls options from GCanvas
		offscreen = this.createImage(options.screenWidth,options.screenHeight); 
		buffScrn = (Graphics2D) offscreen.getGraphics(); 
		buffScrn.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		mainMenu.draw(buffScrn); // start menu draw function
		//used for double buffer - DO NOT CHANGE
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(offscreen, 0, 0, null);
	}
	//used for double buffer - DO NOT CHANGE
	public void update(Graphics g)
	{
		paint(g);
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e){
		mainMenu.mousePressed(e);
		repaint(); //updates graphics
	}
	public void mouseReleased(MouseEvent e){
		mainMenu.mouseReleased(e); //call mouseReleased in mainMenu
		repaint();
	}
	public void mouseMoved(MouseEvent arg0) {
		mainMenu.mouseMoved(arg0);
	}
}	
public class GroupProject implements MouseListener,MouseMotionListener{

	GCanvas canvas;
	JFrame game;
	public Settings options;

	public static void main(String arg[]){new GroupProject();}
	
	//initializes 
	GroupProject() {
		options = new Settings();
		game = new JFrame("Risk");
	    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    canvas = new GCanvas();     // create drawing canvas
	    game.add(canvas); 
	    game.setVisible(true);// add to frame and show		//sets window size and adds listener for mouse to this layer
		game.setBounds(0,0,options.screenWidth+16,options.screenHeight+38);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
//		AudioClip music = Applet.class.getClass().getMethod("getAudioClip", (new URL("bin/"), "Snowstorm.wav"));
//		music.play();
	}
	@Override
	public void mouseClicked(MouseEvent e) {} //if mouse button is clicked

	@Override
	public void mouseEntered(MouseEvent e) {} //if mouse enters the app

	@Override
	public void mouseExited(MouseEvent e) {} //if mouse leaves the app

	@Override
	public void mousePressed(MouseEvent e) { //if mouse button is pressed
		canvas.mousePressed(e);
		canvas.repaint(); //updates graphics
	}

	@Override
	public void mouseReleased(MouseEvent e){ //if mouse button is released
		canvas.mouseReleased(e); //call mouseReleased in mainMenu
		canvas.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		options = canvas.options;
		game.setBounds(0,0,options.screenWidth+16,options.screenHeight+38);
		canvas.mouseMoved(arg0);
		canvas.repaint();
	}
}


