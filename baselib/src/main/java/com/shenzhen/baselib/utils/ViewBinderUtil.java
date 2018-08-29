package com.shenzhen.baselib.utils;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class ViewBinderUtil {

        public static boolean mCanLoadImageFromUrl = true;

        public static void setBackgroundDrawable(View view, Drawable drawable)
        {
                view.setBackgroundDrawable(drawable);
        }

        public static void setRatingBar(RatingBar ratingBar, float rating)
        {
                ratingBar.setRating(rating);
        }

        public static void setTextView(TextView textView, CharSequence content, CharSequence emptyTip)
        {
                if (!TextUtils.isEmpty(content))
                {
                        textView.setText(content);
                } else
                {
                        textView.setText(emptyTip);
                }
        }

        public static void setTextView(TextView textView, CharSequence content)
        {
                setTextView(textView, content, "");
        }

        public static void setTextViewHtml(TextView textView, String contentHtml)
        {
                setTextView(textView, Html.fromHtml(contentHtml), "");
        }

        public static void setTextViewHtml(TextView textView, String contentHtml, String emptyTip)
        {
                setTextView(textView, Html.fromHtml(contentHtml), emptyTip);
        }

        public static void setTextViewsVisibility(TextView textView, CharSequence content)
        {
                if (TextUtils.isEmpty(content))
                {
                        textView.setVisibility(View.GONE);
                } else
                {
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(content);
                }
        }

        public static void setTextViewColorByColorId(TextView textView, int resId)
        {
                textView.setTextColor(ResourcesUtil.getColor(resId));
        }

        public static boolean setViewsVisibility(View view, boolean visible)
        {
                int state = view.getVisibility();
                if (visible)
                {
                        if (state != View.VISIBLE)
                        {
                                view.setVisibility(View.VISIBLE);
                        }
                        return true;
                } else
                {
                        if (state != View.GONE)
                        {
                                view.setVisibility(View.GONE);
                        }
                        return false;
                }
        }

        public static boolean setViewsVisibility(View view, int visible)
        {
                if (visible == 0)
                {
                        return setViewsVisibility(view, false);
                } else if (visible == 1)
                {
                        return setViewsVisibility(view, true);
                }
                return false;
        }

}
