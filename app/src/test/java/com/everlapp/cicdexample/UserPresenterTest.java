package com.everlapp.cicdexample;

import com.everlapp.cicdexample.repositories.NameRepository;
import com.everlapp.cicdexample.rules.RxImmediateSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.subjects.PublishSubject;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
public class UserPresenterTest {

    static final int TIMEOUT_SEC = 2;
    static final String NAME = "Dima";


    @Rule public final MockitoRule rule = MockitoJUnit.rule();

    @Rule public final RxImmediateSchedulerRule timeoutRule = new RxImmediateSchedulerRule();


    @Mock UserPresenter.Listener listener;

    @Mock NameRepository nameRepository;

    @Mock Logger logger;


    PublishSubject<String> nameObservable = PublishSubject.create();

    UserPresenter presenter;

    @Before
    public void setUp() {
        when(nameRepository.getNameRx()).thenReturn(nameObservable.firstOrError());

        presenter = new UserPresenter(listener, nameRepository, logger);
    }


    @Test
    public void getUserName() {
        presenter.getUserName();
        timeoutRule.getTestScheduler().advanceTimeBy(TIMEOUT_SEC - 1, TimeUnit.SECONDS);
        nameObservable.onNext(NAME);
        verify(listener).onUserNameLoaded(NAME);
    }


    @Test
    public void getUserName_timeout() {
        presenter.getUserName();
        timeoutRule.getTestScheduler().advanceTimeBy(TIMEOUT_SEC + 1, TimeUnit.SECONDS);
        nameObservable.onNext(NAME);
        verify(listener).onGettingUserNameError(any());
    }


}