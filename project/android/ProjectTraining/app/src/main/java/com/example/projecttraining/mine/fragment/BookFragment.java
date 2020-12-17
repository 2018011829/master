package com.example.projecttraining.mine.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projecttraining.R;
import com.example.projecttraining.books.entitys.Book;
import com.example.projecttraining.home.fragments.MyFragment;
import com.example.projecttraining.mine.adapter.BookCollectionAdapter;
import com.example.projecttraining.util.ConfigUtil;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookFragment extends Fragment {
    private View view;
    private ListView listView;
    private ImageView iv_collectionbook_null;
    private BookCollectionAdapter bookCollectionAdapter;
    private List<Book> books = new ArrayList<>();
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    try {
                        JSONObject jBooks = new JSONObject((String) msg.obj);
                        JSONArray jArray = jBooks.getJSONArray("books");
                        for(int i= 0;i<jArray.length();i++){
                            String json = jArray.getJSONObject(i).toString();
                            Log.i("json", json);
                            Book book = new Gson().fromJson(json,Book.class);
                            books.add(book);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(books.isEmpty()){
                        iv_collectionbook_null.setImageDrawable(BookFragment.this.getContext().getDrawable(R.drawable.mine_collection_null));
                    }else {
                        initListView();
                    }
                    break;
            }
        }
    };




    @Override
    public void onResume() {
        super.onResume();
        bookCollectionAdapter.removeData();
        queryCollectionBook();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine_collectionbook,container,false);
        iv_collectionbook_null = view.findViewById(R.id.iv_collectionbook_null);
        bookCollectionAdapter =new BookCollectionAdapter(this.getActivity(),books);
        return view;
    }

    private void queryCollectionBook() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", EMClient.getInstance().getCurrentUser());
        builder.add("cname", MyFragment.childName);
        builder.add("type", "book");
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "SearchBookFromCollection")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询收藏图书信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.what=1;
                message.obj = result;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    private void initListView() {
        listView = view.findViewById(R.id.lv_mine_collectionBooks);
//        bookCollectionAdapter = new BookCollectionAdapter(this.getActivity(),books);
        listView.setAdapter(bookCollectionAdapter);
    }
}
