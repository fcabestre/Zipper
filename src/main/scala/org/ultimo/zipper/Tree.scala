package org.ultimo.zipper

sealed abstract class Tree[+I]
case class Item[+I] (i:I) extends Tree[I]
case class Section[+I] (s:List[Tree[I]]) extends Tree[I]