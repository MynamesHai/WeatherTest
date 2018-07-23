package com.tksoft.weather2018.ui.details;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.ui.base.BaseActivity;
import com.tksoft.weather2018.ui.main.MainActivity;

public class DetailsActivity extends BaseActivity implements View.OnClickListener, DetailsMvp {
    private Button btnLogin;
    private Button btnRegister;
    private TextView txtForgot;
    private EditText edtUser;
    private EditText edtPass;
    private DetailsPresenter detailsPresenter;
    private DetailsFragmentRegister mFragment;
    private Context mContext;

    @Override
    public int getLayoutId() {
            return R.layout.activity_details;
    }

    @Override
    public void onViewCreated() {
        mContext = getContext();
        detailsPresenter = new DetailsPresenter(mContext);
        detailsPresenter.attachView(this);
        initViews();
    }

    @Override
    public void setActionForViews() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        txtForgot.setOnClickListener(this);
    }

    public void initViews(){
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnRegister = (Button) findViewById(R.id.btn_register);
        txtForgot = (TextView) findViewById(R.id.txt_forgotpass);
        edtUser = (EditText) findViewById(R.id.edt_user);
        edtPass = (EditText) findViewById(R.id.edt_pass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnlogin:
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                detailsPresenter.receivedHandleLogin(user, pass);
                break;
            case R.id.btn_register:
                mFragment = new DetailsFragmentRegister();
                addFragment(true,R.id.frame_fragment,mFragment);
                Toast.makeText(this, "You chose Register", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txt_forgotpass:
                Toast.makeText(this, "User 'buihai'; Pass '1804'", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, LoginSuccessActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        detailsPresenter.detachView();
        super.onDestroy();
    }
}
