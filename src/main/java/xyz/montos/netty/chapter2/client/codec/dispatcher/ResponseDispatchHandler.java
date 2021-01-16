package xyz.montos.netty.chapter2.client.codec.dispatcher;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import xyz.montos.netty.chapter2.common.ResponseMessage;

/**
 * @author Montos
 * @create 2021/1/11 6:25 下午
 */
public class ResponseDispatchHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    private RequestPendingCenter requestPendingCenter;

    public ResponseDispatchHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        requestPendingCenter.set(msg.getMessageHeader().getStreamId(),msg.getMessageBody());
    }
}
