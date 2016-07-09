package com.sheepyang.youkumenu.ui.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationUtils {


	// 正在运行的动画个数
	public static int runningAnimationCount = 0;

	public static void rotateOut(RelativeLayout layout, long delay) {
		ObjectAnimator rotate = ObjectAnimator.ofFloat(layout, "rotation", 0f, -180f);
		layout.setPivotX(layout.getWidth()/2);
		layout.setPivotY(layout.getHeight());
		//显示的调用invalidate
		layout.invalidate();
		rotate.setDuration(500);
		rotate.setStartDelay(delay);
		rotate.addListener(new MyAnimationListener2());
		rotate.start();
	}

	public static void rotateIn(RelativeLayout layout, long delay) {
		ObjectAnimator rotate = ObjectAnimator.ofFloat(layout, "rotation", -180f, 0f);
		layout.setPivotX(layout.getWidth()/2);
		layout.setPivotY(layout.getHeight());
		//显示的调用invalidate
		layout.invalidate();
		rotate.setDuration(500);
		rotate.setStartDelay(delay);
		rotate.addListener(new MyAnimationListener2());
		rotate.start();
	}
	// 旋转出去的动画
	public static void rotateOutAnim(RelativeLayout layout, long delay) {
		int childCount = layout.getChildCount();
		// 如果隐藏. 则找到所有的子View, 禁用
		for (int i = 0; i < childCount; i++) {
			layout.getChildAt(i).setEnabled(false);
		}
		
		RotateAnimation ra = new RotateAnimation(
				0f, -180f, // 开始, 结束的角度, 逆时针
				Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
				Animation.RELATIVE_TO_SELF, 1.0f); // 相对的y坐标点(指定旋转中心y值)
		
		ra.setDuration(500);
		ra.setFillAfter(true); // 设置动画停留在结束位置
		ra.setStartOffset(delay); // 设置动画开始延时
		ra.setAnimationListener(new MyAnimationListener()); // 添加监听
		
		layout.startAnimation(ra);
	}

	// 旋转进来的动画
	public static void rotateInAnim(RelativeLayout layout, long delay) {

		int childCount = layout.getChildCount();
		// 如果隐藏. 则找到所有的子View, 启用
		for (int i = 0; i < childCount; i++) {
			layout.getChildAt(i).setEnabled(true);
		}
		
		RotateAnimation ra = new RotateAnimation(
				-180f, 0f, // 开始, 结束的角度, 顺时针
				Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
				Animation.RELATIVE_TO_SELF, 1.0f); // 相对的y坐标点(指定旋转中心y值)
		
		ra.setDuration(500);
		ra.setFillAfter(true);
		ra.setStartOffset(delay); // 设置动画开始延时
		ra.setAnimationListener(new MyAnimationListener()); // 添加监听
		
		layout.startAnimation(ra);
	}

	static class MyAnimationListener2 implements Animator.AnimatorListener {

		@Override
		public void onAnimationStart(Animator animation) {
			runningAnimationCount ++;
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			runningAnimationCount --;
		}

		@Override
		public void onAnimationCancel(Animator animation) {

		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	}

	static class MyAnimationListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
			runningAnimationCount ++;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			runningAnimationCount --;
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
		}

	}

}
