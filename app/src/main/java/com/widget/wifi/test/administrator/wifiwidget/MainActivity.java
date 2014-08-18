package com.widget.wifi.test.administrator.wifiwidget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ConfigurationInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
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
    }

    protected void onResume() {
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    class WifiScanReceiver extends BroadcastReceiver {

        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
           // List <WifiInfo> networkInfos = mainWifiObj.getConnectionInfo();

            for (int i = 0; i < wifiScanList.size(); i++){

                ScanResult result = wifiScanList.get(i);
                DataWifi data = new DataWifi();
                data.Name = result.SSID ;
                data.Connect = "Avalibled";
                data.OnOff = true;

                listDataWifi.add(data);
            }
            cAdapter = new CustomAdapter(MainActivity.this, listDataWifi);
            listView.setAdapter(cAdapter);

        }
    }


}
