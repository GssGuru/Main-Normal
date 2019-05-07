package guru.gss.mainnormal.aplication.main.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import guru.gss.mainnormal.model.interactors.news.NewsInteractor;
import guru.gss.mainnormal.model.interactors.news.interfaces.OnFinishedListener;
import guru.gss.mainnormal.utils.model.NewsModel;

@InjectViewState
public class PresenterFragment extends MvpPresenter<ViewFragment> {

    private NewsInteractor newsInteractor;
    private ArrayList<NewsModel> list;

    PresenterFragment(NewsInteractor newsInteractor) {
        this.newsInteractor = newsInteractor;
    }

    public void getNewsList(String author) {
        if (list != null){
            getViewState().setListNews(list);
        } else {
            newsInteractor.getList(author, new OnFinishedListener() {
                @Override
                public void onFailedGetList(String message) {
                    getViewState().setError();
                }

                @Override
                public void onFinishedGetList(ArrayList<NewsModel> listNews) {
                    if (listNews != null && listNews.size() != 0) {
                        list = listNews;
                        getViewState().setListNews(list);
                    } else {
                        getViewState().setEmptyList();
                    }
                }
            });
        }

    }
}