package com.hackaton.agilegamificator.presentation.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hackaton.agilegamificator.AppManager;
import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.network.Dashboard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static final String PAGE_KEY = "com.hackaton.agilegamificator.presentation.main.MainActivity.PAGE_KEY";

    private static final int DEFAULT_PAGE = R.id.navigation_tasks;

    @BindView(R.id.nav_view)
    protected BottomNavigationView mNavView;

    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressBar;

    public Dashboard mDashboard;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);

        setupBottomNavigation();

        int page = getIntent().getIntExtra(PAGE_KEY, DEFAULT_PAGE);
        mNavView.setSelectedItemId(page);

        showMarkDialog();
        showMeetingDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onBackPressed() {
        hideProgress();
        hideKeyboard();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void setupBottomNavigation() {
        mNavView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_tasks:
                    navigateTo(TasksFragment.newInstance());
                    return true;
                case R.id.navigation_dashboard:
                    navigateTo(DashboardFragment.newInstance());
                    return true;
                case R.id.navigation_profile:
                    navigateTo(ProfileFragment.newInstance());
                    return true;
            }
            return false;
        });
    }

    private void navigateTo(@NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this
                    .getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void showMarkDialog() {
        Dialog rankDialog = new Dialog(this);
        rankDialog.setContentView(R.layout.rating_dialog);
        rankDialog.setCancelable(true);
        RatingBar ratingBar = rankDialog.findViewById(R.id.dialog_ratingbar);

        TextView text = rankDialog.findViewById(R.id.rank_dialog_text1);
        if (AppManager.getInstance().readDisplayName() != null
                && AppManager.getInstance().readDisplayName().contains("Borisov")) {
            text.setText("Set a mark to Dmitry Parshin");
        } else {
            text.setText("Set a mark to Dmitriy Borisov");
        }

        View updateButton = rankDialog.findViewById(R.id.rank_dialog_button);
        updateButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            //todo send request
            rankDialog.dismiss();
        });
        rankDialog.show();
    }

    private void showMeetingDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Did you visit a meeting today?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", null)
                .create()
                .show();
    }
}
