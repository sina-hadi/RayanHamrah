package com.codinginflow.rayanhamrah.adapter

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codinginflow.rayanhamrah.ui.stock.*

class StockPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> NamadInfoFragment()
            1 -> NazerMessageFragment()
            2 -> OpenOrderFragment()
            3 -> MyOrderFragment()
            4 -> BookOrderFragment()
            5 -> NoteFragment()
            else -> NamadInfoFragment()
        }
    }
}