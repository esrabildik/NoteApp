package com.esrabildik.noteapp.utils

// Sıralama türlerini tanımlıyoruz.
sealed class OrderType {
   object Ascending : OrderType()
   object Descending : OrderType()
}