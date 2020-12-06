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

    BankAccount bankAccount = new BankAccount(0,0,0);

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
        Label balanceLabel = new Label("Solde : " + bankAccount.getBalanceCurrentAccount());

        TextField transferAmountTextField = new TextField();
        transferAmountTextField.setPromptText("Montant...");

        Button transferButton = new Button("Virement");
        Button expenseButton = new Button("Dépense");

        root.getChildren().addAll(balanceLabel, transferButton, expenseButton, transferAmountTextField);

        //Listener on transferButton
        transferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (transferAmountTextField.getText() != null && !transferAmountTextField.getText().isEmpty()) {
                    bankAccount.setBalanceCurrentAccount(bankAccount.getBalanceCurrentAccount() +  Double.parseDouble(transferAmountTextField.getText()));
                    balanceLabel.setText("Solde : " + bankAccount.getBalanceCurrentAccount());
                }
            }
        });

        expenseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (transferAmountTextField.getText() != null && !transferAmountTextField.getText().isEmpty()) {
                    bankAccount.setBalanceCurrentAccount(bankAccount.getBalanceCurrentAccount() - Double.parseDouble(transferAmountTextField.getText()));
                    balanceLabel.setText("Solde : " + bankAccount.getBalanceCurrentAccount());
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
