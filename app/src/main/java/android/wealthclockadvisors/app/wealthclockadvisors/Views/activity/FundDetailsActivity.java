package android.wealthclockadvisors.app.wealthclockadvisors.Views.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import android.wealthclockadvisors.app.wealthclockadvisors.handler.UserHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.iinterface.ihttpResultHandler;
import android.wealthclockadvisors.app.wealthclockadvisors.manager.SharedPreferenceManager;
import android.wealthclockadvisors.app.wealthclockadvisors.model.FundDetailsModel;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import wealthclockadvisors.app.wealthclockadvisors.R;


public class FundDetailsActivity extends AppCompatActivity {

    private TextView _fundName,_userName,_folioNumber,_currentValue,_totalinvest,_gain_loss,_absoluteChange,_dividend_earned,_navAmount,_totalUnits,_date,_navDate,_dividend_reinvestment;
    private ImageView _rupeeSymbol;
    private KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_fund_details);
        hud = KProgressHUD.create(FundDetailsActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("Please Wait")
                .setCancellable(false);
        hud.show();
        String folio = getIntent().getExtras().getString("folio");
        String schemecode = getIntent().getExtras().getString("schemecode");
        String cc = getIntent().getExtras().getString("clientcode");

        System.out.println("FundDetailsActivity: "+folio + "schemecode: "+schemecode);

        _fundName = findViewById(R.id.fundName);
        _userName = findViewById(R.id.userName);
        _folioNumber = findViewById(R.id.folioNumber);
        _currentValue = findViewById(R.id.currentValue);
        _totalinvest = findViewById(R.id.totalinvest);
        _gain_loss = findViewById(R.id.gain_loss);
        _absoluteChange  = findViewById(R.id.absoluteChange);
        _dividend_earned = findViewById(R.id.dividend_earned);
        _navAmount = findViewById(R.id.navAmount);
        _totalUnits  = findViewById(R.id.totalUnits);
        _date = findViewById(R.id.date);
        _navDate = findViewById(R.id.navDate);
        _dividend_reinvestment = findViewById(R.id.dividend_reinvestment);
        _rupeeSymbol = findViewById(R.id.rupeeSymbol);


        ServerResultHandler serverResultHandler = new ServerResultHandler();
        serverResultHandler.setContext(FundDetailsActivity.this);
        UserHandler.getInstance().set_ihttpResultHandler(serverResultHandler);
        if (cc.equalsIgnoreCase("p")) {
            UserHandler.getInstance().getFundDetails(SharedPreferenceManager.getClientCode(this), schemecode, folio, this);
        }
        else {
            UserHandler.getInstance().getFundDetails(cc, schemecode, folio, this);

        }
        long currentDate = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date(currentDate);
        String date = simpleDateFormat.format(d);
        _date.setText("All values as on "+date);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                //System.out.println("-back button click");
                onBackPressed();
                return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private class ServerResultHandler implements ihttpResultHandler {
        private Context context;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public void onSuccess(Object message, Object messsage1, Object message2, Object message3, Object message4, Object message5, String operation_flag) {
            FundDetailsModel fundDetailsModel = (FundDetailsModel) message;
            _fundName.setText(fundDetailsModel.getSchemeName());
            _userName.setText(SharedPreferenceManager.getUserName(FundDetailsActivity.this));
            _totalinvest.setText(fundDetailsModel.getTotalInvest());
            _currentValue.setText(fundDetailsModel.getCurrentValue());
            _folioNumber.setText("Folio No.:- "+fundDetailsModel.getFolioNo());
            if (fundDetailsModel.getAbsoluteReturn().contains("-")) {
                _gain_loss.setText(fundDetailsModel.getAbsoluteReturn());
                _gain_loss.setTextColor(Color.parseColor("#ff1111"));
               // _rupeeSymbol.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                _rupeeSymbol.setColorFilter(Color.rgb(255, 17, 17));
            }
            else {
                _gain_loss.setText(fundDetailsModel.getAbsoluteReturn());
            }
            _absoluteChange.setText(fundDetailsModel.getReturn_percent()+"%");
            _totalUnits.setText(fundDetailsModel.getUnitBalance());
            _dividend_reinvestment.setText(fundDetailsModel.getDividendReunvestment());

                String navDetails = fundDetailsModel.getNavDetails();



            try {
                SimpleDateFormat source = new SimpleDateFormat("yyyy-mm-dd");  // British format
                SimpleDateFormat target = new SimpleDateFormat("dd-mm-yyyy");
                String c = navDetails.substring(0,10);
                String newDate = target.format(source.parse(c));
                System.out.println("date test: "+newDate);
                _navDate.setText(newDate);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("date test error:  "+e );
            }
            _navAmount.setText(navDetails.substring(11));

            hud.dismiss();

        }

        @Override
        public void onError(Object message) {
            hud.dismiss();
            Toast.makeText(context, "Error has occurred.Please try again.", Toast.LENGTH_LONG).show();
        }
    }
}
