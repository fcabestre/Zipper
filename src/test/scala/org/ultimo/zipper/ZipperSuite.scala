package org.ultimo.zipper

import org.scalatest.FunSuite

class ZipperSuite extends FunSuite {

  val leftTerm = Section(List(Item ("a"), Item ("*"), Item ("b")))
  val midTerm = Item("+")
  val rightTerm = Section(List(Item ("c"), Item("*"), Item("d")))
  val initialTree = Section(List(leftTerm, midTerm, rightTerm))
  val initialLocation = new Location(initialTree, Top)

  test("goDown test") {
    val loc = new Location(initialTree, Top)
    val expectedLoc = new Location(leftTerm, Node(Nil, Top, List(midTerm, rightTerm)))
    val result = loc.goDown
    assert(result == expectedLoc)
  }
}
