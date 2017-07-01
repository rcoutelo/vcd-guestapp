package br.com.a3ysoftwarehouse.vcdguest.ui.passenger;

import br.com.a3ysoftwarehouse.vcdguest.app.App;
import br.com.a3ysoftwarehouse.vcdguest.data.model.Passenger;
import br.com.a3ysoftwarehouse.vcdguest.ui.base.BasePresenter;
import br.com.a3ysoftwarehouse.vcdguest.util.Utils;

/**
 * Created by Iago Belo on 23/06/17.
 */

public class PassengerPresenter extends BasePresenter<IPassengerView>
        implements IPassengerPresenter {
    // Constants
    private static final String TAG = "PassengerPresenter";

    // Save TAG
    private boolean registerTag;

    // Passenger
    private Passenger mPassenger;

    public PassengerPresenter(IPassengerView iPassengerView, Passenger passenger) {
        super(iPassengerView);

        this.mPassenger = passenger;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDettach() {

    }

    @Override
    public void onRegisterBraceletBtClick() {
        if (registerTag) {
            registerTag = false;

        } else {
            registerTag = true;

            getView().showToast("Encoste a pulseira.");
        }
    }

    @Override
    public void onTagRead(String tagId) {
        if (registerTag) {
            Utils.beep(App.getContext());

            // Update passenger tag
            getDataManager().updatePassengerTag(mPassenger.getCOD(), tagId);

            registerTag = false;

            getView().showToast("Tag registrada.");

            getView().showProgress(true);

            mPassenger = getDataManager().getPassenger(mPassenger.getCOD());

            getView().showPassengerData(mPassenger);

            getView().showProgress(false);

            getView().finishActivity();
        }
    }
}
