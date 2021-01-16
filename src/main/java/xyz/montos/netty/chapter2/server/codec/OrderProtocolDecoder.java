package xyz.montos.netty.chapter2.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import xyz.montos.netty.chapter2.common.RequestMessage;

import java.util.List;

/**
 * @author Montos
 * @create 2021/1/11 2:08 下午
 */
public class OrderProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.decode(byteBuf);
        list.add(requestMessage);
    }
}
