package com.anilyilmaz.n11casestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anilyilmaz.n11casestudy.core.designsystem.theme.N11CaseStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            N11CaseStudyTheme {
            }
        }
    }
}