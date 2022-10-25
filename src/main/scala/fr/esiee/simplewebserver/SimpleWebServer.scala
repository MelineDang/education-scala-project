package fr.esiee.simplewebserver

//import WebServerMain.dateTimeFormatter

import java.io.{PrintWriter, StringWriter}
import java.net.ServerSocket
import java.net.Socket
import java.time.ZonedDateTime
import scala.io.Source
import scala.annotation.tailrec
import scala.util.Using
import fr.esiee.simplewebserver.{WebParameter, WebRequest, WebResponse}
import fr.esiee.simplewebserver.services.SimpleWebService

object SimpleWebServer {

  def create(): SimpleWebServerBuilder = SimpleWebServerBuilder(port = None, service = None)

  case class SimpleWebServerBuilder(port: Option[Int], service: Option[SimpleWebService]):
    def listenPort(port: Int): SimpleWebServerBuilder = {
      copy(port = Some(port))
    }
    def withService(service: SimpleWebService): SimpleWebServerBuilder = copy(service = Some(service))
    

    def runForever(): Unit = {

      val unwrappedPort: Int = port match {
        case None => -1
        case Some(port) => port
      }

      println(unwrappedPort)

      @tailrec
      def recursiveRunForever(): Unit = {

        Using(ServerSocket(unwrappedPort).accept()) { sock =>
          //val request = readGetRequestFrom(sock)
          //println(request)
          //val response = call(requete, service)
          //println(">>> Sending response...")
          //sendResponseTo(sock, response, ZonedDateTime.now())
        }.fold(error => println(s">>> client connection failure: ${error.getMessage}"),
          _ => ()
        )
        recursiveRunForever()
      }

      Using(ServerSocket(unwrappedPort).accept()) { server =>
        recursiveRunForever()
      }.get

    }


  def readGetRequestFrom(client: Socket): List[String] = {
    val source = Source.fromInputStream(client.getInputStream)

    source
      .getLines()
      .takeWhile(_.trim.nonEmpty)
      .toList
  }

  def parseParameter(parameter: String): WebParameter = {
    val param = parameter.split('=') // récupération des clés et leur valeur
    WebParameter(param.head, param.last) // format clé-valeur
  }

  def getParameters(parameters: Option[String]): Seq[WebParameter] = {
    parameters
      .map(_.split('&').map(parseParameter).toSeq) // quand on a plusieurs paramètres
      .getOrElse(Seq.empty[WebParameter]) // sinon
  }
  private def parseLineToWebRequest(line: String): WebRequest = {
    val request = line.split(' ') // pour récuperer la request
    val apiRoute = request(1).split('?') // split sur le "?" pour savoir si c'est une recherche
    WebRequest(
      requestType = request(0), // type de la requête (GET, POST, PUT, DELETE)
      route = request(1), // route (user)
      body = None, // pour POST, json comportant ce que nous voulons POST
      parameters = getParameters(if (apiRoute.length > 1) apiRoute.lastOption else None) // obtention des paramètres de la requête
    )
  }


}
