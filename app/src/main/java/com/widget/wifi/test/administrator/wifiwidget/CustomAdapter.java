package com.widget.wifi.test.administrator.wifiwidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by administrator on 15.08.14.
 */
public class CustomAdapter extends BaseAdapter {
private ArrayList<DataWifi> ListDataWifi;
    Context _c;

    public CustomAdapter(MainActivity mainActivity, ArrayList<DataWifi> listDataWifi) {
        _c = mainActivity;
        ListDataWifi = listDataWifi;
    }

    @Override
    public int getCount() {
        return ListDataWifi.size();
    }

    @Override
    public Object getItem(int position) {
        return ListDataWifi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final DataWifi data = ListDataWifi.get(position);

        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item, null);
        }

        TextView wName = (TextView) v.findViewById(R.id.name_wifi);
        TextView wConn = (TextView) v.findViewById(R.id.connect);
        CheckBox wOnOff = (CheckBox) v.findViewById(R.id.on_off);

        wName.setText(data.Name);
        wConn.setText(data.Connect);
        wOnOff.setChecked(data.OnOff);

        return v;
    }

}
