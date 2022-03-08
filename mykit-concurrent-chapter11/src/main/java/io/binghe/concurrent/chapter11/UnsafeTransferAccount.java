package io.binghe.concurrent.chapter11;

/**
 * @author binghe
 * @version 1.0.0
 * @description 线程不安全的转账操作
 */
public class UnsafeTransferAccount {

    //账户余额
    private long balance;

    public void transferMoney(UnsafeTransferAccount targetAccount, long transferMoney){
        synchronized (this){
            if (this.balance >= transferMoney){
                this.balance -= transferMoney;
                targetAccount.balance += transferMoney;
            }
        }
    }
}
