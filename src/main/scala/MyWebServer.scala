import fr.esiee.simplewebserver.SimpleWebService
import fr.esiee.simplewebserver.WebResponse
import fr.esiee.simplewebserver.WebRequest
import fr.esiee.simplewebserver.SimpleWebServer
import fr.esiee.simplewebserver.UserWebService

object MyWebServer {
  def main(args: Array[String]): Unit = {

    val name1 = "John"
    val name2 = "Mary"
    val entry_path = "/user?name=jon&age=32"
    val entry_path_1 = "/users/42"

    // try pour récuperer les données si c'est une recherche
    val requete  = entry_path.split('?')
    val path = requete.head
    // try
    val informations_ext = requete(1).split('&')

    for (x <- informations_ext if x.startsWith("name")){
      val name = x.split('=')(1)
      println(name)
    }
    for (x <- informations_ext if x.startsWith("age")) {
      val age = x.split('=')(1)
      println(age)
    }
    for (x <- informations_ext if x.startsWith("id")) {
      val id = x.split('=')(1)
      println(id)
    }

    val requete2  = entry_path_1.split('/')
    val pathreq = requete2(1)
    val infreq = requete2(2)

    println(s"Hello $name1 and $name2")

    println(pathreq)
    println(infreq)






    //println()


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
