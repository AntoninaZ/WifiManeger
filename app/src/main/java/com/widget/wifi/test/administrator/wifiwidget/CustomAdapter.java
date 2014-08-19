package com.widget.wifi.test.administrator.wifiwidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    public View getView( final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final DataWifi data = ListDataWifi.get(position);

        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item, null);
        }

        TextView wName = (TextView) v.findViewById(R.id.name_wifi);
        TextView wConn = (TextView) v.findViewById(R.id.connect);
        final Button wDell = (Button) v.findViewById(R.id.delete);
        final Button wDisc = (Button) v.findViewById(R.id.disconnect);

        wName.setText(data.Name);
        wConn.setText(data.Connect);
        wDell.setVisibility(View.INVISIBLE);
        wDisc.setVisibility(View.INVISIBLE);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wDell.setVisibility(View.VISIBLE);
                wDell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Object item = getItem(position);
                        ListDataWifi.remove(item);
                        notifyDataSetChanged();
                    }
                });
                if(data.OnOff)
                     wDisc.setVisibility(View.VISIBLE);
                wDisc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.Connect = "Disconnect";
                        data.OnOff = false;
                        notifyDataSetChanged();
                    }
                });
            }
        });
        return v;
    }

}
