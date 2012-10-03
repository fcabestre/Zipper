package org.ultimo.zipper

case class Node[+I] (l:List[Tree[I]], p:Path[I], r:List[Tree[I]]) extends Path[I]
