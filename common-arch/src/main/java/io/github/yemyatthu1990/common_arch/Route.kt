package io.github.yemyatthu1990.common_arch

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

interface Route{
    fun navigateTo(label: String)
}