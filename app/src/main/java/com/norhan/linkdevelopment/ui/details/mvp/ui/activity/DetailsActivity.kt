package com.norhan.linkdevelopment.ui.details.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import com.norhan.linkdevelopment.R
import com.norhan.linkdevelopment.di.component.ApplicationComponent
import com.norhan.linkdevelopment.ui.details.di.component.DaggerDetailsComponent
import com.norhan.linkdevelopment.ui.details.di.module.DetailsModule
import com.norhan.linkdevelopment.ui.details.mvp.contract.DetailsContract
import com.norhan.linkdevelopment.ui.details.mvp.presenter.DetailsPresenter
import com.norhan.linkdevelopment.utils.Utils
import com.norhan.linkdevelopment.utils.base.BaseActivity
import com.norhan.linkdevelopment.utils.getColorCompat
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity<DetailsPresenter>(), DetailsContract.View {

    @Inject
    lateinit var picasso: Picasso

    override val contentView: Int
        get() = R.layout.activity_details

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        initToolBar()
        presenter.onViewReady(
            Gson().fromJson(
                getIntent()!!.getStringExtra("article"),
                Article::class.java
            )
        )
    }

    override fun bindDataToView(article: Article) {
        picasso.load(article.urlToImage).placeholder(R.drawable.ic_placeholder).into(iv_article_image)
        tv_title.text = article.title
        tv_author.text = tv_author.context.getString(R.string.by_author, article.author)
        tv_date.text = Utils.getFormattedDate(article.publishedAt)
        tv_description.text = article.description
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.app_name)
        toolbar.setTitleTextColor(getColorCompat(R.color.white))
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun resolveDaggerDependency(appComponent: ApplicationComponent?) {
        DaggerDetailsComponent
            .builder()
            .applicationComponent(appComponent)
            .detailsModule(DetailsModule(this))
            .build()
            .inject(this)
    }

}