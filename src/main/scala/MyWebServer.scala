import fr.esiee.simplewebserver.SimpleWebServer
import fr.esiee.simplewebserver.SimpleWebService
import fr.esiee.simplewebserver.WebResponse
import fr.esiee.simplewebserver.WebRequest

object MyWebServer {
  def main(args: Array[String]): Unit = {
    val service = new MyWebService

    SimpleWebServer
      .create()
      .listenPort(8081)
      .withService(service)
      .runForever()
  }
}

class MyWebService extends SimpleWebService {
  // utilisateur qui va réécrire les fonctions comme ils le souhaitent
  // ex ci-dessous, néanmoins fonction createOkwithBody n'est pas encore def
  override def get(request: WebRequest): WebResponse = {
    WebResponse(r_statusCode = 200, r_contentType =  "application/json", r_content = """{"response": "hello"}""")
    //WebResponse(r_statusCode = 200, r_contentType =  "text/html", r_content = "<b> Hello <b/>")
  }
}
