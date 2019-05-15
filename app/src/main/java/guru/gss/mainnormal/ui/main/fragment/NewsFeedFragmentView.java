package guru.gss.mainnormal.ui.main.fragment;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import guru.gss.mainnormal.utils.model.NewsModel;

public interface NewsFeedFragmentView extends MvpView {

    void setListNews(ArrayList<NewsModel> list);
    void setEmptyList();
    void setError();
}
