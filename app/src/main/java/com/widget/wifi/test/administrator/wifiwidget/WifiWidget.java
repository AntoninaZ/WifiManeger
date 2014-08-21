package com.widget.wifi.test.administrator.wifiwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by administrator on 20.08.14.
 */
public class WifiWidget extends AppWidgetProvider {
    public static String YOUR_AWESOME_ACTION = "YourAwesomeAction";
    final String LOG_TAG = "myLogs";
    TextView wiFiName;
    TextView wifiConect;
    Button ConDiscon;

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.widget);
        remoteView.setOnClickPendingIntent(R.id.widget, pendingIntent);

        ComponentName thisWidget = new ComponentName(context, WifiWidget.class);

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thisWidget, remoteView);

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
}
