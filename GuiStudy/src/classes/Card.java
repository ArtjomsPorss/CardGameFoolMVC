package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public final class Card extends JPanel{

	//INSTANCE VARIABLES====================================
	private char suit;
	private String rank;
	private boolean flipped = true;			//is card flipped or not


	//variables used for loading images
	private BufferedImage image = null;					//image of the card
	private String fullImagePath;


	//CONSTRUCTORS===========================================
	public Card(){
		this.setSize(80, 123);
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
		if(!flipped){
			URL url = Card.class.getClassLoader().getResource(fullImagePath);
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(image, 0, 0, null);
		}else{
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 80, 123);
		}
	}


	/**
	 * loads image for this instance of card
	 */
	private void loadImage(){
		//build path to this cards load image
		fullImagePath = "images/card/";

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
			return rankStr;
		}
		case 12 :	{
			rankStr = "Q";
			return rankStr;
		}
		case 13 :	{
			rankStr = "K";
			return rankStr;
		}
		case 14 :	{
			rankStr = "A";
			return rankStr;
		}
		default :	{
			rankStr = Integer.toString(rankInt);
			return rankStr;
		}
		}
	}// end rankToStr

}
