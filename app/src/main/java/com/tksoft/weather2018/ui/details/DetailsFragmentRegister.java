package com.tksoft.weather2018.ui.details;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tksoft.weather2018.ui.base.BaseFragment;
import com.tksoft.weather2018.R;
import com.tksoft.weather2018.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsFragmentRegister extends BaseFragment implements DetailsMvp {
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.edt_firtN)
    EditText edtFirtName;
    @BindView(R.id.edt_lastN)
    EditText edtLastName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_re_email)
    EditText edtReEmail;
    @BindView(R.id.edt_new_pass)
    EditText edtNewPass;
    private Context mContext;
    private DetailsPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_details_register;
    }

    @Override
    public void onCreateView() {
        mPresenter = new DetailsPresenter(mContext);
        mPresenter.attachView(this);
    }

    @Override
    public void setActionForViews() {

    }

    @OnClick(R.id.btn_sign)
    public void onSignUp(){
        String firtName = edtFirtName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String email = edtEmail.getText().toString();
        String reEmail = edtReEmail.getText().toString();
        String newPass = edtNewPass.getText().toString();
        mPresenter.receivedHandleSignUp(firtName, lastName, email, reEmail, newPass);
    }
    @OnClick(R.id.rl_fragment_reg)
    public void onBackground(){

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(getContext(), LoginSuccessActivity.class);
        startActivity(intent);
        Toast.makeText(mActivity, "Sign Up Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(mActivity, "Sign Up Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }
}
