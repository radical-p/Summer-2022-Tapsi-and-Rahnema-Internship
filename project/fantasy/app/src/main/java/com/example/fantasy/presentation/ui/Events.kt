package com.example.fantasy.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.fantasy.R
import com.example.fantasy.domain.repository.ManagerFeed
import com.example.fantasy.presentation.viewModels.DeferredData

@Composable
fun Events(managerFeed: DeferredData<ManagerFeed>) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        when(managerFeed) {
            is DeferredData.Error -> {
                Box(modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF)))
            }
            is DeferredData.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(color = Color(0xff05F1FF))
                }
            }
            is DeferredData.Loaded -> {
                Box(modifier = Modifier.fillMaxSize().background(Color(0xffFFFFFF))) {
                    LazyColumn(modifier = Modifier.background(Color.White)) {
                        items(items = managerFeed.data.items) {
                            EventsComponent(isLiked = it.isLiked, playerName = it.fullName, score = it.points)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventsComponent(
    isLiked: Boolean,
    playerName: String,
    score: String,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10))
            .fillMaxWidth()
            .height(160.dp)
            .background(Color(0xffFBFBFB))
    ) {
        Column(modifier = Modifier.weight(0.2f)) {
            Column() {
                Image(
                    modifier = Modifier.
                    align(alignment = Alignment.CenterHorizontally)
                        //.padding(top = 16.dp, start = 8.dp, end = 8.dp)
                        .height(60.dp)
                        .width(60.dp)
                        .clip(CircleShape),
                    painter = painterResource(R.drawable.testimage),
                    contentDescription = "manager_image",
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    text = playerName,
                    textAlign = TextAlign.Center,
                    color = Color(0xff3D195B)
                )
                Spacer(modifier = Modifier.height(5.dp))
                if (isLiked) {
                    Icon(modifier = Modifier.align(alignment = Alignment.CenterHorizontally), imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
                }
                else{
                    Icon(modifier = Modifier.align(alignment = Alignment.CenterHorizontally), imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .padding(8.dp)
                .clip(RoundedCornerShape(10))
                .background(Color(0xffFFFFFF))
        ) {
            Row(modifier = Modifier.weight(1.5f)) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 6.dp, bottom = 10.dp),
                    text = "امتیاز هفته",
                    textAlign = TextAlign.Start,
                    color = Color(0xff3D195B)
                )
                Row(
                    modifier = Modifier
                        .width(55.dp)
                        .height(25.dp)
                        .padding(top = 5.dp)
                        .clip(RoundedCornerShape(20))
                        .background(Color(0xff03FBB8))
                ) {
                    Image(
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp, bottom = 2.dp, top = 3.dp)
                            .height(13.dp)
                            .width(13.dp),
                        painter = painterResource(R.drawable.star),
                        contentDescription = "star_image",
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 1.dp, end = 5.dp, bottom = 3.dp),
                        text = score,
                        textAlign = TextAlign.Start,
                        color = Color(0xff3D195B)
                    )
                }
            }
            Column(modifier = Modifier.weight(4.5f)) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 5.dp),
                    text = "تعویض ها",
                    textAlign = TextAlign.Start,
                    color = Color(0xff3D195B)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .rotate(-90f)
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(20))
                        .background(Color(0xff3D195B)),
                    text = "#هفته_پنجم",
                    textAlign = TextAlign.Center,
                    color = Color(0xff3D195B),
                    maxLines = 1
                )
            }
        }
    }
}

