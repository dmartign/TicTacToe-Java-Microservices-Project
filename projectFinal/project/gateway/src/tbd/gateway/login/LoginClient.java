package tbd.gateway.login;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginClient {

    public String loginUser(String email, String password) {

        String postData = "{\"username\":\"" + email + "\",\"password\":\"" + password + "\"}";
        int postDataLength = postData.length();
        String request = "http://localhost:9001/authentication/login";
        URL url;
        String token = null;
        try {
            url = new URL(request);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData.getBytes());
            wr.flush();
            wr.close();
            InputStream response = conn.getInputStream();
            int len = 0;
            byte[] buffer = new byte[4096];
            while (-1 != (len = response.read(buffer))) {
                token = (token == null ? "" : token) + new String(buffer).trim();
            }
            if (token == null || token.isEmpty()) {
                token = null;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return token;
    }

}
