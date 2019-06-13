package com.maxwell.win_news.News_Tabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;
import com.maxwell.win_news.Full_news_view_page;
import com.maxwell.win_news.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class LiveTv_Tab extends Fragment  {

    HttpURLConnection connection;
    BufferedReader reader;
    InputStream stream;
    LinearLayout make_full_screen,hide_layout;
    VideoView videoView;
    ImageView zoom,play;
    TextView load_text;
    LinearLayout normal_view;
    LinearLayout splash,home_layout;
    ProgressDialog d;
    TextView marqueeText;
    static  int i=0;
    TextView card_title1, card_title2, card_title3,card_title4,card_title5,card_title6,card_title7,card_title8,card_title9,card_time1,card_time2,card_time3,card_time4,card_time5,card_time6,card_time7,card_time8,card_time9,card1_full_news,card2_full_news,card3_full_news,card4_full_news,card5_full_news,card6_full_news,card7_full_news,card8_full_news,card9_full_news;
    ImageView card_image1,card_image2,card_image3,card_image4,card_image5,card_image6,card_image7,card_image8,card_image9;
    TextView hiden_image1,hiden_image2,hiden_image3,hiden_image4,hiden_image5,hiden_image6,hiden_image7,hiden_image8,hiden_image9;
    LinearLayout card1,card2,card3,card5,card4,card6,card7,card8,card9;

    public LiveTv_Tab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.live_tv_fragment, container, false);


        load_text=(TextView)view.findViewById(R.id.load_text);
        make_full_screen=(LinearLayout)view.findViewById(R.id.make_full_screen);
        make_full_screen.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;
        make_full_screen.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;

        hide_layout=(LinearLayout)view.findViewById(R.id.hide_layout);
        videoView = (VideoView)view.findViewById(R.id.video_view);

        videoView.setVideoURI(Uri.parse("http://45.79.203.234:1935/win/myStream/playlist.m3u8"));
        videoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {

                load_text.setVisibility(View.GONE);
                videoView.start();
                make_full_screen.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;
                make_full_screen.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;


            }
        });
         zoom=(ImageView)view.findViewById(R.id.zoom);
        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                else{getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);}
            }
        });


        play=(ImageView)view.findViewById(R.id.play);
        play.setImageResource(R.drawable.new_pause);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(videoView.isPlaying())
              {
                  videoView.pause();
                  play.setImageResource(R.drawable.new_play);
              }
              else{
                  videoView.setVideoURI(Uri.parse("http://45.79.203.234:1935/win/myStream/playlist.m3u8"));
                  videoView.start();
                  play.setImageResource(R.drawable.new_pause);
              }

            }
        });

        card1=(LinearLayout)view. findViewById(R.id.card1);
        card_time1=(TextView)view.findViewById(R.id.card1_time);
        hiden_image1=(TextView)view.findViewById(R.id.hiden_image1);
        card_image1=(ImageView)view.findViewById(R.id.card1_image);
        card1_full_news=(TextView)view.findViewById(R.id.card1_full_news);
        card_title1 =(TextView)view.findViewById(R.id.card1_text);



        card3=(LinearLayout) view.findViewById(R.id.card3);
        hiden_image3=(TextView)view.findViewById(R.id.hiden_image3);
        card_time2=(TextView)view.findViewById(R.id.card2_time);
        card_image3=(ImageView)view.findViewById(R.id.card3_image);
        card3_full_news=(TextView)view.findViewById(R.id.card3_full_news);
        card_title3 =(TextView)view.findViewById(R.id.card3_text);


        card2=(LinearLayout) view.findViewById(R.id.card2);
        card_time3=(TextView)view.findViewById(R.id.card3_time);
        hiden_image2=(TextView)view.findViewById(R.id.hiden_image2);
        card_image2=(ImageView)view.findViewById(R.id.card2_image);
        card2_full_news=(TextView)view.findViewById(R.id.card2_full_news);
        card_title2 =(TextView)view.findViewById(R.id.card2_text);


        card4=(LinearLayout)view. findViewById(R.id.card4);
        card_time4=(TextView)view.findViewById(R.id.card4_time);
        hiden_image4=(TextView)view.findViewById(R.id.hiden_image4);
        card_image4=(ImageView)view.findViewById(R.id.card4_image);
        card4_full_news=(TextView)view.findViewById(R.id.card4_full_news);
        card_title4 =(TextView)view.findViewById(R.id.card4_text);


        card5=(LinearLayout)view. findViewById(R.id.card5);
        card_time5=(TextView)view.findViewById(R.id.card5_time);
        hiden_image5=(TextView)view.findViewById(R.id.hiden_image5);
        card_image5=(ImageView)view.findViewById(R.id.card5_image);
        card5_full_news=(TextView)view.findViewById(R.id.card5_full_news);
        card_title5 =(TextView)view.findViewById(R.id.card5_text);


        card6=(LinearLayout)view. findViewById(R.id.card6);
        card_time6=(TextView)view.findViewById(R.id.card6_time);
        hiden_image6=(TextView)view.findViewById(R.id.hiden_image6);
        card_image6=(ImageView)view.findViewById(R.id.card6_image);
        card6_full_news=(TextView)view.findViewById(R.id.card6_full_news);
        card_title6 =(TextView)view.findViewById(R.id.card6_text);


        card7=(LinearLayout)view. findViewById(R.id.card7);
        card_time7=(TextView)view.findViewById(R.id.card7_time);
        hiden_image7=(TextView)view.findViewById(R.id.hiden_image7);
        card_image7=(ImageView)view.findViewById(R.id.card7_image);
        card7_full_news=(TextView)view.findViewById(R.id.card7_full_news);
        card_title7 =(TextView)view.findViewById(R.id.card7_text);


        card8=(LinearLayout)view. findViewById(R.id.card8);
        card_time8=(TextView)view.findViewById(R.id.card8_time);
        hiden_image8=(TextView)view.findViewById(R.id.hiden_image8);
        card_image8=(ImageView)view.findViewById(R.id.card8_image);
        card8_full_news=(TextView)view.findViewById(R.id.card8_full_news);
        card_title8 =(TextView)view.findViewById(R.id.card8_text);


        card9=(LinearLayout)view. findViewById(R.id.card9);
        card_time9=(TextView)view.findViewById(R.id.card9_time);
        hiden_image9=(TextView)view.findViewById(R.id.hiden_image9);
        card_image9=(ImageView)view.findViewById(R.id.card9_image);
        card9_full_news=(TextView)view.findViewById(R.id.card9_full_news);
        card_title9 =(TextView)view.findViewById(R.id.card9_text);

        marqueeText=(TextView)view.findViewById(R.id.MarqueeText);

        card1=(LinearLayout)view.findViewById(R.id.card1);
        card2=(LinearLayout)view.findViewById(R.id.card2);
        card3=(LinearLayout)view.findViewById(R.id.card3);

        new JsonTask_three_card().execute("http://www.wintvindia.com/api/allcategorynews.php");
        new JsonTask_flash_news().execute("http://www.wintvindia.com/api/flashnews.php");

        return view;
    }


    public void three_card_full_news(String cad_no) {
        if(cad_no.equals("card_one"))
        {
            Toast.makeText(getActivity(), card1_full_news.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getActivity(),Full_news_view_page.class);
            i.putExtra("news_image",hiden_image1.getText().toString());
            i.putExtra("news_title",card_title1.getText().toString());
            i.putExtra("news_full_content",card1_full_news.getText().toString());
            i.putExtra("news_time",card_time1.getText().toString());
            i.putExtra("news_section","தமிழகம்");
            startActivity(i);
        }
        if(cad_no.equals("card_two")){

            Intent i=new Intent(getActivity(),Full_news_view_page.class);
            i.putExtra("news_image",hiden_image2.getText().toString());
            i.putExtra("news_title",card_title2.getText().toString());
            i.putExtra("news_full_content",card2_full_news.getText().toString());
            i.putExtra("news_time",card_time2.getText().toString());
            i.putExtra("news_section","இந்தியா");
            startActivity(i);
        }
        if(cad_no.equals("card_three")){

            Intent i=new Intent(getActivity(),Full_news_view_page.class);
            i.putExtra("news_image",hiden_image3.getText().toString());
            i.putExtra("news_title",card_title3.getText().toString());
            i.putExtra("news_full_content",card3_full_news.getText().toString());
            i.putExtra("news_time",card_time3.getText().toString());
            i.putExtra("news_section","உலகம்");
            startActivity(i);
        }

    }


    public class JsonTask_three_card extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                String finalJson = buffer.toString();
                Log.d("final Json", buffer.toString());
                return finalJson;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonarray = jsonObject.getJSONArray("response");

                JSONObject finaljsonobject1 = jsonarray.getJSONObject(0);
                card_title1.setText(finaljsonobject1.getString("title"));
             //   card_time1.setText(finaljsonobject1.getString("time"));
                hiden_image1.setText(finaljsonobject1.getString("image"));
             //   card1_full_news.setText(Html.fromHtml(finaljsonobject1.getString("long")));
                Glide.with(getContext()).load(finaljsonobject1.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image1);

                JSONObject finaljsonobject2 = jsonarray.getJSONObject(1);
                card_title2.setText(finaljsonobject2.getString("title"));
             //   card_time2.setText(finaljsonobject2.getString("time"));
                hiden_image2.setText(finaljsonobject2.getString("image"));
             //   card2_full_news.setText(Html.fromHtml(finaljsonobject2.getString("long")));
                Glide.with(getContext()).load(finaljsonobject2.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image2);


                JSONObject finaljsonobject3 = jsonarray.getJSONObject(2);
                card_title3.setText(finaljsonobject3.getString("title"));
              //  card_time3.setText(finaljsonobject3.getString("time"));
                hiden_image3.setText(finaljsonobject3.getString("image"));
              //  card3_full_news.setText(Html.fromHtml(finaljsonobject3.getString("long")));
                Glide.with(getContext()).load(finaljsonobject3.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image3);


                JSONObject finaljsonobject4 = jsonarray.getJSONObject(3);
                card_title4.setText(finaljsonobject4.getString("title"));
               // card_time4.setText(finaljsonobject4.getString("time"));
                hiden_image4.setText(finaljsonobject4.getString("image"));
              //  card4_full_news.setText(Html.fromHtml(finaljsonobject4.getString("long")));
                Glide.with(getContext()).load(finaljsonobject4.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image4);

                JSONObject finaljsonobject5 = jsonarray.getJSONObject(4);
                card_title5.setText(finaljsonobject5.getString("title"));
              //  card_time5.setText(finaljsonobject5.getString("time"));
                hiden_image5.setText(finaljsonobject5.getString("image"));
              //  card5_full_news.setText(Html.fromHtml(finaljsonobject5.getString("long")));
                Glide.with(getContext()).load(finaljsonobject5.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image5);

                JSONObject finaljsonobject6 = jsonarray.getJSONObject(5);
                card_title6.setText(finaljsonobject6.getString("title"));
               // card_time6.setText(finaljsonobject6.getString("time"));
                hiden_image6.setText(finaljsonobject6.getString("image"));
             //   card6_full_news.setText(Html.fromHtml(finaljsonobject6.getString("long")));
                Glide.with(getContext()).load(finaljsonobject6.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image6);

                JSONObject finaljsonobject7 = jsonarray.getJSONObject(6);
                card_title7.setText(finaljsonobject7.getString("title"));
                // card_time6.setText(finaljsonobject6.getString("time"));
                hiden_image7.setText(finaljsonobject7.getString("image"));
                //   card6_full_news.setText(Html.fromHtml(finaljsonobject6.getString("long")));
                Glide.with(getContext()).load(finaljsonobject7.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image7);

                JSONObject finaljsonobject8 = jsonarray.getJSONObject(7);
                card_title8.setText(finaljsonobject8.getString("title"));
                // card_time6.setText(finaljsonobject6.getString("time"));
                hiden_image8.setText(finaljsonobject8.getString("image"));
                //   card6_full_news.setText(Html.fromHtml(finaljsonobject6.getString("long")));
                Glide.with(getContext()).load(finaljsonobject8.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image8);

                JSONObject finaljsonobject9 = jsonarray.getJSONObject(8);
                card_title9.setText(finaljsonobject9.getString("title"));
                // card_time6.setText(finaljsonobject6.getString("time"));
                hiden_image9.setText(finaljsonobject9.getString("image"));
                //   card6_full_news.setText(Html.fromHtml(finaljsonobject6.getString("long")));
                Glide.with(getContext()).load(finaljsonobject9.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image9);

                marqueeText.setSelected(true);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    public class JsonTask_flash_news extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                String finalJson = buffer.toString();
                Log.d("final Json", buffer.toString());
                return finalJson;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ArrayList<String> title_values = new ArrayList<String>();


            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonarray = jsonObject.getJSONArray("response");


                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject finaljsonobject = jsonarray.getJSONObject(i);
                    String title = finaljsonobject.getString("title");
                    String appn= Html.fromHtml("&#9733")+"\t"+title+"\t";
                    title_values.add(appn.toString());

                }


                marqueeText.setText(title_values.toString().replace(",","\t"));
                marqueeText.setSelected(true);


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();  // Always call the superclass method first
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible())
        {
            if (!isVisibleToUser)
            {

                videoView.pause();

            }

            if (isVisibleToUser)
            {

                     videoView.setVideoURI(Uri.parse("http://45.79.203.234:1935/win/myStream/playlist.m3u8"));
                     videoView.start();


            }
        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            hide_layout.setVisibility(View.GONE);
            make_full_screen.setFitsSystemWindows(true);
      /*      make_full_screen.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;
            make_full_screen.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;
*/

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){



            make_full_screen.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;
            make_full_screen.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;

            hide_layout.setVisibility(View.VISIBLE);

        }
    }


    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    videoView.pause();

                }

                return false;
            }
        });
    }


}
