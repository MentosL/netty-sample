package xyz.montos.netty.chapter2.common;

import xyz.montos.netty.chapter2.common.auth.AuthOperation;
import xyz.montos.netty.chapter2.common.auth.AuthOperationResult;
import xyz.montos.netty.chapter2.common.keepalive.KeepaliveOperation;
import xyz.montos.netty.chapter2.common.keepalive.KeepaliveOperationResult;
import xyz.montos.netty.chapter2.common.order.OrderOperation;
import xyz.montos.netty.chapter2.common.order.OrderOperationResult;

/**
 * @author Montos
 * @create 2021/1/9 11:53 上午
 */
public enum OperationType {

    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    KEEPALIVE(2, KeepaliveOperation.class, KeepaliveOperationResult.class),
    ORDER(3, OrderOperation.class, OrderOperationResult.class);


    private int opCode;
    private Class<? extends Operation> operationClazz;
    private Class<? extends OperationResult> operationResultClazz;

    OperationType(int opCode, Class<? extends Operation> operationClazz, Class<? extends OperationResult> operationResultClazz) {
        this.opCode = opCode;
        this.operationClazz = operationClazz;
        this.operationResultClazz = operationResultClazz;
    }


    public static OperationType fromOpCode(int opCode) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.getOpCode() == opCode) {
                return operationType;
            }
        }
        return null;
    }

    public static OperationType fromOperation(Operation operation) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.getOperationClazz().equals(operation.getClass())) {
                return operationType;
            }
        }
        return null;
    }


    public int getOpCode() {
        return opCode;
    }

    public OperationType setOpCode(int opCode) {
        this.opCode = opCode;
        return this;
    }

    public Class<? extends Operation> getOperationClazz() {
        return operationClazz;
    }

    public OperationType setOperationClazz(Class<? extends Operation> operationClazz) {
        this.operationClazz = operationClazz;
        return this;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public OperationType setOperationResultClazz(Class<? extends OperationResult> operationResultClazz) {
        this.operationResultClazz = operationResultClazz;
        return this;
    }
}
