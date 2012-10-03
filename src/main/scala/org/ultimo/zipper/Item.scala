package org.ultimo.zipper

case class Item[+I] (i:I) extends Tree[I]