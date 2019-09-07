import com.github.giwiro.app.admin.courier.web.CourierServlet
import com.github.giwiro.app.admin.product.web.ProductServlet
import com.github.giwiro.database.SQLiteDatabase.{closeConnection, configureDb}
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    configureDb
    context.mount(new CourierServlet, "/admin/courier/*")
    context.mount(new ProductServlet, "/admin/product/*")
  }

  override def destroy(context: ServletContext) {
    closeConnection
  }
}
