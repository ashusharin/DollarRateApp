package com.shusharin.dollarrateapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shusharin.dollarrateapp.R

class RateAdapter(private val retry: Retry) : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

    private val rates = ArrayList<RateUi>()

    fun update(new: List<RateUi>) {
        rates.clear()
        rates.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder =
        when (viewType) {
            BASE -> RateViewHolder.Base(LayoutInflater.from(parent.context)
                .inflate(R.layout.rate_layout, parent, false))
            FAIL -> RateViewHolder.Fail(LayoutInflater.from(parent.context)
                .inflate(R.layout.fail_fullscreen, parent, false), retry)
            else -> RateViewHolder.Progress(LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_fullscreen, parent, false))
        }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) =
        holder.bind(rates[position])

    override fun getItemCount(): Int = rates.size


    override fun getItemViewType(position: Int) = when (rates[position]) {
        is RateUi.Base -> BASE
        is RateUi.Fail -> FAIL
        is RateUi.Progress -> PROGRESS
    }

    abstract class RateViewHolder(view: View) : RecyclerView.ViewHolder(view){
        open fun bind(rate: RateUi) {}
        class Base(view: View) : RateViewHolder(view),View.OnClickListener {
            private val valueRate = itemView.findViewById<TextView>(R.id.value_rate)
            private val dateRate = itemView.findViewById<TextView>(R.id.date_rate)

            init {
                itemView.setOnClickListener(this)
            }

            override fun bind(rate: RateUi) {
                rate.map(object : RateUi.StringMapper {
                    override fun setupText(
                        value: String,
                        data: String,
                    ) {
                        valueRate.text = value
                        dateRate.text = data
                    }
                })
            }

            override fun onClick(p0: View?) {
                Snackbar.make(itemView, "доллар ${valueRate.text}!", Snackbar.LENGTH_SHORT).show()
            }
        }

        class Fail(view: View, private val retry: Retry) : RateViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)
            override fun bind(rate: RateUi) {
                rate.map(object : RateUi.StringMapper {
                    override fun setupError(textError: String) {
                        message.text = textError
                    }
                })
                button.setOnClickListener { retry.tryAgain() }
            }

        }

        class Progress(view: View) : RateViewHolder(view)
    }

    interface Retry {
        fun tryAgain()
    }

    companion object {
        private val BASE = 0
        private val FAIL = 1
        private val PROGRESS = 2
    }
}

