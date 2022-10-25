import fr.esiee.simplewebserver.WebResponse
import fr.esiee.simplewebserver.WebRequest
import fr.esiee.simplewebserver.SimpleWebServer
import fr.esiee.simplewebserver.services.{SimpleWebService, UserWebService}

object MyWebServer {
  def main(args: Array[String]): Unit = {

    SimpleWebServer
      .create()
      .listenPort(8082)
      //.withService(service)
      //.withService("/api/users", UserWebService())
      .runForever()

  }
}

/*class MyWebService extends SimpleWebService {
  override def get(request: WebRequest): Unit = {
    WebResponse.createOkWithBody("hello", "text/html")
  }
}*/
