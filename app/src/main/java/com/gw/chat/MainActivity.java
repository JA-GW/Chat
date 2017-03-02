package com.gw.chat;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private Button btnMsg;
    private Button btnContacts;
    private Button btnNews;
    private DrawerLayout drawerLayout;
    private List<String> contactList;


    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        btnMsg = (Button) findViewById(R.id.btn_message);
        btnContacts = (Button) findViewById(R.id.btn_contacts);
        btnNews = (Button) findViewById(R.id.btn_news);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        toolbar.setTitle("消息");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);



        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = drawerLayout.getChildAt(0);
                View menu = drawerView;

                float scale = 1 - slideOffset;
                float leftScale = 1 - 0.3f*scale;
                float rightScale = 0.8f + scale * 0.2f;

                //ViewHelper.setScaleX(menu,leftScale);//缩放
                //ViewHelper.setScaleY(menu,leftScale);
                ViewHelper.setAlpha(menu,0.6f + 0.4f*(1 - scale));

                ViewHelper.setTranslationX(mContent,menu.getMeasuredWidth()*(1-scale));
                //ViewHelper.setPivotX(mContent,0);//旋转缩放的支点设置
                //ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight()/2);
                mContent.invalidate();
                //ViewHelper.setScaleX(mContent, rightScale);
                //ViewHelper.setScaleY(mContent, rightScale);

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add:
                        Toast.makeText(MainActivity.this,"添加",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;


                }

                return true;
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        drawerLayout.setDrawerElevation((float) (0-(100.1)));


        contactList = new ArrayList<>();
        for(int i = 0;i < 50;i++){
            contactList.add("好友"+i);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        recyclerView.setAdapter(new MainPageAdapter(contactList));



        recyclerView.setOnTouchListener(new View.OnTouchListener() {

            double lastX=0,lastY=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                double nowX=0,nowY=0;

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getRawX();
                        lastY = event.getRawY();


                    case MotionEvent.ACTION_MOVE:
                        nowX = event.getRawX();
                        nowY = event.getRawY();

                        Log.i(TAG, "onTouch: Move"+nowX);
                        Log.i(TAG, "onTouch: x:"+(nowX- lastX)+"y:"+(nowY- lastY));
                        if((nowX - lastX) > abs(nowY - lastY) && (nowX - lastX) > 300){
                            Log.i(TAG, "onTouch: YES");
                            Log.i(TAG, "onTouch: x:"+(nowX- lastX));
                            drawerLayout.openDrawer(GravityCompat.START);
                        }


                    case MotionEvent.ACTION_UP:


                        break;

                    default:
                        break;
                }

                return false;
            }
        });




    }



    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main,menu);
        setIconEnable(menu,true);  //  就是这一句使图标能显示
        return super.onCreateOptionsMenu(menu);
    }





}
