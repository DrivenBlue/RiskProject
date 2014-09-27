import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Help implements ImageObserver{
	
	public Settings options;
	String status;
	private GameButton sendToWiki,exit;
	
	Help()
	{
		options = new Settings();		
		sendToWiki = new GameButton("bin/images/help2.jpg",300,250,false, true);//url button
		exit = new GameButton("bin/images/exit2.jpg",0,0); // exit button
		status = "";
	}

	void draw(Graphics g)
	{
		g.fillRect(0, 0, options.screenWidth, options.screenHeight);//creates rectangle for help screen
		sendToWiki.draw(g);
		exit.draw(g);
	}
	public void mousePressed(MouseEvent e) {
		
		exit.mousePressed(e);
		sendToWiki.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		
		exit.mouseReleased(e);
		sendToWiki.mouseReleased(e);
		if(exit.onButton(e.getX(), e.getY()))
			status = "menu";
		else if (sendToWiki.onButton(e.getX(), e.getY()))
		{
			try
			{
				launchBrowser();//calls method to launch the default browser for url.
			}
			catch (Exception ex){}
		}
	}
	public void mouseMoved(MouseEvent evt){
		exit.mouseMoved(evt);
		sendToWiki.mouseMoved(evt);
	}
	

//this method calls the desktop class and tests if its supported before using it.
//this is the method that is called when the button under the help screen is clicked.
//this method redirects the button press to wikipedia for the user to get the rules of the game.
	private void launchBrowser()
	{
		
		Desktop desktop = null;
		
		if(Desktop.isDesktopSupported())
			try
		{
				desktop = Desktop.getDesktop();
				URI uri = new URI("http://en.wikipedia.org/wiki/Risk_%28game%29");
			if(desktop != null)
				desktop.browse(uri);
		}
		 catch (IOException ioe) 
		 {
		      ioe.printStackTrace();
		 } 
		 catch (URISyntaxException use) 
		 {
		      use.printStackTrace();
		 }
	}

	String getStatus() { return status; } //returns start_game value
	void setStatus(String s) { status = s; } //sets the start_game value

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		
		return false;
	}



}
