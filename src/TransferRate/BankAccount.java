package TransferRate;

import java.io.Serializable;
import java.util.List;

public class BankAccount implements Serializable {

    private double balanceCurrentAccount = 0;
    private double balanceSavingAccount = 0;
    private double balanceProAccount = 0;

    private List<Double> transactionHistoryCurrentAccountList;
    private List<Double> transactionHistorySavingAccountList;
    private List<Double> transactionHistoryProAccountList;


    public BankAccount(double balanceCurrentAccount, double balanceSavingAccount, double balanceProAccount) {
        this.setBalanceCurrentAccount(balanceCurrentAccount);
        this.setBalanceSavingAccount(balanceSavingAccount);
        this.setBalanceProAccount(balanceProAccount);
    }

    public double getBalanceCurrentAccount() {
        return balanceCurrentAccount;
    }

    public void setBalanceCurrentAccount(double balanceCurrentAccount) {
        this.balanceCurrentAccount = balanceCurrentAccount;
    }

    public double getBalanceSavingAccount() {
        return balanceSavingAccount;
    }

    public void setBalanceSavingAccount(double balanceSavingAccount) {
        this.balanceSavingAccount = balanceSavingAccount;
    }

    public double getBalanceProAccount() {
        return balanceProAccount;
    }

    public void setBalanceProAccount(double balanceProAccount) {
        this.balanceProAccount = balanceProAccount;
    }

    public List<Double> getTransactionHistoryCurrentAccountList() {
        return transactionHistoryCurrentAccountList;
    }

    public void setTransactionHistoryCurrentAccountList(List<Double> transactionHistoryCurrentAccountList) {
        this.transactionHistoryCurrentAccountList = transactionHistoryCurrentAccountList;
    }

    public List<Double> getTransactionHistorySavingAccountList() {
        return transactionHistorySavingAccountList;
    }

    public void setTransactionHistorySavingAccountList(List<Double> transactionHistorySavingAccountList) {
        this.transactionHistorySavingAccountList = transactionHistorySavingAccountList;
    }

    public List<Double> getTransactionHistoryProAccountList() {
        return transactionHistoryProAccountList;
    }

    public void setTransactionHistoryProAccountList(List<Double> transactionHistoryProAccountList) {
        this.transactionHistoryProAccountList = transactionHistoryProAccountList;
    }
}
