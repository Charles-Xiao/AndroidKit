package com.charlesxiao.androidkit.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	public void init() {
		setContentView();
		findViews();
		showContent();
		initLisenter();
	}

	public abstract void setContentView();

	public abstract void findViews();

	public abstract void showContent();

	public abstract void initLisenter();

}
