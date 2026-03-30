}package com.socialsparks.app

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    // UI references
    private lateinit var etTimeOfDay: EditText
    private lateinit var btnGetSpark: Button
    private lateinit var btnReset: Button
    private lateinit var cardResult: CardView
    private lateinit var cardError: CardView
    private lateinit var tvSuggestion: TextView
    private lateinit var tvError: TextView

    companion object {
        private const val TAG = "SocialSparks"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialise views
        etTimeOfDay = findViewById(R.id.etTimeOfDay)
        btnGetSpark = findViewById(R.id.btnGetSpark)
        btnReset = findViewById(R.id.btnReset)
        cardResult = findViewById(R.id.cardResult)
        cardError = findViewById(R.id.cardError)
        tvSuggestion = findViewById(R.id.tvSuggestion)
        tvError = findViewById(R.id.tvError)

        Log.d(TAG, "MainActivity created successfully")

        // Button listeners
        btnGetSpark.setOnClickListener {
            Log.d(TAG, "Get Spark button clicked")
            handleSparkRequest()
        }

        btnReset.setOnClickListener {
            Log.d(TAG, "Reset button clicked")
            resetApp()
        }
    }

    /**
     * Handles the user's time-of-day input and displays the appropriate spark suggestion.
     */
    private fun handleSparkRequest() {
        val input = etTimeOfDay.text.toString().trim()
        Log.d(TAG, "User input: '$input'")

        // Error handling: empty input
        if (input.isEmpty()) {
            showError(
                "Don't be shy! ✨ Enter a time of day so we can spark your social life. " +
                        "Try something like 'Morning' or 'Afternoon'!"
            )
            Log.w(TAG, "Empty input submitted")
            return
        }

        // Get the spark suggestion using if/else if statements
        val suggestion = getSuggestion(input)

        if (suggestion != null) {
            showSuggestion(suggestion)
            Log.d(TAG, "Suggestion shown: $suggestion")
        } else {
            // Error handling: unrecognised input
            showError(
                "Hmm, we don't recognise '$input' yet! 🤔\n\n" +
                        "Please try one of these:\n" +
                        "• Morning\n• Mid-morning\n• Afternoon\n" +
                        "• Afternoon Snack Time\n• Dinner\n• Night\n\n" +
                        "You've got this — give it another go! 💜"
            )
            Log.w(TAG, "Unrecognised input: '$input'")
        }
    }

    /**
     * Returns a social spark suggestion based on the time of day.
     * Uses if/else if statements as required by the assignment.
     */
    private fun getSuggestion(input: String): String? {
        // Normalise input: lowercase and trim for flexible matching
        val normalised = input.lowercase().trim()

        return if (normalised == "morning") {
            "☀️ Good Morning Spark!\n\nSend a warm 'Good morning' text to a family member. " +
                    "It takes 10 seconds and could make their whole day brighter!"

        } else if (normalised == "mid-morning" || normalised == "mid morning") {
            "🙏 Mid-Morning Spark!\n\nReach out to a colleague with a quick 'Thank you' message. " +
                    "Gratitude is contagious — spread some today!"

        } else if (normalised == "afternoon") {
            "😄 Afternoon Spark!\n\nShare a funny meme or an interesting link with a friend. " +
                    "A little laugh goes a long way to keeping connections alive!"

        } else if (normalised == "afternoon snack time") {
            "💬 Snack Time Spark!\n\nSend a quick 'thinking of you' message to someone special. " +
                    "It doesn't need to be long — just let them know they're on your mind!"

        } else if (normalised == "dinner") {
            "📞 Dinner Spark!\n\nCall a friend or relative for a 5-minute catch-up. " +
                    "Put down the phone apps and pick up a real conversation — you'll love it!"

        } else if (normalised == "after dinner" || normalised == "night" ||
            normalised == "after dinner / night" || normalised == "evening") {
            "💜 Evening Spark!\n\nLeave a thoughtful comment on a friend's social media post. " +
                    "Show them you're paying attention — it means more than you think!"

        } else {
            // Return null for unrecognised input (handled by caller)
            null
        }
    }

    /**
     * Shows the suggestion card and hides the error card.
     */
    private fun showSuggestion(suggestion: String) {
        tvSuggestion.text = suggestion
        cardResult.visibility = View.VISIBLE
        cardError.visibility = View.GONE
        Log.d(TAG, "Suggestion card shown")
    }

    /**
     * Shows the error card and hides the suggestion card.
     */
    private fun showError(message: String) {
        tvError.text = message
        cardError.visibility = View.VISIBLE
        cardResult.visibility = View.GONE
        Log.d(TAG, "Error card shown: $message")
    }

    /**
     * Resets the app to its initial state.
     */
    private fun resetApp() {
        etTimeOfDay.text.clear()
        cardResult.visibility = View.GONE
        cardError.visibility = View.GONE
        tvSuggestion.text = ""
        tvError.text = ""
        Log.d(TAG, "App reset to initial state")
    }
}