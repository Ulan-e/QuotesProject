package com.ulan.app.quotes.di.components;

import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.di.modules.uimodules.MainModule;
import com.ulan.app.quotes.di.qualifires.filters.FilterFunny;
import com.ulan.app.quotes.di.qualifires.filters.FilterHappy;
import com.ulan.app.quotes.di.qualifires.filters.FilterLive;
import com.ulan.app.quotes.di.qualifires.filters.FilterLove;
import com.ulan.app.quotes.di.qualifires.filters.FilterMotif;
import com.ulan.app.quotes.di.qualifires.filters.FilterRandom;
import com.ulan.app.quotes.di.scopes.MainScope;
import com.ulan.app.quotes.ui.mainactivity.MainActivity;

import java.util.List;

import dagger.Subcomponent;
import io.reactivex.Observable;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainActivityComponent {

    @FilterRandom
    Observable<List<QuoteModel>> getObservableList();

    @FilterMotif
    Observable<List<QuoteModel>> getObservableListMotif();

    @FilterLive
    Observable<List<QuoteModel>> getObservableListLive();

    @FilterLove
    Observable<List<QuoteModel>> getObservableListLove();

    @FilterHappy
    Observable<List<QuoteModel>> getObservableListHappy();

    @FilterFunny
    Observable<List<QuoteModel>> getObservableListFunny();

    void inject(MainActivity activity);

    @Subcomponent.Builder
    interface ActivityBuilder{
        ActivityBuilder mainModule(MainModule mainModulee);
        MainActivityComponent build();
    }
}
