package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "render the appSummary page from a new instance of controller" in {
      val controller = new GridController(stubControllerComponents())
      val home = controller.getGrid().apply(FakeRequest(GET, "/summary"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      val resultJson = contentAsJson(home)
      resultJson.toString() mustBe """{"content":"Scala Play Vue Seed"}"""
    }

    "render the appSummary page from the application" in {
      val controller = inject[GridController]
      val home = controller.getGrid().apply(FakeRequest(GET, "/summary"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      val resultJson = contentAsJson(home)
      resultJson.toString() mustBe """{"content":"Scala Play Vue Seed"}"""
    }

    "render the appSummary page from the router" in {
      val request = FakeRequest(GET, "/summary")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      val resultJson = contentAsJson(home)
      resultJson.toString() mustBe """{"content":"Scala Play Vue Seed"}"""
    }
  }
}
