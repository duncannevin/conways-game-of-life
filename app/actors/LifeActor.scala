package actors

import actors.messages.{Grid, UpdateGrid}
import akka.actor.{Actor, ActorRef, Props}
import com.google.inject.Inject

import scala.collection.mutable
import scala.collection.mutable.Map
import scala.concurrent.Future
import scala.util.Random

object LifeActor {
  case class GetGrid(dimensions: (Int, Int))

  case class Start()

  case class Continue()

  case class Stop()

  case class Reset()

  case class GridOut(grid: Option[List[List[Int]]], status: String)

  def props(out: ActorRef) = Props(new LifeActor(out))
}

class LifeActor @Inject()(out: ActorRef) extends Actor {
  import LifeActor._

  val grid: Grid = new Grid

  override def receive: Receive = {
    case GetGrid(dimensions) =>
      grid.createTable(dimensions)
      out ! GridOut(Some(grid.table), "new-grid")
    case Start() =>
      grid.processGrid
      out ! GridOut(Some(grid.table), "starting")
    case Continue() =>
      grid.processGrid
      out ! GridOut(Some(grid.table), "continuing")
    case Stop() =>
      out ! GridOut(Some(grid.table), "stopping")
    case UpdateGrid(x, y) =>
      grid.updateGrid(x, y, grid)
      out ! GridOut(Some(grid.table), "update-grid")
    case Reset() =>
      grid.createTable((grid.table.length, grid.table.head.length))
      out ! GridOut(Some(grid.table), "reset")
  }
}
