package oskar.wasiluk.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class OskarWasilukFrosiRepsSpammerHttpRequestUtility {

    public static String sendPostRequest(final String url, final String payload) throws Exception {
        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost post = new HttpPost(url);

            post.setEntity(new StringEntity(payload, "UTF-8"));
            post.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
}