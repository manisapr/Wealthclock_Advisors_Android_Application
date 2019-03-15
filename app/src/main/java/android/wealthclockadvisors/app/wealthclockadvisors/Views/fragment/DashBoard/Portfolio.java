package android.wealthclockadvisors.app.wealthclockadvisors.Views.fragment.DashBoard;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.wealthclockadvisors.app.wealthclockadvisors.adapter.PortfolioAdapter;
import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.CategoryDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.InvestMentDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.model.PortfolioDetailsModel;
import android.wealthclockadvisors.app.wealthclockadvisors.utils.Utility;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.victor.loading.newton.NewtonCradleLoading;


import java.util.ArrayList;
import java.util.List;

import wealthclockadvisors.app.wealthclockadvisors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Portfolio extends Fragment implements OnChartValueSelectedListener {
    PortfolioAdapter _portfolioAdapter;
    RecyclerView recycle_scheme_name;
    Context context;
    ArrayList<InvestMentDetailsModel> schemeName=new ArrayList<>();
    ArrayList<CategoryDetailsModel > chartValue;

    private TextView _netInvestAmount,_currentMarketValue,_netXirr;

    String selectedPiedata="Total Investment\n00.00";
    String allocation="";


    String[] yData;
    String[] xData = {"sss","ggg","cccc"};

    private String[] test = { "Balanced", "Debt","Equity"};

    PieChart pie_chart;



    public Portfolio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        chartValue = new ArrayList<>();

        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(getContext());
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        UserHandler.getInstance().getPortfolioData(SharedPreferenceManager.getClientCode(getContext()),getContext());

        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        _netInvestAmount = view.findViewById(R.id.netInvestAmount);
        _currentMarketValue = view.findViewById(R.id.currentMarketValue);
        _netXirr = view.findViewById(R.id.netXirr);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycle_scheme_name=(RecyclerView)view.findViewById(R.id.recycle_scheme_name);
       setRecyclerView();
        pie_chart=(PieChart)view.findViewById(R.id.pie_chart);
        //addDataSet();
    }

    private void setRecyclerView() {
        _portfolioAdapter =new PortfolioAdapter(getContext(),schemeName);
        recycle_scheme_name.setAdapter(_portfolioAdapter);
        recycle_scheme_name.setHasFixedSize(true);
        recycle_scheme_name.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        System.out.println("onValueSelected | portfolio: "+e.getX() + "highlight: "+h.toString());

    }

   /* @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }*/

    @Override
    public void onNothingSelected() {

    }


    public void addDataSet(){

    }

    private class ServerResultHandler implements ihttpResultHandler{
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }
        /*@Override
        public void onSuccess(Object message, String operation_flag) {

        }*/

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            if (message != null) {
                if (operation_flag.equalsIgnoreCase("getPortfolioData"))
                {
                    ArrayList<CategoryDetailsModel> categoryArrayList = (ArrayList<CategoryDetailsModel>) message;
                    chartValue.clear();
                    chartValue.addAll(categoryArrayList);
                    //addDataSet(chartValue);
                    yData = new String[chartValue.size()];
                    for (int i=0;i<chartValue.size();i++)
                    {
                        yData[i]  = chartValue.get(i).getCategory_percentage();
                        System.out.println("portfolio data: "+yData[i]);

                    }
                    PortfolioDetailsModel portfolioDetailsModel = (PortfolioDetailsModel) messsage1;
                    Utility.set_totalinvest(portfolioDetailsModel.getNetInvestment());
                    selectedPiedata = "Total Investment"+ "\n" +  portfolioDetailsModel.getNetInvestment();
                    addDataSet(yData);


                    _netInvestAmount.setText(portfolioDetailsModel.getNetInvestment());
                    _currentMarketValue.setText(portfolioDetailsModel.getCurrentMarketValue());
                    _netXirr.setText(portfolioDetailsModel.getXirrValue());

                    ArrayList<InvestMentDetailsModel> fundlist  = (ArrayList<InvestMentDetailsModel>) message2;
                    schemeName.clear();
                    schemeName.addAll(fundlist);
                    System.out.println("44444444444444");
                    _portfolioAdapter.setfund_list(schemeName);


                }
            }
        }

        @Override
        public void onError(Object message) {

        }
    }

    private void addDataSet(String[] yData) {
        ArrayList<PieEntry> yEntry=new ArrayList<>();
        ArrayList<String> xEntry=new ArrayList<>();
        ArrayList<Integer> colorEntry=new ArrayList<>();
        System.out.println("length check :- "+yData.length);
        for (int i=0; i<yData.length;i++){
            Float c = Float.valueOf(yData[i]);

            yEntry.add(new PieEntry(c,
                    test[i]));
            //yEntry.add(new PieEntry(c,i));

        }

        for (int i=0;i<xData.length; i++){
            xEntry.add(xData[i]);
        }

        String[] color=(getActivity().getBaseContext()).getResources().getStringArray(R.array.color);

        for (int i=0;i<color.length;i++){

            int colorCode= Color.parseColor(color[i]);
            //colorEntry.add(colorCode);

            colorEntry.add(0,Color.parseColor("#FFFF00"));
            colorEntry.add(1,Color.parseColor("#FFA500"));
            colorEntry.add(2,Color.parseColor("#13a097"));
            colorEntry.add(3,Color.parseColor("#FFFF00"));

        }


        PieDataSet pieDataSet=new PieDataSet(yEntry,"");
        pieDataSet.setColors(colorEntry);
        pieDataSet.setSliceSpace(10);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        //pieDataSet.setSliceSpace(3f);
        pieDataSet.setIconsOffset(new MPPointF(0, 40));
        pieDataSet.setSelectionShift(5f);

        pieDataSet.setValueLinePart1OffsetPercentage(80.f);
        pieDataSet.setValueLinePart1Length(0.3f);
        pieDataSet.setValueLinePart2Length(0.7f);
        pieDataSet.setUsingSliceColorAsValueLineColor(true);
        pieDataSet.setValueLineWidth(2f);


        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData pieData=new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10);
        pieData.setValueTextColor(Color.WHITE);

        //pie_chart.getDescription().setEnabled(false);
        Legend l = pie_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(15.00f);
        l.setDrawInside(false);
        l.setXEntrySpace(16f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setXOffset(20f);
        l.setTextColor(Color.parseColor("#ffffff"));



        pie_chart.getLegend().setEnabled(true);
        pie_chart.setData(pieData);
        pie_chart.setRotationEnabled(true);
        pie_chart.setHighlightPerTapEnabled(true);
        pie_chart.setDrawHoleEnabled(true);
        pie_chart.setHoleRadius(50);
        pie_chart.setTransparentCircleRadius(20);
        pie_chart.setCenterText(allocation+"\n"+selectedPiedata);
        pie_chart.setCenterTextSize(13);
        pie_chart.setCenterTextColor(Color.BLACK);
        pie_chart.getDescription().setEnabled(false);
        pie_chart.setExtraOffsets(5, 0, 5, -10);

        pie_chart.invalidate();

        pie_chart.setOnChartValueSelectedListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
