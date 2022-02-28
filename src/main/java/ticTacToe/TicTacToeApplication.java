package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.geometry.Insets;


public class TicTacToeApplication extends Application {

    String currentPlayer = "X"; //Initializing currentPlayer to X because X plays first
    ArrayList < Button > buttons = new ArrayList < > (); //Creating an ArrayList of buttons to add to the board

    @Override
    public void start(Stage stage) throws Exception {
        //Initializing UI elements Label & GridPane
        Label gameStatus = new Label("Turn: " + currentPlayer);
        gameStatus.setFont(Font.font("Monospaced", 20));

        GridPane board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        board.setHgap(10);
        board.setVgap(10);

        //Adding 9 buttons to our  3x3 gameboard
        for (int i = 0; i < 9; i++) {
            
            Button button = new Button();
            button.setMinSize(70, 70);
            button.setFont(Font.font("Monospaced", 25));
            
            //Adds the buttons to the Button ArrayList
            buttons.add(button);
            
            button.setOnAction((event) -> {
                
                if (button.getText().isEmpty()) {
                    //Checking if the button is empty so that currentPlayer can mark that box
                    button.setText(currentPlayer); //Marking a box with the currentPlayer's symbol
                    
                    if (isWinner() == true) {
                        //When the game is won, all other buttons are disabled to prevent the other player from playing further
                        button.disarm();
                        //Label is set to "Game over!" 
                        gameStatus.setText("The end!");
                        
                    } else if (allBoxesFilled()==true) {
                        //When the game is not won and all boxes are filled,the game is a draw, it is ended and there is no winner
                        gameStatus.setText("It's a draw!");
                        
                    } else {
                        //Changes turns after one player has played their move
                        changeTurns();
                        gameStatus.setText("Turn: " + currentPlayer); 
                    }
                }
            });
        }

        //Adding all 9 buttons to the 3x3 grid board, last 2 parameters of add specify column, row co-ordinates of the button in the grid
        board.add(buttons.get(0), 0, 0); 
        board.add(buttons.get(1), 0, 1);
        board.add(buttons.get(2), 0, 2);
        board.add(buttons.get(3), 1, 0);
        board.add(buttons.get(4), 1, 1);
        board.add(buttons.get(5), 1, 2);
        board.add(buttons.get(6), 2, 0);
        board.add(buttons.get(7), 2, 1);
        board.add(buttons.get(8), 2, 2);


        BorderPane layout = new BorderPane();
        layout.setTop(gameStatus);
        layout.setCenter(board); 
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show(); 

    }

    //Switches turns between players
    public void changeTurns() {
        
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
            
        } else if (currentPlayer.equals("O")) {
            currentPlayer = "X";
        }
        
    }


    //Checks 3 rows for the same symbol repeated 3 times successively in a row
    public boolean checkRows() {
        //Checks first row 
        if (!buttons.get(0).getText().isEmpty() && ((buttons.get(0).getText().equals(buttons.get(3).getText())) &&
                (buttons.get(0).getText().equals(buttons.get(6).getText())))) {
            return true;
        }
        //Checks second row 
        if (!buttons.get(1).getText().isEmpty() && ((buttons.get(1).getText().equals(buttons.get(4).getText())) &&
                (buttons.get(1).getText().equals(buttons.get(7).getText())))) {
            return true;
        }
        //Checks third row 
        if (!buttons.get(2).getText().isEmpty() && ((buttons.get(2).getText().equals(buttons.get(5).getText())) &&
                (buttons.get(2).getText().equals(buttons.get(8).getText())))) {
            return true;
        }
        else {
            return false;
        }
    }

    
    //Checks 3 columns for the same symbol repeated 3 times successively in a column
    public boolean checkColumns() {
        //Checks the first column 
        if (!buttons.get(0).getText().isEmpty() && ((buttons.get(0).getText().equals(buttons.get(1).getText())) &&
                (buttons.get(0).getText().equals(buttons.get(2).getText())))) {
            return true;
        }
        //Checks the second column
        if (!buttons.get(3).getText().isEmpty() && ((buttons.get(3).getText().equals(buttons.get(4).getText())) &&
                (buttons.get(3).getText().equals(buttons.get(5).getText())))) {
            return true;
        }
        //Checks the third column
        if (!buttons.get(6).getText().isEmpty() && ((buttons.get(6).getText().equals(buttons.get(7).getText())) &&
                (buttons.get(6).getText().equals(buttons.get(8).getText())))) {
            return true;
        }
        else {
            return false;
        }
    }

    
    //Checks the 2 diagonals for the same symbol repeated 3 times successively in a diagonal
    public boolean checkDiagonals() {
        //Checks the first diagonal i.e left to right
        if (!buttons.get(0).getText().isEmpty() && ((buttons.get(0).getText().equals(buttons.get(4).getText())) &&
                (buttons.get(0).getText().equals(buttons.get(8).getText())))) {
            return true;
        }
        //Checks the second diagonal i.e right to left
        if (!buttons.get(2).getText().isEmpty() && ((buttons.get(2).getText().equals(buttons.get(4).getText())) &&
                (buttons.get(2).getText().equals(buttons.get(6).getText())))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
    //Checks if there's a winner according to the above conditions and returns true if any one of them is true
    public boolean isWinner() {
        if (checkColumns() == true || checkRows() == true || checkDiagonals() == true) {
            return true;
        }
        else {
            return false;
        }           
    }
    
    
    //Checks if game can still be played i.e if any boxes are empty
    public boolean allBoxesFilled() {
        for (Button btn: buttons) {
            if (btn.getText().isEmpty()) {
                return false;
            }
        }
       return true;
    }
     
    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }
}