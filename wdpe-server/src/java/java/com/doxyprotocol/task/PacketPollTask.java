package com.doxyprotocol.task;

import com.doxyprotocol.DoxyProtocol;
import dev.waterdog.scheduler.Task;

public class PacketPollTask extends Task {

    private final DoxyProtocol server;

    public PacketPollTask(DoxyProtocol protocol) {
        server = protocol;
    }

    @Override
    public void onRun(int currentTick) {
        server.getServer().tickSession();
    }

    @Override
    public void onCancel() {

    }
}
