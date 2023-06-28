package io.github.lexadiky.pdx.feature.account.login.util

internal class UsernameGenerator {

    private val firstParts = listOf("Ash", "Pika", "Small", "Bulba", "PDX", "Shiny", "Cookie", "Dev", "Anime", "Bizarre", "", "")
    private val joiner = listOf(" ", "", "_")
    private val secondPrefixParts = listOf("Old", "Fresh", "Fast", "Shiny", "Ruby", "Sun", "Moon", "Enjoyed", "", "", "")
    private val thirdSuffixParts = listOf("Trainer", "Pika", "Bulba", "Ketchup", "Solar", "Adventurer", "2023", "")

    fun next(): String {
        return (firstParts.random() + joiner.random() + secondPrefixParts.random() + thirdSuffixParts.random())
            .trim()
    }
}