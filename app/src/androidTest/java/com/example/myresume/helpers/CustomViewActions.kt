package com.example.myresume.helpers

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import kotlinx.android.synthetic.main.resume_row.view.*
import org.hamcrest.Description
import org.hamcrest.Matcher


object MyViewAction {
    fun headerAtPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder =
                    view.findViewHolderForAdapterPosition(position)
                        ?:
                        return false
                return itemMatcher.matches(viewHolder.itemView.header)
            }
        }
    }

    fun descAtPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder =
                    view.findViewHolderForAdapterPosition(position)
                        ?:
                        return false
                return itemMatcher.matches(viewHolder.itemView.desc)
            }
        }
    }
}