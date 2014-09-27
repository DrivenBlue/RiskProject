import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Stack;

/**
 * 
 * Stage 0 Reset
 * 
 * Stage 1 Place armies
 * 
 * Stage 2 Fight 
 * 
 * Stage 3 add more troops after Settings.options.newArmiesRounds
 * 
 */



public class Game implements ImageObserver{

	public Settings options;
	private GameButton endturn, exit, bkgd;
	private MapButton[] AreaButton;
	private TextButton EndGame, armiesinfo, turninfo;
	private Dice dice;
	// private boolean restart;
	String status;
	int stage;
	int rounds;
	boolean endgame = false;
	Stack<MapButton> process = new Stack<MapButton>();
	Stack<Players> turn = new Stack<Players>();
	int playerturn = 0;


	Game() {
		options = new Settings();

		// image file path, x position, y position
		bkgd = new GameButton("bin/images/world_map.jpg", 0, 0, false, false);
		endturn = new GameButton("bin/images/endturn.png", options.screenWidth * 1 / 2, 0);
		exit = new GameButton("bin/images/exit2.jpg", options.screenWidth * 3 / 4, 0); // exit button
		dice = new Dice();
		turninfo = new TextButton("", "Center", options.screenHeight, options.optnfontsize,false,false);
		armiesinfo = new TextButton("", 0, options.screenHeight, options.optnfontsize);
		status = "";
		stage = 0;
		AreaButton = new MapButton[options.mapPlaces];

		for (int i = 0; i < options.maxPlayers; i++)
		{
			turn.push(newPlayer());  //push new players onto the stack
			turn.elementAt(i).name = new String("P" + Integer.toString(i + 1));
		}
		
		AreaButton[0] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 86 / 100, options.screenHeight * 154 / 640, false, true); // Asia. Purple.
		AreaButton[1] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 83 / 100, options.screenHeight * 467 / 640, false, true); // Australia. purple.
		AreaButton[2] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 78 / 100, options.screenHeight * 450 / 640, false, true); // Australia. grey.
		AreaButton[3] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 52 / 100, options.screenHeight * 430 / 640, false, true); // Africa. purple.
		AreaButton[4] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 53 / 100, options.screenHeight * 385 / 640, false, true); // Africa. orange.
		AreaButton[5] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 53 / 100, options.screenHeight * 340 / 640, false, true); // Africa. grey.
		AreaButton[6] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 46 / 100, options.screenHeight * 330 / 640, false, true); // Africa. brown.
		AreaButton[7] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 48 / 100, options.screenHeight * 275 / 640, false, true); // Africa. yellow.
		AreaButton[8] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 49 / 100, options.screenHeight * 225 / 640, false, true); // Europe. orange.
		AreaButton[9] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 46 / 100, options.screenHeight * 200 / 640, false, true); // Europe. brown.
		AreaButton[10] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 55 / 100, options.screenHeight * 205 / 640, false, true); // Europe. Gray.
		AreaButton[11] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 57 / 100, options.screenHeight * 270 / 640, false, true); // MiddleEast. purple.
		AreaButton[12] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 66 / 100, options.screenHeight * 250 / 640, false, true); // Asia. orange.
		AreaButton[13] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 70 / 100, options.screenHeight * 300 / 640, false, true); // Asia. green.
		AreaButton[14] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 76 / 100, options.screenHeight * 230 / 640, false, true); // Asia. yellow.
		AreaButton[15] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 68 / 100, options.screenHeight * 160 / 640, false, true); // Russia.brown
		AreaButton[16] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 75 / 100, options.screenHeight * 370 / 640, false, true); // Phillippines.Black.
		AreaButton[17] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 58 / 100, options.screenHeight * 425 / 640, false, true); // Africa. black.
		AreaButton[18] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 31 / 100, options.screenHeight * 410 / 640, false, true); // South America.Orange.
		AreaButton[19] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 30 / 100, options.screenHeight * 480 / 640, false, true); // South America.green.
		AreaButton[20] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 35 / 100, options.screenHeight * 430 / 640, false, true); // South America.yellow.
		AreaButton[21] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 30 / 100, options.screenHeight * 360 / 640, false, true); // South America.purple.
		AreaButton[22] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 22 / 100, options.screenHeight * 320 / 640, false, true); // Mexico.green.
		AreaButton[23] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 19 / 100, options.screenHeight * 250 / 640, false, true); // U.S.Yellow.
		AreaButton[24] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 25 / 100, options.screenHeight * 260 / 640, false, true); // U.S.Gray.
		AreaButton[25] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 28 / 100, options.screenHeight * 200 / 640, false, true); // Canada.Orange.
		AreaButton[26] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 18 / 100, options.screenHeight * 180 / 640, false, true); // Canada.brown.
		AreaButton[27] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 10 / 100, options.screenHeight * 150 / 640, false, true); // Canada.black.
		AreaButton[28] = new MapButton("bin/images/Areabutton.png",options.screenWidth * 37 / 100, options.screenHeight * 120 / 640, false, true); // North. green.
	}


	String getStatus() {
		return status;
	} // returns start_game value

	void setStatus(String s) {
		status = s;
	} // sets the start_game value

	Players newPlayer(){
		Players player = new Players();
		return player;
	}

	void draw(Graphics g) {
		bkgd.options = options;
		endturn.options = options;
		exit.options = options;
		dice.options = options;
		for(int i=0;i<options.mapPlaces;i++)
			AreaButton[i].options = options;
		bkgd.drawBkgd(g);
		endturn.draw(g);
		exit.draw(g);  //FIXME: Exit button on GAME screen should be help button, not exit.
		// test.draw(g); FIXME: Fix drawing of dice to show outcome of battles
		if(stage == 0)
			stage0();
		g.setFont(new Font("System", Font.BOLD, 20));
		g.setColor(options.system);
		for (int i = 0; i <= 28; i++) {

			AreaButton[i].draw(g);
			if (AreaButton[i].getArmies() < 10)

				g.drawString(Integer.toString(AreaButton[i].getArmies()),
						AreaButton[i].getXEnd() + 15,
						AreaButton[i].getYPos() - 10);
			else
				g.drawString(Integer.toString(AreaButton[i].getArmies()),
						AreaButton[i].getXEnd() + 12,
						AreaButton[i].getYPos() - 10);
			g.drawString(AreaButton[i].getOwner(),
					AreaButton[i].getXEnd() + 12, AreaButton[i].getYPos() + 10);
			g.drawLine(AreaButton[i].getXEnd(), AreaButton[i].getYPos(),
					AreaButton[i].getXEnd() + 10, AreaButton[i].getYPos() - 10);
			g.drawLine(AreaButton[i].getXEnd() + 10,
					AreaButton[i].getYPos() - 10, AreaButton[i].getXEnd() + 35,
					AreaButton[i].getYPos() - 10);

		}
		g.setFont(new Font("System", Font.BOLD, 30));
		for (int y = 0; y < turn.size(); y++)
		{
//			g.setColor(options.playerColor[playerturn]);
			armiesinfo.name = new String(turn.elementAt(playerturn).name+" Armies: " + Integer.toString(turn.elementAt(playerturn).totalArmies));
			armiesinfo.drawSpecial(g);
//XXX			g.drawString(turn.elementAt(playerturn).name+" Armies: " + Integer.toString(turn.elementAt(playerturn).totalArmies), 0, options.screenHeight - 50);
		}

		if(stage == 1 || stage == 3)
		{
//			g.setColor(options.playerColor[playerturn]);
			turninfo.name = new String(turn.elementAt(playerturn).name + " Add Armies");
			turninfo.drawSpecial(g);
//XXX			g.drawString(turn.elementAt(playerturn).name + " Add Armies", (options.screenWidth / 2)-110,options.screenHeight - 20);
		}
		if(stage == 2)
		{
//			g.setColor(options.playerColor[playerturn]);
			turninfo.name = new String(turn.elementAt(playerturn).name + " Turn");
			turninfo.drawSpecial(g);
//XXX			System.out.println(turninfo.getXPos()+" "+turninfo.getYPos());
//XXX			g.drawString(turn.elementAt(playerturn).name + " Turn", (options.screenWidth / 2)-50,options.screenHeight - 20);
		}
//		for(int i = 0; i < turn.size(); i++)
//			if(turn.elementAt(i).started && turn.elementAt(i).totalArmies == 0)
//			{
//				turn.remove(i);
//				nextTurn();
//			}
//		for(int i = 0; i < turn.size(); i++)
//			if(turn.elementAt(i).started && turn.elementAt(i).totalArmies == 0)
//				turn.remove(i);
		if(endgame){
			try {
				Thread.sleep(options.endgameWait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			endgame = false;
		}
		if(checkEndGame()){
			g.setColor(Color.black);
			EndGame = new TextButton(new String("Winner is "+turn.elementAt(playerturn).name), "Center", options.screenHeight/2, options.endgameTextSize,false,false);
			EndGame.drawSpecial(g);
			endgame = true;
			stage = 0;
		}
	}

	void mousePressed(MouseEvent evt) {
		endturn.mousePressed(evt);
		exit.mousePressed(evt);
		for (int i = 0; i <= 28; i++)
			AreaButton[i].mousePressed(evt);
	}

	void mouseReleased(MouseEvent evt) {
		endturn.mouseReleased(evt);
		exit.mouseReleased(evt);
		if (exit.onButton(evt.getX(), evt.getY())) {
			status = "menu";
			stage = 0;
		}
		switch(stage)
		{
		case 1:	stage1(evt);break;
		case 2:	stage2(evt);break;
		case 3:	stage3(evt);break;
		}
	}
	public void mouseMoved(MouseEvent evt) {
		endturn.mouseMoved(evt);
		exit.mouseMoved(evt);
		for (int i = 0; i <= 28; i++)
			AreaButton[i].mouseMoved(evt);
	}
	void cleanup() {
		stage = 1;
		turn.clear();
		for (int i = 0; i < options.maxPlayers; i++)
		{
			turn.push(newPlayer());  //push new players onto the stack
			turn.elementAt(i).name = new String("P" + Integer.toString(i + 1));
		}
		playerturn = 0;
		for (int i = 0; i <= 28; i++) {
			AreaButton[i].setOwner("");
			AreaButton[i].setArmies(0);
		}
	}
	void nextTurn()
	{
		if(playerturn < 0)
			playerturn = 0;
		if(playerturn+1 > turn.size()-1)
			playerturn = 0;
		else
			playerturn++;
	}
	boolean checkEndGame()
	{
		boolean b = false;
		Stack<Players> temp = new Stack<Players>();
		for(int i = 0; i < turn.size(); i++)
			if(turn.elementAt(i).started && turn.elementAt(i).totalArmies > 0 && stage == 2)
				temp.push(turn.elementAt(i));
		if(temp.size() == 1)
			b = true;
		return b;
	}
//	void checkTotalArmies() {
//		int temp = 0;
//		for (int i = 0; i <= 28; i++) {
//			if (AreaButton[i].getOwner() == turn.elementAt(playerturn).name)
//				temp += AreaButton[i].getArmies();
//		}
//		turn.elementAt(playerturn).totalArmies = temp;
//	}
	void checkExit(MouseEvent evt)
	{
		if(turn.elementAt(playerturn).name == "P1")
			if(exit.onButton(evt.getX(), evt.getY()))
			{
				stage = 0;
			}
	}
	void stage0()
	{
		cleanup();
		stage = 1;
	}

	void stage1(MouseEvent evt)
	{
		checkExit(evt);
		for(int i = 0; i <= 28; i++)
		{
			AreaButton[i].mouseReleased(evt);
			if(turn.elementAt(playerturn).countArmies <= options.beginArmies && AreaButton[i].checkOwner(evt, turn.elementAt(playerturn).name))
			{
				AreaButton[i].addArmies();
				turn.elementAt(playerturn).totalArmies++;
				turn.elementAt(playerturn).countArmies++;
				turn.elementAt(playerturn).started = true;
				nextTurn();
			}
		}
		if(turn.lastElement().countArmies >= options.beginArmies)
		{
			stage = 2;
			for(int i=0;i<options.maxPlayers;i++)
				turn.elementAt(i).countArmies = 0;
		}
	}

	void stage2(MouseEvent evt) {
		checkExit(evt);
		for (int i = 0; i <= 28; i++) {
			AreaButton[i].mouseReleased(evt);
			if (AreaButton[i].onButton(evt.getX(), evt.getY())) {
				if (process.size() > 0) {
					if (!process.firstElement().equals(AreaButton[i])) {
						process.add(AreaButton[i]); 
					}
				} else
					process.add(AreaButton[i]);
			}
		}
		// error checking
		if (process.size() > 0)
			if (process.firstElement().getOwner() != turn.elementAt(playerturn).name)
				process.clear();
		if (process.size() > 2)
			process.clear();
		if (process.size() == 1)
			process.elementAt(0).mousePressed(evt);

		if (process.size() == 2 && process.firstElement().getOwner() == turn.elementAt(playerturn).name) {
			if (process.peek().testOwner(turn.elementAt(playerturn).name)) {
				//
				// where locations and ranges are checked
				//
				if (process.firstElement().getArmies() > 1 && process.peek().testOwner(turn.elementAt(playerturn).name)) {
					process.peek().setOwner(turn.elementAt(playerturn).name);
					process.lastElement().setArmies(process.lastElement().getArmies() + process.firstElement().getArmies() - 1);
					process.firstElement().setArmies(1);
				}
			}
			//ATTACK 
			if (process.peek().getOwner() != turn.elementAt(playerturn).name
					&& process.peek().getOwner() != "") {
				if (process.firstElement().getArmies() > 1) { //minimum attack size???  
					dice.RollDice(process.firstElement().getArmies(), process.lastElement().getArmies());
//XXX					System.out.println("ba "+turn.elementAt(0).totalArmies+" "+process.elementAt(0).getArmies()+" "+dice.getAtkWin());
					if(turn.elementAt(playerturn).name == process.elementAt(0).getOwner())
						turn.elementAt(playerturn).totalArmies=turn.elementAt(playerturn).totalArmies-dice.getAtkWin();
//XXX					System.out.println("aa "+turn.elementAt(0).totalArmies+" "+process.elementAt(0).getArmies()+" "+dice.getAtkWin());
					for(int i=0;i<turn.size();i++)
						if(turn.elementAt(i).name == process.elementAt(1).getOwner()){
//XXX							System.out.println("bd "+turn.elementAt(i).totalArmies+" "+process.elementAt(1).getArmies()+" "+dice.getDefWin());
							turn.elementAt(i).totalArmies=turn.elementAt(i).totalArmies-dice.getDefWin();
//XXX							System.out.println("ad "+turn.elementAt(i).totalArmies+" "+process.elementAt(1).getArmies()+" "+dice.getDefWin());
							}
					process.elementAt(0).subArmies(dice.getAtkWin());
					process.elementAt(1).subArmies(dice.getDefWin());
				}
			}
			process.clear();
		}
//		for(int i = 0; i < turn.size(); i++)
//			if(turn.elementAt(i).totalArmies == 0)
//			{
//				turn.remove(i);
//				nextTurn();
//			}
		if (endturn.onButton(evt.getX(), evt.getY())) {
			for(int i = 0; i < turn.size(); i++)
				if(turn.elementAt(i).started && turn.elementAt(i).totalArmies == 0){
					turn.remove(i);
					playerturn--;
				}
			rounds++;
			process.clear();
			nextTurn();
			
			if (rounds/turn.size() >= options.roundsBeforeNewArmies)
			{
				stage = 3;
				rounds = 0;
			}
		}
	}

	void stage3(MouseEvent evt) 
	{
		checkExit(evt);
		for(int i = 0; i <= 28; i++)
		{
			AreaButton[i].mouseReleased(evt);
			if(turn.elementAt(playerturn).countArmies < options.armiesPerRound && AreaButton[i].checkOwner(evt, turn.elementAt(playerturn).name))
			{
				AreaButton[i].addArmies();
				turn.elementAt(playerturn).totalArmies++;
				turn.elementAt(playerturn).countArmies++;
				nextTurn();
			}
		}
		if(turn.elementAt(turn.size()-1).countArmies >= options.armiesPerRound)
		{
			stage = 2;
			for(int i=0;i<turn.size()-1;i++)
				turn.elementAt(i).countArmies = 0;
		}
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {

		return false;
	}

}
