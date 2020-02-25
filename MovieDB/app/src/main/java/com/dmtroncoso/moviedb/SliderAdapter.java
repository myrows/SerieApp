package com.dmtroncoso.moviedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public int[] slideImages = {
            R.drawable.ic_g1,
            R.drawable.ic_g2,
            R.drawable.ic_g3
    };

    public String[] slideHeading = {
            "EAT",
            "SLEEP",
            "CODE"
    };

    public String[] slideDesc = {
            "Recomendable ver series mientras desayunas, almuerzas o cenas, si estás con compañía o si estás disfrutando del día solo",
            "Dormir 8 h es recomendable para la salud mental y física por eso ver una serie o película te ayudará a concebir mejor el sueño",
            "Aplicación creada por Daniel Troncoso, un jóven desarrollador de aplicaciones móviles en lenguaje Java"
    };

    @Override
    public int getCount() {
        return slideHeading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slideImages);
        TextView slideHeadingText = view.findViewById(R.id.slideHeading);
        TextView slideDescText = view.findViewById(R.id.slideDesc);

        slideImageView.setImageResource(slideImages[position]);
        slideHeadingText.setText(slideHeading[position]);
        slideDescText.setText(slideDesc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
