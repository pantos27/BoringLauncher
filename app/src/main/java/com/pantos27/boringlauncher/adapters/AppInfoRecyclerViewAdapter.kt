package com.pantos27.boringlauncher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import com.pantos27.boringlauncher.AppInfoListFragment.OnListFragmentInteractionListener
import com.pantos27.boringlauncher.R
import com.pantos27.boringlauncher.data.LauncherItem

import kotlinx.android.synthetic.main.fragment_appinfo.view.*

/**
 *
 */
class AppInfoRecyclerViewAdapter(
        private val mValues: List<LauncherItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<AppInfoRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private val mOnClickLongListener: View.OnLongClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as LauncherItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentClick(item)
        }

        mOnClickLongListener = View.OnLongClickListener { v ->
            val item = v.tag as LauncherItem
            mListener?.onListFragmentLongPress(item)
            //return -handled
            true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_appinfo, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.label
        holder.mContentView.text = item.pkg

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
            setOnLongClickListener(mOnClickLongListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.app_title
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
