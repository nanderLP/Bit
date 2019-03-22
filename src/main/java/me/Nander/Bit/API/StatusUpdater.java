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

package me.Nander.Bit.API;

import me.Nander.Bit.Bot;
import net.dv8tion.jda.core.entities.Game;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.TimerTask;

public class StatusUpdater extends TimerTask {
    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api-pub.bitfinex.com/v2/ticker/tBTCUSD")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            Bot.jda.getPresence().setGame(Game.watching("the Orders | " + String.format("%.2f$ = 1 BTC", (
                    (double) ((JSONArray) new JSONParser()
                            .parse(response.body().string())).get(0)
            ))));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
