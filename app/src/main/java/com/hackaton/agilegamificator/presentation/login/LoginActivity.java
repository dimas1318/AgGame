package com.hackaton.agilegamificator.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hackaton.agilegamificator.AppManager;
import com.hackaton.agilegamificator.R;
import com.hackaton.agilegamificator.StringUtils;
import com.hackaton.agilegamificator.network.Acc;
import com.hackaton.agilegamificator.network.PyRequestManager;
import com.hackaton.agilegamificator.presentation.main.MainActivity;

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
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_layout_email)
    TextInputLayout mEmailLayout;

    @BindView(R.id.input_email)
    EditText mEmailEditText;

    @BindView(R.id.input_layout_password)
    TextInputLayout mPasswordLayout;

    @BindView(R.id.input_password)
    EditText mPasswordEditText;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mUnbinder = ButterKnife.bind(this);

        mEmailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mEmailLayout.setError(null);
            }
        });

        mPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPasswordLayout.setError(null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @OnClick(R.id.log_in_button)
    public void onLogInButtonClick() {
        String email = mEmailEditText.getText().toString().trim();
        if (StringUtils.isEmpty(email)) {
            mEmailLayout.setError("The field can not be empty");
            return;
        }

        String password = mPasswordEditText.getText().toString().trim();
        if (StringUtils.isEmpty(password)) {
            mPasswordLayout.setError("The field can not be empty");
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);

        PyRequestManager.getInstance().getAccountId(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Response<Acc>>() {

                    @Override
                    public void onSuccess(Response<Acc> response) {
                        mProgressBar.setVisibility(View.GONE);
                        if (response.body() == null || StringUtils.isEmpty(response.body().getAccount_id())) {
                            mEmailLayout.setError("Неверный email");
                        } else {
                            String accountId = response.body().getAccount_id();
                            AppManager.getInstance().saveAccountId(accountId);
                            AppManager.getInstance().saveDisplayName(response.body().getDisplayName());

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);

                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
