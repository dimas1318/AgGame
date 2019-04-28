package com.hackaton.agilegamificator.presentation.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.hackaton.agilegamificator.R;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;
import com.leochuan.ViewPagerLayoutManager;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class ShopActivity extends AppCompatActivity {

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
        mAdapter = new BonusAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadBonuses() {
        Bonus f = new Bonus("Перевести в деньги", 100);
        Bonus s = new Bonus("Купить путевку", 150);
        Bonus t = new Bonus("Взять отгул", 200);
        mAdapter.setData(Arrays.asList(f, s, t));
    }
}
