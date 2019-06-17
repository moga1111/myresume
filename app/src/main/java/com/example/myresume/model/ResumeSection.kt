package com.example.myresume.model

class ResumeSection {
    var header: String? = null
    var items: List<String>? = null

    override fun toString(): String {
        val stringBuilder = StringBuilder()

        if (items!!.isNotEmpty()) {
            for (item in items!!) {
                stringBuilder.append(item).append("\n")
            }
        }

        return stringBuilder.toString()
    }
}