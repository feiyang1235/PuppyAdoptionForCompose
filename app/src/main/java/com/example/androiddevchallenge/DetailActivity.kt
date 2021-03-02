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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val puppy: PuppyBean = intent.getSerializableExtra("puppy") as PuppyBean
        setContent {
            MyTheme(darkTheme = false) {
                Surface(
                    color = MaterialTheme.colors.background, modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
                {
                    DetailLayout(this@DetailActivity, puppy)
                }
            }
        }
    }
}

@Composable
fun DetailLayout(activity: AppCompatActivity, puppy: PuppyBean) {
    val buttonStatus = remember { mutableStateOf(true) }
    Column(
        Modifier
            .padding(10.dp)
    ) {
        Button(onClick = { activity.finish() }, modifier = Modifier.padding(bottom = 30.dp)) {
            Text(
                "back",
                style = MaterialTheme.typography.button
            )
        }
        Button(
            onClick = { buttonStatus.value = !buttonStatus.value },
            enabled = buttonStatus.value,
            colors = ButtonDefaults.buttonColors(backgroundColor = if (buttonStatus.value) MaterialTheme.colors.primary else Color.Gray)
        ) {
            Text(
                if (buttonStatus.value) "Adpot it" else "Thank you",
                style = MaterialTheme.typography.button
            )
        }
        SubDetailLayout(puppy)
    }
}

@Composable
fun SubDetailLayout(puppy: PuppyBean) {
    val state = rememberScrollState()
    Column(
        Modifier
            .padding(20.dp)
            .verticalScroll(state),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imagePainter = painterResource(puppy.resId)
        Image(
            imagePainter,
            "",
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(4.dp))
        )
        Text(
            puppy.name,
            style = MaterialTheme.typography.h5
        )
        Text(
            puppy.detail,
            Modifier.padding(top = 2.dp)
        )
    }
}