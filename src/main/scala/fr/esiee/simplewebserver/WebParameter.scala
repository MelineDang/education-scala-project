package fr.esiee.simplewebserver

// le WebParameter comporte la clé et sa valeur du paramètre donné en entrée (ici pour le name, age, id)
case class WebParameter(
                         key: String,
                         value: String
                       )
