package diss.android.com.diss;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText index_name,index_password;//用户名密码
    private TextView index_login,index_forget,index_serviceContent;//注册，忘记密码，服务条款
    private Button index_submit;//登录按钮
    private String number,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //自带标题栏隐藏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //初始化控件
        initContents();

        //从sharedPreference中读取数据
        readData();


        //开启他们的监听事件
        index_serviceContentListener();
        index_loginListener();
        index_forgetListener();
        index_submitListener();

        //当用户输入的用户名和SharedPreferen里的用户名一样的时候，密码显示在密码框
        index_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(number)&&number.equals(s)){
                    index_password.setText(password);
                    index_password.setSelection(0,password.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void initContents(){
        //默认会自动保存他们的用户名和密码！
        index_name = findViewById(R.id.index_name);//用户名
        index_password = findViewById(R.id.index_password);//密码
        index_login = findViewById(R.id.index_login);//注册
        index_forget = findViewById(R.id.index_forget);//忘记密码
        index_serviceContent = findViewById(R.id.index_serviceContent);//服务条款
        index_submit = findViewById(R.id.index_submit);//登录
    }
    //服务条款的函数
    private void index_serviceContentListener(){
        index_serviceContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, index_service.class);
                startActivity(intent);
            }
        });
    }

    //读取SHaredPreference数据
    private void readData(){
        SharedPreferences sp = getSharedPreferences("login_data",MODE_PRIVATE);
        if(sp!=null){
            number = sp.getString("number","");//学号登录
            password = sp.getString("password","");
        }
        index_name.setText(number);
        index_password.setText(password);
    }
    //注册的函数
    private void index_loginListener(){
        index_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);//跳转到注册界面
            }
        });


    }
    //忘记密码的函数
    private void index_forgetListener(){
        index_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, forget.class);
                startActivity(intent);//跳转到忘记密码的界面
            }
        });

}
    //登陆的函数
    private void index_submitListener(){
        index_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 此时对应的逻辑是向服务器发送用户名，密码，检验是否存在此用户名密码，若存在就将此内容存到
                 * SharedPreference中，若不存在就将SharedPreference中的密码置为空，用户名不变
                 * */
                String currentNumber = index_name.getText().toString();//获取当前的用户名
                String currentPassword = index_password.getText().toString();//获取当前的密码
                if(TextUtils.isEmpty(currentNumber)&&TextUtils.isEmpty(currentPassword)){//当前的用户名和密码都不为空
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("咳咳")
                            .setMessage("你的用户名密码不见了咦")
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //确定后执行的代码
                                }
                            });
                    dialog.show();//显示对话框
                }
                else if(currentNumber.equals("admin")&&currentPassword.equals("admin")){//此时应该验证逻辑，此步先省略
                   // Toast.makeText()
                    //执行成功后保存数据
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("login_data",MODE_PRIVATE).edit();
                    editor.putString("password",currentPassword);//保存密码
                    editor.putString("number",currentNumber);//保存的是学号
                    editor.commit();
                }
                else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("错误")
                            .setMessage("你的用户名密码不正确")
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //确定后执行的代码

                                }
                            });
                    dialog.show();//显示对话框
                    index_password.getText().clear();//密码框内容清空
                }
            }
        });

    }
}
