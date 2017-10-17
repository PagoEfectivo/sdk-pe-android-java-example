package pe.elcomercio.pagoefectivosdkjavasample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;
import pe.elcomercio.pagoefectivosdk.cip.CipListener;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest;
import pe.elcomercio.pagoefectivosdk.util.Currency;
import pe.elcomercio.pagoefectivosdk.util.DocumentType;
import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.data.CustomSpinnerAdapter;
import pe.elcomercio.pagoefectivosdkjavasample.data.TypeCurrency;
import pe.elcomercio.pagoefectivosdkjavasample.data.TypeDocument;

public class GenerateCipActivity extends AppCompatActivity implements CipListener {

    private PagoEfectivoSdk instance;

    private EditText txtAmount;
    private EditText txtTransactionCode;
    private EditText txtDateExpiry;
    private EditText txtAdditionalData;
    private EditText txtPaymentConcept;
    private EditText txtUserEmail;
    private EditText txtUserName;
    private EditText txtUserLastName;
    private EditText txtUserUbigeo;
    private EditText txtUserCountry;
    private EditText txtUserDocumentNumber;
    private EditText txtUserPhone;
    private EditText txtUserCodeCountry;
    private EditText txtAdminEmail;

    private CustomSpinnerAdapter customSpinnerAdapter;
    private Currency currentCurrency;
    private DocumentType currentDocument;

    private Toast toastDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_cip);
        init();
    }

    private void init() {
        //Initiate Values for Spinners
        List<Object> itemsCurrency = new ArrayList<>();
        itemsCurrency.add(new TypeCurrency(getResources().getString(R.string.select_one_spinner), Currency.PEN));
        itemsCurrency.add(new TypeCurrency(getResources().getString(R.string.currency_pen), Currency.PEN));
        itemsCurrency.add(new TypeCurrency(getResources().getString(R.string.currency_usd), Currency.USD));

        List<Object> itemsDocumentType = new ArrayList<>();
        itemsDocumentType.add(new TypeDocument(getResources().getString(R.string.select_one_spinner), DocumentType.DNI));
        itemsDocumentType.add(new TypeDocument(getResources().getString(R.string.document_dni), DocumentType.DNI));
        itemsDocumentType.add(new TypeDocument(getResources().getString(R.string.document_pas), DocumentType.PASSPORT));
        itemsDocumentType.add(new TypeDocument(getResources().getString(R.string.document_lmi), DocumentType.LIBRETA_MILITAR));
        itemsDocumentType.add(new TypeDocument(getResources().getString(R.string.document_par), DocumentType.PARTIDA_NACIMIENTO));
        itemsDocumentType.add(new TypeDocument(getResources().getString(R.string.document_nan), DocumentType.OTROS));

        //Get Instance from PagoEfectivo SDK
        instance = PagoEfectivoSdk.getInstance();

        //Init UI
        Spinner spiCurrency = (Spinner) findViewById(R.id.spiCurrency);
        spiCurrency.setAdapter(new CustomSpinnerAdapter(this, itemsCurrency));

        spiCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customSpinnerAdapter = (CustomSpinnerAdapter) parent.getAdapter();
                TypeCurrency currency = (TypeCurrency) customSpinnerAdapter.getItem(position);
                currentCurrency = currency.getCurrency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtTransactionCode = (EditText) findViewById(R.id.txtTransactionCode);
        txtDateExpiry = (EditText) findViewById(R.id.txtDateExpiry);
        txtAdditionalData = (EditText) findViewById(R.id.txtAdditionalData);
        txtPaymentConcept = (EditText) findViewById(R.id.txtPaymentConcept);
        txtUserEmail = (EditText) findViewById(R.id.txtUserEmail);
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtUserLastName = (EditText) findViewById(R.id.txtUserLastName);
        txtUserUbigeo = (EditText) findViewById(R.id.txtUserUbigeo);
        txtUserCountry = (EditText) findViewById(R.id.txtUserCountry);

        Spinner spiUserDocumentType = (Spinner) findViewById(R.id.spiUserDocumentType);
        spiUserDocumentType.setAdapter(new CustomSpinnerAdapter(this, itemsDocumentType));

        spiUserDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customSpinnerAdapter = (CustomSpinnerAdapter) parent.getAdapter();
                TypeDocument document = (TypeDocument) customSpinnerAdapter.getItem(position);
                currentDocument = document.getDocumentType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        txtUserDocumentNumber = (EditText) findViewById(R.id.txtUserDocumentNumber);
        txtUserPhone = (EditText) findViewById(R.id.txtUserPhone);
        txtUserCodeCountry = (EditText) findViewById(R.id.txtUserCodeCountry);
        txtAdminEmail = (EditText) findViewById(R.id.txtAdminEmail);
    }

    public void generateCipSdkOnClick(View view) {
        showMessage(getResources().getString(R.string.generating_cip));
        instance.generateCip(getPrepareRequest(), this);
    }

    private CipRequest getPrepareRequest() {
        //Object necessary for the request
        CipRequest cipRequest = new CipRequest();

        cipRequest.setCurrency(currentCurrency);

        if (!txtAmount.getText().toString().isEmpty()) {
            cipRequest.setAmount(Double.parseDouble(txtAmount.getText().toString()));
        }
        cipRequest.setTransactionCode(txtTransactionCode.getText().toString());
        //cipRequest.setDateExpiry(txtDateExpiry.getText().toString());
        cipRequest.setAdditionalData(txtAdditionalData.getText().toString());
        cipRequest.setPaymentConcept(txtPaymentConcept.getText().toString());
        cipRequest.setUserEmail(txtUserEmail.getText().toString());
        cipRequest.setUserName(txtUserName.getText().toString());
        cipRequest.setUserLastName(txtUserLastName.getText().toString());
        cipRequest.setUserUbigeo(txtUserUbigeo.getText().toString());
        cipRequest.setUserCountry(txtUserCountry.getText().toString());
        cipRequest.setUserDocumentType(currentDocument);
        cipRequest.setUserDocumentNumber(txtUserDocumentNumber.getText().toString());
        cipRequest.setUserPhone(txtUserPhone.getText().toString());
        cipRequest.setUserCodeCountry(txtUserCodeCountry.getText().toString());
        cipRequest.setAdminEmail(txtAdminEmail.getText().toString());

        return cipRequest;
    }

    @Override
    public void OnCipSuccessful(Cip cip) {
        StringBuilder cipResult = new StringBuilder();
        cipResult.append(getString(R.string.cip_generated));
        cipResult.append("\n\n");
        cipResult.append(" - CIP: ").append(cip.getCip()).append("\n");
        cipResult.append(" - AMOUNT: ").append(cip.getAmount()).append("\n");
        cipResult.append(" - CURRENCY: ").append(cip.getCurrency()).append("\n");
        cipResult.append(" - DATEXPIRY: ").append(cip.getDateExpiry()).append("\n");
        cipResult.append(" - TRANSACTIONCODE: ").append(cip.getTransactionCode()).append("\n");

        showMessage(cipResult.toString());
    }

    @Override
    public void OnCipError(List<CipError> list) {

        StringBuilder errorMessage = new StringBuilder();
        for (CipError error : list) {
            errorMessage.append("- (").append(error.getCode()).append(")").append(" | ").append(error.getField()).append(" --> ").append(error.getMessage()).append("\n");
        }

        showMessage(errorMessage.toString());
    }

    @Override
    public void OnCipFailure(String s) {
        showMessage(s);
    }

    private void showMessage(String message) {
        if (toastDialog != null) {
            toastDialog.cancel();
        }
        toastDialog = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toastDialog.show();
    }
}