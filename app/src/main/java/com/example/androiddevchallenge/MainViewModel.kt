package com.example.androiddevchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val puppyList = MutableLiveData<List<PuppyBean>>()
    fun getPuppyList() {
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
        puppyList.postValue(puppys)
    }
}