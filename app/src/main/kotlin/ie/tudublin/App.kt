package ie.tudublin

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: java -jar sudoku.jar <input_file>")
        return
    }

    val fileName = args[0]
    val rows = readBoardFromFile(fileName)
    
    if (rows == null) {
        println("Error reading file: '$fileName'")
        return
    }

    println("Starting Grid:")
    printBoard(rows)

    val sudoku = Sudoku(rows)
    val solved = sudoku.solve()

    if (solved) {
        println("\nSolution:")
        printBoard(sudoku.getBoard())
    } else {
        println("Could not find a solution")
    }
}


fun readBoardFromFile(fileName: String): Array<String>? {
    return try {
        File(fileName).readLines().take(9).toTypedArray()
    } catch (e: Exception) {
        null
    }
}


fun printBoard(rows: Array<String>) {
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            print("${rows[i][j]} ")
            if (j == 2 || j == 5) print("| ")
        }
        println()
        if (i == 2 || i == 5) println("------+-------+------")
    }
}

fun printBoard(board: Array<IntArray>) {
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            print("${board[i][j]} ")
            if (j == 2 || j == 5) print("| ")
        }
        println()
        if (i == 2 || i == 5) println("------+-------+------")
    }
}
