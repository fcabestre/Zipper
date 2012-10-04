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
}
