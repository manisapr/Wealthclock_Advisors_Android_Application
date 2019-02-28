package android.wealthclockadvisors.app.wealthclockadvisors.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import wealthclockadvisors.app.wealthclockadvisors.R;

public class ImageAdapter extends PagerAdapter {

    Context mContext;

    public ImageAdapter(Context context) {

        this.mContext = context;

    }



    @Override

    public boolean isViewFromObject(View view, Object object) {

        return view == ((ImageView) object);

    }



    private int[] sliderImageId = new int[]{

            R.drawable.lifeinsurance_small,R.drawable.motorinsurance_small,R.drawable.healthinsurancesmall,

    };



    @Override

    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);

        //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setScaleType(ImageView.ScaleType.FIT_START);

        imageView.setAdjustViewBounds(true);

        imageView.setImageResource(sliderImageId[position]);

        ((ViewPager) container).addView(imageView, 0);

        return imageView;

    }



    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);

    }



    @Override

    public int getCount() {

        return sliderImageId.length;

    }


}
