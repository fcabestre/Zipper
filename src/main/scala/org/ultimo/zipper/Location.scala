package org.ultimo.zipper

sealed case class Location[I] (t: Tree[I], p: Path[I]) {

  def goLeft : Location[I] = p match {
    case Top => throw new NoSuchElementException("Left of Top")
    case Node(l :: left, up, right) => Location(l, Node(left, up, t::right))
    case Node(Nil, up, right) => throw new NoSuchElementException("Left of first")
  }

  def goRight : Location[I] = p match {
    case Top => throw new NoSuchElementException("Right of Top")
    case Node(left, up, r :: right) => Location(r, Node(t :: left, up, right))
    case _ => throw new NoSuchElementException("Right of last")
  }

  def goUp : Location[I] = p match {
    case Top => throw new NoSuchElementException("Up of Top")
    case Node(left, up, right) => Location(Section(left.reverse ::: (t :: right)), up)
  }

  def goDown : Location[I] = t match {
    case Item (_) => throw new NoSuchElementException("Down of item")
    case Section(t1 :: trees) => Location(t1, Node(Nil, p, trees))
    case _ => throw new NoSuchElementException("Down of empty")
  }

  def change(t1 : Tree[I]) =  Location(t1, p)

  def insertLeft(t1 : Tree[I]) = p match {
    case Top => throw new NoSuchElementException("Insert of top")
    case Node(left, up, right) => Location(t, Node(t1 :: left, up, right))
  }

  def insertRight(t1 : Tree[I]) = p match {
    case Top => throw new NoSuchElementException("Insert of top")
    case Node(left, up, right) => Location(t, Node(left, up, t1 :: right))
  }

  def insertDown(t1 : Tree[I]) = t match {
    case Item(_) => throw new NoSuchElementException("Insert of down")
    case Section(sons) => Location(t1, Node(Nil, p, sons))
  }

  def delete : Location[I] = p match {
    case Top => throw new NoSuchElementException("Delete of top")
    case Node(left, up, r :: right) => Location(r, Node(left, up, right))
    case Node(l :: left, up, Nil) => Location(l, Node(left, up, Nil))
    case Node(Nil, up, Nil) => Location(Section(Nil), up)
  }
}
