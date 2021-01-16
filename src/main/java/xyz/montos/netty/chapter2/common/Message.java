package xyz.montos.netty.chapter2.common;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * @author Montos
 * @create 2021/1/9 11:31 上午
 */
public abstract class Message<T extends MessageBody> {

    protected MessageHeader messageHeader;
    protected T messageBody;

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JSONObject.toJSONString(messageBody).getBytes());
    }


    public abstract Class<T> getMessageBodyDecodeClass(int opCode);


    public void decode(ByteBuf byteBuf) {
        int version = byteBuf.readInt();
        long streamId = byteBuf.readLong();
        int opCode = byteBuf.readInt();
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setOpCode(opCode);
        messageHeader.setStreamId(streamId);
        messageHeader.setVersion(version);
        this.messageHeader = messageHeader;

        Class<T> messageBodyDecodeClass = getMessageBodyDecodeClass(opCode);

        T t = JSONObject.parseObject(byteBuf.toString(Charset.forName("UTF-8")), messageBodyDecodeClass);
        this.messageBody = t;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public Message<T> setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
        return this;
    }

    public T getMessageBody() {
        return messageBody;
    }

    public Message<T> setMessageBody(T messageBody) {
        this.messageBody = messageBody;
        return this;
    }
}
