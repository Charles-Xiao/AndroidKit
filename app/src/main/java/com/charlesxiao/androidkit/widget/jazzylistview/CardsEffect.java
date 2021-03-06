package com.charlesxiao.androidkit.widget.jazzylistview;

import android.view.View;
import android.view.ViewPropertyAnimator;

public class CardsEffect implements JazzyEffect {

	private static final int INITIAL_ROTATION_ANGLE = 90;

	@Override
	public void initView(View item, int position, int scrollDirection) {
		item.setPivotX(item.getWidth() / 2);
		item.setPivotY(item.getHeight() / 2);
		item.setRotationX(INITIAL_ROTATION_ANGLE * scrollDirection);
		item.setTranslationY(item.getHeight() * scrollDirection);
	}

	@Override
	public void setupAnimation(View item, int position, int scrollDirection,
			ViewPropertyAnimator animator) {
		animator.rotationXBy(-INITIAL_ROTATION_ANGLE * scrollDirection)
				.translationYBy(-item.getHeight() * scrollDirection);
	}
}
