package xyz.montos.netty.chapter2.client.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import xyz.montos.netty.chapter2.common.Operation;
import xyz.montos.netty.chapter2.common.RequestMessage;
import xyz.montos.netty.chapter2.util.IdUtil;

import java.util.List;

/**
 * @author Montos
 * @create 2021/1/11 5:26 下午
 */
public class OperationToRequestMessageEncode extends MessageToMessageEncoder<Operation> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Operation operation, List<Object> list) throws Exception {
        RequestMessage requestMessage = new RequestMessage(IdUtil.nextId(), operation);
        list.add(requestMessage);
    }
}
