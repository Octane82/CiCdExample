package com.everlapp.cicdexample;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.everlapp.cicdexample.rules.CreateFileRule;
import com.everlapp.cicdexample.rules.FragmentAsyncTestRule;
import com.everlapp.cicdexample.rules.FragmentTestRule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class UsersFragmentTest {


    @Rule
    public final RuleChain ruleChain = RuleChain
            .outerRule(new CreateFileRule(getTestFile(), "{name: Dima}"))
            .around(new FragmentTestRule<>(MainActivity.class, new UsersFragment()));


    /**
     * Правило необходимо для того чтобы тест упал
     * если асинхронные действия будут обращаться к полям фрагмента после его завершения.
     */
    @ClassRule
    public static TestRule asyncRule =
            new FragmentAsyncTestRule<>(MainActivity.class, new UsersFragment());


   /* @Rule
    public final FragmentTestRule<MainActivity, UsersFragment> fragmentRule =
            new FragmentTestRule<>(
                    MainActivity.class,
                    new UsersFragment(),
                    createTestApplicationComponent()
    );


    private ApplicationComponent createTestApplicationComponent() {
        ApplicationComponent component = mock(ApplicationComponent.class);
        when(component.createUserComponent())
                .thenReturn(DaggerUserFragmentTest_TestUserComponent.create());
        return component;
    }


    @Singleton
    @Component(modules = {TestUserModule.class})
    interface TestUserComponent extends UserComponent {}


    @Module
    static class TestUserModule {
        @Provides
        public NameRepository provideNameRepository() {
            NameRepository nameRepository = mock(NameRepository.class);
            when(nameRepository.getNameRx()).thenReturn(
                    Single.fromCallable(() -> "Dima"));
            return nameRepository;
        }
    }
*/

    // --------------- OLD ----------------------------------




    @Test
    public void nameDisplayedTest() {
        // onView(withText("Dima")).check(matches(isDisplayed()));

        // Rx test
        await()
            .atMost(5, TimeUnit.SECONDS)
            .ignoreExceptions()
            .untilAsserted(()
                    -> onView(withText("Dima"))
                    .check(matches(isDisplayed())));
    }



    private File getTestFile() {
        return new File(InstrumentationRegistry.getTargetContext()
                .getFilesDir().getAbsoluteFile()
                + File.separator
                + "test_file");
    }


   // ********************************************

    // TODO: 07.09.18 Test with Dagger 2


   /* @ClassRule
    public static TestRule asyncRule =
            new FragmentAsyncTestRule<>(MainActivity.class, new UsersFragment());

    @Rule
    public final FragmentTestRule<MainActivity, UsersFragment> fragmentRule =
            new FragmentTestRule<>(
                    MainActivity.class, new UsersFragment(), createTestApplicationComponent());

    @Test
    public void getNameMethodCalledOnCreate() {
        //verify(fragmentRule.getFragment().userPresenter).getUserName();
    }

    private ApplicationComponent createTestApplicationComponent() {
        ApplicationComponent component = mock(ApplicationComponent.class);
        when(component.createUserComponent(any(UserModule.class)))
                .thenReturn(DaggerUsersFragmentTest_TestUserComponent.create());
        return component;
    }

    @Singleton
    @Component(modules = {TestUserModule.class})
    interface TestUserComponent extends UserComponent {}

    @Module
    static class TestUserModule {
        @Provides
        public UserPresenter provideUserPresenter() {
            return mock(UserPresenter.class);
        }
    }*/

}