package com.maxwell.win_news.News_Tabs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
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
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.maxwell.win_news.Full_news_view_page;
import com.maxwell.win_news.Adapter_class.News_Collector_Module;
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
import java.util.HashMap;
import java.util.List;


public class Fragment_World_Tab extends Fragment  {

    HttpURLConnection connection;
    BufferedReader reader;
    InputStream stream;
    TextView marqueeText;
    private SliderLayout mDemoSlider;
    TextView card_title1, card_title2, card_title3,card_time1,card_time2,card_time3,card1_full_news,card2_full_news,card3_full_news;
    ImageView card_image1,card_image2,card_image3;
    TextView hiden_image1,hiden_image2,hiden_image3,card1_news_id,card2_news_id,card3_news_id;
    LinearLayout card1,card2,card3;
    ListView news_list;
    LinearLayout common_view;
    TextView loading;
    public Fragment_World_Tab() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.tab_world_fragment, container, false);

        news_list=(ListView)view.findViewById(R.id.news_list);
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.tab_world_fragment_header,news_list,false);
        news_list.addHeaderView(header);

        common_view=(LinearLayout) header.findViewById(R.id.common_view);
       // loading=(TextView) header.findViewById(R.id.loading);



        card1=(LinearLayout) header.findViewById(R.id.card1);
        card1_news_id=(TextView)header.findViewById(R.id.card1_news_id);
        card_time1=(TextView)header.findViewById(R.id.card1_time);
        hiden_image1=(TextView)header.findViewById(R.id.hiden_image1);
        card_image1=(ImageView)header.findViewById(R.id.card1_image);
        card1_full_news=(TextView)header.findViewById(R.id.card1_full_news);
        card_title1 =(TextView)header.findViewById(R.id.card1_text);



        card3=(LinearLayout) header.findViewById(R.id.card3);
        card3_news_id=(TextView)header.findViewById(R.id.card3_news_id);
        hiden_image3=(TextView)header.findViewById(R.id.hiden_image3);
        card_time2=(TextView)header.findViewById(R.id.card2_time);
        card_image3=(ImageView)header.findViewById(R.id.card3_image);
        card3_full_news=(TextView)header.findViewById(R.id.card3_full_news);
        card_title3 =(TextView)header.findViewById(R.id.card3_text);
        mDemoSlider = (SliderLayout)header.findViewById(R.id.slider);

        card2=(LinearLayout) header.findViewById(R.id.card2);
        card2_news_id=(TextView)header.findViewById(R.id.card2_news_id);
        card_time3=(TextView)header.findViewById(R.id.card3_time);
        hiden_image2=(TextView)header.findViewById(R.id.hiden_image2);
        card_image2=(ImageView)header.findViewById(R.id.card2_image);
        card2_full_news=(TextView)header.findViewById(R.id.card2_full_news);
        card_title2 =(TextView)header.findViewById(R.id.card2_text);
        mDemoSlider = (SliderLayout)header.findViewById(R.id.slider);

        //flash news scrolling text
        marqueeText=(TextView)header.findViewById(R.id.MarqueeText);
        marqueeText.setSelected(true);



       new JsonTask_three_card().execute("http://www.wintvindia.com/api/category.php?category_id=3");
       new JsonTask_image().execute("http://www.wintvindia.com/api/slider_news.php?category_id=3");
       new JsonTask_flash_news().execute("http://www.wintvindia.com/api/flashnews.php");


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {three_card_full_news("card_one");
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {three_card_full_news("card_two");
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {three_card_full_news("card_three");
            }
        });


        return view;
    }

    public void three_card_full_news(String cad_no) {
        if(cad_no.equals("card_one"))
        {
            Intent i=new Intent(getActivity(),Full_news_view_page.class);
            i.putExtra("news_id",card1_news_id.getText().toString());
            i.putExtra("news_image",hiden_image1.getText().toString());
            i.putExtra("news_title",card_title1.getText().toString());
            i.putExtra("news_full_content",card1_full_news.getText().toString());
            i.putExtra("news_time",card_time1.getText().toString());
            i.putExtra("news_section","உலகம்");
            startActivity(i);
        }
        if(cad_no.equals("card_two")){

            Intent i=new Intent(getActivity(),Full_news_view_page.class);
            i.putExtra("news_id",card2_news_id.getText().toString());
            i.putExtra("news_image",hiden_image2.getText().toString());
            i.putExtra("news_title",card_title2.getText().toString());
            i.putExtra("news_full_content",card2_full_news.getText().toString());
            i.putExtra("news_time",card_time2.getText().toString());
            i.putExtra("news_section","உலகம்");
            startActivity(i);
        }
        if(cad_no.equals("card_three")){

            Intent i=new Intent(getActivity(),Full_news_view_page.class);
            i.putExtra("news_id",card3_news_id.getText().toString());
            i.putExtra("news_image",hiden_image3.getText().toString());
            i.putExtra("news_title",card_title3.getText().toString());
            i.putExtra("news_full_content",card3_full_news.getText().toString());
            i.putExtra("news_time",card_time3.getText().toString());
            i.putExtra("news_section","உலகம்");
            startActivity(i);
        }

    }


    public class JsonTask_three_card extends AsyncTask<String, String, String> {
        ProgressDialog dialog=new ProgressDialog(getActivity());
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

            List<News_Collector_Module> movieModelslist = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonarray = jsonObject.getJSONArray("response");

                JSONObject finaljsonobject1 = jsonarray.getJSONObject(0);
                card_title1.setText(finaljsonobject1.getString("title"));
                card1_news_id.setText(finaljsonobject1.getString("id"));
                card_time1.setText(finaljsonobject1.getString("time"));
                hiden_image1.setText(finaljsonobject1.getString("image"));
                card1_full_news.setText(Html.fromHtml(finaljsonobject1.getString("long")));
                Glide.with(getContext()).load(finaljsonobject1.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image1);


                JSONObject finaljsonobject2 = jsonarray.getJSONObject(1);
                card_title2.setText(finaljsonobject2.getString("title"));
                card2_news_id.setText(finaljsonobject2.getString("id"));
                card_time2.setText(finaljsonobject2.getString("time"));
                hiden_image2.setText(finaljsonobject2.getString("image"));
                card2_full_news.setText(Html.fromHtml(finaljsonobject2.getString("long")));
                Glide.with(getContext()).load(finaljsonobject2.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image2);



                JSONObject finaljsonobject3 = jsonarray.getJSONObject(2);
                card_title3.setText(finaljsonobject3.getString("title"));
                card3_news_id.setText(finaljsonobject3.getString("id"));
                card_time3.setText(finaljsonobject3.getString("time"));
                hiden_image3.setText(finaljsonobject3.getString("image"));
                card3_full_news.setText(Html.fromHtml(finaljsonobject3.getString("long")));
                Glide.with(getContext()).load(finaljsonobject3.getString("image"))
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(card_image3);


                for (int i = 3; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    News_Collector_Module movieModel = new News_Collector_Module();


                    String news_title=jsonobject.getString("short");
                    movieModel.setNews_title(news_title);

                    String news_image=jsonobject.getString("image");
                    movieModel.setNews_image(news_image);

                    String news_long_content=jsonobject.getString("long");
                    movieModel.setNews_full_content(news_long_content);

                    String news_time=jsonobject.getString("time");
                    movieModel.setNews_time(news_time);

                    String news_id=jsonobject.getString("id");
                    movieModel.setNews_id(news_id);

                    movieModelslist.add(movieModel);



                    marqueeText.setSelected(true);

                }

                MovieAdapter Adapter = new MovieAdapter(getActivity(), R.layout.news_list_items, movieModelslist);
                news_list.setAdapter(Adapter);

             /*   loading_layout.setVisibility(View.GONE);
                news_list.setVisibility(View.VISIBLE);
*/

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    public class JsonTask_image extends AsyncTask<String, String, String> {


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

            final HashMap<String,String> file_maps = new HashMap<String, String>();
            file_maps.clear();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonarray = jsonObject.getJSONArray("response");


                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject finaljsonobject = jsonarray.getJSONObject(i);
                    String title = finaljsonobject.getString("title");
                    String image = finaljsonobject.getString("image");
                    String id = finaljsonobject.getString("id");
                    file_maps.put(title, image);
                }


                for(final String name : file_maps.keySet()){
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    // initialize a SliderLayout
                    textSliderView
                            .description(name)
                            .image(file_maps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView slider) {
                                    String i=  slider.getDescription();
                                    String j=slider.getUrl();
                                    Toast.makeText(getActivity(), i+j, Toast.LENGTH_SHORT).show();
                                    mDemoSlider.startAutoCycle();
                                }
                            });


                    //add your extra information
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra",name);

                    mDemoSlider.addSlider(textSliderView);

                }
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                mDemoSlider.setDuration(4000);




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
                    String appn= Html.fromHtml("&#9733")+"\t\t"+title;
                    title_values.add(appn.toString());

                }


                marqueeText.setText(title_values.toString().replace(",","\t"));



            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }



    @Override
    public void onStop() {
        // make sure to call stopAutoCycle() on the slider before activity
        super.onStop();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible())
        {
            if (!isVisibleToUser)
            {


            }

            if (isVisibleToUser)
            {


            }
        }

    }

    public class MovieAdapter extends ArrayAdapter {
        private List<News_Collector_Module> movieModelslist;
        private int resource;
        private LayoutInflater inflater;
        public Activity context;

        public MovieAdapter(Activity context, int resource, List<News_Collector_Module> objects) {
            super(context, resource, objects);

            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.context = context;

            this.movieModelslist = objects;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = context.getLayoutInflater();

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_list_items, parent, false);
            final TextView news_title,news_time,news_section,news_full,news_id;
            ImageView news_image;



            //    TouchImageView imageView = new TouchImageView(context);

            news_id = (TextView) convertView.findViewById(R.id.news_id);
            news_title = (TextView) convertView.findViewById(R.id.news_title);
            news_time = (TextView) convertView.findViewById(R.id.news_time);
            news_full = (TextView) convertView.findViewById(R.id.news_full);
            news_section = (TextView) convertView.findViewById(R.id.news_section);
            news_image= (ImageView) convertView.findViewById(R.id.news_image);


            news_section.setText("உலகம்");
            news_id.setText(movieModelslist.get(position).getNews_id());
            news_full.setText(Html.fromHtml(movieModelslist.get(position).getNews_full_content()));
            news_time.setText(movieModelslist.get(position).getNews_time());
            news_title.setText(Html.fromHtml(movieModelslist.get(position).getNews_title()));
            Glide.with(getContext()).load(movieModelslist.get(position).getNews_image())
                    .crossFade()
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(news_image);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(getActivity(),Full_news_view_page.class);
                    i.putExtra("news_id",movieModelslist.get(position).getNews_id());
                    i.putExtra("news_image",movieModelslist.get(position).getNews_image());
                    i.putExtra("news_title",movieModelslist.get(position).getNews_title());
                    i.putExtra("news_full_content",news_full.getText().toString());
                    i.putExtra("news_time",movieModelslist.get(position).getNews_time());
                    i.putExtra("news_section","உலகம்");
                    startActivity(i);

                }
            });

            return convertView;
        }
    }

}





