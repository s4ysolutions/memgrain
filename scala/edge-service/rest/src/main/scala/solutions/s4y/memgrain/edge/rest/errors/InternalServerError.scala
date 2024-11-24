package solutions.s4y.memgrain.edge.rest.errors
import zio.{Task, ZIO}
import zio.schema.{DeriveSchema, Schema}

case class InternalServerError(message: String) extends Throwable

object InternalServerError {
  def apply(message: String): InternalServerError = new InternalServerError(message)
  implicit val schema: Schema[InternalServerError] = DeriveSchema.gen
}

extension[T](zio: Task[T])
  def mapInternalServerError(using errorMapper: Throwable => InternalServerError): ZIO[Any, InternalServerError, T] =
    zio.mapError(errorMapper)

given Conversion[Throwable, InternalServerError] with
  def apply(throwable: Throwable): InternalServerError =
    InternalServerError( s"A server error occurred: ${throwable.toString}")