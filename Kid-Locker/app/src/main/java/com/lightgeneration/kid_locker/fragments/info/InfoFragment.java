package com.lightgeneration.kid_locker.fragments.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.activities.EditInfoAcitivity;
import com.lightgeneration.kid_locker.fragments.BaseFragment;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

/**
 * Created by PhamVanLong on 3/10/2017.
 */

public class InfoFragment extends BaseFragment implements View.OnClickListener {
    private Button btnUpdate;
    private TextView tvName,tvAge;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.info_fragment, container, false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        btnUpdate = (Button) contentView.findViewById(R.id.btn_update);
        tvName=(TextView)contentView.findViewById(R.id.tv_name_baby);
        tvAge=(TextView)contentView.findViewById(R.id.tv_age_baby);



    }

    @Override
    protected void init() {
        super.init();
        tvName.setText("Tên Bé : "+MySharedPreferences.getString(Constant.NAME_BABY,""));
        tvAge.setText("Tuổi Bé : "+MySharedPreferences.getInt(Constant.AGE_BABY,0));
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_update: {
                editInfo();
                break;
            }

        }
    }

  private void editInfo()
  {
       Intent intent=new Intent(getActivity(), EditInfoAcitivity.class);
      intent.putExtra("nameBaby", MySharedPreferences.getString(Constant.NAME_BABY,""));
      intent.putExtra("ageBaby",MySharedPreferences.getInt(Constant.AGE_BABY,0));
      startActivity(intent);
      getActivity().overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
  }
}
