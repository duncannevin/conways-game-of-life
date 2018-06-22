package controllers

import actors.LifeActor
import akka.actor.ActorSystem
import akka.stream.Materializer
import com.google.inject.Inject
import convertFlow.RequestConvertFlow
import convertFlow.actors.convert_flow.ResponseConvertFlow
import play.api.Environment
import play.api.libs.json.JsValue
import play.api.libs.streams.ActorFlow
import play.api.mvc._

class LifeController @Inject()(implicit val system: ActorSystem,
                               implicit val mat: Materializer,
                               environment: Environment)
    extends InjectedController {

  private type WSMessage = JsValue

  def lifeSocket: WebSocket = WebSocket.accept[WSMessage, WSMessage] { _ =>
    RequestConvertFlow()
      .via(
        ActorFlow.actorRef { out =>
          LifeActor
            .props(out)
        }
      )
      .via(ResponseConvertFlow())
  }
}
