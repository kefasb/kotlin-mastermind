package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    println("Secret is: $secret")
    println("Guess is:  $guess")

    fun evaluateRight(secret: String, guess: String) =
        secret.zip(guess).filter { pair -> pair.first == pair.second }.count()

    fun evaluateWrong(secret: String, guess: String): Int {
        val (leftSecret, leftGuess) = secret.zip(guess).filterNot { pair -> pair.first == pair.second }
            .unzip()
        val leftSecretMutable = ArrayList(leftSecret)

        for (char in leftGuess) {
            leftSecretMutable.remove(char)
        }

        return leftSecret.count() - leftSecretMutable.count()
    }

    return Evaluation(
        evaluateRight(secret, guess),
        evaluateWrong(secret, guess)
    )
}
