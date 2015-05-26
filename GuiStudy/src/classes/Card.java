/**
 * holds state and behaviour of every card
 */
package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Card extends JPanel{

	//INSTANCE VARIABLES====================================
	private char suit;
	private String rank;
	private boolean flipped = true;			//if true - cards back is shown, if false - image

	//variables used for loading images
	private BufferedImage image = null;					//image of the card
	private String fullImagePath;


	//CONSTRUCTORS===========================================
	public Card(){
		this.setSize(80, 123);
		this.setOpaque(true);
	}

	public Card(String rank, char suit){
		this();
		this.suit = suit;
		this.rank = rank;
		loadImage();
	}


	//INSTANCE METHODS=======================================

	//Paints card image or back
	public void paint(Graphics g){
		if(!flipped){	//if card is not flipped - show image
			URL url = Card.class.getClassLoader().getResource(fullImagePath);
			
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(image, 0, 0, null);
			g.drawRect(0, 0, 79, 122);
		}else{		//if card is flipped - show back of a card
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 80, 123);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, 79, 122);
		}
	}


	/**
	 * loads image for this instance of card
	 */
	private void loadImage(){
		//build path to this cards load image
		fullImagePath = "card/";

		//add suit to image path
		switch(this.suit){
		case 'S' : fullImagePath += "S/"; break;
		case 'C' : fullImagePath += "C/"; break;
		case 'H' : fullImagePath += "H/"; break;
		case 'D' : fullImagePath += "D/"; break;
		}

		//add rank to image path
		fullImagePath += this.rank + ".png";
	}


	//CLASS METHODS==========================================
	//prints cards rank and suit
	public String toString(){
		return this.rank + this.suit;
	}


	//card "UP" state getters and setters
	public void setFlipped(boolean flipped){
		this.flipped = flipped;
	}

	public boolean getFlipped(){
		return this.flipped;
	}
	
	
	// rank getter
	public String getRank(){
		return rank;
	}
	
	
	// suit getter
	public char getSuit(){
		return suit;
	}

	/**
	 * takes rank of card as integer and returns string 
	 * @param int rankInt
	 * @return String number if ranks is between 6 - 10 inclusive or letter if its Jack, Queen, King or Ace 
	 */
	public static String rankToStr(int rankInt){
		String rankStr;
		switch(rankInt){
			case 11 :	{
				rankStr = "J";
				break;
			}
			case 12 :	{
				rankStr = "Q";
				break;
			}
			case 13 :	{
				rankStr = "K";
				break;
			}
			case 14 :	{
				rankStr = "A";
				break;
			}
			default :	{
				rankStr = Integer.toString(rankInt);
				break;
			}
		}
		return rankStr;
	}// end rankToStr

	
	//returns integer representation of card's rank
	public static int rankToInt(String rank){
		int number = 0;
		if(rank.equals("J")){
			number = 11;
		}else if(rank.equals("Q")){
			number = 12;
		}else if(rank.equals("K")){
			number = 13;
		}else if(rank.equals("A")){
			number = 14;
		}else{
			number = Integer.parseInt(rank);
		}
		return number;
	}
}
