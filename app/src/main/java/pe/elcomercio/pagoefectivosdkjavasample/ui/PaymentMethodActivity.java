package pe.elcomercio.pagoefectivosdkjavasample.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip;
import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.data.PaymentMethodAdapter;
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


        /*StringBuilder cipResult = new StringBuilder();
        cipResult.append(getString(R.string.cip_generated));
        cipResult.append("\n\n");
        cipResult.append(" - CIP: ").append(cip.getCip()).append("\n");
        cipResult.append(" - AMOUNT: ").append(cip.getAmount()).append("\n");
        cipResult.append(" - CURRENCY: ").append(cip.getCurrency()).append("\n");
        cipResult.append(" - DATEXPIRY: ").append(cip.getDateExpiry()).append("\n");
        cipResult.append(" - TRANSACTIONCODE: ").append(cip.getTransactionCode()).append("\n");*/

        //showMessage(cipResult.toString());

    }

    private void whereToPay(int typeItem) {
        switch (typeItem){
            case PAGO_EFECTIVO_MOVIL:
            case PAGO_EFECTIVO_AGENTES:
                Toast.makeText(this, "Go to pay "+typeItem, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, R.string.payment_method_not_available, Toast.LENGTH_SHORT).show();
        }
    }
}
