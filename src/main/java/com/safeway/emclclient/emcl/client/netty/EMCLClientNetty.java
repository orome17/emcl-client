package com.safeway.emclclient.emcl.client.netty;

import com.safeway.emclclient.emcl.client.EMCLClient;
import com.safeway.emclclient.emcl.model.CustomerInformation;
import com.safeway.emclclient.emcl.utils.HexUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.List;

@Service
public class EMCLClientNetty implements EMCLClient {
    private static final Log logger = LogFactory.getLog(EMCLClientNetty.class);

    @Value("${emcl.server.hostname}")
    private String hostname;

    @Value("${emcl.server.port}")
    private int port;

    EventLoopGroup group;
    private ChannelFuture channelFuture;

    public EMCLClientNetty() {

    }

    @PostConstruct
    public void init() {
        group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.remoteAddress(new InetSocketAddress(hostname, port));
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(new EMCLClientInboundHandler());
                }
            });
            channelFuture = bootstrap.connect();
            channelFuture.channel().closeFuture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessages(CustomerInformation customerInformation, List<String> messages) {
        logger.info("[Netty] Channel id: " + channelFuture.channel().id() + ", active: " + channelFuture.channel().isActive());
        for (String message : messages) {
            byte[] byteMessage = HexUtil.hexToBin(message);
            ByteBuf bb = Unpooled.copiedBuffer(byteMessage);
            channelFuture.channel().writeAndFlush(bb);
            logger.info("[Netty] message sent!");
        }
    }

    @PreDestroy
    public void destroy() {
        group.shutdownGracefully();
    }
}
