package com.annevonwolffen.tfs.developerslife.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.annevonwolffen.tfs.developerslife.R;
import com.annevonwolffen.tfs.developerslife.databinding.MainActivityLayoutBinding;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainViewModel = new ViewModelProvider(this, new ViewModelProviderFactory(this))
                .get(MainViewModel.class);

        mMainViewModel.onCreated();

        MainActivityLayoutBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinding.setViewModel(mMainViewModel);
        dataBinding.setLifecycleOwner(this);

        mMainViewModel.getCurrentGifUrl().observe(this, this::setGif);
        mMainViewModel.getCurrentIndex().observe(this, this::setButtonBackEnabled);

    }

    private void setGif(String url) {
        ImageView gifImageView = findViewById(R.id.gif);

        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_gif_placeholder)
                .fitCenter()
                .into(gifImageView);
    }

    private void setButtonBackEnabled(int curIndex) {
        final View backButton = findViewById(R.id.btn_back);
        if (curIndex > 0) {
            backButton.setEnabled(true);
            backButton.setAlpha((float) 1);
        } else {
            backButton.setEnabled(false);
            backButton.setAlpha((float) 0.4);
        }
    }
}