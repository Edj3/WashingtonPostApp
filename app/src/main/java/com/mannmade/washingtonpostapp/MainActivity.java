package com.mannmade.washingtonpostapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadPostsTask().execute();

    }

    public class DownloadPostsTask extends AsyncTask<Void, Integer, Integer> {
        protected Integer doInBackground(Void... voids) {
            try{
                String washPostURL = "http://www.washingtonpost.com/wp-srv/simulation/simulation_test.json";
                ConnectionManager urlConnect = ConnectionManager.getInstance(getApplicationContext());
                String washJSONString = urlConnect.connectToURL(washPostURL);
                JSONParser.getInstance().getJSONforString(washJSONString);
                return 1;
            }catch(Exception e){
                Log.e(e.getClass().getName(), e.getMessage());
                return 0;
            }
        }

        protected void onPostExecute(Integer result) {
            if(result == 1){
                TableLayout postTable = (TableLayout) findViewById(R.id.post_table);
                setupPostTable(postTable);

            }
        }
    }

    private void setupPostTable(final TableLayout table){
        if(table != null){
            //Title Table Row
            table.addView(this.getLayoutInflater().inflate(R.layout.table_row, null));
            for(int i = 0; i < JSONParser.getInstance().jsonArrayList.size(); i++){
                RelativeLayout currentTitle = (RelativeLayout) this.getLayoutInflater().inflate(R.layout.post_title, null);
                TableRow row = (TableRow) table.getChildAt(0);
                row.addView(currentTitle);
                RelativeLayout titleLayout = (RelativeLayout) row.getChildAt(i);
                TextView title = (TextView) titleLayout.findViewById(R.id.post_title_text);
                title.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(i).title));
                final int index = i;
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                        intent.putExtra("index", index);
                        startActivity(intent);
                    }
                });
            }

            //Title Table Row
            table.addView(this.getLayoutInflater().inflate(R.layout.table_row, null));
            for(int i = 0; i < JSONParser.getInstance().jsonArrayList.size(); i++){
                RelativeLayout currentDate = (RelativeLayout) this.getLayoutInflater().inflate(R.layout.post_date, null);
                TableRow row = (TableRow) table.getChildAt(1);
                row.addView(currentDate);
                RelativeLayout dateLayout = (RelativeLayout) row.getChildAt(i);
                TextView date = (TextView) dateLayout.findViewById(R.id.post_date_text);
                date.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(i).date));
                final int index = i;
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                        intent.putExtra("index", index);
                        startActivity(intent);
                    }
                });
            }

            Button titleButton = (Button) findViewById(R.id.sort_title_button);
            Button dateButton = (Button) findViewById(R.id.sort_date_button);

            //not enough time for null checks && to separate this logic into separate function
            titleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //comparator for title
                    Collections.sort(JSONParser.getInstance().jsonArrayList, new Comparator<PostObject>() {
                        public int compare(PostObject p1, PostObject p2) {
                            return p1.title.compareTo(p2.title);
                        }
                    });

                    for(int i = 0; i < JSONParser.getInstance().jsonArrayList.size(); i++){
                        TableRow row = (TableRow) table.getChildAt(0);
                        RelativeLayout titleLayout = (RelativeLayout) row.getChildAt(i);
                        TextView title = (TextView) titleLayout.findViewById(R.id.post_title_text);
                        title.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(i).title));
                        final int index = i;
                        title.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                                intent.putExtra("index", index);
                                startActivity(intent);
                            }
                        });
                    }
                    for(int i = 0; i < JSONParser.getInstance().jsonArrayList.size(); i++){
                        TableRow row = (TableRow) table.getChildAt(1);
                        RelativeLayout dateLayout = (RelativeLayout) row.getChildAt(i);
                        TextView date = (TextView) dateLayout.findViewById(R.id.post_date_text);
                        date.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(i).date));
                        final int index = i;
                        date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                                intent.putExtra("index", index);
                                startActivity(intent);
                            }
                        });
                    }
                }
            });

            dateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sort with comparator
                    Collections.sort(JSONParser.getInstance().jsonArrayList, new Comparator<PostObject>() {
                        public int compare(PostObject p1, PostObject p2) {
                            return p1.date.compareTo(p2.date);
                        }
                    });

                    for(int i = 0; i < JSONParser.getInstance().jsonArrayList.size(); i++){
                        TableRow row = (TableRow) table.getChildAt(1);
                        RelativeLayout dateLayout = (RelativeLayout) row.getChildAt(i);
                        TextView date = (TextView) dateLayout.findViewById(R.id.post_date_text);
                        date.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(i).date));
                        final int index = i;
                        date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                                intent.putExtra("index", index);
                                startActivity(intent);
                            }
                        });
                    }
                    for(int i = 0; i < JSONParser.getInstance().jsonArrayList.size(); i++){
                        TableRow row = (TableRow) table.getChildAt(0);
                        RelativeLayout titleLayout = (RelativeLayout) row.getChildAt(i);
                        TextView title = (TextView) titleLayout.findViewById(R.id.post_title_text);
                        title.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(i).title));
                        final int index = i;
                        title.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
                                intent.putExtra("index", index);
                                startActivity(intent);
                            }
                        });
                    }
                }
            });
        }
    }
}
