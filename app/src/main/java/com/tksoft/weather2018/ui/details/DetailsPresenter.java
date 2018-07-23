package com.tksoft.weather2018.ui.details;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tksoft.weather2018.R;
import com.tksoft.weather2018.ui.base.BasePresenter;

public class DetailsPresenter extends BasePresenter<DetailsMvp> {
    private Context mContext;

    public DetailsPresenter (Context context){
        this.mContext = context;
    }

    public void receivedHandleLogin(String user, String pass){
        if (user.equals("buihai")&& pass.equals("1804")){

            getMvpView().loginSuccess();
        }else {
            getMvpView().loginFail();
        }
    }

//    public void receivedHandleRegister(DetailsFragmentRegister mFragment,FragmentManager fragmentManager){
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frame_fragment, mFragment).addToBackStack("addFragmentReg");
//        fragmentTransaction.commit();
//    }

    public void receivedHandleSignUp(String firtName, String lastName, String email,String reEmail, String newPass){
        if (firtName.equals("")|| lastName.equals("")|| email.equals("")|| reEmail.equals("")|| newPass.equals("")){
            getMvpView().loginFail();
        }else if (email.equals(reEmail)){
            getMvpView().loginSuccess();
        } else {
            getMvpView().loginFail();
        }
    }
}
