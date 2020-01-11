package com.ulan.app.quotes.ui.home;

import com.ulan.app.quotes.data.QuoteModel;

import java.util.List;

public interface HomeFragmentView {

    void showQuotes(List<QuoteModel> listQuotes);
    void showQuotesError(Throwable e);

}
