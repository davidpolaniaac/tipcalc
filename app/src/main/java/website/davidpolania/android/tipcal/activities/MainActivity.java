package website.davidpolania.android.tipcal.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import website.davidpolania.android.tipcal.R;
import website.davidpolania.android.tipcal.TipCalcApp;
import website.davidpolania.android.tipcal.fragments.TipHistoryListFragment;
import website.davidpolania.android.tipcal.fragments.TipHistoryListFragmentListener;
import website.davidpolania.android.tipcal.models.TipRecord;

public class MainActivity extends AppCompatActivity {


    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTGE = 10;
    private TipHistoryListFragmentListener fragmentListener;
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.separador)
    View separador;
    @Bind(R.id.txtTip)
    TextView txtTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);

    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getABOUT_URL();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSumit() {

        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());

            fragmentListener.addTolist(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    private int getTipPercentage() {
        int tipPercetage = DEFAULT_TIP_PERCENTGE;
        String strInputTipPercetage = inputPercentage.getText().toString().trim();
        if (!strInputTipPercetage.isEmpty()) {
            tipPercetage = Integer.parseInt(strInputTipPercetage);
        } else {
            inputPercentage.setText(String.valueOf(DEFAULT_TIP_PERCENTGE));
        }
        return tipPercetage;
    }

    @OnClick({R.id.btnIncrease, R.id.btnDecrease})
    public void onClick(View view) {
        hideKeyboard();
        switch (view.getId()) {
            case R.id.btnIncrease:
                handleTipChange(TIP_STEP_CHANGE);
                break;
            case R.id.btnDecrease:
                handleTipChange(-TIP_STEP_CHANGE);
                break;
        }
    }

    private void handleTipChange(int change) {
        int tipPercetage = getTipPercentage();
        tipPercetage += change;
        if (tipPercetage > 0) {
            inputPercentage.setText(String.valueOf(tipPercetage));
        }
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear() {
        fragmentListener.clearList();
    }
}
