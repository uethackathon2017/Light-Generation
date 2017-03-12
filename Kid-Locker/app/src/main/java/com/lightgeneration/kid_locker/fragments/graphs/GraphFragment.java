package com.lightgeneration.kid_locker.fragments.graphs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eralp.circleprogressview.CircleProgressView;
import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.fragments.BaseFragment;
import com.lightgeneration.kid_locker.models.ItemComparision;
import com.lightgeneration.kid_locker.models.comparision;
import com.lightgeneration.kid_locker.networks.ApiClient;
import com.lightgeneration.kid_locker.networks.ApiClientUtils;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.LockKidApplication;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ngoc Sang on 3/11/2017.
 */

public class GraphFragment extends BaseFragment{
    private ItemComparision itemComparision;
    private CircleProgressView count,leftRight,upDown,recognize;
    private ProgressDialog progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.graph_fragment,container,false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        count=(CircleProgressView)contentView.findViewById(R.id.circle_progress_count);
        upDown=(CircleProgressView)contentView.findViewById(R.id.circle_progress_upDown);
        leftRight=(CircleProgressView)contentView.findViewById(R.id.circle_progress_leftRigh);
        recognize=(CircleProgressView)contentView.findViewById(R.id.circle_progress_recognize);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        progress=new ProgressDialog(getActivity());
        progress.setMessage("Loading...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCanceledOnTouchOutside(false);
        progress.setCancelable(false);
        progress.show();
        ApiClientUtils.getApiClient().getComparison("winter").enqueue(new Callback<ItemComparision>() {
            @Override
            public void onResponse(Call<ItemComparision> call, Response<ItemComparision> response) {
                itemComparision=response.body();
                fillData(itemComparision);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ItemComparision> call, Throwable t) {

            }
        });
    }
    private void fillData(ItemComparision itemComparision)
    {
      recognize.setProgress(itemComparision.getRecognize().getProb());
        count.setProgress(itemComparision.getCount().getProb());
        upDown.setProgress(itemComparision.getUpDown().getProb());
        leftRight.setProgress(itemComparision.getLeftRigh().getProb());
    }
}
