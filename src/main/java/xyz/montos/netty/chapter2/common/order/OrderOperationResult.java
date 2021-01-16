package xyz.montos.netty.chapter2.common.order;

import lombok.Data;
import xyz.montos.netty.chapter2.common.OperationResult;

/**
 * @author Montos
 * @create 2021/1/10 9:20 下午
 */
@Data
public class OrderOperationResult extends OperationResult {

    private int tableId;
    private String dish;
    private boolean complete;

    public OrderOperationResult() {
    }

    public OrderOperationResult(int tableId, String dish, boolean complete) {
        this.tableId = tableId;
        this.dish = dish;
        this.complete = complete;
    }
}
