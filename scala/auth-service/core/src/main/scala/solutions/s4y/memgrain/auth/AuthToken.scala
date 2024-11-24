package solutions.s4y.memgrain.auth

sealed class AuthToken {

}

class AuthTokenString(val value: String) extends AuthToken {
  override def toString: String = value
}

object AuthToken {
  def apply(value: String): AuthToken = new AuthTokenString(value)
}