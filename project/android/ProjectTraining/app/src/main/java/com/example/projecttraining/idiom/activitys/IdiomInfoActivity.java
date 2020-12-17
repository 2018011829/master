package com.example.projecttraining.idiom.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.projecttraining.ChangeStatusBarColor;
import com.example.projecttraining.R;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.idiom.entity.IdiomInfo;
import com.example.projecttraining.idiom.entity.IdiomInfoResult;
import com.example.projecttraining.idiom.fragment.IdiomAllusionFragment;
import com.example.projecttraining.idiom.fragment.IdiomExampleSentenceFragment;
import com.example.projecttraining.idiom.fragment.IdiomMeanFragment;
import com.example.projecttraining.idiom.fragment.IdiomNearAntonymsFragment;
import com.example.projecttraining.idiom.read.control.InitConfig;
import com.example.projecttraining.idiom.read.listener.UiMessageListener;
import com.example.projecttraining.idiom.read.util.Auth;
import com.example.projecttraining.idiom.read.util.AutoCheck;
import com.example.projecttraining.idiom.read.util.IOfflineResourceConst;
import com.example.projecttraining.util.ConfigUtil;
import com.example.projecttraining.util.IdiomJsonUtil;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 2020-11-26
 * 2020-11-28
 * 2020-11-30
 * 2020-12-2
 * 2020-12-5
 * 2020-12-6
 * 2020-12-15
 * @author lrf
 */
public class IdiomInfoActivity extends AppCompatActivity implements IOfflineResourceConst {

    private int src;// 成语收藏图片保存器
    private String idiomName;
    private Handler myHandler;
    private IdiomInfoResult idiomInfoResult;
    private String phoneNum = MyFragment.phoneNum;
    private String childName = MyFragment.childName;
    private String APPKEY = "922ee172234b0479";
    private String url = "https://api.jisuapi.com/chengyu/detail";

