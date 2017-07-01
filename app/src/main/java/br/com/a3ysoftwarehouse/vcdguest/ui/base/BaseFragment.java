package br.com.a3ysoftwarehouse.vcdguest.ui.base;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import be.appfoundry.nfclibrary.activities.NfcActivity;

/**
 * Created by Iago Belo on 07/06/17.
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {
    // Constants
    private static final String TAG = "BaseFragment";

    // Activity
    private BaseNfcActivity mActivity;

    // Presenter
    private T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof NfcActivity) {
            Log.i(TAG, "onAttach()");

            mActivity = (BaseNfcActivity) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mPresenter.onDettach();
    }

    @Override
    public void showToast(String msg) {
        if (baseActivityExists()) mActivity.showToast(msg);
    }

    @Override
    public void showToast(String msg, int time) {
        if (baseActivityExists()) mActivity.showToast(msg, time);
    }

    @Override
    public void showProgress(boolean show) {
        if (baseActivityExists()) mActivity.showProgress(show);
    }

    @Override
    public void showSnackBar(String msg) {
        if (baseActivityExists()) mActivity.showSnackBar(msg);
    }

    private boolean baseActivityExists() {
        return mActivity != null;
    }

    protected T getPresenter() {
        return mPresenter;
    }

    protected void setPresenter(T t) {
        mPresenter = t;
    }
}
