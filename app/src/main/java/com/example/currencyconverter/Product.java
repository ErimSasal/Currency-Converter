package com.example.currencyconverter;

import java.util.ArrayList;

public class Product {

    int imageId;
    String currencyName;
    Double currencyRates;
    ArrayList<Product> productList,spinnerList;


    public Product(String currencyName,int imageId,Double currencyRates) {
        this.imageId = imageId;
        this.currencyName = currencyName;
        this.currencyRates = currencyRates;
    }


    public Product(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getCurrencyRates() {
        return currencyRates;
    }

    public void setCurrencyRates(Double currencyRates) {
        this.currencyRates = currencyRates;
    }

    public static ArrayList<Product> getData(){
        //DatabaseHelper db;
        //db = new DatabaseHelper();

        ArrayList<Product> productList = new ArrayList<Product>();
        int productImages[] = {R.drawable.aed,R.drawable.afn,R.drawable.all,R.drawable.amd,R.drawable.ang,R.drawable.aoa,R.drawable.ars,R.drawable.aud, R.drawable.awg,R.drawable.azn,R.drawable.bam,R.drawable.bbd,R.drawable.bdt,R.drawable.bgn,R.drawable.bhd,R.drawable.bif,R.drawable.bmd, R.drawable.bnd,R.drawable.bob,R.drawable.brl,R.drawable.bsd,R.drawable.btc,R.drawable.btn,R.drawable.bwp,R.drawable.byn,R.drawable.byr, R.drawable.bzd,R.drawable.cad,R.drawable.cdf,R.drawable.chf,R.drawable.clf,R.drawable.clp,R.drawable.cny,R.drawable.cop,R.drawable.crc,R.drawable.cuc, R.drawable.cup,R.drawable.cve,R.drawable.czk,R.drawable.djf,R.drawable.dkk,R.drawable.dop,R.drawable.dzd,R.drawable.egp,R.drawable.ern,R.drawable.etb, R.drawable.eur,R.drawable.fjd,R.drawable.fkp,R.drawable.gbp,R.drawable.gel,R.drawable.ggp,R.drawable.ghs,R.drawable.gip,R.drawable.gmd, R.drawable.gnf,R.drawable.gtq,R.drawable.gyd,R.drawable.hkd,R.drawable.hnl,R.drawable.hrk,R.drawable.htg,R.drawable.huf,R.drawable.idr,R.drawable.ils, R.drawable.imp,R.drawable.inr,R.drawable.iqd,R.drawable.irr,R.drawable.isk,R.drawable.jep,R.drawable.jmd,R.drawable.jod,R.drawable.jpy,R.drawable.kes, R.drawable.kgs,R.drawable.khr,R.drawable.kmf,R.drawable.kpw,R.drawable.krw,R.drawable.kwd,R.drawable.kyd,R.drawable.kzt,R.drawable.lak,R.drawable.lbp, R.drawable.lkr,R.drawable.lrd,R.drawable.lsl,R.drawable.ltl,R.drawable.lvl,R.drawable.lyd,R.drawable.mad,R.drawable.mdl, R.drawable.mga,R.drawable.mkd,R.drawable.mmk,R.drawable.mnt,R.drawable.mop,R.drawable.mro,R.drawable.mur,R.drawable.mvr,R.drawable.mwk,R.drawable.mxn,R.drawable.myr, R.drawable.mzn,R.drawable.nad,R.drawable.ngn,R.drawable.nio,R.drawable.nok,R.drawable.npr,R.drawable.nzd,R.drawable.omr,R.drawable.pab,R.drawable.pen,R.drawable.pgk, R.drawable.php,R.drawable.pkr,R.drawable.pln,R.drawable.pyg,R.drawable.qar,R.drawable.ron,R.drawable.rsd,R.drawable.rub,R.drawable.rwf,R.drawable.sar,R.drawable.sbd, R.drawable.scr,R.drawable.sdg,R.drawable.sek,R.drawable.sgd,R.drawable.shp,R.drawable.sll,R.drawable.sos,R.drawable.srd,R.drawable.std,R.drawable.svc,R.drawable.syp, R.drawable.szl,R.drawable.thb,R.drawable.tjs,R.drawable.tmt,R.drawable.tnd,R.drawable.top,R.drawable.trl,R.drawable.ttd,R.drawable.twd,R.drawable.tzs,R.drawable.uah, R.drawable.ugx,R.drawable.usd,R.drawable.uyu,R.drawable.uzs,R.drawable.vef,R.drawable.vnd,R.drawable.vuv,R.drawable.wst,R.drawable.xaf,R.drawable.xag,R.drawable.xau, R.drawable.xcd,R.drawable.xdr,R.drawable.xof,R.drawable.xpf,R.drawable.yer,R.drawable.zar,R.drawable.zmk,R.drawable.zmw,R.drawable.zwl};
        String[] productNames = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", "BTC", "BTN", "BWP", "BYN", "BYR", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STD", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMK", "ZMW", "ZWL"};
        String[] productRates = {"4.1198","87.935288","122.424641","539.991791","1.899575","540.856943","67.228303","1.59814","2.018847","1.916988","1.963764", "2.276197","95.705482","1.95594","0.424515","2118.529677","1.121582","1.516628","7.784251","4.50842", "1.127355","0.000155","80.475144","11.929701","2.378209","21983.000408","2.272381","1.456654","1889.865115","1.085017","0.030067","829.63081","7.809798","3680.481412","643.720899", "1.121582","29.721914","110.712324","25.422999","199.327233","7.472874","59.613865","134.074712","17.998473","16.824087","35.984692","1","2.405347","0.911711","0.846604","3.208194","0.846564","6.324353", "0.911711","57.481356","10766.099284","8.686196","235.852942","8.738299","27.756146","7.475605","107.16097","330.945084","15583.275682","3.873887","0.846564","79.821282","1345.827661","47224.195749", "135.821565","0.846564","149.411679","0.795196","121.92828","114.109492","77.963046","4593.861056","492.206013","492.206013","1009.348179","1295.146281","0.340062", "0.939496","431.684172","10016.370216","1704.788073","204.471244","210.406576","15.71342","3.311739","0.678433","1.577172","10.783169","19.390245","4161.012194","61.739453","1663.953756", "3071.485128","9.041532","400.404464","40.92688","17.328745","830.173208","21.233446","4.587833","68.926862","15.713088","406.575025","38.030659","9.849422","128.760243","1.668276", "0.433471","1.127355","3.738806","3.844232","56.869771","174.585489", "4.383422","7274.893544","4.083713","4.789273","117.597804","69.408967","1068.808433", "4.207468","9.313504","15.425324","50.601222","10.502376","1.508632","1.481494","10935.42107","651.639123","8.36475", "24182.186499","9.864858","577.614667","15.828453","33.849088","10.921929","3.925536","3.112383","2.563094","6.669457", "7.622698","33.492113","2577.061092","26.717951","4131.69336","1.121582","41.880213","10718.255968","11.201795","25989.850865", "129.35163","2.955901","658.618094","0.062721","0.000738","3.031131","0.809333","658.618095","119.743814","280.788194","15.720773","10095.576084","15.867311","361.149294"};

        for(int i=0;i < productImages.length;i++){
            Product temp = new Product();
            temp.setImageId(productImages[i]);
            temp.setCurrencyName(productNames[i]);

            //db.insertFavCurrency(productNames[i],productImages[i],productRates[i]);

            productList.add(temp);
        }
        return productList;
    }




}
