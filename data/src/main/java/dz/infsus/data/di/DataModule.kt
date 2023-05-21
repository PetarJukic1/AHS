package dz.infsus.data.di

import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dz.infsus.data.addNew.api.AddNewApi
import dz.infsus.data.addNew.repository.RemoteAddNewRepository
import dz.infsus.data.adverts.api.AdvertsApi
import dz.infsus.data.adverts.mapper.AdvertsMapper
import dz.infsus.data.adverts.repository.RemoteAdvertsRepository
import dz.infsus.data.delete.api.DeleteApi
import dz.infsus.data.delete.repository.RemoteDeleteRepository
import dz.infsus.data.register.api.LogInApi
import dz.infsus.data.register.api.SignUpApi
import dz.infsus.data.register.mapper.LogInMapper
import dz.infsus.data.register.mapper.SignUpMapper
import dz.infsus.data.register.repository.RemoteRegisterRepository
import dz.infsus.data.reservation.api.ReservationApi
import dz.infsus.data.reservation.repository.RemoteReserveRepository
import dz.infsus.data.storeId.LocalStoreIdRepository
import dz.infsus.domain.addNew.repository.AddNewRepository
import dz.infsus.domain.adverts.repository.AdvertsRepository
import dz.infsus.domain.delete.repository.DeleteRepository
import dz.infsus.domain.register.repository.RegisterRepository
import dz.infsus.domain.reservation.repository.ReserveRepository
import dz.infsus.domain.storeId.repository.StoreIdRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<LogInApi> {
        val retrofit = get<Retrofit.Builder>()
            .baseUrl("http://vulama.ddns.net:4321/")
            .build()

        retrofit.create(LogInApi::class.java)
    }

    single<SignUpApi> {
        val retrofit = get<Retrofit.Builder>()
            .baseUrl("http://vulama.ddns.net:4321/")
            .build()

        retrofit.create(SignUpApi::class.java)
    }

    single<AdvertsApi> {
        val retrofit = get<Retrofit.Builder>()
            .baseUrl("http://vulama.ddns.net:4321/")
            .build()

        retrofit.create(AdvertsApi::class.java)
    }

    single<AddNewApi> {
        val retrofit = get<Retrofit.Builder>()
            .baseUrl("http://vulama.ddns.net:4321/")
            .build()

        retrofit.create(AddNewApi::class.java)
    }

    single<DeleteApi> {
        val retrofit = get<Retrofit.Builder>()
            .baseUrl("http://vulama.ddns.net:4321/")
            .build()

        retrofit.create(DeleteApi::class.java)
    }

    single<ReservationApi> {
        val retrofit = get<Retrofit.Builder>()
            .baseUrl("http://vulama.ddns.net:4321/")
            .build()

        retrofit.create(ReservationApi::class.java)
    }

    single<Retrofit.Builder> {
        Retrofit.Builder()
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
    }
    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single<RegisterRepository>() {
        RemoteRegisterRepository(
            logInApi = get<LogInApi>(),
            logInMapper = get<LogInMapper>(),
            signUpApi = get<SignUpApi>(),
            signUpMapper = get<SignUpMapper>(),
        )
    }

    single<SignUpMapper> { SignUpMapper() }
    single<LogInMapper> { LogInMapper() }

    single<StoreIdRepository> {
        LocalStoreIdRepository(
            sharedPreferences = get<SharedPreferences>(),
        )
    }

    single<AdvertsRepository> {
        RemoteAdvertsRepository(
            advertsApi = get<AdvertsApi>(),
            mapper = get<AdvertsMapper>()
        )
    }

    single<ReserveRepository> {
        RemoteReserveRepository(
            reservationApi = get<ReservationApi>(),
        )
    }

    single<DeleteRepository> {
        RemoteDeleteRepository(
            deleteApi = get<DeleteApi>(),
        )
    }

    single<AddNewRepository> {
        RemoteAddNewRepository(
            addNewApi = get<AddNewApi>(),
        )
    }

    single<AdvertsMapper> { AdvertsMapper() }

    single<SharedPreferences> {
        androidContext().getSharedPreferences("default", android.content.Context.MODE_PRIVATE)
    }
}