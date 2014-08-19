package com.widget.wifi.test.administrator.wifiwidget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView;
    ArrayList<DataWifi>listDataWifi = new ArrayList<DataWifi>();
    CustomAdapter cAdapter;
    Integer Cnt;

    WifiManager mainWifiObj;
    WifiScanReceiver wifiReciever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.wifi_list);

        mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        wifiReciever = new WifiScanReceiver();
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
    }


    class WifiScanReceiver extends BroadcastReceiver {

        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiActScanList = mainWifiObj.getScanResults();

            for (int i = 0; i < wifiActScanList.size(); i++){
                ScanResult scanResult = wifiActScanList.get(i);

                DataWifi data = new DataWifi();
                data.Name = scanResult.SSID;
                data.Connect = "Avalibled";
                data.OnOff = false;

                listDataWifi.add(data);
            }

            List<WifiConfiguration> wifiScanList = mainWifiObj.getConfiguredNetworks();

           for (int i = 0; i < wifiScanList.size(); i++){
                WifiConfiguration wifiInfo =  wifiScanList.get(i);

                DataWifi data = new DataWifi();

                data.Name = wifiInfo.SSID.replace("\"","");

                 switch (wifiInfo.status)
                 {
                     case 0:data.Connect = "Avalibled";data.OnOff = true;break;
                     case 1:data.Connect = "Not avalibled";data.OnOff = false;break;
                     case 2:data.Connect = "Not avalibled";data.OnOff = false;break;
                 }
               if(data.OnOff)listDataWifi.add(0,data);
               else listDataWifi.add(data);


           }
           cAdapter = new CustomAdapter(MainActivity.this, listDataWifi);
           listView.setAdapter(cAdapter);
        }
    }

}
