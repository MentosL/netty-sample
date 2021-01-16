package xyz.montos.netty.chapter2.client.codec.dispatcher;

import xyz.montos.netty.chapter2.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Montos
 * @create 2021/1/11 6:10 下午
 */
public class RequestPendingCenter {

    private Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();


    public void add(Long streamId, OperationResultFuture future) {
        map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult operationResult) {
        OperationResultFuture operationResultFuture = this.map.get(streamId);
        if (operationResultFuture != null) {
            operationResultFuture.setSuccess(operationResult);
            this.map.remove(streamId);
        }
    }


}
