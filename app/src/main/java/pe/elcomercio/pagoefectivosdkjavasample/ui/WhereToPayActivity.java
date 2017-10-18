package pe.elcomercio.pagoefectivosdkjavasample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip;
import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.data.WhereToPayAdapter;

import static pe.elcomercio.pagoefectivosdkjavasample.util.Constant.PAGO_EFECTIVO_AGENTES;

public class WhereToPayActivity extends AppCompatActivity {

    private Cip cip;
    private int typePayment;
    private List<String> typePaymentMethods = new ArrayList<>();
    private List<Object> listMethodPaymentsAndCip = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to_pay);
        init();
    }

    private void init() {

        //Type Payments
        typePaymentMethods.add(getString(R.string.title_movil));
        typePaymentMethods.add(getString(R.string.title_agents));

        //Get Cip
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.type_method_payment)) && intent.hasExtra(getString(R.string.serialize_cip))) {
            cip = (Cip) getIntent().getSerializableExtra(getString(R.string.serialize_cip));
            typePayment = getIntent().getIntExtra(getString(R.string.type_method_payment), 0);
            prepareData();
        }

        //Set title toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(typePaymentMethods.get(typePayment));
        }

        //Setup Adapter
        WhereToPayAdapter whereToPayAdapter = new WhereToPayAdapter(listMethodPaymentsAndCip, this);
        whereToPayAdapter.setOnItemClickListener(new WhereToPayAdapter.OnItemClickListener() {
            @Override
            public void onItemClickAgentMobil(int position) {
                Toast.makeText(WhereToPayActivity.this, "CIP" + cip.getDateExpiry(), Toast.LENGTH_SHORT).show();
            }
        });

        //Setup Recycler
        RecyclerView rcvWhereToPay = (RecyclerView) findViewById(R.id.rcvWhereToPay);
        rcvWhereToPay.setAdapter(whereToPayAdapter);
        rcvWhereToPay.setHasFixedSize(true);
        rcvWhereToPay.addItemDecoration(new DividerItemDecoration(rcvWhereToPay.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void prepareData() {

        listMethodPaymentsAndCip.add(cip);

        listMethodPaymentsAndCip.add(getString(R.string.agent_bcp));
        listMethodPaymentsAndCip.add(getString(R.string.agent_banbif));
        listMethodPaymentsAndCip.add(getString(R.string.agent_bbva));
        listMethodPaymentsAndCip.add(getString(R.string.agent_scotiabank));
        listMethodPaymentsAndCip.add(getString(R.string.agent_ibk));

        if (typePayment == PAGO_EFECTIVO_AGENTES) {
            listMethodPaymentsAndCip.add(getString(R.string.agent_casnet));
            listMethodPaymentsAndCip.add(getString(R.string.agent_wester));
            listMethodPaymentsAndCip.add(getString(R.string.agent_full_charge));
        }
    }
}
