package com.safeway.emclclient.emcl.client.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EMCLClientInboundHandler extends SimpleChannelInboundHandler {
    private static final Log logger = LogFactory.getLog(EMCLClientInboundHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        logger.info("Connected!");
        super.channelActive(channelHandlerContext);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause){
        cause.printStackTrace();
        channelHandlerContext.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;
        String received = inBuffer.toString(CharsetUtil.UTF_8);
        logger.info("Message received: " + received);
    }
}
