package xyz.montos.netty.chapter1.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

/**
 * @author Montos
 * @create 2021/1/5 4:30 下午
 */
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest){
            HttpRequest req = (HttpRequest) msg;
            FullHttpResponse response = new DefaultFullHttpResponse(req.protocolVersion(), HttpResponseStatus.OK,Unpooled.wrappedBuffer("helloworld".getBytes()));
            response.headers().set(CONTENT_TYPE,TEXT_PLAIN)
                    .setInt(CONTENT_LENGTH,response.content().readableBytes());
            ChannelFuture write = channelHandlerContext.write(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
