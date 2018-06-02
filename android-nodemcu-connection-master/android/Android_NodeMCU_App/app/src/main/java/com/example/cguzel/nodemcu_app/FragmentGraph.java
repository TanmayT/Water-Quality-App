package com.example.cguzel.nodemcu_app;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class FragmentGraph extends Fragment {
    private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("LOG", "I was here");
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        mSeries1 = new LineGraphSeries<>(generateData());
        graph.addSeries(mSeries1);
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(32);

        GraphView graph2 = (GraphView) rootView.findViewById(R.id.graph2);
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries(mSeries2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);
        GridLabelRenderer glr2 = graph2.getGridLabelRenderer();
        glr2.setPadding(32);

        return rootView;
    }

    @Override
    public void onAttach(Activity GraphActivity) {
        super.onAttach(GraphActivity);
//            ((com.example.cguzel.nodemcu_app.MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(com.example.cguzel.nodemcu_app.MainActivity.ARG_SECTION_NUMBER));
    }

    @Override
    public void onResume() {
        Log.d("XD", "In resume");
        super.onResume();

        IntentFilter filter = new IntentFilter();
        filter.addAction("my.action");
        getActivity().registerReceiver(ReceivefromService, filter);

        mTimer1 = new Runnable() {
            @Override
            public void run() {
                mSeries1.resetData(generateData());
                mHandler.postDelayed(this, 300);
            }
        };
        mHandler.postDelayed(mTimer1, 300);

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                mSeries2.appendData(new DataPoint(graph2LastXValue,Double.parseDouble(ReceivefromService.getData())), true, 40);
                mHandler.postDelayed(this, 200);
            }
        };

        mHandler.postDelayed(mTimer2, 1000);


    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer1);
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
        try {
            getActivity().unregisterReceiver(ReceivefromService);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("Receiver not registered")) {
                Log.i("TAG","Tried to unregister the reciver when it's not registered");
            }
            else
            {
                throw e;
            }
        }
    }

    private DataPoint[] generateData() {
        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double f = mRand.nextDouble()*0.15+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }

    double mLastRandom = 2;
    Random mRand = new Random();
    private double getRandom() {
        return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
    }

    private MyBroadcastReceiver ReceivefromService = new MyBroadcastReceiver();
    public class MyBroadcastReceiver extends BroadcastReceiver{
        private String data = "0";
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //get the data using the keys you entered at the service
            String action = intent.getAction();

            Log.i("Receiver", "Broadcast received: " + action);
            data = intent.getStringExtra("data");
        }

        public String getData(){
            return data;
        }
    };

}