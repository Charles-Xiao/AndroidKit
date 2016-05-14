package com.charlesxiao.androidkit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlesxiao.androidkit.R;
import com.charlesxiao.androidkit.widget.SquareListAdapter;
import com.charlesxiao.androidkit.widget.jazzylistview.CardsEffect;
import com.charlesxiao.androidkit.widget.jazzylistview.ChildAnimation;
import com.charlesxiao.androidkit.widget.jazzylistview.JazzyListView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;

public class SquareFragment extends Fragment implements
		BaseSliderView.OnSliderClickListener {

	private View mRootView;
	private SliderLayout mSliderLayout;
	private JazzyListView mJazzyListView;
	HashMap<String, String> url_maps = new HashMap<String, String>();
	HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
	private ArrayList<String> mList = new ArrayList<String>();

	public SquareFragment() {
		for (int i = 0; i < 16; i++) {
			mList.add("TextView " + i);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater
				.inflate(R.layout.fragment_square, container, false);
		mSliderLayout = (SliderLayout) mRootView
				.findViewById(R.id.sl_square_banner);
		mJazzyListView = (JazzyListView) mRootView
				.findViewById(R.id.jlv_square_plan);
		mJazzyListView.setTransitionEffect(new CardsEffect());
		SquareListAdapter mSquareListAdapter = new SquareListAdapter(
				getActivity());
		mSquareListAdapter.setData(mList);
		mJazzyListView.setAdapter(mSquareListAdapter);

		setSliderLsyout();
		return mRootView;
	}

	private void setSliderLsyout() {
		mSliderLayout
				.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		mSliderLayout.setCustomAnimation(new ChildAnimation());
		mSliderLayout.setDuration(3000);

		url_maps.put(
				"Hannibal",
				"http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
		url_maps.put("Big Bang Theory",
				"http://tvfiles.alphacoders.com/100/hdclearart-10.png");
		url_maps.put("House of Cards",
				"http://cdn3.nflximg.net/images/3093/2043093.jpg");
		url_maps.put(
				"Game of Thrones",
				"http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

		file_maps.put("Hannibal", R.drawable.hannibal);
		file_maps.put("Big Bang Theory", R.drawable.bigbang);
		file_maps.put("House of Cards", R.drawable.house);
		file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

		for (String name : file_maps.keySet()) {
			TextSliderView textSliderView = new TextSliderView(getActivity());
			textSliderView.description(name).image(file_maps.get(name))
					.setScaleType(BaseSliderView.ScaleType.Fit)
					.setOnSliderClickListener(this);

//			textSliderView.getBundle().putString("extra", name);

			mSliderLayout.addSlider(textSliderView);
		}

	}

	@Override
	public void onSliderClick(BaseSliderView slider) {
//		Toast.makeText(getActivity(),
//				url_maps.get(slider.getBundle().get("extra")),
//				Toast.LENGTH_SHORT).show();
	}
}
