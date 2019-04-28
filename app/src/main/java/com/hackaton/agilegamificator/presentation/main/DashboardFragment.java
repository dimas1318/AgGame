package com.hackaton.agilegamificator.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.network.Dashboard;
import com.hackaton.agilegamificator.network.PyRequestManager;
import com.hackaton.agilegamificator.network.Sprint;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class DashboardFragment extends Fragment {

    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private DashboardAdapter mAdapter;

    private Dashboard mDashboard;

    @NonNull
    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setupRecycler();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setupSpinner() {
        if (getContext() != null) {
            List<String> list = Arrays.asList("Active", "Total");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(dataAdapter);
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String ratingName = parent.getItemAtPosition(position).toString();
                    if (ratingName.equalsIgnoreCase("Active")) {
                        mAdapter.setData(mDashboard.getActive());
                    } else {
                        mAdapter.setData(mDashboard.getTotal());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void setupRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new DashboardAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showProgress();
        }

        PyRequestManager.getInstance().getDashboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<Dashboard>>() {
                    @Override
                    public void onSuccess(Response<Dashboard> dashboardResponse) {
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).hideProgress();
                        }

                        Dashboard dashboard = dashboardResponse.body();
                        if (dashboard != null) {
                            mDashboard = dashboard;
                            mAdapter.setData(dashboard.getActive());
                            setupSpinner();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getActivity() instanceof MainActivity) {
                            ((MainActivity) getActivity()).hideProgress();
                        }
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }
}
