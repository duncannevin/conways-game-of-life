package actors.messages

import play.api.libs.json.Json

object UpdateGrid {
  implicit val updateGridFormat = Json.format[UpdateGrid]
}

case class UpdateGrid(x: Int, y: Int)
