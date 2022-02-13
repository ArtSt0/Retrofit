package ru.artsto.retrofit.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.artsto.retrofit.room.Case

interface RetrofitApiInterface {
    @POST("/cases/new_case/")
    fun newCase(@Body case: Case): Single<Response<Case>>

    @GET("index.php")
    fun getNews():Single<String>
}