package com.tsu.firstlab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tsu.firstlab.databinding.ActivityOnBoardBinding

class OnBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardBinding
    private var IndexCurrentPage = 0

    private var HeaderList = mutableListOf<String>()
    private var DescriptionList = mutableListOf<String>()
    private var ImageList = mutableListOf<Int>()
    private var PaginationList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addToList(getString(R.string.onboard_header1), getString(R.string.onboard_text1), R.drawable.intro1, R.drawable.pagination1)
        addToList(getString(R.string.onboard_header2), getString(R.string.onboard_text2), R.drawable.intro2, R.drawable.pagination2)
        addToList(getString(R.string.onboard_header3), getString(R.string.onboard_text3), R.drawable.intro3, R.drawable.pagination3)

        var viewPager = binding.ViewPager
        viewPager.adapter = ViewPagerAdapter(HeaderList, DescriptionList, ImageList, PaginationList)

        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position == 2) {
                binding.buttonNext.text = getString(R.string.onboard_button3)
            }
            else{
                binding.buttonNext.text = getString(R.string.onboard_button1)
            }
        }
        })

        binding.buttonNext.setOnClickListener {
            IndexCurrentPage = viewPager.currentItem
            if(IndexCurrentPage < 2) {
                ++IndexCurrentPage
                viewPager.setCurrentItem(IndexCurrentPage, true)
            } else if (IndexCurrentPage == 2) {

                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.skip.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun addToList(header: String, description: String, image: Int, pagImage: Int) {
        HeaderList.add(header)
        DescriptionList.add(description)
        ImageList.add(image)
        PaginationList.add(pagImage)

    }
}