package solutions.s4y.memgrain.users.directory.dependencies

import monix.eval.Task
import solutions.s4y.memgrain.users.directory.UserProfileBean

trait UsersDirectoryProvider {
  def getUserProfile(userId: String): Task[UserProfileBean]
}
