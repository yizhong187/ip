package casper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Casper casper;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/troll.png"));
    private Image casperImage = new Image(this.getClass().getResourceAsStream("/images/casper.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setCasper(Casper c) {
        casper = c;
    }

    public void addStartUpMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getCasperDialog(casper.loadSavedTasks(), casperImage),
                DialogBox.getCasperDialog(casper.welcomeUser(), casperImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getCasperDialog(casper.getResponse(userText), casperImage));
        userInput.clear();
    }
}

