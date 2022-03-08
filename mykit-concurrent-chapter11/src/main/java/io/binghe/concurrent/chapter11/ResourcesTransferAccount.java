package io.binghe.concurrent.chapter11;

/**
 * @author binghe
 * @version 1.0.0
 * @description 破坏请求与保持条件
 */
public class ResourcesTransferAccount {
    //账户余额
    private long balance;
    private static ResourcesRequester requester;

    static {
        requester = new ResourcesRequester();
    }

    public void transferMoney(ResourcesTransferAccount targetAccount, long transferMoney){
        //以循环的方式确保申请到所有的资源
        while (true){
            if (requester.applyResources(this, targetAccount)){
                break;
            }
        }
        try{
            synchronized (this){
                synchronized (targetAccount){
                    if (this.balance >= transferMoney){
                        this.balance -= transferMoney;
                        targetAccount.balance += transferMoney;
                    }
                }
            }
        }finally {
            requester.releaseResources(this, targetAccount);
        }

    }
}
