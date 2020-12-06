package TransferRate;

public class BankAccount {

    private double balanceCurrentAccount = 0;
    private double balanceSavingAccount;
    private double balanceProAccount;

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
}
