package classes;

import java.awt.Component;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Card extends Component{
	
	//INSTANCE VARIABLES
	private BufferedImage image = null;					//image of the card
	
	//variables used for loading images
	private final String IMAGES_PATH = "images/card/";	//holds path to card images folder
	private final String S_PATH = "S/";					//suit paths..
	private final String C_PATH = "C/";
	private final String H_PATH = "H/";
	private final String D_PATH = "D/";
	private final String IMG_EXTENSION = ".png";		//image extension

	public Card(){
		this.setSize(80, 123);
	}
	
	public void paint(Graphics g){
		//loads ace of spades
		String imgFileName = IMAGES_PATH + S_PATH + "A" + IMG_EXTENSION;
		URL url = Card.class.getClassLoader().getResource(imgFileName);
		
		image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		g.drawImage(image, 0, 0, null);
	}
	
}
