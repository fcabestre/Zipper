package org.ultimo.zipper

class Location[I] (t: Tree[I], p: Path[I]) {

  def goLeft : Location[I] = p match {
    case Top => throw new NoSuchElementException("Left of Top")
    case Node(l :: left, up, right) => new Location(l, Node(left, up, t::right))
    case Node(Nil, up, right) => throw new NoSuchElementException("Left of first")
  }

  def goRight : Location[I] = p match {
    case Top => throw new NoSuchElementException("Right of Top")
    case Node(left, up, r :: right) => new Location(r, Node(t :: left, up, right))
    case _ => throw new NoSuchElementException("Right of last")
  }

  def goUp : Location[I] = p match {
    case Top => throw new NoSuchElementException("Up of Top")
    case Node(left, up, right) => new Location(Section(left.reverse ::: (t :: right)), up)
  }

  def goDown : Location[I] = t match {
    case Item (_) => throw new NoSuchElementException("Down of item")
    case Section(t1 :: trees) => new Location(t1, Node(Nil, p, trees))
    case _ => throw new NoSuchElementException("Down of empty")
  }
}
