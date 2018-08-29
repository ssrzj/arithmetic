package com.shenzhen.baselib.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhen.baselib.R;
import com.shenzhen.baselib.utils.ResourcesUtil;
import com.shenzhen.baselib.utils.ViewBinderUtil;
import com.shenzhen.baselib.utils.ViewUtil;


public class DialogCustom extends DialogBase
{

	public View mView;
	public TextView mTvTitle;
	public LinearLayout mLlContent;
	public TextView mTvCancel;
	public TextView mTvConfirm;

	private SDDialogCustomListener mListener;

	public DialogCustom setmListener(SDDialogCustomListener mListener)
	{
		this.mListener = mListener;
		return this;
	}

	public DialogCustom(Context context)
	{
		super(context);
		init();
	}


	protected void init()
	{
		mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom, null);
		mTvTitle = (TextView) mView.findViewById(R.id.dialog_custom_tv_title);
		mLlContent = (LinearLayout) mView.findViewById(R.id.dialog_custom_ll_content);
		mTvCancel = (TextView) mView.findViewById(R.id.dialog_custom_tv_cancel);
		mTvConfirm = (TextView) mView.findViewById(R.id.dialog_custom_tv_confirm);

		setDialogView(mView);
		initViewStates();
	}

	private void initViewStates()
	{
		mTvTitle.setVisibility(View.GONE);
		mTvCancel.setVisibility(View.GONE);
		mTvConfirm.setVisibility(View.GONE);

		mTvCancel.setOnClickListener(this);
		mTvConfirm.setOnClickListener(this);

		setTextColorCancel(ResourcesUtil.getColor(R.color.main_color));
		setTextColorConfirm(ResourcesUtil.getColor(R.color.main_color));

		setTextTitle("提示").setTextConfirm("确定").setTextCancel("取消");
	}

	public DialogCustom setCustomView(View view)
	{
		setCustomView(view, null);
		return this;
	}

	public DialogCustom setCustomView(View view, LinearLayout.LayoutParams params)
	{
		mLlContent.removeAllViews();
		if (params == null)
		{
			params = ViewUtil.getLayoutParamsLinearLayoutMM();
		}
		mLlContent.addView(view, params);
		return this;
	}

	private void changeBackground()
	{
		if (mTvCancel.getVisibility() == View.VISIBLE && mTvConfirm.getVisibility() == View.VISIBLE)
		{
			ViewBinderUtil.setBackgroundDrawable(mTvCancel, getBackgroundBottomLeft());
			ViewBinderUtil.setBackgroundDrawable(mTvConfirm, getBackgroundBottomRight());
		} else if (mTvCancel.getVisibility() == View.VISIBLE)
		{
			ViewBinderUtil.setBackgroundDrawable(mTvCancel, getBackgroundBottomSingle());
		} else if (mTvConfirm.getVisibility() == View.VISIBLE)
		{
			ViewBinderUtil.setBackgroundDrawable(mTvConfirm, getBackgroundBottomSingle());
		}
	}

	// ---------------------------color

	public DialogCustom setTextColorTitle(int color)
	{
		mTvTitle.setTextColor(color);
		return this;
	}

	public DialogCustom setTextColorCancel(int color)
	{
		mTvCancel.setTextColor(color);
		return this;
	}

	public DialogCustom setTextColorConfirm(int color)
	{
		mTvConfirm.setTextColor(color);
		return this;
	}

	// ---------------------------text
	public DialogCustom setTextTitle(String text)
	{
		if (TextUtils.isEmpty(text))
		{
			mTvTitle.setVisibility(View.GONE);
		} else
		{
			mTvTitle.setVisibility(View.VISIBLE);
			mTvTitle.setText(text);
		}
		return this;
	}

	public DialogCustom setTextCancel(String text)
	{
		if (TextUtils.isEmpty(text))
		{
			mTvCancel.setVisibility(View.GONE);
		} else
		{
			mTvCancel.setVisibility(View.VISIBLE);
			mTvCancel.setText(text);
		}
		changeBackground();
		return this;
	}

	public DialogCustom setTextConfirm(String text)
	{
		if (TextUtils.isEmpty(text))
		{
			mTvConfirm.setVisibility(View.GONE);
		} else
		{
			mTvConfirm.setVisibility(View.VISIBLE);
			mTvConfirm.setText(text);
		}
		changeBackground();
		return this;
	}

	@Override
	public void onClick(View v)
	{
		if (v == mTvCancel)
		{
			clickCancel(v);
		} else if (v == mTvConfirm)
		{
			clickConfirm(v);
		}

	}

	private void clickCancel(View v)
	{
		if (mListener != null)
		{
			mListener.onClickCancel(v, DialogCustom.this);
		}
		if (mDismissAfterClick)
		{
			dismiss();
		}
	}

	private void clickConfirm(View v)
	{
		if (mListener != null)
		{
			mListener.onClickConfirm(v, DialogCustom.this);
		}
		if (mDismissAfterClick)
		{
			dismiss();
		}
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{
		if (mListener != null)
		{
			mListener.onDismiss(DialogCustom.this);
		}
	}

	public interface SDDialogCustomListener
	{
		public void onClickCancel(View v, DialogCustom dialog);

		public void onClickConfirm(View v, DialogCustom dialog);

		public void onDismiss(DialogCustom dialog);
	}
}
