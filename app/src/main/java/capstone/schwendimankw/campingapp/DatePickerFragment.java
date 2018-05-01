package capstone.schwendimankw.campingapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class DatePickerFragment extends DialogFragment {
    private static final String TAG = "DialogFragment";

    public static final String EXTRA_DATE =
            "capstone.schwendimankw.campingapp.date";
    public static final String EXTRA_TITLE =
            "capstone.schwendimankw.campingapp.title";
    public static final String EXTRA_ID =
            "capstone.schwendimankw.campingapp.id";

    private Date mDate;
    private String mTitle;
    private UUID mId;

    private EditText mTitleField;

    public static DatePickerFragment newInstance(Date date, String title, UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);
        args.putSerializable(EXTRA_TITLE, title);
        args.putSerializable(EXTRA_ID, id);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Log.d(TAG, "sendResult() called");

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);
        i.putExtra(EXTRA_TITLE, mTitle);
        i.putExtra(EXTRA_ID, mId);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
        mTitle = (String)getArguments().getSerializable(EXTRA_TITLE);
        mId = (UUID)getArguments().getSerializable(EXTRA_ID);

        // Create calendar to get the year, month, day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final View view = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker)view.findViewById(R.id.dialog_date_picker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                // Translate year, month, day into a Date object using a calendar
                mDate = new GregorianCalendar(i, i1, i2).getTime();

                // Update argument to preserve selected value on rotation
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }
        });

        mTitleField = (EditText)view.findViewById(R.id.date_picker_checklist_titleEditText);
        mTitleField.setText(mTitle);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTitle = charSequence.toString();
                getArguments().putSerializable(EXTRA_TITLE, mTitle);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.new_check)
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /*mTitleField = (EditText)view.findViewById(R.id.date_picker_checklist_titleEditText);
                                mTitleField.setText(mTitle);
                                mTitleField.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                        mTitle = charSequence.toString();
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }
                                });*/
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }
}
