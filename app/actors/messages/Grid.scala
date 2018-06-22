package actors.messages

class Grid {
  var table: List[List[Int]] = List.empty[List[Int]]

  def createRow(len: Int): List[Int] =
    List.fill(len)(0)

  def createTable(dimensions: (Int, Int)): Unit =
    this.table = List.fill(dimensions._1)(createRow(dimensions._2))

  // Not being used currently
  def rearangeTable(pos: (Int, Int)) = pos match {
    // add a row to top
    case (rowPos, _) if rowPos - 1 < 0 =>
      table = createRow(table.head.length) :: table
    // add a row to bottom
    case (rowPos, _) if rowPos + 1 > table.length =>
      table = table :+ createRow(table.head.length)
    // add a new cell to end of each row
    case (_, cellPos) if cellPos - 1 < 0 =>
      table = table.map(row => row :+ 0)
    // add a new cell to the beginning of each row
    case (_, cellPos) if cellPos + 1 > table.length =>
      table = table.map(row => 0 :: row)
  }

  def neighborsSum(pos: (Int, Int)): Int = {
    // previous row
    val topLeft =
      if (pos._1 > 0 && pos._2 > 0) {
        table(pos._1 - 1)(pos._2 - 1)
      } else 0
    val top =
      if (pos._1 > 0) {
        table(pos._1 - 1)(pos._2)
      } else 0
    val topRight =
      if (pos._1 > 0 && pos._2 < table.head.length - 1) {
        table(pos._1 - 1)(pos._2 + 1)
      } else 0

    // own row
    val right =
      if (pos._2 < table.head.length - 1) {
        table(pos._1)(pos._2 + 1)
      } else 0
    val left =
      if (pos._2 > 0) {
        table(pos._1)(pos._2 - 1)
      } else 0

    // next row
    val bottomLeft =
      if (pos._1 < table.length - 1 && pos._2 > 0) {
        table(pos._1 + 1)(pos._2 - 1)
      } else 0
    val bottom =
      if (pos._1 < table.length - 1) {
        table(pos._1 + 1)(pos._2)
      } else 0
    val bottomRight =
      if (pos._1 < table.length - 1 && pos._2 < table.head.length - 1) {
        table(pos._1 + 1)(pos._2 + 1)
      } else 0

    List(topLeft, top, topRight, right, bottomRight, bottom, bottomLeft, left).sum
  }

  def liveOrDie(cellValue: Int, cellPosition: (Int, Int)) = cellValue match {

    case cell if cell == 1 => // live cell
      cellPosition match {
        // Any live cell with fewer than two live neighbors dies, as if by under population.
        case cellPos if neighborsSum(cellPos) < 2 => 0
        // Any live cell with two or three live neighbors lives on to the next generation.
        case cellPos if neighborsSum(cellPos) <= 3 => 1
        // Any live cell with more than three live neighbors dies, as if by overpopulation.
        case cellPos if neighborsSum(cellPos) > 3 => 0
      }
    case cell if cell == 0 => // dead cell
      cellPosition match {
        // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        case cellPos if neighborsSum(cellPos) == 3 => 1
        case _                                     => 0
      }
  }

  def processGrid = this.table = this.table.zipWithIndex.map { row =>
    row._1.zipWithIndex.map { cell =>
      liveOrDie(cell._1, (row._2, cell._2))
    }
  }

  def updateGrid(x: Int, y: Int, grid: Grid): Unit = {
    this.table = grid.table.zipWithIndex.map {
      case (l, rowPos) if rowPos == x =>
        l.zipWithIndex.map {
          case (cell, cellPos) if cellPos == y => setOpposite(cell)
          case cell                            => cell._1
        }
      case row => row._1
    }
  }

  private def setOpposite(cell: Int): Int = cell match {
    case cell if cell == 1 => 0
    case cell if cell == 0 => 1
  }
}
