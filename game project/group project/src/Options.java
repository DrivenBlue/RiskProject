import java.awt.Graphics;
import java.awt.event.MouseEvent;


public class Options {

	Settings theoptions;
	private GameButton exit;
	private GameButton bkgd;
	String status;
	TextButton[][] optnbuttons;
	TextButton[] resolution;
	
	Options(){
		status = "";
		theoptions = new Settings();
		exit = new GameButton("bin/images/exit2.jpg", theoptions.screenWidth * 3 / 4, 0);
		bkgd = new GameButton("bin/images/background2.jpg",0,0,false,false);
		resolution = new TextButton[6];
		optnbuttons = new TextButton[4][10];
		resolution[0] = new TextButton("800x600",theoptions.screenWidth*2/30,theoptions.screenHeight*13/16,theoptions.optnfontsize);
		resolution[1] = new TextButton("1024x640",theoptions.screenWidth*11/30,theoptions.screenHeight*13/16,theoptions.optnfontsize);
		resolution[2] = new TextButton("1280x800",theoptions.screenWidth*22/30,theoptions.screenHeight*13/16,theoptions.optnfontsize);
		resolution[3] = new TextButton("1280x1024",theoptions.screenWidth*2/30,theoptions.screenHeight*14/16,theoptions.optnfontsize);
		resolution[4] = new TextButton("1400x900",theoptions.screenWidth*11/30,theoptions.screenHeight*14/16,theoptions.optnfontsize);
		resolution[5] = new TextButton("1680x1050",theoptions.screenWidth*22/30,theoptions.screenHeight*14/16,theoptions.optnfontsize);
		for(int y=0;y<10;y++){
			optnbuttons[0][y] = new TextButton(Integer.toString(y+1),theoptions.screenWidth*(2*(y+1))/30,theoptions.screenHeight*5/16,theoptions.optnfontsize);	
			optnbuttons[1][y] = new TextButton(Integer.toString(5*(y+1)),theoptions.screenWidth*(2*(y+1))/30,theoptions.screenHeight*7/16,theoptions.optnfontsize);	
			optnbuttons[2][y] = new TextButton(Integer.toString(y),theoptions.screenWidth*(2*(y+1))/30,theoptions.screenHeight*9/16,theoptions.optnfontsize);	
			optnbuttons[3][y] = new TextButton(Integer.toString(y+1),theoptions.screenWidth*(2*(y+1))/30,theoptions.screenHeight*11/16,theoptions.optnfontsize);	
		}
	}
	public void draw(Graphics g){
		bkgd.drawBkgd(g);
		exit.draw(g);
		g.setFont(theoptions.optnfont);
		g.drawString("Max Players", theoptions.screenWidth*1/20, theoptions.screenHeight*2/8);
		g.drawString("Beginning Armies", theoptions.screenWidth*1/20, theoptions.screenHeight*3/8);
		g.drawString("Armies Per Round", theoptions.screenWidth*1/20, theoptions.screenHeight*4/8);
		g.drawString("Rounds Before New Armies", theoptions.screenWidth*1/20, theoptions.screenHeight*5/8);
		g.drawString("Resolution   DO NOT USE", theoptions.screenWidth*1/20, theoptions.screenHeight*6/8);
		for(int i=0;i<6;i++){
			if(resolution[i].getName().equals(new String(theoptions.screenWidth+"x"+theoptions.screenHeight)))
				resolution[i].select = true;
			else
				resolution[i].select = false;
			resolution[i].draw(g);
		}
		for(int x=0;x<4;x++)
			for(int y=0;y<10;y++){
				if(optnbuttons[0][y].getName().equals(Integer.toString(theoptions.maxPlayers)))
					optnbuttons[0][y].select = true;
				else
					optnbuttons[0][y].select = false;
				if(optnbuttons[1][y].getName().equals(Integer.toString(theoptions.beginArmies)))
					optnbuttons[1][y].select = true;
				else
					optnbuttons[1][y].select = false;
				if(optnbuttons[2][y].getName().equals(Integer.toString(theoptions.armiesPerRound)))
					optnbuttons[2][y].select = true;
				else
					optnbuttons[2][y].select = false;
				if(optnbuttons[3][y].getName().equals(Integer.toString(theoptions.roundsBeforeNewArmies)))
					optnbuttons[3][y].select = true;
				else
					optnbuttons[3][y].select = false;
				optnbuttons[x][y].draw(g);
			}
	}
	public void mousePressed(MouseEvent evt){
		exit.mousePressed(evt);
		for(int i=0;i<6;i++)
			resolution[i].mousePressed(evt);
		for(int x=0;x<4;x++)
			for(int y=0;y<10;y++)
				optnbuttons[x][y].mousePressed(evt);
	}
	public void mouseReleased(MouseEvent evt){
		exit.mouseReleased(evt);
		for(int i=0;i<6;i++)
			resolution[i].mouseReleased(evt);
		for(int x=0;x<4;x++)
			for(int y=0;y<10;y++)
				optnbuttons[x][y].mouseReleased(evt);
		for(int x=0;x<4;x++)
			for(int y=0;y<10;y++){
				if(optnbuttons[0][y].onButton(evt.getX(), evt.getY()))
					theoptions.maxPlayers = Integer.parseInt(optnbuttons[0][y].getName());
				if(optnbuttons[1][y].onButton(evt.getX(), evt.getY()))
					theoptions.beginArmies = Integer.parseInt(optnbuttons[1][y].getName());
				if(optnbuttons[2][y].onButton(evt.getX(), evt.getY()))
					theoptions.armiesPerRound = Integer.parseInt(optnbuttons[2][y].getName());
				if(optnbuttons[3][y].onButton(evt.getX(), evt.getY()))
					theoptions.roundsBeforeNewArmies = Integer.parseInt(optnbuttons[3][y].getName());
			}
		for(int i=0;i<6;i++)
			if(resolution[i].onButton(evt.getX(), evt.getY())){
				String temp = resolution[i].getName();
				String w,h;
				int xloc;
				xloc = temp.indexOf("x");
				w = temp.substring(0, xloc);
				h = temp.substring(xloc+1, temp.length());
				theoptions.screenHeight = Integer.parseInt(h);
				theoptions.screenWidth = Integer.parseInt(w);
			}
					
		if(exit.onButton(evt.getX(), evt.getY()))
			status = "menu";
	}
	public void mouseMoved(MouseEvent evt){
		exit.mouseMoved(evt);
		for(int i=0;i<6;i++)
			resolution[i].mouseMoved(evt);
		for(int x=0;x<4;x++)
			for(int y=0;y<10;y++)
				optnbuttons[x][y].mouseMoved(evt);
	}
	public void setStatus(String s) {
		status = s;
	}
	public Object getStatus() {
		return status;
	}
}
