package fr.esiee.simplewebserver

//import WebServerMain.dateTimeFormatter

import java.io.{PrintWriter, StringWriter}
import java.net.ServerSocket
import java.net.Socket
import java.time.ZonedDateTime
import scala.io.Source
import scala.annotation.tailrec
import scala.util.Using

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
          val request = readGetRequestFrom(sock)
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
  //case class ImmutableListenPort(port: Int):
  //  def port(port: Int): ImmutableListenPort = copy(port)


  def readGetRequestFrom(client: Socket): List[String] = {
    val source = Source.fromInputStream(client.getInputStream)

    source
      .getLines()
      .takeWhile(_.trim.nonEmpty)
      .toList
  }



  /*def sendResponseTo(client: Socket, now: ZonedDateTime): Unit = {
    val printer = new PrintWriter(client.getOutputStream)

    printer.print("HTTP/1.1 200 OK\r\n")
    printer.print(s"Date: ${dateTimeFormatter.format(now)}\r\n")
    printer.print("Content-Type: text/html\r\n")
    printer.print("\r\n")
    printer.print("<b> Hello</b>")

    printer.flush()
  }*/

}
