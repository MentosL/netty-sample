package xyz.montos.netty.chapter2.client.codec.dispatcher;

import io.netty.util.concurrent.DefaultPromise;
import lombok.Data;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/11 6:04 下午
 */
@Data
public class OperationResultFuture extends DefaultPromise<OperationResult> {

}
