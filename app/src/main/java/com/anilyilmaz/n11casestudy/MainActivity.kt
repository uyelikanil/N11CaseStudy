package com.anilyilmaz.n11casestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.anilyilmaz.n11casestudy.core.designsystem.theme.N11CaseStudyTheme
import com.anilyilmaz.n11casestudy.ui.N11App
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            N11CaseStudyTheme {
                N11App()
            }
        }
    }
}