package TransferRate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.*;

public class Main extends Application {

    BankAccount bankAccount = new BankAccount(0, 0, 0);
    int accountTypeNumber = 1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Created the Vbox layout and added 10 spacing between each element
        VBox vBox = new VBox(10);

        //Padding and alignment adjustment
        vBox.setPadding(new Insets(25));
        vBox.setAlignment(Pos.BASELINE_CENTER);

        //Scene creation
        Scene scene = new Scene(vBox, 800, 600);

        //Elements creation (buttons, textfields, etc.)
        Label balanceCurrentAccountLabel = new Label("-->Compte courant: " + bankAccount.getBalanceCurrentAccount());
        Label balanceSavingAccountLabel = new Label("Compte Epargne: " + bankAccount.getBalanceSavingAccount());
        Label balanceProAccountLabel = new Label("Compte Pro: " + bankAccount.getBalanceProAccount());

        TextField amountTextField = new TextField();
        amountTextField.setPromptText("Montant...");
        amountTextField.setPrefWidth(100);
        amountTextField.setMaxWidth(100);

        Button depositButton = new Button("Versement");
        Button expenseButton = new Button("Dépense");
        Button saveButton = new Button("Sauvegarder");
        Button loadButton = new Button("Charger");
        Button selectCurrentAccount = new Button("Compte courant");
        Button selectSavingAccount = new Button("Compte épargne");
        Button selectProAccount = new Button("Compte pro");

        //This hBox is contained in a vBox to have two horizontal elements in a vertical box for balance Labels
        HBox balanceLabelsHBox = new HBox(25, balanceCurrentAccountLabel, balanceSavingAccountLabel, balanceProAccountLabel);
        balanceLabelsHBox.setAlignment(Pos.BASELINE_CENTER);
        //Same to select account buttons
        HBox selectAccountHBox = new HBox(25, selectCurrentAccount, selectSavingAccount, selectProAccount);
        selectAccountHBox.setAlignment(Pos.BASELINE_CENTER);
        //Same to deposit and expense buttons and texfield
        HBox depositAndExpenseHBox = new HBox(25, amountTextField, depositButton, expenseButton);
        depositAndExpenseHBox.setAlignment(Pos.BASELINE_CENTER);
        //Same to save and load buttons
        HBox saveAndLoadHBox = new HBox(25, saveButton, loadButton);
        saveAndLoadHBox.setAlignment(Pos.BASELINE_CENTER);
        //ListView to show transaction history for each account
        ListView transactionHistoryListView = new ListView();
   /*     transactionHistoryListView.getItems().add(2);
        transactionHistoryListView.getItems().add(8.9);
        transactionHistoryListView.getItems().add(27.2); */

        HBox transactionHistoryHBox = new HBox(25, transactionHistoryListView);


        vBox.getChildren().addAll(balanceLabelsHBox, selectAccountHBox, depositAndExpenseHBox, saveAndLoadHBox, transactionHistoryHBox);

