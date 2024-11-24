package solutions.s4y.memgrain.auth

trait AuthTokenFactory {
    def from(string: String): AuthToken
}
