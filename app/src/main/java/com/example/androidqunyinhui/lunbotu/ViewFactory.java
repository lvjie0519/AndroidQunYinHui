package com.example.androidqunyinhui.lunbotu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.androidqunyinhui.R;
/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 *
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.view_banner, null);
		Glide.with(context)
				.load(url)
				.placeholder(R.drawable.icon_default)
				.error(R.drawable.icon_default)
				.into(imageView);
		return imageView;
	}
}
