At its core, an HTTP request is composed of a `header` and an optional `body` for certain kinds of requests. 

For example:

    GET /welcome HTTP/1.1
    Host: localhost
    Connection: keep-alive
    Cache-Control: max-age=0
    Accept: text/html, application/xhtml+xml, application/xml;q=0.9
    User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4)
                AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.125
                Safari/537.36
    DNT: 1
    Accept-Encoding: gzip, deflate, sdch
    Accept-Language: en-US,en;q=0.8,de;q=0.6,fr;q=0.4,nl;q=0.2
    
The first line is particularly interesting. 

It consists of the *method*(GET), and *path*(/welcome), and the *protocol*(HTTP/1.1).

The rest of the header is a collection of header fields that can be used for various purposes, such
as *content negotiation*, *compression*, *internationalization*, and so on.


In the **Scala Play API**, all of this core information is represented by the **play.api.mvc.RequestHeader** trait.

The **RequestHeader** is one of the cornerstones of *Play*, and it's both used by *Play* internally
and available to us for interpreting and responding to requests. 

The **RequestHeader** exposes all the information in a developer-friendly fashion, and it mainly
consists of the following:

- The request path
- The method
- The query string
- The request headers
- The cookies, including the client-side session cookie and a special "flash" cookie.
- A number of convenience methods that help make sense of some of the headers for content negotiation


Let the router know about the binding by adding `routesImport += "binders.PathBinders._"` to the `build.sbt` file.

*Play*'s route file is turned into a Scala source file and then compiled alongside the sources of the application.
When the Scala compiler finds a route that specifies a given type, it will try to find the appropriate `PathBindable` 
for that type and will complain if it can't find one. 


#### A look under the hood of `Action`s

    trait Action[A] extends EssentialAction {
      def parser: BodyParser[A]
      def apply(request: Request[A]): Future[Result]
    }
    
    trait Request[+A] extends RequestHeader {
      def body: A
    }
    
