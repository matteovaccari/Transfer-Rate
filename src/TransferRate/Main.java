package TransferRate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {

    double balance = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Created the Vbox layout and added 10 spacing between each element
        VBox root = new VBox(10);

        //Padding and alignment adjustment
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.BASELINE_CENTER);

        //Scene creation
        Scene scene = new Scene(root, 600,400);

        //Elements creation (buttons, textfields, etc.)
        Label balanceLabel = new Label("Solde : " + balance);

        TextField transferAmountTextField = new TextField();
        transferAmountTextField.setPromptText("Montant...");

        Button transferButton = new Button("Virement");

        root.getChildren().addAll(balanceLabel, transferButton, transferAmountTextField);

        //Listener on transferButton
        transferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (transferAmountTextField.getText() != null && !transferAmountTextField.getText().isEmpty()) {
                    balance = balance + Double.parseDouble(transferAmountTextField.getText());
                    balanceLabel.setText("Solde : " + balance);
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Transfer Rate by VACCARI Matteo");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
