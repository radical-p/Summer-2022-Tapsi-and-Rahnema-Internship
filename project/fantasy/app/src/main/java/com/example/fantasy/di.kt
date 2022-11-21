package com.example.fantasy

import PersistentStorage
import Storage
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.fantasy.data.*
import com.example.fantasy.data.api.*
import com.example.fantasy.domain.repository.*
import com.example.fantasy.domain.usecase.*
import com.example.fantasy.presentation.viewModels.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

inline fun <reified T> Retrofit.create(): T = create(T::class.java)
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "Preferences")

@RequiresApi(Build.VERSION_CODES.O)
fun playerModule() = module {

    single<PlayerRepo> {
        PLayerRepoImpl(get(), get())
    }

    single<RemoveFromTeamRepo> {
        RemoveFromTeamImpl(get(), get())
    }

    single<AddToTeamRepo> {
        AddToTeamImpl(get(), get())
    }

    single<LoginRepo> {
        LoginRepoImpl(get())
    }

    single<DashboardRepo> {
        DashboardRepoImpl(get(), get())
    }

    single<SearchPlayerRepo> {
        SearchPlayerRepoImpl(get(), get())
    }

    single<DateAndWeekRepo> {
        DateAndWeekRepoImpl(get())
    }

    single<SignupRepo>{
        SignUpRepoImpl(get())
    }

    single<VerificationRepo>{
        VerificationRepoImpl(get())
    }

    single<ManagerFeedRepo> {
        ManagerFeedRepoImpl(get(), get())
    }

    single<ManagerInfoRepo> {
        ManagerInfoRepoImpl(get())
    }

    single<ManagerFollowingsRepo> {
        ManagerFollowingsRepoImpl(get(), get())
    }

    single<ManagerFollowersRepo> {
        ManagerFollowersRepoImpl(get(), get())
    }

    single<SearchManagerRepo> {
        SearchManagerRepoImpl(get(), get())
    }

    single<FollowManagerRepo> {
        FollowManagerRepoImpl(get(), get())
    }

    single<SubstitutionRepo> {
        SubstitutionRepoImpl(get(), get())
    }

    single<SearchManagerFollowingsRepo> {
        SearchManagerFollowingsRepoImpl(get(), get())
    }

    single<SearchManagerFollowersRepo> {
        SearchManagerFollowersRepoImpl(get(), get())
    }

    single<ManagerProfileRepo> {
        ManagerProfileRepoImpl(get(), get())
    }

    single<UpdateProfileRepo> {
        UpdateProfileRepoImpl(get(), get())
    }

    factory {
        RemoveFromTeamUseCase(get())
    }

    factory {
        AddToTeamUseCase(get())
    }

    factory {
        LoginUseCase(get())
    }

    factory {
        GetDashboardUseCase(get())
    }

    factory {
        GetPlayersListUseCase(get())
    }

    factory {
        SearchPlayersUseCase(get())
    }

    factory {
        GetDateAndWeekUseCase(get())
    }

    factory {
        SignUpUseCase(get())
    }

    factory {
        VerificationUseCase(get())
    }

    factory {
        GetManagerFeedUseCase(get())
    }

    factory {
        GetManagerFollowersUseCase(get())
    }

    factory {
        GetManagerFollowingsUseCase(get())
    }

    factory {
        GetManagerInfoUseCase(get())
    }

    factory {
        SearchManagerUseCase(get())
    }

    factory {
        FollowManagerUseCase(get())
    }

    factory {
        SubstitutionUseCase(get())
    }

    factory {
        SearchManagerFollowersUseCase(get())
    }

    factory {
        SearchManagerFollowingsUseCase(get())
    }

    factory {
        GetManagerProfileUseCase(get())
    }

    factory {
        UpdateManagerProfileUseCase(get())
    }

    viewModel {
        LogInViewModel(get(), get())
    }

    viewModel {
        DashboardViewModel(get(), get(), get())
    }

    viewModel {
        PlayerViewModel(get(), get())
    }

    viewModel {
        DateAndWeekViewModel(get())
    }

    viewModel {
        SignUpViewModel(get(), get())
    }

    viewModel {
        VerificationViewModel(get(), get())
    }

    viewModel {
        ManagerFeedViewModel(get())
    }

    viewModel {
        ManagerFollowingsViewModel(get(), get())
    }

    viewModel {
        ManagerFollowersViewModel(get(), get())
    }

    viewModel {
        ManagerInfoViewModel(get())
    }

    viewModel {
        SearchManagerViewModel(get())
    }

    viewModel {
        FollowManagerViewModel(get(), get())
    }

    viewModel {
        SubstitutionViewModel(get(), get())
    }

    viewModel {
        ManagerProfileViewModel(get(), get())
    }

    single<AddToTeamApi> {
        get<Retrofit>().create()
    }

    single<RemoveFromTeamApi> {
        get<Retrofit>().create()
    }

    single<LogInApi> {
        get<Retrofit>().create()
    }

    single<DashboardApi> {
        get<Retrofit>().create()
    }

    single<PlayerApi> {
        get<Retrofit>().create()
    }

    single<SearchPlayerApi> {
        get<Retrofit>().create()
    }

    single<DateAndWeekApi> {
        get<Retrofit>().create()
    }

    single<SignUpApi>{
        get<Retrofit>().create()
    }

    single<VerifyApi>{
        get<Retrofit>().create()
    }

    single<ManagerFeedApi> {
        get<Retrofit>().create()
    }

    single<ManagerInfoApi> {
        get<Retrofit>().create()
    }

    single<ManagerFollowingsApi> {
        get<Retrofit>().create()
    }

    single<ManagerFollowersApi> {
        get<Retrofit>().create()
    }

    single<SearchManagerApi> {
        get<Retrofit>().create()
    }

    single<FollowManagerApi> {
        get<Retrofit>().create()
    }

    single<SubstitutionApi> {
        get<Retrofit>().create()
    }

    single<SearchManagerFollowersApi> {
        get<Retrofit>().create()
    }

    single<SearchManagerFollowingsApi> {
        get<Retrofit>().create()
    }

    single<ManagerProfileApi> {
        get<Retrofit>().create()
    }

    single<UpdateProfileApi> {
        get<Retrofit>().create()
    }

    single<Retrofit> {
        val BASE_URL = "http://178.216.248.37:8080/api/v1/"
        val client = get<OkHttpClient>()
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }

    single<OkHttpClient> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient
            .Builder()
            .connectTimeout(Duration.ofSeconds(20))
            .readTimeout(Duration.ofSeconds(20))
            .writeTimeout(Duration.ofSeconds(20))
            .addInterceptor(interceptor)
            .build()
    }

    single<Storage>{
        PersistentStorage(
            dataStore = androidContext().dataStore
        )
    }
}