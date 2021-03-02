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
            Intent(this, DetailsActivity::class.java)
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
            imagePainter, "",
            Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp))
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 10.dp)
                }
        )

        Text(
            text = puppy.name,
            modifier = Modifier.constrainAs(nameText) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(image.end, margin = 10.dp)
//                end.linkTo(parent.end,10.dp)
            }, style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Text(
            "${puppy.subtitle} days",
            Modifier
                .constrainAs(subtitle) {
                    top.linkTo(nameText.bottom, margin = 2.dp)
                    start.linkTo(image.end, margin = 10.dp)
//                    end.linkTo(parent.end,10.dp)
                }
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(getList())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

fun getList(): List<PuppyBean> {
    val puppys = mutableListOf<PuppyBean>()
    puppys.add(
        PuppyBean(
            0,
            "Golden hair",
            R.drawable.golden,
            "She sat in the darkened room waiting. It was now a standoff. He had the power to put her in the room, but not the power to make her repent. It wasn't fair and no matter how long she had to endure the darkness, she wouldn't change her attitude. At three years old, Sandy's stubborn personality had already bloomed into full view.",
            "If you're looking for random paragraphs, you've come to the right place. When a random word or a random sentence"
        )
    )
    puppys.add(
        PuppyBean(
            1,
            "Fadou",
            R.drawable.fadou,
            "She was in a hurry. Not the standard hurry when you're in a rush to get someplace, but a frantic hurry. The type of hurry where a few seconds could mean life or death. She raced down the road ignoring speed limits and weaving between cars. She was only a few minutes away when traffic came to a dead standstill on the road ahead.",
            "We created the Random Paragraph Generator with you in mind. The process is quite simple. Choose the number of random paragraphs"
        )
    )
    puppys.add(
        PuppyBean(
            2,
            "Kirky",
            R.drawable.kirky,
            "Sometimes it's the first moment of the day that catches you off guard. That's what Wendy was thinking. She opened her window to see fire engines screeching down the street. While this wasn't something completely unheard of, it also wasn't normal. It was a sure sign of what was going to happen that day. She could feel it in her bones and it wasn't the way she wanted the day to begin.",
            " you'd like to see and click the button. Your chosen number of paragraphs will instantly appear."
        )
    )
    puppys.add(
        PuppyBean(
            3,
            "puppy1",
            R.drawable.puppy_01,
            "He was aware there were numerous wonders of this world including the unexplained creations of humankind that showed the wonder of our ingenuity. There are huge heads on Easter Island. There are the Egyptian pyramids. There’s Stonehenge. But he now stood in front of a newly discovered monument that simply didn't make any sense and he wondered how he was ever going to be able to explain it.",
            "While it may not be obvious to everyone, there are a number of reasons creating random paragraphs can be useful. "
        )
    )
    puppys.add(
        PuppyBean(
            4,
            "puppy2",
            R.drawable.puppy_02,
            "Her eyebrows were a shade darker than her hair. They were thick and almost horizontal, emphasizing the depth of her eyes. She was rather handsome than beautiful. Her face was captivating by reason of a certain frankness of expression and a contradictory subtle play of features. Her manner was engaging.",
            "A few examples of how some people use this generator are listed in the following paragraphs Creative"
        )
    )
    puppys.add(
        PuppyBean(
            5,
            "puppy3",
            R.drawable.puppy_03,
            "Out of another, I get a lovely view of the bay and a little private wharf belonging to the estate. There is a beautiful shaded lane that runs down there from the house. I always fancy I see people walking in these numerous paths and arbors, but John has cautioned me not to give way to fancy in the least. He says that with my imaginative power and habit of story-making a nervous weakness like mine is sure to lead to all manner of excited fancies and that I ought to use my will and good sense to check the tendency. So I try.",
            "Generating random paragraphs can be an excellent way for writers to get their creative flow going at the beginning of the day."
        )
    )
    puppys.add(
        PuppyBean(
            6,
            "puppy4",
            R.drawable.puppy_04,
            "She's asked the question so many times that she barely listened to the answers anymore. The answers were always the same. Well, not exactly the same, but the same in a general sense. A more accurate description was the answers never surprised her. So, she asked for the 10,000th time, \"What's your favorite animal?\" But this time was different. When she heard the young boy's answer, she wondered if she had heard him correctly.",
            "The writer has no idea what topic the random paragraph will be about when it appears. "
        )
    )
    puppys.add(
        PuppyBean(
            7,
            "shepherd",
            R.drawable.shepherd,
            "The rain and wind abruptly stopped, but the sky still had the gray swirls of storms in the distance. Dave knew this feeling all too well. The calm before the storm. He only had a limited amount of time before all Hell broke loose, but he stopped to admire the calmness. Maybe it would be different this time, he thought, with the knowledge deep within that it wouldn't.",
            "This forces the writer to use creativity to complete one of three common writing challenges"
        )
    )
    puppys.add(
        PuppyBean(
            8,
            "teddy",
            R.drawable.teddy,
            "It was their first date and she had been looking forward to it the entire week. She had her eyes on him for months, and it had taken a convoluted scheme with several friends to make it happen, but he'd finally taken the hint and asked her out. After all the time and effort she'd invested into it, she never thought that it would be anything but wonderful. It goes without saying that things didn't work out quite as she expected.",
            "The writer can use the paragraph as the first one of a short story and build upon it. A second option is to use the random paragraph somewhere"
        )
    )
    return puppys
}
