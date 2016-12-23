package com.example.android.networkjsondemo;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    private static final String URL = "http://www.mocky.io/v2/57a4dfb40f0000821dc9a3b8";
    OkHttpClient client = new OkHttpClient();

    private static final String TAG = "InterenetServiceTAG_";

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + Thread.currentThread().toString());
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String response = null;
        List<User> users = new ArrayList<User>();
        try {
            response = run(URL);
            Log.d(TAG, "onHandleIntent: " + response);
            users = readJSONToList(response, User.class);
//            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//            User[] users = mapper.readValue(response, TypeFactory.defaultInstance().constructArrayType(User.class));
//            List<User> myObjects = mapper.readValue(response, new TypeReference<List<User>>(){});
            if (users != null) {
                for (User user : users) {
                    Log.d(TAG, "onHandleIntent: " + user.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public List<User> readJSONToList(String jsonValue,Class<User> type){

        List<User> users=null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root= objectMapper.readTree(jsonValue);
            TypeFactory tf = objectMapper.getTypeFactory();
            JavaType listOfObjs = tf.constructCollectionType(ArrayList.class,type);
            users=objectMapper.readValue(root.traverse(), listOfObjs);

        } catch (NullPointerException e) {
            Log.d(TAG, "readJSONToList: " + "Jackson Json List Reader " + e.getMessage());
        } catch (JsonParseException e) {
            Log.d(TAG, "readJSONToList: " + "Jackson Json List Reader " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "readJSONToList: " + "Jackson Json List Reader " + e.getMessage());
        }
        return  users;
    }

}

