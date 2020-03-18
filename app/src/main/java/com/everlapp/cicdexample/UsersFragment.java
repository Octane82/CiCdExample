package com.everlapp.cicdexample;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.everlapp.cicdexample.di.UserModule;
import com.everlapp.cicdexample.repositories.FileReader;
import com.everlapp.cicdexample.repositories.NameRepository;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class UsersFragment extends Fragment implements UserPresenter.Listener {

    private TextView textView;
    private Disposable disposable;

    //@Inject NameRepository nameRepository;

    @Inject UserPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        textView = new TextView(getActivity());

        try {
            textView.setText(createNameRepository().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return textView;

        ((App) getActivity().getApplication())
                .getComponent()
                .createUserComponent(new UserModule(this))
                .injectsUserFragment(this);

        // textView = new TextView(getActivity());

        /*disposable = nameRepository
                .getNameRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(name -> textView.setText(name), Throwable::printStackTrace);*/

        // presenter.getUserName();

        return textView;
    }


    private NameRepository createNameRepository() {
        return new NameRepository(
                new FileReader(
                        new File(getContext().getFilesDir().getAbsoluteFile()
                                + File.separator
                                + "test_file")));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
            // disposable.dispose();
        textView = null;
    }

    @Override
    public void onUserNameLoaded(String message) {
        textView.setText(message);
    }

    @Override
    public void onGettingUserNameError(String message) {
        textView.setText(message);
    }
}
