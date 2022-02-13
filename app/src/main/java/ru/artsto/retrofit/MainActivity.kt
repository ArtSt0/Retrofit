package ru.artsto.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.artsto.retrofit.compose.MainScreen
import ru.artsto.retrofit.ui.theme.RetrofitTheme
import ru.artsto.retrofit.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val vmMainView: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        ViewModelProvider(this, factory)[MainViewModel::class.java]

        setContent {
            RetrofitTheme() {
                MainScreen()
            }
        }
    }
}