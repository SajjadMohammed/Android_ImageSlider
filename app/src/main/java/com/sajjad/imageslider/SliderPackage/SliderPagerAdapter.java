package com.sajjad.imageslider.SliderPackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sajjad.imageslider.R;

import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private List<Integer> imageResources;
    private List<String> titles;

    public SliderPagerAdapter(List<Integer> imageResources) {
        this.imageResources = imageResources;
        titles=new ArrayList<>();
        titles.add("A");
        titles.add("B");
        titles.add("C");
    }

    @Override
    public int getCount() {
        return imageResources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_item, container, false);

        ImageView sliderImage = view.findViewById(R.id.sliderImage);
        sliderImage.setImageResource(imageResources.get(position));

        Animation animation =
                AnimationUtils.loadAnimation(container.getContext(), android.R.anim.fade_in);
        animation.setDuration(2000);
        animation.start();

        sliderImage.setAnimation(animation);

        TextView sliderTitle = view.findViewById(R.id.sliderTitle);
        sliderTitle.setText(titles.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
