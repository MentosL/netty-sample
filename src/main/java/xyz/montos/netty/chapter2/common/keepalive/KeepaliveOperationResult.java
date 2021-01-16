package xyz.montos.netty.chapter2.common.keepalive;

import lombok.Data;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/10 9:19 下午
 */
@Data
public class KeepaliveOperationResult extends OperationResult {

    private long time;

    public KeepaliveOperationResult(long time) {
        this.time = time;
    }
}
