package com.polish.uploadimagetodarotserver.di

import com.polish.uploadimagetodarotserver.repository.IRepository
import com.polish.uploadimagetodarotserver.repository.IRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class IRepositoryInjectionModule {
    @Binds
    abstract fun bindToInterface(repositoryImpl:IRepositoryImpl):IRepository
}