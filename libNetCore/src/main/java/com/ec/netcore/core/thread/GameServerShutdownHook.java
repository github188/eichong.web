package com.ec.netcore.core.thread;

import com.ec.netcore.server.IGameServer;

/**
 * 服务器关闭钩子
 *
 * @author haojian
 *         Mar 27, 2013 4:26:51 PM
 */
public class GameServerShutdownHook extends Thread {

    private IGameServer server;

    public GameServerShutdownHook(IGameServer server) {
        this.server = server;
    }

    @Override
    public void run() {

        server.stop();

    }


}
