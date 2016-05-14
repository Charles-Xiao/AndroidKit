package com.charlesxiao.androidkit.test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.charlesxiao.androidkit.R;

public class ZYActivity extends Activity {

    private SeekBar mSeekBar;
    private ActionFactory mActionFactory;
    private IAction mAction;
    private TextView brightnessTV;
    private TextView colorTV;
    private TextView saturationTV;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zy);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        brightnessTV = (TextView) findViewById(R.id.tv_brightness_val);
        colorTV = (TextView) findViewById(R.id.tv_color_val);
        saturationTV = (TextView) findViewById(R.id.tv_saturation_val);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_btn);

        // TODO 在UI界面中动态添加Button的方法
        // Button newBtn = addNewEvent(this, mLinearLayout, "newBtnText");

        mActionFactory = new ActionFactory();
        mSharedPreferences = this.getSharedPreferences("zy", Activity.MODE_PRIVATE);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAction.action(seekBar, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void actionClick(View v) {
        mAction = mActionFactory.buildAction(v.getId());
        getSharedPreference(v.getId());
    }

    /*
    工厂类负责生产不同的UI响应具体行为类
     */
    public class ActionFactory {
        public IAction buildAction(int id) {
            IAction action = null;
            switch (id) {
                case R.id.btn_brightness:

                    action = new BrightNessAction();
                    break;
                case R.id.btn_color:

                    action = new ColorAction();
                    break;
                case R.id.btn_saturation:

                    action = new SaturationAction();
                    break;
            }

            return action;
        }
    }

    /*
    抽象产品接口,action函数负责响应不同的调节行为
     */
    public interface IAction {
        void action(SeekBar seekBar, int progress);
    }

    public class BrightNessAction implements IAction {

        @Override
        public void action(SeekBar seekBar, int progress) {
            brightnessTV.setText(progress + "");
            editor = mSharedPreferences.edit();
            editor.putInt("brightness", progress);
            editor.commit();
            // TODO 调节亮度
            // Adjust(flag)


        }
    }

    public class ColorAction implements IAction {

        @Override
        public void action(SeekBar seekBar, int progress) {
            colorTV.setText(progress + "");
            editor = mSharedPreferences.edit();
            editor.putInt("color", progress);
            editor.commit();
            // TODO 调节色彩
            // Adjust(flag)
        }
    }

    public class SaturationAction implements IAction {

        @Override
        public void action(SeekBar seekBar, int progress) {
            saturationTV.setText(progress + "");
            editor = mSharedPreferences.edit();
            editor.putInt("saturation", progress);
            editor.commit();
            // TODO 调节亮度
            // Adjust(flag)
        }
    }
    /*
    动态添加Button方法
     */
    public static Button addNewEvent(Context context, LinearLayout linearLayout, String text) {
        Button newBtn = new Button(context);
        newBtn.setText(text);
        linearLayout.addView(newBtn);
        return newBtn;
    }

    /*
    利用SharedPreferences记录上一次设置的亮度,色彩，饱和度的值
     */
    public void getSharedPreference(int id) {
        switch (id) {
            case R.id.btn_brightness:
                mSeekBar.setProgress(mSharedPreferences.getInt("brightness", 0));
                break;
            case R.id.btn_color:
                mSeekBar.setProgress(mSharedPreferences.getInt("color", 0));
                break;
            case R.id.btn_saturation:
                mSeekBar.setProgress(mSharedPreferences.getInt("saturation", 0));
                break;
        }

    }

    // 真正调节亮度，色彩，饱和度的借口，根据flag的值执行不同的调节操作
    public void adjust(int flag) {

    }



}
