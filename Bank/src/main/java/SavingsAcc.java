package BankAccount;

import Bank.FinancialTracker;
import Exceptions.InvalidMoneyAmount;

public class SavingsAccount extends BankAccount {
    private double cashInAccount = 0;
    private double accountWithdrawLimit = 20000;
    private final double interestRate = 1.03;

    public SavingsAccount(FinancialTracker financialTracker) {
        super(financialTracker);
    }

    @Override
    public double getMoneyInAccount() {
        return this.cashInAccount;
    }

    @Override
    public void transferMoney(BankAccount recipient, double amount) throws InvalidMoneyAmount {
        if (!isWithdrawalLimitReached() && amount < cashInAccount) {
            recipient.depositMoney(amount);
            cashInAccount -= amount;
            financialTracker.addCash(amount);
            accountWithdrawLimit -= amount;
        } else throw new InvalidMoneyAmount();

    }

    @Override
    public void depositMoney(double amount) throws InvalidMoneyAmount {
        if (amount > maxExchange) throw new InvalidMoneyAmount();
        else cashInAccount += amount;
    }


    @Override
    public boolean IsMinumumReached() {
        {
            if (this.cashInAccount < minimumAccountBalance) return false;
            return true;
        }
    }


    @Override
    public void updateAccount () {
        if (!IsMinumumReached()) cashInAccount =- minimumCharge;
        addCompoundInterest();
        accountWithdrawalLimit = 20,000;
    }

    @Override
    public void depositMoneyUsingCheck(String bankRoutingNumber, String checkAccountNumber, double amount) throws InvalidMoneyAmount{
        if(amount > maxExchange || !isCheckNumberValid(bankRoutingNumber, checkAccountNumber)) throw new InvalidMoneyAmount();
        else cashInAccount+=amount;
    }

    private boolean isWithdrawalLimitReached () {
        if (accountWithdrawLimit > 0) return true;
        return false;
    } // Used for withdrawing money


   private void addCompoundInterest(){
        cashInAccount *= interestRate;
   }


}
