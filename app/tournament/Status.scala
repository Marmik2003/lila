package lila
package tournament

sealed abstract class Status(val id: Int) extends Ordered[Status] {

  def compare(other: Status) = id compare other.id

  def name = toString

  def is(s: Status): Boolean = this == s
  def is(f: Status.type ⇒ Status): Boolean = is(f(Status))
}

object Status {

  case object Created extends Status(10)
  case object Started extends Status(20)
  case object Aborted extends Status(25) // from this point the game is finished
  case object Finished extends Status(30)

  val all = List(Created, Started, Aborted, Finished)

  val byId = all map { v ⇒ (v.id, v) } toMap

  def apply(id: Int): Option[Status] = byId get id
}
