package com.lightgeneration.kid_locker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lightgeneration.kid_locker.R;
import com.lightgeneration.kid_locker.custom.Diano;
import com.lightgeneration.kid_locker.models.FullQuestion;
import com.lightgeneration.kid_locker.models.Question;
import com.lightgeneration.kid_locker.musics.MusicPlayer;
import com.lightgeneration.kid_locker.networks.ApiClient;
import com.lightgeneration.kid_locker.networks.ApiClientUtils;
import com.lightgeneration.kid_locker.utils.Config;
import com.lightgeneration.kid_locker.utils.Constant;
import com.lightgeneration.kid_locker.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ngoc Sang on 3/10/2017.
 */

public class LockScreenActivity extends AppCompatActivity implements Runnable, View.OnClickListener {
    private ArrayList<String> arrImg;
    private ArrayList<FullQuestion> fullQuestions;
    private Question question;
    private FullQuestion fullQuestion;
    private String packageName;
    private TextView txtCategoryQues;
    private TextView txtTextQues;
    private int posQues;
    private int ans;
    private boolean isGameRunning;
    private boolean isNextQues;

    private RelativeLayout ansHori1;
    private RelativeLayout ansHori2;
    private RelativeLayout ansHori3;
    private RelativeLayout ansHori4;
    private RelativeLayout ansVerti1;
    private RelativeLayout ansVerti2;
    private RelativeLayout ansVerti3;
    private RelativeLayout ansVerti4;

    private LinearLayout llQuesHori;
    private LinearLayout llQuesVerti;

    private ImageView imgAnsHori1;
    private ImageView imgAnsHori2;
    private ImageView imgAnsHori3;
    private ImageView imgAnsHori4;

    private ImageView imgAnsVerti1;
    private ImageView imgAnsVerti2;
    private ImageView imgAnsVerti3;
    private ImageView imgAnsVerti4;

    private ImageView imgVolume;

    private Handler handlerChangeUI;

    private int timeNextQues;
    private Boolean isFirstPlay;

    private Diano diano;
    private int voiceId[] = {R.raw.voice_true_2, R.raw.voice_true_3, R.raw.voice_true_4};
    private int bgId[] = {R.drawable.background_00, R.drawable.background_01, R.drawable.background_02,
            R.drawable.background_03, R.drawable.background_04, R.drawable.background_05,
            R.drawable.background_06, R.drawable.background_07, R.drawable.background_08,
            R.drawable.background_09, R.drawable.background_10, R.drawable.background_11, R.drawable.background_12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lock_screen);
        getSupportActionBar().hide();

