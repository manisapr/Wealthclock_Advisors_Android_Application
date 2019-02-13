package android.wealthclockadvisors.app.wealthclockadvisors.iinterface;

import android.content.Context;
import android.wealthclockadvisors.app.wealthclockadvisors.model.Top3Funds;

import java.util.ArrayList;

public interface iisendPurchaseDetails {
    void sendpurchaseDetails(ArrayList<Top3Funds> top3FundsArrayList,ArrayList<Top3Funds> stringArrayList,String amount,String year,Context context);
}
