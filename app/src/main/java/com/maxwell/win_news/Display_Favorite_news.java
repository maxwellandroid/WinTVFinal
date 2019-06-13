package com.maxwell.win_news;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.maxwell.win_news.Adapter_class.Employee;

import java.util.ArrayList;
import java.util.List;

import static com.maxwell.win_news.Full_news_view_page.DATABASE_NAME;


public class Display_Favorite_news extends AppCompatActivity {

    ListView listViewEmployees;
    SQLiteDatabase mDatabase;
    ProductAdapter adapter;
    LinearLayout no_data;
    List<Employee> employeeList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__favorite_news);

        create_table_and_database();

        listViewEmployees = (ListView) findViewById(R.id.fav_news_list_items);
        listViewEmployees.setDivider(null);


        no_data=(LinearLayout)findViewById(R.id.no_data);

        employeeList = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        showNewsDetailsFromDatabase();
    }

    private void showNewsDetailsFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM FavoriteNews", null);

        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {

                //pushing each record in the employee list
                employeeList.add(new Employee(
                        cursorEmployees.getString(0),
                        cursorEmployees.getString(1),
                        cursorEmployees.getString(2),
                        cursorEmployees.getString(3),
                        cursorEmployees.getString(4),
                        cursorEmployees.getString(5)
                ));
            } while (cursorEmployees.moveToNext());
        }
        else{
            listViewEmployees.setVisibility(View.GONE);
            no_data.setVisibility(View.VISIBLE);

        }
        //closing the cursor
        cursorEmployees.close();


        //creating the adapter object
        adapter = new ProductAdapter(this, R.layout.news_list_items, employeeList, mDatabase);


        //adding the adapter to listview
        listViewEmployees.setAdapter(adapter);
    }

    public class ProductAdapter extends ArrayAdapter<Employee> {

        Context mCtx;
        TextView updatetext;
        int listLayoutRes,k;
        String grandtotal;
        List<Employee> employeeList;
        SQLiteDatabase mDatabase;

        public ProductAdapter(Context mCtx, int listLayoutRes, List<Employee> employeeList, SQLiteDatabase mDatabase) {
            super(mCtx, listLayoutRes, employeeList);

            this.mCtx = mCtx;
            this.listLayoutRes = listLayoutRes;
            this.employeeList = employeeList;
            this.mDatabase = mDatabase;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(listLayoutRes, null);

            final Employee favorite_news = employeeList.get(position);


            final TextView news_title,news_time,news_section,news_full,news_id;
            ImageView news_image;
            CardView news_card;



            //    TouchImageView imageView = new TouchImageView(context);
            news_card = (CardView) view.findViewById(R.id.news_card_view);

            news_id = (TextView) view.findViewById(R.id.news_id);
            news_title = (TextView) view.findViewById(R.id.news_title);
            news_time = (TextView) view.findViewById(R.id.news_time);
            news_full = (TextView) view.findViewById(R.id.news_full);
            news_section = (TextView) view.findViewById(R.id.news_section);
            news_image= (ImageView) view.findViewById(R.id.news_image);


            news_section.setText(favorite_news.getNews_section());
            news_id.setText(favorite_news.getNews_id());
            news_full.setText(Html.fromHtml(favorite_news.getNews_full_content()));
            news_time.setText(favorite_news.getNews_time());
            news_title.setText(Html.fromHtml(favorite_news.getNews_title()));
            Glide.with(getContext()).load(favorite_news.getNews_image())
                    .crossFade()
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(news_image);



            news_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Display_Favorite_news.this,Full_news_view_page.class);
                    finish();
                    i.putExtra("news_id",favorite_news.getNews_id());
                    i.putExtra("news_image",favorite_news.getNews_image());
                    i.putExtra("news_title",favorite_news.getNews_title());
                    i.putExtra("news_full_content",favorite_news.getNews_full_content());
                    i.putExtra("news_time",favorite_news.getNews_time());
                    i.putExtra("news_section",favorite_news.getNews_section());
                    startActivity(i);
                }
            });



            return view;
        }



    }

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

}
