/*
 * Copyright (c) 2019 Bit Team
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

package me.Nander.Bit.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.Nander.Bit.Bot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDAInfo;

public class Info extends Command {

    public Info() {
        name = "info";
        help = "Shows you Informations about the Bot";
        aliases = new String[]{"infos", "about"};
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply(new EmbedBuilder().setImage(Bot.jda.getSelfUser().getAvatarUrl())
                .setTitle("About Bot")
                .setFooter("Bot was made by Nander#1119", Bot.jda.getUserById(event.getClient().getOwnerId()).getAvatarUrl())
                .addField("",
                        "Prefix: coin" +
                                "\nServers: " + Bot.jda.getGuilds().size() +
                                "\nUsers: " + Bot.jda.getUsers().size() +
                                "\nLibrary: JDA Version " + JDAInfo.VERSION + " [Github Link](" + JDAInfo.GITHUB + ")" +
                                "\nInvite Link: [Click](https://discordapp.com/api/oauth2/authorize?client_id=534807943816937480&permissions=8&scope=bot)",
                        true)
                .build());
    }
}
