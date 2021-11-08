package ru.drankin.dev.githubapp.di

import android.app.Application
import dagger.Component
import retrofit2.Retrofit
import ru.drankin.dev.githubapp.App
import ru.drankin.dev.githubapp.data.repository.UserRepository
import ru.drankin.dev.githubapp.di.modules.NetworkModule
import ru.drankin.dev.githubapp.di.modules.RepositoryModule
import ru.drankin.dev.githubapp.ui.main.MainActivity
import javax.inject.Singleton

//@Singleton
//@Component(modules=[
//    NetworkModule::class,
//    RepositoryModule::class
//])
//interface AppComponent {
//    fun inject(mainActivity: MainActivity)
//}