package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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

class DetailsActivity : AppCompatActivity() {


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
                    DetailLayout(this@DetailsActivity, puppy)
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
        Button(onClick = { activity.finish() },modifier = Modifier.padding(bottom = 30.dp)) {
            Text("back", style = MaterialTheme.typography.button)
        }

        Button(
            onClick = {
                buttonStatus.value = !buttonStatus.value
            },
            enabled = buttonStatus.value,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (buttonStatus.value) MaterialTheme.colors.primary else Color.Gray
            )
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
            imagePainter, "",
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
            Modifier
                .padding(top = 2.dp)
        )
    }
}