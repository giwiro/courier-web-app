import com.github.giwiro.server.{ConfigParser, ServerLauncher}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object JettyLauncher extends ServerLauncher[Server] {
  override def configureServer: Server = {
    var config = ConfigParser.getConfig
    val server = new Server(config.port)
    val context = new WebAppContext()
    context setContextPath "/"
    context.setResourceBase("src/main/webapp")
    context.addEventListener(new ScalatraListener)
    context.addServlet(classOf[DefaultServlet], "/")

    server.setHandler(context)
    server
  }

  def main(args: Array[String]): Unit = {
    var server = configureServer
    server.start
    server.join
  }
}
