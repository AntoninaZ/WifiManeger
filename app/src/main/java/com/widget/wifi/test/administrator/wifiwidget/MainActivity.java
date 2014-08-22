package com.widget.wifi.test.administrator.wifiwidget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView;
    ArrayList<DataWifi>listDataWifi = new ArrayList<DataWifi>();
    CustomAdapter cAdapter;

    WifiManager mainWifiObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.wifi_list);

        mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        List<WifiConfiguration> wifiScanList = mainWifiObj.getConfiguredNetworks();

        for (int i = 0; i < wifiScanList.size(); i++) {
            WifiConfiguration wifiInfo = wifiScanList.get(i);

            DataWifi data = new DataWifi();

            data.Name = wifiInfo.SSID.replace("\"", "");
            data.WifiId = wifiInfo.networkId;
            switch (wifiInfo.status) {
                case 0:
                    data.Connect = "Connected";
                    data.OnOff = true;
                    break;
                case 1:
                    data.Connect = "DISABLED";
                    data.OnOff = false;
                    break;
                case 2:
                    data.Connect = "ENABLED";
                    data.OnOff = false;
                    break;

            }
            if (data.OnOff) listDataWifi.add(0, data);
            else listDataWifi.add(data);


        }
        cAdapter = new CustomAdapter(MainActivity.this, listDataWifi);
        listView.setAdapter(cAdapter);
    }

    protected void onPause(){
        super.onPause();
    }


}
