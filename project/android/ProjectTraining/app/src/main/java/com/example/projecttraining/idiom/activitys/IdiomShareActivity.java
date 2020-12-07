package com.example.projecttraining.idiom.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projecttraining.R;
import com.example.projecttraining.idiom.FlowLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-12-6
 * @author lrf
 */
public class IdiomShareActivity extends AppCompatActivity {

    private String idiomName;
    private String idiomPronounce;
    private String idiomContent;
    private List<String> idiomAntonym = new ArrayList<>();
    private List<String> idiomThesaurus = new ArrayList<>();
    private String idiomComefrom;
    private String idiomExample;

    @BindView(R.id.idiom_share) RelativeLayout idiomShare;
    @BindView(R.id.back) ImageView back;
    @BindView(R.id.idiom_share_name) TextView idiomShareName;
    @BindView(R.id.idiom_share_pronounce) TextView idiomSharePronounce;
    @BindView(R.id.idiom_share_mean) TextView idiomShareMean;
    @BindView(R.id.idiom_share_thesaurus) LinearLayout idiomShareThesaurus;
    @BindView(R.id.idiom_share_antonym) LinearLayout idiomShareAntonym;
    @BindView(R.id.idiom_share_comefrom) TextView idiomShareComefrom;
    @BindView(R.id.idiom_share_example) TextView idiomShareExample;

    @BindView(R.id.share_now) Button shareNow;
    @BindView(R.id.save_idiom) Button saveIdiom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idiom_share);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        idiomName = bundle.getString("name");
        idiomPronounce = bundle.getString("pronounce");
        idiomContent = bundle.getString("content");
        idiomAntonym = bundle.getStringArrayList("antonym");
        idiomThesaurus = bundle.getStringArrayList("thesaurus");
        idiomComefrom = bundle.getString("comefrom");
        idiomExample = bundle.getString("example");

        initView();

    }

    private void initView() {
        idiomShareName.setText(idiomName);
        idiomSharePronounce.setText(idiomPronounce);

        if(null != idiomContent && !idiomContent.equals("null")){
            idiomShareMean.setText(idiomContent);
        } else {
            idiomShareMean.setText("暂无该成语的解释");
        }

        if(null != idiomThesaurus){
            FlowLayout flowLayout = new FlowLayout(this);
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomShareThesaurus.addView(flowLayout);

            for(int i = 0; i < idiomThesaurus.size(); ++i){
                final TextView textView = new TextView(this);
                textView.setId(i);
                textView.setTextSize(16);
                textView.setTextColor(Color.BLACK);
                textView.setText(idiomThesaurus.get(i));
                textView.setPadding(20,20,20,20);
                //添加到布局中
                flowLayout.addView(textView);
                //更新界面
                flowLayout.invalidate();
            }
        }else {
            FlowLayout flowLayout = new FlowLayout(this);
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomShareThesaurus.addView(flowLayout);

            TextView textView = new TextView(this);
            textView.setText("暂无该成语的近义词");
            textView.setTextSize(18);
            textView.setTextColor(Color.BLACK);
            //添加到布局文件中去
            flowLayout.addView(textView);
            //更新界面
            flowLayout.invalidate();
        }

        if(null != idiomAntonym){
            FlowLayout flowLayout = new FlowLayout(this);
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomShareAntonym.addView(flowLayout);

            for(int i = 0; i < idiomAntonym.size(); ++i){
                final TextView textView = new TextView(this);
                textView.setId(i);
                textView.setTextSize(16);
                textView.setTextColor(Color.BLACK);
                textView.setText(idiomAntonym.get(i));
                textView.setPadding(20,20,20,20);
                //添加到布局中
                flowLayout.addView(textView);
                //更新界面
                flowLayout.invalidate();
            }
        }else {
            FlowLayout flowLayout = new FlowLayout(this);
            flowLayout.setPadding(20,20,20,20);
            flowLayout.setVerticalSpacing(30);
            flowLayout.setHorizontalSpacing(30);

            idiomShareAntonym.addView(flowLayout);

            TextView textView = new TextView(this);
            textView.setText("暂无该成语的反义词");
            textView.setTextSize(18);
            textView.setTextColor(Color.BLACK);
            //添加到布局文件中去
            flowLayout.addView(textView);
            //更新界面
            flowLayout.invalidate();
        }

        if(null != idiomComefrom && !idiomComefrom.equals("null")){
            idiomShareComefrom.setText(idiomComefrom);
        }else {
            idiomShareComefrom.setText("暂无该成语的典故出处");
        }

        if(null != idiomExample && !idiomExample.equals("")){
            idiomShareExample.setText(idiomExample);
        }else {
            idiomShareExample.setText("暂无该成语的例句");
        }

    }

    // 返回
    @OnClick(R.id.back)
    public void clickToBack(){
        finish();
    }

    // 将截图分享
    @OnClick(R.id.share_now)
    public void clickShare(){
        // TODO
        Bitmap bitmap = cutScreenImg(idiomShare);
        Toast.makeText(this,"立即分享",Toast.LENGTH_SHORT).show();
    }

    // 将截图保存到本地相册
    @OnClick(R.id.save_idiom)
    public void clickSaveIdiom(){
        Bitmap bitmap = cutScreenImg(idiomShare);
        boolean flag = saveImageToGallery(this,bitmap);
        if(flag){
            Toast.makeText(this,"保存相册成功！",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"保存相册失败！",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 生成截图
     * @return
     */
    private Bitmap cutScreenImg(RelativeLayout relativeLayout){
        Bitmap bitmap = null;
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(),
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        relativeLayout.draw(canvas);
        return bitmap;
    }

    /**
     * 将图片保存到手机相册
     * @param context
     * @param bmp
     * @return
     */
    public boolean saveImageToGallery(Context context, Bitmap bmp) {
        boolean flag = false;
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "TianTian");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            flag = bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));
        return flag;
    }

}