        initComponents();
        initHandlerChangeUI();
        loadQuestions();
    }

    private void initComponents() {
        packageName=getIntent().getStringExtra("namepackage");
        txtCategoryQues = (TextView) findViewById(R.id.tv_category_question);
        txtTextQues = (TextView) findViewById(R.id.tv_text_question);
        diano=(Diano)findViewById(R.id.diano);
        ansHori1 = (RelativeLayout) findViewById(R.id.ans_hori_1);
        ansHori2 = (RelativeLayout) findViewById(R.id.ans_hori_2);
        ansHori3 = (RelativeLayout) findViewById(R.id.ans_hori_3);
        ansHori4 = (RelativeLayout) findViewById(R.id.ans_hori_4);
        ansVerti1 = (RelativeLayout) findViewById(R.id.ans_verti_1);
        ansVerti2 = (RelativeLayout) findViewById(R.id.ans_verti_2);
        ansVerti3 = (RelativeLayout) findViewById(R.id.ans_verti_3);
        ansVerti4 = (RelativeLayout) findViewById(R.id.ans_verti_4);

        llQuesHori = (LinearLayout) findViewById(R.id.ll_ques_horizontal);
        llQuesVerti = (LinearLayout) findViewById(R.id.ll_ques_vertical);

        imgAnsHori1 = (ImageView) findViewById(R.id.img_ans_hori_1);
        imgAnsHori2 = (ImageView) findViewById(R.id.img_ans_hori_2);
        imgAnsHori3 = (ImageView) findViewById(R.id.img_ans_hori_3);
        imgAnsHori4 = (ImageView) findViewById(R.id.img_ans_hori_4);

        imgAnsVerti1 = (ImageView) findViewById(R.id.img_ans_verti_1);
        imgAnsVerti2 = (ImageView) findViewById(R.id.img_ans_verti_2);
        imgAnsVerti3 = (ImageView) findViewById(R.id.img_ans_verti_3);
        imgAnsVerti4 = (ImageView) findViewById(R.id.img_ans_verti_4);

        imgVolume = (ImageView) findViewById(R.id.ic_volume);

        ansHori1.setOnClickListener(this);
        ansHori2.setOnClickListener(this);
        ansHori3.setOnClickListener(this);
        ansHori4.setOnClickListener(this);

        ansVerti1.setOnClickListener(this);
        ansVerti2.setOnClickListener(this);
        ansVerti3.setOnClickListener(this);
        ansVerti4.setOnClickListener(this);

        imgVolume.setOnClickListener(this);

        fullQuestions = new ArrayList<>();
        arrImg = new ArrayList<>();
        posQues = -1;
        ans = -1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void setAllGone() {
        llQuesHori.setVisibility(View.GONE);
        llQuesVerti.setVisibility(View.GONE);
        ansHori1.setVisibility(View.GONE);
        ansHori2.setVisibility(View.GONE);
        ansHori3.setVisibility(View.GONE);
        ansHori4.setVisibility(View.GONE);
        ansVerti1.setVisibility(View.GONE);
        ansVerti2.setVisibility(View.GONE);
        ansVerti3.setVisibility(View.GONE);
        ansVerti4.setVisibility(View.GONE);
    }

    private int getRandomBackground() {
        Random random = new Random();
        int u = random.nextInt(bgId.length);
        return bgId[u];
    }

    private void setVisibleHori() {
        llQuesHori.setVisibility(View.VISIBLE);

        int d = arrImg.size();
        if (d == 1) {
            ansHori1.setVisibility(View.VISIBLE);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsHori1);
            ansHori1.setBackgroundResource(getRandomBackground());
        }

        if (d == 2) {
            ansHori1.setVisibility(View.VISIBLE);
            ansHori2.setVisibility(View.VISIBLE);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsHori1);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(1)).thumbnail(0.1f).into(imgAnsHori2);
            ansHori1.setBackgroundResource(getRandomBackground());
            ansHori2.setBackgroundResource(getRandomBackground());
        }

        if (d == 3) {
            ansHori1.setVisibility(View.VISIBLE);
            ansHori2.setVisibility(View.VISIBLE);
            ansHori3.setVisibility(View.VISIBLE);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsHori1);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(1)).thumbnail(0.1f).into(imgAnsHori2);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(2)).thumbnail(0.1f).into(imgAnsHori3);
            ansHori1.setBackgroundResource(getRandomBackground());
            ansHori2.setBackgroundResource(getRandomBackground());
            ansHori3.setBackgroundResource(getRandomBackground());
        }

        if (d == 4) {
            ansHori1.setVisibility(View.VISIBLE);
            ansHori2.setVisibility(View.VISIBLE);
            ansHori3.setVisibility(View.VISIBLE);
            ansHori4.setVisibility(View.VISIBLE);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsHori1);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(1)).thumbnail(0.1f).into(imgAnsHori2);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(2)).thumbnail(0.1f).into(imgAnsHori3);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(3)).thumbnail(0.1f).into(imgAnsHori4);
            ansHori1.setBackgroundResource(getRandomBackground());
            ansHori2.setBackgroundResource(getRandomBackground());
            ansHori3.setBackgroundResource(getRandomBackground());
            ansHori4.setBackgroundResource(getRandomBackground());
        }
    }

    private void setVisibleVerti() {
        llQuesVerti.setVisibility(View.VISIBLE);

        int d = arrImg.size();
        if (d == 1) {
            ansVerti1.setVisibility(View.VISIBLE);
            ansVerti1.setBackgroundResource(getRandomBackground());
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsVerti1);
        }

        if (d == 2) {
            ansVerti1.setVisibility(View.VISIBLE);
            ansVerti2.setVisibility(View.VISIBLE);
            ansVerti1.setBackgroundResource(getRandomBackground());
            ansVerti2.setBackgroundResource(getRandomBackground());
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsVerti1);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(1)).thumbnail(0.1f).into(imgAnsVerti2);
        }

        if (d == 3) {
            ansVerti1.setVisibility(View.VISIBLE);
            ansVerti2.setVisibility(View.VISIBLE);
            ansVerti3.setVisibility(View.VISIBLE);
            ansVerti1.setBackgroundResource(getRandomBackground());
            ansVerti2.setBackgroundResource(getRandomBackground());
            ansVerti3.setBackgroundResource(getRandomBackground());
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsVerti1);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(1)).thumbnail(0.1f).into(imgAnsVerti2);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(2)).thumbnail(0.1f).into(imgAnsVerti3);
        }

        if (d == 4) {
            ansVerti1.setVisibility(View.VISIBLE);
            ansVerti2.setVisibility(View.VISIBLE);
            ansVerti3.setVisibility(View.VISIBLE);
            ansVerti4.setVisibility(View.VISIBLE);
            ansVerti1.setBackgroundResource(getRandomBackground());
            ansVerti2.setBackgroundResource(getRandomBackground());
            ansVerti3.setBackgroundResource(getRandomBackground());
            ansVerti4.setBackgroundResource(getRandomBackground());
            Glide.with(this).load(Config.BASE_URL + arrImg.get(0)).thumbnail(0.1f).into(imgAnsVerti1);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(1)).thumbnail(0.1f).into(imgAnsVerti2);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(2)).thumbnail(0.1f).into(imgAnsVerti3);
            Glide.with(this).load(Config.BASE_URL + arrImg.get(3)).thumbnail(0.1f).into(imgAnsVerti4);
        }
    }

    private void updateUINextQuestion() {
        setAllGone();
        if (question.isHorizontal()) setVisibleHori();
        else setVisibleVerti();
        setAllClickable(true);
        txtCategoryQues.setText(fullQuestion.getCategory());
        txtTextQues.setText(question.getText());
        MusicPlayer musicPlayer = new MusicPlayer(Config.BASE_URL + question.getAudio());
        musicPlayer.play();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MySharedPreferences.putBoolen(Constant.ON_TEST,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySharedPreferences.putBoolen(Constant.ON_TEST,false);
    }

    private void setAllClickable(boolean b) {
        ansHori1.setClickable(b);
        ansHori2.setClickable(b);
        ansHori3.setClickable(b);
        ansHori4.setClickable(b);
        ansVerti1.setClickable(b);
        ansVerti2.setClickable(b);
        ansVerti3.setClickable(b);
        ansVerti4.setClickable(b);
    }

    private void initHandlerChangeUI() {
        handlerChangeUI = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constant.UPDATE_NEXT_QUESTION: {
                        updateUINextQuestion();
                        break;
                    }

                    case Constant.FINISH_ACTIVITY_TEST: {
                        MySharedPreferences.putString(Constant.ON_APP,packageName);
                        finish();
                        break;
                    }
                    case Constant.ANSWER_FAIL:
                        diano.doFail();
                        handlerChangeUI.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                diano.doDance();
                            }
                        },1500);
                        break;

                    case Constant.SET_UNCLICKABLE: {
                        setAllClickable(false);
                        diano.doTrue();
                        handlerChangeUI.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                diano.doDance();
                            }
                        },1500);
                        break;
                    }

                    default: {
                        break;
                    }
                }
            }
        };
    }

    private void loadQuestions() {
        ApiClient apiClient = ApiClientUtils.getApiClient();
        apiClient.getAllQuestion(MySharedPreferences.getString(Constant.USER_NAME,""),MySharedPreferences.getInt(Constant.NUMBER_QUESTION,5)).enqueue(new Callback<ArrayList<FullQuestion>>() {
            @Override
            public void onResponse(Call<ArrayList<FullQuestion>> call, Response<ArrayList<FullQuestion>> response) {
                fullQuestions = response.body();
                playTest();
            }

            @Override
            public void onFailure(Call<ArrayList<FullQuestion>> call, Throwable t) {
                Toast.makeText(LockScreenActivity.this, "Network error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void run() {
        while (isGameRunning) {
            if (isNextQues) {
                timeNextQues++;
                if (isFirstPlay || timeNextQues == 1200) {
                    prepareNextQues();
                    isNextQues = false;
                    timeNextQues = -1;
                    isFirstPlay = false;
                    if (!isGameRunning) {
                        handlerChangeUI.sendEmptyMessage(Constant.FINISH_ACTIVITY_TEST);
                        return;
                    }
                }
            }

            if (ans != -1) {
                if (ans == question.getAnswer()) {
                    isNextQues = true;

                    MusicPlayer mpTrue1 = new MusicPlayer(R.raw.voice_true_1, LockScreenActivity.this);
                    MusicPlayer mpTrue2 = new MusicPlayer(voiceId, LockScreenActivity.this);
                    mpTrue1.play();
                    mpTrue2.play();
                    ans = -1;
                    timeNextQues = -1;
                    handlerChangeUI.sendEmptyMessage(Constant.SET_UNCLICKABLE);

                } else {
                    MusicPlayer mpFalse = new MusicPlayer(R.raw.voice_false, LockScreenActivity.this);
                    mpFalse.play();
                    ans = -1;
                    handlerChangeUI.sendEmptyMessage(Constant.ANSWER_FAIL);
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ans_hori_1:
            case R.id.ans_verti_1: {
                ans = 0;
                break;
            }

            case R.id.ans_hori_2:
            case R.id.ans_verti_2: {
                ans = 1;
                break;
            }

            case R.id.ans_hori_3:
            case R.id.ans_verti_3: {
                ans = 2;
                break;
            }

            case R.id.ans_hori_4:
            case R.id.ans_verti_4: {
                ans = 3;
                break;
            }

            case R.id.ic_volume: {
                if (question != null) {
                    MusicPlayer musicPlayer = new MusicPlayer(Config.BASE_URL + question.getAudio());
                    musicPlayer.play();
                }
                break;
            }

            default: {
                break;
            }
        }
    }

    private void playTest() {
        ans = -1;
        isGameRunning = true;
        isNextQues = true;
        timeNextQues = -1;
        isFirstPlay = true;
        new Thread(this).start();
    }

    private void prepareNextQues() {
        posQues++;
        if (posQues >= fullQuestions.size()) {
            isGameRunning = false;
            return;
        }
        fullQuestion = fullQuestions.get(posQues);
        question = fullQuestion.getQuestion();
        arrImg = question.getImages();
        handlerChangeUI.sendEmptyMessage(Constant.UPDATE_NEXT_QUESTION);
        ans = -1;
    }

}
