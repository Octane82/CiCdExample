package com.everlapp.cicdexample.di;

import android.content.Context;

import com.everlapp.cicdexample.UserPresenter;
import com.everlapp.cicdexample.repositories.FileReader;
import com.everlapp.cicdexample.repositories.NameRepository;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.logging.Logger;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private final UserPresenter.Listener listener;

    public UserModule(UserPresenter.Listener listener) {
        this.listener = listener;
    }

    @Provides
    UserPresenter provideUserPresenter(
            @Private NameRepository nameRepository, @Private Logger logger) {
        return new UserPresenter(listener, nameRepository, logger);
    }

    @Private
    @Provides
    NameRepository provideNameRepository(@Private FileReader fileReader) {
        return new NameRepository(fileReader);
    }

    @Private
    @Provides
    FileReader provideFileReader(@Private File file) {
        return new FileReader(file);
    }

    @Private
    @Provides
    File provideFile(Context context) {
        return new File(context.getFilesDir().getAbsoluteFile() + File.separator + "test_file");
    }

    @Private
    @Provides
    Logger provideLogger() {
        return Logger.getLogger("UserPresenter");
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface Private {}
}