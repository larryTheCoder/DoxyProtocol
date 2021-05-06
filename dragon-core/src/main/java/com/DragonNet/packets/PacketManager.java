package com.DragonNet.packets;

import com.DragonNet.packets.impl.GenericPacket;
import com.DragonNet.packets.impl.DisconnectPacket;
import com.DragonNet.packets.impl.KeepAlivePacket;
import com.DragonNet.packets.protocol.DragonNetProtocol;

public class PacketManager {

    private final static Packet[] packets = new Packet[0x7FFF];

    public static Packet getPacket(int packetId) {
        if (packetId <= 0x7FFF && packets[packetId] != null) {
            return packets[packetId].clone();
        }

        return packets[0];
    }

    public static void registerPacket(Packet packet, boolean force) {
        if (packet.getPacketId() < 0 || packet.getPacketId() > 0x7FFF) {
            throw new IllegalArgumentException("Packet id cannot be lesser than 0 or bigger than 32767");
        } else if (packets[packet.getPacketId()] != null && !force) {
            throw new IllegalArgumentException("Cannot re-register a packet that is already been registered.");
        }

        packets[packet.getPacketId()] = packet;
    }

    static {
        packets[0] = new GenericPacket();
        packets[DragonNetProtocol.DISCONNECT_PACKET] = new DisconnectPacket();
        packets[DragonNetProtocol.KEEP_ALIVE_PACKET] = new KeepAlivePacket();
    }
}
