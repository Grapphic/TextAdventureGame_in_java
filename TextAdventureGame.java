import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAdventureGame extends JFrame {
    private JTextArea textArea;
    private JTextField inputField;
    private JButton submitButton;

    // Game variables
    private int playerHealth;
    private boolean hasSword;
    private boolean hasKey;

    // Game states
    private enum GameState {
        START, CAVE, FOREST, TREASURE
    }

    private GameState currentState;

    public TextAdventureGame() {
        setTitle("Text Adventure Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });
        inputPanel.add(inputField, BorderLayout.CENTER);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });
        inputPanel.add(submitButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        initializeGame();

        setVisible(true);
    }

    private void initializeGame() {
        playerHealth = 100;
        hasSword = false;
        hasKey = false;
        currentState = GameState.START;
        displayMessage("Welcome to the Text Adventure Game!\n");
        displayMessage("You find yourself at the entrance of a dark cave.\n");
        displayMessage("You have 100 health. Use your wits to survive!\n");
        displayMessage("Enter commands to interact with the game.\n");
        displayMessage("Available commands: go, take, use, quit\n");
        displayMessage("What will you do?\n");
    }

    private void processInput() {
        String input = inputField.getText().trim().toLowerCase();
        inputField.setText("");

        switch (currentState) {
            case START:
                handleStartState(input);
                break;
            case CAVE:
                handleCaveState(input);
                break;
            case FOREST:
                handleForestState(input);
                break;
            case TREASURE:
                handleTreasureState(input);
                break;
        }
    }

    private void handleStartState(String input) {
        if (input.equals("go")) {
            currentState = GameState.CAVE;
            displayMessage("You enter the dark cave...\n");
            displayMessage("What will you do?\n");
        } else if (input.equals("quit")) {
            displayMessage("Game Over. Thanks for playing!");
            System.exit(0);
        } else {
            displayMessage("Invalid command. Try again.\n");
        }
    }

    private void handleCaveState(String input) {
        if (input.equals("go")) {
            currentState = GameState.FOREST;
            displayMessage("You emerge from the cave into a dense forest.\n");
            displayMessage("What will you do?\n");
        } else if (input.equals("take")) {
            hasSword = true;
            displayMessage("You find a sword lying on the ground and take it.\n");
        } else if (input.equals("quit")) {
            displayMessage("Game Over. Thanks for playing!");
            System.exit(0);
        } else {
            displayMessage("Invalid command. Try again.\n");
        }
    }

    private void handleForestState(String input) {
        if (input.equals("go")) {
            currentState = GameState.TREASURE;
            displayMessage("You find a hidden path leading to a treasure chest!\n");
            displayMessage("What will you do?\n");
        } else if (input.equals("take")) {
            displayMessage("There's nothing to take here.\n");
        } else if (input.equals("use")) {
            if (hasSword) {
                playerHealth -= 20;
                displayMessage("You bravely wield your sword against a lurking enemy.\n");
                displayMessage("You lose 20 health. Current Health: " + playerHealth + "\n");
                if (playerHealth <= 0) {
                    displayMessage("Your health has dropped to zero. You died.\n");
                    displayMessage("Game Over. Thanks for playing!");
                    System.exit(0);
                }
            } else {
                displayMessage("You don't have a weapon to use.\n");
            }
        } else if (input.equals("quit")) {
            displayMessage("Game Over. Thanks for playing!");
            System.exit(0);
        } else {
            displayMessage("Invalid command. Try again.\n");
        }
    }

    private void handleTreasureState(String input) {
        if (input.equals("go")) {
            displayMessage("You open the treasure chest and find riches beyond your wildest dreams!\n");
            displayMessage("Congratulations! You've won the game.\n");
            displayMessage("Game Over. Thanks for playing!");
            System.exit(0);
        } else if (input.equals("take")) {
            displayMessage("There's nothing to take here.\n");
        } else if (input.equals("use")) {
            displayMessage("There's nothing to use here.\n");
        } else if (input.equals("quit")) {
            displayMessage("Game Over. Thanks for playing!");
            System.exit(0);
        } else {
            displayMessage("Invalid command. Try again.\n");
        }
    }

    private void displayMessage(String message) {
        textArea.append(message);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextAdventureGame();
            }
        });
    }
}
