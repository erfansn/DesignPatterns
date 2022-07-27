package creational

val notations = listOf("qa5", "pb4")

val gameState = notations.map { Piece.fromNotation(it) }

sealed class Piece(open val position: String) {
    companion object {
        fun fromNotation(piece: String): Piece {
            val pieceType = piece[0]
            val position = piece.drop(1)
            return when (pieceType) {
                'q' -> Queen(position)
                'p' -> Pawn(position)
                else -> error("Unknown piece!")
            }
        }
    }
}

data class Queen(override val position: String) : Piece(position)
data class Pawn(override val position: String) : Piece(position)
