package io.binghe.concurrent.chapter11;

/**
 * @author binghe
 * @version 1.0.0
 * @description 线程安全的转账操作
 *              但是多个转账操作之间是串行执行的
 */
public class SafeTransferAccount {

    //账户余额
    private long balance;

    public void transferMoney(SafeTransferAccount targetAccount, long transferMoney){
        synchronized (SafeTransferAccount.class){
            if (this.balance >= transferMoney){
                this.balance -= transferMoney;
                targetAccount.balance += transferMoney;
            }
        }
    }
}
