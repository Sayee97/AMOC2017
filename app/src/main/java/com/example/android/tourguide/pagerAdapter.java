package com.example.android.tourguide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by SAYEE on 18-03-2017.
 */

public class pagerAdapter extends PagerAdapter {


    private int[] imageRes = {R.drawable.rsz_img1,R.drawable.rsz_img2,R.drawable.rsz_img7,R.drawable.rsz_img8};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public pagerAdapter(Context ctx){
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return imageRes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        imageView.setImageResource(imageRes[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);

    }


}
