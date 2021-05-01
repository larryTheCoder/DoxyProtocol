package com.DragonNet.packets.impl;

import com.DragonNet.packets.protocol.DragonNetProtocol;
import com.DragonNet.packets.Packet;
import lombok.NonNull;

public class KeepAlivePacket extends Packet {

    @Override
    public int getPacketId() {
        return DragonNetProtocol.KEEP_ALIVE_PACKET;
    }

    @Override
    public @NonNull Packet clone() {
        return new KeepAlivePacket();
    }

    @Override
    public String toString() {
        return "KeepAlivePacket()";
    }
}
