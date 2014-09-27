//  Group Assignment
//  The Game of Risk - Java Style
//  
//
//  Dice Roll Version 1.3a
//Version Updates

/* Version 1.0
 * Base program
 *
 * Version 1.1
 * Added graphical element to display dice in JFrame
 *
 * Version 1.2
 * Added Bubble Sort for rolled dice to match RISK Rules
 * Added Attacker 1 die advantage over defender
 * 
 * Planned Version 1.3
 * Converted from JFrame to JApplet
 * FIXME:Intergrate to Game.Java program (setting atkqty and defqty)
 * 
 *
 *
 */

import java.awt.Graphics;
import java.util.Random;

public class Dice {

	public Settings options;
    private int atkqty = 0; //get value from other loc
    private int defqty = 0; //get value from other loc
    private int atkwin; //send value back to other loc
    private int defwin; //send value back to other loc
    private int[] atkRolls;
    private int[] defRolls;
    private int atkBeginXPos;
    private int defBeginXPos;
    private boolean result; //false means defend wins, true means attack wins, tie goes to the defender
    private DiceImage[] atkDiceArr;
    private DiceImage[] defDiceArr;

    Dice()
    {
    	options = new Settings();
    	atkRolls = new int[atkqty + 1];
    	defRolls = new int[defqty + 1];
    	atkBeginXPos = 0;
    	defBeginXPos = options.screenWidth/2;
    	result = false;
        atkDiceArr = new DiceImage[7];
        defDiceArr = new DiceImage[7];
        //Preload Dice Images
        atkDiceArr[1] = new DiceImage("bin/dice/1red.png",0,0);
        atkDiceArr[2] = new DiceImage("bin/dice/2red.png",0,0);
        atkDiceArr[3] = new DiceImage("bin/dice/3red.png",0,0);
        atkDiceArr[4] = new DiceImage("bin/dice/4red.png",0,0);
        atkDiceArr[5] = new DiceImage("bin/dice/5red.png",0,0);
        atkDiceArr[6] = new DiceImage("bin/dice/6red.png",0,0);
        defDiceArr[1] = new DiceImage("bin/dice/1white.png",0,0);
        defDiceArr[2] = new DiceImage("bin/dice/2white.png",0,0);
        defDiceArr[3] = new DiceImage("bin/dice/3white.png",0,0);
        defDiceArr[4] = new DiceImage("bin/dice/4white.png",0,0);
        defDiceArr[5] = new DiceImage("bin/dice/5white.png",0,0);
        defDiceArr[6] = new DiceImage("bin/dice/6white.png",0,0);

//        JLabel atkDiceResArr[] = new JLabel[atkqty + 1];
//        JLabel defDiceResArr[] = new JLabel[defqty + 1];
    }
    void setQty(int a,int d){
    	if(a==2)
    		atkqty = 1;
		if(a==3)
			atkqty = 2;
		if(a>=4)
			atkqty = 3;
    	if(d==1)
    		defqty = 1;
		if(d==2)
			defqty = 2;
		if(d>=3)
			defqty = 3; 	
    }

    int getAtkWin(){return atkwin;}
    int getDefWin(){return defwin;}
    public void RollDice(int a, int d)
    {
    	int temp;
    	setQty(a,d);
        atkwin=defwin=0;
    	atkRolls = new int[atkqty + 1];
    	defRolls = new int[defqty + 1];
    	if(defqty > atkqty)
    		temp = atkqty;
    	else
    		temp = defqty;
        for (int roll = 0; roll < temp; roll++) {
            defRolls[roll] = defroll();
            atkRolls[roll] = atkroll();
//            System.out.println("Attackers:  " + atkRolls[roll]); XXX
//            System.out.println("Defenders:  " + defRolls[roll]); XXX
        }
        sort();
    }
    private void sort()
    {
        //Sort of arrays prior to results
        boolean doMore = true;
        while (doMore) {
            doMore = false;
            for (int i = 0; i < defRolls.length - 1; i++) {
                if (defRolls[i] < defRolls[i + 1]) {
                    int temp = defRolls[i + 1];
                    defRolls[i + 1] = defRolls[i];
                    defRolls[i] = temp;
                    doMore = true;
                }
            }
        }
        doMore = true;
        while (doMore) {
            doMore = false;
            for (int i = 0; i < atkRolls.length - 1; i++) {
                if (atkRolls[i] < atkRolls[i + 1]) {
                    int temp = atkRolls[i + 1];
                    atkRolls[i + 1] = atkRolls[i];
                    atkRolls[i] = temp;
                    doMore = true;
                }
            }
        }
        winlose();
    }

    private void winlose()
    {
    	int temp = 0;
    	if(defqty > atkqty)
    		temp = atkqty;
    	else
    		temp = defqty;
        for (int roll = 0; roll < temp; ++roll) {
            result = atkRolls[roll] > defRolls[roll];

            if (result == false) {
                defwin++;
            } else {
                atkwin++;
            }
        }
//        System.out.println("Attackers:  " + atkwin);
//        System.out.println("Defenders:  " + defwin);
    }
	public void draw (Graphics g)
	{
        for (int roll = 0; roll < atkqty; ++roll) {
        	if (roll == 0)
                atkDiceArr[atkRolls[roll]].setXPos(atkBeginXPos);
        	else
        		atkDiceArr[atkRolls[roll]].setXPos(atkDiceArr[atkRolls[roll-1]].getXEnd());
            atkDiceArr[atkRolls[roll]].setYPos(options.screenHeight/2);
            atkDiceArr[atkRolls[roll]].draw(g);
        }
        for (int roll = 0; roll < defqty; ++roll) {
        	if(roll == 0)
        		defDiceArr[defRolls[roll]].setXPos(defBeginXPos);
        	else
        		defDiceArr[defRolls[roll]].setXPos(defDiceArr[defRolls[roll-1]].getXEnd());
            defDiceArr[defRolls[roll]].setYPos(options.screenHeight/2);
            defDiceArr[defRolls[roll]].draw(g);
        }

	}
    private static int defroll() {
        Random die = new Random();
        final int LIMIT = 6;
        int myRoll = die.nextInt(LIMIT) + 1;
        return (myRoll);
    }
    private static int atkroll() {
        Random die = new Random();
        final int LIMIT = 6;
        int myRoll = die.nextInt(LIMIT) + 1;
        return (myRoll);
    }
}
