package net.sistransito.mobile.number;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import net.sistransito.R;
import net.sistransito.mobile.appconstants.AppConstants;
import net.sistransito.mobile.appobject.AppObject;
import net.sistransito.mobile.http.WebClient;

public class NumberHttpResultAsyncTask extends
        AsyncTask<String, Integer, String> {

    private ProgressDialog pDialog;
    private String jsonText = null;
    private String url;
    private AsyncListernerNumber listener;
    private Context context;
    private int type;
    private boolean isSuccess;

    public NumberHttpResultAsyncTask(final AsyncListernerNumber listener,
                                     Context context, int type) {
        this.type = type;
        switch (this.type) {
            case AppConstants.AIT_NUMBER:
                url = WebClient.URL_AIT_NUMBER;
                break;
            case AppConstants.TAV_NUMBER:
                url = WebClient.URL_TAV_NUMBER;
                break;
            case AppConstants.TCA_NUMBER:
                url = WebClient.URL_TCA_NUMBER;
                break;
            case AppConstants.RRD_NUMBER:
                url = WebClient.URL_RRD_NUMBER;
                break;
        }

        this.context = context;
        this.listener = listener;
        pDialog = null;
        pDialog = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        pDialog.setMax(100);
        pDialog.setCancelable(false);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        listener.asyncTaskComplete(false);
                        cancel(true);

                    }
                });
        pDialog.setMessage(context.getString(R.string.loading_number));

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        try {
            jsonText = AppObject.getHttpClient().executeHttpGet(url);
            CreateNumberDataFromJson numberDataFromJson = new CreateNumberDataFromJson(
                    type, context, jsonText);
            isSuccess = numberDataFromJson.saveDataNumber();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        if ((pDialog != null) && (pDialog.isShowing())) {
            pDialog.dismiss();
        }
        listener.asyncTaskComplete(isSuccess);
        super.onPostExecute(result);
    }
}