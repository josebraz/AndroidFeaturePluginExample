package com.josebraz.myapplication.base.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josebraz.myapplication.base.AppHostContract
import com.josebraz.myapplication.base.FeatureMenuBurger
import com.josebraz.myapplication.base.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch


@Composable
fun CustomAppBar(
    title: String,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (drawerState.isClosed) {
                IconButton(
                    onClick = { scope.launch { drawerState.open() } }
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Localized description"
                    )
                }
            } else {
                IconButton(
                    onClick = { scope.launch { drawerState.close() } }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        actions = { }
    )
}

@Composable
fun CustomDrawer() {
    val context = LocalContext.current
    val applicationHost = context.applicationContext as AppHostContract
    Text(
        applicationHost.getApplicationName(),
        modifier = Modifier.padding(16.dp)
    )
    Divider()

    val items = applicationHost.getFeatures()
        .mapNotNull { it.featureMenuBurger }
        .sortedBy { it.priority }

    LazyColumn {
        this.items(count = items.size) { index ->
            val menuBurger = items[index]
            CustomDrawerItem(
                menuBurger,
                onClick = {
                    Intent(context, menuBurger.activity).let {
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                        context.startActivity(it)
                    }
                }
            )
        }
    }
}

@Composable
fun CustomDrawerItem(
    menuBurger: FeatureMenuBurger,
    onClick: () -> Unit
) {
    val isCurrent = LocalContext.current.javaClass == menuBurger.activity
    ListItem(
        modifier = Modifier.clickable { onClick.invoke() },
        icon = {
            Icon(
                painter = painterResource(id = menuBurger.iconId),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        },
        text = {
            Text(
                stringResource(menuBurger.titleId),
                style = MaterialTheme.typography.h5,
                color = if (isCurrent) MaterialTheme.colors.primaryVariant else LocalTextStyle.current.color
            )
        }
    )
}

@Composable
fun CustomScaffold(
    title: String,
    content: @Composable (PaddingValues) -> Unit
) {
    MyApplicationTheme {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                CustomAppBar(title, scaffoldState.drawerState)
            },
            drawerContent = { CustomDrawer() },
            content = content
        )
    }
}