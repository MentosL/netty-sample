package xyz.montos.netty.chapter2.server.codec.handler;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Montos
 * @create 2021/1/14 11:32 下午
 */
@ChannelHandler.Sharable
public class MetricHandler extends ChannelDuplexHandler {

    private AtomicLong totalNum = new AtomicLong(0);


    {
        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("totalNum",new Gauge<Long>(){
            @Override
            public Long getValue() {
                return totalNum.longValue();
            }
        });
        ConsoleReporter build = ConsoleReporter.forRegistry(metricRegistry).build();
        build.start(5, TimeUnit.SECONDS);

        JmxReporter build1 = JmxReporter.forRegistry(metricRegistry).build();
        build1.start();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        totalNum.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        totalNum.decrementAndGet();
        super.channelInactive(ctx);
    }
}
