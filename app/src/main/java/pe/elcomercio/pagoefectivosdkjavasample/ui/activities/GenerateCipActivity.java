package pe.elcomercio.pagoefectivosdkjavasample.ui.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;
import pe.elcomercio.pagoefectivosdk.cip.CipListener;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.Cip;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipRequest;
import pe.elcomercio.pagoefectivosdk.util.Currency;
import pe.elcomercio.pagoefectivosdk.util.DocumentType;
import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.ui.components.DatePickerDialogFragment;
import pe.elcomercio.pagoefectivosdkjavasample.ui.components.TimePickerDialogFragment;
import pe.elcomercio.pagoefectivosdkjavasample.util.Utils;

public class GenerateCipActivity extends AppCompatActivity implements CipListener,
        DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    private PagoEfectivoSdk instance;

    private Spinner spiCurrency;
    private Spinner spiUserDocumentType;
    private EditText txtAmount;
    private EditText txtTransactionCode;
    private TextView lblDateExpiry;
    private TextView lblTimeExpiry;
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

    private Toast toastDialog;

    final Calendar calendar = Calendar.getInstance();

    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH);
    private int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    private int hourOfDay = calendar.get(Calendar.HOUR);
    private int minute = calendar.get(Calendar.MINUTE);

    private List<String> currencyNameList = new ArrayList<>();
    private List<String> documentTypeNameList = new ArrayList<>();

    private List<Currency> currencyValueList = new ArrayList<>();
    private List<DocumentType> documentTypeValueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_cip);
        init();
    }

    private void init() {
        initCurrencyValues();
        initCurrencyNames();
        initDocumentTypeValues();
        initDocumentTypeNames();

        //Get Instance from PagoEfectivo SDK
        instance = PagoEfectivoSdk.getInstance();

        //Setting Up Views
        spiCurrency = (Spinner) findViewById(R.id.spiCurrency);
        spiUserDocumentType = (Spinner) findViewById(R.id.spiUserDocumentType);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtTransactionCode = (EditText) findViewById(R.id.txtTransactionCode);
        lblDateExpiry = (TextView) findViewById(R.id.lblDateExpiry);
        lblTimeExpiry = (TextView) findViewById(R.id.lblTimeExpiry);
        txtAdditionalData = (EditText) findViewById(R.id.txtAdditionalData);
        txtPaymentConcept = (EditText) findViewById(R.id.txtPaymentConcept);
        txtUserEmail = (EditText) findViewById(R.id.txtUserEmail);
        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtUserLastName = (EditText) findViewById(R.id.txtUserLastName);
        txtUserUbigeo = (EditText) findViewById(R.id.txtUserUbigeo);
        txtUserCountry = (EditText) findViewById(R.id.txtUserCountry);
        txtUserDocumentNumber = (EditText) findViewById(R.id.txtUserDocumentNumber);
        txtUserPhone = (EditText) findViewById(R.id.txtUserPhone);
        txtUserCodeCountry = (EditText) findViewById(R.id.txtUserCodeCountry);
        txtAdminEmail = (EditText) findViewById(R.id.txtAdminEmail);

        //Setting Up currency adapter
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencyNameList);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiCurrency.setAdapter(currencyAdapter);

        //Setting Up documentType adapter
        ArrayAdapter<String> documentTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, documentTypeNameList);
        documentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiUserDocumentType.setAdapter(documentTypeAdapter);

        setCurrentDate(this.year, this.month, this.dayOfMonth);
        setCurrentTime(this.hourOfDay, this.minute);
    }

    private void initCurrencyValues() {
        currencyValueList.add(Currency.PEN);
        currencyValueList.add(Currency.USD);
    }

    private void initCurrencyNames() {
        currencyNameList.add(getString(R.string.currency_pen));
        currencyNameList.add(getString(R.string.currency_usd));
    }

    private void initDocumentTypeValues() {
        documentTypeValueList.add(DocumentType.DNI);
        documentTypeValueList.add(DocumentType.LIBRETA_MILITAR);
        documentTypeValueList.add(DocumentType.OTROS);
        documentTypeValueList.add(DocumentType.PARTIDA_NACIMIENTO);
        documentTypeValueList.add(DocumentType.PASSPORT);
    }

    private void initDocumentTypeNames() {
        documentTypeNameList.add(getString(R.string.document_dni));
        documentTypeNameList.add(getString(R.string.document_lmi));
        documentTypeNameList.add(getString(R.string.document_nan));
        documentTypeNameList.add(getString(R.string.document_par));
        documentTypeNameList.add(getString(R.string.document_pas));
    }

    private void setCurrentDate(int year, int month, int dayOfMonth) {
        lblDateExpiry.setText(getString(R.string.dateformat_with_values,
                Utils.addZeroToNumber(String.valueOf(year)),
                Utils.addZeroToNumber(String.valueOf(month + 1)),
                String.valueOf(dayOfMonth)));
    }

    private void setCurrentTime(int hourOfDay, int minute) {
        String pm_am;
        if (hourOfDay <= 12) {
            pm_am = "AM";
        } else {
            pm_am = "PM";
        }

        lblTimeExpiry.setText(getString(R.string.timeformat_with_values,
                Utils.addZeroToNumber(String.valueOf(hourOfDay)),
                Utils.addZeroToNumber(String.valueOf(minute)),
                pm_am));
    }

    public void generateCipSdkOnClick(View view) {
        showMessage(getResources().getString(R.string.generating_cip));
        instance.generateCip(getCipRequestObject(), this);
    }

    private CipRequest getCipRequestObject() {
        //Object necessary for the request
        CipRequest cipRequest = new CipRequest();
        cipRequest.setCurrency(currencyValueList.get(spiCurrency.getSelectedItemPosition()));
        if (!txtAmount.getText().toString().isEmpty()) {
            cipRequest.setAmount(Double.parseDouble(txtAmount.getText().toString()));
        }
        cipRequest.setTransactionCode(txtTransactionCode.getText().toString());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, this.year);
        calendar.set(Calendar.MONTH, this.month);
        calendar.set(Calendar.DATE, this.dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, this.hourOfDay);
        calendar.set(Calendar.MINUTE, this.minute);
        calendar.set(Calendar.SECOND, 0);
        cipRequest.setDateExpiry(calendar.getTime());
        cipRequest.setAdditionalData(txtAdditionalData.getText().toString());
        cipRequest.setPaymentConcept(txtPaymentConcept.getText().toString());
        cipRequest.setUserEmail(txtUserEmail.getText().toString());
        cipRequest.setUserName(txtUserName.getText().toString());
        cipRequest.setUserLastName(txtUserLastName.getText().toString());
        cipRequest.setUserUbigeo(txtUserUbigeo.getText().toString());
        cipRequest.setUserCountry(txtUserCountry.getText().toString());
        cipRequest.setUserDocumentType(documentTypeValueList.get(spiUserDocumentType.getSelectedItemPosition()));
        cipRequest.setUserDocumentNumber(txtUserDocumentNumber.getText().toString());
        cipRequest.setUserPhone(txtUserPhone.getText().toString());
        cipRequest.setUserCodeCountry(txtUserCodeCountry.getText().toString());
        if (!txtAdminEmail.getText().toString().isEmpty()) {
            cipRequest.setAdminEmail(txtAdminEmail.getText().toString());
        }
        return cipRequest;
    }

    @Override
    public void OnCipSuccessful(Cip cip) {

        Intent intent = new Intent(this, PaymentMethodActivity.class);
        intent.putExtra(getString(R.string.serialize_cip), cip);
        startActivity(intent);
        if (toastDialog != null) {
            toastDialog.cancel();
        }

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

    public void showDatePickerDialog(View view) {
        DialogFragment datePickerDialogFragment = DatePickerDialogFragment.newInstance();
        datePickerDialogFragment.show(getSupportFragmentManager(), "date_picker_dialog_fragment");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment timePickerDialogFragment = TimePickerDialogFragment.newInstance();
        timePickerDialogFragment.show(getSupportFragmentManager(), "time_picker_dialog_fragment");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        setCurrentDate(this.year, this.month, this.dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        setCurrentTime(this.hourOfDay, this.minute);
    }
}