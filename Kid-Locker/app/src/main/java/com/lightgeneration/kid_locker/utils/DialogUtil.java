package com.lightgeneration.kid_locker.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.lightgeneration.kid_locker.R;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class DialogUtil {
    private static DialogUtil mInsance;
    private static Dialog mDialogLoading;
    public static boolean isShowing = false;

    private static Dialog getInstanceDialogLoading(Context pContext) {
        if (mDialogLoading == null) {
            mDialogLoading = new Dialog(pContext);
            mDialogLoading.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return mDialogLoading;
    }
    private DialogUtil() {
    }

    public static DialogUtil getInstance() {
        if (mInsance == null) {
            mInsance = new DialogUtil();
        }
        return mInsance;
    }
    public static void showLoading(Context pContext) {
        if (pContext != null && !isShowing) {
            mDialogLoading = getInstanceDialogLoading(pContext);
            mDialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialogLoading.setContentView(R.layout.loading_dialog);
            mDialogLoading.setCancelable(false);
            mDialogLoading.setCanceledOnTouchOutside(false);
            mDialogLoading.show();
        }
    }
    public static void dismissLoading() {
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
            mDialogLoading = null;
        }
    }
}
