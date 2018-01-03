import javax.inject.Inject

import play.api.http.HttpFilters
import play.filters.gzip.GzipFilter
import play.filters.headers.SecurityHeadersFilter

// Injects the `GzipFilter`, which gzips responses sent to the client to speed things up a little
class Filters @Inject()(gzip: GzipFilter)
    extends HttpFilters {   // The `HttpFilters` trait sets up a filter chain with the filters you specify.

  // Specifies the filters you'd like to apply in the order they should be applied. Play's `SecurityHeadersFilter` adds
  // a number of header-based security checks and policies.
  val filters = Seq(gzip, SecurityHeadersFilter())
}


/**
  * Filters are useful when it comes to dealing with cross-cutting concerns, which are more easily handled by hooking
  * them into the request-processing pipeline than dealing with them for each action.
  */