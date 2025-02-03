package com.esrabildik.noteapp.navigation

sealed class NavScreen(val route : String) {
    object HomePageUI : NavScreen("home_page")
    object AddNoteUI : NavScreen("add_note")
}