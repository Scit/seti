package ru.scit.seti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: scit
 * Date: 12/12/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class HttpConverter {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "http://www.web2fb2.net/";

    public static String convert(ConvertParamsHolder cph) {
        String url = URL + "?ajax=1&autodetect=1&font=1&_=1";
        url += "&url=" + cph.getUrl();

        if (cph.withImages()) {
            url += "&img=1";
        }
        if (cph.withTables()) {
            url += "&tab=1";
        }

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String pattern = "href = '(.*?\\.fb2\\.zip)'";
            Matcher m = Pattern.compile(pattern).matcher(response.toString());

            if(m.find()) {
                return URL + m.group(1);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
