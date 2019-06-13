package com.maxwell.win_news;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.maxwell.win_news.News_Tabs.Fragment_India_Tab;
import com.maxwell.win_news.News_Tabs.Fragment_Sports_Tab;
import com.maxwell.win_news.News_Tabs.Fragment_Technology_Tab;
import com.maxwell.win_news.News_Tabs.Fragment_World_Tab;
import com.maxwell.win_news.News_Tabs.LiveTv_Tab;
import com.pushbots.push.Pushbots;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity  {


    private MainActivity mContext;
    LinearLayout normal_view;
    private Toolbar toolbar;
    TabLayout tabLayout ;
    ImageView rate;
    ViewPager viewPager ;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    FrameLayout top_view;
    FragmentAdapterClass fragmentAdapter ;



    String[] web = {
            "India",
            "World",
            "Sports",
            "Technology",
            "Social Media",
            "Upload News",

    } ;
    Integer[] imageId = {
            R.drawable.sports_icon,
            R.drawable.india_icon,
            R.drawable.globe_icon,
            R.drawable.tech_icon,
            R.drawable.share_icon,
            R.drawable.upload_icon,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialise the push bots for notifications
        Pushbots.sharedInstance().init(this);
        Pushbots.sharedInstance().registerForRemoteNotifications();

        set_current_date_time();
        normal_view=(LinearLayout)findViewById(R.id.normal_view);
        top_view=(FrameLayout)findViewById(R.id.below);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        // ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.text_address, mDrawerList, false);
        //initialising navigation drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        CustomList adapter = new CustomList(MainActivity.this, web, imageId);
        ListView list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.text_address, null, false);
        list.addFooterView(footerView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });


        tabLayout = (TabLayout)findViewById(R.id.tab_layout1);
        viewPager = (ViewPager) findViewById(R.id.pager1);



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("நேரலை"));
        tabLayout.addTab(tabLayout.newTab().setText("இந்தியா"));
        tabLayout.addTab(tabLayout.newTab().setText("உலகம்"));
        tabLayout.addTab(tabLayout.newTab().setText("விளையாட்டு"));
        tabLayout.addTab(tabLayout.newTab().setText("தொழில்நுட்பம்"));



        rate=(ImageView)findViewById(R.id.summa);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Display_Favorite_news.class);
                startActivity(i);
            }
        });

        try {


        fragmentAdapter = new FragmentAdapterClass(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);


      //  viewPager.setPageTransformer(true, new CubeOutTransformer());
        } catch (Exception e){Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();}
        //off screen page limit is used inorder to retain the data loaded in the viewpager and donot destroye the data
        //when we move to other fragments
        int limit = (fragmentAdapter.getCount() > 1 ? fragmentAdapter.getCount() - 1 : 1);
        viewPager.setOffscreenPageLimit(limit);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                viewPager.setCurrentItem(LayoutTab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {



            }
        });



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // adds item to action bar
        getMenuInflater().inflate(R.menu.material_search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        return true;
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {



            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            else{
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure, You wanted to Close");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                android.os.Process.killProcess(android.os.Process.myPid());
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }}}


    public class FragmentAdapterClass extends FragmentStatePagerAdapter {

        int TabCount;

        public FragmentAdapterClass(FragmentManager fragmentManager, int CountTabs) {

            super(fragmentManager);

            this.TabCount = CountTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    LiveTv_Tab tab1 = new LiveTv_Tab();
                    return tab1;

                case 1:
                    Fragment_India_Tab tab2 = new Fragment_India_Tab();
                    return tab2;

                case 2:
                    Fragment_World_Tab tab3 = new Fragment_World_Tab();
                    return tab3;

                case 3:
                    Fragment_Sports_Tab tab4 = new Fragment_Sports_Tab();
                    return tab4;

                case 4:
                    Fragment_Technology_Tab tab5 = new Fragment_Technology_Tab();
                    return tab5;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TabCount;
        }
    }


    public void set_current_date_time() {
        TextView datetext=(TextView)findViewById(R.id.date);
        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        datetext.setText(format.format(todaysdate));

        TextView time=(TextView)findViewById(R.id.time);
        String Time = java.text.DateFormat.getTimeInstance().format(new Date());
        time.setText(Time);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Hide this view so that when we play live tv in Landscape view the Tabbar will not be displayed
            top_view.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            //stroring the tablayout when the orientation is potrait
            top_view.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
        }
    }}




