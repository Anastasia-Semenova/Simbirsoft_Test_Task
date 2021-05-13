import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DownloadingPage {
    public void download(String uri, String path) {
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(uri);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            final HttpEntity entity1 = response1.getEntity();
            writer.write(EntityUtils.toString(entity1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
