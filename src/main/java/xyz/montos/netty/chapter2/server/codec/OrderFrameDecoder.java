package xyz.montos.netty.chapter2.server.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Montos
 * @create 2021/1/11 9:16 上午
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {

    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }



}
