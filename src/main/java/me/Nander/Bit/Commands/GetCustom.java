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
import me.Nander.Bit.API.BitCoinAPI;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetCustom extends Command {
    public GetCustom() {
        this.name = "custom";
        this.botPermissions = new Permission[]{
                Permission.MESSAGE_EMBED_LINKS
        };
        this.help = "Shows you the price of a custom Crypto currency";
        this.arguments = "[contraction]";
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().isEmpty()) {
            event.replyError("You have not given a contraction! Syntax: custom [contraction]");
        }
        BitCoinAPI api = new BitCoinAPI();

        event.getChannel().sendTyping().queue();
        Double price;
        price = api.getPrice(event.getArgs().split(" ")[0]);
        if (price == 0) {
            event.replyError("I din't found the contraction " + event.getArgs().split(" ")[0]);
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.US);
        Date date = new Date();
        EmbedBuilder embd = new EmbedBuilder().setFooter("The " + event.getArgs().split(" ")[0] + " Price of the " + dateFormat.format(date), "https://cdn.discordapp.com/avatars/534807943816937480/0037bf1e95d3a620ef4b95ffcaa58b93.png");
        if (price == 0) {
            event.replyError("An error has occurred! Please try again later or contact a developer.");
        } else {
            embd.addField("The current Bitcoin price", "1 Bitcoin = `" + String.format("%.2f", price) + "$`", true);
            event.reply(embd.build());
        }
    }
}
