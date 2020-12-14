package com.example.projecttraining.home.fragments.MomentsFragment.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MomentsFragment.Util.WindowSize;

import java.util.List;

public class MyGridViewAdapter extends BaseAdapter {
    private Context context;
    private int num;
    private int col;
    private List<String> pictureUrl;
    // 保存下当前动画类，以便可以随时结束动画
    private Animator mCurrentAnimator;
    //系统的短时长动画持续时间（单位ms）
    // 对于不易察觉的动画或者频繁发生的动画
    // 这个动画持续时间是最理想的
    private int mShortAnimationDuration;
    private View view;


    public MyGridViewAdapter(Context context, int num, int col, List<String> pictureUrl,View view) {
        this.context = context;
        this.num = num;
        this.col = col;
        this.pictureUrl = pictureUrl;
        this.view = view;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return num;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView img = new ImageView(context);
        View thumbView = img;
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        int width = WindowSize.getWidth(context);// 获取屏幕宽度
        Log.i("tag", "width" + width);
        int height = 0;
        width = width / col;// 对当前的列数进行设置imgView的宽度
        height = width;
        img.setLayoutParams(new AbsListView.LayoutParams(width-80, height));
        //img.setImageResource(R.drawable.banner1);
        Glide.with(context)
                .load(pictureUrl.get(position))
                .into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "是心动啊" + position, 0).show();

            }
        });
        thumbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomImageFromThumb(thumbView,pictureUrl.get(position),view);
            }
        });
        return img;
     }

    //预览图片的方法
    private void zoomImageFromThumb(final View thumbView, String imgUrl, View view) {
        // 如果有动画在执行，立即取消，然后执行现在这个动画
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        // 加载高分辨率的图片

        ImageView expandedImageView = (ImageView) view.findViewById(
                R.id.expanded_image);
        Glide.with(context)
                .load(imgUrl)
                .into(expandedImageView);
        // 计算开始和结束位置的图片范围
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();
        // 开始的范围就是ImageButton的范围，
        // 结束的范围是容器（FrameLayout）的范围
        // getGlobalVisibleRect(Rect)得到的是view相对于整个硬件屏幕的Rect
        // 即绝对坐标，减去偏移，获得动画需要的坐标，即相对坐标
        // getGlobalVisibleRect(Rect,Point)中，Point获得的是view在它在
        // 父控件上的坐标与在屏幕上坐标的偏移
        thumbView.getGlobalVisibleRect(startBounds);
        view.findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        // 下面这段逻辑其实就是保持纵横比
        float startScale;
        // 如果结束图片的宽高比比开始图片的宽高比大
        // 就是结束时“视觉上”拉宽了（压扁了）图片
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }
        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        // 隐藏小的图片，展示大的图片。当动画开始的时候，
        // 要把大的图片发在小的图片的位置上
        //小的设置透明
        thumbView.setAlpha(0f);
        //大的可见
        expandedImageView.setVisibility(View.VISIBLE);
        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);
        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }
            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;
        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        // 再次点击返回小的图片，就是上面扩大的反向动画。即预览完成
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }
                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
