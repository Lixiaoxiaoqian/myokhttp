package test.bwie.com.myokhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();
    private TextView tv;
    private String mResult;
    private String url="http://admin.wap.china.com/user/NavigateTypeAction.do?processID=getNavigateNews";
    private String url2="http://admin.wap.china.com/user/NavigateTypeAction.do";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new Thread(networkTask).start();

    }

    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            HashMap<String, String> map = new HashMap<>();
            map.put("processID","getNavigateNews");
            map.put("page","1");
            map.put("code","news");
            map.put("pageSize","20");
            map.put("parentid","0");
            map.put("type","1");
            RequestManager.getInstance(MainActivity.this,tv).requestSyn(url2,RequestManager.TYPE_POST_FORM,map);
           /* RequestManager.getInstance(MainActivity.this,tv).requestAsyn(url2,RequestManager.TYPE_POST_JSON,map,new ReqCallBack<String>() {
                @Override
                public void onReqSuccess(String result) {
                    tv.setText(result);
                }

                @Override
                public void onReqFailed(String errorMsg) {

                }
            });*/
        }
    };

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }
    
}
