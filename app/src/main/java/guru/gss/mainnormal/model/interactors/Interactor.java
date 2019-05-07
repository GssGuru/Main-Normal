package guru.gss.mainnormal.model.interactors;

import guru.gss.mainnormal.model.interactors.news.interfaces.OnFinishedListener;

public interface Interactor {

    interface InteractorNews {

        void getList(String author, OnFinishedListener listener);
    }
}
