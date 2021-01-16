package xyz.montos.netty.chapter2.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Montos
 * @create 2021/1/11 9:09 上午
 */
public class IdUtil {

    private static final AtomicLong IDX = new AtomicLong();

    private IdUtil(){

    }

    public static long nextId(){
        return IDX.incrementAndGet();
    }

}
