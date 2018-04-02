package com.ec.netcore.server;

/**
 * 服务接口
 *
 * @author hao
 *         Mar 18, 2014 10:20:37 AM
 */
public interface IServer {
    /**
     * 服务初始化
     */
    void init();

    /**
     * 服务启动
     */
    void start();

    /**
     * 服务停止的时候执行
     */
    void stop();

}
