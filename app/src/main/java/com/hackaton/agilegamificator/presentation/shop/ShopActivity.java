package com.hackaton.agilegamificator.presentation.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hackaton.agilegamificator.AppManager;
import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.StringUtils;
import com.hackaton.agilegamificator.network.Bon;
import com.hackaton.agilegamificator.network.PyRequestManager;
import com.hackaton.agilegamificator.presentation.login.LoginActivity;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.leochuan.ViewPagerLayoutManager;

import java.util.Arrays;

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
public class ShopActivity extends AppCompatActivity implements BonusPay {

    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private BonusAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Bonus Shop");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.shop_activity);
        mUnbinder = ButterKnife.bind(this);

        setupRecyclerView();
        loadBonuses();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    private void setupRecyclerView() {
        ScaleLayoutManager layoutManager = new ScaleLayoutManager.Builder(this, 16)
                .setOrientation(ViewPagerLayoutManager.HORIZONTAL)
                .setMaxAlpha(1F)
                .setMinAlpha(0.2F)
                .setMinScale(0.5F)
                .build();
        mRecyclerView.setLayoutManager(layoutManager);
        new CenterSnapHelper().attachToRecyclerView(mRecyclerView);
        mAdapter = new BonusAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadBonuses() {
        Bonus f = new Bonus("Перевести в деньги", 100);
        Bonus s = new Bonus("Купить путевку", 150);
        Bonus t = new Bonus("Взять отгул", 200);
        mAdapter.setData(Arrays.asList(f, s, t));
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void pay(int cost) {
        String accountId = AppManager.getInstance().readAccountId();
        if (StringUtils.isEmpty(accountId)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            showProgress();

            PyRequestManager.getInstance().postBonusWasting(accountId, cost)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSingleObserver<Response<Bon>>() {
                        @Override
                        public void onSuccess(Response<Bon> response) {
                            hideProgress();
                            //todo
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideProgress();
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}
