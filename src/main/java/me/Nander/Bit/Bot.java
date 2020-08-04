/*
 *   Copyright (c) 2020 Bit Team
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package me.Nander.Bit;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.Nander.Bit.API.StatusUpdater;
import me.Nander.Bit.Commands.GetBitCoin;
import me.Nander.Bit.Commands.GetCustom;
import me.Nander.Bit.Commands.GetEthereum;
import me.Nander.Bit.Commands.Info;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;

public class Bot {

    private static final Logger log = LoggerFactory.getLogger(Bot.class);

    public static JDA jda;

    public static void main(String[] args) throws LoginException, InterruptedException, IOException, ParseException {

        Yaml yaml = new Yaml();
        Map<String, String> tokens = yaml.load(new FileReader("Tokens.yml"));

        jda = new JDABuilder(AccountType.BOT).setToken(tokens.get("Bot-Token")).build();

        jda.awaitReady();

        CommandClientBuilder cmdClientBuilder = new CommandClientBuilder();
        cmdClientBuilder.setPrefix("coin ");
        cmdClientBuilder.setAlternativePrefix(jda.getSelfUser().getAsMention());
        cmdClientBuilder.setOwnerId("329630845805789186");
        cmdClientBuilder.setCoOwnerIds("294864600091066369");
        cmdClientBuilder.setGame(Game.playing("with coins"));
        cmdClientBuilder.addCommand(new GetBitCoin());
        cmdClientBuilder.addCommand(new GetEthereum());
        cmdClientBuilder.addCommand(new GetCustom());
        cmdClientBuilder.addCommand(new Info());

        jda.addEventListener(cmdClientBuilder.build());
        new Timer().scheduleAtFixedRate(new StatusUpdater(), 0, 60000);

        Thread.sleep(500);
        log.info("Loaded Successfully!");
    }
}

