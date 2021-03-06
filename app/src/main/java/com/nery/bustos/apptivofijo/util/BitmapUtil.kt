package com.nery.bustos.apptivofijo.util

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory





object BitmapUtil  {
 fun Bitmap.toBase64() : String{
     val byteArrayOutputStream = ByteArrayOutputStream()
     this.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
     val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
     return Base64.encodeToString(byteArray, Base64.DEFAULT)
 }

 fun String.toBitmap() : Bitmap{
     val decodedString: ByteArray = Base64.decode(this, Base64.DEFAULT)
     return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
 }

}