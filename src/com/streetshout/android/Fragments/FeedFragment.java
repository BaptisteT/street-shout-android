package com.streetshout.android.fragments;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.streetshout.android.adapters.NewShoutFeedAdapter;
import com.streetshout.android.models.Shout;
import com.streetshout.android.R;

import java.util.ArrayList;

public class FeedFragment extends ListFragment {
    private OnFeedShoutSelectedListener shoutSelectedListener;

    private View progressBarWrapper = null;

    private View feedWrapperView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBarWrapper = getView().findViewById(R.id.feed_progress_bar);
        feedWrapperView = getView().findViewById(R.id.feed_wrapper);
    }

    public void showFeedProgressBar() {
        progressBarWrapper.setVisibility(View.VISIBLE);
        feedWrapperView.setVisibility(View.GONE);
    }

    public void hideFeedProgressBar() {
        progressBarWrapper.setVisibility(View.GONE);
        feedWrapperView.setVisibility(View.VISIBLE);
    }

    public void setAdapter(Activity activity, ArrayList<Shout> shouts) {
        setListAdapter(new NewShoutFeedAdapter(activity, shouts));
    }

    public interface OnFeedShoutSelectedListener {
        public void onFeedShoutSelected(Shout shout);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            shoutSelectedListener = (OnFeedShoutSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFeedShoutSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        shoutSelectedListener.onFeedShoutSelected((Shout) getListAdapter().getItem(position));
    }
}
