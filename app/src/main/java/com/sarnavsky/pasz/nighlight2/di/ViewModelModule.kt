package com.sarnavsky.pasz.nighlight2.di

import com.sarnavsky.pasz.nighlight2.MediaPlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MediaPlayerViewModel() }
}