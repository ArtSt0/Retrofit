package ru.artsto.retrofit.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.artsto.retrofit.retrofit.RetrofitApiInterface
import ru.artsto.retrofit.room.Case
import ru.artsto.retrofit.room.CaseDao
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val caseDao: CaseDao,
    private val retrofitApiInterface: RetrofitApiInterface
):ViewModel() {
    private var disposables: CompositeDisposable = CompositeDisposable()

    init {
        insetCase(Case(name = "добавить новую запись"))
        getNews()
    }

    private fun insetCase(case: Case){
        caseDao.insertCase(case = case)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    Log.i("rx", "запись добавлена")
                },
                onError = {
                    Log.i("rx", "ошибка добавления = ${it.message}")
                }
            )
            .addTo(disposables)
    }

    private fun newCase(){
        retrofitApiInterface.newCase(case = Case(name = "дело №1"))
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {response ->
                    if(response.isSuccessful){
                        val case = response.body()
                    }
                },
                onError = {error->
                    Log.i("rx", "error = ${error.message}")
                }
            ).addTo(disposables)
    }

    private fun getNews(){
        retrofitApiInterface.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                    onSuccess = {str->
                        Log.i("rx", "новости  = $str")
                    },
                    onError = {error->
                        Log.i("rx", "error = ${error.message}")
                    }
            ).addTo(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}