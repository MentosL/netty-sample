package xyz.montos.netty.chapter2.common.order;

import lombok.Data;
import lombok.extern.java.Log;
import xyz.montos.netty.chapter2.common.Operation;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/10 9:20 下午
 */
@Data
@Log
public class OrderOperation extends Operation {

    private int tableId;
    private String dish;

    public OrderOperation(int tableId, String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }

    @Override
    public OperationResult execute() {
        log.info("OrderOperation is start "+toString());
        log.info("OrderOperation is complete");
        OrderOperationResult result = new OrderOperationResult(tableId,dish,true);
        return result;
    }
}
