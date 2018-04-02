package com.ec.netcore.netty.server;

import com.ec.netcore.server.IServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * netty服务器接口
 *
 * @author hao
 *         Mar 17, 2014 5:19:35 PM
 */
public interface INettyServer extends IServer {

    /**
     * 获取解码器
     */
    ByteToMessageDecoder getDecoder();

    /**
     * 获取编码器
     */
    MessageToByteEncoder getEncoder();

    /**
     * 连接成功
     */
    void channelConnected(ChannelHandlerContext ctx);

    /**
     * 消息处理
     */
    void messageReceived(ChannelHandlerContext ctx, Object obj);

    /**
     * 连接关闭
     */
    void channelClosed(ChannelHandlerContext ctx);

    /**
     * 异常处理
     */
    void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);


}
