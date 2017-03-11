package com.lightgeneration.kid_locker.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lightgeneration.kid_locker.service.LockService;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

/**
 * Created by Ngoc Sang on 3/12/2017.
 */

public class BootBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
            if(MySharedPreferences.isLockApp())
            {
                Intent i=new Intent(LockKidApplication.getAppContext(), LockService.class);
                LockKidApplication.getAppContext().stopService(i);
            }
    }
}
