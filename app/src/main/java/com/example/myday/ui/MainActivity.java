package com.example.myday.ui;

import static com.amap.api.services.core.ServiceSettings.updatePrivacyAgree;
import static com.amap.api.services.core.ServiceSettings.updatePrivacyShow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

import com.example.myday.Manager.DBHelper;
import com.example.myday.DB.DBStruct;
import com.example.myday.R;
import com.example.myday.util.ToastUtil;


/**
 * 首页面
 */
public class MainActivity extends AppCompatActivity implements WeatherSearch.OnWeatherSearchListener {
    ListView view_all;
    Button button,review,self;

    //about weather layout
//    private TextView reporttime1;
    private TextView weather;
    private TextView Temperature;
    private TextView wind;
    private TextView humidity;



    DBHelper dbHelper= new DBHelper(MainActivity.this);


    private WeatherSearchQuery mquery;
    private String cityname = "南京市";
    private WeatherSearch mweathersearch;
    private LocalWeatherLive weatherlive;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //隐私合规
        /**
         * 更新隐私合规状态,需要在初始化地图之前完成
         * @param  context: 上下文
         * @param  isContains: 隐私权政策是否包含高德开平隐私权政策  true是包含
         * @param  isShow: 隐私权政策是否弹窗展示告知用户 true是展示
         * @since  8.1.0
         */
        updatePrivacyShow(this, true, true) ;
        /**
         * 更新同意隐私状态,需要在初始化地图之前完成
         * @param context: 上下文
         * @param isAgree: 隐私权政策是否取得用户同意  true是用户同意
         * @since 8.1.0
         */
        updatePrivacyAgree(this, true) ;

        button=findViewById(R.id.button);
        review=findViewById(R.id.main_review);
        view_all=findViewById(R.id.view_all);
        self=findViewById(R.id.author_self);

        weather=findViewById(R.id.weather);
        Temperature=findViewById(R.id.temp);
        wind=findViewById(R.id.wind);
//        reporttime1=findViewById(R.id.reporttime1);
        humidity=findViewById(R.id.humidity);


//        ArrayAdapter<DBStruct> arrayAdapter=new ArrayAdapter<>(
//                MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                dbHelper.show());
//        view_all.setAdapter(arrayAdapter);

        searchliveweather();
        getlist_view();

        //jump to self_center
        self.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,Author.class);
            startActivity(intent);
        });


        //add new function button
        button.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        //refresh the main_manu
        review.setOnClickListener(v -> {
            getlist_view();
        });

        //function when attach the position
        view_all.setOnItemClickListener((parent, view, position, id) -> {
            DBStruct dbStruct=(DBStruct) parent.getItemAtPosition(position);

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setPositiveButton("修改", (dialog, which) -> {
                Intent intent=new Intent(MainActivity.this, rewrite.class);
                intent.putExtra("id",dbStruct.getId());
                intent.putExtra("content",dbStruct.getContent());
                intent.putExtra("date",dbStruct.getDate());
                intent.putExtra("title",dbStruct.getTitle());
                startActivity(intent);
            });

            builder.setNegativeButton("删除", (dialog, which) -> {
                dbHelper.delete(dbStruct);
                getlist_view();
            });

            builder.create();
            builder.show();
        });



    }

    private void getlist_view() {
        DBAdapter adapter=new DBAdapter(
                MainActivity.this,
                R.layout.list_view,
                dbHelper.show().toArray(new DBStruct[0])
        );
        view_all.setAdapter(adapter);
    }
    /**
     * 实时天气查询
     */
    private void searchliveweather() {
        mquery = new WeatherSearchQuery(cityname, WeatherSearchQuery.WEATHER_TYPE_LIVE);//检索参数为城市和天气类型，实时天气为1、天气预报为2
        try {
            mweathersearch = new WeatherSearch(this);
            mweathersearch.setOnWeatherSearchListener(this);
            mweathersearch.setQuery(mquery);
            mweathersearch.searchWeatherAsyn(); //异步搜索
        } catch (AMapException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (weatherLiveResult != null && weatherLiveResult.getLiveResult() != null) {
                weatherlive = weatherLiveResult.getLiveResult();
//                reporttime1.setText(weatherlive.getReportTime() + "发布");
                weather.setText(weatherlive.getWeather());
                Temperature.setText(weatherlive.getTemperature() + "°");
                wind.setText(weatherlive.getWindDirection() + "风     " + weatherlive.getWindPower() + "级");
                humidity.setText("湿度         " + weatherlive.getHumidity() + "%");
            } else {
                ToastUtil.show(MainActivity.this, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(MainActivity.this, rCode);
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i) {

    }
}