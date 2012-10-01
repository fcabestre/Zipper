package org.utimo.zipper

case class Section[+I] (s:List[Tree[I]]) extends Tree[I]
