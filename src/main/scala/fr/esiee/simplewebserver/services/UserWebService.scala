package fr.esiee.simplewebserver.services

import fr.esiee.simplewebserver.{SimpleWebService, WebRequest, WebResponse}

case class UserWebService() extends SimpleWebService {
  override def get(request: WebRequest): WebResponse = WebResponse()
  override def post(request: WebRequest): WebResponse = WebResponse()
  override def put(request: WebRequest): WebResponse = WebResponse()
  override def delete(request: WebRequest): WebResponse = WebResponse()
}
