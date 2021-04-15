package com.doxyprotocol;

import com.doxyprotocol.session.ServerSession;
import com.doxyprotocol.task.PacketPollTask;
import dev.waterdog.plugin.Plugin;
import dev.waterdog.utils.JsonConfig;
import lombok.Getter;

public class DoxyProtocol extends Plugin {

    @Getter
    private ServerSession server;

    @Override
    public void onEnable() {
        saveResource("config.json");

        JsonConfig config = new JsonConfig(getDataFolder() + "config.json");
        server = new ServerSession(config.getInt("server-port"));

        getProxy().getScheduler().scheduleRepeating(new PacketPollTask(this), 1);
    }

    @Override
    public void onDisable() {
        server.shutdown();
    }
}
