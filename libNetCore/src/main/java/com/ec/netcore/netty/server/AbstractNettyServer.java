package com.ec.netcore.netty.server;

import com.ec.netcore.model.conf.ServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractNettyServer implements INettyServer {

    private static final Logger logger = LoggerFactory.getLogger(AbstractNettyServer.class);

    public ServerConfig serverConfig;
    private ByteToMessageDecoder decoder;
    private MessageToByteEncoder encoder;

    private int bossThreadCount = 0;
    private int workerThreadCount = 0;

    protected boolean isStop = false;

    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;

    public AbstractNettyServer(ServerConfig serverConfig, ByteToMessageDecoder decoder, MessageToByteEncoder encoder, int bossThreadCount,
                               int workerThreadCount) {
        this.serverConfig = serverConfig;
        this.decoder = decoder;
        this.encoder = encoder;
        this.bossThreadCount = bossThreadCount;
        this.workerThreadCount = workerThreadCount;
    }

    public void init() {

    }

    public void start() {

        this.init();

        if (bossThreadCount > 0)
            bossGroup = new NioEventLoopGroup(bossThreadCount);
        else
            bossGroup = new NioEventLoopGroup();
        if (workerThreadCount > 0)
            workerGroup = new NioEventLoopGroup(workerThreadCount);
        else
            workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)

                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)

                    .childHandler(new ServerChannelInitializer(this));

            ChannelFuture f = bootstrap.bind(serverConfig.getPort());

            f.addListener(
                    new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture future)
                                throws Exception {
                            if (future.isSuccess()) {
                                logger.info("listen  success {},{}",
                                        new Object[]{serverConfig.getDescription(), serverConfig.getPort()});
                            } else {
                                logger.info("listen fail,{},{}",
                                        new Object[]{serverConfig.getDescription(), serverConfig.getPort()});
                                System.exit(0);
                            }

                        }
                    }
            );
            // Wait until the server socket is closed.
            //f.channel().closeFuture().sync();


        } catch (Exception e) {
            logger.error("netty5鏈嶅姟绔惎鍔ㄥ嚭鐜伴敊璇紒銆恵}銆�", new Object[]{e.getMessage()});
            e.printStackTrace();
        } finally {
            logger.info("==========finally=========");
            //bossGroup.shutdownGracefully();
            //workerGroup.shutdownGracefully();
        }

    }

    public void stop() {
        isStop = true;
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
            bossGroup = null;
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
            workerGroup = null;
        }

    }

    public ByteToMessageDecoder getDecoder() {
        return this.decoder;
    }

    public MessageToByteEncoder getEncoder() {
        return this.encoder;
    }

}
