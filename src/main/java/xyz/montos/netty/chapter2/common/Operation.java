package xyz.montos.netty.chapter2.common;

/**
 * @author Montos
 * @create 2021/1/9 11:53 上午
 */
public abstract class Operation extends MessageBody{


    public  abstract OperationResult execute();

}
