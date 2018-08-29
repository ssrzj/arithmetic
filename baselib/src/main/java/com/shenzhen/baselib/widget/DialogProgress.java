package com.shenzhen.baselib.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shenzhen.baselib.R;
import com.shenzhen.baselib.utils.ViewBinderUtil;
import com.shenzhen.baselib.utils.ViewUtil;


public class DialogProgress extends DialogBase
{

	private LinearLayout mLlBlur = null;
	private TextView mTxtMsg = null;
	private ProgressBar mPbCircle = null;

	private DialogProgressListener mListener = null;

	public DialogProgress setmListener(DialogProgressListener mListener)
	{
		this.mListener = mListener;
		return this;
	}

	public DialogProgress setTextMsg(String msg)
	{
		if (msg != null)
		{
			mTxtMsg.setText(msg);
		}
		return this;
	}

	public DialogProgress(Context context)
	{
		super(context);
		init();
	}
	private void init()
	{
		View view = View.inflate(getContext(), R.layout.dialog_progress, null);
		mLlBlur = (LinearLayout) view.findViewById(R.id.dialog_progress_ll_blur);
		mTxtMsg = (TextView) view.findViewById(R.id.dialog_progress_txt_progress_msg);
		mPbCircle = (ProgressBar) view.findViewById(R.id.dialog_progress_pb_progress);
		mPbCircle.setIndeterminateDrawable(getContext().getResources().getDrawable(R.drawable.rotate_progress_white));

		setDialogView(view, false);
		initViewStates();

	}

	private void initViewStates()
	{
		setCancelable(false);
		MyDrawable drawable = new MyDrawable().color(Color.parseColor("#000000")).cornerAll(mConfig.getmCornerRadius()).alpha(0x55);

		ViewBinderUtil.setBackgroundDrawable(mLlBlur, drawable);
	}

	public DialogProgress height(int height)
	{
		ViewGroup.LayoutParams params = mLlBlur.getLayoutParams();
		params.height = ViewUtil.dp2px(height);
		mLlBlur.setLayoutParams(params);
		return this;
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{
		if (mListener != null)
		{
			mListener.onDismiss(DialogProgress.this);
		}
	}

	public interface DialogProgressListener
	{
		public void onDismiss(DialogProgress dialog);
	}

}