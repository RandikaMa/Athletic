package com.example.atc;


import com.example.atc.Notification.MyResponse;
import com.example.atc.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA2rsLnaw:APA91bFiZ_iZjKWafCyoRjzdis5wxeCuxHIfh3IqARslKjTmQ6th0iyPIZomD3PFg5bZkN46K4AeeiKxu46AMS7OsMggZ4OVh9zIrokmAvKVHQq-8Wz_4W0uMqNV7K-saER-VE-KPCAN"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}

