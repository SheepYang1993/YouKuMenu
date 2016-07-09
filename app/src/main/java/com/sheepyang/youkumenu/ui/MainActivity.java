package com.sheepyang.youkumenu.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sheepyang.youkumenu.R;
import com.sheepyang.youkumenu.ui.BaseActivity;
import com.sheepyang.youkumenu.ui.util.AnimationUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;
    boolean isLevel3Display = true;
    boolean isLevel2Display = true;
    boolean isLevel1Display = true;
    private ImageButton ib_home;
    private ImageButton ib_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
        ib_home = (ImageButton) findViewById(R.id.ib_home);
        ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        ib_home.setOnClickListener(this);
        ib_menu.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (AnimationUtils.runningAnimationCount > 0) {
                return true;
            }
//			如果按下的是菜单按钮
            if(isLevel1Display){
                long delay = 0;
                // 隐藏三级菜单
                if(isLevel3Display){
                    AnimationUtils.rotateOut(rl_level3, 0);
                    isLevel3Display = false;
                    delay += 200;
                }
                // 隐藏二级菜单
                if(isLevel2Display){
                    AnimationUtils.rotateOut(rl_level2, delay);
                    isLevel2Display = false;
                    delay += 200;
                }
                // 隐藏一级菜单
                AnimationUtils.rotateOut(rl_level1, delay);
            }else {
                // 顺次转进来
                AnimationUtils.rotateIn(rl_level1, 0);
                AnimationUtils.rotateIn(rl_level2, 200);
                AnimationUtils.rotateIn(rl_level3, 400);
                isLevel3Display = true;
                isLevel2Display = true;
            }
            isLevel1Display = !isLevel1Display;
            return true;// 消费了当前事件
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (AnimationUtils.runningAnimationCount > 0) {
            return;
        }
        switch (v.getId()) {
            case R.id.ib_home:
                if (isLevel2Display) {
                    long delay = 0;
                    // 如果当前三级菜单已经显示, 先转出去
                    if (isLevel3Display) {
                        AnimationUtils.rotateOut(rl_level3, 0);
                        isLevel3Display = !isLevel3Display;
                        delay += 200;
                    }
                    // 如果当前二级菜单已经显示, 转出去
                    AnimationUtils.rotateOut(rl_level2, delay);
                } else {
                    // 如果当前二级菜单没有显示, 转出来
                    AnimationUtils.rotateIn(rl_level2, 0);
                    // 如果当前二级菜单没有显示, 转出来
//                    AnimationUtils.rotateIn(rl_level3, 200);
//                    isLevel3Display = !isLevel3Display;
                }
                // 置反
                isLevel2Display = !isLevel2Display;
                break;
            case R.id.ib_menu:
                if (isLevel3Display) {
                    // 如果当前三级菜单已经显示, 转出去
                    AnimationUtils.rotateOut(rl_level3, 0);
                } else {
                    // 如果当前三级菜单没有显示, 转出来
                    AnimationUtils.rotateIn(rl_level3, 0);
                }
                // 置反
                isLevel3Display = !isLevel3Display;
                break;
            default:
                break;
        }
    }
}
