package org.ultimo.zipper

import org.scalatest.FunSuite

class ZipperSuite extends FunSuite {

  val leftTerm = Section(List(Item ("a"), Item ("*"), Item ("b")))
  val midTerm = Item("+")
  val rightTerm = Section(List(Item ("c"), Item("*"), Item("d")))
  val otherTerm = Item("/")
  val initialTree = Section(List(leftTerm, midTerm, rightTerm))
  val initialLocation = new Location(initialTree, Top)

  test("goDown: nominal") {
    expect(Location(leftTerm, Node(Nil, Top, List(midTerm, rightTerm)))) {
      Location(initialTree, Top).goDown
    }
  }

  test("goDown: down of item ") {
    intercept[NoSuchElementException] {
      Location(Item(""), Top).goDown
    }
  }

  test("goDown: down of empty") {
    intercept[NoSuchElementException] {
      Location(Section(Nil), Top).goDown
    }
  }

  test("goUp: nominal") {
    expect(initialLocation) {
      Location(midTerm, Node(List(leftTerm), Top, List(rightTerm))).goUp
    }
  }

  test("goUp: up of top") {
    intercept[NoSuchElementException] {
      initialLocation.goUp
    }
  }

  test("goRight: nominal") {
    expect(Location(midTerm, Node(List(leftTerm), Top, List(rightTerm)))) {
      Location(leftTerm, Node(Nil, Top, List(midTerm, rightTerm))).goRight
    }
  }

  test("goRight: right of top") {
    intercept[NoSuchElementException] {
      initialLocation.goRight
    }
  }

  test("goRight: right of last") {
    intercept[NoSuchElementException] {
      Location(rightTerm, Node(List(midTerm, leftTerm), Top, Nil)).goRight
    }
  }

  test("goLeft: nominal") {
    expect(Location(leftTerm, Node(Nil, Top, List(midTerm, rightTerm)))) {
      Location(midTerm, Node(List(leftTerm), Top, List(rightTerm))).goLeft
    }
  }

  test("goRight: left of top") {
    intercept[NoSuchElementException] {
      initialLocation.goLeft
    }
  }

  test("goRight: left of first") {
    intercept[NoSuchElementException] {
      Location(leftTerm, Node(Nil, Top, List(midTerm, rightTerm))).goLeft
    }
  }

  test("change") {
    expect(Location(otherTerm, Node(List(leftTerm), Top, List(rightTerm)))) {
      Location(midTerm, Node(List(leftTerm), Top, List(rightTerm))).change(otherTerm)
    }
  }

  test("insertLeft: insert of top") {
    intercept[NoSuchElementException] {
      Location(initialTree, Top).insertLeft(otherTerm)
    }
  }

  test("insertLeft: nominal") {
    expect(Location(midTerm, Node(List(otherTerm, leftTerm), Top, List(rightTerm)))) {
      Location(midTerm, Node(List(leftTerm), Top, List(rightTerm))).insertLeft(otherTerm)
    }
  }

  test("insertRight: insert of top") {
    intercept[NoSuchElementException] {
      Location(initialTree, Top).insertRight(otherTerm)
    }
  }

  test("insertRight: nominal") {
    expect(Location(midTerm, Node(List(leftTerm), Top, List(otherTerm, rightTerm)))) {
      Location(midTerm, Node(List(leftTerm), Top, List(rightTerm))).insertRight(otherTerm)
    }
  }

  test("insertDown: insert of down") {
    intercept[NoSuchElementException] {
      Location(Item(""), Top).insertDown(otherTerm)
    }
  }

  test("insertDown: nominal") {
    expect(Location(otherTerm, Node(Nil, Top, List(leftTerm, midTerm, rightTerm)))) {
      Location(initialTree, Top).insertDown(otherTerm)
    }
  }

  test("delete: delete of top") {
    intercept[NoSuchElementException] {
      initialLocation.delete
    }
  }

  test("delete: nominal") {
    expect(Location(rightTerm, Node(List(leftTerm), Top, Nil))) {
      Location(midTerm, Node(List(leftTerm), Top, List(rightTerm))).delete
    }
  }

  test("delete: nominal right empty") {
    expect(Location(leftTerm, Node(Nil, Top, Nil))) {
      Location(rightTerm, Node(List(leftTerm), Top, Nil)).delete
    }
  }

  test("delete: nominal left and right empty") {
    expect(Location(Section(Nil), Top)) {
      Location(leftTerm, Node(Nil, Top, Nil)).delete
    }
  }
}
