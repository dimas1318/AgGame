package com.hackaton.agilegamificator.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hackaton.agilegamificator.AppManager;
import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.StringUtils;
import com.hackaton.agilegamificator.network.Balance;
import com.hackaton.agilegamificator.network.PyRequestManager;
import com.hackaton.agilegamificator.presentation.login.LoginActivity;
import com.hackaton.agilegamificator.presentation.shop.ShopActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.avatar_image)
    ImageView mPhoto;

    @BindView(R.id.account_name)
    TextView mName;

    @BindView(R.id.account_points)
    TextView mBalance;

    @BindView(R.id.account_position)
    TextView mGrade;

    private Unbinder mUnbinder;

    @NonNull
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mName.setText(AppManager.getInstance().readDisplayName());
    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.go_to_shop)
    public void onGoToShopClicked() {
        Intent intent = new Intent(getContext(), ShopActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logout)
    public void onLogOutClicked() {
        AppManager.getInstance().removeAccountId();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
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

            PyRequestManager.getInstance().getBalance(accountId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<Response<Balance>>() {
                        @Override
                        public void onSuccess(Response<Balance> balanceResponse) {
                            if (getActivity() instanceof MainActivity) {
                                ((MainActivity) getActivity()).hideProgress();
                            }
                            if (mBalance != null) {
                                if (balanceResponse.body() != null) {
                                    mBalance.setText(StringUtils.formatBalance(balanceResponse.body().getBalance()));
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
}
