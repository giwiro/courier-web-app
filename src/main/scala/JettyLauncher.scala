import com.github.giwiro.server.{ConfigParser, ServerLauncher}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ErrorPageErrorHandler}
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object JettyLauncher extends ServerLauncher[Server] {
  override def configureServer: Server = {
    val config = ConfigParser.getConfig
    val server = new Server(config.port)
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false")

    val errorHandler = new ErrorPageErrorHandler
    errorHandler.addErrorPage(404, "/404.html")
    context.setErrorHandler(errorHandler)

    if (config.env == "production") {
      context.setInitParameter("org.scalatra.environment", "production")
    }

    server.setHandler(context)
    server
  }

  def main(args: Array[String]): Unit = {
    var server = configureServer
    server.start
    server.join
  }
}
