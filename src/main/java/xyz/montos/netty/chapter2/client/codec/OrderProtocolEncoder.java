package xyz.montos.netty.chapter2.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import xyz.montos.netty.chapter2.common.RequestMessage;
import xyz.montos.netty.chapter2.common.ResponseMessage;

import java.util.List;

/**
 * @author Montos
 * @create 2021/1/11 2:08 下午
 */
public class OrderProtocolEncoder extends MessageToMessageEncoder<RequestMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestMessage responseMessage, List<Object> list) throws Exception {
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        responseMessage.encode(buffer);
        list.add(buffer);
    }
}
