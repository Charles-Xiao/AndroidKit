package com.charlesxiao.androidkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.charlesxiao.androidkit.R;
import com.charlesxiao.androidkit.utils.LogUtils;

import java.util.List;

public class SquareListAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mDataList;
	private OnClickListener mSquareListener;

	public SquareListAdapter(Context context) {
		mContext = context;
		mSquareListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				LogUtils.d(v.getId() + " + " + v.getTag().toString());
				if (v.getId() == R.id.btn1_listitem_square) {
					LogUtils.d(v.getId() + " + " + v.getTag().toString());
				}
				if (v.getId() == R.id.btn2_listitem_square) {
					LogUtils.d(v.getId() + " + " + v.getTag().toString());
				}

			}
		};
	}

	public void setData(List<String> list) {
		mDataList = list;
	}

	@Override
	public int getCount() {
		return mDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		 * Listview缓存优化机制:View与Viewholder相结合,只new可见数目个item的View以及每个item相对应的Viewholder对象
		 * 其它item的View和Viewholder对象都从回收器中取出
		 * 1.使用setTag()函数绑定Viewholder到convertView，使用getTag()获取View对应的Viewholder对象
		 * 2.先判断convertView是否为null，再决定是否需要inflate xml文件
		 */
		final ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.listitem_square, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_listitem_square);
			viewHolder.btn1 = (Button) convertView.findViewById(R.id.btn1_listitem_square);
			viewHolder.btn2 = (Button) convertView.findViewById(R.id.btn2_listitem_square);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tv.setText(getItem(position).toString());
		viewHolder.btn1.setOnClickListener(mSquareListener);
		viewHolder.btn1.setTag(getItem(position));

		viewHolder.btn2.setOnClickListener(mSquareListener);
		viewHolder.btn2.setTag(getItem(position));

		return convertView;
	}

	public static final class ViewHolder {
		private TextView tv;
		private Button btn1;
		private Button btn2;

	}
}
