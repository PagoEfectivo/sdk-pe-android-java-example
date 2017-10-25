package pe.elcomercio.pagoefectivosdkjavasample.ui.activities;

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
import pe.elcomercio.pagoefectivosdkjavasample.adapter.PaymentMethodAdapter;
import pe.elcomercio.pagoefectivosdkjavasample.model.PaymentMethodEntity;

import static pe.elcomercio.pagoefectivosdkjavasample.util.Constant.PAGO_EFECTIVO_AGENTES;
import static pe.elcomercio.pagoefectivosdkjavasample.util.Constant.PAGO_EFECTIVO_MOVIL;
import static pe.elcomercio.pagoefectivosdkjavasample.util.Constant.PAGO_OTROS;

public class PaymentMethodActivity extends AppCompatActivity {

    private List<PaymentMethodEntity> paymentMethodList = new ArrayList<>();
    private Cip cip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        init();
    }

    private void init() {
        //Get Cip
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.serialize_cip))) {
            cip = (Cip) getIntent().getSerializableExtra(getString(R.string.serialize_cip));
        }

        //Init Adapter
        paymentMethodList.add(new PaymentMethodEntity(PAGO_EFECTIVO_MOVIL, getString(R.string.pe_mobile_internet)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_EFECTIVO_AGENTES, getString(R.string.pe_agents_agency)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_visa)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_master_card)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_american_express)));
        paymentMethodList.add(new PaymentMethodEntity(PAGO_OTROS, getString(R.string.pe_dinners_club)));

        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(paymentMethodList);
        paymentMethodAdapter.setOnItemClickListener(new PaymentMethodAdapter.OnItemClickListener() {
            @Override
            public void onItemClickMethodPayment(int typeItem) {
                whereToPay(typeItem);
            }
        });

        //Setup Recycler
        RecyclerView rcvPaymentMethods = (RecyclerView) findViewById(R.id.rcvPaymentMethods);
        rcvPaymentMethods.setAdapter(paymentMethodAdapter);
        rcvPaymentMethods.setHasFixedSize(true);
        rcvPaymentMethods.addItemDecoration(new DividerItemDecoration(rcvPaymentMethods.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void whereToPay(int typeItem) {
        switch (typeItem) {
            case PAGO_EFECTIVO_MOVIL:
            case PAGO_EFECTIVO_AGENTES:
                Intent intent = new Intent(this, WhereToPayActivity.class);
                intent.putExtra(getString(R.string.serialize_cip), cip);
                intent.putExtra(getString(R.string.type_method_payment), typeItem);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, R.string.payment_method_not_available, Toast.LENGTH_SHORT).show();
        }
    }
}
