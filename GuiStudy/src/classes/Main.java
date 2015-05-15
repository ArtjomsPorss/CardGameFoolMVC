package classes;

public class Main {
	private GUI gui;
	private Model model;
	private Controller control;

	public static void main(String[] args) {
		new Main().SetupGame();
	}
	
	private void SetupGame(){
		gui = new GUI();
		model = new Model();
		control = new Controller(gui, model);

	}

}
