package ua.cn.stu.converter.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ConverterService extends Service {
    public ConverterService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}