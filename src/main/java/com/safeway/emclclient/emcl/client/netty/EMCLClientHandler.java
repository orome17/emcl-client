package com.safeway.emclclient.emcl.client.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.SocketAddress;

public class EMCLClientHandler extends ChannelOutboundHandlerAdapter {
    private static final Log logger = LogFactory.getLog(EMCLClientHandler.class);

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
                        SocketAddress localAddress, ChannelPromise promise) throws Exception {
        logger.info("Connected!");
        ctx.connect(remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise)
            throws Exception {
        logger.info("Disconnected!");
        ctx.disconnect(promise);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        logger.info("Sending message to EMCL!");
        System.out.println(new String((byte[])msg));

        ByteBuf bb = Unpooled.copiedBuffer((byte[])msg);
        ctx.writeAndFlush(bb, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        logger.info("Message received from EMCL!");
        ctx.read();
    }
}
