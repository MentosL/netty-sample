package xyz.montos.netty.chapter2.common;

/**
 * @author Montos
 * @create 2021/1/9 11:54 上午
 */
public class RequestMessage extends Message<Operation>{

    public RequestMessage() {
    }

    public RequestMessage(Long streamId,Operation operation){
            MessageHeader messageHeader = new MessageHeader();
            messageHeader.setStreamId(streamId);
            messageHeader.setOpCode(OperationType.fromOperation(operation).getOpCode());
            this.setMessageHeader(messageHeader);
            this.setMessageBody(operation);

    }

    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getOperationClazz();
    }
}
