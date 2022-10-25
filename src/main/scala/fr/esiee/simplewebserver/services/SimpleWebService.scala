package fr.esiee.simplewebserver.services

import fr.esiee.simplewebserver.{WebRequest, WebResponse}

trait SimpleWebService:
  def get(request: WebRequest): WebResponse
  def post(request: WebRequest): WebResponse
  def put(request: WebRequest): WebResponse
  def delete(request: WebRequest): WebResponse
