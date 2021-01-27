package com.example.wichitoaplication

import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties

@Keep
@IgnoreExtraProperties
data class ModeloDB(var datos1:String? = null, var datos2:String? = null)