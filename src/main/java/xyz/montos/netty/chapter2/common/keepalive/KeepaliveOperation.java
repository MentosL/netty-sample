package xyz.montos.netty.chapter2.common.keepalive;

import xyz.montos.netty.chapter2.common.Operation;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/10 9:19 下午
 */
public class KeepaliveOperation extends Operation {

    private long time;

    public KeepaliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        KeepaliveOperationResult result = new KeepaliveOperationResult(time);
        return result;
    }
}
