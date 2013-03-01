package pl.qbasso.videorecorder;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SliderPreference extends DialogPreference {

	private int mMinValue;
	private int mMaxValue;
	private SeekBar mBar;
	private TextView mTitleView;
	private TextView mCurrentValueView;

	public SliderPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public SliderPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		setDialogLayoutResource(R.layout.slider_dialog);
		TypedArray ar = context.obtainStyledAttributes(attrs,
				R.styleable.pl_qbasso_videorecorder_SliderPreference);
		mMinValue = ar.getInt(R.styleable.pl_qbasso_videorecorder_SliderPreference_min, 0);
		mMaxValue = ar.getInt(R.styleable.pl_qbasso_videorecorder_SliderPreference_max, 0);
	}

	@Override
	protected void onBindDialogView(View view) {
		// TODO Auto-generated method stub
		super.onBindDialogView(view);
		mBar = (SeekBar) view.findViewById(R.id.pref_seek_bar);
		mTitleView = (TextView) view.findViewById(R.id.pref_title);
		mCurrentValueView = (TextView) view.findViewById(R.id.pref_curr_val);
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		if (positiveResult) {
			persistInt(mBar.getProgress());
		}
	}
	

}
