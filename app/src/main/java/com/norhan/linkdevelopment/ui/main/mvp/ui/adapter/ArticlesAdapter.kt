package com.norhan.linkdevelopment.ui.main.mvp.ui.adapter

import android.view.View
import com.norhan.linkdevelopment.R
import com.norhan.linkdevelopment.utils.Utils
import com.norhan.linkdevelopment.utils.base.BaseHolder
import com.norhan.linkdevelopment.utils.base.BaseRecyclerViewAdapter
import com.norhan.linkdevelopment.utils.remoteDataProvider.entity.articles.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*


class ArticlesAdapter(list: MutableList<Article>, private val picasso: Picasso) :
    BaseRecyclerViewAdapter<Article>(list) {

    override fun getLayoutId(viewType: Int) = R.layout.item_article

    override fun getHolder(v: View, viewType: Int) = ArticlesViewHolder(v)

    inner class ArticlesViewHolder(itemView: View) : BaseHolder<Article>(itemView) {
        override fun setData(data: Article) {
            with(itemView) {
                picasso.load(data.urlToImage).placeholder(R.drawable.ic_placeholder).into(iv_article_image)
                tv_title.text = data.title
                tv_author.text = tv_author.context.getString(R.string.by_author, data.author)
                tv_date.text = Utils.getFormattedDate(data.publishedAt)
            }
        }
    }
}