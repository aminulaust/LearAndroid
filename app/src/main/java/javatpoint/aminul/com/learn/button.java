package javatpoint.aminul.com.learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;
import android.content.Context.*;
public class button extends Fragment {

    private EditText etNormalText;
    private EditText etEmailAddrss;
    private EditText etPhoneNumber;
    private Button btnSubmit;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView= inflater.inflate(R.layout.button,container, false);

        registerViews();
        // date 04 Janulary 2017
       /*
        final EditText edittext1=(EditText)rootView.findViewById(R.id.editText1);
        final  EditText edittext2=(EditText)rootView.findViewById(R.id.editText2);
        Button sendBtn = (Button)rootView.findViewById(R.id.button1);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String value1=edittext1.getText().toString();
                String value2=edittext2.getText().toString();
                int a=Integer.parseInt(value1);
                int b=Integer.parseInt(value2);
                int sum=a+b;
                Toast.makeText(getActivity().getApplicationContext(),String.valueOf(sum),Toast.LENGTH_LONG).show();
                //Toast.makeText(getActivity().getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();

            }
        });
        */

        return rootView;
    }

    private void registerViews() {
        etNormalText = (EditText)rootView.findViewById(R.id.et_normal_text);
        // TextWatcher would let us check validation error on the fly
        etNormalText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(etNormalText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etEmailAddrss = (EditText)rootView.findViewById(R.id.et_email_address);
        etEmailAddrss.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(etEmailAddrss, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etPhoneNumber = (EditText)rootView.findViewById(R.id.et_phone_number);
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(etPhoneNumber, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        btnSubmit = (Button)rootView.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            // To hide softkey when click button
            @Override
            public void onClick(View view) {
                try  {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {

                }
                // Check from validation
                if ( checkValidation () )
                    submitForm();
                else
                    Toast.makeText(getActivity().getApplicationContext(), "Form contains error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
        // get value from eidt text
        String name = etNormalText.getText().toString();
        String email = etEmailAddrss.getText().toString();
        String phone = etPhoneNumber.getText().toString();
        // Show EditText Value in Toast message
        Toast.makeText(getActivity().getApplicationContext(), "Submitting form...", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity().getApplicationContext(), "Name: " + name + " Email: " + email + " Phone: " + phone, Toast.LENGTH_LONG).show();
      // Clean edit text
        etNormalText.getText().clear();
        etEmailAddrss.getText().clear();
        etPhoneNumber.getText().clear();
    }


    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(etNormalText)) ret = false;
        if (!Validation.isEmailAddress(etEmailAddrss, true)) ret = false;
        if (!Validation.isPhoneNumber(etPhoneNumber, false)) ret = false;

        return ret;
    }


// Top manu title show
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Button");
    }
}
