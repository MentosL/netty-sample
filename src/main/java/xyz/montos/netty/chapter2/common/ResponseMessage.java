package xyz.montos.netty.chapter2.common;

/**
 * @author Montos
 * @create 2021/1/9 11:54 上午
 */
public class ResponseMessage extends Message<OperationResult>{
    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getOperationResultClazz();
    }
}
