package classes;

public class Main {

	public static void main(String[] args) {
		new Main().SetupGame();
	}
	
	private void SetupGame(){
		GUI gui = new GUI();
		Model model = new Model(gui);
		Controller control = new Controller(gui, model);
	}

}
