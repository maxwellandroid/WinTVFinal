package com.maxwell.win_news;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codesgood.views.JustifiedTextView;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Full_news_view_page extends AppCompatActivity {

    ImageView image,back;
    TextView section,time;
    JustifiedTextView content,title;
    ImageView favorite;
    private BoomMenuButton bmb2;
    SQLiteDatabase mDatabase;
    String news_in_databse;
    String news_image,news_title,news_full_content,news_time,news_section,news_id;
    public static final String DATABASE_NAME = "Favorite_News";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_news_view_page);
        set_current_date_time();

        create_table_and_database();



        //getting the news details from previous activity
         Intent intent = getIntent();
         news_id = intent.getStringExtra("news_id");
         news_image = intent.getStringExtra("news_image");
         news_title = intent.getStringExtra("news_title");
         news_full_content = intent.getStringExtra("news_full_content");
         news_time = intent.getStringExtra("news_time");
         news_section = intent.getStringExtra("news_section");


        image=(ImageView)findViewById(R.id.news_image);
        title=(JustifiedTextView) findViewById(R.id.title);
        content=(JustifiedTextView)findViewById(R.id.full_news);
        section=(TextView) findViewById(R.id.section);
        time=(TextView) findViewById(R.id.news_time);


        back=(ImageView)findViewById(R.id.rate);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText(news_title);
        time.setText(news_time);
        section.setText(news_section);
        content.setText(Html.fromHtml(news_full_content));
        Glide.with(this).load(news_image)
                .crossFade()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);

        initBmbWithWhitePieceColor(bmb2 = (BoomMenuButton)findViewById(R.id.bmb2));
        bmb2.setShareLine1Color(Color.rgb(241,90,40));
        bmb2.setShareLine2Color(Color.rgb(241,90,40));
        bmb2.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                Toast.makeText(Full_news_view_page.this, index, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });


        favorite=(ImageView)findViewById(R.id.fav);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              if(news_in_databse.equals("yes"))
              {
               ///remove the news from favorite
                  remove_news_from_database();
                  favorite.setImageResource(R.drawable.unfav);
                  news_in_databse="no";
              }
              else
              {
                  //add news to favorite
                  add_news_database();
                  favorite.setImageResource(R.drawable.fav);
                  news_in_databse="yes";
              }


            }
        });

        //to update the favorite icon
        check_whether_the_news_already_added_to_favorite();


    }


    private BoomMenuButton initBmbWithWhitePieceColor(BoomMenuButton bmb) {
        assert bmb != null;
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++)
            bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilderWithDifferentPieceColor());
        return bmb;
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


    //Creating two tables named Cart and product_data
    public void create_table_and_database(){

        try
        {
            mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            mDatabase.execSQL("CREATE TABLE IF NOT EXISTS FavoriteNews(news_id  VARCHAR ,news_title VARCHAR,news_image VARCHAR,news_full_content VARCHAR,news_time VARCHAR,news_section VARCHAR);");

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please try again Later", Toast.LENGTH_SHORT).show();
        }

    }


    //Adding the news to database
    public void add_news_database() {
        String insertSQL = "INSERT INTO FavoriteNews \n" +
                "(news_id, news_title, news_image, news_full_content, news_time, news_section)\n" +
                "VALUES \n" +
                "(?, ?, ?, ?, ?, ?);";
        try {


            mDatabase.execSQL(insertSQL, new String[]{news_id, news_title, news_image, news_full_content, news_time, news_section});

            Toast.makeText(this, "News Added to your Favorite list", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void show_data_from_table() {
        final String view_news_id="\'"+news_id+"\'";
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM FavoriteNews Where news_id="+view_news_id+"", null);
        if (cursorEmployees.moveToFirst())
        {
            String i= cursorEmployees.getString(1);
            Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
        }
        cursorEmployees.close();

    }


    public void check_whether_the_news_already_added_to_favorite() {
        final String view_news_id="\'"+news_id+"\'";
        Cursor news_cursor = mDatabase.rawQuery("SELECT * FROM FavoriteNews Where news_id="+view_news_id+"", null);
        if (news_cursor.moveToFirst())
        {
            favorite.setImageResource(R.drawable.fav);
            news_in_databse="yes";
        }
        else
            {
                favorite.setImageResource(R.drawable.unfav);
                news_in_databse="no";
            }
        news_cursor.close();
    }


    //Deleting news from Database
    public void remove_news_from_database () {

        String sql = "DELETE FROM FavoriteNews WHERE news_id = ?";
        mDatabase.execSQL(sql, new String[]{news_id});
        Toast.makeText(this, "Deleetd fron Favorite list", Toast.LENGTH_SHORT).show();

    }



}
