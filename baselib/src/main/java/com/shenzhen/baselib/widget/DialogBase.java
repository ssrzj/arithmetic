package com.shenzhen.baselib.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.shenzhen.baselib.BaseLib;
import com.shenzhen.baselib.R;
import com.shenzhen.baselib.utils.LogUtil;
import com.shenzhen.baselib.utils.ViewBinderUtil;
import com.shenzhen.baselib.utils.ViewUtil;
import com.shenzhen.baselib.widget.DrawableManager;
import com.shenzhen.baselib.widget.LibraryConfig;
import com.shenzhen.baselib.widget.MyDrawable;


public class DialogBase extends Dialog implements View.OnClickListener, OnDismissListener
{

	public static final int DEFAULT_PADDING = ViewUtil.dp2px(25);

	private View mView;
	public DrawableManager mDrawableManager;
	private LinearLayout mLlAll;
	private Activity  activity;
	protected LibraryConfig mConfig = BaseLib.getInstance().getLibraryConfig();
	protected boolean mDismissAfterClick = true;

	// ------------------getter setter-----------------

	public boolean ismDismissAfterClick()
	{
		return mDismissAfterClick;
	}

	public DialogBase setmDismissAfterClick(boolean mDismissAfterClick)
	{
		this.mDismissAfterClick = mDismissAfterClick;
		return this;
	}

	public DialogBase(Context context)
	{
		super(context, R.style.dialogBase);
		LogUtil.i(""+(context==null));
		activity = (Activity) context;
		baseInit();
	}

	public DialogBase(Context context, int style){
		super(context , style);
		baseInit();
	}


	private void initDrawable()
	{
		mDrawableManager = new DrawableManager();
	}

	private void baseInit()
	{

		mLlAll = new LinearLayout(getContext());
		mLlAll.setBackgroundColor(Color.parseColor("#00000000"));
		mLlAll.setGravity(Gravity.CENTER);
		this.setOnDismissListener(this);
		initDrawable();
		setCanceledOnTouchOutside(false);
	}

	@Override
	public void onClick(View v)
	{

	}

	// ---------------------show gravity

	public DialogBase setGrativity(int gravity)
	{
		getWindow().setGravity(gravity);
		return this;
	}

	public void showTop() {
		if (!activity.isFinishing()&&activity!=null) {
			showTop(true);
		}
	}
	public void showTop(boolean anim)
	{
		setGrativity(Gravity.TOP);
		if (anim)
		{
			//		setAnimations(R.style.anim_top_top);
		}
		if(!activity.isFinishing()&&activity!=null) {
			show();
		}
	}

	public void showBottom()
	{
		if(!activity.isFinishing()&&activity!=null) {
			showBottom(true);
		}
	}

	public void showBottom(boolean anim)
	{
		setGrativity(Gravity.BOTTOM);
		if (anim)
		{
			//		setAnimations(R.style.anim_bottom_bottom);
		}
		if(!activity.isFinishing()&&activity!=null) {
			show();
		}
	}

	public void showCenter()
	{
		setGrativity(Gravity.CENTER);
		if(!activity.isFinishing()&&activity!=null) {
			show();
		}
	}

	public void setAnimations(int resId)
	{
		getWindow().setWindowAnimations(resId);
	}

	// -----------------------padding

	public DialogBase paddingTopBottom(int topBottom)
	{
		mLlAll.setPadding(mLlAll.getPaddingLeft(), topBottom, mLlAll.getPaddingRight(), topBottom);
		return this;
	}

	public DialogBase paddingLeftRight(int leftRight)
	{
		mLlAll.setPadding(leftRight, mLlAll.getPaddingTop(), leftRight, mLlAll.getPaddingBottom());
		return this;
	}

	public DialogBase paddings(int paddings)
	{
		padding(paddings, paddings, paddings, paddings);
		return this;
	}

	public DialogBase padding(int left, int top, int right, int bottom)
	{
		mLlAll.setPadding(left, top, right, bottom);
		return this;
	}

	// -----------------------------layoutParams

	public WindowManager.LayoutParams getLayoutParams()
	{
		return getWindow().getAttributes();
	}

	public DialogBase setLayoutParams(WindowManager.LayoutParams params)
	{
		getWindow().setAttributes(params);
		return this;
	}

	// ----------------------dialogView

	public DialogBase setDialogView(View view)
	{
		return setDialogView(view, null, true);
	}

