import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


class MapButton implements ImageObserver{

	public Settings options;
	private Image gbutton; //image game button, game button over, game button click
	private Image gbutton_over;
	private Image gbutton_click;
	private int x; //image x and y
	private int y;
	private boolean mover; //mouse over and mouse click
	private boolean mclick;
	private String owner;
	private int armies;

	//auto image load constructor
	MapButton(String imgpath, int xtemp, int ytemp) {
		options = new Settings();
		String imgpathover = "", imgpathclick = "";
		mover = mclick = false;
		owner = "";
		armies = 0;
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
	MapButton(String imgpath, int xtemp, int ytemp, boolean imgover, boolean imgclick) {
		options = new Settings();
		String imgpathover = "", imgpathclick = "";
		mover = mclick = false;
		owner = "";
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
	int getArmies(){return armies;}
	void addArmies(){armies++;}
	void subArmies(int i){
		if(armies-i<=0)
		{
			armies=0;
			owner="";
		}
		else 
			armies-=i;
	}
	void setArmies(int i){armies=i;}
	String getOwner() { return owner; }
	void setOwner(String s) { owner = s; }
	boolean testOwner(String s)
	{
		boolean b = false;
		if(owner == "" || owner == s)
			b = true;
		return b;
	}
	boolean checkOwner(MouseEvent evt, String s) { 
		boolean b;
		if((owner.equals("") || owner.equals(s)) && onButton(evt.getX(),evt.getY())) {
			owner = s;
			b = true;
		}
		else
			b = false;
		return b;
	}
	boolean checkOwner2(MouseEvent evt, String s) { 
		boolean b;
		if((owner.equals("") || owner.equals(s)) && onButton(evt.getX(),evt.getY())) {
			b = true;
		}
		else
			b = false;
		return b;
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
	boolean onButton(int x, int y) { //checks if the mouse is on the button
		boolean pos = false;
		if(x>this.getXPos() && x<this.getXEnd())
			if(y>this.getYPos() && y<this.getYEnd())
				pos = true;
		return pos;
	}
	void mousePressed(MouseEvent evt) {
		//if mouse position is on the button, set button state boolean click true; else false
		if(onButton(evt.getX(),evt.getY())) 
			mclick = (true);
		else
			mclick = (false);
	}
	void mouseReleased(MouseEvent evt)
	{ //sets the click state boolean in each button false
		mclick = (false);
	}
	public void mouseMoved(MouseEvent evt) {
		if(onButton(evt.getX(),evt.getY()))
			mover = true;
		else
			mover = false;
	}
	void draw(Graphics g) {
		g.setColor(options.system);
		//used for clickable images
		//if mouse button is clicked on button, change image; else paint not click image
		if(!mover && !mclick)
			g.drawImage(getImage(), getXPos(), getYPos(), this);
		if(mclick) {
			g.drawImage(getImageClick(), getXPos()+4, getYPos()+4, this);
			g.drawRect(x+3, y+3, 13, 13);
		}
		else if(mover){
//			g.drawImage(getImageOver(), getXPos(), getYPos(), this);
			g.drawRect(x-1, y-1, 21, 21);
			g.drawImage(getImage(), getXPos(), getYPos(), this);
		}
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

//						e.printStackTrace(); XXX
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
