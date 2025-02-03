package com.esrabildik.noteapp.presentation.homePage

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.esrabildik.noteapp.R
import com.esrabildik.noteapp.navigation.AppNavHost
import com.esrabildik.noteapp.ui.theme.CustomFontFamily
import com.esrabildik.noteapp.utils.getFormattedDateTime


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePageUI(navController: NavController, context: Context) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            TopBar()
            TabTopBar()
        }

    }

    @Composable
    fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 20.dp, 5.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Sol taraftaki metinler için bir Column
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Today's Task",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontFamily = CustomFontFamily
                )
                Text(
                    text = getFormattedDateTime(),
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    fontFamily = CustomFontFamily
                )
            }

            ExtendedFloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = Color.White
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add icon",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(2.dp),
                    tint = Color.DarkGray
                )
                Text(
                    text = "New Task",
                    fontFamily = CustomFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }
        }
    }


    @Composable
    fun TabTopBar() {
        val tabs = listOf("All", "Open", "Closed")
        var selectedTabIndex by remember { mutableStateOf(1) }
        Column {
            TabRow(selectedTabIndex = selectedTabIndex,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                    )
                }) {

                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                text = title,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                            )
                        })
                }

            }
        }
    }

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    AppNavHost()
}




