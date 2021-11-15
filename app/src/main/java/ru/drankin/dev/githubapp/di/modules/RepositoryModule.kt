package ru.drankin.dev.githubapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.drankin.dev.githubapp.data.datasource.MainDatabase
import ru.drankin.dev.githubapp.data.datasource.UserDAO
import ru.drankin.dev.githubapp.data.repository.ApiKeyRepository
import ru.drankin.dev.githubapp.data.repository.UserRepository
import ru.drankin.dev.githubapp.ui.main.MainActivity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule() {
    @Provides
    @Singleton
    fun provideRoomDbInstance(@ApplicationContext appContext : Context): MainDatabase {
        return MainDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideApiKeyRepository(@ApplicationContext appContext : Context): ApiKeyRepository {
        return ApiKeyRepository(appContext)
    }

    @Provides
    @Singleton
    fun provideUserDAO(mainDb : MainDatabase): UserDAO {
        return mainDb.userDao()
    }

    @Provides
    @Singleton
    fun provideRepository(userDAO: UserDAO): UserRepository {
        return UserRepository(userDAO)
    }
}