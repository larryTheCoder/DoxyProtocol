package com.DragonNet.pipeline;

import com.DragonNet.packets.Packet;
import com.DragonNet.session.SessionHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class DragonNetPacketTelemetry extends ChannelInboundHandlerAdapter {

    private final SessionHandler session;
    private final SocketChannel channel;

    public DragonNetPacketTelemetry(SocketChannel channel, SessionHandler session) {
        this.session = session;
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        session.queueRawPacket(channel, (Packet) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        session.removeConnection(channel, cause);
    }
}
