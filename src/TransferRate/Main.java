package TransferRate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
        Label balanceCurrentAccountLabel = new Label("-->Compte courant: " + bankAccount.getBalanceCurrentAccount() + "€");
        balanceCurrentAccountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Label balanceSavingAccountLabel = new Label("Compte Epargne: " + bankAccount.getBalanceSavingAccount() + "€");
        balanceSavingAccountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        Label balanceProAccountLabel = new Label("Compte Pro: " + bankAccount.getBalanceProAccount() + "€");
        balanceProAccountLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

        TextField amountTextField = new TextField();
        amountTextField.setPromptText("Montant...");
        amountTextField.setPrefWidth(100);
        amountTextField.setMaxWidth(100);

        Button depositButton = new Button("Versement");
        Button expenseButton = new Button("Dépense");
        Button saveButton = new Button("Sauvegarder");
        Button loadButton = new Button("Charger");
        Button selectCurrentAccountButton = new Button("Compte courant");
        Button selectSavingAccountButton = new Button("Compte épargne");
        Button selectProAccountButton = new Button("Compte pro");
        Button endCurrentMonthButton = new Button("Fin du mois");
        Button deleteLastMonthButton = new Button( "Suppr. dernier mois");
        Button editLastMonthButton = new Button("Modifier dernier mois");

        //This hBox is contained in a vBox to have two horizontal elements in a vertical box for balance Labels
        HBox balanceLabelsHBox = new HBox(25, balanceCurrentAccountLabel, balanceSavingAccountLabel, balanceProAccountLabel);
        balanceLabelsHBox.setAlignment(Pos.BASELINE_CENTER);
        //Same to select account buttons
        HBox selectAccountHBox = new HBox(25, selectCurrentAccountButton, selectSavingAccountButton, selectProAccountButton);
        selectAccountHBox.setAlignment(Pos.BASELINE_CENTER);
        //Same to deposit and expense buttons and texfield
        HBox depositAndExpenseHBox = new HBox(25, amountTextField, depositButton, expenseButton);
        depositAndExpenseHBox.setAlignment(Pos.BASELINE_CENTER);
        //Same to save and load buttons
        HBox saveAndLoadHBox = new HBox(25, saveButton, loadButton);
        saveAndLoadHBox.setAlignment(Pos.BASELINE_CENTER);

        //ListView to show transaction history for each account
        Label transactionHistoryLabel = new Label("Historique de transactions : ");
        transactionHistoryLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        ListView<Double> transactionHistoryListView = new ListView<Double>();

        VBox transactionHistoryVBox = new VBox(9);
        HBox transactionListViewHBox = new HBox(25, transactionHistoryListView);
        HBox transactionListViewTitleLabelHBox = new HBox(25, transactionHistoryLabel);
        transactionHistoryVBox.getChildren().addAll(transactionListViewTitleLabelHBox, transactionListViewHBox);

        //Vbox to show buttons related to last months balances listview (next comment)
        VBox lastMonthButtonsVbox = new VBox(20, endCurrentMonthButton, deleteLastMonthButton, editLastMonthButton);
        lastMonthButtonsVbox.setAlignment(Pos.CENTER);

        //ListView to show last months balances
        Label lastMonthsLabel = new Label("Solde des derniers mois : ");
        lastMonthsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        ListView<Double> lastMonthsListView = new ListView<Double>();

        VBox lastMonthsVbox = new VBox(9);
        HBox lastMonthsListViewHBox = new HBox(25, lastMonthsListView);
        HBox lastMonthsListViewTitleLabelHBox = new HBox(25, lastMonthsLabel);
        lastMonthsVbox.getChildren().addAll(lastMonthsListViewTitleLabelHBox, lastMonthsListViewHBox);

        HBox listViewHBox = new HBox(25);
        listViewHBox.getChildren().addAll(transactionHistoryVBox, lastMonthsVbox, lastMonthButtonsVbox);

        vBox.getChildren().addAll(balanceLabelsHBox, selectAccountHBox, depositAndExpenseHBox, saveAndLoadHBox, listViewHBox);

        //Listener on each Button
        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (amountTextField.getText() != null && !amountTextField.getText().isEmpty()) {
                    switch (accountTypeNumber){
                        //Current account
                        case 1:
                            //Deposing money to account
                            bankAccount.setBalanceCurrentAccount(bankAccount.getBalanceCurrentAccount() + Double.parseDouble(amountTextField.getText()));
                            //Limiting digits to xxx.xx
                            bankAccount.setBalanceCurrentAccount(Math.round(bankAccount.getBalanceCurrentAccount()* 100.0)/100.00);
                            balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount() + "€");
                            //Adding amount to transaction history
                            bankAccount.setCurrentAccountTransactionHistory(Double.parseDouble(amountTextField.getText()));
                            break;
                        //Saving account
                        case 2:
                            bankAccount.setBalanceSavingAccount(bankAccount.getBalanceSavingAccount() + Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceSavingAccount(Math.round(bankAccount.getBalanceSavingAccount()* 100.0)/100.00);

                            balanceSavingAccountLabel.setText("-->Compte épargne : " + bankAccount.getBalanceSavingAccount() + "€");
                            bankAccount.setSavingAccountTransactionHistory(Double.parseDouble(amountTextField.getText()));
                            break;
                        //Pro account
                        case 3:
                            bankAccount.setBalanceProAccount(bankAccount.getBalanceProAccount() + Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceProAccount(Math.round(bankAccount.getBalanceProAccount()* 100.0)/100.00);

                            balanceProAccountLabel.setText("-->Compte pro : " + bankAccount.getBalanceProAccount() + "€");
                            bankAccount.setProAccountTransactionHistory(Double.parseDouble(amountTextField.getText()));
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

                            balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount() + "€");
                            //Adding amount to transaction history (in negative, a deposit of 50€ will be writed -50)
                            bankAccount.setCurrentAccountTransactionHistory(Double.parseDouble(amountTextField.getText()) - Double.parseDouble(amountTextField.getText()) * 2);
                            break;
                        //Saving account selected
                        case 2 :
                            bankAccount.setBalanceSavingAccount(bankAccount.getBalanceSavingAccount() - Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceSavingAccount(Math.round(bankAccount.getBalanceSavingAccount()* 100.0)/100.00);

                            balanceSavingAccountLabel.setText("-->Compte épargne : " + bankAccount.getBalanceSavingAccount() + "€");
                            bankAccount.setSavingAccountTransactionHistory(Double.parseDouble(amountTextField.getText()) - Double.parseDouble(amountTextField.getText()) * 2);
                            break;
                        //Pro account selected
                        case 3 :
                            bankAccount.setBalanceProAccount(bankAccount.getBalanceProAccount() - Double.parseDouble(amountTextField.getText()));
                            bankAccount.setBalanceProAccount(Math.round(bankAccount.getBalanceProAccount()* 100.0)/100.00);

                            balanceProAccountLabel.setText("-->Compte pro : " + bankAccount.getBalanceProAccount() + "€");
                            bankAccount.setProAccountTransactionHistory(Double.parseDouble(amountTextField.getText()) - Double.parseDouble(amountTextField.getText()) * 2);
                            break;
                    }
                }
            }
        });
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    //Serialization to save info for balances and transaction history
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
                    ObjectInputStream loadBalancesSerialization = new ObjectInputStream(new FileInputStream(save));
                    bankAccount = (BankAccount) loadBalancesSerialization.readObject();
                    balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount() + "€");
                    balanceSavingAccountLabel.setText("Compte épargne : " + bankAccount.getBalanceSavingAccount() + "€");
                    balanceProAccountLabel.setText("Compte pro : " + bankAccount.getBalanceProAccount() + "€");
                    accountTypeNumber = 1;
                    transactionHistoryListView.getItems().clear();
                    int i = 0;
                    while (i < bankAccount.getCurrentAccountTransactionHistory().size()) {
                        transactionHistoryListView.getItems().add(bankAccount.getCurrentAccountTransactionHistory().get(i));
                        i++;
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        selectCurrentAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                balanceCurrentAccountLabel.setText("-->Compte courant: " + bankAccount.getBalanceCurrentAccount() + "€");
                balanceSavingAccountLabel.setText("Compte épargne: " + bankAccount.getBalanceSavingAccount() + "€");
                balanceProAccountLabel.setText("Compte pro: " + bankAccount.getBalanceProAccount() + "€");
                //Updating listview
                transactionHistoryListView.getItems().clear();
                int i = 0;
                while (i < bankAccount.getCurrentAccountTransactionHistory().size()) {
                    transactionHistoryListView.getItems().add(bankAccount.getCurrentAccountTransactionHistory().get(i));
                    i++;
                }
                //Set Account type var to 1 (current account selected)
                accountTypeNumber = 1;
            }
        });
        selectSavingAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                balanceCurrentAccountLabel.setText("Compte courant: " + bankAccount.getBalanceCurrentAccount() + "€");
                balanceSavingAccountLabel.setText("-->Compte épargne: " + bankAccount.getBalanceSavingAccount() + "€");
                balanceProAccountLabel.setText("Compte pro: " + bankAccount.getBalanceProAccount() + "€");

                transactionHistoryListView.getItems().clear();
                int i = 0;
                while (i < bankAccount.getSavingAccountTransactionHistory().size()) {
                    transactionHistoryListView.getItems().add(bankAccount.getSavingAccountTransactionHistory().get(i));
                    i++;
                }
                //Set Account type var to 2 (saving account selected)
                accountTypeNumber = 2;
            }
        });
        selectProAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                balanceCurrentAccountLabel.setText("Compte courant: " + bankAccount.getBalanceCurrentAccount() + "€");
                balanceSavingAccountLabel.setText("Compte épargne: " + bankAccount.getBalanceSavingAccount() + "€");
                balanceProAccountLabel.setText("-->Compte pro: " + bankAccount.getBalanceProAccount() + "€");
                transactionHistoryListView.getItems().clear();
                int i = 0;
                while (i < bankAccount.getProAccountTransactionHistory().size()) {
                    transactionHistoryListView.getItems().add(bankAccount.getProAccountTransactionHistory().get(i));
                    i++;
                }
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
                balanceCurrentAccountLabel.setText("-->Compte courant : " + bankAccount.getBalanceCurrentAccount() + "€");
                balanceSavingAccountLabel.setText("Compte épargne : " + bankAccount.getBalanceSavingAccount() + "€");
                balanceProAccountLabel.setText("Compte pro : " + bankAccount.getBalanceProAccount() + "€");

                int i= 0 ;
                System.out.println(bankAccount.getCurrentAccountTransactionHistory().size());
                while (i < bankAccount.getCurrentAccountTransactionHistory().size()) {
                    transactionHistoryListView.getItems().add(bankAccount.getCurrentAccountTransactionHistory().get(i));
                    i++;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
