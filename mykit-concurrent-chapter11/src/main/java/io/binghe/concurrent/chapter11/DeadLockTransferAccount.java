package io.binghe.concurrent.chapter11;

/**
 * @author binghe
 * @version 1.0.0
 * @description 转账时发生死锁
 */
public class DeadLockTransferAccount {
    //账户余额
    private long balance;

    public void transferMoney(DeadLockTransferAccount targetAccount, long transferMoney){
        synchronized (this){
            synchronized (targetAccount){
                if (this.balance >= transferMoney){
                    this.balance -= transferMoney;
                    targetAccount.balance += transferMoney;
                }
            }
        }
    }
}
