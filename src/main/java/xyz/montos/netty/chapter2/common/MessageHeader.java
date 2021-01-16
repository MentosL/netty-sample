package xyz.montos.netty.chapter2.common;

import lombok.Data;

/**
 * @author Montos
 * @create 2021/1/9 11:32 上午
 */
@Data
public class MessageHeader {

    private int version =1;
    private long streamId;
    private int opCode;
}
