package pe.elcomercio.pagoefectivosdkjavasample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;
import pe.elcomercio.pagoefectivosdk.cip.SearchListener;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch;
import pe.elcomercio.pagoefectivosdkjavasample.R;

public class SearchCipActivity extends AppCompatActivity implements SearchListener {

    private PagoEfectivoSdk pagoEfectivoSdk;

    private Toast toastDialog;
    private LinearLayout lnlCip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cip);

        init();
    }

    private void init() {
        //init UI
        lnlCip = (LinearLayout) findViewById(R.id.lnlCip);
        //Get Instance from PagoEfectivo SDK
        pagoEfectivoSdk = PagoEfectivoSdk.getInstance();
    }

    private void addNewChildViews() {
        int MAX_NUMBER_OF_VIEWS = 5;
        if (lnlCip.getChildCount() < MAX_NUMBER_OF_VIEWS) {
            TextInputLayout textInputLayout = new TextInputLayout(this);
            EditText editText = new EditText(this);
            editText.setHint(getString(R.string.new_cip));
            editText.setTag(lnlCip.getChildCount());
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            textInputLayout.addView(editText);
            lnlCip.addView(textInputLayout);
        }
    }

    private List<Integer> getEditTextValues() {
        List<Integer> cipList = new ArrayList<>();
        for (int i = 0; i < lnlCip.getChildCount(); i++) {
            TextInputLayout textInputLayout = (TextInputLayout) lnlCip.getChildAt(i);

            EditText editText = textInputLayout.findViewWithTag(i);
            if (editText.getText().toString().isEmpty()) {
                cipList.add(0);
            } else {
                cipList.add(Integer.parseInt(editText.getText().toString()));
            }
        }
        return cipList;
    }

    public void searchCipSdkOnClick(View view) {
        showMessage(getString(R.string.searching_cip));
        pagoEfectivoSdk.searchCip(getEditTextValues(), this);
    }

    public void addCipRow(View view) {
        addNewChildViews();
    }

    @Override
    public void OnSearchSuccessful(List<CipSearch> list) {
        Intent intent = new Intent(this, ResultSearchCipActivity.class);
        intent.putExtra(getString(R.string.result_cip_list), (ArrayList<CipSearch>) list);
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
}
