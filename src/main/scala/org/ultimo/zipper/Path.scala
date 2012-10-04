package org.ultimo.zipper

sealed abstract class Path[+I]
case class Node[+I] (l:List[Tree[I]], p:Path[I], r:List[Tree[I]]) extends Path[I]
case object Top extends Path