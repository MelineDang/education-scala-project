package fr.esiee.simplewebserver

// dans une WebRequest nous souhaitons avoir son type (GET, POST, PUT, DELETE), la route (user..),
// le body en JSON pour le POST, et les paramètres suplémentaires (name, age, id)
case class WebRequest(
                       requestType: String,
                       route: String,
                       body: Option[String],
                       parameters: Seq[WebParameter]
                     )


}
