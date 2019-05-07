package guru.gss.mainnormal.aplication.main.fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.Objects;

import guru.gss.mainnormal.R;
import guru.gss.mainnormal.aplication.BaseFragment;
import guru.gss.mainnormal.model.interactors.news.NewsInteractor;
import guru.gss.mainnormal.model.repository.network.NetworkRepositoryImpl;
import guru.gss.mainnormal.utils.model.NewsModel;

public class FragmentNews extends BaseFragment implements ViewFragment {

    @InjectPresenter
    PresenterFragment presenter;

    @ProvidePresenter
    PresenterFragment providePresenter() {
        return new PresenterFragment(new NewsInteractor(new NetworkRepositoryImpl()));
    }

    private static final String NEWS_AUTHOR = "news_author";
    private static final String NEWS_TITLE = "news_title";
    private String author, title;

    private OnFragmentInteractionListener mListener;

    private AdapterNews adapterNews;
    private ProgressBar progress;
    private RecyclerView recyclerView;
    private LinearLayout fl_items_not_found;
    private PullRefreshLayout refresh_view;

    public FragmentNews() {
    }

    public static FragmentNews newInstance(String author, String title) {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();
        args.putString(NEWS_AUTHOR, author);
        args.putString(NEWS_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            author = getArguments().getString(NEWS_AUTHOR);
            title = getArguments().getString(NEWS_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_news, container, false);

        progress = v.findViewById(R.id.progress);
        refresh_view = v.findViewById(R.id.refresh_view);
        recyclerView = v.findViewById(R.id.recyclerView);
        fl_items_not_found = v.findViewById(R.id.fl_items_not_found);

        refresh_view.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getNewsList(author);
            }
        });

        adapterNews = new AdapterNews(getContext());
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_news_animation);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterNews);

        progress.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        fl_items_not_found.setVisibility(View.GONE);

        Toolbar mToolbar = v.findViewById(R.id.toolbar);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorIcons), PorterDuff.Mode.SRC_ATOP);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.openDrover();
                }
            }
        });

        mToolbar.setTitle(String.valueOf(title));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));

        presenter.getNewsList(author);

        return v;
    }

    boolean mUserVisibleHint;

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        mUserVisibleHint = menuVisible;
        if (menuVisible && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mUserVisibleHint) {
            if (adapterNews.getItemCount() == 0) {
                presenter.getNewsList(author);
            }
        }
    }

    @Override
    public void setListNews(ArrayList<NewsModel> list) {
        if (list.size() == 0) {
            if (fl_items_not_found.getVisibility() != View.VISIBLE) {
                showContentAnimation(fl_items_not_found, progress);
            }
        } else {
            adapterNews.addAll(list);
            showContentAnimation(recyclerView, progress);
        }
        hideRefreshView(refresh_view);
    }

    @Override
    public void setEmptyList() {
        hideRefreshView(refresh_view);
    }

    @Override
    public void setError() {
        hideRefreshView(refresh_view);
        DialigError mDialigError = DialigError.newInstance();
        mDialigError.registerInterfaceCallback(new DialigError.InterfaceCallback() {
            @Override
            public void refresh() {
                presenter.getNewsList(author);
            }

            @Override
            public void exit() {
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        mDialigError.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), mDialigError.getClass().getSimpleName());
    }

    public void hideRefreshView(PullRefreshLayout refresh_view) {
        if (refresh_view.isShown()) {
            refresh_view.setRefreshing(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void openDrover();
    }

}