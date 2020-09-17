package com.orly.whispers.ui.replies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orly.whispers.R
import com.orly.whispers.data.model.RepliesWhispers
import com.orly.whispers.databinding.ItemWhisperBinding
import com.orly.whispers.util.DateUtil

class RepliesAdapter :
    ListAdapter<RepliesWhispers, RepliesAdapter.RepliesViewHolder>(RepliesWhisperDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepliesViewHolder =
        RepliesViewHolder(
            ItemWhisperBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RepliesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RepliesViewHolder(
        private val binding: ItemWhisperBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("RestrictedApi")
        fun onBind(whispers: RepliesWhispers) {
            with(binding) {
                Glide
                    .with(this.root)
                    .load(whispers.url)
                    .placeholder(R.mipmap.icon_placeholder)
                    .into(this.ivWhisper)
                this.tvMe.text = whispers.me2.toString()
                this.tvReplies.text = whispers.replies.toString()
                this.tvTime.text = DateUtil.timeElapsed(whispers.ts)
                if (whispers.replies > 0)
                    this.cardWhisper.setOnClickListener {
                        navigateToReplies(whispers.nickname, whispers.wid, this.root)
                    }
            }
        }

        private fun navigateToReplies(nickname: String, wid: String, view: View) {
            val direction = RepliesFragmentDirections
                .actionRepliesToReplies(view.context.getString(R.string.response_to, nickname), wid)
            view.findNavController().navigate(direction)
        }
    }
}

private class RepliesWhisperDiff : DiffUtil.ItemCallback<RepliesWhispers>() {
    override fun areItemsTheSame(oldItem: RepliesWhispers, newItem: RepliesWhispers): Boolean {
        return oldItem.wid == newItem.wid
    }

    override fun areContentsTheSame(oldItem: RepliesWhispers, newItem: RepliesWhispers): Boolean {
        return oldItem == newItem
    }
}