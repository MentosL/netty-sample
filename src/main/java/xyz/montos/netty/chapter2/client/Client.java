package xyz.montos.netty.chapter2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import xyz.montos.netty.chapter2.client.codec.*;
import xyz.montos.netty.chapter2.client.codec.dispatcher.OperationResultFuture;
import xyz.montos.netty.chapter2.client.codec.dispatcher.RequestPendingCenter;
import xyz.montos.netty.chapter2.client.codec.dispatcher.ResponseDispatchHandler;
import xyz.montos.netty.chapter2.common.OperationResult;
import xyz.montos.netty.chapter2.common.RequestMessage;
import xyz.montos.netty.chapter2.common.order.OrderOperation;
import xyz.montos.netty.chapter2.util.IdUtil;

import java.util.concurrent.ExecutionException;

/**
 * @author Montos
 * @create 2021/1/11 3:53 下午
 */
public class Client {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(NioChannelOption.CONNECT_TIMEOUT_MILLIS,10*1000);
        bootstrap.group(new NioEventLoopGroup());

        RequestPendingCenter requestPendingCenter = new RequestPendingCenter();


        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline pipeline = nioSocketChannel.pipeline();
                pipeline.addLast(new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderProtocolEncoder());
                pipeline.addLast(new OrderProtocolDecoder());
                pipeline.addLast(new ResponseDispatchHandler(requestPendingCenter));
                pipeline.addLast(new OperationToRequestMessageEncode());

                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
            }
        });


        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8090).sync();

        long streamId = IdUtil.nextId();


        RequestMessage requestMessage = new RequestMessage(streamId,new OrderOperation(1001, "tudou"));
        channelFuture.channel().writeAndFlush(requestMessage);

        OperationResultFuture operationResultFuture = new OperationResultFuture();

        requestPendingCenter.add(streamId,operationResultFuture);

        OperationResult operationResult = operationResultFuture.get();

        System.out.println(operationResult.toString());

        channelFuture.channel().closeFuture().get();
    }
}
