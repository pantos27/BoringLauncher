package com.pantos27.boringlauncher

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pantos27.boringlauncher.adapters.MyAppInfoRecyclerViewAdapter
import com.pantos27.boringlauncher.data.AppInfo
import ua.at.tsvetkov.util.Log


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [AppInfoListFragment.OnListFragmentInteractionListener] interface.
 */
class AppInfoListFragment : Fragment() {

    enum class Mode{
        Favs,Lex,Recent
    }

    // TODO: Customize parameters
    private var mode : Mode  = Mode.Lex

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")
        arguments?.let {
            mode = it.getSerializable(ARG_MODE) as Mode
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? = inflater.inflate(R.layout.fragment_appinfo_list, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                Log.d("getLauncherActivities-pre")
                adapter = MyAppInfoRecyclerViewAdapter(getLauncherActivities(context.packageManager), listener)
                Log.d("getLauncherActivities-post")
            }
        }    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentClick(item: AppInfo?)
        fun onListFragmentLongPress(item: AppInfo?)
    }

    companion object {

        const val ARG_MODE = "fragment mode"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(mode: Mode) =
                AppInfoListFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_MODE,mode)
                    }
                }
    }
}
