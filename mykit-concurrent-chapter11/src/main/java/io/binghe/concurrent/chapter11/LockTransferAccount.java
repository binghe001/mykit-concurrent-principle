package io.binghe.concurrent.chapter11;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author binghe
 * @version 1.0.0
 * @description 使用Lock破坏不可剥夺条件
 */
public class LockTransferAccount {

    //账户余额
    private long balance;
    //转出账户的锁
    private Lock thisLock = new ReentrantLock();
    //转入账户的锁
    private Lock targetAccountLock = new ReentrantLock();

    public void transferMoney(LockTransferAccount targetAccount, long transferMoney){
        try{
            if (thisLock.tryLock()){
                try{
                    if (targetAccountLock.tryLock()){
                        if (this.balance >= transferMoney){
                            this.balance -= transferMoney;
                            targetAccount.balance += transferMoney;
                        }
                    }
                }finally {
                    targetAccountLock.unlock();
                }
            }
        }finally {
            thisLock.unlock();
        }
    }
}
