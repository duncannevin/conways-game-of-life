package convertFlow

package actors.convert_flow
import akka.NotUsed
import akka.stream.scaladsl.Flow
import play.api.libs.json.{JsValue, Json, Writes}
import _root_.actors.LifeActor.GridOut

object ResponseConvertFlow {
  final case class LifeResponse(status: String,
                                message: Option[String],
                                grid: Option[List[List[Int]]])

  implicit val videoResponseWrites: Writes[LifeResponse] =
    Json.format[LifeResponse]

  def responseToJson(msg: Any): JsValue = {
    val cr = msg match {
      case GridOut(Some(grid), status) =>
        LifeResponse("success", Some(status), Some(grid))
      case _ => LifeResponse("failed", None, None)
    }
    Json.toJson[LifeResponse](cr)
  }

  def apply(): Flow[Any, JsValue, NotUsed] =
    Flow[Any].map(responseToJson)
}
