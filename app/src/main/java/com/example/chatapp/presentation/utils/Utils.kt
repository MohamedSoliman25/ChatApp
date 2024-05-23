package com.example.chatapp.presentation.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun decodeImage(data: String): Bitmap? {
    val decodedString: ByteArray = Base64.decode(data, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}
var ID = ""
var EXTRA_FROM_USER_ID = "from_user"
var EXTRA_To_USER_ID = "to_user"

