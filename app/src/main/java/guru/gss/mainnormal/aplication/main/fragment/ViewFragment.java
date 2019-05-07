package guru.gss.mainnormal.aplication.main.fragment;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import guru.gss.mainnormal.utils.model.NewsModel;

public interface ViewFragment extends MvpView {

    void setListNews(ArrayList<NewsModel> list);
    void setEmptyList();
    void setError();
}
