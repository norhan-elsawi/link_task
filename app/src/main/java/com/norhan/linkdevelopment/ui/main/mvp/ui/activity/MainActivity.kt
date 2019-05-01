package com.norhan.linkdevelopment.ui.main.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.norhan.linkdevelopment.R
import com.norhan.linkdevelopment.di.component.ApplicationComponent
import com.norhan.linkdevelopment.ui.details.mvp.ui.activity.DetailsActivity
import com.norhan.linkdevelopment.ui.main.di.component.DaggerMainComponent
import com.norhan.linkdevelopment.ui.main.di.module.MainModule
import com.norhan.linkdevelopment.ui.main.mvp.contract.MainContract
import com.norhan.linkdevelopment.ui.main.mvp.presenter.MainPresenter
import com.norhan.linkdevelopment.ui.main.mvp.ui.adapter.ArticlesAdapter
import com.norhan.linkdevelopment.utils.base.BaseActivity
import com.norhan.linkdevelopment.utils.base.BaseRecyclerViewAdapter
import com.norhan.linkdevelopment.utils.getColorCompat
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var articlesAdapter: ArticlesAdapter

    @Inject
    lateinit var picasso: Picasso

    private lateinit var menu: Menu

    override val contentView: Int
        get() = R.layout.activity_main

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        initClickListeners()
        initToolBar()
        initSideNavigators()
        initRecyclerView()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.app_name)
        toolbar.setTitleTextColor(getColorCompat(R.color.white))
        toolbar.setNavigationOnClickListener {
            if (!drawer_layout.isDrawerOpen(GravityCompat.START))
                drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    private fun initClickListeners() {
        iv_retry.setOnClickListener {
            presenter.onRetryClicked()
        }
    }

    private fun initRecyclerView() {
        rv_articles.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main_activity_toolbar, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.search) {
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun initSideNavigators() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        nv_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        presenter.onNavigationItemClicked(item.itemId)

        //close navigation drawer
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showNavigationMsg(id: Int) {
        val item = nv_view.menu.findItem(id)
        showMessageInGreen(item.title.toString())
    }

    override fun setUserName(name: String) {
        val headerView = nv_view.getHeaderView(0)
        val navUsername = headerView.findViewById(R.id.tv_name) as TextView
        navUsername.text = name
    }

    override fun setArticlesList(list: List<Article>) {
        articlesAdapter = ArticlesAdapter(list.toMutableList(), picasso)
        rv_articles.adapter = articlesAdapter
        articlesAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter
        .OnRecyclerViewItemClickListener<Article> {
            override fun onItemClick(view: View, data: Article, position: Int) {
                presenter.onArticlesItemClicked(data)
            }
        })
    }

    override fun setRetryVisibility(isVisible: Boolean) {
        if (isVisible) {
            if (!::articlesAdapter.isInitialized)
                iv_retry.visibility = View.VISIBLE
        } else
            iv_retry.visibility = View.GONE
    }

    override fun navigateToDetailPage(data: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("article", Gson().toJson(data))
        startActivity(intent)
    }

    override fun resolveDaggerDependency(appComponent: ApplicationComponent?) {
        DaggerMainComponent
            .builder()
            .applicationComponent(appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }
}