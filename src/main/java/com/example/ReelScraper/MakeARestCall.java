package com.example.ReelScraper;

import com.google.common.net.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;

public class MakeARestCall {

    public static void main(String[] args) {
        String url = "https://www.journaldev.com/sitemap.xml";

        try {
            downloadUsingNIO(url, "/Users/pankaj/sitemap.xml");

            downloadUsingStream(url, "/Users/pankaj/sitemap_stream.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    private static void makeHttpCall(){
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("text/plain");
//        RequestBody body = RequestBody.create(mediaType,"");
//        Request request = new Request.Builder()
//                .url("https://api.instavideosave.com/allinone")
//                .method("GET",body)
//                .addHeader("url", "https://www.instagram.com/reel/CiIPoqYLbex/")
//                .build();
//        Response response = client.newCall(request).execute();
//    }

    public static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);



        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    public static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}
