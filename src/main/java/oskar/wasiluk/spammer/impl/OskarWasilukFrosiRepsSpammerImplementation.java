package oskar.wasiluk.spammer.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import oskar.wasiluk.spammer.IOskarWasilukFrosiRepsSpammer;
import oskar.wasiluk.util.OskarWasilukFrosiRepsSpammerMultithreadingUtil;

import java.util.concurrent.Callable;

public class OskarWasilukFrosiRepsSpammerImplementation implements IOskarWasilukFrosiRepsSpammer {

    @Override
    public void run() {
       final Runnable requestTask = () -> {
           while (true) {
               this.sendRequest();
           }
       };

       OskarWasilukFrosiRepsSpammerMultithreadingUtil.runTaskWithCustomThreads(requestTask, 1000);
    }

    private void sendRequest() {
        final String title = "RAIDED BY BALENCI";
        final String imageUrl = "https://cdn.discordapp.com/attachments/1042893183517999196/1263203130569392329/Video_sans_titre_Realisee_avec_Clipchamp_8.gif?ex=67569057&is=67553ed7&hm=94d88d71c32140a9d0619efea231fce86ca9120b45ebed8ba6dbf83f2e95b128&";

        final String jsonBody = String.format("{\"title\":\"%s\",\"imageUrl\":\"%s\"}", title, imageUrl);

        final String url = "https://frosireps.eu/api/send-to-discord";

        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost post = new HttpPost(url);

            post.setHeader("Content-Type", "application/json");

            post.setEntity(new StringEntity(jsonBody, "UTF-8"));

            try (final CloseableHttpResponse response = httpClient.execute(post)) {
                final String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("Response: " + responseString);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
