package diss.android.com.diss;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText login_number,login_name,login_realname,login_password,login_repassword,login_province;
    private Button login_login;
    private TextView login_serviceContent;
    RadioGroup group;//以上为对应的页面的id

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //自带标题栏隐藏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }


        initServinceContent();//初始化控件

        login_serviceContentListener();//阅读服务条款

        login_loginListener();//注册监听函数
    }
    private void initServinceContent(){
        //初始化这些控件
        login_login = findViewById(R.id.login_login);
        login_serviceContent = findViewById(R.id.login_serviceContent);
        login_number = findViewById(R.id.login_number);
        login_realname = findViewById(R.id.login_realname);
        login_name = findViewById(R.id.login_name);
        login_password = findViewById(R.id.login_password);
        login_repassword = findViewById(R.id.login_repassword);
        login_province = findViewById(R.id.login_province);
        group = findViewById(R.id.login_sex);

    }
    private void login_loginListener(){
        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = login_number.getText().toString().trim();//获取学号
                String nickname = login_name.getText().toString();//获取用户名（昵称）
                String name = login_realname.getText().toString();//获取真实的用户名
                int checked = group.getCheckedRadioButtonId();//获取性别
                String sex = "未知";//性别
                if(checked == R.id.login_man){
                    sex = "男";
                }else{
                sex = "女";}
                String password = login_password.getText().toString();//密码
                String repassword = login_repassword.getText().toString();//第二遍密码
                String province = login_province.getText().toString();//省份
                /**
                 * 这里面缺了很多功能，比如默认清空内容时右边的小图标，输入字符的正确长度，清空其中的空格
                 * */
                if(TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)||TextUtils.isEmpty(name)||TextUtils.isEmpty(nickname)
                        ||TextUtils.isEmpty(province)||TextUtils.isEmpty(number)){//若其中有一项为空的话，提示出错
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Login.this);
                    dialog.setTitle("错误！").setMessage("任何一项均不能为空哦").setCancelable(false)//设置图标身边么的都有对应的函数
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//选择确定时出发的函数
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    dialog.show();
                } else if(!password.equals(repassword)){//当两次密码不一致时触发此函数
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Login.this);//在此界面生成对话框
                    dialog.setTitle("密码错误！").setMessage("两次密码不一致").setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//点击确定后触发的事件
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    dialog.show();

                } else{//可以像服务器发送数据了
                    Toast.makeText(Login.this,name+" "+password+" "+nickname,Toast.LENGTH_LONG).show();//这只是实验的代码
                }
            }
        });
    }
    private void login_serviceContentListener(){
        login_serviceContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,index_service.class);
                startActivity(intent);
            }
        });
    }
}
