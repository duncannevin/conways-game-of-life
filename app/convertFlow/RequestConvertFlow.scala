package convertFlow

import akka.NotUsed
import akka.stream.scaladsl.Flow
import play.api.libs.json.{JsValue, Json, Reads}
import _root_.actors.LifeActor.{Continue, GetGrid, Start, Stop, Reset}
import _root_.actors.messages.{UpdateGrid}

object RequestConvertFlow {
  final case class VideoRequest(action: String,
                                dimensions: Option[Map[String, Int]],
                                update: Option[UpdateGrid])

  implicit val videoRequestFormat: Reads[VideoRequest] =
    Json.format[VideoRequest]

  def jsonToCompilerCommand(msg: JsValue): Any = {
    Json.fromJson[VideoRequest](msg).asOpt match {
      case Some(VideoRequest(action, Some(dimensions), None))
          if action == "get-grid" =>
        GetGrid((dimensions("x"), dimensions("y")))
      case Some(VideoRequest(action, None, None)) if action == "start" =>
        Start()
      case Some(VideoRequest(action, None, None)) if action == "continue" =>
        Continue()
      case Some(VideoRequest(action, None, Some(updateGrid)))
          if action == "update" =>
        updateGrid
      case Some(VideoRequest(action, None, None)) if action == "reset" =>
        Reset()
      case Some(VideoRequest(action, None, None)) if action == "stop" =>
        Stop()
      case _ => ???
    }
  }

  def apply(): Flow[JsValue, Any, NotUsed] = {
    Flow[JsValue].map(jsonToCompilerCommand)
  }
}
