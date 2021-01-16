package xyz.montos.netty.chapter2.client.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author Montos
 * @create 2021/1/11 9:16 上午
 */
public class OrderFrameEncoder extends LengthFieldPrepender {

    public OrderFrameEncoder() {
        super( 2);
    }
}