	public DialogBase setDialogView(View view, boolean needDefaultBackground)
	{
		return setDialogView(view, null, needDefaultBackground);
	}

	public DialogBase setDialogView(View view, LayoutParams params, boolean needDefaultBackground)
	{
		mView = view;
		wrapperView(mView);
		setDefaultBackgroundEnable(needDefaultBackground);
		if (params == null)
		{
			params = new LayoutParams(ViewUtil.getScreenWidth(), LayoutParams.WRAP_CONTENT);
		}
		paddings(DEFAULT_PADDING);
		super.setContentView(mLlAll, params);
		return this;
	}

	public DialogBase setDefaultBackgroundEnable(boolean enable)
	{
		if (enable)
		{
			ViewBinderUtil.setBackgroundDrawable(mView, new MyDrawable().cornerAll(mConfig.getmCornerRadius()));
		} else
		{
			ViewBinderUtil.setBackgroundDrawable(mView, null);
		}
		return this;
	}

	private void wrapperView(View view)
	{
		mLlAll.removeAllViews();
		mLlAll.addView(view, ViewUtil.getLayoutParamsLinearLayoutMM());
	}

	public View getDialogView()
	{
		return mView;
	}

	// ------------------------setContentView

	@Override
	public void setContentView(int layoutResID)
	{
		this.setContentView(ViewUtil.inflate(layoutResID, null));
	}

	@Override
	public void setContentView(View view)
	{
		this.setContentView(view, null);
	}

	@Override
	public void setContentView(View view, LayoutParams params)
	{
		setDialogView(view, params, false);
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{

	}

	// ------------------------------background

	/**
	 * 边框：top，right 圆角：bottomLeft
	 *
	 * @return
	 */
	public Drawable getBackgroundBottomLeft()
	{
		MyDrawable drawableCancel = new MyDrawable();
		drawableCancel.strokeColor(mConfig.getmStrokeColor()).strokeWidth(0, mConfig.getmStrokeWidth(), mConfig.getmStrokeWidth(), 0)
				.cornerBottomLeft(mConfig.getmCornerRadius());

		MyDrawable drawableCancelPressed = new MyDrawable();
		drawableCancelPressed.strokeColor(mConfig.getmStrokeColor()).color(mConfig.getmGrayPressColor())
				.strokeWidth(0, mConfig.getmStrokeWidth(), mConfig.getmStrokeWidth(), 0).cornerBottomLeft(mConfig.getmCornerRadius());

		return MyDrawable.getStateListDrawable(drawableCancel, null, null, drawableCancelPressed);
	}

	/**
	 * 边框：top 圆角：bottomRight
	 *
	 * @return
	 */
	public Drawable getBackgroundBottomRight()
	{
		MyDrawable drawableConfirm = new MyDrawable();
		drawableConfirm.strokeColor(mConfig.getmStrokeColor()).strokeWidth(0, mConfig.getmStrokeWidth(), 0, 0)
				.cornerBottomRight(mConfig.getmCornerRadius());

		MyDrawable drawableConfirmPressed = new MyDrawable();
		drawableConfirmPressed.strokeColor(mConfig.getmStrokeColor()).color(mConfig.getmGrayPressColor())
				.strokeWidth(0, mConfig.getmStrokeWidth(), 0, 0).cornerBottomRight(mConfig.getmCornerRadius());

		return MyDrawable.getStateListDrawable(drawableConfirm, null, null, drawableConfirmPressed);
	}

	/**
	 * 边框：top 圆角：bottomLeft，bottomRight
	 *
	 * @return
	 */
	public Drawable getBackgroundBottomSingle()
	{
		MyDrawable drawableConfirm = new MyDrawable();
		drawableConfirm.strokeColor(mConfig.getmStrokeColor()).strokeWidth(0, mConfig.getmStrokeWidth(), 0, 0)
				.corner(0, 0, mConfig.getmCornerRadius(), mConfig.getmCornerRadius());

		MyDrawable drawableConfirmPressed = new MyDrawable();
		drawableConfirmPressed.strokeColor(mConfig.getmStrokeColor()).color(mConfig.getmGrayPressColor())
				.strokeWidth(0, mConfig.getmStrokeWidth(), 0, 0).corner(0, 0, mConfig.getmCornerRadius(), mConfig.getmCornerRadius());
		return MyDrawable.getStateListDrawable(drawableConfirm, null, null, drawableConfirmPressed);
	}
}
