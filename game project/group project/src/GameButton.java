import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


class GameButton implements ImageObserver{

private Image gbutton; //image game button, game button over, game button click
private Image gbutton_over;
private Image gbutton_click;
public int x; //image x and y
public int y;
private boolean mover; //mouse over and mouse click
private boolean mclick;
public Settings options;

	//auto image load constructor
	GameButton(String imgpath, int xtemp, int ytemp) {
		options = new Settings();
		String imgpathover = "", imgpathclick = "";
		mover = mclick = false;
		//reads image path and adds "_over" and "_click" in two other strings
		for(int i=0; i<imgpath.length(); i++) 
		{
			if(imgpath.charAt(i) == '.')
			{
				imgpathover += "_over";
				imgpathclick += "_click";
			}
			imgpathover += imgpath.charAt(i);
			imgpathclick += imgpath.charAt(i);
		}
		//loads all images
		gbutton = ImageLoad(imgpath);
		gbutton_over = ImageLoad(imgpathover);
		gbutton_click = ImageLoad(imgpathclick);
		x=xtemp; //sets image x position
		y=ytemp; //sets image y position
	}
	//constructor with parameters to not load _over and _click images
	GameButton(String imgpath, int xtemp, int ytemp, boolean imgover, boolean imgclick) {
		options = new Settings();
		String imgpathover = "", imgpathclick = "";
		mover = mclick = false;
		for(int i=0; i<imgpath.length(); i++)
		{
			if(imgpath.charAt(i) == '.')
			{
				imgpathover += "_over";
				imgpathclick += "_click";
			}
			imgpathover += imgpath.charAt(i);
			imgpathclick += imgpath.charAt(i);
		}
		
		gbutton = ImageLoad(imgpath);
		if(imgover)
			gbutton_over = ImageLoad(imgpathover);
		if(imgclick)
			gbutton_click = ImageLoad(imgpathclick);
		x=xtemp;
		y=ytemp;
	}
	int getXPos(){ //get x position of image
		return x;
	}
	int getXEnd(){ //get x position of image plus the image width
		return x+gbutton.getWidth(this);
	}
	int getYPos(){ //get y position of image
		return y;
	}
	int getYEnd(){ //get y position of image plus the image height
		return y+gbutton.getHeight(this);
	}
	Image getImage(){ //get the image
		return gbutton;
	}
	Image getImageOver(){ // get the mouse over image
		return gbutton_over;
	}
	Image getImageClick(){ // get the mouse click image
		return gbutton_click;
	}
	private void setMOver(boolean b) //sets the "mouse over the image" state
	{
		mover = b;
	}
	private void setMClick(boolean b) //sets the "mouse click the image" state
	{
		mclick = b;
	}
	private boolean getMOver() //gets the "mouse over the image" state
	{
		return mover;
	}
	private boolean getMClick() //gets the "mouse click the image" state
	{
		return mclick;
	}
	boolean onButton(int x, int y) { //checks if the mouse is on the button
		boolean pos = false;
		if(x>this.getXPos() && x<this.getXEnd())
			if(y>this.getYPos() && y<this.getYEnd())
				pos = true;
		return pos;
	}
	void mousePressed(MouseEvent evt) {
		//if mouse position is on the button, set button state boolean click true; else false
		if(onButton(evt.getX(), evt.getY())) 
			setMClick(true);
		else
			setMClick(false);
	}
	void mouseReleased(MouseEvent evt)
	{ //sets the click state boolean in each button false
		setMClick(false);
	}
	public void mouseMoved(MouseEvent evt) {
		if(onButton(evt.getX(),evt.getY()))
			setMOver(true);
		else
			setMOver(false);
	}
	void draw(Graphics g) {
		//used for clickable images
		//if mouse button is clicked on button, change image; else paint not click image
		if(!getMOver() && !getMClick())
			g.drawImage(getImage(), getXPos(), getYPos(), this);
		if(getMClick()) 
			g.drawImage(getImageClick(), getXPos(), getYPos(), this);
		else if(getMOver())
			g.drawImage(getImageOver(), getXPos(), getYPos(), this);
	}
	void drawBkgd(Graphics g) {
		//draw resized background image
		//can also be used for drawing any non-clickable images
		g.drawImage(getImage(), getXPos(), getYPos(), options.screenWidth, options.screenHeight, this);
	}

	//reads the file path, loads the image temporarily, returns that image
	public Image ImageLoad(String imagepath) { 
		File file = new File(imagepath);
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			
//			e.printStackTrace(); XXX
			System.out.println("Error loading "+imagepath);
		}
		return image;
	}
	//IGNORE
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		
		return false;
	}
}
