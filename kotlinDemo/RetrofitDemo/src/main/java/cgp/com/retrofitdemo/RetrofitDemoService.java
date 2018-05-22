package cgp.com.retrofitdemo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018\5\21 0021.
 */

public interface RetrofitDemoService {
    //@Query相当于119-42?date=date
    //@Path相当于119-42/path
    @GET("119-42/")
    Call<ResponseBody> getTestData(@Query("date") int date,@Query("showapi_appid") int appid,@Query("showapi_sign") String sign);

    @FormUrlEncoded
    @POST("25-3/")
    Call<ResponseBody> postTestData(@FieldMap Map<String, String> params);

}
