package com.hackaton.agilegamificator.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hackaton.agilegamificator.AppManager;
import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.StringUtils;
import com.hackaton.agilegamificator.custom_views.ItemOffsetDecoration;
import com.hackaton.agilegamificator.network.PyRequestManager;
import com.hackaton.agilegamificator.network.Sprint;
import com.hackaton.agilegamificator.presentation.login.LoginActivity;

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
public class TasksFragment extends Fragment {

    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.tip)
    TextView mTip;

    private Unbinder mUnbinder;

    private TaskAdapter mAdapter;

    private List<Sprint> mSprints;

    @NonNull
    public static TasksFragment newInstance() {
        return new TasksFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tasks_fragment, container, false);
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

    private void setupSpinner(List<String> list) {
        if (getContext() != null) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinner.setAdapter(dataAdapter);
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String sprintName = parent.getItemAtPosition(position).toString();
                    List<Sprint> sprints = mSprints.stream()
                            .filter(sprint -> sprint.getName().equalsIgnoreCase(sprintName))
                            .collect(Collectors.toList());
                    if (sprints.get(0).getIssues() == null ||
                            sprints.get(0).getIssues().isEmpty()) {
                        mTip.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        mTip.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mAdapter.setData(sprints.get(0).getIssues());
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
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(getContext(), R.dimen.margin_16dp));
        mAdapter = new TaskAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData() {
        String accountId = AppManager.getInstance().readAccountId();
        if (StringUtils.isEmpty(accountId)) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            if (getActivity() != null) {
                getActivity().finish();
            }
        } else {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).showProgress();
            }

            PyRequestManager.getInstance().getSprints(accountId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<Response<List<Sprint>>>() {
                        @Override
                        public void onSuccess(Response<List<Sprint>> listResponse) {
                            if (getActivity() instanceof MainActivity) {
                                ((MainActivity) getActivity()).hideProgress();
                            }

                            List<Sprint> sprints = listResponse.body();
                            if (sprints != null) {
                                List<String> sprintNames = sprints.stream()
                                        .map(Sprint::getName)
                                        .collect(Collectors.toList());
                                setupSpinner(sprintNames);
                                mSprints = sprints;

                                if (mTip != null
                                        && mRecyclerView != null) {
                                    if (sprints.get(0).getIssues() == null ||
                                            sprints.get(0).getIssues().isEmpty()) {
                                        mTip.setVisibility(View.VISIBLE);
                                        mRecyclerView.setVisibility(View.GONE);
                                    } else {
                                        mTip.setVisibility(View.GONE);
                                        mRecyclerView.setVisibility(View.VISIBLE);
                                        mAdapter.setData(sprints.get(0).getIssues());
                                    }
                                }
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

    private int convertPrior(String p) {
        if (p.equalsIgnoreCase("highest")) return 4;
        if (p.equalsIgnoreCase("high")) return 3;
        if (p.equalsIgnoreCase("low")) return 2;
        if (p.equalsIgnoreCase("lowest")) return 1;
        return 0;
    }

    private int convertStatus(String p) {
        if (p.equalsIgnoreCase("in progress")) return 3;
        if (p.equalsIgnoreCase("to do")) return 2;
        if (p.equalsIgnoreCase("done")) return 1;
        return 0;
    }
}
