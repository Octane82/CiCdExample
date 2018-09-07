package com.everlapp.cicdexample.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class})
public interface ApplicationComponent {

    UserComponent createUserComponent(UserModule userModule);
}
