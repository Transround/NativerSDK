package com.transround.nativeradmin.network;

import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.transround.nativeradmin.util.Session;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by szeibert on 2014.11.27..
 */
public abstract class JSONService<T> {
    public T get(String url) throws IOException {
        if (Session.getInstance().getToken() != null) {
            url += "&token=" + Session.getInstance().getToken();
        }
        if (Session.getInstance().getSessid() != null) {
            url += "&sessid=" + Session.getInstance().getSessid();
        }
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        if (Session.getInstance().getSessid() != null) {
            httpGet.setHeader("Cookie", "PHPSESSID=" + Session.getInstance().getSessid());
        }

        HttpResponse response = client.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            throw new IOException("HTTP " + statusCode);
        }
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Type type = new TypeToken<T>(getClass()){}.getType();
        return gson.fromJson(builder.toString(), type);
    }

    public T post(String url, Object data) throws IOException {
        if (Session.getInstance().getToken() != null) {
            url += "&token=" + Session.getInstance().getToken();
        }
        if (Session.getInstance().getSessid() != null) {
            url += "&sessid=" + Session.getInstance().getSessid();
        }
        StringBuilder builder = new StringBuilder();
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
        HttpClient client = new DefaultHttpClient(httpParams);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cookie", "PHPSESSID=" + Session.getInstance().getSessid());
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        StringEntity postData = new StringEntity(gson.toJson(data));
        postData.setContentType("application/json");
        httpPost.setEntity(postData);

        HttpResponse response = client.execute(httpPost);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            throw new IOException("HTTP " + statusCode);
        }
        Type type = new TypeToken<T>(getClass()){}.getType();
        return gson.fromJson(builder.toString(), type);
    }

    public T upload(String url, List<File> files) throws IOException {

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (File f : files) {
            builder.addPart("files[]", new FileBody(f));
            builder.addTextBody("original_paths[]", f.getAbsolutePath());
        }
        builder.addTextBody("sessid", Session.getInstance().getSessid());

        HttpPost request = new HttpPost(url);
        request.setEntity(builder.build());

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient client = clientBuilder.build();

        HttpResponse response = client.execute(request);
        StringBuilder stringBuilder = new StringBuilder();
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } else {
            throw new IOException("HTTP " + statusCode);
        }
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Type type = new TypeToken<T>(getClass()){}.getType();
        return gson.fromJson(stringBuilder.toString(), type);
    }

    public String getRawHTML(String url) throws  IOException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        if (Session.getInstance().getSessid() != null) {
            httpGet.setHeader("Cookie", "PHPSESSID=" + Session.getInstance().getSessid());
        }

        HttpResponse response = client.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            throw new IOException("HTTP " + statusCode);
        }
        return builder.toString();
    }
}
