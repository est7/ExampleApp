package com.application.others.tablayoutext

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @CreateDate:     2020/6/12 11:15
 * @Description:    java类作用描述
 * @Author:         LOPER7
 * @Email:          loper7@163.com
 */
class CustomFragmentAdapter(fragment:FragmentActivity) : FragmentStateAdapter(fragment) {


    fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "直播"
            1-> "推荐"
            2-> "热门"
            3-> "追番"
            4-> "抗击肺炎"
            5-> "总之就是非常可爱"
            else-> "PHP"
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return SimpleFragment.newInstance("PAGE $position")
    }

}