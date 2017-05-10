package test.bwie.com.lixiaoqian20170509;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @类的用途：点击条目详情页
 * @author: 李晓倩
 * @date: 2017/5/9
 */

public class Main3Activity extends AppCompatActivity {

    private TextView textView4;
    private ImageView imageView2;
    private TextView textView5;
    private TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        Intent intent = getIntent();
        Students.StudentsBean.StudentBean stu= (Students.StudentsBean.StudentBean) intent.getSerializableExtra("name");
        textView4.setText(stu.getName()+"的信息");
        textView5.setText(stu.getName());
        textView6.setText(stu.getContent());
        Glide.with(this)
                .load(stu.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView2);
    }

    private void initView() {
        textView4 = (TextView) findViewById(R.id.textView4);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
    }
}
