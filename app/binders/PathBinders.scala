package binders

import play.api.i18n.Lang
import play.api.mvc.PathBindable

/**
  * Places all binders in one object to simplify importing them into the router
  */
object PathBinders {

  implicit object LangPathBindable extends PathBindable[Lang] {

    /**
      * Implements the `bind` method to read a query fragment as a type
      *
      * // Checks if there's a language for the input value; otherwise return an
      * // error message.
      * Lang.get(value).toRight(s"Language $value is not recognized")
      *
      */
    override def bind(key: String, value: String): Either[String, Lang] =
      Lang.get(value).toRight(s"Language $value is not recognized")

    /**
      * Implements the unbind method to write a type as a path fragment.
      */
    override def unbind(key: String, value: Lang): String =
      value.code
  }

}
