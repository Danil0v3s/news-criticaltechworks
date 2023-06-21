package br.com.firstsoft.news.techcrunch

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import br.com.firstsoft.feature.main.ui.MainApp
import br.com.firstsoft.feature.security.api.SecurityApi
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TechCrunchActivity : FragmentActivity() {

    @Inject
    lateinit var moshi: Moshi

    @Inject
    lateinit var securityApi: SecurityApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainApp(moshi, securityApi)
        }
    }
}
