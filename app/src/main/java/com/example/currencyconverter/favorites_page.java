package com.example.currencyconverter;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class favorites_page extends Fragment {

    public favorites_page() {
        // Required empty public constructor
    }
    ConstraintLayout constraintLayout;
    ArrayList<String> currencyNames;
    ArrayList<String> currencyFlags;
    ArrayList<String> currencyRates;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    AdapterRecycler2 adapter2;
    ImageButton buttonCurrencies,buttonRefresh;
    DatabaseHelper dH;
    ImageView checked;


    private List<Product> products = new ArrayList<>();
    private int productImages[] = {R.drawable.aed,R.drawable.afn,R.drawable.all,R.drawable.amd,R.drawable.ang,R.drawable.aoa,R.drawable.ars,R.drawable.aud, R.drawable.awg,R.drawable.azn,R.drawable.bam,R.drawable.bbd,R.drawable.bdt,R.drawable.bgn,R.drawable.bhd,R.drawable.bif,R.drawable.bmd, R.drawable.bnd,R.drawable.bob,R.drawable.brl,R.drawable.bsd,R.drawable.btc,R.drawable.btn,R.drawable.bwp,R.drawable.byn,R.drawable.byr, R.drawable.bzd,R.drawable.cad,R.drawable.cdf,R.drawable.chf,R.drawable.clf,R.drawable.clp,R.drawable.cny,R.drawable.cop,R.drawable.crc,R.drawable.cuc, R.drawable.cup,R.drawable.cve,R.drawable.czk,R.drawable.djf,R.drawable.dkk,R.drawable.dop,R.drawable.dzd,R.drawable.egp,R.drawable.ern,R.drawable.etb, R.drawable.eur,R.drawable.fjd,R.drawable.fkp,R.drawable.gbp,R.drawable.gel,R.drawable.ggp,R.drawable.ghs,R.drawable.gip,R.drawable.gmd, R.drawable.gnf,R.drawable.gtq,R.drawable.gyd,R.drawable.hkd,R.drawable.hnl,R.drawable.hrk,R.drawable.htg,R.drawable.huf,R.drawable.idr,R.drawable.ils, R.drawable.imp,R.drawable.inr,R.drawable.iqd,R.drawable.irr,R.drawable.isk,R.drawable.jep,R.drawable.jmd,R.drawable.jod,R.drawable.jpy,R.drawable.kes, R.drawable.kgs,R.drawable.khr,R.drawable.kmf,R.drawable.kpw,R.drawable.krw,R.drawable.kwd,R.drawable.kyd,R.drawable.kzt,R.drawable.lak,R.drawable.lbp, R.drawable.lkr,R.drawable.lrd,R.drawable.lsl,R.drawable.ltl,R.drawable.lvl,R.drawable.lyd,R.drawable.mad,R.drawable.mdl, R.drawable.mga,R.drawable.mkd,R.drawable.mmk,R.drawable.mnt,R.drawable.mop,R.drawable.mro,R.drawable.mur,R.drawable.mvr,R.drawable.mwk,R.drawable.mxn,R.drawable.myr, R.drawable.mzn,R.drawable.nad,R.drawable.ngn,R.drawable.nio,R.drawable.nok,R.drawable.npr,R.drawable.nzd,R.drawable.omr,R.drawable.pab,R.drawable.pen,R.drawable.pgk, R.drawable.php,R.drawable.pkr,R.drawable.pln,R.drawable.pyg,R.drawable.qar,R.drawable.ron,R.drawable.rsd,R.drawable.rub,R.drawable.rwf,R.drawable.sar,R.drawable.sbd, R.drawable.scr,R.drawable.sdg,R.drawable.sek,R.drawable.sgd,R.drawable.shp,R.drawable.sll,R.drawable.sos,R.drawable.srd,R.drawable.std,R.drawable.svc,R.drawable.syp, R.drawable.szl,R.drawable.thb,R.drawable.tjs,R.drawable.tmt,R.drawable.tnd,R.drawable.top,R.drawable.trl,R.drawable.ttd,R.drawable.twd,R.drawable.tzs,R.drawable.uah, R.drawable.ugx,R.drawable.usd,R.drawable.uyu,R.drawable.uzs,R.drawable.vef,R.drawable.vnd,R.drawable.vuv,R.drawable.wst,R.drawable.xaf,R.drawable.xag,R.drawable.xau, R.drawable.xcd,R.drawable.xdr,R.drawable.xof,R.drawable.xpf,R.drawable.yer,R.drawable.zar,R.drawable.zmk,R.drawable.zmw,R.drawable.zwl};
    //private String[] productNames = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYN", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMK", "ZMW", "ZWL"};
    private Double[] productRates = {4.099112,86.207588,121.806392,534.245515,1.880312,538.141264,66.651904,1.605811,2.00871, 1.901545, 1.960952, 2.253186, 94.60773, 1.953847, 0.420813, 2103.565739, 1.11595, 1.507471, 7.716557, 4.53835, 1.115915, 0.000154, 80.149861, 11.948009, 2.354049, 21872.619888, 2.249376, 1.449647, 1880.376144, 1.08504, 0.030911, 852.924946, 7.773154, 3636.490449, 636.673063, 1.11595, 29.572675, 110.818183, 25.345401, 198.327072, 7.474414, 59.372877, 133.026764, 17.896, 16.739649, 35.766635, 1.00, 2.400971, 0.907134, 0.852684, 3.197242, 0.852972, 6.37251, 0.907134, 57.192872, 10515.043118, 8.603774, 233.169059, 8.681271, 27.614227, 7.449069, 106.075076, 329.781123, 15556.546023, 3.876867, 0.852972, 80.081732, 1328.538468, 46987.074903, 136.916336, 0.852972, 146.185856, 0.791253, 120.617499, 112.66029, 77.571586, 4541.916868, 492.413364, 1004.785162, 1301.041888, 0.338177, 0.929929, 427.316202, 9915.216089, 1689.548681, 202.435119, 209.914406, 15.962294, 3.295111, 0.675027,           1.566531, 10.718743, 19.249582, 4039.739366, 61.483663, 1634.301406, 3057.705478, 8.939752, 398.393962, 40.680936, 17.253009, 820.223631, 21.114277, 4.578189, 68.658867, 15.962247, 403.420082, 38.087794, 9.854993, 128.242857, 1.674595, 0.429676, 1.115915, 3.701053, 3.773306, 57.127755, 172.864787, 4.243121, 7230.887938, 4.063217, 4.772476, 117.565749, 69.243362, 1043.413245, 4.187025, 9.266739, 15.292622, 50.357286, 10.489177, 1.506265, 1.474063, 10880.512822, 648.367324, 8.322798, 24060.763477, 9.764506, 574.714624, 15.962164, 33.676585, 10.814748, 3.905825, 3.111831, 2.549988, 6.664681, 7.545509, 33.534708, 2564.345548, 26.410287, 4117.756052, 1.11595, 41.718975, 10604.318865, 11.145555, 25859.351242, 127.692762, 2.9036, 657.67788, 0.061844, 0.000719, 3.015911, 0.807916, 655.063018, 119.857046, 279.378487, 15.955602, 10044.893098, 15.818119, 359.335899};
    private List<Product> pro = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_favorites_page, container, false);
        dH = new DatabaseHelper(getActivity().getApplicationContext());

        Toolbar toolbarFav = view.findViewById(R.id.fToolbar);
        toolbarFav.setTitle("ConvertIT");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarFav);
        toolbarFav.setTitleTextColor(0xFFFFFFFF);
        toolbarFav.setSubtitleTextColor(Color.WHITE);

        buttonCurrencies = view.findViewById(R.id.currenciesListButton);
        buttonCurrencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), CurrencyActivity.class);
                startActivity(intent);
            }
        });
        buttonRefresh = view.findViewById(R.id.refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().recreate();
            }
        });

        currencyNames = new ArrayList<String>();
        currencyFlags = new ArrayList<>();
        currencyRates = new ArrayList<>();

        //recyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        try{
            pro = dH.getAllCurrencyData();

            if(pro.size()>0 || pro.size()<169) {
                adapter = new RecyclerAdapter(pro);
                recyclerView.setAdapter(adapter);
            }
        }catch (Exception e){

        }


        return view;
    }



   /* private void getProducts(){
        int count = 0;
        for(String productName : adapter2.getFavNames()){
            for(Integer productImg : adapter2.getFavImgs()){
                Product product = new Product(productImg,productName,productRates);
                products.add(product);
                count++;
            }
        }
    }*/

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


                return super.onContextItemSelected(item);

        }


    public void deleteItem(int position){
        dH = new DatabaseHelper(getActivity().getApplicationContext());
        pro = dH.getAllCurrencyData();

        //Toast.makeText(getActivity().getBaseContext(), "Item added", Toast.LENGTH_LONG).show();
    }



    private void displayMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    private class asyncTask extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage(getString(R.string.downloading));
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        protected Boolean doInBackground(Void... voids) {
            SQLiteOpenHelper dH = new DatabaseHelper(getActivity());
            SQLiteDatabase db = dH.getWritableDatabase();
            try {
                db.rawQuery( "select * from favorite", null);
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(getActivity(),
                        getString(R.string.crash), Toast.LENGTH_SHORT);
                toast.show();
            } else {
                dialog.dismiss();
            }
        }
    }


}
