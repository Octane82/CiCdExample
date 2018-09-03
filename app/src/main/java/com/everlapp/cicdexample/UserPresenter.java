package com.everlapp.cicdexample;

import com.everlapp.cicdexample.repositories.NameRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter {

    public interface Listener {
        void onUserNameLoaded(String message);

        void onGettingUserNameError(String message);
    }

    private Disposable disposable;

    private final Listener listener;
    private final NameRepository nameRepository;

    public UserPresenter(Listener listener, NameRepository nameRepository) {
        this.listener = listener;
        this.nameRepository = nameRepository;
    }


    public void getUserName() {
        disposable = nameRepository.getNameRx()
                .timeout(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onUserNameLoaded,
                        error -> listener.onGettingUserNameError(error.getMessage()));
    }


}
