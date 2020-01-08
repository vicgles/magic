package com.bootcamp.magic.Robots

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Adapter.DetailAdapter
import com.bootcamp.magic.Adapter.ViewlHolder
import com.bootcamp.magic.Extensions.isDisplayed
import com.bootcamp.magic.Extensions.scrollToPostionRecyclerView
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.R
import com.bootcamp.magic.View.DetailFragment
import com.bootcamp.magic.View.DetailFragmentTest
import io.mockk.mockk


fun DetailFragmentTest.withDetailFragment(mock: DetailFragmentAct.() -> Unit): DetailFragmentAct{
    return DetailFragmentAct().apply {
        mock()
    }
}

class DetailFragmentAct{

    infix fun initHomeFragment(act: DetailFragmentAct.() -> Unit):DetailFragmentAct{
        launchFragmentInContainer<DetailFragment>(themeResId = R.style.Theme_MaterialComponents_NoActionBar)
        return this.apply(act)
    }

    infix fun checkIf(assert: DetailFragmentAssert.() -> Unit){
        DetailFragmentAssert().apply(assert)
    }


}

class DetailFragmentAssert{

    fun isCardImageDisplayied(){
        R.id.image_item.isDisplayed()
    }

    fun isFavoriteButtonDisplayed(){
        R.id.detail_button_favorite.isDisplayed()
    }
    fun isCloseButtonDisplayed(){
        R.id.detail_button_close.isDisplayed()
    }

    fun scrollToPosition(){
        R.id.detail_scroll_view.scrollToPostionRecyclerView<ViewlHolder>(5)
    }

}