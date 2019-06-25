package com.example.lentanews.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lentanews.*
import com.example.lentanews.rowtypes.HeaderRowType
import com.example.lentanews.rowtypes.NewsHorizontalRowType
import com.example.lentanews.rowtypes.NewsRowType
import com.example.lentanews.rowtypes.RowType
import kotlinx.android.synthetic.main.fragment_news_list.*
import java.util.*
import android.support.v7.widget.GridLayoutManager
import android.widget.LinearLayout


class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val mainList : ArrayList<RowType> = arrayListOf(
            HeaderRowType("Top7"),
            NewsRowType("item1"),
            NewsRowType("item2"),
            NewsRowType("item3"),
            NewsRowType("item4"),
            HeaderRowType("Last24"),
            NewsHorizontalRowType("itemH1", "itemH2"),
            NewsHorizontalRowType("itemH3", "itemH4"),
            HeaderRowType("All"),
            NewsHorizontalRowType("itemH1", "itemH2"),
            NewsHorizontalRowType("itemH3", "itemH4")

        )

        val recyclerView: RecyclerView = view!!.findViewById(R.id.main_recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)

        val recyclerViewAdapter = RecyclerViewAdapter(mainList)
        recyclerView.adapter = recyclerViewAdapter
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager

        return view
    }

    fun onClick(v: View, text: String) {
        val bundle = Bundle()
        bundle.putString("header", text)
        val newsListFragment = NewsListFragment()
        newsListFragment.setArguments(bundle)
        val activity = v.context as AppCompatActivity

        activity.getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment_container,
            newsListFragment
        ).addToBackStack(null).commit()
    }
}