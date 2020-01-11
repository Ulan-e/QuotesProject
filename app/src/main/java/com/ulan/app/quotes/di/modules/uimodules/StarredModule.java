package com.ulan.app.quotes.di.modules.uimodules;

import com.ulan.app.quotes.data.QuoteModel;
import com.ulan.app.quotes.data.database.DaoStarredQuotes;
import com.ulan.app.quotes.di.qualifires.LikedQuotes;
import com.ulan.app.quotes.di.scopes.StarredScope;
import com.ulan.app.quotes.ui.starred.StarredFragmentView;
import com.ulan.app.quotes.ui.starred.StarredPresenterImpl;

import java.util.List;
import java.util.concurrent.Callable;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

@StarredScope
@Module
public class StarredModule {

    public StarredFragmentView view;

    public StarredModule(StarredFragmentView view) {
        this.view = view;
    }

    @StarredScope
    @Provides
    public StarredFragmentView provideView(){
        return view;
    }

    @StarredScope
    @LikedQuotes
    @Provides
    public Observable<List<QuoteModel>> listObservable(DaoStarredQuotes daoQuotes){
            return Observable.fromCallable(new Callable<List<QuoteModel>>() {
                @Override
                public List<QuoteModel> call() throws Exception {
                    return daoQuotes.getLikedQuotes();
                }
            });
    }

    @StarredScope
    @Provides
    public StarredPresenterImpl likedPresenter(StarredFragmentView view, @LikedQuotes Observable<List<QuoteModel>> listObservable){
        return new StarredPresenterImpl(view, listObservable);
    }


}
