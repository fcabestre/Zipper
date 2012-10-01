package org.utimo.zipper

case class Item[+I] (i:I) extends Tree[I]