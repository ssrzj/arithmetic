package com.shenzhen.baselib.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shenzhen.baselib.R;
import com.shenzhen.baselib.utils.ViewUtil;


/**
 * 带标题，内容，确定按钮和取消按钮的窗口
 * 
 * @author js02
 * 
 */
public class DialogConfirm extends DialogCustom
{

	private TextView mTvContent;


	public DialogConfirm(Context context)
	{
		super(context);
	}

	@Override
	protected void init()
	{
		super.init();
		addTextView();
	}

	public DialogConfirm setTextContent(String text)
	{
		if (TextUtils.isEmpty(text))
		{
			mTvContent.setVisibility(View.GONE);
		} else
		{
			mTvContent.setVisibility(View.VISIBLE);
			mTvContent.setText(text);
		}
		return this;
	}

	private void addTextView()
	{
		View view = ViewUtil.inflate(R.layout.dialog_confirm, null);
		mTvContent = (TextView) view.findViewById(R.id.dialog_confirm_tv_content);
		LinearLayout.LayoutParams params = ViewUtil.getLayoutParamsLinearLayoutWW();
		setCustomView(view, params);
	}

}
