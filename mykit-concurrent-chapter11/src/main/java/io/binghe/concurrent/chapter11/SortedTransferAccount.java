package io.binghe.concurrent.chapter11;

/**
 * @author binghe
 * @version 1.0.0
 * @description 破坏循环等待条件
 */
public class SortedTransferAccount {

    private long no;
    //账户余额
    private long balance;

    public void transferMoney(SortedTransferAccount targetAccount, long transferMoney){
        SortedTransferAccount beforeLockAccount = this;
        SortedTransferAccount afterLockAccount = targetAccount;
        if (this.no > targetAccount.no){
            beforeLockAccount = targetAccount;
            afterLockAccount = this;
        }
        synchronized (beforeLockAccount){
            synchronized (afterLockAccount){
                if (this.balance >= transferMoney){
                    this.balance -= transferMoney;
                    targetAccount.balance += transferMoney;
                }
            }
        }
    }
}
