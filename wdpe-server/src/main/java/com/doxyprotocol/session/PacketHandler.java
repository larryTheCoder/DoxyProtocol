package com.doxyprotocol.session;

import com.DragonNet.packets.Packet;
import com.DragonNet.packets.impl.LoginPacket;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayDeque;
import java.util.Queue;

@Log4j2
public class PacketHandler {

    private final Queue<Packet> packetSend = new ArrayDeque<>();
    private final Queue<Packet> packetRecv = new ArrayDeque<>();

    private final SocketChannel channel;

    public PacketHandler(SocketChannel channel) {
        this.channel = channel;
    }

    public void queueRecvPacket(Packet packet) {
        packetRecv.add(packet);
    }

    public void queueSendPacket(Packet packet) {
        packetSend.add(packet);
    }

    /**
     * Process the packets received/sent
     */
    public void processPacket() {
        Packet pk;
        while ((pk = packetRecv.poll()) != null) {
            if (pk instanceof LoginPacket) {
                queueSendPacket(pk);
            }

            log.info(pk.toString());
        }

        while ((pk = packetSend.poll()) != null) {
            channel.writeAndFlush(pk);
        }
    }
}
