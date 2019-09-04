import com.github.giwiro.app._
import com.github.giwiro.app.admin.courier.web.CourierServlet
import com.github.giwiro.database.SQLiteDatabase.{closeConnection, configureDb}
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    configureDb
    context.mount(new CourierServlet, "/admin/*")
  }

  override def destroy(context: ServletContext) {
    closeConnection
  }
}
