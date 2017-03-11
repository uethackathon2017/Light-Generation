package com.lightgeneration.kid_locker.callbacks;

import com.lightgeneration.kid_locker.models.AppInfo;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public interface GetResultListener {
    void onResult(ArrayList<AppInfo> appInfos);
}
