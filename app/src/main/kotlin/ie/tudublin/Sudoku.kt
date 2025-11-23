package ie.tudublin

class Sudoku(private val rows: Array<String>) {
    private var solved = false
    private val grid = IntArray(81)
    private var iterations = 0
    private val maxIterations = 2000000

    init {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                grid[9 * i + j] = rows[i][j].toString().toInt()
            }
        }
    }

    fun solve(): Boolean {
        iterations = 0
        solved = false
        placeNumber(0)
        return solved
    }

    fun getBoard(): Array<IntArray> {
        val board = Array(9) { IntArray(9) }
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                board[i][j] = grid[i * 9 + j]
            }
        }
        return board
    }

    private fun placeNumber(pos: Int) {
        iterations++
        if (iterations > maxIterations) return
        if (solved) return
        if (pos == 81) {
            solved = true
            return
        }

        if (grid[pos] > 0) {
            placeNumber(pos + 1)
            return
        }

        for (n in 1..9) {
            if (checkValidity(n, pos % 9, pos / 9)) {
                grid[pos] = n
                placeNumber(pos + 1)
                if (solved) return
                grid[pos] = 0
            }
        }
    }

    private fun checkValidity(v: Int, x: Int, y: Int): Boolean {
        for (i in 0 until 9) {
            if (grid[y * 9 + i] == v || grid[i * 9 + x] == v) {
                return false
            }
        }

        val startX = (x / 3) * 3
        val startY = (y / 3) * 3
        for (i in startY until startY + 3) {
            for (j in startX until startX + 3) {
                if (grid[i * 9 + j] == v) {
                    return false
                }
            }
        }
        return true
    }
}