        //Listener on each Button
        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (amountTextField.getText() != null && !amountTextField.getText().isEmpty()) {
                    switch (accountTypeNumber){
                        //Current account
                        case 1:
                            bankAccount.setBalanceCurrentAccount(bankAccount.getBalanceCurrentAccount() + Double.parseDouble(amountTextField.getText()));
                            //Limiting digits to xxx.xx

                            bankAccount.setBalanceCurrentAccount(Math.round(bankAccount.getBalanceCurrentAccount()* 100.0)/100.00);
                            balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount());
                            break;
                        //Saving account
                        case 2:
                            bankAccount.setBalanceSavingAccount(bankAccount.getBalanceSavingAccount() + Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceSavingAccount(Math.round(bankAccount.getBalanceSavingAccount()* 100.0)/100.00);

                            balanceSavingAccountLabel.setText("-->Compte épargne : " + bankAccount.getBalanceSavingAccount());
                            break;
                        //Pro account
                        case 3:
                            bankAccount.setBalanceProAccount(bankAccount.getBalanceProAccount() + Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceProAccount(Math.round(bankAccount.getBalanceProAccount()* 100.0)/100.00);

                            balanceProAccountLabel.setText("-->Compte pro : " + bankAccount.getBalanceProAccount());
                            break;
                    }
                }
            }
        });
        expenseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (amountTextField.getText() != null && !amountTextField.getText().isEmpty()) {
                    switch(accountTypeNumber){
                        //Current account selected
                        case 1 :
                            //Update balance after expense
                            bankAccount.setBalanceCurrentAccount(bankAccount.getBalanceCurrentAccount() - Double.parseDouble(amountTextField.getText()));
                            //Limiting digits to xxx.xx
                            bankAccount.setBalanceCurrentAccount(Math.round(bankAccount.getBalanceCurrentAccount()* 100.0)/100.00);

                            balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount());
                            break;
                        //Saving account selected
                        case 2 :
                            bankAccount.setBalanceSavingAccount(bankAccount.getBalanceSavingAccount() - Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceSavingAccount(Math.round(bankAccount.getBalanceSavingAccount()* 100.0)/100.00);

                            balanceSavingAccountLabel.setText("-->Compte épargne : " + bankAccount.getBalanceSavingAccount());
                            break;
                        //Pro account selected
                        case 3 :
                            bankAccount.setBalanceProAccount(bankAccount.getBalanceProAccount() - Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceProAccount(Math.round(bankAccount.getBalanceProAccount()* 100.0)/100.00);

                            balanceProAccountLabel.setText("-->Compte pro : " + bankAccount.getBalanceProAccount());
                            break;
                    }
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
                    balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount());
                    balanceSavingAccountLabel.setText("-->Compte épargne : " + bankAccount.getBalanceSavingAccount());
                    balanceProAccountLabel.setText("-->Compte pro : " + bankAccount.getBalanceProAccount());

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        selectCurrentAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                balanceCurrentAccountLabel.setText("-->Compte courant: " + bankAccount.getBalanceCurrentAccount());
                balanceSavingAccountLabel.setText("Compte épargne: " + bankAccount.getBalanceSavingAccount());
                balanceProAccountLabel.setText("Compte pro: " + bankAccount.getBalanceProAccount());
                //Set Account type var to 1 (current account selected)
                accountTypeNumber = 1;
            }
        });
        selectSavingAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                balanceCurrentAccountLabel.setText("Compte courant: " + bankAccount.getBalanceCurrentAccount());
                balanceSavingAccountLabel.setText("-->Compte Epargne: " + bankAccount.getBalanceSavingAccount());
                balanceProAccountLabel.setText("Compte Pro: " + bankAccount.getBalanceProAccount());
                //Set Account type var to 2 (saving account selected)
                accountTypeNumber = 2;
            }
        });
        selectProAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                balanceCurrentAccountLabel.setText("Compte courant: " + bankAccount.getBalanceCurrentAccount());
                balanceSavingAccountLabel.setText("Compte épargne: " + bankAccount.getBalanceSavingAccount());
                balanceProAccountLabel.setText("-->Compte pro: " + bankAccount.getBalanceProAccount());
                //Set Account type var to 3 (pro account selected)
                accountTypeNumber = 3;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Transfer Rate by VACCARI Matteo");

        //If a save file exists, get info from it
            File save = new File("C:/Users/Poste/Documents/Projets Java/TransferRate/save.ser");
            if(save.exists()){
                try {
                    ObjectInputStream loadSerialization = new ObjectInputStream(new FileInputStream(save));
                    bankAccount = (BankAccount) loadSerialization.readObject();
                    balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount());
                    balanceSavingAccountLabel.setText("Compte épargne : " + bankAccount.getBalanceSavingAccount());
                    balanceProAccountLabel.setText("Compte pro : " + bankAccount.getBalanceProAccount());

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
