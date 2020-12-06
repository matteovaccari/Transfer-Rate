package TransferRate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.*;

public class Main extends Application {

    BankAccount bankAccount = new BankAccount(0, 0, 0);

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Created the Vbox layout and added 10 spacing between each element
        VBox vBox = new VBox(10);

        //Padding and alignment adjustment
        vBox.setPadding(new Insets(25));
        vBox.setAlignment(Pos.BASELINE_CENTER);

        //Scene creation
        Scene scene = new Scene(vBox, 600, 400);

        //Elements creation (buttons, textfields, etc.)
        Label balanceLabel = new Label("Solde : " + bankAccount.getBalanceCurrentAccount());

        TextField transferAmountTextField = new TextField();
        transferAmountTextField.setPromptText("Montant...");
        transferAmountTextField.setPrefWidth(100);
        transferAmountTextField.setMaxWidth(100);

        Button transferButton = new Button("Versement");
        Button expenseButton = new Button("Dépense");
        Button saveButton = new Button("Sauvegarder");
        Button loadButton = new Button("Charger");

        //This hBox is contained in a vBox to have two horizontal elements in a vertical box - to transfer and expense
        HBox hBox = new HBox(25, transferButton, expenseButton);
        hBox.setAlignment(Pos.BASELINE_CENTER);
        //Same for this hBox, to save and load
        HBox saveAndLoadHBox = new HBox(25, saveButton, loadButton);
        saveAndLoadHBox.setAlignment(Pos.BASELINE_CENTER);

        vBox.getChildren().addAll(balanceLabel, hBox, transferAmountTextField, saveAndLoadHBox);

        //Listener on each Button
        transferButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (transferAmountTextField.getText() != null && !transferAmountTextField.getText().isEmpty()) {
                    //Update balance after transfer
                    bankAccount.setBalanceCurrentAccount(bankAccount.getBalanceCurrentAccount() + Double.parseDouble(transferAmountTextField.getText()));
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

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    //Serialization to save info
                    File save = new File("C:/Users/Poste/Documents/Projets Java/TransferRate/save.ser");
                    ObjectOutputStream saveSerialization = new ObjectOutputStream(new FileOutputStream(save));
                    saveSerialization.writeObject(bankAccount);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    File save = new File("C:/Users/Poste/Documents/Projets Java/TransferRate/save.ser");
                    ObjectInputStream loadSerialization = new ObjectInputStream(new FileInputStream(save));
                    bankAccount = (BankAccount) loadSerialization.readObject();
                    balanceLabel.setText("Solde : " + bankAccount.getBalanceCurrentAccount());

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
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
