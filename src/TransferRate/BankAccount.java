package TransferRate;

import java.io.Serializable;
import java.util.*;

public class BankAccount implements Serializable {

    private double balanceCurrentAccount = 0;
    private double balanceSavingAccount = 0;
    private double balanceProAccount = 0;
    private List<Double> currentAccountTransactionHistory;
    private List<Double> savingAccountTransactionHistory;
    private List<Double> proAccountTransactionHistory;
    private List<Double> lastMonthsBalancesList;

    public BankAccount(double balanceCurrentAccount, double balanceSavingAccount, double balanceProAccount) {
        this.setBalanceCurrentAccount(balanceCurrentAccount);
        this.setBalanceSavingAccount(balanceSavingAccount);
        this.setBalanceProAccount(balanceProAccount);
        currentAccountTransactionHistory = new ArrayList<Double>();
        savingAccountTransactionHistory = new ArrayList<Double>();
        proAccountTransactionHistory = new ArrayList<Double>();
        lastMonthsBalancesList = new ArrayList<Double>();
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

    public List<Double> getCurrentAccountTransactionHistory() {
        return currentAccountTransactionHistory;
    }

    public void setCurrentAccountTransactionHistory(Double amount) {
        this.currentAccountTransactionHistory.add(amount);
    }

    public List<Double> getSavingAccountTransactionHistory() {
        return savingAccountTransactionHistory;
    }

    public void setSavingAccountTransactionHistory(Double amount) {
        this.savingAccountTransactionHistory.add(amount);
    }

    public List<Double> getProAccountTransactionHistory() {
        return proAccountTransactionHistory;
    }

    public void setProAccountTransactionHistory(Double amount) {
        this.proAccountTransactionHistory.add(amount);
    }

    public List<Double> getLastMonthsBalancesList() {
        return lastMonthsBalancesList;
    }

    public void setLastMonthsBalancesList(Double amount) {
        if (lastMonthsBalancesList == null) {
            lastMonthsBalancesList = new ArrayList<Double>();
        }
        this.lastMonthsBalancesList.add(amount);
    }

    public void removeLastMonthBalance() {
        if (lastMonthsBalancesList != null) {
            this.lastMonthsBalancesList.remove(lastMonthsBalancesList.size() - 1);
        }
    }
}
