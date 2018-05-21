package cgp.com.retrofitdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView tvGet;
    private TextView tvPost;
    private RetrofitDemoService service;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.arg1) {
                case 100:
                    tvGet.setText("Get" + message.arg1 + "" + message.obj);
                    break;
                case 200:
                    tvPost.setText("Post" + message.arg1 + "" + message.obj);
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvGet = findViewById(R.id.tv1);
        tvPost = findViewById(R.id.tv2);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://route.showapi.com/").build();
        service = retrofit.create(RetrofitDemoService.class);
    }

    public void satrtPost(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "410201197906264674");
        params.put("showapi_appid", "65745");
        params.put("showapi_sign", "cb7589d2f976425c8a0444f37197ed63");
        Call<ResponseBody> call = service.postTestData(params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Message msg = new Message();
                    msg.obj = string;
                    msg.arg1 = 200;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void satrtGet(View view) {
        Call<ResponseBody> call = service.getTestData(0705, 65745, "cb7589d2f976425c8a0444f37197ed63");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    Message msg = new Message();
                    msg.obj = string;
                    msg.arg1 = 100;
                    mHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
