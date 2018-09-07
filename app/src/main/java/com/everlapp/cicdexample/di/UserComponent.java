package com.everlapp.cicdexample.di;

import com.everlapp.cicdexample.UsersFragment;

import dagger.Subcomponent;


@Subcomponent(modules = {UserModule.class})
public interface UserComponent {

    void injectsUserFragment(UsersFragment userFragment);
}
