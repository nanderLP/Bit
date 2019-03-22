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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class BitCoinAPI {

    public double getPrice(String Currency) {
        double price = 0;
        Map<String, String> token;
        try {
            token = new Yaml().load(new FileReader("Tokens.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return price;
        }
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rest.coinapi.io/v1/exchangerate/" + Currency + "?apikey=" + token.get("CoinApi-Token"))
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject fileStart = (JSONObject) new JSONParser().parse(response.body().string());
            JSONArray content = (JSONArray) fileStart.get("rates");
            JSONObject valueInEuro = null;
            for (Object o : content) {
                JSONObject tmpobj = (JSONObject) o;
                if (tmpobj.get("asset_id_quote").equals("USD")) {
                    valueInEuro = tmpobj;
                    break;
                }
            }
            price = (double) valueInEuro.get("rate");
        } catch (Exception e) {
            e.printStackTrace();
            return price;
        }

        return price;


    }
}
