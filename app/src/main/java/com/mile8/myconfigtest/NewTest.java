package com.mile8.myconfigtest;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class NewTest extends Activity {
    private TextView tv_time;

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1;  //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    //在主线程里面处理消息并更新UI界面
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    setTime();
                    break;
                default:
                    break;

            }
        }
    };

    //获取当天0点时间
    public void getDate1(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);//控制时
        cal.set(Calendar.MINUTE, 0);//控制分
        cal.set(Calendar.SECOND, 0);//控制秒
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(cal.getTime());
        tv_time.setText(time);
    }

    private void setTime2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String time = format.format(cal.getTime());
        tv_time.setText(time);
    }

    private void setTime() {
        String my_time_1;
        String my_time_2;

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        int m = cal.get(Calendar.MONTH);

        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String day = String.valueOf(cal.get(Calendar.DATE));
        String hour;
        String minute;
//        if (cal.get(Calendar.AM_PM) == 0)
//            hour = String.valueOf(cal.get(Calendar.HOUR));
//        else
//            hour = String.valueOf(cal.get(Calendar.HOUR) + 12);
        hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        minute = String.valueOf(cal.get(Calendar.MINUTE));
        String second = String.valueOf(cal.get(Calendar.SECOND));

        my_time_1 = year + "-" + month + "-" + day;
        my_time_2 = hour + "-" + minute + "-" + second;

        tv_time.setText(my_time_1 + "   " + my_time_2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("NewTest", "NewTest onCreate");

        setContentView(R.layout.time_layout);

        tv_time = findViewById(R.id.time);

        new TimeThread().start();
    }


}
