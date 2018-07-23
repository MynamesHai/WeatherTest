//package com.tksoft.weather2018.data.model.login;
//
//import android.content.Context;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//
//import com.tksoft.weather2018.R;
//import com.tksoft.weather2018.ui.details.DetailsFragmentRegister;
//import com.tksoft.weather2018.ui.details.DetailsMvp;
//
//public class ModelLogin {
//    private Context mContext;
//
//    public ModelLogin(Context context){
//        this.mContext = context;
//    }
//
//    public void handleLogin(String user, String pass){
//        if (user.equals("buihai")&& pass.equals("1804")){
//            .loginSuccess();
//        }else {
//            callback.loginFail();
//        }
//    }
//
//    public void handleRegister(DetailsFragmentRegister mFragment, FragmentManager fragmentManager){
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.frame_fragment, mFragment).addToBackStack("addFragmentReg");
//        fragmentTransaction.commit();
//    }
//}
