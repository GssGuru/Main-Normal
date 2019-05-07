package guru.gss.mainnormal.model.repository.network;

import guru.gss.mainnormal.model.interactors.news.interfaces.OnFinishedListener;

public interface NetworkRepository {

    void getNews(OnFinishedListener listener, String author, String key);

}
