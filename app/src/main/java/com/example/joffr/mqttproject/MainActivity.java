package com.example.joffr.mqttproject;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    GraphView graf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: IMPLEMENTAR UM REFRESH

                Snackbar.make(view, "A implementar", Snackbar.LENGTH_LONG).show();
            }
        });

        //TODO: IMPLEMENTAR QUALQUER COISA NOVA AQUI EMBAIXO


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "yey", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //O primeiro fragment
    public static class FragmentMain extends Fragment {

        //TODO IMPLEMENTAR O FRAGMENT
        int count = 1;

        DataPoint[] pontos = new DataPoint[]{
                new DataPoint(1,1)
        };
        DataPoint[] auxpontos;

        LineGraphSeries<DataPoint> series;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            //TODO: IMPLEMENTAR O INFLA FRAGMENT

            final View view = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) view.findViewById(R.id.section_label);
//            textView.setText("Helou");
            final GraphView grafi = (GraphView) view.findViewById(R.id.graph);

            //conjunto de pontos
            series = new LineGraphSeries<>(pontos);

            //JOGA O CONJUNTO NA VIEW
            grafi.addSeries(series);

            //zoom e scroll
            //grafi.getViewport().setScalable(true);
            //scroll horizontal
            grafi.getViewport().setScrollable(true);
            //scroll orizontal e vertical zoom e scrool
            grafi.getViewport().setScalableY(true);
            //scroll vertical
            //grafi.getViewport().setScrollableY(true);

            //JANELA DE EXIBIÇÃO MANUAL
            // set manual X bounds
            grafi.getViewport().setXAxisBoundsManual(true);
            grafi.getViewport().setMinX(1);
            grafi.getViewport().setMaxX(5);

            // set manual Y bounds
            grafi.getViewport().setYAxisBoundsManual(true);
            grafi.getViewport().setMinY(0);
            grafi.getViewport().setMaxY(5);

            //listener do ponto
            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPoint) {
                    Toast.makeText(getContext(), "Você clicou no ponto: "+dataPoint, Toast.LENGTH_SHORT).show();
                }
            });


            Button botao = (Button) view.findViewById(R.id.botaoAdd);
            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO ADD PONTOS
                    count++;
                    Toast.makeText(getContext(), "contador: "+count, Toast.LENGTH_SHORT).show();
                    //aux pega os valores guardados para recriar o velho com um novo tamanho
                    auxpontos = new DataPoint[pontos.length];
                    auxpontos = pontos.clone();
                    pontos = new DataPoint[auxpontos.length+1];
                    for (int i=0; i<auxpontos.length; i++){
                        pontos[i] = auxpontos[i];
                    }
                    pontos[pontos.length-1] = new DataPoint(count, count);
                    series.resetData(pontos);

                }
            });
            return view;
        }
    }


    //O PAGER ADAPTER DE FARGMENTS
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {
                case 0:
                    return new FragmentMain();
                case 1:
                    return new FragmentMain();
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
