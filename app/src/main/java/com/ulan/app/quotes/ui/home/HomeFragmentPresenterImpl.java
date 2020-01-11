package com.ulan.app.quotes.ui.home;

import android.util.Log;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.ulan.app.quotes.utils.Constants.TAG_OTHER;

public class HomeFragmentPresenterImpl implements HomeFragmentPresenter {

    private HomeFragmentView mView;
    private CompositeDisposable compositeDisposable;

    public HomeFragmentPresenterImpl(HomeFragmentView mView) {
        this.mView = mView;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attachRxData(Observable<List<QuoteModel>> listObservable) {
        compositeDisposable.add(listObservable
                    .retry(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<List<QuoteModel>>() {
                        @Override
                        public void onNext(List<QuoteModel> quoteData) {
                            Log.d(TAG_OTHER, "onNext: Main Fragment " + quoteData.size());
                            mView.showQuotes(quoteData);
                        }

                        @Override
                        public void onError(Throwable error) {
                            Log.d(TAG_OTHER, "onError: Main Fragment " + error.getMessage());
                            mView.showQuotesError(error);
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG_OTHER, "onComplete: ");
                        }
                    }));
    }

    @Override
    public void detachRxData(){
        compositeDisposable.dispose();
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

}
