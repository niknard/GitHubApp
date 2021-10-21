package ru.drankin.dev.githubapp.di

import dagger.Component
import ru.drankin.dev.githubapp.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules=[])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}