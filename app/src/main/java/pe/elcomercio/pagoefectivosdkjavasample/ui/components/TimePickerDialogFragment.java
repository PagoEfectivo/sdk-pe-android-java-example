package pe.elcomercio.pagoefectivosdkjavasample.ui.components;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TimePicker;

import pe.elcomercio.pagoefectivosdkjavasample.R;

/**
 * Created by carlosleonardocamilovargashuaman on 10/25/17.
 */

@SuppressWarnings("ALL")
public class TimePickerDialogFragment extends DialogFragment implements View.OnClickListener {

    private TimePickerDialog.OnTimeSetListener mListener;

    private TimePicker timePicker;
    private AppCompatButton btnCancel;
    private AppCompatButton btnOk;

    public static TimePickerDialogFragment newInstance() {
        return new TimePickerDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            this.mListener = (TimePickerDialog.OnTimeSetListener) context;
        }
    }

    @Override
    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_time_picker, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        timePicker = view.findViewById(R.id.timePicker);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnOk = view.findViewById(R.id.btnOk);

        assert btnOk != null;
        assert btnCancel != null;
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        timePicker.setIs24HourView(false);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOk) {
            getDialog().dismiss();
            mListener.onTimeSet(timePicker,
                    timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        } else if (v.getId() == R.id.btnCancel) {
            getDialog().dismiss();
        }
    }
}
