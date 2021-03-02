/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        MainViewModel()
    }
    private val puppyList = mutableListOf<PuppyBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.puppyList.observe(this, Observer {
            setContent {
                MyTheme(darkTheme = false) {
                    MyApp(it) { id ->
                        val puppy = getPuppyById(it, id)
                        puppy?.let { puppy ->
                            gotoDetail(puppy)
                        }
                    }
                }
            }
        })
        viewModel.getPuppyList()
    }

    private fun gotoDetail(puppy: PuppyBean) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .apply {
                    putExtra("puppy", puppy)
                }
        )
    }

    private fun getPuppyById(puppys: List<PuppyBean>, id: Int): PuppyBean? {
        return puppys.find {
            it.id == id
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(puppyList: List<PuppyBean> = mutableListOf(), itemClick: (id: Int) -> Unit = {}) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxWidth())
    {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(puppyList) {
                ItemView(it, itemClick)
            }
        }
    }
}

@Composable
fun ItemView(puppy: PuppyBean, itemClick: (id: Int) -> Unit) {
    ConstraintLayout(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .clickable { itemClick(puppy.id) }
    ) {
        val (image, nameText, subtitle) = createRefs()
        val imagePainter = painterResource(puppy.resId)
        Image(
            imagePainter,
            "",
            Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp))
                .constrainAs(image) { top.linkTo(parent.top, margin = 10.dp) })

        Text(
            text = puppy.name, modifier = Modifier.constrainAs(nameText) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(image.end, margin = 10.dp)
            }, style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Text(puppy.subtitle, Modifier.constrainAs(subtitle) {
            top.linkTo(nameText.bottom, margin = 2.dp)
            start.linkTo(image.end, margin = 10.dp)
        })
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
