import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private Button[][] buttons = new Button[5][5];
    private boolean playerXTurn = true; // Player X starts the game

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.setOnAction(e -> buttonClicked(button));

                buttons[i][j] = button;
                gridPane.add(button, j, i);
            }
        }

        Scene scene = new Scene(gridPane, 550, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    private void buttonClicked(Button button) {
        if (!button.getText().isEmpty()) {
            return; // Cell already marked
        }

        button.setText(playerXTurn ? "X" : "O");
        if (checkForWin()) {
            System.out.println((playerXTurn ? "X" : "O") + " wins!");
            disableAllButtons();
        } else if (checkForDraw()) {
            System.out.println("It's a draw!");
            disableAllButtons();
        } else {
            playerXTurn = !playerXTurn;
        }
    }

    private boolean checkForWin() {
        String[][] board = new String[5][5];

        // Copying button texts to a 2D array for easier checking
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = buttons[i][j].getText();
            }
        }

        // Check rows and columns
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!board[i][j].isEmpty()) {
                    // Check row
                    if (j + 4 < 5 && board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) && board[i][j].equals(board[i][j + 3]) && board[i][j].equals(board[i][j + 4])) {
                        return true;
                    }
                    // Check column
                    if (i + 4 < 5 && board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) && board[i][j].equals(board[i + 3][j]) && board[i][j].equals(board[i + 4][j])) {
                        return true;
                    }
                    // Check diagonals
                    if (i + 4 < 5 && j + 4 < 5) {
                        // Main diagonal
                        if (board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) && board[i][j].equals(board[i + 3][j + 3]) && board[i][j].equals(board[i + 4][j + 4])) {
                            return true;
                        }
                        // Anti-diagonal
                        if (board[i][j].equals(board[i + 1][j + 4]) && board[i][j].equals(board[i + 2][j + 3]) && board[i][j].equals(board[i + 3][j + 2]) && board[i][j].equals(board[i + 4][j + 1])) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean checkForDraw() {
        // Check if all buttons are filled
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false; // Found an empty button
                }
            }
        }
        return true; // All buttons filled, it's a draw
    }

    private void disableAllButtons() {
        // Disable all buttons after game over
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}