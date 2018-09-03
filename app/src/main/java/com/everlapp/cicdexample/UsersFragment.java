package com.everlapp.cicdexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.everlapp.cicdexample.repositories.FileReader;
import com.everlapp.cicdexample.repositories.NameRepository;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsersFragment extends Fragment {

    private TextView textView;
    private Disposable disposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        textView = new TextView(getActivity());

        /*try {
            textView.setText(createNameRepository().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textView;*/

        disposable =
                createNameRepository()
                .getNameRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(name -> textView.setText(name));
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
        disposable.dispose();
        textView = null;
    }
}
