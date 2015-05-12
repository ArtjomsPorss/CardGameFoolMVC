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
		control = new Controller(gui, model);
		model = new Model(gui, control);
	}

}