    @BindView(R.id.tv_idiom_name) TextView tvIdiomName;
    @BindView(R.id.tv_idiom_pronounce) TextView tvIdiomPronounce;
    @BindView(R.id.idiom_info_tab) TabLayout tabLayout;
    @BindView(R.id.idiom_view_pager) ViewPager viewPager;
    @BindView(R.id.read_idiom) LinearLayout readIdiom;
    @BindView(R.id.linear_forward) ImageView linearForward;
    @BindView(R.id.idiom_shoucang) ImageView idiomShouCang;
    @BindView(R.id.idiom_fenxiang) ImageView idiomFenXiang;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String string = (String) msg.obj;
                    switch (string){
                        case "该成语已被收藏":
                            //初始化成语收藏图片保存器
                            src = R.drawable.idiom_yishoucang;
                            idiomShouCang.setImageResource(src);
                            Toast.makeText(getBaseContext(),string,Toast.LENGTH_SHORT).show();
                            break;
                        case "该成语未被收藏":
                            //初始化成语收藏图片保存器
                            src = R.drawable.idiom_shoucang;
                            idiomShouCang.setImageResource(src);
                            break;
                        case "成语收藏失败！":
                            Toast.makeText(getBaseContext(),string,Toast.LENGTH_SHORT).show();
                            break;
                        case "成语收藏成功！":
                            src = R.drawable.idiom_yishoucang;
                            idiomShouCang.setImageResource(src);
                            Toast.makeText(getBaseContext(),string,Toast.LENGTH_SHORT).show();
                            break;
                        case "成语取消收藏失败！":
                            Toast.makeText(getBaseContext(),string,Toast.LENGTH_SHORT).show();
                            break;
                        case "成语取消收藏成功！":
                            src = R.drawable.idiom_shoucang;
                            idiomShouCang.setImageResource(src);
                            Toast.makeText(getBaseContext(),string,Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
            }
        }
    };


    // ================== 百度语音合成部分,精简版初始化参数设置开始 ==========================
    /**
     * 发布时请替换成自己申请的appId appKey 和 secretKey。注意如果需要离线合成功能,请在您申请的应用中填写包名。
     * 本demo的包名是com.baidu.tts.sample，定义在build.gradle中。
     */
    protected String appId;

    protected String appKey;

    protected String secretKey;

    protected String sn; // 纯离线合成SDK授权码；离在线合成SDK没有此参数

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    private TtsMode ttsMode = DEFAULT_SDK_TTS_MODE;

    private boolean isOnlineSDK = TtsMode.ONLINE.equals(DEFAULT_SDK_TTS_MODE);
    // ================ 纯离线sdk或者选择TtsMode.ONLINE  以下参数无用;
    private static final String TEMP_DIR = "/sdcard/baiduTTS"; // 重要！请手动将assets目录下的3个dat 文件复制到该目录

    // 请确保该PATH下有这个文件
    private static final String TEXT_FILENAME = TEMP_DIR + "/" + TEXT_MODEL;

    // 请确保该PATH下有这个文件 ，m15是离线男声
    private static final String MODEL_FILENAME = TEMP_DIR + "/" + VOICE_MALE_MODEL;

    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================

    protected SpeechSynthesizer mSpeechSynthesizer;

    protected Handler mainHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 百度语音合成部分
        appId = Auth.getInstance(this).getAppId();
        appKey = Auth.getInstance(this).getAppKey();
        secretKey = Auth.getInstance(this).getSecretKey();
        sn = Auth.getInstance(this).getSn(); // 纯离线合成必须有此参数；离在线合成SDK没有此参数


        setContentView(R.layout.activity_idiom_info);
        ChangeStatusBarColor.initSystemBar(this);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        idiomName = intent.getStringExtra("name");

        if(!MyFragment.phoneNum.equals("") && !MyFragment.childName.equals("")){
            idiomIsSave(ConfigUtil.SERVICE_ADDRESS + "SendIdiomIsSaveServlet");
        }

        getIdiomInfoFromAPI(idiomName);

        tvIdiomName.setText(idiomName);

        myHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        String json = (String) msg.obj;
                        Log.e("lrf_json", json);
                        IdiomInfo idiomInfo = IdiomJsonUtil.convertToIdiomInfo(json);
                        Log.e("lrf_反序列化", idiomInfo.toString());
                        if(idiomInfo.getStatus() == 0){
                            idiomInfoResult = idiomInfo.getIdiomInfoResult();
                        }else{
                            String string = idiomInfo.getMsg();
                            Log.e("lrf_成语查询出错",string);
                            idiomInfoResult = new IdiomInfoResult(idiomName,"暂无该成语读音","null","null",null,null,"");
                        }
                        tvIdiomPronounce.setText(idiomInfoResult.getPronounce());

                        //为ViewPager设置Adapter
                        ViewPager viewPager = setViewPagerAdapter();
                        //将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
                        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
                        break;
                }
            }
        };

        // 百度语音合成部分
        initView();
        initPermission();
        initTTs();
    }

    // 点击返回
    @OnClick(R.id.linear_forward)
    public void clickToForward(){
        finish();
    }

    // 点击收藏
    @OnClick(R.id.idiom_shoucang)
    public void clickShouCang(){
        if(!MyFragment.phoneNum.equals("") && !MyFragment.childName.equals("")){
            if(src == R.drawable.idiom_shoucang){ //收藏
                saveIdiom(ConfigUtil.SERVICE_ADDRESS + "SaveIdiomServlet");
            }else{ //取消收藏
                cancelSaveIdiom(ConfigUtil.SERVICE_ADDRESS + "CancelSaveIdiomServlet");
            }
        }else {
            Toast.makeText(getBaseContext(),"请确保您已登录，且选择了孩子！",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 向服务端请求数据，查看当前成语是否已被收藏
     * @param url
     */
    private void idiomIsSave(String url){
        new Thread(){
            @Override
            public void run() {
                try {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    //设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    //获取网络输出流
                    OutputStream out = conn.getOutputStream();
                    String str = idiomName + "&" + phoneNum + "&" + childName;
                    out.write(str.getBytes());
                    //获取网络输入流
                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    //读取字符信息
                    String result = reader.readLine();
                    Log.e("lrf_该成语是否已经被收藏的返回信息",result);
                    //关闭流
                    reader.close();
                    in.close();
                    out.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = mHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 0;
                    msg.obj = result;
                    //发送Message对象
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 收藏成语
     * @param url
     */
    private void saveIdiom(String url) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    //设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    //获取网络输出流
                    OutputStream out = conn.getOutputStream();
                    String str = idiomName + "&" + phoneNum + "&" + childName;
                    out.write(str.getBytes());
                    //获取网络输入流
                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    //读取字符信息
                    String result = reader.readLine();
                    Log.e("lrf_该成语是否收藏成功的返回信息",result);
                    //关闭流
                    reader.close();
                    in.close();
                    out.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = mHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 0;
                    msg.obj = result;
                    //发送Message对象
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 取消收藏成语
     * @param url
     */
    private void cancelSaveIdiom(String url) {
        new Thread(){
            @Override
            public void run() {
                try {
                    //创建URL对象
                    URL urlPath = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    //设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    //获取网络输出流
                    OutputStream out = conn.getOutputStream();
                    String str = idiomName + "&" + phoneNum + "&" + childName;
                    out.write(str.getBytes());
                    //获取网络输入流
                    InputStream in = conn.getInputStream();
                    //使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
                    //读取字符信息
                    String result = reader.readLine();
                    Log.e("lrf_该成语取消收藏成功与否的返回信息",result);
                    //关闭流
                    reader.close();
                    in.close();
                    out.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = mHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 0;
                    msg.obj = result;
                    //发送Message对象
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 点击分享
    @OnClick(R.id.idiom_fenxiang)
    public void clickFenXiang(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(this,IdiomShareActivity.class);
        bundle.putString("name",idiomName);
        bundle.putString("pronounce",idiomInfoResult.getPronounce());
        bundle.putString("content", idiomInfoResult.getContent());
        bundle.putStringArrayList("antonym", (ArrayList<String>) idiomInfoResult.getAntonym());
        bundle.putStringArrayList("thesaurus", (ArrayList<String>) idiomInfoResult.getThesaurus());
        bundle.putString("comefrom", idiomInfoResult.getComefrom());
        bundle.putString("example", idiomInfoResult.getExample());
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }

    /**
     * 调用成语API接口，查询某成语的详细信息
     *
     * @param word
     * @return
     */
    private void getIdiomInfoFromAPI(String word) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // 创建URL对象
                    URL urlPath = new URL(url + "?appkey=" + APPKEY + "&chengyu=" + URLEncoder.encode(word, "utf-8"));
                    HttpURLConnection conn = (HttpURLConnection) urlPath.openConnection();
                    // 设置网络请求方式为POST
                    conn.setRequestMethod("POST");
                    // 获取网络输入流
                    InputStream in = conn.getInputStream();
                    // 使用字符流读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    // 读取字符信息
                    String json = reader.readLine();
                    Log.e("lrf", json);
                    // 关闭流
                    reader.close();
                    in.close();
                    //通过发送message对象将数据发布出去
                    //获取Message对象
                    Message msg = myHandler.obtainMessage();
                    //设置Message对象的属性(what、obj)
                    msg.what = 1;
                    msg.obj = json;
                    //发送Message对象
                    myHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 得到ViewPager引用，并设置Adapter
     *
     * @return ViewPager
     */
    private ViewPager setViewPagerAdapter() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),1) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                switch (position){
                    case 0:
                        IdiomMeanFragment idiomMeanFragment = new IdiomMeanFragment();
                        bundle.putString("content", idiomInfoResult.getContent());
                        idiomMeanFragment.setArguments(bundle);
                        return idiomMeanFragment;
                    case 1:
                        IdiomNearAntonymsFragment idiomNearAntonymsFragment = new IdiomNearAntonymsFragment();
                        bundle.putStringArrayList("antonym", (ArrayList<String>) idiomInfoResult.getAntonym());
                        bundle.putStringArrayList("thesaurus", (ArrayList<String>) idiomInfoResult.getThesaurus());
                        idiomNearAntonymsFragment.setArguments(bundle);
                        return idiomNearAntonymsFragment;
                    case 2:
                        IdiomAllusionFragment idiomAllusionFragment = new IdiomAllusionFragment();
                        bundle.putString("comefrom", idiomInfoResult.getComefrom());
                        idiomAllusionFragment.setArguments(bundle);
                        return idiomAllusionFragment;
                    case 3:
                        IdiomExampleSentenceFragment idiomExampleSentenceFragment = new IdiomExampleSentenceFragment();
                        bundle.putString("example", idiomInfoResult.getExample());
                        idiomExampleSentenceFragment.setArguments(bundle);
                        return idiomExampleSentenceFragment;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        return viewPager;
    }

    // 百度语音合成部分
    /**
     * 注意此处为了说明流程，故意在UI线程中调用。
     * 实际集成中，该方法一定在新线程中调用，并且该线程不能结束。具体可以参考NonBlockSyntherizer的写法
     */
    private void initTTs() {
        LoggerProxy.printable(true); // 日志打印在logcat中
        boolean isSuccess;
        if (!isOnlineSDK) {
            // 检查2个离线资源是否可读
            isSuccess = checkOfflineResources();
            if (!isSuccess) {
                return;
            } else {
                Log.e("lrf","离线资源存在并且可读, 目录：" + TEMP_DIR);
            }
        }
        // 日志更新在UI中，可以换成MessageListener，在logcat中查看日志
        SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);

        // 1. 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);

        // 2. 设置listener
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);

        // 3. 设置appId，appKey.secretKey
        int result = mSpeechSynthesizer.setAppId(appId);
        checkResult(result, "setAppId");
        result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
        checkResult(result, "setApiKey");

        // 4. 如果是纯离线SDK需要离线功能的话
        if (!isOnlineSDK) {
            // 文本模型文件路径 (离线引擎使用)， 注意TEXT_FILENAME必须存在并且可读
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME);
            // 声学模型文件路径 (离线引擎使用)， 注意TEXT_FILENAME必须存在并且可读
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME);

            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
            // 该参数设置为TtsMode.MIX生效。
            // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
            // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
            // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
            // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        }

        // 5. 以下setParam 参数选填。不填写则默认值生效
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置合成的音量，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5");
        // 设置合成的语调，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");

        // mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL); // 调整音频输出

        if (sn != null) {
            // 纯离线sdk这个参数必填；离在线sdk没有此参数
            mSpeechSynthesizer.setParam(PARAM_SN_NAME, sn);
        }

        // x. 额外 ： 自动so文件是否复制正确及上面设置的参数
        Map<String, String> params = new HashMap<>();
        // 复制下上面的 mSpeechSynthesizer.setParam参数
        // 上线时请删除AutoCheck的调用
        if (!isOnlineSDK) {
            params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME);
            params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME);
        }

        // 检测参数，通过一次后可以去除，出问题再打开debug
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);
        AutoCheck.getInstance(getApplicationContext()).check(initConfig, new Handler() {
            @Override
            /**
             * 开新线程检查，成功后回调
             */
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainDebugMessage();
                        Log.e("lrf",message);
                    }
                }
            }

        });

        // 6. 初始化
        result = mSpeechSynthesizer.initTts(ttsMode);
        checkResult(result, "initTts");

    }

    /**
     * 在线SDK不需要调用，纯离线SDK会检查资源文件
     *
     * 检查 TEXT_FILENAME, MODEL_FILENAME 这2个文件是否存在，不存在请自行从assets目录里手动复制
     *
     * @return 检测是否成功
     */
    private boolean checkOfflineResources() {
        String[] filenames = {TEXT_FILENAME, MODEL_FILENAME};
        for (String path : filenames) {
            File f = new File(path);
            if (!f.canRead()) {
                Log.e("lrf","[ERROR] 文件不存在或者不可读取，请从demo的assets目录复制同名文件到："
                        + f.getAbsolutePath());
                Log.e("lrf","[ERROR] 初始化失败！！！");
                return false;
            }
        }
        return true;
    }

    private void speak() {
        /* 以下参数每次合成时都可以修改
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
         *  设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5"); 设置合成的音量，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5"); 设置合成的语速，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5"); 设置合成的语调，0-9 ，默认 5
         */

        if (mSpeechSynthesizer == null) {
            Log.e("lrf","[ERROR], 初始化失败");
            return;
        }
        int result = mSpeechSynthesizer.speak(idiomName);
        Log.e("lrf","合成并播放 按钮已经点击");
        checkResult(result, "speak");
    }

    // 点击播放，则播放该成语读音
    private void initView() {
        readIdiom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
        mainHandler = new Handler() {
            /*
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj != null) {
                    Log.e("lrf",msg.obj.toString());
                }
            }

        };
    }

    @Override
    protected void onDestroy() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.stop();
            mSpeechSynthesizer.release();
            mSpeechSynthesizer = null;
            Log.e("lrf","释放资源成功");
        }
        super.onDestroy();
    }

    private void checkResult(int result, String method) {
        if (result != 0) {
            Log.e("lrf","error code :" + result + " method:" + method);
        }
    }

    //  下面是android 6.0以上的动态授权

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
                Log.e("lrf","没有权限");
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
}