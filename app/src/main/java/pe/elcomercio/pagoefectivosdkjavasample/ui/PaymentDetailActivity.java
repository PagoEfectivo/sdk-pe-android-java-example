package pe.elcomercio.pagoefectivosdkjavasample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip;
import pe.elcomercio.pagoefectivosdkjavasample.R;

public class PaymentDetailActivity extends AppCompatActivity {

    private Cip cip;
    private String agentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        init();
    }

    private void init() {

        //Get Intent Data
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.agent_payment)) && intent.hasExtra(getString(R.string.serialize_cip))) {
            cip = (Cip) getIntent().getSerializableExtra(getString(R.string.serialize_cip));
            agentName = getIntent().getStringExtra(getString(R.string.agent_payment));
        }

        //Init UI
        TextView lblTitleSummaryDetail = (TextView) findViewById(R.id.lblTitleSummary);
        TextView lblNearAgent = (TextView) findViewById(R.id.lblNearAgent);
        TextView lblCipCode = (TextView) findViewById(R.id.lblCipCode);

        String titleSummary = String.format(getString(R.string.title_summary_payment), String.valueOf(cip.getAmount()), agentName);
        String stepOneSummary = String.format(getString(R.string.step_one_for_payment), agentName);
        String stepThreeSummary = String.format(getString(R.string.step_three_for_payment), String.valueOf(cip.getCip()));

        lblTitleSummaryDetail.setText(titleSummary);
        lblNearAgent.setText(stepOneSummary);
        lblCipCode.setText(stepThreeSummary);
    }
}