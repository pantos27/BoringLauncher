package com.pantos27.boringlauncher


import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.pantos27.boringlauncher.data.AppInfo
import com.pantos27.boringlauncher.data.LauncherItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

import ua.at.tsvetkov.util.Log

class MainActivity : AppCompatActivity(), AppInfoListFragment.OnListFragmentInteractionListener {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        registerPackageUpdateReceivers()
    }

    private fun registerPackageUpdateReceivers() {

        IntentFilter().run {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addAction(Intent.ACTION_PACKAGE_REPLACED)
            addDataScheme("package")
            registerReceiver(PackageActionReceiver,this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterPackageUpdateReceivers()
    }

    private fun unregisterPackageUpdateReceivers() {
        unregisterReceiver(PackageActionReceiver)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    //disable back presses
    override fun onBackPressed() {}

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("onKey $event")
        return super.onKeyUp(keyCode, event)
    }

    override fun onListFragmentClick(item: LauncherItem?) {
        Log.d("onListFragmentClick: $item")
        item?.let {
            startLauncherItem(this,item)
        }
    }
    override fun onListFragmentLongPress(item: LauncherItem?) {
        Log.d("onListFragmentLongPress $item")
        //todo: open context menu
        item?.let {
            gotToAppSettingsActivity(this,item.pkg)
        }
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            Log.d("position: $position")
            // getItem is called to instantiate the fragment for the given page.
            return when(position) {
                0 -> AppInfoListFragment.newInstance(AppInfoListFragment.Mode.Lex)
                1 -> AppInfoListFragment.newInstance(AppInfoListFragment.Mode.Recent)
                2 -> AppInfoListFragment.newInstance(AppInfoListFragment.Mode.Favs)
                else -> AppInfoListFragment.newInstance(AppInfoListFragment.Mode.Lex)
            }
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private const val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
