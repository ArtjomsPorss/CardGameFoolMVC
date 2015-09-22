/**
 * Holds Launches the game
 */

package classes;

public class Game {
	
	public Game(){
		runGame();	//deck created and shuffled, cards dealed
	}
	
	private void runGame(){
		GUI gui = new GUI();
		Model model = new Model();
		Controller control = new Controller(gui, model);

	}
}
