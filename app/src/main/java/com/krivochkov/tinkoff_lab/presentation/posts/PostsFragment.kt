package com.krivochkov.tinkoff_lab.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.krivochkov.tinkoff_lab.R
import com.krivochkov.tinkoff_lab.domain.model.Post
import com.krivochkov.tinkoff_lab.presentation.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
abstract class PostsFragment : Fragment() {

    abstract val category: String

    private val viewModel by viewModels<PostsViewModel>()

    private lateinit var postGif: ImageView
    private lateinit var postDescription: TextView
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var errorButton: Button
    private lateinit var errorLayout: ConstraintLayout
    private lateinit var buttonsLayout: LinearLayout
    private lateinit var postLayout: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postGif = view.findViewById(R.id.post_image)
        postDescription = view.findViewById(R.id.post_description)
        nextButton = view.findViewById(R.id.next_button)
        prevButton = view.findViewById(R.id.prev_button)
        progressBar = view.findViewById(R.id.progress_bar)
        errorButton = view.findViewById(R.id.error_button)
        errorLayout = view.findViewById(R.id.error_layout)
        buttonsLayout = view.findViewById(R.id.buttons_layout)
        postLayout = view.findViewById(R.id.post_layout)

        nextButton.setOnClickListener {
            deactivateAllButton()
            viewModel.nextPost(category)
        }

        prevButton.setOnClickListener {
            deactivateAllButton()
            viewModel.prevPost(category)
        }

        errorButton.setOnClickListener {
            hideError()
            viewModel.currentPost(category)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                render(state)
            }
        }

        viewModel.currentPost(category)
    }

    private fun render(state: ScreenState) {
        when (state) {
            is ScreenState.FirstPostLoaded -> {
                activateButton(nextButton, R.drawable.active_next_button)
                showPost(state.result)
            }

            is ScreenState.NonFirstPostLoaded -> {
                activateButton(nextButton, R.drawable.active_next_button)
                activateButton(prevButton, R.drawable.active_prev_button)
                showPost(state.result)
            }

            is ScreenState.Loading -> {
                showProgressBar()
                hidePost()
            }

            is ScreenState.Failure -> {
                showError()
            }

            is ScreenState.Init -> {
                deactivateAllButton()
                hideProgressBar()
            }
        }
    }

    private fun showError() {
        errorButton.isEnabled = true
        errorLayout.visibility = View.VISIBLE
        postLayout.visibility = View.GONE
        buttonsLayout.visibility = View.GONE
    }

    private fun hideError() {
        errorButton.isEnabled = false
        errorLayout.visibility = View.GONE
        postLayout.visibility = View.VISIBLE
        buttonsLayout.visibility = View.VISIBLE
    }

    private fun deactivateAllButton(){
        deactivateButton(nextButton, R.drawable.inactive_next_button)
        deactivateButton(prevButton, R.drawable.inactive_prev_button)
    }

    private fun deactivateButton(button: ImageButton, resource: Int) {
        button.isEnabled = false
        button.setImageResource(resource)
    }

    private fun activateButton(button: ImageButton, resource: Int) {
        button.isEnabled = true
        button.setImageResource(resource)
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun showPost(post: Post) {
        postDescription.text = post.description
        Glide.with(this)
            .asGif()
            .load(post.gifURL)
            .error(R.drawable.errorholder)
            .centerCrop()
            .into(postGif)
    }

    private fun hidePost() {
        postDescription.text = ""
        postGif.setImageBitmap(null)
    }
}