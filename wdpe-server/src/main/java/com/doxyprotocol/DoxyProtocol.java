package com.doxyprotocol;

import com.doxyprotocol.session.ServerSession;
import com.doxyprotocol.task.PacketPollTask;
import dev.waterdog.waterdogpe.plugin.Plugin;
import dev.waterdog.waterdogpe.utils.YamlConfig;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.File;

@Log4j2
public class DoxyProtocol extends Plugin {

    @Getter
    private ServerSession server;

    @Override
    public void onEnable() {
        saveResource("config.yml");
        saveResource("certificate/certificate.crt");
        saveResource("certificate/private_key.key");

        YamlConfig config = new YamlConfig(getDataFolder() + File.separator + "config.yml");
        server = new ServerSession(config.getInt("server-port"),
                new File(getDataFolder() + File.separator + "certificate" + File.separator + "certificate.crt"),
                new File(getDataFolder() + File.separator + "certificate" + File.separator + "private_key.key"));

        getProxy().getScheduler().scheduleRepeating(new PacketPollTask(this), 1);

        log.info("Enabled DoxyProtocol systems");
    }

    @Override
    public void onDisable() {
        server.shutdown();
    }
}
