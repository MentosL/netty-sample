package xyz.montos.netty.chapter2.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import xyz.montos.netty.chapter2.server.codec.OrderFrameDecoder;
import xyz.montos.netty.chapter2.server.codec.OrderFrameEncoder;
import xyz.montos.netty.chapter2.server.codec.OrderProtocolDecoder;
import xyz.montos.netty.chapter2.server.codec.OrderProtocolEncoder;
import xyz.montos.netty.chapter2.server.codec.handler.MetricHandler;
import xyz.montos.netty.chapter2.server.codec.handler.OrderServerProcessHandler;

import java.util.concurrent.ExecutionException;

/**
 * @author Montos
 * @create 2021/1/11 3:53 下午
 */
public class Server {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
        NioEventLoopGroup boss = new NioEventLoopGroup(0, new DefaultThreadFactory("boss"));
        NioEventLoopGroup work = new NioEventLoopGroup(0, new DefaultThreadFactory("work"));

        serverBootstrap.group(boss,work);


        serverBootstrap.childOption(NioChannelOption.TCP_NODELAY,true);
        serverBootstrap.childOption(NioChannelOption.SO_BACKLOG,1024);
        MetricHandler metricHandler = new MetricHandler();


        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                ChannelPipeline pipeline = nioSocketChannel.pipeline();
                pipeline.addLast("OrderFrameDecoder",new OrderFrameDecoder());
                pipeline.addLast(new OrderFrameEncoder());
                pipeline.addLast(new OrderProtocolEncoder());
                pipeline.addLast(new OrderProtocolDecoder());

                pipeline.addLast("metricHandler",metricHandler);

                pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                pipeline.addLast(new OrderServerProcessHandler());
            }
        });


        ChannelFuture sync = serverBootstrap.bind(8090).sync();


        sync.channel().closeFuture().get();

    }
}
