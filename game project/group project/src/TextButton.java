	import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class TextButton implements ImageObserver{

	public Settings options;
	public String name, align;
	public int xpos, xend; //image x and y
	public int ypos, yend;
	Font font;
	FontMetrics fm;
	private boolean mover; //mouse over and mouse click
	private boolean mclick;
	public boolean select;
	private boolean bkgd, box;

		//auto image load constructor
	TextButton(String text, int xtemp, int ytemp, float textsize) {
		options = new Settings();
		select = false;
		bkgd=box=true;
		Font temp = null;
		try {
			temp = Font.createFont(Font.TRUETYPE_FONT, options.textfont);
		} catch (FontFormatException e) {
			System.out.println(options.textfont);
		} catch (IOException e) {
			System.out.println(options.textfont);
		}
		font = temp.deriveFont(textsize);
		setName(text);
		xpos=xtemp; //sets image x position
		yend=ytemp; //sets image y position
	}		
	TextButton(String text, String pos, int ytemp, float textsize, boolean bkgd, boolean box) {
		options = new Settings();
		select = false;
		this.bkgd = bkgd;
		this.box = box;
		Font temp = null;
		try {
			temp = Font.createFont(Font.TRUETYPE_FONT, options.textfont);
		} catch (FontFormatException e) {
			System.out.println(options.textfont);
		} catch (IOException e) {
			System.out.println(options.textfont);
		}
		font = temp.deriveFont(textsize);
		setName(text);
		align = pos;
		yend = ytemp;
	}
	int getXPos(){ //get x position of image
		return xpos;
	}
	int getXEnd(){ //get x position of image plus the image width
		return xend;
	}
	int getYPos(){ //get y position of image
		return ypos;
	}
	int getYEnd(){ //get y position of image plus the image height
		return yend;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
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
			mover = (true);
		else
			mover = (false);
	}
	void draw(Graphics g) {
		//used for clickable text
		//if mouse button is clicked on button, change color; else change to another color
	    g.setFont(font);
	    fm = g.getFontMetrics();
	    /* XXX
	    System.out.println(fm.getAscent()+" "+fm.getDescent()+" "+fm.getHeight()+" "+fm.getLeading()+" "+ypos+" "+yend);
	    g.drawLine(400, ypos, 800, ypos);
	    g.drawLine(400, ypos+fm.getDescent(), 800, ypos+fm.getDescent());
	    g.drawLine(400, ypos+fm.getDescent()+fm.getLeading(), 800, ypos+fm.getDescent()+fm.getLeading());
	    g.drawLine(400, ypos+fm.getDescent()*2, 800, ypos+fm.getDescent()*2);
	    g.drawLine(400, ypos+fm.getDescent()*2+fm.getLeading(), 800, ypos+fm.getDescent()*2+fm.getLeading());
	    g.drawLine(400, ypos+fm.getAscent(), 800, ypos+fm.getAscent());
	    g.drawLine(400, ypos+fm.getAscent()+fm.getDescent(), 800, ypos+fm.getAscent()+fm.getDescent());
	    g.drawLine(400, ypos+fm.getAscent()+fm.getDescent()+fm.getLeading(), 800, ypos+fm.getAscent()+fm.getDescent()+fm.getLeading());
	     XXX*/
	    if(align == "Center")
	    {
			xpos = (options.screenWidth/2)-(fm.stringWidth(getName())/2);
		    xend=xpos+fm.stringWidth(getName());
	    }
	    else
	    {
		    xend=xpos+fm.stringWidth(getName());
	    }
	    ypos=yend-fm.getHeight();

	    g.setColor(options.buttonbkgd);
	    if(bkgd)
	    	g.fillRect(xpos-5, ypos, fm.stringWidth(getName())+10, fm.getHeight());
		if(!mover && !mclick)
			g.setColor(options.button);
		if(mclick || select) 
			g.setColor(options.buttonclick);
		else if(mover)
			g.setColor(options.buttonover);
		
		if(box)
			g.drawRect(xpos-5, ypos, fm.stringWidth(getName())+10, fm.getHeight());
		g.drawString(getName(),xpos,yend-fm.getDescent()-fm.getLeading());
	}	
	void drawSpecial(Graphics g) {
		//used for clickable text
		//if mouse button is clicked on button, change color; else change to another color
	    g.setFont(font);
	    fm = g.getFontMetrics();
	    //aligns center
	    if(align == "Center")
	    {
			xpos = (options.screenWidth/2)-(fm.stringWidth(getName())/2);
		    xend=xpos+fm.stringWidth(getName());
	    }
	    else
	    {
		    xend=xpos+fm.stringWidth(getName());
	    }
	    ypos=yend-fm.getHeight();

		g.drawString(getName(),xpos,yend-fm.getDescent()-fm.getLeading());
	}
	//IGNORE
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		
		return false;
	}
}
