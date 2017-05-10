package test.bwie.com.lixiaoqian20170509;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
/**
 * @类的用途：主页面
 * @author: 李晓倩
 * @date: 2017/5/9
 */
public class Main2Activity extends AppCompatActivity {

    private ProgressDialog progressDialog = null;
    private TextView textView2;
    private GridView lv;
    private String mString = "{\n" +
            "  \"Students\": {\n" +
            "    \"Student\": [\n" +
            "      {\n" +
            "        \"name\": \"张三\",\n" +
            "        \"content\": \"学生简介学生简介学生简介学生简介学生简介\",\n" +
            "        \"img\": \"http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"李四\",\n" +
            "        \"content\": \"这是一个好学生，好好学习，特别努力\",\n" +
            "        \"img\": \"http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"王五\",\n" +
            "        \"content\": \"喜欢读书，喜欢英语，认真学习的时候特别多\",\n" +
            "        \"img\": \"http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"赵老大\",\n" +
            "        \"content\": \"长的好看，个头大\",\n" +
            "        \"img\": \"http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"秦老二\",\n" +
            "        \"content\": \"努力学习，认真工作\",\n" +
            "        \"img\": \"http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"name\": \"齐老三\",\n" +
            "        \"content\": \"齐老三学习好，是一个三好学生\",\n" +
            "        \"img\": \"http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        progressDialog = ProgressDialog.show(Main2Activity.this, "请稍等...", "获取数据中...", true);
        getJson();
    }

    private void getJson() {
        Students students = GsonUtil.GsonToBean(mString, Students.class);
        Students.StudentsBean studentsBean = students.getStudents();
        final List<Students.StudentsBean.StudentBean> student = studentsBean.getStudent();
        MyAdapter adapter=new MyAdapter(student);
        lv.setAdapter(adapter);
        progressDialog.dismiss();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("name",student.get(position));
                startActivity(intent);
            }
        });
      /*  OkHttpUtils.get()
                .url("http://ycf-1253707494.costj.myqcloud.com/ycf.json")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, String s) {
//                        Students students = GsonUtil.GsonToBean(s, Students.class);
//                        Students.StudentsBean studentsBean = students.getStudents();
//                        final List<Students.StudentsBean.StudentBean> student = studentsBean.getStudent();
//                        MyAdapter adapter=new MyAdapter(student);
//                        lv.setAdapter(adapter);
//                        progressDialog.dismiss();
//                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
//                                intent.putExtra("name",student.get(position));
//                                startActivity(intent);
//                            }
//                        });
                    }
                });*/
    }

    class MyAdapter extends BaseAdapter{
        private List<Students.StudentsBean.StudentBean> list;

        public MyAdapter(List<Students.StudentsBean.StudentBean> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(Main2Activity.this,R.layout.lv_item,null);
                holder=new ViewHolder();
                holder.img= (ImageView) convertView.findViewById(R.id.imageView);
                holder.tv= (TextView) convertView.findViewById(R.id.textView3);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            Glide.with(Main2Activity.this)
                    .load(list.get(position).getImg())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.img);
            holder.tv.setText(list.get(position).getName());
            return convertView;
        }


    }

    class ViewHolder{
        ImageView img;
        TextView tv;
    }

    private void initView() {
        textView2 = (TextView) findViewById(R.id.textView2);
        lv = (GridView) findViewById(R.id.lv);
    }
}
