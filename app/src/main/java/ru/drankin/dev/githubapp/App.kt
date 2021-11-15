package ru.drankin.dev.githubapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.drankin.dev.githubapp.data.repository.UserRepository
import javax.inject.Inject

@HiltAndroidApp
class App : Application()