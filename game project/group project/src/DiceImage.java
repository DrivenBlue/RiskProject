import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
	
public class DiceImage implements ImageObserver{

	private Image dimage; //image game button, game button over, game button click
	private int x; //image x and y
	private int y;

	//auto image load constructor
	DiceImage(String imgpath, int xtemp, int ytemp) {
		//loads images
		dimage = ImageLoad(imgpath);
		x=xtemp; //sets image x position
		y=ytemp; //sets image y position
	}
	int getXPos(){ return x; }//get x position of image
	void setXPos(int xTemp){ x = xTemp; }
	//get x position of image plus the image width
	int getXEnd(){ return x+dimage.getWidth(this); }
	
	int getYPos(){ return y; }//get y position of image
	void setYPos(int yTemp){ y = yTemp; }
	//get y position of image plus the image height
	int getYEnd(){ return y+dimage.getHeight(this); }
	
	Image getImage(){ //get the image
		return dimage;
	}
	void draw(Graphics g) {
		//used for images
		g.drawImage(getImage(), getXPos(), getYPos(), this);
	}

	//reads the file path, loads the image temporarily, returns that image
	public Image ImageLoad(String imagepath) { 
		File file = new File(imagepath);
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			
//			e.printStackTrace();
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
