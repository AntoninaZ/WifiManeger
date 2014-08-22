package com.widget.wifi.test.administrator.wifiwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by administrator on 20.08.14.
 */
public class WifiWidget extends AppWidgetProvider {
    final String LOG_TAG = "myLogs";
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i = 0; i < appWidgetIds.length; ++i) {
            int Id = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget);

            remoteView.setOnClickPendingIntent(R.id.widget, pendingIntent);
            ComponentName thisWidget = new ComponentName(context, WifiWidget.class);

            DataWifi data = ReturtWifiConn(context);

            remoteView.setTextViewText(R.id.name_wifi,data.Name);
            remoteView.setTextViewText(R.id.conn_wifi,data.Connect);

            appWidgetManager.updateAppWidget(Id,remoteView);
            appWidgetManager.updateAppWidget(thisWidget, remoteView);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(LOG_TAG, "onDisabled");
    }
   DataWifi ReturtWifiConn(Context c){
        DataWifi dataWifi = new DataWifi();
        WifiManager wifiManager = (WifiManager) c.getSystemService(c.WIFI_SERVICE);
        List<WifiConfiguration> wifiScanList = wifiManager.getConfiguredNetworks();
        Collections.sort(wifiScanList, new Comparator<WifiConfiguration>() {
           public int compare(WifiConfiguration conf1, WifiConfiguration conf2) {
               if (conf1.status < conf2.status) return -1;
               if (conf1.status > conf2.status) return 1;
               return 0;
           }
       });
       if (wifiScanList.get(0).status == 0) {
           dataWifi.Name = wifiScanList.get(0).SSID.replace("\"","");
           dataWifi.Connect = "Connected";
       }else if (wifiScanList.get(0).status == 1){
           dataWifi.Name = "";
           dataWifi.Connect = "Not avalibled";
       }else {
           dataWifi.Name = wifiScanList.get(0).SSID.replace("\"","");
           dataWifi.Connect = "Avalibled";
       }
       return dataWifi;
    }

}
