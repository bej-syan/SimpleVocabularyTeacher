import javax.inject._
import play.api.http.DefaultHttpErrorHandler
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.routing.Router
import scala.concurrent._

class ErrorHandler @Inject()(
                              env: Environment,
                              config: Configuration,
                              sourceMapper: OptionalSourceMapper,
                              router: Provider[Router])
  extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  override protected def onNotFound(request: RequestHeader, message: String): Future[Result] = {
    Future.successful {
      NotFound("Could not find " + request)
    }
  }

}

/**
  * Override this default mechanism by overriding Play's DefaultHttpErrorHandler, which is an entry point for
  * customizing the default error-handling concerns.
  *
  * The `DefaultHttpErrorHandler` provides default behavior through these method:
  * - onBadRequest        : Handles 400 Bad Request client errors
  * - onForbidden         : Handles 403 Forbidden client errors
  * - onNotFound          : Handles 404 Not Found client errors
  * - onOtherClientError  : Handles any other type of client errors
  *
  * - logServerError      : Specifies how to log server errors
  * - onDevServerError    : Specifies how to display server errors during development
  * - onProdServerError   : Specifies how to display server errors in production mode.
  */