package xyz.montos.netty.chapter2.server.codec.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xyz.montos.netty.chapter2.common.Operation;
import xyz.montos.netty.chapter2.common.OperationResult;
import xyz.montos.netty.chapter2.common.RequestMessage;
import xyz.montos.netty.chapter2.common.ResponseMessage;

/**
 * @author Montos
 * @create 2021/1/11 3:27 下午
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RequestMessage requestMessage) throws Exception {
        Operation messageBody = requestMessage.getMessageBody();
        OperationResult execute = messageBody.execute();

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(requestMessage.getMessageHeader());
        responseMessage.setMessageBody(execute);
        channelHandlerContext.writeAndFlush(responseMessage);

    }
}
