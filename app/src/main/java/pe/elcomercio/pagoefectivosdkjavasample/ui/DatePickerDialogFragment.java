package pe.elcomercio.pagoefectivosdkjavasample.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import pe.elcomercio.pagoefectivosdkjavasample.R;

public class DatePickerDialogFragment extends DialogFragment implements View.OnClickListener{

    private DatePickerDialog.OnDateSetListener mListener;

    private DatePicker datePicker;
    AppCompatButton btnCancel;
    AppCompatButton btnOk;

    public static DatePickerDialogFragment newInstance(){
        return new DatePickerDialogFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mListener = (OnDateSetListener) activity;
    }

    @Override
    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_date_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        datePicker = view.findViewById(R.id.datePicker);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnOk = view.findViewById(R.id.btnOk);

        assert btnOk != null;
        assert btnCancel != null;
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnOk){
            getDialog().dismiss();
            mListener.onDateSet(datePicker,
                    datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        }else if(v.getId() == R.id.btnCancel){
            getDialog().dismiss();
        }
    }
}