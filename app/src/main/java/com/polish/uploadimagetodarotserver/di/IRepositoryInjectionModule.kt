package com.polish.uploadimagetodarotserver.di

import com.polish.uploadimagetodarotserver.repository.IRepository
import com.polish.uploadimagetodarotserver.repository.IRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class IRepositoryInjectionModule {
    abstract fun bindToInterface(repositoryImpl:IRepositoryImpl):IRepository
}