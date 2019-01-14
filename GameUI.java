import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameUI extends Application {
	//This class provides a user interface for a guessing game	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Game game = new Game(); //Create a new game
	
		//Game instructions
		Text prompt = new Text();
		prompt.setFont(Font.font("Verdana", 12));
		prompt.setText("I'm thinking of a number between 1 to 100. Can you guess it?");
		
		//Two messages are provided during the game
		Text message = new Text();
		message.setFont(Font.font("Verdana", 12));
		Text score = new Text();
		score.setFont(Font.font("Verdana", 12));

		//A text field enables the user to provide input
		TextField field = new TextField();
		
		//Two buttons are provided to control the game
		Button newGame = new Button();
		newGame.setText("New Game");
		Button giveUp = new Button();
		giveUp.setText("I give up");

		//Alignment of nodes on the screen
		VBox pane = new VBox();
		pane.setPadding(new Insets(25, 25, 200, 25));
		pane.getChildren().add(prompt);
		pane.getChildren().add(new Label());
		pane.getChildren().add(message);
		pane.getChildren().add(score);
		pane.getChildren().add(new Label());
		pane.getChildren().add(field);
		pane.getChildren().add(new Label());
		pane.getChildren().add(newGame);
		pane.getChildren().add(new Text());
		pane.getChildren().add(giveUp);

		//New game button
		newGame.setOnAction(e -> {
			//Create a new game and reset text messages.
			game.newGame();
			message.setText("");
			score.setText("");
		});
	
		//Give up button
		giveUp.setOnAction(e -> {
			//Advise user of the target number
			message.setText("I was thinking of " + game.getNumber() + ". Better luck next time.");
		});
		
		//When text is entered
		field.setOnAction(e -> {
			String s = field.getText(); //Get the text
			field.clear(); //Clear the field
			
			int guess;
			if (Game.isNumeral(s)) { //If the text is a number
				guess = Integer.parseInt(s); //Get the number
				game.scoreUp1(); //Increase the number of total guesses
				score.setText("You have guessed " + game.getScore() + " times."); //Output the number of guesses

				int number = game.getNumber(); //Check the number the game is "thinking of"
				//Provide feedback to the user about their guess.
				if (guess > number)
					message.setText(guess + " is too high. Try again.");
				else if (guess < number)
					message.setText(guess + " is too low. Try again.");
				else
					message.setText("Yes, I was thinking of " + number + ", well done.");
			}
			else {
				//Advise the user if they didn't enter a number
				message.setText(s + " is not a number.");
			}
        });

		//Window title
		primaryStage.setTitle("Guessing Game");
		primaryStage.setWidth(500);
		primaryStage.setHeight(350);
		
		//Displaying the stage.
		primaryStage.setScene(new Scene(pane));		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
