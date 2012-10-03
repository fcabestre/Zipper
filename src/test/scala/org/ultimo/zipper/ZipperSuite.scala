package org.ultimo.zipper

import org.scalatest.FunSuite

class ZipperSuite extends FunSuite {

  val tree = Section(List(Section(List(Item ("a"), Item ("*"), Item ("b"))), Item("+"), Section(List(Item ("c"), Item("*"), Item("d")))))

  test("Basic location test") {
    val loc = new Location(tree, Top)
    val item = loc.goDown
    assert(item == Item("+"))
  }

}
