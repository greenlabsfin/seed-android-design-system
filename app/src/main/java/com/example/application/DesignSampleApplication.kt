package com.example.application

import android.app.Application
import java.util.*

class DesignSampleApplication : Application() {
    companion object {
        var language = Locale.getDefault().language
    }
}
